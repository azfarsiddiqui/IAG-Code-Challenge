package au.com.iag.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import au.com.iag.R;
import au.com.iag.adapter.PolicyListAdapter;
import au.com.iag.entity.Policy;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class PolicyListActivity extends BaseActivity {

    @InjectView(R.id.policy_rv)
    RecyclerView mPolicyRv;

    private ArrayList<Policy> mPolicyList = new ArrayList<>();

    private static final String KEY_BUNDLE_POLICY_LIST = "policy_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        if (savedInstanceState != null) {
            mPolicyList = savedInstanceState.getParcelableArrayList(KEY_BUNDLE_POLICY_LIST);
            bindAdapter();
        }
        else {
            prepareDummyDateSource();
            bindAdapter();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_BUNDLE_POLICY_LIST, mPolicyList);
    }

    private void initUI() {
        setContentView(R.layout.activity_policy_list);
        ButterKnife.inject(this);

        //Setting up the RecyclerView should be moved to a custom RV subclass
        //or atleast the BaseActivity, this is boiler-plate..
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mPolicyRv.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mPolicyRv.getContext(), DividerItemDecoration.VERTICAL);
        mPolicyRv.addItemDecoration(dividerItemDecoration);
    }

    private void prepareDummyDateSource() {
        Policy policy1 = new Policy("MOT019396613", new Date(), new Date(), "Current", "Comprehensive Car Insurance", "388 GEORGE STREET SYDNEY 2000 NSW", 682.22f);
        Policy policy2 = new Policy("MOT019396613", new Date(), new Date(), "Current", "Comprehensive Car Insurance", "388 GEORGE STREET SYDNEY 2000 NSW", 682.22f);
        Policy policy3 = new Policy("MOT019396613", new Date(), new Date(), "Current", "Comprehensive Car Insurance", "388 GEORGE STREET SYDNEY 2000 NSW", 682.22f);
        Policy policy4 = new Policy("MOT019396613", new Date(), new Date(), "Current", "Comprehensive Car Insurance", "388 GEORGE STREET SYDNEY 2000 NSW", 682.22f);
        Policy policy5 = new Policy("MOT019396613", new Date(), new Date(), "Current", "Comprehensive Car Insurance", "388 GEORGE STREET SYDNEY 2000 NSW", 682.22f);
        Policy policy6 = new Policy("MOT019396613", new Date(), new Date(), "Current", "Comprehensive Car Insurance", "388 GEORGE STREET SYDNEY 2000 NSW", 682.22f);
        Policy policy7 = new Policy("MOT019396613", new Date(), new Date(), "Current", "Comprehensive Car Insurance", "388 GEORGE STREET SYDNEY 2000 NSW", 682.22f);
        Policy policy8 = new Policy("MOT019396613", new Date(), new Date(), "Current", "Comprehensive Car Insurance", "388 GEORGE STREET SYDNEY 2000 NSW", 682.22f);

        mPolicyList.add(policy1);
        mPolicyList.add(policy2);
        mPolicyList.add(policy3);
        mPolicyList.add(policy4);
        mPolicyList.add(policy5);
        mPolicyList.add(policy6);
        mPolicyList.add(policy7);
        mPolicyList.add(policy8);
    }

    private void bindAdapter() {
        mPolicyRv.setAdapter(new PolicyListAdapter(mPolicyList));
    }
}
