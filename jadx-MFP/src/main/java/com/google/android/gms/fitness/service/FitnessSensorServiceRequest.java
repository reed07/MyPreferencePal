package com.google.android.gms.fitness.service;

import android.os.IBinder;
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
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.data.zzu;
import java.util.concurrent.TimeUnit;

@Class(creator = "FitnessSensorServiceRequestCreator")
@Reserved({1000})
public class FitnessSensorServiceRequest extends AbstractSafeParcelable {
    public static final Creator<FitnessSensorServiceRequest> CREATOR = new zzb();
    public static final int UNSPECIFIED = -1;
    @Field(getter = "getListenerBinder", id = 2, type = "android.os.IBinder")
    private final zzt zzhr;
    @Field(getter = "getSamplingRateMicros", id = 3)
    private final long zziz;
    @Field(getter = "getBatchIntervalMicros", id = 4)
    private final long zzja;
    @Field(getter = "getDataSource", id = 1)
    private final DataSource zzr;

    @Constructor
    FitnessSensorServiceRequest(@Param(id = 1) DataSource dataSource, @Param(id = 2) IBinder iBinder, @Param(id = 3) long j, @Param(id = 4) long j2) {
        this.zzr = dataSource;
        this.zzhr = zzu.zza(iBinder);
        this.zziz = j;
        this.zzja = j2;
    }

    public DataSource getDataSource() {
        return this.zzr;
    }

    public SensorEventDispatcher getDispatcher() {
        return new zzc(this.zzhr);
    }

    public long getSamplingRate(TimeUnit timeUnit) {
        long j = this.zziz;
        if (j == -1) {
            return -1;
        }
        return timeUnit.convert(j, TimeUnit.MICROSECONDS);
    }

    public long getBatchInterval(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzja, TimeUnit.MICROSECONDS);
    }

    public String toString() {
        return String.format("FitnessSensorServiceRequest{%s}", new Object[]{this.zzr});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzhr.asBinder(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zziz);
        SafeParcelWriter.writeLong(parcel, 4, this.zzja);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FitnessSensorServiceRequest)) {
            return false;
        }
        FitnessSensorServiceRequest fitnessSensorServiceRequest = (FitnessSensorServiceRequest) obj;
        return Objects.equal(this.zzr, fitnessSensorServiceRequest.zzr) && this.zziz == fitnessSensorServiceRequest.zziz && this.zzja == fitnessSensorServiceRequest.zzja;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzr, Long.valueOf(this.zziz), Long.valueOf(this.zzja));
    }
}
