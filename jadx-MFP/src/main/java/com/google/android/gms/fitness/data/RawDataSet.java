package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.fitness.zzf;
import java.util.List;

@KeepName
@Class(creator = "RawDataSetCreator")
@Reserved({2, 1000})
public final class RawDataSet extends AbstractSafeParcelable {
    public static final Creator<RawDataSet> CREATOR = new zzaa();
    @Field(id = 4)
    public final boolean zzal;
    @Field(id = 1)
    public final int zzdw;
    @Field(id = 3)
    public final List<RawDataPoint> zzdy;

    @Constructor
    public RawDataSet(@Param(id = 1) int i, @Param(id = 3) List<RawDataPoint> list, @Param(id = 4) boolean z) {
        this.zzdw = i;
        this.zzdy = list;
        this.zzal = z;
    }

    public RawDataSet(DataSet dataSet, List<DataSource> list) {
        this.zzdy = dataSet.zza(list);
        this.zzal = dataSet.zza();
        this.zzdw = zzf.zza(dataSet.getDataSource(), list);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataSet)) {
            return false;
        }
        RawDataSet rawDataSet = (RawDataSet) obj;
        return this.zzdw == rawDataSet.zzdw && this.zzal == rawDataSet.zzal && Objects.equal(this.zzdy, rawDataSet.zzdy);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzdw));
    }

    public final String toString() {
        return String.format("RawDataSet{%s@[%s]}", new Object[]{Integer.valueOf(this.zzdw), this.zzdy});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzdw);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzdy, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
