package com.myfitnesspal.shared.model.v1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.uacf.core.util.Strings;

public class DatabaseObject implements DiaryEntryCellModel {
    @Expose
    public long localId;
    @Expose
    public long masterDatabaseId;
    @Expose
    protected String uid;

    public Drawable image() {
        return null;
    }

    public boolean isMealEntries() {
        return false;
    }

    public int itemType() {
        return 1;
    }

    public String summaryLine1() {
        return "???";
    }

    public DatabaseObject() {
    }

    protected DatabaseObject(Parcel parcel) {
        this();
        this.localId = parcel.readLong();
        this.masterDatabaseId = parcel.readLong();
        this.uid = parcel.readString();
    }

    public long getLocalId() {
        return this.localId;
    }

    public void setLocalId(long j) {
        this.localId = j;
    }

    public boolean hasLocalId() {
        return this.localId != 0;
    }

    public long getMasterDatabaseId() {
        return this.masterDatabaseId;
    }

    public void setMasterDatabaseId(long j) {
        this.masterDatabaseId = j;
    }

    public boolean hasMasterDatabaseId() {
        return this.masterDatabaseId != 0;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public boolean hasUid() {
        return Strings.notEmpty(this.uid);
    }

    public boolean isFood() {
        int itemType = itemType();
        return itemType == 1 || itemType == 3;
    }

    public boolean isExercise() {
        return itemType() == 2;
    }

    public boolean isFoodEntry() {
        return itemType() == 4;
    }

    public boolean isExerciseEntry() {
        return itemType() == 5;
    }

    public Context getContext() {
        return MyFitnessPalApp.getInstance().getApplicationContext();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.localId);
        parcel.writeLong(this.masterDatabaseId);
        parcel.writeString(this.uid);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DatabaseObject)) {
            return false;
        }
        DatabaseObject databaseObject = (DatabaseObject) obj;
        if (!(this.localId == databaseObject.localId && this.masterDatabaseId == databaseObject.masterDatabaseId && Strings.equals(this.uid, databaseObject.uid))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = (527 + Strings.toString(this.uid).hashCode()) * 31;
        long j = this.localId;
        int i = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.masterDatabaseId;
        return i + ((int) (j2 ^ (j2 >>> 32)));
    }
}
