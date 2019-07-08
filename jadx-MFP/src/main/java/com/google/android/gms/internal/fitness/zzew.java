package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.DataSource;

@Class(creator = "FitnessUnregistrationRequestCreator")
@Reserved({1000})
public final class zzew extends AbstractSafeParcelable {
    public static final Creator<zzew> CREATOR = new zzex();
    @Field(getter = "getDataSource", id = 1)
    private final DataSource zzr;

    @Constructor
    public zzew(@Param(id = 1) DataSource dataSource) {
        this.zzr = dataSource;
    }

    public final DataSource getDataSource() {
        return this.zzr;
    }

    public final String toString() {
        return String.format("ApplicationUnregistrationRequest{%s}", new Object[]{this.zzr});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzr, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
