package com.myfitnesspal.feature.nutrition.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.ParcelableUtil;

public class NutrientEntry implements Parcelable {
    public static final Creator<NutrientEntry> CREATOR = new Creator<NutrientEntry>() {
        public NutrientEntry createFromParcel(Parcel parcel) {
            return new NutrientEntry(parcel);
        }

        public NutrientEntry[] newArray(int i) {
            return new NutrientEntry[i];
        }
    };
    @Expose
    private String fieldLabel;
    @Expose
    private String goal;
    @Expose
    private boolean isSubordinateEntry;
    @Expose
    private String left;
    @Expose
    private int nutrientIndex;
    @Expose
    private String total;
    @Expose
    private String unit;

    public int describeContents() {
        return 0;
    }

    public NutrientEntry(int i, String str) {
        this(i, str, null, null, null, false);
    }

    public NutrientEntry(int i, String str, String str2, String str3, String str4, boolean z) {
        this(i, str, str2, str3, str4, z, null);
    }

    public NutrientEntry(String str, String str2, String str3, String str4) {
        this(0, str, str2, str3, null, false, str4);
    }

    public NutrientEntry(int i, String str, String str2, String str3, String str4, boolean z, String str5) {
        this.nutrientIndex = i;
        this.fieldLabel = str;
        this.total = str2;
        this.goal = str3;
        this.left = str4;
        this.isSubordinateEntry = z;
        this.unit = str5;
    }

    private NutrientEntry(Parcel parcel) {
        this.nutrientIndex = parcel.readInt();
        this.fieldLabel = parcel.readString();
        this.total = parcel.readString();
        this.goal = parcel.readString();
        this.left = parcel.readString();
        this.isSubordinateEntry = ParcelableUtil.readBoolean(parcel);
    }

    public int getNutrientIndex() {
        return this.nutrientIndex;
    }

    public String getFieldLabel() {
        return this.fieldLabel;
    }

    public String getLeft() {
        return this.left;
    }

    public String getTotal() {
        return this.total;
    }

    public String getGoal() {
        return this.goal;
    }

    public boolean isSubordinateEntry() {
        return this.isSubordinateEntry;
    }

    public String getUnit() {
        return this.unit;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.nutrientIndex);
        parcel.writeString(this.fieldLabel);
        parcel.writeString(this.total);
        parcel.writeString(this.goal);
        parcel.writeString(this.left);
        ParcelableUtil.writeBoolean(parcel, this.isSubordinateEntry);
    }
}
