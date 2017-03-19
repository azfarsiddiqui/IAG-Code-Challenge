package au.com.iag.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import au.com.iag.R;
import au.com.iag.adapter.PolicyListAdapter;
import au.com.iag.dal.PolicyDataLayer;
import au.com.iag.entity.Policy;
import au.com.iag.observer.AppNetworkObserver;
import au.com.iag.view.LinearRecyclerView;
import butterknife.InjectView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PolicyListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String KEY_BUNDLE_POLICY_LIST = "policy_list";

    @InjectView(R.id.policy_lrv)
    LinearRecyclerView mPolicyLrv;

    @InjectView(R.id.srl)
    SwipeRefreshLayout mPolicySrl;

    @InjectView(R.id.empty_tv)
    TextView mEmptyTv;

    private ArrayList<Policy> mPolicyList;

    Subscription mApiSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachUIListeners();

        if (savedInstanceState != null) {
            mPolicyList = savedInstanceState.getParcelableArrayList(KEY_BUNDLE_POLICY_LIST);
            if (mPolicyList != null) {
                updateUI();
            }
            else {
                dispatchApiRequestToGetPolicyList();
            }
        }
        else {
            dispatchApiRequestToGetPolicyList();
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_policy_list;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_BUNDLE_POLICY_LIST, mPolicyList);
    }


    @Override
    protected void onStop() {
        super.onStop();

        if (mApiSubscription != null && !mApiSubscription.isUnsubscribed()) {
            mApiSubscription.unsubscribe();
        }
    }

    private void attachUIListeners() {
        mPolicySrl.setOnRefreshListener(this);
    }

    private void dispatchApiRequestToGetPolicyList() {
        showLoader();

        PolicyDataLayer policyDataLayer = new PolicyDataLayer();

        mApiSubscription = policyDataLayer.getList().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new AppNetworkObserver<List<Policy>>(this) {
                    @Override
                    public void onNext(List<Policy> policyList) {
                        super.onNext(policyList);
                        mPolicyList = new ArrayList<>(policyList);
                        hideLoader();
                        updateUI();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        hideLoader();
                        updateUI();
                    }
                });
    }

    private void showLoader() {
        mPolicySrl.setRefreshing(true);
    }

    private void hideLoader() {
        mPolicySrl.setRefreshing(false);
    }

    private void updateUI() {
        boolean hasData = mPolicyList != null && mPolicyList.size() > 0;
        mEmptyTv.setVisibility(hasData ? View.GONE : View.VISIBLE);
        mPolicyLrv.setVisibility(hasData ? View.VISIBLE : View.GONE);

        if (hasData) {
            Collections.sort(mPolicyList);
            mPolicyLrv.setAdapter(new PolicyListAdapter(this, mPolicyList));
        }
    }

    @Override
    public void onRefresh() {
        dispatchApiRequestToGetPolicyList();
    }
}
