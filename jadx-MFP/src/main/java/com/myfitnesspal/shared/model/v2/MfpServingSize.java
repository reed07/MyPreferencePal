package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.uacf.core.util.Strings;

public class MfpServingSize implements Parcelable, Comparable {
    public static final Creator<MfpServingSize> CREATOR = new Creator<MfpServingSize>() {
        public MfpServingSize createFromParcel(Parcel parcel) {
            return new MfpServingSize(parcel);
        }

        public MfpServingSize[] newArray(int i) {
            return new MfpServingSize[i];
        }
    };
    @Expose
    private Double nutritionMultiplier = Double.valueOf(1.0d);
    @Expose
    private Boolean recommended;
    @Expose
    private String unit;
    @Expose
    private Double value = Double.valueOf(1.0d);

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpServingSize> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpServingSize() {
    }

    public MfpServingSize(Parcel parcel) {
        readFromParcel(parcel);
    }

    public Double getValue() {
        Double d = this.value;
        return Double.valueOf(d == null ? 1.0d : d.doubleValue());
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

    public Double getNutritionMultiplier() {
        Double d = this.nutritionMultiplier;
        return Double.valueOf(d == null ? 1.0d : d.doubleValue());
    }

    public void setNutritionMultiplier(Double d) {
        this.nutritionMultiplier = d;
    }

    public Boolean getRecommended() {
        return this.recommended;
    }

    public void setRecommended(Boolean bool) {
        this.recommended = bool;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        if (this.value == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.value.doubleValue());
        }
        parcel.writeString(this.unit);
        if (this.nutritionMultiplier == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.nutritionMultiplier.doubleValue());
        }
        Boolean bool = this.recommended;
        if (bool != null && bool.booleanValue()) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
    }

    private void readFromParcel(Parcel parcel) {
        Double d = null;
        this.value = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.unit = parcel.readString();
        if (parcel.readByte() != 0) {
            d = Double.valueOf(parcel.readDouble());
        }
        this.nutritionMultiplier = d;
        this.recommended = Boolean.valueOf(parcel.readByte() != 0);
    }

    public String descriptionWithAmount() {
        return descriptionWithAmount(1.0f);
    }

    public String descriptionWithAmount(float f) {
        return initString(new String(), Strings.initStringWithFormattedFloat(f * this.value.floatValue(), 1));
    }

    public String initString(String str, String str2) {
        if (str2 == null || this.unit == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(" ");
        sb.append(this.unit);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MfpServingSize)) {
            return false;
        }
        MfpServingSize mfpServingSize = (MfpServingSize) obj;
        String str = this.unit;
        if (str == null ? mfpServingSize.unit != null : !str.equals(mfpServingSize.unit)) {
            return false;
        }
        Double d = this.value;
        return d == null ? mfpServingSize.value == null : d.equals(mfpServingSize.value);
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

    public int compareTo(Object obj) {
        return descriptionWithAmount().compareTo(((MfpServingSize) obj).descriptionWithAmount());
    }

    public FoodPortion toFoodPortion() {
        FoodPortion foodPortion = new FoodPortion();
        foodPortion.setGramWeight(getNutritionMultiplier().floatValue());
        foodPortion.setDescription(getUnit());
        foodPortion.setAmount(getValue().floatValue());
        return foodPortion;
    }
}
