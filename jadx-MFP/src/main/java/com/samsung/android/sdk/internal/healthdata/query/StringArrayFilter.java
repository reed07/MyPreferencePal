package com.samsung.android.sdk.internal.healthdata.query;

import android.os.Parcel;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter.ParcelType;

public class StringArrayFilter extends Filter {
    private String a;
    private String[] b;

    public StringArrayFilter(String str, String[] strArr) {
        this.mType = ParcelType.STRING_ARRAY;
        this.a = str;
        this.b = strArr;
    }

    public StringArrayFilter(Parcel parcel) {
        readFromParcel(parcel);
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.a = parcel.readString();
        this.b = parcel.createStringArray();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeStringArray(this.b);
    }

    public String getField() {
        return this.a;
    }

    public String[] getList() {
        return this.b;
    }
}
