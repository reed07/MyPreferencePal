package com.twitter.sdk.android.core.internal.oauth;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.AuthToken;

public class OAuth2Token extends AuthToken implements Parcelable {
    public static final Creator<OAuth2Token> CREATOR = new Creator<OAuth2Token>() {
        public OAuth2Token createFromParcel(Parcel parcel) {
            return new OAuth2Token(parcel);
        }

        public OAuth2Token[] newArray(int i) {
            return new OAuth2Token[i];
        }
    };
    @SerializedName("access_token")
    private final String accessToken;
    @SerializedName("token_type")
    private final String tokenType;

    public int describeContents() {
        return 0;
    }

    public boolean isExpired() {
        return false;
    }

    public OAuth2Token(String str, String str2) {
        this.tokenType = str;
        this.accessToken = str2;
    }

    private OAuth2Token(Parcel parcel) {
        this.tokenType = parcel.readString();
        this.accessToken = parcel.readString();
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tokenType);
        parcel.writeString(this.accessToken);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OAuth2Token oAuth2Token = (OAuth2Token) obj;
        String str = this.accessToken;
        if (str == null ? oAuth2Token.accessToken != null : !str.equals(oAuth2Token.accessToken)) {
            return false;
        }
        String str2 = this.tokenType;
        return str2 == null ? oAuth2Token.tokenType == null : str2.equals(oAuth2Token.tokenType);
    }

    public int hashCode() {
        String str = this.tokenType;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.accessToken;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }
}
