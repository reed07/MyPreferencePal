package com.uacf.identity.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.uacf.core.app.UacfUserAccountDomain;
import io.uacf.core.consents.UacfUserConsentStatusProvider;

public class UacfAccountLink implements Parcelable {
    public static final Creator<UacfAccountLink> CREATOR = new Creator<UacfAccountLink>() {
        public UacfAccountLink createFromParcel(Parcel parcel) {
            return new UacfAccountLink(parcel);
        }

        public UacfAccountLink[] newArray(int i) {
            return new UacfAccountLink[i];
        }
    };
    @Expose
    private UacfUserAccountDomain domain;
    @SerializedName("domainUserId")
    @Expose
    private String domainUserId;
    @Expose
    private UacfUserConsentStatusProvider uacfUserConsentStatusProvider;

    public int describeContents() {
        return 0;
    }

    public UacfAccountLink() {
    }

    public UacfUserAccountDomain getDomain() {
        return this.domain;
    }

    public UacfAccountLink setDomain(UacfUserAccountDomain uacfUserAccountDomain) {
        this.domain = uacfUserAccountDomain;
        return this;
    }

    public String getDomainUserId() {
        return this.domainUserId;
    }

    public UacfAccountLink setDomainUserId(String str) {
        this.domainUserId = str;
        return this;
    }

    protected UacfAccountLink(Parcel parcel) {
        this.domain = (UacfUserAccountDomain) parcel.readValue(UacfUserAccountDomain.class.getClassLoader());
        this.domainUserId = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.domain);
        parcel.writeString(this.domainUserId);
    }

    public UacfUserConsentStatusProvider getUacfUserConsentStatusProvider() {
        return this.uacfUserConsentStatusProvider;
    }

    public UacfAccountLink setUacfUserConsentStatusProvider(UacfUserConsentStatusProvider uacfUserConsentStatusProvider2) {
        this.uacfUserConsentStatusProvider = uacfUserConsentStatusProvider2;
        return this;
    }
}
