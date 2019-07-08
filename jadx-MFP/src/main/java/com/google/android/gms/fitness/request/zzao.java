package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Class(creator = "SensorRegistrationRequestCreator")
@Reserved({14, 1000})
public final class zzao extends AbstractSafeParcelable {
    public static final Creator<zzao> CREATOR = new zzap();
    @Field(getter = "getSamplingRateMicros", id = 6)
    private final long zzec;
    @Field(getter = "getAccuracyMode", id = 10)
    private final int zzed;
    @Nullable
    @Field(getter = "getCallbackBinder", id = 13, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getIntent", id = 8)
    private final PendingIntent zzhi;
    @Field(getter = "getListenerBinder", id = 3, type = "android.os.IBinder")
    private zzt zzhr;
    @Field(getter = "getMaxDeliveryLatencyMicros", id = 7)
    private final long zzhs;
    @Field(getter = "getFastestRateMicros", id = 9)
    private final long zzht;
    @Field(defaultValueUnchecked = "null", getter = "getLocationRequests", id = 11)
    private final List<LocationRequest> zzhu;
    @Field(getter = "getRegistrationTimeOutMicros", id = 12)
    private final long zzhv;
    private final List<ClientIdentity> zzhw;
    @Field(getter = "getDataType", id = 2)
    private DataType zzq;
    @Field(getter = "getDataSource", id = 1)
    private DataSource zzr;

    @Constructor
    zzao(@Param(id = 1) DataSource dataSource, @Param(id = 2) DataType dataType, @Param(id = 3) IBinder iBinder, @Param(id = 4) int i, @Param(id = 5) int i2, @Param(id = 6) long j, @Param(id = 7) long j2, @Param(id = 8) PendingIntent pendingIntent, @Param(id = 9) long j3, @Param(id = 10) int i3, @Param(id = 11) List<LocationRequest> list, @Param(id = 12) long j4, @Param(id = 13) IBinder iBinder2) {
        zzt zzt;
        this.zzr = dataSource;
        this.zzq = dataType;
        if (iBinder == null) {
            zzt = null;
        } else {
            zzt = zzu.zza(iBinder);
        }
        this.zzhr = zzt;
        this.zzec = j == 0 ? (long) i : j;
        this.zzht = j3;
        this.zzhs = j2 == 0 ? (long) i2 : j2;
        this.zzhu = list;
        this.zzhi = pendingIntent;
        this.zzed = i3;
        this.zzhw = Collections.emptyList();
        this.zzhv = j4;
        this.zzgj = zzcr.zzj(iBinder2);
    }

    public zzao(SensorRequest sensorRequest, @Nullable zzt zzt, @Nullable PendingIntent pendingIntent, zzcq zzcq) {
        SensorRequest sensorRequest2 = sensorRequest;
        this(sensorRequest.getDataSource(), sensorRequest.getDataType(), zzt, pendingIntent, sensorRequest2.getSamplingRate(TimeUnit.MICROSECONDS), sensorRequest2.getFastestRate(TimeUnit.MICROSECONDS), sensorRequest2.getMaxDeliveryLatency(TimeUnit.MICROSECONDS), sensorRequest.getAccuracyMode(), null, Collections.emptyList(), sensorRequest.zzx(), zzcq);
    }

    private zzao(DataSource dataSource, DataType dataType, zzt zzt, PendingIntent pendingIntent, long j, long j2, long j3, int i, List<LocationRequest> list, List<ClientIdentity> list2, long j4, @Nullable zzcq zzcq) {
        this.zzr = dataSource;
        this.zzq = dataType;
        this.zzhr = zzt;
        this.zzhi = pendingIntent;
        this.zzec = j;
        this.zzht = j2;
        this.zzhs = j3;
        this.zzed = i;
        this.zzhu = null;
        this.zzhw = list2;
        this.zzhv = j4;
        this.zzgj = zzcq;
    }

    public final String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", new Object[]{this.zzq, this.zzr, Long.valueOf(this.zzec), Long.valueOf(this.zzht), Long.valueOf(this.zzhs)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzr, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzq, i, false);
        zzt zzt = this.zzhr;
        IBinder iBinder = null;
        SafeParcelWriter.writeIBinder(parcel, 3, zzt == null ? null : zzt.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 4, 0);
        SafeParcelWriter.writeInt(parcel, 5, 0);
        SafeParcelWriter.writeLong(parcel, 6, this.zzec);
        SafeParcelWriter.writeLong(parcel, 7, this.zzhs);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzhi, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzht);
        SafeParcelWriter.writeInt(parcel, 10, this.zzed);
        SafeParcelWriter.writeTypedList(parcel, 11, this.zzhu, false);
        SafeParcelWriter.writeLong(parcel, 12, this.zzhv);
        zzcq zzcq = this.zzgj;
        if (zzcq != null) {
            iBinder = zzcq.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 13, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof zzao) {
                zzao zzao = (zzao) obj;
                if (Objects.equal(this.zzr, zzao.zzr) && Objects.equal(this.zzq, zzao.zzq) && Objects.equal(this.zzhr, zzao.zzhr) && this.zzec == zzao.zzec && this.zzht == zzao.zzht && this.zzhs == zzao.zzhs && this.zzed == zzao.zzed && Objects.equal(this.zzhu, zzao.zzhu)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzr, this.zzq, this.zzhr, Long.valueOf(this.zzec), Long.valueOf(this.zzht), Long.valueOf(this.zzhs), Integer.valueOf(this.zzed), this.zzhu);
    }
}
