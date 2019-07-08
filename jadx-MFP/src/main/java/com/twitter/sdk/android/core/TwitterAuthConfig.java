package com.twitter.sdk.android.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TwitterAuthConfig implements Parcelable {
    public static final Creator<TwitterAuthConfig> CREATOR = new Creator<TwitterAuthConfig>() {
        public TwitterAuthConfig createFromParcel(Parcel parcel) {
            return new TwitterAuthConfig(parcel);
        }

        public TwitterAuthConfig[] newArray(int i) {
            return new TwitterAuthConfig[i];
        }
    };
    private final String consumerKey;
    private final String consumerSecret;

    public int describeContents() {
        return 0;
    }

    public int getRequestCode() {
        return 140;
    }

    public TwitterAuthConfig(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("TwitterAuthConfig must not be created with null consumer key or secret.");
        }
        this.consumerKey = sanitizeAttribute(str);
        this.consumerSecret = sanitizeAttribute(str2);
    }

    private TwitterAuthConfig(Parcel parcel) {
        this.consumerKey = parcel.readString();
        this.consumerSecret = parcel.readString();
    }

    public String getConsumerKey() {
        return this.consumerKey;
    }

    public String getConsumerSecret() {
        return this.consumerSecret;
    }

    static String sanitizeAttribute(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.consumerKey);
        parcel.writeString(this.consumerSecret);
    }
}
