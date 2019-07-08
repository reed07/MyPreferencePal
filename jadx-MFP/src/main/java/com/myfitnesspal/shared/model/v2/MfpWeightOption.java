package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpWeightOption implements Parcelable {
    public static final Creator<MfpWeightOption> CREATOR = new Creator<MfpWeightOption>() {
        public MfpWeightOption createFromParcel(Parcel parcel) {
            return new MfpWeightOption(parcel);
        }

        public MfpWeightOption[] newArray(int i) {
            return new MfpWeightOption[i];
        }
    };
    @Expose
    private Double confidence;
    @Expose
    private MfpServingSize servingSize;
    @Expose
    private Double servings;

    public int describeContents() {
        return 0;
    }

    public MfpWeightOption() {
    }

    public Double getServings() {
        return this.servings;
    }

    public void setServings(Double d) {
        this.servings = d;
    }

    public MfpServingSize getServingSize() {
        return this.servingSize;
    }

    public void setServingSize(MfpServingSize mfpServingSize) {
        this.servingSize = mfpServingSize;
    }

    public Double getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Double d) {
        this.confidence = d;
    }

    protected MfpWeightOption(Parcel parcel) {
        Double d = null;
        this.servings = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.servingSize = (MfpServingSize) parcel.readValue(MfpServingSize.class.getClassLoader());
        if (parcel.readByte() != 0) {
            d = Double.valueOf(parcel.readDouble());
        }
        this.confidence = d;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MfpWeightOption mfpWeightOption = (MfpWeightOption) obj;
        Double d = this.servings;
        if (d == null ? mfpWeightOption.servings != null : !d.equals(mfpWeightOption.servings)) {
            return false;
        }
        MfpServingSize mfpServingSize = this.servingSize;
        if (mfpServingSize == null ? mfpWeightOption.servingSize != null : !mfpServingSize.equals(mfpWeightOption.servingSize)) {
            return false;
        }
        Double d2 = this.confidence;
        if (d2 != null) {
            z = d2.equals(mfpWeightOption.confidence);
        } else if (mfpWeightOption.confidence != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        Double d = this.servings;
        int i = 0;
        int hashCode = (d != null ? d.hashCode() : 0) * 31;
        MfpServingSize mfpServingSize = this.servingSize;
        int hashCode2 = (hashCode + (mfpServingSize != null ? mfpServingSize.hashCode() : 0)) * 31;
        Double d2 = this.confidence;
        if (d2 != null) {
            i = d2.hashCode();
        }
        return hashCode2 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.servings == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.servings.doubleValue());
        }
        parcel.writeValue(this.servingSize);
        if (this.confidence == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeDouble(this.confidence.doubleValue());
    }
}
