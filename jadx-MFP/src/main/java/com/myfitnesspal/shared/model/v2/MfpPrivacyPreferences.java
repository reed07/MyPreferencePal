package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpPrivacyPreferences implements Parcelable {
    public static final Creator<MfpPrivacyPreferences> CREATOR = new Creator<MfpPrivacyPreferences>() {
        public MfpPrivacyPreferences createFromParcel(Parcel parcel) {
            return new MfpPrivacyPreferences(parcel);
        }

        public MfpPrivacyPreferences[] newArray(int i) {
            return new MfpPrivacyPreferences[i];
        }
    };
    @Expose
    private String diaryVisibility;
    @Expose
    private boolean friendVisibility;
    @Expose
    private String profileVisibility;

    public int describeContents() {
        return 0;
    }

    public MfpPrivacyPreferences() {
    }

    public MfpPrivacyPreferences(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setFriendVisibility(boolean z) {
        this.friendVisibility = z;
    }

    public void setDiaryVisibility(String str) {
        this.diaryVisibility = str;
    }

    public void setProfileVisibility(String str) {
        this.profileVisibility = str;
    }

    public boolean isFriendVisibility() {
        return this.friendVisibility;
    }

    public String getDiaryVisibility() {
        return this.diaryVisibility;
    }

    public String getProfileVisibility() {
        return this.profileVisibility;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.friendVisibility ? (byte) 1 : 0);
        parcel.writeString(this.diaryVisibility);
        parcel.writeString(this.profileVisibility);
    }

    private void readFromParcel(Parcel parcel) {
        this.friendVisibility = parcel.readByte() != 0;
        this.diaryVisibility = parcel.readString();
        this.profileVisibility = parcel.readString();
    }
}
