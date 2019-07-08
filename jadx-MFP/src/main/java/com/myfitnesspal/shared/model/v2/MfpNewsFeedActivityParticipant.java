package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;

public class MfpNewsFeedActivityParticipant implements Parcelable {
    public static final Creator<MfpNewsFeedActivityParticipant> CREATOR = new Creator<MfpNewsFeedActivityParticipant>() {
        public MfpNewsFeedActivityParticipant createFromParcel(Parcel parcel) {
            return new MfpNewsFeedActivityParticipant(parcel);
        }

        public MfpNewsFeedActivityParticipant[] newArray(int i) {
            return new MfpNewsFeedActivityParticipant[i];
        }
    };
    @Expose
    private String displayName;
    @Expose
    private String profilePhotoId;
    @Expose
    private String profilePhotoUrl;
    @Expose
    private String profileVisibility;
    @Expose
    private String relationship;
    @Expose
    private String userId;
    @Expose
    private String username;
    @Expose
    private MfpMeasuredValue weightLost;

    public static final class ProfileVisibility {
        public static final String NONE = "none";
        public static final String VISIBLE = "visible";
    }

    public static final class Relationship {
        public static final String FRIEND = "friend";
        public static final String NONE = "none";
    }

    public int describeContents() {
        return 0;
    }

    public MfpNewsFeedActivityParticipant() {
    }

    private MfpNewsFeedActivityParticipant(Parcel parcel) {
        this.userId = parcel.readString();
        this.username = parcel.readString();
        this.displayName = parcel.readString();
        this.relationship = parcel.readString();
        this.profileVisibility = parcel.readString();
        this.weightLost = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        this.profilePhotoId = parcel.readString();
        this.profilePhotoUrl = parcel.readString();
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public MfpNewsFeedActivityParticipant setDisplayName(String str) {
        this.displayName = str;
        return this;
    }

    public MfpMeasuredValue getWeightLost() {
        return this.weightLost;
    }

    public MfpNewsFeedActivityParticipant setWeightLost(MfpMeasuredValue mfpMeasuredValue) {
        this.weightLost = mfpMeasuredValue;
        return this;
    }

    public String getProfileVisibility() {
        return this.profileVisibility;
    }

    public MfpNewsFeedActivityParticipant setProfileVisibility(String str) {
        this.profileVisibility = str;
        return this;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public MfpNewsFeedActivityParticipant setRelationship(String str) {
        this.relationship = str;
        return this;
    }

    public String getProfilePhotoId() {
        return this.profilePhotoId;
    }

    public MfpNewsFeedActivityParticipant setProfilePhotoId(String str) {
        this.profilePhotoId = str;
        return this;
    }

    public String getProfilePhotoUrl() {
        return this.profilePhotoUrl;
    }

    public MfpNewsFeedActivityParticipant setProfilePhotoUrl(String str) {
        this.profilePhotoUrl = str;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public MfpNewsFeedActivityParticipant setUserId(String str) {
        this.userId = str;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public MfpNewsFeedActivityParticipant setUsername(String str) {
        this.username = str;
        return this;
    }

    public boolean isProfileVisible() {
        return Strings.equalsIgnoreCase(this.profileVisibility, ProfileVisibility.VISIBLE);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.userId);
        parcel.writeString(this.username);
        parcel.writeString(this.displayName);
        parcel.writeString(this.relationship);
        parcel.writeString(this.profileVisibility);
        parcel.writeParcelable(this.weightLost, i);
        parcel.writeString(this.profilePhotoId);
        parcel.writeString(this.profilePhotoUrl);
    }
}
