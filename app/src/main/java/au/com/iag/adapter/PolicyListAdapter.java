package au.com.iag.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import au.com.iag.R;
import au.com.iag.entity.Policy;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by siddiquim on 3/17/17.
 */

public class PolicyListAdapter extends RecyclerView.Adapter<PolicyListAdapter.ViewHolder> {

    private List<Policy> mPolicyList;

    public PolicyListAdapter(@NonNull List<Policy> policyList) {
        mPolicyList = policyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_policy, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Policy policy = mPolicyList.get(position);

        holder.mTitleTv.setText(policy.getTitle());
        holder.mDateTv.setText(policy.getStartDate() + " - " + policy.getRenewalDate());
        holder.mTypeTv.setText(policy.getType());
        holder.mPremiumTv.setText("$" + policy.getPremium());
    }

    @Override
    public int getItemCount() {
        return mPolicyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.title_tv)
        public TextView mTitleTv;

        @InjectView(R.id.date_tv)
        public TextView mDateTv;

        @InjectView(R.id.type_tv)
        public TextView mTypeTv;

        @InjectView(R.id.premium_tv)
        public TextView mPremiumTv;

        @InjectView(R.id.type_iv)
        public ImageView mTypeIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
