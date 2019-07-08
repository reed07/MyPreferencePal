package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.util.ParcelableUtil;
import java.util.Date;

public class MiniUserInfo extends DatabaseObject implements Parcelable {
    public static final Creator<MiniUserInfo> CREATOR = new Creator<MiniUserInfo>() {
        public MiniUserInfo createFromParcel(Parcel parcel) {
            return new MiniUserInfo(parcel);
        }

        public MiniUserInfo[] newArray(int i) {
            return new MiniUserInfo[i];
        }
    };
    private String city;
    private int diaryPrivacySetting;
    private int friendCount;
    private String fullsizeImageURL;
    private int gender;
    private String imageURL;
    private boolean isFriend;
    private boolean isProfileViewable;
    private Date lastLoginDate;
    private int loginStreak;
    private float poundsLost;
    private String state;
    private String username;

    public int describeContents() {
        return 0;
    }

    public int itemType() {
        return 19;
    }

    public static MiniUserInfo forCurrentUser(Session session) {
        User user = session.getUser();
        MiniUserInfo miniUserInfo = new MiniUserInfo();
        miniUserInfo.setMasterDatabaseId(user.getMasterDatabaseId());
        miniUserInfo.setUid(user.getUid());
        miniUserInfo.username = user.getUsername();
        return miniUserInfo;
    }

    public MiniUserInfo() {
    }

    private MiniUserInfo(Parcel parcel) {
        super(parcel);
        this.username = parcel.readString();
        this.imageURL = parcel.readString();
        this.fullsizeImageURL = parcel.readString();
        this.lastLoginDate = Database.decodeDateAndTimeStringAsLocalTime(parcel.readString());
        this.poundsLost = parcel.readFloat();
        this.isFriend = ParcelableUtil.readBoolean(parcel);
        this.diaryPrivacySetting = parcel.readInt();
        this.gender = parcel.readInt();
        this.city = parcel.readString();
        this.state = parcel.readString();
        this.friendCount = parcel.readInt();
        this.loginStreak = parcel.readInt();
        this.isProfileViewable = ParcelableUtil.readBoolean(parcel);
    }

    public boolean isCurrentUser(Session session) {
        return this.masterDatabaseId > 0 && session.getUser().getMasterDatabaseId() == this.masterDatabaseId;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public void setImageUrl(String str) {
        this.imageURL = str;
    }

    public void setFullsizeImageURL(String str) {
        this.fullsizeImageURL = str;
    }

    public String getFullsizeImageURL() {
        return this.fullsizeImageURL;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public int getGender() {
        return this.gender;
    }

    public void setIsFriend(boolean z) {
        this.isFriend = z;
    }

    public boolean isFriend() {
        return this.isFriend;
    }

    public void setLastLoginDate(Date date) {
        this.lastLoginDate = date;
    }

    public Date getLastLoginDate() {
        return this.lastLoginDate;
    }

    public void setPoundsLost(float f) {
        this.poundsLost = f;
    }

    public float getPoundsLost() {
        return this.poundsLost;
    }

    public String getUsername() {
        return this.username;
    }

    public String getImageUrl() {
        return this.imageURL;
    }

    public Boolean allowsDiaryToBeViewedByUser(Session session) {
        if (isCurrentUser(session)) {
            return Boolean.valueOf(false);
        }
        switch (this.diaryPrivacySetting) {
            case 0:
                return Boolean.valueOf(false);
            case 1:
                return Boolean.valueOf(true);
            case 2:
                return Boolean.valueOf(isFriend());
            case 3:
                return Boolean.valueOf(true);
            default:
                return Boolean.valueOf(false);
        }
    }

    public Boolean diaryRequiresPassword() {
        return Boolean.valueOf(this.diaryPrivacySetting == 3);
    }

    public int getDiaryPrivacySetting() {
        return this.diaryPrivacySetting;
    }

    public void setDiaryPrivacySetting(int i) {
        this.diaryPrivacySetting = i;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public int getFriendCount() {
        return this.friendCount;
    }

    public void setFriendCount(int i) {
        this.friendCount = i;
    }

    public int getLoginStreak() {
        return this.loginStreak;
    }

    public void setLoginStreak(int i) {
        this.loginStreak = i;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public boolean isProfileViewable() {
        return this.isProfileViewable;
    }

    public void setProfileViewable(boolean z) {
        this.isProfileViewable = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.username);
        parcel.writeString(this.imageURL);
        parcel.writeString(this.fullsizeImageURL);
        parcel.writeString(Database.encodeDateAndTime(this.lastLoginDate));
        parcel.writeFloat(this.poundsLost);
        ParcelableUtil.writeBoolean(parcel, this.isFriend);
        parcel.writeInt(this.diaryPrivacySetting);
        parcel.writeInt(this.gender);
        parcel.writeString(this.city);
        parcel.writeString(this.state);
        parcel.writeInt(this.friendCount);
        parcel.writeInt(this.loginStreak);
        ParcelableUtil.writeBoolean(parcel, this.isProfileViewable);
    }
}
