package com.myfitnesspal.feature.goals.event;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ExerciseCaloriesUpdatedEvent implements Parcelable {
    public static final Creator<ExerciseCaloriesUpdatedEvent> CREATOR = new Creator<ExerciseCaloriesUpdatedEvent>() {
        public ExerciseCaloriesUpdatedEvent createFromParcel(Parcel parcel) {
            return new ExerciseCaloriesUpdatedEvent(parcel);
        }

        public ExerciseCaloriesUpdatedEvent[] newArray(int i) {
            return new ExerciseCaloriesUpdatedEvent[i];
        }
    };
    private int carbohydrates;
    private int fat;
    private int protein;

    public int describeContents() {
        return 0;
    }

    public ExerciseCaloriesUpdatedEvent() {
    }

    public ExerciseCaloriesUpdatedEvent(int i, int i2, int i3) {
        this.carbohydrates = i;
        this.protein = i2;
        this.fat = i3;
    }

    public ExerciseCaloriesUpdatedEvent(Parcel parcel) {
        readFromParcel(parcel);
    }

    public int getCarbohydrates() {
        return this.carbohydrates;
    }

    public int getProtein() {
        return this.protein;
    }

    public int getFat() {
        return this.fat;
    }

    public void setCarbohydrates(int i) {
        this.carbohydrates = i;
    }

    public void setProtein(int i) {
        this.protein = i;
    }

    public void setFat(int i) {
        this.fat = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.carbohydrates);
        parcel.writeInt(this.protein);
        parcel.writeInt(this.fat);
    }

    private void readFromParcel(Parcel parcel) {
        this.carbohydrates = parcel.readInt();
        this.protein = parcel.readInt();
        this.fat = parcel.readInt();
    }
}
