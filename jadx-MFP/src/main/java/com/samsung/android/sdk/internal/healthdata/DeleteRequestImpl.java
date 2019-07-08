package com.samsung.android.sdk.internal.healthdata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.samsung.android.sdk.healthdata.HealthDataResolver.DeleteRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import java.util.ArrayList;
import java.util.List;

public final class DeleteRequestImpl implements Parcelable, DeleteRequest {
    public static final Creator<DeleteRequestImpl> CREATOR = new Creator<DeleteRequestImpl>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new DeleteRequestImpl[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new DeleteRequestImpl(parcel, 0);
        }
    };
    private final String a;
    private final Filter b;
    private List<String> c;

    public final int describeContents() {
        return 0;
    }

    /* synthetic */ DeleteRequestImpl(Parcel parcel, byte b2) {
        this(parcel);
    }

    public DeleteRequestImpl(String str, Filter filter, List<String> list) {
        this.c = null;
        this.a = str;
        this.b = filter;
        this.c = list;
    }

    private DeleteRequestImpl(Parcel parcel) {
        this.c = null;
        this.a = parcel.readString();
        this.b = (Filter) parcel.readParcelable(Filter.class.getClassLoader());
        this.c = new ArrayList();
        parcel.readStringList(this.c);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeParcelable(this.b, 0);
        parcel.writeStringList(this.c);
    }

    public final String getDataType() {
        return this.a;
    }

    public final Filter getFilter() {
        return this.b;
    }

    public final List<String> getDeviceUuids() {
        return this.c;
    }
}
