package com.myfitnesspal.shared.api.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import java.util.ArrayList;

public class MfpDayOfWeekDailyGoalPost extends MfpDailyGoalPost implements Parcelable {
    public static final Creator<MfpDayOfWeekDailyGoalPost> CREATOR = new Creator<MfpDayOfWeekDailyGoalPost>() {
        public MfpDayOfWeekDailyGoalPost createFromParcel(Parcel parcel) {
            return new MfpDayOfWeekDailyGoalPost(parcel);
        }

        public MfpDayOfWeekDailyGoalPost[] newArray(int i) {
            return new MfpDayOfWeekDailyGoalPost[i];
        }
    };
    @Expose
    private String dayOfWeek;

    public static class LIST_MAPPER extends ArrayList<MfpDayOfWeekDailyGoalPost> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpDayOfWeekDailyGoalPost() {
    }

    public MfpDayOfWeekDailyGoalPost(MfpDailyGoal mfpDailyGoal) {
        super(mfpDailyGoal);
        this.dayOfWeek = mfpDailyGoal.getDayOfWeek();
    }

    public MfpDayOfWeekDailyGoalPost(Parcel parcel) {
        super(parcel);
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.dayOfWeek);
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.dayOfWeek = parcel.readString();
    }
}
