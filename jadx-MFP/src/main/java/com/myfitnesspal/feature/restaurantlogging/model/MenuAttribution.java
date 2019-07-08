package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MenuAttribution implements Parcelable {
    public static final Creator<MenuAttribution> CREATOR = new Creator<MenuAttribution>() {
        public MenuAttribution createFromParcel(Parcel parcel) {
            return new MenuAttribution(parcel);
        }

        public MenuAttribution[] newArray(int i) {
            return new MenuAttribution[i];
        }
    };
    @Expose
    private String attributionImage;
    @Expose
    private String attributionImageLink;
    @Expose
    private String secureAttributionImage;
    @Expose
    private String secureAttributionImageLink;

    public int describeContents() {
        return 0;
    }

    public MenuAttribution() {
    }

    private MenuAttribution(Parcel parcel) {
        this.secureAttributionImage = parcel.readString();
        this.attributionImage = parcel.readString();
        this.attributionImageLink = parcel.readString();
        this.secureAttributionImageLink = parcel.readString();
    }

    public String getAttributionImageLink() {
        return this.attributionImageLink;
    }

    public String getSecureAttributionImage() {
        return this.secureAttributionImage;
    }

    public String getAttributionImage() {
        return this.attributionImage;
    }

    public String getSecureAttributionImageLink() {
        return this.secureAttributionImageLink;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.secureAttributionImage);
        parcel.writeString(this.attributionImage);
        parcel.writeString(this.attributionImageLink);
        parcel.writeString(this.secureAttributionImageLink);
    }
}
