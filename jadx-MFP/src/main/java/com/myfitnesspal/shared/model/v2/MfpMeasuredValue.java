package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpMeasuredValue implements Parcelable {
    public static final Creator<MfpMeasuredValue> CREATOR = new Creator<MfpMeasuredValue>() {
        public MfpMeasuredValue createFromParcel(Parcel parcel) {
            return new MfpMeasuredValue(parcel);
        }

        public MfpMeasuredValue[] newArray(int i) {
            return new MfpMeasuredValue[i];
        }
    };
    @Expose
    private String unit;
    @Expose
    private float value;

    public static final class Unit {
        public static final String CALORIES = "calories";
        public static final String FEET = "feet";
        public static final String INCHES = "inches";
        public static final String KILOGRAMS = "kilograms";
        public static final String KILOJOULES = "kilojoules";
        public static final String KILOMETERS = "kilometers";
        public static final String MILES = "miles";
        public static final String MILES_PER_HOUR = "miles_per_hour";
        public static final String POUNDS = "pounds";
        public static final String STONES = "stones";
    }

    public int describeContents() {
        return 0;
    }

    public MfpMeasuredValue() {
    }

    public MfpMeasuredValue(Parcel parcel) {
        readFromParcel(parcel);
    }

    public MfpMeasuredValue(String str, float f) {
        this.unit = str;
        this.value = f;
    }

    public static Float getValueSafe(MfpMeasuredValue mfpMeasuredValue) {
        if (mfpMeasuredValue != null) {
            return Float.valueOf(mfpMeasuredValue.getValue());
        }
        return null;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float f) {
        this.value = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.value);
        parcel.writeString(this.unit);
    }

    private void readFromParcel(Parcel parcel) {
        this.value = parcel.readFloat();
        this.unit = parcel.readString();
    }
}
