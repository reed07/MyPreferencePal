package com.myfitnesspal.shared.model.v15;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;

public abstract class BaseObject implements Parcelable, BinaryApiSerializable {
    @Expose
    private long localId;
    @Expose
    private long masterId;
    @Expose
    private String uid;

    public int describeContents() {
        return 0;
    }

    public BaseObject() {
    }

    public BaseObject(Parcel parcel) {
        readFromParcel(parcel);
    }

    public boolean hasLocalId() {
        return getLocalId() > 0;
    }

    public long getLocalId() {
        return this.localId;
    }

    public void setLocalId(long j) {
        this.localId = j;
    }

    public boolean hasMasterId() {
        return getMasterId() > 0;
    }

    public long getMasterId() {
        return this.masterId;
    }

    public void setMasterId(long j) {
        this.masterId = j;
    }

    public boolean hasUid() {
        return Strings.notEmpty(this.uid);
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.localId);
        parcel.writeLong(this.masterId);
        parcel.writeString(this.uid);
    }

    public void readFromParcel(Parcel parcel) {
        this.localId = parcel.readLong();
        this.masterId = parcel.readLong();
        this.uid = parcel.readString();
    }
}
