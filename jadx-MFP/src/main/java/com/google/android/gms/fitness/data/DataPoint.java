package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Class(creator = "DataPointCreator")
@Reserved({1000})
public final class DataPoint extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<DataPoint> CREATOR = new zzh();
    @Field(getter = "getTimestampNanos", id = 3)
    private long zzao;
    @Field(getter = "getStartTimeNanos", id = 4)
    private long zzap;
    @Field(getter = "getValues", id = 5)
    private final Value[] zzaq;
    @Field(getter = "getOriginalDataSourceIfSet", id = 6)
    private DataSource zzar;
    @Field(getter = "getRawTimestamp", id = 7)
    private long zzas;
    @Field(getter = "getInsertionTimeMillis", id = 8)
    private long zzat;
    @Field(getter = "getDataSource", id = 1)
    private final DataSource zzr;

    @Constructor
    public DataPoint(@Param(id = 1) DataSource dataSource, @Param(id = 3) long j, @Param(id = 4) long j2, @Param(id = 5) Value[] valueArr, @Param(id = 6) DataSource dataSource2, @Param(id = 7) long j3, @Param(id = 8) long j4) {
        this.zzr = dataSource;
        this.zzar = dataSource2;
        this.zzao = j;
        this.zzap = j2;
        this.zzaq = valueArr;
        this.zzas = j3;
        this.zzat = j4;
    }

    DataPoint(List<DataSource> list, RawDataPoint rawDataPoint) {
        this(zza(list, rawDataPoint.zzo()), zza(list, rawDataPoint.zzp()), rawDataPoint);
    }

    private DataPoint(DataSource dataSource, @Nullable DataSource dataSource2, RawDataPoint rawDataPoint) {
        this(dataSource, zza(Long.valueOf(rawDataPoint.getTimestampNanos()), 0), zza(Long.valueOf(rawDataPoint.zzn()), 0), rawDataPoint.zzc(), dataSource2, zza(Long.valueOf(rawDataPoint.zze()), 0), zza(Long.valueOf(rawDataPoint.zzf()), 0));
    }

    private static DataSource zza(List<DataSource> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return (DataSource) list.get(i);
    }

    private DataPoint(DataSource dataSource) {
        this.zzr = (DataSource) Preconditions.checkNotNull(dataSource, "Data source cannot be null");
        List<Field> fields = dataSource.getDataType().getFields();
        this.zzaq = new Value[fields.size()];
        int i = 0;
        for (Field format : fields) {
            this.zzaq[i] = new Value(format.getFormat());
            i++;
        }
    }

    public static DataPoint create(DataSource dataSource) {
        return new DataPoint(dataSource);
    }

    @Nullable
    public static DataPoint extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataPoint) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR);
    }

    public final DataPoint setTimestamp(long j, TimeUnit timeUnit) {
        this.zzao = timeUnit.toNanos(j);
        return this;
    }

    public final DataPoint setTimeInterval(long j, long j2, TimeUnit timeUnit) {
        this.zzap = timeUnit.toNanos(j);
        this.zzao = timeUnit.toNanos(j2);
        return this;
    }

    public final Value zzb(int i) {
        DataType dataType = getDataType();
        Preconditions.checkArgument(i >= 0 && i < dataType.getFields().size(), "fieldIndex %s is out of range for %s", Integer.valueOf(i), dataType);
        return this.zzaq[i];
    }

    public final Value getValue(Field field) {
        return this.zzaq[getDataType().indexOf(field)];
    }

    public final Value[] zzc() {
        return this.zzaq;
    }

    public final DataPoint setFloatValues(float... fArr) {
        zzc(fArr.length);
        for (int i = 0; i < fArr.length; i++) {
            this.zzaq[i].setFloat(fArr[i]);
        }
        return this;
    }

    public final DataPoint setIntValues(int... iArr) {
        zzc(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            this.zzaq[i].setInt(iArr[i]);
        }
        return this;
    }

    private final void zzc(int i) {
        List fields = getDataType().getFields();
        int size = fields.size();
        Preconditions.checkArgument(i == size, "Attempting to insert %s values, but needed %s: %s", Integer.valueOf(i), Integer.valueOf(size), fields);
    }

    public final DataType getDataType() {
        return this.zzr.getDataType();
    }

    public final DataSource getDataSource() {
        return this.zzr;
    }

    public final DataSource getOriginalDataSource() {
        DataSource dataSource = this.zzar;
        return dataSource != null ? dataSource : this.zzr;
    }

    @Nullable
    public final DataSource zzd() {
        return this.zzar;
    }

    public final long getTimestamp(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzao, TimeUnit.NANOSECONDS);
    }

    public final long zze() {
        return this.zzas;
    }

    public final long zzf() {
        return this.zzat;
    }

    public final long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzap, TimeUnit.NANOSECONDS);
    }

    public final long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzao, TimeUnit.NANOSECONDS);
    }

    public final void zzg() {
        Preconditions.checkArgument(getDataType().getName().equals(getDataSource().getDataType().getName()), "Conflicting data types found %s vs %s", getDataType(), getDataType());
        Preconditions.checkArgument(this.zzao > 0, "Data point does not have the timestamp set: %s", this);
        Preconditions.checkArgument(this.zzap <= this.zzao, "Data point with start time greater than end time found: %s", this);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataPoint)) {
            return false;
        }
        DataPoint dataPoint = (DataPoint) obj;
        return Objects.equal(this.zzr, dataPoint.zzr) && this.zzao == dataPoint.zzao && this.zzap == dataPoint.zzap && Arrays.equals(this.zzaq, dataPoint.zzaq) && Objects.equal(getOriginalDataSource(), dataPoint.getOriginalDataSource());
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzr, Long.valueOf(this.zzao), Long.valueOf(this.zzap));
    }

    public final String toString() {
        String str = "DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}";
        Object[] objArr = new Object[7];
        objArr[0] = Arrays.toString(this.zzaq);
        objArr[1] = Long.valueOf(this.zzap);
        objArr[2] = Long.valueOf(this.zzao);
        objArr[3] = Long.valueOf(this.zzas);
        objArr[4] = Long.valueOf(this.zzat);
        objArr[5] = this.zzr.toDebugString();
        DataSource dataSource = this.zzar;
        objArr[6] = dataSource != null ? dataSource.toDebugString() : "N/A";
        return String.format(str, objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzao);
        SafeParcelWriter.writeLong(parcel, 4, this.zzap);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zzaq, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzar, i, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzas);
        SafeParcelWriter.writeLong(parcel, 8, this.zzat);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private static long zza(@Nullable Long l, long j) {
        if (l != null) {
            return l.longValue();
        }
        return 0;
    }
}
