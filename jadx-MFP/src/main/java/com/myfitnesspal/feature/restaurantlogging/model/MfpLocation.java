package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MfpLocation implements Parcelable, Comparable<MfpLocation> {
    public static final Creator<MfpLocation> CREATOR = new Creator<MfpLocation>() {
        public MfpLocation createFromParcel(Parcel parcel) {
            return new MfpLocation(parcel);
        }

        public MfpLocation[] newArray(int i) {
            return new MfpLocation[i];
        }
    };
    @SerializedName("address_line_1")
    @Expose
    private String addressLine1;
    @SerializedName("address_line_2")
    @Expose
    private String addressLine2;
    @Expose
    private String city;
    @Expose
    private String countryCode;
    private float distanceFromUser;
    @Expose
    private double latitude;
    @Expose
    private double longitude;
    @Expose
    private String postalCode;
    @Expose
    private String state;

    public int describeContents() {
        return 0;
    }

    public MfpLocation(Parcel parcel) {
        this.addressLine1 = parcel.readString();
        this.addressLine2 = parcel.readString();
        this.city = parcel.readString();
        this.state = parcel.readString();
        this.postalCode = parcel.readString();
        this.countryCode = parcel.readString();
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public float getDistanceFromUser() {
        return this.distanceFromUser;
    }

    public void setDistanceFromUser(float f) {
        this.distanceFromUser = f;
    }

    public int compareTo(MfpLocation mfpLocation) {
        return Float.compare(this.distanceFromUser, mfpLocation.distanceFromUser);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.addressLine1);
        parcel.writeString(this.addressLine2);
        parcel.writeString(this.city);
        parcel.writeString(this.state);
        parcel.writeString(this.postalCode);
        parcel.writeString(this.countryCode);
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }
}
