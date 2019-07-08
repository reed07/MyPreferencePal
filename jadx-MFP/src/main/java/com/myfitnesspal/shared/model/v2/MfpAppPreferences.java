package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpAppPreferences implements Parcelable {
    public static final Creator<MfpAppPreferences> CREATOR = new Creator<MfpAppPreferences>() {
        public MfpAppPreferences createFromParcel(Parcel parcel) {
            return new MfpAppPreferences(parcel);
        }

        public MfpAppPreferences[] newArray(int i) {
            return new MfpAppPreferences[i];
        }
    };
    @Expose
    private boolean displayDiaryMealMacro;
    @Expose
    private boolean enableNegativeAdjustment;
    @Expose
    private boolean mealGoalsEnabled;

    public int describeContents() {
        return 0;
    }

    public MfpAppPreferences() {
    }

    public MfpAppPreferences(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setEnableNegativeAdjustment(boolean z) {
        this.enableNegativeAdjustment = z;
    }

    public boolean isEnableNegativeAdjustment() {
        return this.enableNegativeAdjustment;
    }

    public void setDisplayDiaryMealMacro(boolean z) {
        this.displayDiaryMealMacro = z;
    }

    public boolean isDisplayDiaryMealMacro() {
        return this.displayDiaryMealMacro;
    }

    public void setMealGoalsEnabled(boolean z) {
        this.mealGoalsEnabled = z;
    }

    public boolean isMealGoalsEnabled() {
        return this.mealGoalsEnabled;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.enableNegativeAdjustment ? (byte) 1 : 0);
        parcel.writeByte(this.displayDiaryMealMacro ? (byte) 1 : 0);
        parcel.writeByte(this.mealGoalsEnabled ? (byte) 1 : 0);
    }

    private void readFromParcel(Parcel parcel) {
        boolean z = true;
        this.enableNegativeAdjustment = parcel.readByte() != 0;
        this.displayDiaryMealMacro = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.mealGoalsEnabled = z;
    }
}
