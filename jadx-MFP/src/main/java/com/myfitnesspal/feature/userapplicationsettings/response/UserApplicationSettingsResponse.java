package com.myfitnesspal.feature.userapplicationsettings.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import java.util.Map;

public class UserApplicationSettingsResponse implements Parcelable {
    public static Creator<UserApplicationSettingsResponse> CREATOR = new Creator<UserApplicationSettingsResponse>() {
        public UserApplicationSettingsResponse createFromParcel(Parcel parcel) {
            return new UserApplicationSettingsResponse(parcel);
        }

        public UserApplicationSettingsResponse[] newArray(int i) {
            return new UserApplicationSettingsResponse[i];
        }
    };
    @Expose
    private Map<String, UserApplicationSetting> settings;
    @Expose
    private String updatedAt;
    @Expose
    private String userId;

    public static class API_RESPONSE_MAPPER extends ApiResponse<UserApplicationSettingsResponse> {
    }

    public int describeContents() {
        return 0;
    }

    public UserApplicationSettingsResponse() {
    }

    public UserApplicationSettingsResponse(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setSettings(Map<String, UserApplicationSetting> map) {
        this.settings = map;
    }

    public Map<String, UserApplicationSetting> getSettings() {
        return this.settings;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.userId);
        parcel.writeMap(this.settings);
        parcel.writeString(this.updatedAt);
    }

    private void readFromParcel(Parcel parcel) {
        this.userId = parcel.readString();
        parcel.readMap(this.settings, UserApplicationSetting.class.getClassLoader());
        this.updatedAt = parcel.readString();
    }
}
