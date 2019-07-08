package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpNormalizedData implements Parcelable {
    public static final Creator<MfpNormalizedData> CREATOR = new Creator<MfpNormalizedData>() {
        public MfpNormalizedData createFromParcel(Parcel parcel) {
            return new MfpNormalizedData(parcel);
        }

        public MfpNormalizedData[] newArray(int i) {
            return new MfpNormalizedData[i];
        }
    };
    @Expose
    private String ingredient = "";
    @Expose
    private Double quantity = Double.valueOf(0.0d);
    @Expose
    private String weight = "";

    public int describeContents() {
        return 0;
    }

    public MfpNormalizedData() {
    }

    public String getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(String str) {
        this.ingredient = str;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String str) {
        this.weight = str;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Double d) {
        this.quantity = d;
    }

    protected MfpNormalizedData(Parcel parcel) {
        this.ingredient = parcel.readString();
        this.weight = parcel.readString();
        this.quantity = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MfpNormalizedData mfpNormalizedData = (MfpNormalizedData) obj;
        String str = this.ingredient;
        if (str == null ? mfpNormalizedData.ingredient != null : !str.equals(mfpNormalizedData.ingredient)) {
            return false;
        }
        String str2 = this.weight;
        if (str2 == null ? mfpNormalizedData.weight != null : !str2.equals(mfpNormalizedData.weight)) {
            return false;
        }
        Double d = this.quantity;
        if (d != null) {
            z = d.equals(mfpNormalizedData.quantity);
        } else if (mfpNormalizedData.quantity != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.ingredient;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.weight;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Double d = this.quantity;
        if (d != null) {
            i = d.hashCode();
        }
        return hashCode2 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ingredient);
        parcel.writeString(this.weight);
        if (this.quantity == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeDouble(this.quantity.doubleValue());
    }
}
