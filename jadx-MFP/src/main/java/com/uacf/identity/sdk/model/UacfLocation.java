package com.uacf.identity.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;
import java.util.Arrays;

public class UacfLocation implements Parcelable {
    public static final Creator<UacfLocation> CREATOR = new Creator<UacfLocation>() {
        public UacfLocation createFromParcel(Parcel parcel) {
            return new UacfLocation(parcel);
        }

        public UacfLocation[] newArray(int i) {
            return new UacfLocation[i];
        }
    };
    @Expose
    private String country;
    @Expose
    private String postalCode;
    @Expose
    private String region;

    public int describeContents() {
        return 0;
    }

    public UacfLocation() {
    }

    public String getRegion() {
        return this.region;
    }

    public UacfLocation setRegion(String str) {
        this.region = str;
        return this;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public UacfLocation setPostalCode(String str) {
        this.postalCode = str;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public UacfLocation setCountry(String str) {
        this.country = str;
        return this;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UacfLocation uacfLocation = (UacfLocation) obj;
        if (!Strings.equalsIgnoreCase(this.region, uacfLocation.region) || !Strings.equalsIgnoreCase(this.postalCode, uacfLocation.postalCode) || !Strings.equalsIgnoreCase(this.country, uacfLocation.country)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.region, this.postalCode, this.country});
    }

    protected UacfLocation(Parcel parcel) {
        this.region = parcel.readString();
        this.postalCode = parcel.readString();
        this.country = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.region);
        parcel.writeString(this.postalCode);
        parcel.writeString(this.country);
    }
}
