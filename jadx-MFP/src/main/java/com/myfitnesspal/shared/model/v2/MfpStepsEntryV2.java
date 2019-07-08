package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import java.util.ArrayList;

public class MfpStepsEntryV2 implements Parcelable {
    public static final Creator<MfpStepsEntryV2> CREATOR = new Creator<MfpStepsEntryV2>() {
        public MfpStepsEntryV2 createFromParcel(Parcel parcel) {
            return new MfpStepsEntryV2(parcel);
        }

        public MfpStepsEntryV2[] newArray(int i) {
            return new MfpStepsEntryV2[i];
        }
    };
    @Expose
    private String date;
    @Expose
    private int duration;
    @Expose
    private MfpMeasuredValue energy;
    @Expose
    private String startTime;
    @Expose
    private int steps;
    @Expose
    private String type;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpStepsEntryV2> {
    }

    public static class LIST_MAPPER extends ArrayList<MfpStepsEntryV2> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpStepsEntryV2() {
    }

    public MfpStepsEntryV2(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setEnergy(MfpMeasuredValue mfpMeasuredValue) {
        this.energy = mfpMeasuredValue;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public String getType() {
        return this.type;
    }

    public String getDate() {
        return this.date;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public int getDuration() {
        return this.duration;
    }

    public MfpMeasuredValue getEnergy() {
        return this.energy;
    }

    public int getSteps() {
        return this.steps;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeString(this.date);
        parcel.writeString(this.startTime);
        parcel.writeInt(this.duration);
        if (this.energy != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.energy, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeInt(this.steps);
    }

    private void readFromParcel(Parcel parcel) {
        this.type = parcel.readString();
        this.date = parcel.readString();
        this.startTime = parcel.readString();
        this.duration = parcel.readInt();
        if (parcel.readByte() == 1) {
            this.energy = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        this.steps = parcel.readInt();
    }
}
