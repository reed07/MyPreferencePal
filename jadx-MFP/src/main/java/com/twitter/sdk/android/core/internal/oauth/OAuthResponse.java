package com.twitter.sdk.android.core.internal.oauth;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.twitter.sdk.android.core.TwitterAuthToken;

public class OAuthResponse implements Parcelable {
    public static final Creator<OAuthResponse> CREATOR = new Creator<OAuthResponse>() {
        public OAuthResponse createFromParcel(Parcel parcel) {
            return new OAuthResponse(parcel);
        }

        public OAuthResponse[] newArray(int i) {
            return new OAuthResponse[i];
        }
    };
    public final TwitterAuthToken authToken;
    public final long userId;
    public final String userName;

    public int describeContents() {
        return 0;
    }

    public OAuthResponse(TwitterAuthToken twitterAuthToken, String str, long j) {
        this.authToken = twitterAuthToken;
        this.userName = str;
        this.userId = j;
    }

    private OAuthResponse(Parcel parcel) {
        this.authToken = (TwitterAuthToken) parcel.readParcelable(TwitterAuthToken.class.getClassLoader());
        this.userName = parcel.readString();
        this.userId = parcel.readLong();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("authToken=");
        sb.append(this.authToken);
        sb.append(",userName=");
        sb.append(this.userName);
        sb.append(",userId=");
        sb.append(this.userId);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.authToken, i);
        parcel.writeString(this.userName);
        parcel.writeLong(this.userId);
    }
}
