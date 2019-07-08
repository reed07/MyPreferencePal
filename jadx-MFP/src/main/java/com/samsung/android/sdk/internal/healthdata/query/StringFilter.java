package com.samsung.android.sdk.internal.healthdata.query;

import android.os.Parcel;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter.ParcelType;

public class StringFilter extends Filter {
    private String a;
    private String b;

    public StringFilter(String str, String str2) {
        this.mType = ParcelType.STRING;
        this.a = str;
        this.b = str2;
    }

    public StringFilter(Parcel parcel) {
        readFromParcel(parcel);
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }

    public String getField() {
        return this.a;
    }

    public String getValue() {
        return this.b;
    }
}
