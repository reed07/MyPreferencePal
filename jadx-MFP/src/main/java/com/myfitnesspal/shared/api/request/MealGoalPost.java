package com.myfitnesspal.shared.api.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MealGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import java.util.ArrayList;

public class MealGoalPost implements Parcelable {
    public static final Creator<MealGoalPost> CREATOR = new Creator<MealGoalPost>() {
        public MealGoalPost createFromParcel(Parcel parcel) {
            return new MealGoalPost(parcel);
        }

        public MealGoalPost[] newArray(int i) {
            return new MealGoalPost[i];
        }
    };
    @Expose
    private MfpMeasuredValue energy;
    @Expose
    private int mealIndex;

    public static class LIST_MAPPER extends ArrayList<MealGoalPost> {
    }

    public int describeContents() {
        return 0;
    }

    public MealGoalPost() {
    }

    public MealGoalPost(MealGoal mealGoal) {
        this.mealIndex = mealGoal.getMealIndex();
        this.energy = mealGoal.getEnergy();
    }

    public MealGoalPost(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setMealIndex(int i) {
        this.mealIndex = i;
    }

    public void setEnergy(MfpMeasuredValue mfpMeasuredValue) {
        this.energy = mfpMeasuredValue;
    }

    public int getMealIndex() {
        return this.mealIndex;
    }

    public MfpMeasuredValue getEnergy() {
        return this.energy;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mealIndex);
        if (this.energy != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.energy, 0);
            return;
        }
        parcel.writeByte(0);
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        this.mealIndex = parcel.readInt();
        if (parcel.readByte() == 1) {
            this.energy = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
    }
}
