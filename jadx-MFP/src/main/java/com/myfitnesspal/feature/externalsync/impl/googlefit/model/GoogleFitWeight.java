package com.myfitnesspal.feature.externalsync.impl.googlefit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.DataFitResult.Type;

public class GoogleFitWeight implements Parcelable, DataFitResult {
    public static final Creator<GoogleFitWeight> CREATOR = new Creator<GoogleFitWeight>() {
        public GoogleFitWeight createFromParcel(Parcel parcel) {
            return new GoogleFitWeight(parcel);
        }

        public GoogleFitWeight[] newArray(int i) {
            return new GoogleFitWeight[i];
        }
    };
    private long entryTime;
    private String source;
    private float weight;

    public int describeContents() {
        return 0;
    }

    public GoogleFitWeight() {
    }

    public GoogleFitWeight(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setEntryTime(long j) {
        this.entryTime = j;
    }

    public void setWeight(float f) {
        this.weight = f;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public long getEntryTime() {
        return this.entryTime;
    }

    public float getWeight() {
        return this.weight;
    }

    public String getSource() {
        return this.source;
    }

    public Type getType() {
        return Type.FitWeight;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.entryTime);
        parcel.writeFloat(this.weight);
        parcel.writeString(this.source);
    }

    private void readFromParcel(Parcel parcel) {
        this.entryTime = parcel.readLong();
        this.weight = parcel.readFloat();
        this.source = parcel.readString();
    }
}
