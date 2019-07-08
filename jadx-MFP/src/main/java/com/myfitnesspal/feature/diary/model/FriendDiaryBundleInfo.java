package com.myfitnesspal.feature.diary.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.uacf.core.util.ParcelableUtil;
import java.util.Date;

public class FriendDiaryBundleInfo implements Parcelable {
    public static final Creator<FriendDiaryBundleInfo> CREATOR = new Creator<FriendDiaryBundleInfo>() {
        public FriendDiaryBundleInfo createFromParcel(Parcel parcel) {
            return new FriendDiaryBundleInfo(parcel);
        }

        public FriendDiaryBundleInfo[] newArray(int i) {
            return new FriendDiaryBundleInfo[i];
        }
    };
    private final Date currentDate;
    private String diaryToken;
    private final String userImageUrl;
    private long userMasterId;
    private final String userUid;
    private final String username;

    public int describeContents() {
        return 0;
    }

    public FriendDiaryBundleInfo(String str, String str2, String str3, long j, String str4, Date date) {
        this.username = str;
        this.userUid = str2;
        this.userImageUrl = str3;
        this.userMasterId = j;
        this.diaryToken = str4;
        this.currentDate = date;
    }

    private FriendDiaryBundleInfo(Parcel parcel) {
        this.username = parcel.readString();
        this.userUid = parcel.readString();
        this.userImageUrl = parcel.readString();
        this.userMasterId = parcel.readLong();
        this.diaryToken = parcel.readString();
        this.currentDate = ParcelableUtil.readDate(parcel);
    }

    public String getUsername() {
        return this.username;
    }

    public String getUserUid() {
        return this.userUid;
    }

    public String getUserImageUrl() {
        return this.userImageUrl;
    }

    public long getUserMasterId() {
        return this.userMasterId;
    }

    public String getDiaryToken() {
        return this.diaryToken;
    }

    public Date getCurrentDate() {
        return this.currentDate;
    }

    public void setDiaryToken(String str) {
        this.diaryToken = str;
    }

    public void setUserMasterId(long j) {
        this.userMasterId = j;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.username);
        parcel.writeString(this.userUid);
        parcel.writeString(this.userImageUrl);
        parcel.writeLong(this.userMasterId);
        parcel.writeString(this.diaryToken);
        ParcelableUtil.writeDate(parcel, this.currentDate);
    }
}
