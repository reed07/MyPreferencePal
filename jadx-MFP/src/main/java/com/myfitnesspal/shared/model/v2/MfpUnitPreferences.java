package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;

public class MfpUnitPreferences implements Parcelable {
    public static final Creator<MfpUnitPreferences> CREATOR = new Creator<MfpUnitPreferences>() {
        public MfpUnitPreferences createFromParcel(Parcel parcel) {
            return new MfpUnitPreferences(parcel);
        }

        public MfpUnitPreferences[] newArray(int i) {
            return new MfpUnitPreferences[i];
        }
    };
    @Expose(deserialize = true, serialize = false)
    private String bodyWeight;
    @Expose
    private String distance;
    @Expose
    private String energy;
    @Expose
    private String height;
    @Expose
    private String weight;

    public int describeContents() {
        return 0;
    }

    public MfpUnitPreferences() {
    }

    public MfpUnitPreferences(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setEnergy(String str) {
        this.energy = str;
    }

    public void setWeight(String str) {
        this.weight = str;
    }

    public void setDistance(String str) {
        this.distance = str;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public String getEnergy() {
        return this.energy;
    }

    public String getWeight() {
        return Strings.isEmpty(this.weight) ? this.bodyWeight : this.weight;
    }

    public String getDistance() {
        return this.distance;
    }

    public String getHeight() {
        return this.height;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.energy);
        parcel.writeString(this.bodyWeight);
        parcel.writeString(this.weight);
        parcel.writeString(this.distance);
        parcel.writeString(this.height);
    }

    private void readFromParcel(Parcel parcel) {
        this.energy = parcel.readString();
        this.bodyWeight = parcel.readString();
        this.weight = parcel.readString();
        this.distance = parcel.readString();
        this.height = parcel.readString();
    }
}
