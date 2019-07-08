package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class MfpSocialPreferences implements Parcelable {
    public static final Creator<MfpSocialPreferences> CREATOR = new Creator<MfpSocialPreferences>() {
        public MfpSocialPreferences createFromParcel(Parcel parcel) {
            return new MfpSocialPreferences(parcel);
        }

        public MfpSocialPreferences[] newArray(int i) {
            return new MfpSocialPreferences[i];
        }
    };
    @Expose
    private List<String> autoUpdateNewsFeed;
    @Expose
    private List<String> facebook;
    @Expose
    private List<String> twitter;

    public int describeContents() {
        return 0;
    }

    public MfpSocialPreferences() {
    }

    public MfpSocialPreferences(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setAutoUpdateNewsFeed(List<String> list) {
        if (list == null) {
            this.autoUpdateNewsFeed = new ArrayList();
        } else {
            this.autoUpdateNewsFeed = list;
        }
    }

    public void setFacebook(List<String> list) {
        if (list == null) {
            this.facebook = new ArrayList();
        } else {
            this.facebook = list;
        }
    }

    public void setTwitter(List<String> list) {
        if (list == null) {
            this.twitter = new ArrayList();
        } else {
            this.twitter = list;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.autoUpdateNewsFeed);
        parcel.writeList(this.facebook);
        parcel.writeList(this.twitter);
    }

    private void readFromParcel(Parcel parcel) {
        this.autoUpdateNewsFeed.clear();
        parcel.readList(this.autoUpdateNewsFeed, String.class.getClassLoader());
        this.facebook.clear();
        parcel.readList(this.facebook, String.class.getClassLoader());
        this.twitter.clear();
        parcel.readList(this.twitter, String.class.getClassLoader());
    }
}
