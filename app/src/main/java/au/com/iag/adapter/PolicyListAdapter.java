package au.com.iag.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import au.com.iag.R;
import au.com.iag.entity.Policy;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by siddiquim on 3/17/17.
 */

public class PolicyListAdapter extends RecyclerView.Adapter<PolicyListAdapter.ViewHolder> {
    private Context mContext;
    private List<Policy> mPolicyList;

    public PolicyListAdapter(@NonNull Context context, @NonNull List<Policy> policyList) {
        mContext = context;
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

        int iconResId = policy.getIconResId();
        if (iconResId > 0) {
            holder.mTypeIv.setVisibility(View.VISIBLE);
            holder.mTypeIv.setImageResource(policy.getIconResId());
        }
        else {
            holder.mTypeIv.setVisibility(View.GONE);
        }

        holder.mTitleTv.setText(policy.getTitle());
        holder.mDateTv.setText(mContext.getString(R.string.policy_list_item_date_range,
                policy.getFormattedStartDate(), policy.getFormattedRenewalDate()));
        holder.mTypeTv.setText(policy.getType());
        holder.mPremiumTv.setText(mContext.getString(R.string.currency_text, policy.getPremium()));
        holder.mStatusTv.setVisibility(policy.needsRenewal() ? View.VISIBLE : View.GONE);
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

        @InjectView(R.id.status_tv)
        public TextView mStatusTv;

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
