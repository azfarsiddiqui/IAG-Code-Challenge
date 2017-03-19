package au.com.iag.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import au.com.iag.R;

/**
 * Created by siddiquim on 3/17/17.
 */

public class Policy implements Parcelable, Comparable<Policy> {
    private static final SimpleDateFormat DATE_FORMATTER_UI = new SimpleDateFormat("dd MMM yy");

    private static HashMap<String, Integer> sIconResMap = new HashMap<String, Integer>() {{
        put("Comprehensive Car Insurance", R.drawable.ic_car_insurance);
        put("Home Buildings Insurance", R.drawable.ic_home_insurance);
        put("CTP GreenSlip Insurance", R.drawable.ic_ctp);
    }};

    @SerializedName("policy_number")
    private String mId;

    @SerializedName("start_date")
    private Date mStartDate;

    @SerializedName("renewal_date")
    private Date mRenewalDate;

    @SerializedName("policy_status")
    private String mStatus;

    @SerializedName("policy_type")
    private String mType;

    @SerializedName("description")
    private String mTitle;

    @SerializedName("premium")
    private float mPremium;

    protected Policy(Parcel in) {
        mId = in.readString();
        mStatus = in.readString();
        mType = in.readString();
        mTitle = in.readString();
        mPremium = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mStatus);
        dest.writeString(mType);
        dest.writeString(mTitle);
        dest.writeFloat(mPremium);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Policy> CREATOR = new Creator<Policy>() {
        @Override
        public Policy createFromParcel(Parcel in) {
            return new Policy(in);
        }

        @Override
        public Policy[] newArray(int size) {
            return new Policy[size];
        }
    };

    public String getId() {
        return mId;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public Date getRenewalDate() {
        return mRenewalDate;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getType() {
        return mType;
    }

    public String getTitle() {
        return mTitle;
    }

    public float getPremium() {
        return mPremium;
    }

    public boolean isExpired() {
        return true;
    }

    public String getFormattedStartDate() {
        return DATE_FORMATTER_UI.format(mStartDate);
    }

    public String getFormattedRenewalDate() {
        return DATE_FORMATTER_UI.format(mRenewalDate);
    }

    public boolean needsRenewal() {
        return mStatus.equals("Renewal");
    }

    public int getIconResId() {
        int iconResId = 0;

        if (sIconResMap.containsKey(mType)) {
            iconResId = sIconResMap.get(mType);
        }

        return iconResId;
    }

    @Override
    public int compareTo(Policy another) {
        if (getRenewalDate().getTime() > another.getRenewalDate().getTime()) return -1;
        return 1;
    }
}
