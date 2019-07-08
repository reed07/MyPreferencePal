package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.DataType;
import java.util.Collections;
import java.util.List;

@Class(creator = "FitnessDataSourcesRequestCreator")
@Reserved({1000})
public final class zzeu extends AbstractSafeParcelable {
    public static final Creator<zzeu> CREATOR = new zzev();
    @Field(getter = "getDataTypes", id = 1)
    private final List<DataType> zzah;

    @Constructor
    public zzeu(@Param(id = 1) List<DataType> list) {
        this.zzah = list;
    }

    public final List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zzah);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzah).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, Collections.unmodifiableList(this.zzah), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
