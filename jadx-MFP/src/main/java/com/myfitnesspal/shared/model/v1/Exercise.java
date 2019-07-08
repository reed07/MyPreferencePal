package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.ParcelableUtil;

public class Exercise extends FoodOrExercise implements Parcelable {
    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        public Exercise createFromParcel(Parcel parcel) {
            return new Exercise(parcel);
        }

        public Exercise[] newArray(int i) {
            return new Exercise[i];
        }
    };
    private boolean calorieAdjustmentExercise;
    private int exerciseType;
    private float mets;

    public boolean isExercise() {
        return true;
    }

    public int itemType() {
        return 2;
    }

    public Exercise() {
    }

    private Exercise(Parcel parcel) {
        super(parcel);
        this.exerciseType = parcel.readInt();
        this.mets = parcel.readFloat();
        this.calorieAdjustmentExercise = ParcelableUtil.readBoolean(parcel);
    }

    public int getExerciseType() {
        return this.exerciseType;
    }

    public boolean isCardio() {
        return getExerciseType() == 0;
    }

    public boolean isStrength() {
        return getExerciseType() == 1;
    }

    public void setExerciseType(int i) {
        this.exerciseType = i;
    }

    public float getMets() {
        return this.mets;
    }

    public void setMets(float f) {
        this.mets = f;
    }

    public Exercise initWithExerciseType(int i, String str) {
        setLocalId(0);
        setOwnerUserId(0);
        this.exerciseType = i;
        setDescription(str);
        return this;
    }

    public int cardioCaloriesBurnedForHours(float f, Session session) {
        return (int) (session.getUser().getProfile().getCurrentWeight().getKilograms() * f * this.mets);
    }

    public Object clone() {
        Exercise exercise = new Exercise();
        exercise.setUid(getUid());
        exercise.setOriginalUid(getOriginalUid());
        exercise.setLocalId(getLocalId());
        exercise.setMasterDatabaseId(getMasterDatabaseId());
        exercise.setOwnerUserId(getOwnerUserId());
        exercise.setOwnerUserMasterId(getOwnerUserMasterId());
        exercise.setSortPriority(getSortPriority());
        exercise.setIsDeleted(isDeleted());
        exercise.setIsPublic(isPublic());
        exercise.setDescription(getDescription());
        exercise.setExerciseType(getExerciseType());
        exercise.setMets(getMets());
        exercise.setCalorieAdjustmentExercise(isCalorieAdjustmentExercise());
        return exercise;
    }

    public boolean isCalorieAdjustmentExercise() {
        return this.calorieAdjustmentExercise;
    }

    public void setCalorieAdjustmentExercise(boolean z) {
        this.calorieAdjustmentExercise = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.exerciseType);
        parcel.writeFloat(this.mets);
        ParcelableUtil.writeBoolean(parcel, this.calorieAdjustmentExercise);
    }
}
