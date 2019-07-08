package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;

public class FacebookLoggedInUser extends FacebookFriend {
    public static final Creator<FacebookLoggedInUser> CREATOR = new Creator<FacebookLoggedInUser>() {
        public FacebookLoggedInUser createFromParcel(Parcel parcel) {
            FacebookLoggedInUser facebookLoggedInUser = new FacebookLoggedInUser();
            facebookLoggedInUser.readFromParcel(parcel);
            return facebookLoggedInUser;
        }

        public FacebookLoggedInUser[] newArray(int i) {
            return new FacebookLoggedInUser[i];
        }
    };
    @Expose
    private String accessToken;

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.accessToken = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(Strings.toString(this.accessToken));
    }

    public boolean equals(Object obj) {
        return super.equals(obj) && (obj instanceof FacebookLoggedInUser) && Strings.equals(this.accessToken, ((FacebookLoggedInUser) obj).accessToken);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }
}
