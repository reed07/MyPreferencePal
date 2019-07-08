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
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Class(creator = "DataDeleteRequestCreator")
@Reserved({9, 1000})
public class DataDeleteRequest extends AbstractSafeParcelable {
    public static final Creator<DataDeleteRequest> CREATOR = new zzj();
    @Field(getter = "getDataTypes", id = 4)
    private final List<DataType> zzah;
    @Nullable
    @Field(getter = "getCallbackBinder", id = 8, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getDataSources", id = 3)
    private final List<DataSource> zzgm;
    @Field(getter = "getSessions", id = 5)
    private final List<Session> zzgn;
    @Field(getter = "deleteAllData", id = 6)
    private final boolean zzgo;
    @Field(getter = "deleteAllSessions", id = 7)
    private final boolean zzgp;
    @Field(getter = "getStartTimeMillis", id = 1)
    private final long zzs;
    @Field(getter = "getEndTimeMillis", id = 2)
    private final long zzt;

    public static class Builder {
        /* access modifiers changed from: private */
        public List<DataType> zzah = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataSource> zzgm = new ArrayList();
        /* access modifiers changed from: private */
        public List<Session> zzgn = new ArrayList();
        /* access modifiers changed from: private */
        public boolean zzgo = false;
        /* access modifiers changed from: private */
        public boolean zzgp = false;
        /* access modifiers changed from: private */
        public long zzs;
        /* access modifiers changed from: private */
        public long zzt;

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid start time :%d", Long.valueOf(j));
            Preconditions.checkArgument(j2 > j, "Invalid end time :%d", Long.valueOf(j2));
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }

        public Builder deleteAllData() {
            Preconditions.checkArgument(this.zzah.isEmpty(), "Specific data type already added for deletion. deleteAllData() will delete all data types and cannot be combined with addDataType()");
            Preconditions.checkArgument(this.zzgm.isEmpty(), "Specific data source already added for deletion. deleteAllData() will delete all data sources and cannot be combined with addDataSource()");
            this.zzgo = true;
            return this;
        }

        public Builder addDataType(DataType dataType) {
            boolean z = true;
            Preconditions.checkArgument(!this.zzgo, "All data is already marked for deletion.  addDataType() cannot be combined with deleteAllData()");
            if (dataType == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "Must specify a valid data type");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder addDataSource(DataSource dataSource) {
            boolean z = true;
            Preconditions.checkArgument(!this.zzgo, "All data is already marked for deletion.  addDataSource() cannot be combined with deleteAllData()");
            if (dataSource == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "Must specify a valid data source");
            if (!this.zzgm.contains(dataSource)) {
                this.zzgm.add(dataSource);
            }
            return this;
        }

        public Builder addSession(Session session) {
            Preconditions.checkArgument(!this.zzgp, "All sessions already marked for deletion.  addSession() cannot be combined with deleteAllSessions()");
            boolean z = false;
            Preconditions.checkArgument(session != null, "Must specify a valid session");
            if (session.getEndTime(TimeUnit.MILLISECONDS) > 0) {
                z = true;
            }
            Preconditions.checkArgument(z, "Cannot delete an ongoing session. Please stop the session prior to deleting it");
            this.zzgn.add(session);
            return this;
        }

        public Builder deleteAllSessions() {
            Preconditions.checkArgument(this.zzgn.isEmpty(), "Specific session already added for deletion. deleteAllData() will delete all sessions and cannot be combined with addSession()");
            this.zzgp = true;
            return this;
        }

        public DataDeleteRequest build() {
            long j = this.zzs;
            Preconditions.checkState(j > 0 && this.zzt > j, "Must specify a valid time interval");
            Preconditions.checkState((this.zzgo || !this.zzgm.isEmpty() || !this.zzah.isEmpty()) || (this.zzgp || !this.zzgn.isEmpty()), "No data or session marked for deletion");
            if (!this.zzgn.isEmpty()) {
                for (Session session : this.zzgn) {
                    Preconditions.checkState(session.getStartTime(TimeUnit.MILLISECONDS) >= this.zzs && session.getEndTime(TimeUnit.MILLISECONDS) <= this.zzt, "Session %s is outside the time interval [%d, %d]", session, Long.valueOf(this.zzs), Long.valueOf(this.zzt));
                }
            }
            return new DataDeleteRequest(this);
        }
    }

    @Constructor
    DataDeleteRequest(@Param(id = 1) long j, @Param(id = 2) long j2, @Param(id = 3) List<DataSource> list, @Param(id = 4) List<DataType> list2, @Param(id = 5) List<Session> list3, @Param(id = 6) boolean z, @Param(id = 7) boolean z2, @Param(id = 8) IBinder iBinder) {
        this.zzs = j;
        this.zzt = j2;
        this.zzgm = Collections.unmodifiableList(list);
        this.zzah = Collections.unmodifiableList(list2);
        this.zzgn = list3;
        this.zzgo = z;
        this.zzgp = z2;
        this.zzgj = zzcr.zzj(iBinder);
    }

    private DataDeleteRequest(Builder builder) {
        this(builder.zzs, builder.zzt, builder.zzgm, builder.zzah, builder.zzgn, builder.zzgo, builder.zzgp, (zzcq) null);
    }

    public DataDeleteRequest(DataDeleteRequest dataDeleteRequest, zzcq zzcq) {
        this(dataDeleteRequest.zzs, dataDeleteRequest.zzt, dataDeleteRequest.zzgm, dataDeleteRequest.zzah, dataDeleteRequest.zzgn, dataDeleteRequest.zzgo, dataDeleteRequest.zzgp, zzcq);
    }

    private DataDeleteRequest(long j, long j2, List<DataSource> list, List<DataType> list2, List<Session> list3, boolean z, boolean z2, @Nullable zzcq zzcq) {
        this.zzs = j;
        this.zzt = j2;
        this.zzgm = Collections.unmodifiableList(list);
        this.zzah = Collections.unmodifiableList(list2);
        this.zzgn = list3;
        this.zzgo = z;
        this.zzgp = z2;
        this.zzgj = zzcq;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public List<Session> getSessions() {
        return this.zzgn;
    }

    public boolean deleteAllData() {
        return this.zzgo;
    }

    public boolean deleteAllSessions() {
        return this.zzgp;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj != this) {
            if (obj instanceof DataDeleteRequest) {
                DataDeleteRequest dataDeleteRequest = (DataDeleteRequest) obj;
                if (this.zzs == dataDeleteRequest.zzs && this.zzt == dataDeleteRequest.zzt && Objects.equal(this.zzgm, dataDeleteRequest.zzgm) && Objects.equal(this.zzah, dataDeleteRequest.zzah) && Objects.equal(this.zzgn, dataDeleteRequest.zzgn) && this.zzgo == dataDeleteRequest.zzgo && this.zzgp == dataDeleteRequest.zzgp) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTimeMillis", Long.valueOf(this.zzs)).add("endTimeMillis", Long.valueOf(this.zzt)).add("dataSources", this.zzgm).add("dateTypes", this.zzah).add("sessions", this.zzgn).add("deleteAllData", Boolean.valueOf(this.zzgo)).add("deleteAllSessions", Boolean.valueOf(this.zzgp)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeTypedList(parcel, 3, getDataSources(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getSessions(), false);
        SafeParcelWriter.writeBoolean(parcel, 6, deleteAllData());
        SafeParcelWriter.writeBoolean(parcel, 7, deleteAllSessions());
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 8, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
