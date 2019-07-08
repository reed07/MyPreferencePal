package com.samsung.android.sdk.internal.healthdata.query;

import android.os.Parcel;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter.ParcelType;

public class NotFilter extends Filter {
    public NotFilter(Filter filter) {
        checkFilter(filter);
        this.mType = ParcelType.NOT;
        this.mFilters.add(filter);
    }

    public NotFilter(Parcel parcel) {
        readFromParcel(parcel);
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.mFilters = parcel.createTypedArrayList(Filter.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.mFilters);
    }
}
