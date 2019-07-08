package com.twitter.sdk.android.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class TwitterAuthToken extends AuthToken implements Parcelable {
    public static final Creator<TwitterAuthToken> CREATOR = new Creator<TwitterAuthToken>() {
        public TwitterAuthToken createFromParcel(Parcel parcel) {
            return new TwitterAuthToken(parcel);
        }

        public TwitterAuthToken[] newArray(int i) {
            return new TwitterAuthToken[i];
        }
    };
    @SerializedName("secret")
    public final String secret;
    @SerializedName("token")
    public final String token;

    public int describeContents() {
        return 0;
    }

    public TwitterAuthToken(String str, String str2) {
        this.token = str;
        this.secret = str2;
    }

    private TwitterAuthToken(Parcel parcel) {
        this.token = parcel.readString();
        this.secret = parcel.readString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("token=");
        sb.append(this.token);
        sb.append(",secret=");
        sb.append(this.secret);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.token);
        parcel.writeString(this.secret);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TwitterAuthToken)) {
            return false;
        }
        TwitterAuthToken twitterAuthToken = (TwitterAuthToken) obj;
        String str = this.secret;
        if (str == null ? twitterAuthToken.secret != null : !str.equals(twitterAuthToken.secret)) {
            return false;
        }
        String str2 = this.token;
        return str2 == null ? twitterAuthToken.token == null : str2.equals(twitterAuthToken.token);
    }

    public int hashCode() {
        String str = this.token;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.secret;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }
}
