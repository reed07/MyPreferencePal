package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import com.google.android.gms.internal.fitness.zzbh;
import com.google.android.gms.internal.fitness.zzbi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Class(creator = "DataReadRequestCreator")
@Reserved({11, 15, 1000})
public class DataReadRequest extends AbstractSafeParcelable {
    public static final Creator<DataReadRequest> CREATOR = new zzn();
    public static final int NO_LIMIT = 0;
    @Field(getter = "getLimit", id = 10)
    private final int limit;
    @Field(getter = "getDataTypes", id = 1)
    private final List<DataType> zzah;
    @Field(getter = "getBucketType", id = 7)
    private final int zzak;
    @Field(getter = "getDataSources", id = 2)
    private final List<DataSource> zzgm;
    @Field(getter = "getAggregatedDataTypes", id = 5)
    private final List<DataType> zzgr;
    @Field(getter = "getAggregatedDataSources", id = 6)
    private final List<DataSource> zzgs;
    @Field(getter = "getBucketDurationMillis", id = 8)
    private final long zzgt;
    @Field(getter = "getActivityDataSource", id = 9)
    private final DataSource zzgu;
    @Field(getter = "flushBufferBeforeRead", id = 12)
    private final boolean zzgv;
    @Field(getter = "areServerQueriesEnabled", id = 13)
    private final boolean zzgw;
    @Nullable
    @Field(getter = "getCallbackBinder", id = 14, type = "android.os.IBinder")
    private final zzbh zzgx;
    @Field(getter = "getFilteredDevices", id = 16)
    private final List<Device> zzgy;
    @Field(getter = "getFilteredDataQualityStandards", id = 17)
    private final List<Integer> zzgz;
    @Field(getter = "getIntervalStartTimesNanos", id = 18)
    private final List<Long> zzha;
    @Field(getter = "getIntervalEndTimesNanos", id = 19)
    private final List<Long> zzhb;
    @Field(getter = "getStartTimeMillis", id = 3)
    private final long zzs;
    @Field(getter = "getEndTimeMillis", id = 4)
    private final long zzt;

    public static class Builder {
        /* access modifiers changed from: private */
        public int limit = 0;
        /* access modifiers changed from: private */
        public List<DataType> zzah = new ArrayList();
        /* access modifiers changed from: private */
        public int zzak = 0;
        /* access modifiers changed from: private */
        public List<DataSource> zzgm = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataType> zzgr = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataSource> zzgs = new ArrayList();
        /* access modifiers changed from: private */
        public long zzgt = 0;
        /* access modifiers changed from: private */
        public DataSource zzgu;
        private boolean zzgv = false;
        /* access modifiers changed from: private */
        public boolean zzgw = false;
        /* access modifiers changed from: private */
        public final List<Device> zzgy = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Integer> zzgz = new ArrayList();
        /* access modifiers changed from: private */
        public List<Long> zzha = new ArrayList();
        /* access modifiers changed from: private */
        public List<Long> zzhb = new ArrayList();
        /* access modifiers changed from: private */
        public long zzs;
        /* access modifiers changed from: private */
        public long zzt;

        public Builder read(DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkArgument(!this.zzgs.contains(dataSource), "Cannot add the same data source as aggregated and detailed");
            if (!this.zzgm.contains(dataSource)) {
                this.zzgm.add(dataSource);
            }
            return this;
        }

        public Builder read(DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.zzgr.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder aggregate(DataSource dataSource, DataType dataType) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkState(!this.zzgm.contains(dataSource), "Cannot add the same data source for aggregated and detailed");
            DataType dataType2 = dataSource.getDataType();
            List aggregatesForInput = DataType.getAggregatesForInput(dataType2);
            Preconditions.checkArgument(!aggregatesForInput.isEmpty(), "Unsupported input data type specified for aggregation: %s", dataType2);
            Preconditions.checkArgument(aggregatesForInput.contains(dataType), "Invalid output aggregate data type specified: %s -> %s", dataType2, dataType);
            if (!this.zzgs.contains(dataSource)) {
                this.zzgs.add(dataSource);
            }
            return this;
        }

        public Builder aggregate(DataType dataType, DataType dataType2) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.zzah.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            List aggregatesForInput = DataType.getAggregatesForInput(dataType);
            Preconditions.checkArgument(!aggregatesForInput.isEmpty(), "Unsupported input data type specified for aggregation: %s", dataType);
            Preconditions.checkArgument(aggregatesForInput.contains(dataType2), "Invalid output aggregate data type specified: %s -> %s", dataType, dataType2);
            if (!this.zzgr.contains(dataType)) {
                this.zzgr.add(dataType);
            }
            return this;
        }

        public Builder bucketByTime(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(this.zzak == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzak));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration: %d", Integer.valueOf(i));
            this.zzak = 1;
            this.zzgt = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder bucketByActivityType(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(this.zzak == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzak));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzak = 3;
            this.zzgt = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder bucketByActivityType(int i, TimeUnit timeUnit, DataSource dataSource) {
            Preconditions.checkArgument(this.zzak == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzak));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            Preconditions.checkArgument(dataSource != null, "Invalid activity data source specified");
            Preconditions.checkArgument(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.zzgu = dataSource;
            this.zzak = 3;
            this.zzgt = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder addFilteredDataQualityStandard(int i) {
            Preconditions.checkArgument(this.zzgy.isEmpty(), "Cannot add data quality standard filter when filtering by device.");
            this.zzgz.add(Integer.valueOf(i));
            return this;
        }

        public Builder bucketByActivitySegment(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(this.zzak == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzak));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzak = 4;
            this.zzgt = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder bucketByActivitySegment(int i, TimeUnit timeUnit, DataSource dataSource) {
            Preconditions.checkArgument(this.zzak == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzak));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            Preconditions.checkArgument(dataSource != null, "Invalid activity data source specified");
            Preconditions.checkArgument(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.zzgu = dataSource;
            this.zzak = 4;
            this.zzgt = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder bucketBySession(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(this.zzak == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzak));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for a session: %d", Integer.valueOf(i));
            this.zzak = 2;
            this.zzgt = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder setTimeRange(long j, long j2, TimeUnit timeUnit) {
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }

        public Builder enableServerQueries() {
            this.zzgw = true;
            return this;
        }

        public Builder setLimit(int i) {
            Preconditions.checkArgument(i > 0, "Invalid limit %d is specified", Integer.valueOf(i));
            this.limit = i;
            return this;
        }

        public DataReadRequest build() {
            boolean z = false;
            Preconditions.checkState(!this.zzgm.isEmpty() || !this.zzah.isEmpty() || !this.zzgs.isEmpty() || !this.zzgr.isEmpty(), "Must add at least one data source (aggregated or detailed)");
            if (this.zzak != 5) {
                Preconditions.checkState(this.zzs > 0, "Invalid start time: %s", Long.valueOf(this.zzs));
                long j = this.zzt;
                Preconditions.checkState(j > 0 && j > this.zzs, "Invalid end time: %s", Long.valueOf(this.zzt));
            }
            boolean z2 = this.zzgs.isEmpty() && this.zzgr.isEmpty();
            if (this.zzak == 0) {
                Preconditions.checkState(z2, "Must specify a valid bucketing strategy while requesting aggregation");
            }
            if (!z2) {
                if (this.zzak != 0) {
                    z = true;
                }
                Preconditions.checkState(z, "Must specify a valid bucketing strategy while requesting aggregation");
            }
            return new DataReadRequest(this);
        }
    }

    @Constructor
    DataReadRequest(@Param(id = 1) List<DataType> list, @Param(id = 2) List<DataSource> list2, @Param(id = 3) long j, @Param(id = 4) long j2, @Param(id = 5) List<DataType> list3, @Param(id = 6) List<DataSource> list4, @Param(id = 7) int i, @Param(id = 8) long j3, @Param(id = 9) DataSource dataSource, @Param(id = 10) int i2, @Param(id = 12) boolean z, @Param(id = 13) boolean z2, @Param(id = 14) IBinder iBinder, @Param(id = 16) List<Device> list5, @Param(id = 17) List<Integer> list6, @Param(id = 18) List<Long> list7, @Param(id = 19) List<Long> list8) {
        zzbh zzbh;
        this.zzah = list;
        this.zzgm = list2;
        this.zzs = j;
        this.zzt = j2;
        this.zzgr = list3;
        this.zzgs = list4;
        this.zzak = i;
        this.zzgt = j3;
        this.zzgu = dataSource;
        this.limit = i2;
        this.zzgv = z;
        this.zzgw = z2;
        if (iBinder == null) {
            zzbh = null;
        } else {
            zzbh = zzbi.zzc(iBinder);
        }
        this.zzgx = zzbh;
        this.zzgy = list5 == null ? Collections.emptyList() : list5;
        this.zzgz = list6 == null ? Collections.emptyList() : list6;
        this.zzha = list7 == null ? Collections.emptyList() : list7;
        this.zzhb = list8 == null ? Collections.emptyList() : list8;
        Preconditions.checkArgument(this.zzha.size() == this.zzhb.size(), "Unequal number of interval start and end times.");
    }

    private DataReadRequest(Builder builder) {
        this(builder.zzah, builder.zzgm, builder.zzs, builder.zzt, builder.zzgr, builder.zzgs, builder.zzak, builder.zzgt, builder.zzgu, builder.limit, false, builder.zzgw, (zzbh) null, builder.zzgy, builder.zzgz, builder.zzha, builder.zzhb);
    }

    public DataReadRequest(DataReadRequest dataReadRequest, zzbh zzbh) {
        DataReadRequest dataReadRequest2 = dataReadRequest;
        this(dataReadRequest2.zzah, dataReadRequest2.zzgm, dataReadRequest2.zzs, dataReadRequest2.zzt, dataReadRequest2.zzgr, dataReadRequest2.zzgs, dataReadRequest2.zzak, dataReadRequest2.zzgt, dataReadRequest2.zzgu, dataReadRequest2.limit, dataReadRequest2.zzgv, dataReadRequest2.zzgw, zzbh, dataReadRequest2.zzgy, dataReadRequest2.zzgz, dataReadRequest2.zzha, dataReadRequest2.zzhb);
    }

    private DataReadRequest(List<DataType> list, List<DataSource> list2, long j, long j2, List<DataType> list3, List<DataSource> list4, int i, long j3, DataSource dataSource, int i2, boolean z, boolean z2, @Nullable zzbh zzbh, List<Device> list5, List<Integer> list6, List<Long> list7, List<Long> list8) {
        this(list, list2, j, j2, list3, list4, i, j3, dataSource, i2, z, z2, zzbh == null ? null : zzbh.asBinder(), list5, list6, list7, list8);
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public List<DataType> getAggregatedDataTypes() {
        return this.zzgr;
    }

    public List<DataSource> getAggregatedDataSources() {
        return this.zzgs;
    }

    public int getBucketType() {
        return this.zzak;
    }

    public long getBucketDuration(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzgt, TimeUnit.MILLISECONDS);
    }

    @Nullable
    public DataSource getActivityDataSource() {
        return this.zzgu;
    }

    public int getLimit() {
        return this.limit;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DataReadRequest) {
                DataReadRequest dataReadRequest = (DataReadRequest) obj;
                if (this.zzah.equals(dataReadRequest.zzah) && this.zzgm.equals(dataReadRequest.zzgm) && this.zzs == dataReadRequest.zzs && this.zzt == dataReadRequest.zzt && this.zzak == dataReadRequest.zzak && this.zzgs.equals(dataReadRequest.zzgs) && this.zzgr.equals(dataReadRequest.zzgr) && Objects.equal(this.zzgu, dataReadRequest.zzgu) && this.zzgt == dataReadRequest.zzgt && this.zzgw == dataReadRequest.zzgw && this.limit == dataReadRequest.limit && this.zzgv == dataReadRequest.zzgv && Objects.equal(this.zzgx, dataReadRequest.zzgx) && Objects.equal(this.zzgy, dataReadRequest.zzgy) && Objects.equal(this.zzgz, dataReadRequest.zzgz)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzak), Long.valueOf(this.zzs), Long.valueOf(this.zzt));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataReadRequest{");
        if (!this.zzah.isEmpty()) {
            for (DataType zzm : this.zzah) {
                sb.append(zzm.zzm());
                sb.append(" ");
            }
        }
        if (!this.zzgm.isEmpty()) {
            for (DataSource debugString : this.zzgm) {
                sb.append(debugString.toDebugString());
                sb.append(" ");
            }
        }
        if (this.zzak != 0) {
            sb.append("bucket by ");
            sb.append(Bucket.zza(this.zzak));
            if (this.zzgt > 0) {
                sb.append(" >");
                sb.append(this.zzgt);
                sb.append("ms");
            }
            sb.append(": ");
        }
        if (!this.zzgr.isEmpty()) {
            for (DataType zzm2 : this.zzgr) {
                sb.append(zzm2.zzm());
                sb.append(" ");
            }
        }
        if (!this.zzgs.isEmpty()) {
            for (DataSource debugString2 : this.zzgs) {
                sb.append(debugString2.toDebugString());
                sb.append(" ");
            }
        }
        sb.append(String.format(Locale.US, "(%tF %tT - %tF %tT)", new Object[]{Long.valueOf(this.zzs), Long.valueOf(this.zzs), Long.valueOf(this.zzt), Long.valueOf(this.zzt)}));
        if (this.zzgu != null) {
            sb.append("activities: ");
            sb.append(this.zzgu.toDebugString());
        }
        if (!this.zzgz.isEmpty()) {
            sb.append("quality: ");
            for (Integer intValue : this.zzgz) {
                sb.append(DataSource.zzd(intValue.intValue()));
                sb.append(" ");
            }
        }
        if (this.zzgw) {
            sb.append(" +server");
        }
        sb.append("}");
        return sb.toString();
    }

    public List<Integer> getFilteredDataQualityStandards() {
        return this.zzgz;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getDataSources(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzs);
        SafeParcelWriter.writeLong(parcel, 4, this.zzt);
        SafeParcelWriter.writeTypedList(parcel, 5, getAggregatedDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getAggregatedDataSources(), false);
        SafeParcelWriter.writeInt(parcel, 7, getBucketType());
        SafeParcelWriter.writeLong(parcel, 8, this.zzgt);
        SafeParcelWriter.writeParcelable(parcel, 9, getActivityDataSource(), i, false);
        SafeParcelWriter.writeInt(parcel, 10, getLimit());
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzgv);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzgw);
        zzbh zzbh = this.zzgx;
        SafeParcelWriter.writeIBinder(parcel, 14, zzbh == null ? null : zzbh.asBinder(), false);
        SafeParcelWriter.writeTypedList(parcel, 16, this.zzgy, false);
        SafeParcelWriter.writeIntegerList(parcel, 17, getFilteredDataQualityStandards(), false);
        SafeParcelWriter.writeLongList(parcel, 18, this.zzha, false);
        SafeParcelWriter.writeLongList(parcel, 19, this.zzhb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
