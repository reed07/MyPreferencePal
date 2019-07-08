package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.util.Log;
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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.internal.fitness.zzj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "DataSetCreator")
@Reserved({2})
public final class DataSet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<DataSet> CREATOR = new zzi();
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int versionCode;
    @Field(getter = "serverHasMoreData", id = 5)
    private boolean zzal;
    @Field(getter = "getRawDataPoints", id = 3, type = "java.util.List")
    private final List<DataPoint> zzau;
    @Field(getter = "getUniqueDataSources", id = 4)
    private final List<DataSource> zzav;
    @Field(getter = "getDataSource", id = 1)
    private final DataSource zzr;

    @Constructor
    DataSet(@Param(id = 1000) int i, @Param(id = 1) DataSource dataSource, @Param(id = 3) List<RawDataPoint> list, @Param(id = 4) List<DataSource> list2, @Param(id = 5) boolean z) {
        this.zzal = false;
        this.versionCode = i;
        this.zzr = dataSource;
        this.zzal = z;
        this.zzau = new ArrayList(list.size());
        if (i < 2) {
            list2 = Collections.singletonList(dataSource);
        }
        this.zzav = list2;
        for (RawDataPoint dataPoint : list) {
            this.zzau.add(new DataPoint(this.zzav, dataPoint));
        }
    }

    private DataSet(DataSource dataSource) {
        this.zzal = false;
        this.versionCode = 3;
        this.zzr = (DataSource) Preconditions.checkNotNull(dataSource);
        this.zzau = new ArrayList();
        this.zzav = new ArrayList();
        this.zzav.add(this.zzr);
    }

    public DataSet(RawDataSet rawDataSet, List<DataSource> list) {
        this.zzal = false;
        this.versionCode = 3;
        this.zzr = (DataSource) list.get(rawDataSet.zzdw);
        this.zzav = list;
        this.zzal = rawDataSet.zzal;
        List<RawDataPoint> list2 = rawDataSet.zzdy;
        this.zzau = new ArrayList(list2.size());
        for (RawDataPoint dataPoint : list2) {
            this.zzau.add(new DataPoint(this.zzav, dataPoint));
        }
    }

    public static DataSet create(DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new DataSet(dataSource);
    }

    public final DataPoint createDataPoint() {
        return DataPoint.create(this.zzr);
    }

    public final void add(DataPoint dataPoint) {
        DataSource dataSource = dataPoint.getDataSource();
        Preconditions.checkArgument(dataSource.getStreamIdentifier().equals(this.zzr.getStreamIdentifier()), "Conflicting data sources found %s vs %s", dataSource, this.zzr);
        dataPoint.zzg();
        zzb(dataPoint);
        zza(dataPoint);
    }

    private final void zza(DataPoint dataPoint) {
        this.zzau.add(dataPoint);
        DataSource originalDataSource = dataPoint.getOriginalDataSource();
        if (originalDataSource != null && !this.zzav.contains(originalDataSource)) {
            this.zzav.add(originalDataSource);
        }
    }

    public final void addAll(Iterable<DataPoint> iterable) {
        for (DataPoint add : iterable) {
            add(add);
        }
    }

    public final void zza(Iterable<DataPoint> iterable) {
        for (DataPoint zza : iterable) {
            zza(zza);
        }
    }

    public final DataSource getDataSource() {
        return this.zzr;
    }

    public final DataType getDataType() {
        return this.zzr.getDataType();
    }

    public final List<DataPoint> getDataPoints() {
        return Collections.unmodifiableList(this.zzau);
    }

    public final boolean isEmpty() {
        return this.zzau.isEmpty();
    }

    public final boolean zza() {
        return this.zzal;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataSet)) {
            return false;
        }
        DataSet dataSet = (DataSet) obj;
        return Objects.equal(this.zzr, dataSet.zzr) && Objects.equal(this.zzau, dataSet.zzau) && this.zzal == dataSet.zzal;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzr);
    }

    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String toString() {
        /*
            r10 = this;
            java.util.List r0 = r10.zzh()
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r2 = "DataSet{%s %s}"
            r3 = 2
            java.lang.Object[] r4 = new java.lang.Object[r3]
            com.google.android.gms.fitness.data.DataSource r5 = r10.zzr
            java.lang.String r5 = r5.toDebugString()
            r6 = 0
            r4[r6] = r5
            java.util.List<com.google.android.gms.fitness.data.DataPoint> r5 = r10.zzau
            int r5 = r5.size()
            r7 = 1
            r8 = 10
            if (r5 >= r8) goto L_0x0020
            goto L_0x003d
        L_0x0020:
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r8 = "%d data points, first 5: %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.util.List<com.google.android.gms.fitness.data.DataPoint> r9 = r10.zzau
            int r9 = r9.size()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r3[r6] = r9
            r9 = 5
            java.util.List r0 = r0.subList(r6, r9)
            r3[r7] = r0
            java.lang.String r0 = java.lang.String.format(r5, r8, r3)
        L_0x003d:
            r4[r7] = r0
            java.lang.String r0 = java.lang.String.format(r1, r2, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.DataSet.toString():java.lang.String");
    }

    public static void zzb(DataPoint dataPoint) throws IllegalArgumentException {
        String zza = zzj.zza(dataPoint, zzf.zzam);
        if (zza != null) {
            String valueOf = String.valueOf(dataPoint);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
            sb.append("Invalid data point: ");
            sb.append(valueOf);
            Log.w("Fitness", sb.toString());
            throw new IllegalArgumentException(zza);
        }
    }

    private final List<RawDataPoint> zzh() {
        return zza(this.zzav);
    }

    /* access modifiers changed from: 0000 */
    public final List<RawDataPoint> zza(List<DataSource> list) {
        ArrayList arrayList = new ArrayList(this.zzau.size());
        for (DataPoint rawDataPoint : this.zzau) {
            arrayList.add(new RawDataPoint(rawDataPoint, list));
        }
        return arrayList;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeList(parcel, 3, zzh(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzav, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzal);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
