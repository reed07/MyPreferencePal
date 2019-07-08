package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpLocationPreferences implements Parcelable {
    public static final Creator<MfpLocationPreferences> CREATOR = new Creator<MfpLocationPreferences>() {
        public MfpLocationPreferences createFromParcel(Parcel parcel) {
            return new MfpLocationPreferences(parcel);
        }

        public MfpLocationPreferences[] newArray(int i) {
            return new MfpLocationPreferences[i];
        }
    };
    @Expose
    private String city;
    @Expose
    private String countryCode;
    @Expose
    private String locale;
    @Expose
    private String postalCode;
    @Expose
    private String state;
    @Expose
    private String timeZone;

    public int describeContents() {
        return 0;
    }

    public MfpLocationPreferences() {
    }

    public MfpLocationPreferences(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setPostalCode(String str) {
        this.postalCode = str;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getState() {
        return this.state;
    }

    public String getCity() {
        return this.city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.timeZone);
        parcel.writeString(this.countryCode);
        parcel.writeString(this.locale);
        parcel.writeString(this.state);
        parcel.writeString(this.city);
        parcel.writeString(this.postalCode);
    }

    private void readFromParcel(Parcel parcel) {
        this.timeZone = parcel.readString();
        this.countryCode = parcel.readString();
        this.locale = parcel.readString();
        this.state = parcel.readString();
        this.city = parcel.readString();
        this.postalCode = parcel.readString();
    }
}
