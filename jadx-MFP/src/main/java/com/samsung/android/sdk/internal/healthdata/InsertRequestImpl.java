package com.samsung.android.sdk.internal.healthdata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.samsung.android.sdk.healthdata.HealthData;
import com.samsung.android.sdk.healthdata.HealthDataResolver.InsertRequest;
import java.util.ArrayList;
import java.util.List;

public final class InsertRequestImpl implements Parcelable, InsertRequest {
    public static final Creator<InsertRequestImpl> CREATOR = new Creator<InsertRequestImpl>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new InsertRequestImpl[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new InsertRequestImpl(parcel, 0);
        }
    };
    private final String a;
    private final List<HealthData> b;

    public final int describeContents() {
        return 0;
    }

    /* synthetic */ InsertRequestImpl(Parcel parcel, byte b2) {
        this(parcel);
    }

    public InsertRequestImpl(String str) {
        this.a = str;
        this.b = new ArrayList();
    }

    private InsertRequestImpl(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.createTypedArrayList(HealthData.CREATOR);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeTypedList(this.b);
    }

    private static void a(HealthData healthData) {
        if (healthData == null) {
            throw new IllegalArgumentException("data is null");
        } else if (healthData.getSourceDevice() == null) {
            throw new IllegalArgumentException("source device is not set");
        }
    }

    public final void addHealthData(HealthData healthData) {
        a(healthData);
        this.b.add(healthData);
    }

    public final void addHealthData(List<HealthData> list) {
        if (list != null) {
            for (HealthData a2 : list) {
                a(a2);
            }
            this.b.addAll(list);
            return;
        }
        throw new IllegalArgumentException("data list is null");
    }

    public final String getDataType() {
        return this.a;
    }

    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    public final List<HealthData> getItems() {
        return this.b;
    }
}
