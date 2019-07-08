package com.myfitnesspal.feature.challenges.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class ChallengeSocial implements Parcelable {
    public static final Creator<ChallengeSocial> CREATOR = new Creator<ChallengeSocial>() {
        public ChallengeSocial createFromParcel(Parcel parcel) {
            return new ChallengeSocial(parcel);
        }

        public ChallengeSocial[] newArray(int i) {
            return new ChallengeSocial[i];
        }
    };
    @Expose
    private String emailBodyInvite;
    @Expose
    private String emailSubjectInvite;
    @Expose
    private String twitterInvite;

    public int describeContents() {
        return 0;
    }

    public ChallengeSocial(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getTwitterInvite() {
        return this.twitterInvite;
    }

    public String getEmailSubjectInvite() {
        return this.emailSubjectInvite;
    }

    public String getEmailBodyInvite() {
        return this.emailBodyInvite;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.twitterInvite);
        parcel.writeString(this.emailBodyInvite);
        parcel.writeString(this.emailSubjectInvite);
    }

    private void readFromParcel(Parcel parcel) {
        this.twitterInvite = parcel.readString();
        this.emailBodyInvite = parcel.readString();
        this.emailSubjectInvite = parcel.readString();
    }
}
