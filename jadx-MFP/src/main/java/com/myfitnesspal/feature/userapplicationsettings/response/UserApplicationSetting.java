package com.myfitnesspal.feature.userapplicationsettings.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;

public class UserApplicationSetting implements Parcelable {
    public static Creator<UserApplicationSetting> CREATOR = new Creator<UserApplicationSetting>() {
        public UserApplicationSetting createFromParcel(Parcel parcel) {
            return new UserApplicationSetting(parcel);
        }

        public UserApplicationSetting[] newArray(int i) {
            return new UserApplicationSetting[i];
        }
    };
    private String name;
    private Integer syncFlag;
    @Expose
    private String type;
    private String updatedAt;
    private String userId;
    @Expose
    private String value;

    public static class API_RESPONSE_MAPPER extends ApiResponse<UserApplicationSetting> {
    }

    public int describeContents() {
        return 0;
    }

    public UserApplicationSetting() {
    }

    public UserApplicationSetting(String str, String str2, String str3, String str4, String str5) {
        this.name = str;
        this.userId = str2;
        this.value = str3;
        this.type = str4;
        this.updatedAt = str5;
    }

    public UserApplicationSetting(String str, String str2, String str3, String str4, String str5, Integer num) {
        this.name = str;
        this.userId = str2;
        this.value = str3;
        this.type = str4;
        this.updatedAt = str5;
        this.syncFlag = num;
    }

    private UserApplicationSetting(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public Integer getSyncFlag() {
        return this.syncFlag;
    }

    public void setSyncFlag(Integer num) {
        this.syncFlag = num;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.value);
        parcel.writeString(this.type);
        parcel.writeString(this.updatedAt);
    }

    private void readFromParcel(Parcel parcel) {
        this.name = parcel.readString();
        this.value = parcel.readString();
        this.type = parcel.readString();
        this.updatedAt = parcel.readString();
    }
}
