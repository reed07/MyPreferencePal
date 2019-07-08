package com.myfitnesspal.feature.externalsync.impl.googlefit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.DataFitResult.Type;

public class GoogleFitStepsEntry implements Parcelable, DataFitResult {
    public static final Creator<GoogleFitStepsEntry> CREATOR = new Creator<GoogleFitStepsEntry>() {
        public GoogleFitStepsEntry createFromParcel(Parcel parcel) {
            return new GoogleFitStepsEntry(parcel);
        }

        public GoogleFitStepsEntry[] newArray(int i) {
            return new GoogleFitStepsEntry[i];
        }
    };
    private long endTime;
    private String source;
    private long startTime;
    private int steps;

    public int describeContents() {
        return 0;
    }

    public GoogleFitStepsEntry() {
    }

    public GoogleFitStepsEntry(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public int getSteps() {
        return this.steps;
    }

    public String getSource() {
        return this.source;
    }

    public Type getType() {
        return Type.FitSteps;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.startTime);
        parcel.writeLong(this.endTime);
        parcel.writeInt(this.steps);
        parcel.writeString(this.source);
    }

    private void readFromParcel(Parcel parcel) {
        this.startTime = parcel.readLong();
        this.endTime = parcel.readLong();
        this.steps = parcel.readInt();
        this.source = parcel.readString();
    }
}
