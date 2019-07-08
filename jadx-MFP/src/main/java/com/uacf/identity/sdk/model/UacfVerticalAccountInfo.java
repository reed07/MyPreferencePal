package com.uacf.identity.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.mapping.GsonMappableYmdDate;
import com.uacf.core.util.ParcelableUtil;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfUserAccountDomain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UacfVerticalAccountInfo implements Parcelable {
    public static final Creator<UacfVerticalAccountInfo> CREATOR = new Creator<UacfVerticalAccountInfo>() {
        public UacfVerticalAccountInfo createFromParcel(Parcel parcel) {
            return new UacfVerticalAccountInfo(parcel);
        }

        public UacfVerticalAccountInfo[] newArray(int i) {
            return new UacfVerticalAccountInfo[i];
        }
    };
    /* access modifiers changed from: private */
    @Expose
    public String accessToken;
    /* access modifiers changed from: private */
    @Expose
    public List<UacfAccountLink> accountLinks;
    /* access modifiers changed from: private */
    @Expose
    public UacfAppId appId;
    /* access modifiers changed from: private */
    @Expose
    public GsonMappableYmdDate birthdate;
    /* access modifiers changed from: private */
    @Expose
    public String displayName;
    /* access modifiers changed from: private */
    @Expose
    public UacfUserAccountDomain domain;
    /* access modifiers changed from: private */
    @Expose
    public String emailAddress;
    /* access modifiers changed from: private */
    @Expose
    public long expirationTime;
    /* access modifiers changed from: private */
    @Expose
    public String firstName;
    /* access modifiers changed from: private */
    @Expose
    public String fullName;
    /* access modifiers changed from: private */
    @Expose
    public String gender;
    /* access modifiers changed from: private */
    @Expose
    public Double height;
    /* access modifiers changed from: private */
    @Expose
    public String lastName;
    /* access modifiers changed from: private */
    @Expose
    public UacfLocation location;
    /* access modifiers changed from: private */
    @Expose
    public String profilePictureUri;
    /* access modifiers changed from: private */
    @Expose
    public String refreshToken;
    /* access modifiers changed from: private */
    @Expose
    public List<UacfSocialMediaLink> socialMediaLinks;
    /* access modifiers changed from: private */
    @Expose
    public String uacfUserId;
    /* access modifiers changed from: private */
    @Expose
    public String userLocale;
    /* access modifiers changed from: private */
    @Expose
    public Double weight;

    public static final class Builder {
        private String accessToken;
        private List<UacfAccountLink> accountLinks;
        private UacfAppId appId;
        private Date birthdate;
        private String displayName;
        private UacfUserAccountDomain domain;
        private String emailAddress;
        private long expirationTime;
        private String firstName;
        private String fullName;
        private String gender;
        private Double height;
        private String lastName;
        private UacfLocation location;
        private String profilePictureUri;
        private String refreshToken;
        private List<UacfSocialMediaLink> socialMediaLinks;
        private String uacfUserId;
        private String userLocale;
        private Double weight;

        public Builder withAppId(UacfAppId uacfAppId) {
            this.appId = uacfAppId;
            return this;
        }

        public Builder withDomain(UacfUserAccountDomain uacfUserAccountDomain) {
            this.domain = uacfUserAccountDomain;
            return this;
        }

        public Builder withUserLocale(String str) {
            this.userLocale = str;
            return this;
        }

        public Builder withUacfUserId(String str) {
            this.uacfUserId = str;
            return this;
        }

        public Builder withAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public Builder withRefreshToken(String str) {
            this.refreshToken = str;
            return this;
        }

        public Builder withFullName(String str) {
            this.fullName = str;
            return this;
        }

        public Builder withLastName(String str) {
            this.lastName = str;
            return this;
        }

        public Builder withFirstName(String str) {
            this.firstName = str;
            return this;
        }

        public Builder withDisplayName(String str) {
            this.displayName = str;
            return this;
        }

        public Builder withBirthdate(Date date) {
            this.birthdate = date;
            return this;
        }

        public Builder withEmailAddress(String str) {
            this.emailAddress = str;
            return this;
        }

        public Builder withGender(String str) {
            this.gender = str;
            return this;
        }

        public Builder withWeight(Double d) {
            this.weight = d;
            return this;
        }

        public Builder withHeight(Double d) {
            this.height = d;
            return this;
        }

        public Builder withLocation(UacfLocation uacfLocation) {
            this.location = uacfLocation;
            return this;
        }

        public Builder withProfilePictureUri(String str) {
            this.profilePictureUri = str;
            return this;
        }

        public Builder withExpirationTime(long j) {
            this.expirationTime = j;
            return this;
        }

        public Builder withAccountLinks(List<UacfAccountLink> list) {
            this.accountLinks = list != null ? new ArrayList(list) : null;
            return this;
        }

        public Builder withSocialMediaLinks(List<UacfSocialMediaLink> list) {
            this.socialMediaLinks = list != null ? new ArrayList(list) : null;
            return this;
        }

        public UacfVerticalAccountInfo build() {
            UacfVerticalAccountInfo uacfVerticalAccountInfo = new UacfVerticalAccountInfo();
            uacfVerticalAccountInfo.appId = this.appId;
            uacfVerticalAccountInfo.uacfUserId = this.uacfUserId;
            uacfVerticalAccountInfo.domain = this.domain;
            uacfVerticalAccountInfo.userLocale = this.userLocale;
            uacfVerticalAccountInfo.accessToken = this.accessToken;
            uacfVerticalAccountInfo.refreshToken = this.refreshToken;
            uacfVerticalAccountInfo.fullName = this.fullName;
            uacfVerticalAccountInfo.lastName = this.lastName;
            uacfVerticalAccountInfo.firstName = this.firstName;
            uacfVerticalAccountInfo.displayName = this.displayName;
            uacfVerticalAccountInfo.birthdate = GsonMappableYmdDate.newInstance(this.birthdate);
            uacfVerticalAccountInfo.emailAddress = this.emailAddress;
            uacfVerticalAccountInfo.profilePictureUri = this.profilePictureUri;
            uacfVerticalAccountInfo.gender = this.gender;
            uacfVerticalAccountInfo.height = this.height;
            uacfVerticalAccountInfo.weight = this.weight;
            uacfVerticalAccountInfo.location = this.location;
            uacfVerticalAccountInfo.expirationTime = this.expirationTime;
            uacfVerticalAccountInfo.accountLinks = this.accountLinks;
            uacfVerticalAccountInfo.socialMediaLinks = this.socialMediaLinks;
            return uacfVerticalAccountInfo;
        }
    }

    public int describeContents() {
        return 0;
    }

    private UacfVerticalAccountInfo() {
    }

    public UacfUserAccountDomain getDomain() {
        return this.domain;
    }

    public String getUserLocale() {
        return this.userLocale;
    }

    public String getUacfUserId() {
        return this.uacfUserId;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public GsonMappableYmdDate getBirthdate() {
        return this.birthdate;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getGender() {
        return this.gender;
    }

    public Double getWeight() {
        return this.weight;
    }

    public Double getHeight() {
        return this.height;
    }

    public UacfLocation getLocation() {
        return this.location;
    }

    public List<UacfAccountLink> getAccountLinks() {
        return this.accountLinks;
    }

    protected UacfVerticalAccountInfo(Parcel parcel) {
        this.appId = (UacfAppId) parcel.readValue(UacfAppId.class.getClassLoader());
        this.uacfUserId = parcel.readString();
        this.domain = (UacfUserAccountDomain) parcel.readValue(UacfUserAccountDomain.class.getClassLoader());
        parcel.readString();
        parcel.readString();
        this.userLocale = parcel.readString();
        this.accessToken = parcel.readString();
        this.refreshToken = parcel.readString();
        this.fullName = parcel.readString();
        this.lastName = parcel.readString();
        this.firstName = parcel.readString();
        this.displayName = parcel.readString();
        this.birthdate = (GsonMappableYmdDate) parcel.readValue(GsonMappableYmdDate.class.getClassLoader());
        this.emailAddress = parcel.readString();
        this.profilePictureUri = parcel.readString();
        this.gender = parcel.readString();
        this.weight = ParcelableUtil.readDoubleObject(parcel);
        this.height = ParcelableUtil.readDoubleObject(parcel);
        this.location = (UacfLocation) parcel.readValue(UacfLocation.class.getClassLoader());
        this.expirationTime = parcel.readLong();
        this.accountLinks = parcel.readArrayList(UacfAccountLink.class.getClassLoader());
        this.socialMediaLinks = parcel.readArrayList(UacfSocialMediaLink.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.appId);
        parcel.writeString(this.uacfUserId);
        parcel.writeValue(this.domain);
        parcel.writeString("");
        parcel.writeString("");
        parcel.writeString(this.userLocale);
        parcel.writeString(this.accessToken);
        parcel.writeString(this.refreshToken);
        parcel.writeString(this.fullName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.firstName);
        parcel.writeString(this.displayName);
        parcel.writeValue(this.birthdate);
        parcel.writeString(this.emailAddress);
        parcel.writeString(this.profilePictureUri);
        parcel.writeString(this.gender);
        ParcelableUtil.writeDoubleObject(parcel, this.weight);
        ParcelableUtil.writeDoubleObject(parcel, this.height);
        parcel.writeValue(this.location);
        parcel.writeLong(this.expirationTime);
        parcel.writeList(this.accountLinks);
        parcel.writeList(this.socialMediaLinks);
    }
}
