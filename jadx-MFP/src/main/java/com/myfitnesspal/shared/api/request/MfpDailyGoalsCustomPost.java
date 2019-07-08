package com.myfitnesspal.shared.api.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;

public class MfpDailyGoalsCustomPost extends MfpDayOfWeekDailyGoalPost implements Parcelable {
    public static final Creator<MfpDailyGoalsCustomPost> CREATOR = new Creator<MfpDailyGoalsCustomPost>() {
        public MfpDailyGoalsCustomPost createFromParcel(Parcel parcel) {
            return new MfpDailyGoalsCustomPost(parcel);
        }

        public MfpDailyGoalsCustomPost[] newArray(int i) {
            return new MfpDailyGoalsCustomPost[i];
        }
    };
    @Expose
    private int exerciseCarbohydratesPercentage;
    @Expose
    private int exerciseFatPercentage;
    @Expose
    private int exerciseProteinPercentage;

    public int describeContents() {
        return 0;
    }

    public MfpDailyGoalsCustomPost() {
    }

    public MfpDailyGoalsCustomPost(Parcel parcel) {
        readFromParcel(parcel);
    }

    public MfpDailyGoalsCustomPost(MfpDailyGoal mfpDailyGoal) {
        super(mfpDailyGoal);
        this.exerciseCarbohydratesPercentage = mfpDailyGoal.getExerciseCarbohydratesPercentage();
        this.exerciseFatPercentage = mfpDailyGoal.getExerciseFatPercentage();
        this.exerciseProteinPercentage = mfpDailyGoal.getExerciseProteinPercentage();
    }

    public void setExerciseCarbohydratesPercentage(int i) {
        this.exerciseCarbohydratesPercentage = i;
    }

    public void setExerciseFatPercentage(int i) {
        this.exerciseFatPercentage = i;
    }

    public void setExerciseProteinPercentage(int i) {
        this.exerciseProteinPercentage = i;
    }

    public int getExerciseCarbohydratesPercentage() {
        return this.exerciseCarbohydratesPercentage;
    }

    public int getExerciseFatPercentage() {
        return this.exerciseFatPercentage;
    }

    public int getExerciseProteinPercentage() {
        return this.exerciseProteinPercentage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.exerciseCarbohydratesPercentage);
        parcel.writeInt(this.exerciseFatPercentage);
        parcel.writeInt(this.exerciseProteinPercentage);
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.exerciseCarbohydratesPercentage = parcel.readInt();
        this.exerciseFatPercentage = parcel.readInt();
        this.exerciseProteinPercentage = parcel.readInt();
    }
}
