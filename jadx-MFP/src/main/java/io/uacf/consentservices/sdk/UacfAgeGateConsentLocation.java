package io.uacf.consentservices.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UacfAgeGateConsentLocation implements Parcelable {
    public static final Creator<UacfAgeGateConsentLocation> CREATOR = new Creator<UacfAgeGateConsentLocation>() {
        public UacfAgeGateConsentLocation createFromParcel(Parcel parcel) {
            return new UacfAgeGateConsentLocation(parcel);
        }

        public UacfAgeGateConsentLocation[] newArray(int i) {
            return new UacfAgeGateConsentLocation[i];
        }
    };
    private int ageGate;
    private String isoCode;
    private String standard;

    public int describeContents() {
        return 0;
    }

    protected UacfAgeGateConsentLocation(Parcel parcel) {
        this.standard = parcel.readString();
        this.ageGate = parcel.readInt();
        this.isoCode = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.standard);
        parcel.writeInt(this.ageGate);
        parcel.writeString(this.isoCode);
    }
}
