package au.com.iag.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by siddiquim on 3/17/17.
 */

public class Policy implements Parcelable {
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


    public Policy(String id, Date startDate, Date renewalDate, String status, String type, String title, float premium) {
        mId = id;
        mStartDate = startDate;
        mRenewalDate = renewalDate;
        mStatus = status;
        mType = type;
        mTitle = title;
        mPremium = premium;
    }

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
}
