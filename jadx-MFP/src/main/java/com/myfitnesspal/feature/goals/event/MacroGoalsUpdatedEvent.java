package com.myfitnesspal.feature.goals.event;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.uacf.core.util.ParcelableUtil;

public class MacroGoalsUpdatedEvent implements Parcelable {
    public static final Creator<MacroGoalsUpdatedEvent> CREATOR = new Creator<MacroGoalsUpdatedEvent>() {
        public MacroGoalsUpdatedEvent createFromParcel(Parcel parcel) {
            return new MacroGoalsUpdatedEvent(parcel);
        }

        public MacroGoalsUpdatedEvent[] newArray(int i) {
            return new MacroGoalsUpdatedEvent[i];
        }
    };
    private String analyticsEventsJSON;
    private float carbohydrates;
    private float fat;
    private boolean isGrams;
    private float localizedEnergyValue;
    private float protein;

    public int describeContents() {
        return 0;
    }

    public MacroGoalsUpdatedEvent(boolean z, float f, float f2, float f3, float f4, String str) {
        this.isGrams = z;
        this.localizedEnergyValue = f;
        this.carbohydrates = f2;
        this.protein = f3;
        this.fat = f4;
        this.analyticsEventsJSON = str;
    }

    public MacroGoalsUpdatedEvent(Parcel parcel) {
        readFromParcel(parcel);
    }

    public boolean isGrams() {
        return this.isGrams;
    }

    public float getCarbohydrates() {
        return this.carbohydrates;
    }

    public float getProtein() {
        return this.protein;
    }

    public float getFat() {
        return this.fat;
    }

    public float getLocalizedEnergyValue() {
        return this.localizedEnergyValue;
    }

    public String getAnalyticsEventsJSON() {
        return this.analyticsEventsJSON;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.localizedEnergyValue);
        parcel.writeFloat(this.carbohydrates);
        parcel.writeFloat(this.protein);
        parcel.writeFloat(this.fat);
        ParcelableUtil.writeBoolean(parcel, this.isGrams);
        parcel.writeString(this.analyticsEventsJSON);
    }

    private void readFromParcel(Parcel parcel) {
        this.localizedEnergyValue = parcel.readFloat();
        this.carbohydrates = parcel.readFloat();
        this.protein = parcel.readFloat();
        this.fat = parcel.readFloat();
        this.isGrams = ParcelableUtil.readBoolean(parcel);
        this.analyticsEventsJSON = parcel.readString();
    }
}
