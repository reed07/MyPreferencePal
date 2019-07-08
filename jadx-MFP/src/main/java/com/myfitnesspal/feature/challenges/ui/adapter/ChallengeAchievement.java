package com.myfitnesspal.feature.challenges.ui.adapter;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ChallengeAchievement implements Parcelable, AchievementListItem {
    public static final Creator<ChallengeAchievement> CREATOR = new Creator<ChallengeAchievement>() {
        public ChallengeAchievement createFromParcel(Parcel parcel) {
            return new ChallengeAchievement(parcel);
        }

        public ChallengeAchievement[] newArray(int i) {
            return new ChallengeAchievement[i];
        }
    };
    private String criteriaType;
    private String description;
    private String detailsUrl;
    private String emailBodyShare;
    private String emailSubjectShare;
    private String imageUrl;
    private boolean isEarned;
    private int progress;
    private int progressMax;
    private String title;
    private String twitterShare;

    public int describeContents() {
        return 0;
    }

    public ChallengeAchievement(String str, String str2, String str3, boolean z, int i, int i2, String str4, String str5, String str6, String str7, String str8) {
        this.title = str;
        this.description = str2;
        this.imageUrl = str3;
        this.isEarned = z;
        this.progress = i;
        this.progressMax = i2;
        this.criteriaType = str4;
        this.emailBodyShare = str5;
        this.emailSubjectShare = str6;
        this.twitterShare = str7;
        this.detailsUrl = str8;
    }

    protected ChallengeAchievement(Parcel parcel) {
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.imageUrl = parcel.readString();
        this.isEarned = parcel.readByte() != 0;
        this.progress = parcel.readInt();
        this.progressMax = parcel.readInt();
        this.criteriaType = parcel.readString();
        this.emailBodyShare = parcel.readString();
        this.emailSubjectShare = parcel.readString();
        this.twitterShare = parcel.readString();
        this.detailsUrl = parcel.readString();
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public boolean isEarned() {
        return this.isEarned;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getProgressMax() {
        return this.progressMax;
    }

    public String getCriteriaType() {
        return this.criteriaType;
    }

    public String getEmailBodyShare() {
        return this.emailBodyShare;
    }

    public String getEmailSubjectShare() {
        return this.emailSubjectShare;
    }

    public String getTwitterShare() {
        return this.twitterShare;
    }

    public String getDetailsUrl() {
        return this.detailsUrl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.imageUrl);
        parcel.writeByte(this.isEarned ? (byte) 1 : 0);
        parcel.writeInt(this.progress);
        parcel.writeInt(this.progressMax);
        parcel.writeString(this.criteriaType);
        parcel.writeString(this.emailBodyShare);
        parcel.writeString(this.emailSubjectShare);
        parcel.writeString(this.twitterShare);
        parcel.writeString(this.detailsUrl);
    }
}
