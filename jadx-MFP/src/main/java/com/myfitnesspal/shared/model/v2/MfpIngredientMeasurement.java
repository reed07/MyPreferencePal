package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpIngredientMeasurement implements Parcelable {
    public static final Creator<MfpIngredientMeasurement> CREATOR = new Creator<MfpIngredientMeasurement>() {
        public MfpIngredientMeasurement createFromParcel(Parcel parcel) {
            return new MfpIngredientMeasurement(parcel);
        }

        public MfpIngredientMeasurement[] newArray(int i) {
            return new MfpIngredientMeasurement[i];
        }
    };
    @Expose
    private String unit;
    @Expose
    private Double value;
    @Expose
    private String weightId;

    public int describeContents() {
        return 0;
    }

    public MfpIngredientMeasurement() {
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double d) {
        this.value = d;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getWeightId() {
        return this.weightId;
    }

    public void setWeightId(String str) {
        this.weightId = str;
    }

    protected MfpIngredientMeasurement(Parcel parcel) {
        this.value = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.unit = parcel.readString();
        this.weightId = parcel.readString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MfpIngredientMeasurement mfpIngredientMeasurement = (MfpIngredientMeasurement) obj;
        String str = this.unit;
        if (str == null ? mfpIngredientMeasurement.unit != null : !str.equals(mfpIngredientMeasurement.unit)) {
            return false;
        }
        Double d = this.value;
        if (d == null ? mfpIngredientMeasurement.value != null : !d.equals(mfpIngredientMeasurement.value)) {
            return false;
        }
        String str2 = this.weightId;
        if (str2 != null) {
            z = str2.equals(mfpIngredientMeasurement.weightId);
        } else if (mfpIngredientMeasurement.weightId != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.unit;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Double d = this.value;
        int hashCode2 = (hashCode + (d != null ? d.hashCode() : 0)) * 31;
        String str2 = this.weightId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.value == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.value.doubleValue());
        }
        parcel.writeString(this.unit);
        parcel.writeString(this.weightId);
    }
}
