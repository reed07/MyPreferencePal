package com.myfitnesspal.feature.externalsync.impl.googlefit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.DataFitResult.Type;

public class GoogleFitActivityEntry implements Parcelable, DataFitResult {
    public static final Creator<GoogleFitActivityEntry> CREATOR = new Creator<GoogleFitActivityEntry>() {
        public GoogleFitActivityEntry createFromParcel(Parcel parcel) {
            return new GoogleFitActivityEntry(parcel);
        }

        public GoogleFitActivityEntry[] newArray(int i) {
            return new GoogleFitActivityEntry[i];
        }
    };
    private int activityId;
    private String activityName;
    private long duration;
    private long endTime;
    private int numberOfSegments;
    private String source;
    private long startTime;

    public int describeContents() {
        return 0;
    }

    public GoogleFitActivityEntry() {
    }

    public GoogleFitActivityEntry(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setActivityId(int i) {
        this.activityId = i;
    }

    public void setActivityName(String str) {
        this.activityName = str;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setNumberOfSegments(int i) {
        this.numberOfSegments = i;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public int getActivityId() {
        return this.activityId;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getNumberOfSegments() {
        return this.numberOfSegments;
    }

    public String getSource() {
        return this.source;
    }

    public Type getType() {
        return Type.FitActivity;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.activityId);
        parcel.writeString(this.activityName);
        parcel.writeLong(this.startTime);
        parcel.writeLong(this.endTime);
        parcel.writeLong(this.duration);
        parcel.writeInt(this.numberOfSegments);
        parcel.writeString(this.source);
    }

    private void readFromParcel(Parcel parcel) {
        this.activityId = parcel.readInt();
        this.activityName = parcel.readString();
        this.startTime = parcel.readLong();
        this.endTime = parcel.readLong();
        this.duration = parcel.readLong();
        this.numberOfSegments = parcel.readInt();
        this.source = parcel.readString();
    }
}
