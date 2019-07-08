package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.service.syncv2.ItemSyncState;
import java.util.ArrayList;
import java.util.List;

public class MfpNutrientGoal implements Parcelable {
    public static final Creator<MfpNutrientGoal> CREATOR = new Creator<MfpNutrientGoal>() {
        public MfpNutrientGoal createFromParcel(Parcel parcel) {
            return new MfpNutrientGoal(parcel);
        }

        public MfpNutrientGoal[] newArray(int i) {
            return new MfpNutrientGoal[i];
        }
    };
    @Expose
    private List<MfpDailyGoal> dailyGoals = new ArrayList();
    @Expose
    private MfpDailyGoal defaultGoal;
    @Expose
    private int defaultGroupId;
    @Expose
    private int syncFlag = ItemSyncState.Synchronized.getId();
    @Expose
    private String userId;
    @Expose
    private String validFrom;
    @Expose
    private String validTo;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpNutrientGoal> {
    }

    public static class LIST_MAPPER extends ArrayList<MfpNutrientGoal> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpNutrientGoal() {
    }

    public MfpNutrientGoal(Parcel parcel) {
        readFromParcel(parcel);
    }

    public MfpNutrientGoal clone(MfpNutrientGoal mfpNutrientGoal) {
        this.userId = mfpNutrientGoal.getUserId();
        this.validFrom = mfpNutrientGoal.getValidFrom();
        this.validTo = mfpNutrientGoal.getValidTo();
        this.dailyGoals = mfpNutrientGoal.getDailyGoals();
        this.defaultGroupId = mfpNutrientGoal.getDefaultGroupId();
        this.defaultGoal = mfpNutrientGoal.getDefaultGoal();
        this.syncFlag = mfpNutrientGoal.getSyncFlag();
        return this;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setValidFrom(String str) {
        this.validFrom = str;
    }

    public void setValidTo(String str) {
        this.validTo = str;
    }

    public void setDailyGoals(List<MfpDailyGoal> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.dailyGoals = list;
    }

    public void setDefaultGroupId(int i) {
        this.defaultGroupId = i;
    }

    public void setDefaultGoal(MfpDailyGoal mfpDailyGoal) {
        this.defaultGoal = mfpDailyGoal;
    }

    public void setSyncFlag(int i) {
        this.syncFlag = i;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getValidFrom() {
        return this.validFrom;
    }

    public String getValidTo() {
        return this.validTo;
    }

    public List<MfpDailyGoal> getDailyGoals() {
        return this.dailyGoals;
    }

    public int getDefaultGroupId() {
        return this.defaultGroupId;
    }

    public MfpDailyGoal getDefaultGoal() {
        return this.defaultGoal;
    }

    public int getSyncFlag() {
        return this.syncFlag;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.userId);
        parcel.writeString(this.validFrom);
        parcel.writeString(this.validTo);
        parcel.writeList(this.dailyGoals);
        parcel.writeInt(this.defaultGroupId);
        if (this.defaultGoal != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.defaultGoal, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeInt(this.syncFlag);
    }

    private void readFromParcel(Parcel parcel) {
        this.userId = parcel.readString();
        this.validFrom = parcel.readString();
        this.validTo = parcel.readString();
        this.dailyGoals.clear();
        parcel.readList(this.dailyGoals, MfpDailyGoal.class.getClassLoader());
        this.defaultGroupId = parcel.readInt();
        if (parcel.readByte() == 1) {
            this.defaultGoal = (MfpDailyGoal) parcel.readParcelable(MfpDailyGoal.class.getClassLoader());
        }
        this.syncFlag = parcel.readInt();
    }
}
