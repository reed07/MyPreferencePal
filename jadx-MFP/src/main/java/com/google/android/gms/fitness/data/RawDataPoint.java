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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@KeepName
@Class(creator = "RawDataPointCreator")
@Reserved({1000})
public final class RawDataPoint extends AbstractSafeParcelable {
    public static final Creator<RawDataPoint> CREATOR = new zzz();
    @Field(getter = "getTimestampNanos", id = 1)
    private final long zzao;
    @Field(getter = "getStartTimeNanos", id = 2)
    private final long zzap;
    @Field(getter = "getValues", id = 3)
    private final Value[] zzaq;
    @Field(getter = "getRawTimestamp", id = 6)
    private final long zzas;
    @Field(getter = "getInsertionTimeMillis", id = 7)
    private final long zzat;
    @Field(getter = "getDataSourceIndex", id = 4)
    private final int zzdw;
    @Field(getter = "getOriginalDataSourceIndex", id = 5)
    private final int zzdx;

    @Constructor
    public RawDataPoint(@Param(id = 1) long j, @Param(id = 2) long j2, @Param(id = 3) Value[] valueArr, @Param(id = 4) int i, @Param(id = 5) int i2, @Param(id = 6) long j3, @Param(id = 7) long j4) {
        this.zzao = j;
        this.zzap = j2;
        this.zzdw = i;
        this.zzdx = i2;
        this.zzas = j3;
        this.zzat = j4;
        this.zzaq = valueArr;
    }

    RawDataPoint(DataPoint dataPoint, List<DataSource> list) {
        this.zzao = dataPoint.getTimestamp(TimeUnit.NANOSECONDS);
        this.zzap = dataPoint.getStartTime(TimeUnit.NANOSECONDS);
        this.zzaq = dataPoint.zzc();
        this.zzdw = zzf.zza(dataPoint.getDataSource(), list);
        this.zzdx = zzf.zza(dataPoint.zzd(), list);
        this.zzas = dataPoint.zze();
        this.zzat = dataPoint.zzf();
    }

    public final long getTimestampNanos() {
        return this.zzao;
    }

    public final long zzn() {
        return this.zzap;
    }

    public final Value[] zzc() {
        return this.zzaq;
    }

    public final int zzo() {
        return this.zzdw;
    }

    public final int zzp() {
        return this.zzdx;
    }

    public final long zze() {
        return this.zzas;
    }

    public final long zzf() {
        return this.zzat;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataPoint)) {
            return false;
        }
        RawDataPoint rawDataPoint = (RawDataPoint) obj;
        return this.zzao == rawDataPoint.zzao && this.zzap == rawDataPoint.zzap && Arrays.equals(this.zzaq, rawDataPoint.zzaq) && this.zzdw == rawDataPoint.zzdw && this.zzdx == rawDataPoint.zzdx && this.zzas == rawDataPoint.zzas;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzao), Long.valueOf(this.zzap));
    }

    public final String toString() {
        return String.format(Locale.US, "RawDataPoint{%s@[%s, %s](%d,%d)}", new Object[]{Arrays.toString(this.zzaq), Long.valueOf(this.zzap), Long.valueOf(this.zzao), Integer.valueOf(this.zzdw), Integer.valueOf(this.zzdx)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzao);
        SafeParcelWriter.writeLong(parcel, 2, this.zzap);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zzaq, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzdw);
        SafeParcelWriter.writeInt(parcel, 5, this.zzdx);
        SafeParcelWriter.writeLong(parcel, 6, this.zzas);
        SafeParcelWriter.writeLong(parcel, 7, this.zzat);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
