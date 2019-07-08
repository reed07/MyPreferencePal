package com.uacf.identity.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import io.uacf.core.app.UacfSocialNetworkProvider;

public class UacfSocialMediaLink implements Parcelable {
    public static final Creator<UacfSocialMediaLink> CREATOR = new Creator<UacfSocialMediaLink>() {
        public UacfSocialMediaLink createFromParcel(Parcel parcel) {
            return new UacfSocialMediaLink(parcel);
        }

        public UacfSocialMediaLink[] newArray(int i) {
            return new UacfSocialMediaLink[i];
        }
    };
    /* access modifiers changed from: private */
    @Expose
    public String appId;
    /* access modifiers changed from: private */
    @Expose
    public String authToken;
    /* access modifiers changed from: private */
    @Expose
    public String expiry;
    /* access modifiers changed from: private */
    @Expose
    public UacfSocialNetworkProvider provider;
    /* access modifiers changed from: private */
    @Expose
    public String refreshToken;
    /* access modifiers changed from: private */
    @Expose
    public Long userId;
    /* access modifiers changed from: private */
    @Expose
    public String username;

    public static final class Builder {
        private String appId;
        private String authToken;
        private String expiry;
        private UacfSocialNetworkProvider provider;
        private String refreshToken;
        private Long userId;
        private String username;

        public Builder withUserId(Long l) {
            this.userId = l;
            return this;
        }

        public Builder withProvider(UacfSocialNetworkProvider uacfSocialNetworkProvider) {
            this.provider = uacfSocialNetworkProvider;
            return this;
        }

        public Builder withUsername(String str) {
            this.username = str;
            return this;
        }

        public Builder withAppId(String str) {
            this.appId = str;
            return this;
        }

        public Builder withAuthToken(String str) {
            this.authToken = str;
            return this;
        }

        public Builder withExpiry(String str) {
            this.expiry = str;
            return this;
        }

        public Builder withRefreshToken(String str) {
            this.refreshToken = str;
            return this;
        }

        public UacfSocialMediaLink build() {
            UacfSocialMediaLink uacfSocialMediaLink = new UacfSocialMediaLink();
            uacfSocialMediaLink.userId = this.userId;
            uacfSocialMediaLink.provider = this.provider;
            uacfSocialMediaLink.username = this.username;
            uacfSocialMediaLink.appId = this.appId;
            uacfSocialMediaLink.authToken = this.authToken;
            uacfSocialMediaLink.expiry = this.expiry;
            uacfSocialMediaLink.refreshToken = this.refreshToken;
            return uacfSocialMediaLink;
        }
    }

    public int describeContents() {
        return 0;
    }

    public UacfSocialMediaLink() {
    }

    protected UacfSocialMediaLink(Parcel parcel) {
        this.userId = parcel.readByte() == 0 ? null : Long.valueOf(parcel.readLong());
        this.provider = (UacfSocialNetworkProvider) parcel.readValue(UacfSocialNetworkProvider.class.getClassLoader());
        this.username = parcel.readString();
        this.appId = parcel.readString();
        this.authToken = parcel.readString();
        this.expiry = parcel.readString();
        this.refreshToken = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.userId == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeLong(this.userId.longValue());
        }
        parcel.writeValue(this.provider);
        parcel.writeString(this.username);
        parcel.writeString(this.appId);
        parcel.writeString(this.authToken);
        parcel.writeString(this.expiry);
        parcel.writeString(this.refreshToken);
    }
}
