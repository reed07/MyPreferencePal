package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;

public class MfpAvailabilityDetails implements Parcelable {
    public static final Creator<MfpAvailabilityDetails> CREATOR = new Creator<MfpAvailabilityDetails>() {
        public MfpAvailabilityDetails createFromParcel(Parcel parcel) {
            return new MfpAvailabilityDetails(parcel);
        }

        public MfpAvailabilityDetails[] newArray(int i) {
            return new MfpAvailabilityDetails[i];
        }
    };
    @Expose
    private String availabilityDate = "";
    @Expose
    private ArrayList<MfpPlatformDetails> availablePlatforms = new ArrayList<>();
    @Expose
    private String countryCode = "";
    @Expose
    private MfpProductPrice pricePoint = new MfpProductPrice();

    public int describeContents() {
        return 0;
    }

    public MfpAvailabilityDetails() {
    }

    public MfpAvailabilityDetails(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getAvailabilityDate() {
        return this.availabilityDate;
    }

    public void setAvailabilityDate(String str) {
        this.availabilityDate = str;
    }

    public MfpProductPrice getPricePoint() {
        return this.pricePoint;
    }

    public void setPricePoint(MfpProductPrice mfpProductPrice) {
        if (mfpProductPrice == null) {
            mfpProductPrice = new MfpProductPrice();
        }
        this.pricePoint = mfpProductPrice;
    }

    public ArrayList<MfpPlatformDetails> getAvailablePlatforms() {
        return this.availablePlatforms;
    }

    public void setAvailablePlatforms(ArrayList<MfpPlatformDetails> arrayList) {
        if (arrayList == null) {
            this.availablePlatforms.clear();
        } else {
            this.availablePlatforms = arrayList;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.countryCode);
        parcel.writeString(this.availabilityDate);
        parcel.writeParcelable(this.pricePoint, 0);
        parcel.writeList(this.availablePlatforms);
    }

    private void readFromParcel(Parcel parcel) {
        this.availablePlatforms.clear();
        this.countryCode = parcel.readString();
        this.availabilityDate = parcel.readString();
        this.pricePoint = (MfpProductPrice) parcel.readParcelable(MfpProductPrice.class.getClassLoader());
        parcel.readList(this.availablePlatforms, MfpPlatformDetails.class.getClassLoader());
    }
}
