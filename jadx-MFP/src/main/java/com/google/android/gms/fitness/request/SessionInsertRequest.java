package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.util.Log;
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
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import com.google.android.gms.internal.fitness.zze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Class(creator = "SessionInsertRequestCreator")
@Reserved({5, 1000})
public class SessionInsertRequest extends AbstractSafeParcelable {
    public static final Creator<SessionInsertRequest> CREATOR = new zzau();
    /* access modifiers changed from: private */
    public static final TimeUnit zzhz = TimeUnit.MILLISECONDS;
    @Field(getter = "getDataSets", id = 2)
    private final List<DataSet> zzaj;
    @Nullable
    @Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getAggregateDataPoints", id = 3)
    private final List<DataPoint> zzia;
    @Field(getter = "getSession", id = 1)
    private final Session zzz;

    public static class Builder {
        /* access modifiers changed from: private */
        public List<DataSet> zzaj = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataPoint> zzia = new ArrayList();
        private List<DataSource> zzib = new ArrayList();
        /* access modifiers changed from: private */
        public Session zzz;

        public Builder setSession(Session session) {
            this.zzz = session;
            return this;
        }

        public Builder addDataSet(DataSet dataSet) {
            Preconditions.checkArgument(dataSet != null, "Must specify a valid data set.");
            DataSource dataSource = dataSet.getDataSource();
            Preconditions.checkState(!this.zzib.contains(dataSource), "Data set for this data source %s is already added.", dataSource);
            Preconditions.checkArgument(!dataSet.getDataPoints().isEmpty(), "No data points specified in the input data set.");
            this.zzib.add(dataSource);
            this.zzaj.add(dataSet);
            return this;
        }

        public Builder addAggregateDataPoint(DataPoint dataPoint) {
            Preconditions.checkArgument(dataPoint != null, "Must specify a valid aggregate data point.");
            DataSource dataSource = dataPoint.getDataSource();
            Preconditions.checkState(!this.zzib.contains(dataSource), "Data set/Aggregate data point for this data source %s is already added.", dataSource);
            DataSet.zzb(dataPoint);
            this.zzib.add(dataSource);
            this.zzia.add(dataPoint);
            return this;
        }

        public SessionInsertRequest build() {
            boolean z = true;
            Preconditions.checkState(this.zzz != null, "Must specify a valid session.");
            if (this.zzz.getEndTime(TimeUnit.MILLISECONDS) == 0) {
                z = false;
            }
            Preconditions.checkState(z, "Must specify a valid end time, cannot insert a continuing session.");
            for (DataSet dataPoints : this.zzaj) {
                for (DataPoint zzd : dataPoints.getDataPoints()) {
                    zzd(zzd);
                }
            }
            for (DataPoint zzd2 : this.zzia) {
                zzd(zzd2);
            }
            return new SessionInsertRequest(this);
        }

        private final void zzd(DataPoint dataPoint) {
            DataPoint dataPoint2 = dataPoint;
            long startTime = this.zzz.getStartTime(TimeUnit.NANOSECONDS);
            long endTime = this.zzz.getEndTime(TimeUnit.NANOSECONDS);
            long timestamp = dataPoint2.getTimestamp(TimeUnit.NANOSECONDS);
            if (timestamp != 0) {
                if (timestamp < startTime || timestamp > endTime) {
                    timestamp = zze.zza(timestamp, TimeUnit.NANOSECONDS, SessionInsertRequest.zzhz);
                }
                Preconditions.checkState(timestamp >= startTime && timestamp <= endTime, "Data point %s has time stamp outside session interval [%d, %d]", dataPoint2, Long.valueOf(startTime), Long.valueOf(endTime));
                if (dataPoint2.getTimestamp(TimeUnit.NANOSECONDS) != timestamp) {
                    Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", new Object[]{Long.valueOf(dataPoint2.getTimestamp(TimeUnit.NANOSECONDS)), Long.valueOf(timestamp), SessionInsertRequest.zzhz}));
                    dataPoint2.setTimestamp(timestamp, TimeUnit.NANOSECONDS);
                }
            }
            long startTime2 = this.zzz.getStartTime(TimeUnit.NANOSECONDS);
            long endTime2 = this.zzz.getEndTime(TimeUnit.NANOSECONDS);
            long startTime3 = dataPoint2.getStartTime(TimeUnit.NANOSECONDS);
            long endTime3 = dataPoint2.getEndTime(TimeUnit.NANOSECONDS);
            if (startTime3 != 0 && endTime3 != 0) {
                if (endTime3 > endTime2) {
                    endTime3 = zze.zza(endTime3, TimeUnit.NANOSECONDS, SessionInsertRequest.zzhz);
                }
                Preconditions.checkState(startTime3 >= startTime2 && endTime3 <= endTime2, "Data point %s has start and end times outside session interval [%d, %d]", dataPoint2, Long.valueOf(startTime2), Long.valueOf(endTime2));
                if (endTime3 != dataPoint2.getEndTime(TimeUnit.NANOSECONDS)) {
                    Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", new Object[]{Long.valueOf(dataPoint2.getEndTime(TimeUnit.NANOSECONDS)), Long.valueOf(endTime3), SessionInsertRequest.zzhz}));
                    dataPoint.setTimeInterval(startTime3, endTime3, TimeUnit.NANOSECONDS);
                }
            }
        }
    }

    @Constructor
    SessionInsertRequest(@Param(id = 1) Session session, @Param(id = 2) List<DataSet> list, @Param(id = 3) List<DataPoint> list2, @Param(id = 4) IBinder iBinder) {
        this.zzz = session;
        this.zzaj = Collections.unmodifiableList(list);
        this.zzia = Collections.unmodifiableList(list2);
        this.zzgj = zzcr.zzj(iBinder);
    }

    private SessionInsertRequest(Builder builder) {
        this(builder.zzz, builder.zzaj, builder.zzia, (zzcq) null);
    }

    public SessionInsertRequest(SessionInsertRequest sessionInsertRequest, zzcq zzcq) {
        this(sessionInsertRequest.zzz, sessionInsertRequest.zzaj, sessionInsertRequest.zzia, zzcq);
    }

    private SessionInsertRequest(Session session, List<DataSet> list, List<DataPoint> list2, @Nullable zzcq zzcq) {
        this.zzz = session;
        this.zzaj = Collections.unmodifiableList(list);
        this.zzia = Collections.unmodifiableList(list2);
        this.zzgj = zzcq;
    }

    public Session getSession() {
        return this.zzz;
    }

    public List<DataSet> getDataSets() {
        return this.zzaj;
    }

    public List<DataPoint> getAggregateDataPoints() {
        return this.zzia;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj != this) {
            if (obj instanceof SessionInsertRequest) {
                SessionInsertRequest sessionInsertRequest = (SessionInsertRequest) obj;
                if (Objects.equal(this.zzz, sessionInsertRequest.zzz) && Objects.equal(this.zzaj, sessionInsertRequest.zzaj) && Objects.equal(this.zzia, sessionInsertRequest.zzia)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzz, this.zzaj, this.zzia);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("session", this.zzz).add("dataSets", this.zzaj).add("aggregateDataPoints", this.zzia).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getSession(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getDataSets(), false);
        SafeParcelWriter.writeTypedList(parcel, 3, getAggregateDataPoints(), false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
