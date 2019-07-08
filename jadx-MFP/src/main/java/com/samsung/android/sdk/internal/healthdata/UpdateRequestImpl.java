package com.samsung.android.sdk.internal.healthdata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.samsung.android.sdk.healthdata.HealthData;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.UpdateRequest;
import java.util.ArrayList;
import java.util.List;

public final class UpdateRequestImpl implements Parcelable, UpdateRequest {
    public static final Creator<UpdateRequestImpl> CREATOR = new Creator<UpdateRequestImpl>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new UpdateRequestImpl[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new UpdateRequestImpl(parcel);
        }
    };
    private final String a;
    private final HealthData b;
    private final Filter c;
    private List<String> d = null;

    public final int describeContents() {
        return 0;
    }

    public UpdateRequestImpl(String str, HealthData healthData, Filter filter, List<String> list) {
        this.a = str;
        this.b = healthData;
        this.c = filter;
        this.d = list;
    }

    public UpdateRequestImpl(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (HealthData) parcel.readParcelable(HealthData.class.getClassLoader());
        this.c = (Filter) parcel.readParcelable(Filter.class.getClassLoader());
        this.d = new ArrayList();
        parcel.readStringList(this.d);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeParcelable(this.b, 0);
        parcel.writeParcelable(this.c, 0);
        parcel.writeStringList(this.d);
    }

    public final String getDataType() {
        return this.a;
    }

    public final HealthData getDataObject() {
        return this.b;
    }

    public final Filter getFilter() {
        return this.c;
    }

    public final List<String> getDeviceUuids() {
        return this.d;
    }
}
