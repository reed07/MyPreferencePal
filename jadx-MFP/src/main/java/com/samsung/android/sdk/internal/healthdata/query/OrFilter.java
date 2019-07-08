package com.samsung.android.sdk.internal.healthdata.query;

import android.os.Parcel;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter.ParcelType;
import java.util.Arrays;

public class OrFilter extends Filter {
    public OrFilter(Filter filter, Filter... filterArr) {
        checkFilter(filter);
        for (Filter checkFilter : filterArr) {
            checkFilter(checkFilter);
        }
        this.mType = ParcelType.OR;
        this.mFilters.add(filter);
        this.mFilters.addAll(Arrays.asList(filterArr));
    }

    public OrFilter(Parcel parcel) {
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
