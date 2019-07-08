package com.myfitnesspal.shared.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.uacf.core.util.Strings;

public class MiniUserInfoDto implements Parcelable {
    public static final Creator<MiniUserInfoDto> CREATOR = new Creator<MiniUserInfoDto>() {
        public MiniUserInfoDto createFromParcel(Parcel parcel) {
            return new MiniUserInfoDto(parcel);
        }

        public MiniUserInfoDto[] newArray(int i) {
            return new MiniUserInfoDto[i];
        }
    };
    private String imageURL;
    private long masterId;
    private String uid;
    private String username;

    public int describeContents() {
        return 0;
    }

    public MiniUserInfoDto() {
    }

    public MiniUserInfoDto(Parcel parcel) {
        this.username = parcel.readString();
        this.imageURL = parcel.readString();
        this.masterId = parcel.readLong();
        this.uid = parcel.readString();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String str) {
        this.imageURL = str;
    }

    public long getMasterId() {
        return this.masterId;
    }

    public void setMasterId(long j) {
        this.masterId = j;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Strings.toString(this.username));
        parcel.writeString(Strings.toString(this.imageURL));
        parcel.writeLong(this.masterId);
        parcel.writeString(this.uid);
    }
}
