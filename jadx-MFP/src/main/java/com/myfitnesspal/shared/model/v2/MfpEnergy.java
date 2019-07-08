package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;

public class MfpEnergy implements Parcelable {
    public static final Creator<MfpEnergy> CREATOR = new Creator<MfpEnergy>() {
        public MfpEnergy createFromParcel(Parcel parcel) {
            return new MfpEnergy(parcel);
        }

        public MfpEnergy[] newArray(int i) {
            return new MfpEnergy[i];
        }
    };
    @Expose
    private String unit = "calories";
    @Expose
    private Double value;

    public static final class Units {
        public static final String CALORIES = "calories";
        public static final String KILOJOULES = "kilojoules";
    }

    public int describeContents() {
        return 0;
    }

    public MfpEnergy() {
    }

    public MfpEnergy(Double d, String str) {
        this.value = d;
        this.unit = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double d) {
        this.value = d;
    }

    public float getCaloriesValue() {
        double doubleValue = NumberUtils.getDoubleValue(getValue());
        if (!isUnitCalories()) {
            doubleValue = UnitsUtils.getCalories(doubleValue);
        }
        return (float) doubleValue;
    }

    public boolean isUnitCalories() {
        return Strings.equals(getUnit(), "calories");
    }

    protected MfpEnergy(Parcel parcel) {
        this.unit = parcel.readString();
        this.value = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MfpEnergy mfpEnergy = (MfpEnergy) obj;
        String str = this.unit;
        if (str == null ? mfpEnergy.unit != null : !str.equals(mfpEnergy.unit)) {
            return false;
        }
        Double d = this.value;
        if (d != null) {
            z = d.equals(mfpEnergy.value);
        } else if (mfpEnergy.value != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.unit;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Double d = this.value;
        if (d != null) {
            i = d.hashCode();
        }
        return hashCode + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.unit);
        if (this.value == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeDouble(this.value.doubleValue());
    }
}
