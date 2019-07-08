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
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.concurrent.TimeUnit;

@Class(creator = "DataUpdateRequestCreator")
@Reserved({1000})
public class DataUpdateRequest extends AbstractSafeParcelable {
    public static final Creator<DataUpdateRequest> CREATOR = new zzz();
    @Field(getter = "getDataSet", id = 3)
    private final DataSet zzeb;
    @Nullable
    @Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getStartTimeMillis", id = 1)
    private final long zzs;
    @Field(getter = "getEndTimeMillis", id = 2)
    private final long zzt;

    public static class Builder {
        /* access modifiers changed from: private */
        public DataSet zzeb;
        /* access modifiers changed from: private */
        public long zzs;
        /* access modifiers changed from: private */
        public long zzt;

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid start time :%d", Long.valueOf(j));
            Preconditions.checkArgument(j2 >= j, "Invalid end time :%d", Long.valueOf(j2));
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }

        public Builder setDataSet(DataSet dataSet) {
            Preconditions.checkNotNull(dataSet, "Must set the data set");
            this.zzeb = dataSet;
            return this;
        }

        public DataUpdateRequest build() {
            boolean z;
            Preconditions.checkNotZero(this.zzs, (Object) "Must set a non-zero value for startTimeMillis/startTime");
            Preconditions.checkNotZero(this.zzt, (Object) "Must set a non-zero value for endTimeMillis/endTime");
            Preconditions.checkNotNull(this.zzeb, "Must set the data set");
            for (DataPoint dataPoint : this.zzeb.getDataPoints()) {
                long startTime = dataPoint.getStartTime(TimeUnit.MILLISECONDS);
                long endTime = dataPoint.getEndTime(TimeUnit.MILLISECONDS);
                if (startTime <= endTime) {
                    int i = (startTime > 0 ? 1 : (startTime == 0 ? 0 : -1));
                    if ((i == 0 || startTime >= this.zzs) && ((i == 0 || startTime <= this.zzt) && endTime <= this.zzt && endTime >= this.zzs)) {
                        z = false;
                        Preconditions.checkState(!z, "Data Point's startTimeMillis %d, endTimeMillis %d should lie between timeRange provided in the request. StartTimeMillis %d, EndTimeMillis: %d", Long.valueOf(startTime), Long.valueOf(endTime), Long.valueOf(this.zzs), Long.valueOf(this.zzt));
                    }
                }
                z = true;
                Preconditions.checkState(!z, "Data Point's startTimeMillis %d, endTimeMillis %d should lie between timeRange provided in the request. StartTimeMillis %d, EndTimeMillis: %d", Long.valueOf(startTime), Long.valueOf(endTime), Long.valueOf(this.zzs), Long.valueOf(this.zzt));
            }
            return new DataUpdateRequest(this);
        }
    }

    @Constructor
    public DataUpdateRequest(@Param(id = 1) long j, @Param(id = 2) long j2, @Param(id = 3) DataSet dataSet, @Nullable @Param(id = 4) IBinder iBinder) {
        this.zzs = j;
        this.zzt = j2;
        this.zzeb = dataSet;
        this.zzgj = zzcr.zzj(iBinder);
    }

    private DataUpdateRequest(Builder builder) {
        this(builder.zzs, builder.zzt, builder.zzeb, null);
    }

    public DataUpdateRequest(DataUpdateRequest dataUpdateRequest, IBinder iBinder) {
        this(dataUpdateRequest.zzs, dataUpdateRequest.zzt, dataUpdateRequest.getDataSet(), iBinder);
    }

    public final long zzu() {
        return this.zzs;
    }

    public final long zzv() {
        return this.zzt;
    }

    public DataSet getDataSet() {
        return this.zzeb;
    }

    public IBinder getCallbackBinder() {
        zzcq zzcq = this.zzgj;
        if (zzcq == null) {
            return null;
        }
        return zzcq.asBinder();
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj != this) {
            if (obj instanceof DataUpdateRequest) {
                DataUpdateRequest dataUpdateRequest = (DataUpdateRequest) obj;
                if (this.zzs == dataUpdateRequest.zzs && this.zzt == dataUpdateRequest.zzt && Objects.equal(this.zzeb, dataUpdateRequest.zzeb)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt), this.zzeb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTimeMillis", Long.valueOf(this.zzs)).add("endTimeMillis", Long.valueOf(this.zzt)).add("dataSet", this.zzeb).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataSet(), i, false);
        SafeParcelWriter.writeIBinder(parcel, 4, getCallbackBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
