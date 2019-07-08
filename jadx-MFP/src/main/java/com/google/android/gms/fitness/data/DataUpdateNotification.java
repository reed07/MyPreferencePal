package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.concurrent.TimeUnit;

@Class(creator = "DataUpdateNotificationCreator")
@Reserved({1000})
public class DataUpdateNotification extends AbstractSafeParcelable {
    public static final String ACTION = "com.google.android.gms.fitness.DATA_UPDATE_NOTIFICATION";
    public static final Creator<DataUpdateNotification> CREATOR = new zzn();
    public static final String EXTRA_DATA_UPDATE_NOTIFICATION = "vnd.google.fitness.data_udpate_notification";
    public static final int OPERATION_DELETE = 2;
    public static final int OPERATION_INSERT = 1;
    public static final int OPERATION_UPDATE = 3;
    @Field(getter = "getUpdateStartTimeNanos", id = 1)
    private final long zzbz;
    @Field(getter = "getUpdateEndTimeNanos", id = 2)
    private final long zzca;
    @Field(getter = "getOperationType", id = 3)
    private final int zzcb;
    @Field(getter = "getDataType", id = 5)
    private final DataType zzq;
    @Field(getter = "getDataSource", id = 4)
    private final DataSource zzr;

    @Constructor
    public DataUpdateNotification(@Param(id = 1) long j, @Param(id = 2) long j2, @Param(id = 3) int i, @Param(id = 4) DataSource dataSource, @Param(id = 5) DataType dataType) {
        this.zzbz = j;
        this.zzca = j2;
        this.zzcb = i;
        this.zzr = dataSource;
        this.zzq = dataType;
    }

    public static DataUpdateNotification getDataUpdateNotification(Intent intent) {
        return (DataUpdateNotification) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_UPDATE_NOTIFICATION, CREATOR);
    }

    public long getUpdateStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzbz, TimeUnit.NANOSECONDS);
    }

    public long getUpdateEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzca, TimeUnit.NANOSECONDS);
    }

    public int getOperationType() {
        return this.zzcb;
    }

    public DataSource getDataSource() {
        return this.zzr;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataUpdateNotification)) {
            return false;
        }
        DataUpdateNotification dataUpdateNotification = (DataUpdateNotification) obj;
        return this.zzbz == dataUpdateNotification.zzbz && this.zzca == dataUpdateNotification.zzca && this.zzcb == dataUpdateNotification.zzcb && Objects.equal(this.zzr, dataUpdateNotification.zzr) && Objects.equal(this.zzq, dataUpdateNotification.zzq);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzbz), Long.valueOf(this.zzca), Integer.valueOf(this.zzcb), this.zzr, this.zzq);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("updateStartTimeNanos", Long.valueOf(this.zzbz)).add("updateEndTimeNanos", Long.valueOf(this.zzca)).add("operationType", Integer.valueOf(this.zzcb)).add("dataSource", this.zzr).add("dataType", this.zzq).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzbz);
        SafeParcelWriter.writeLong(parcel, 2, this.zzca);
        SafeParcelWriter.writeInt(parcel, 3, getOperationType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
