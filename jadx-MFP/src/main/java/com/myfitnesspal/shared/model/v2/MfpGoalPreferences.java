package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;

public class MfpGoalPreferences implements Parcelable {
    public static final Creator<MfpGoalPreferences> CREATOR = new Creator<MfpGoalPreferences>() {
        public MfpGoalPreferences createFromParcel(Parcel parcel) {
            return new MfpGoalPreferences(parcel);
        }

        public MfpGoalPreferences[] newArray(int i) {
            return new MfpGoalPreferences[i];
        }
    };
    @Expose
    private MfpMeasuredValue dailyEnergyGoal;
    @Expose
    private int dailyStepGoal;
    @Expose
    private String diaryGoalDisplay;
    @Expose
    private String homeGoalDisplay;
    @Expose
    private String macroGoalFormat;
    @Expose
    private MfpMeasuredValue weeklyExerciseEnergy;
    @Expose
    private int weeklyWorkoutDuration;
    @Expose
    private MfpMeasuredValue weightChangeGoal;
    @Expose
    private MfpMeasuredValue weightGoal;
    @Expose
    private int workoutsPerWeek;

    public interface MacroGoalFormat {
        public static final String GRAMS = "grams";
        public static final String PERCENTAGE = "percentage";
    }

    public int describeContents() {
        return 0;
    }

    public MfpGoalPreferences() {
    }

    public MfpGoalPreferences(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setDailyStepGoal(int i) {
        this.dailyStepGoal = i;
    }

    public void setWorkoutsPerWeek(int i) {
        this.workoutsPerWeek = i;
    }

    public void setWeeklyWorkoutDuration(int i) {
        this.weeklyWorkoutDuration = i;
    }

    public void setWeeklyExerciseEnergy(MfpMeasuredValue mfpMeasuredValue) {
        this.weeklyExerciseEnergy = mfpMeasuredValue;
    }

    public void setDailyEnergyGoal(MfpMeasuredValue mfpMeasuredValue) {
        this.dailyEnergyGoal = mfpMeasuredValue;
    }

    public void setWeightChangeGoal(MfpMeasuredValue mfpMeasuredValue) {
        this.weightChangeGoal = mfpMeasuredValue;
    }

    public void setWeightGoal(MfpMeasuredValue mfpMeasuredValue) {
        this.weightGoal = mfpMeasuredValue;
    }

    public void setMacroGoalFormat(String str) {
        this.macroGoalFormat = str;
    }

    public void setMacroGoalFormatAsGrams(boolean z) {
        setMacroGoalFormat(z ? "grams" : MacroGoalFormat.PERCENTAGE);
    }

    public void setDiaryGoalDisplay(String str) {
        this.diaryGoalDisplay = str;
    }

    public void setHomeGoalDisplay(String str) {
        this.homeGoalDisplay = str;
    }

    public int getDailyStepGoal() {
        return this.dailyStepGoal;
    }

    public int getWorkoutsPerWeek() {
        return this.workoutsPerWeek;
    }

    public int getWeeklyWorkoutDuration() {
        return this.weeklyWorkoutDuration;
    }

    public MfpMeasuredValue getWeeklyExerciseEnergy() {
        return this.weeklyExerciseEnergy;
    }

    public MfpMeasuredValue getDailyEnergyGoal() {
        return this.dailyEnergyGoal;
    }

    public MfpMeasuredValue getWeightChangeGoal() {
        return this.weightChangeGoal;
    }

    public MfpMeasuredValue getWeightGoal() {
        return this.weightGoal;
    }

    public String getMacroGoalFormat() {
        return this.macroGoalFormat;
    }

    public boolean isMacroGoalFormatGrams() {
        return Strings.equals(this.macroGoalFormat, "grams");
    }

    public String getDiaryGoalDisplay() {
        return this.diaryGoalDisplay;
    }

    public String getHomeGoalDisplay() {
        return this.homeGoalDisplay;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.dailyStepGoal);
        parcel.writeInt(this.workoutsPerWeek);
        parcel.writeInt(this.weeklyWorkoutDuration);
        if (this.weeklyExerciseEnergy != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.weeklyExerciseEnergy, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.dailyEnergyGoal != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.dailyEnergyGoal, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.weightChangeGoal != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.weightChangeGoal, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.weightGoal != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.weightGoal, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeString(this.macroGoalFormat);
        parcel.writeString(this.diaryGoalDisplay);
        parcel.writeString(this.homeGoalDisplay);
    }

    private void readFromParcel(Parcel parcel) {
        this.dailyStepGoal = parcel.readInt();
        this.workoutsPerWeek = parcel.readInt();
        this.weeklyWorkoutDuration = parcel.readInt();
        if (parcel.readByte() == 1) {
            this.weeklyExerciseEnergy = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.dailyEnergyGoal = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.weightChangeGoal = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.weightGoal = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        this.macroGoalFormat = parcel.readString();
        this.diaryGoalDisplay = parcel.readString();
        this.homeGoalDisplay = parcel.readString();
    }
}
