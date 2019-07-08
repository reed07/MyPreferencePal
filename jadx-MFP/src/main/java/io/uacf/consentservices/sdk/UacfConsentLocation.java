package io.uacf.consentservices.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UacfConsentLocation implements Parcelable, UacfConsentIsoCodeProvider {
    public static final Creator<UacfConsentLocation> CREATOR = new Creator<UacfConsentLocation>() {
        public UacfConsentLocation createFromParcel(Parcel parcel) {
            return new UacfConsentLocation(parcel);
        }

        public UacfConsentLocation[] newArray(int i) {
            return new UacfConsentLocation[i];
        }
    };
    private String displayName;
    private String isoCode;

    public int describeContents() {
        return 0;
    }

    protected UacfConsentLocation(Parcel parcel) {
        this.displayName = parcel.readString();
        this.isoCode = parcel.readString();
    }

    public String getIsoCode() {
        return this.isoCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.displayName);
        parcel.writeString(this.isoCode);
    }
}
