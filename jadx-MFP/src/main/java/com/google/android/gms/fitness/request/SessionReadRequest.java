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
import com.google.android.gms.internal.fitness.zzck;
import com.google.android.gms.internal.fitness.zzcl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Class(creator = "SessionReadRequestCreator")
@Reserved({11, 1000})
public class SessionReadRequest extends AbstractSafeParcelable {
    public static final Creator<SessionReadRequest> CREATOR = new zzaw();
    @Field(getter = "getDataTypes", id = 5)
    private final List<DataType> zzah;
    @Field(getter = "getDataSources", id = 6)
    private final List<DataSource> zzgm;
    @Field(getter = "areServerQueriesEnabled", id = 8)
    private final boolean zzgw;
    @Field(getter = "getSessionName", id = 1)
    private final String zzic;
    @Field(getter = "getSessionId", id = 2)
    private final String zzid;
    @Field(getter = "includeSessionsFromAllApps", id = 7)
    private boolean zzie;
    @Field(getter = "getExcludedPackages", id = 9)
    private final List<String> zzif;
    @Nullable
    @Field(getter = "getCallbackBinder", id = 10, type = "android.os.IBinder")
    private final zzck zzig;
    @Field(getter = "getStartTimeMillis", id = 3)
    private final long zzs;
    @Field(getter = "getEndTimeMillis", id = 4)
    private final long zzt;

    public static class Builder {
        /* access modifiers changed from: private */
        public List<DataType> zzah = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataSource> zzgm = new ArrayList();
        /* access modifiers changed from: private */
        public boolean zzgw = false;
        /* access modifiers changed from: private */
        public String zzic;
        /* access modifiers changed from: private */
        public String zzid;
        /* access modifiers changed from: private */
        public List<String> zzif = new ArrayList();
        /* access modifiers changed from: private */
        public boolean zzih = false;
        /* access modifiers changed from: private */
        public long zzs = 0;
        /* access modifiers changed from: private */
        public long zzt = 0;

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }

        public Builder setSessionName(String str) {
            this.zzic = str;
            return this;
        }

        public Builder setSessionId(String str) {
            this.zzid = str;
            return this;
        }

        public Builder read(DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            if (!this.zzgm.contains(dataSource)) {
                this.zzgm.add(dataSource);
            }
            return this;
        }

        public Builder read(DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder readSessionsFromAllApps() {
            this.zzih = true;
            return this;
        }

        public Builder excludePackage(String str) {
            Preconditions.checkNotNull(str, "Attempting to use a null package name");
            if (!this.zzif.contains(str)) {
                this.zzif.add(str);
            }
            return this;
        }

        public Builder enableServerQueries() {
            this.zzgw = true;
            return this;
        }

        public SessionReadRequest build() {
            Preconditions.checkArgument(this.zzs > 0, "Invalid start time: %s", Long.valueOf(this.zzs));
            long j = this.zzt;
            Preconditions.checkArgument(j > 0 && j > this.zzs, "Invalid end time: %s", Long.valueOf(this.zzt));
            return new SessionReadRequest(this);
        }
    }

    @Constructor
    SessionReadRequest(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) long j, @Param(id = 4) long j2, @Param(id = 5) List<DataType> list, @Param(id = 6) List<DataSource> list2, @Param(id = 7) boolean z, @Param(id = 8) boolean z2, @Param(id = 9) List<String> list3, @Param(id = 10) IBinder iBinder) {
        this.zzic = str;
        this.zzid = str2;
        this.zzs = j;
        this.zzt = j2;
        this.zzah = list;
        this.zzgm = list2;
        this.zzie = z;
        this.zzgw = z2;
        this.zzif = list3;
        this.zzig = zzcl.zzh(iBinder);
    }

    private SessionReadRequest(Builder builder) {
        this(builder.zzic, builder.zzid, builder.zzs, builder.zzt, builder.zzah, builder.zzgm, builder.zzih, builder.zzgw, builder.zzif, (zzck) null);
    }

    public SessionReadRequest(SessionReadRequest sessionReadRequest, zzck zzck) {
        this(sessionReadRequest.zzic, sessionReadRequest.zzid, sessionReadRequest.zzs, sessionReadRequest.zzt, sessionReadRequest.zzah, sessionReadRequest.zzgm, sessionReadRequest.zzie, sessionReadRequest.zzgw, sessionReadRequest.zzif, zzck);
    }

    private SessionReadRequest(String str, String str2, long j, long j2, List<DataType> list, List<DataSource> list2, boolean z, boolean z2, List<String> list3, @Nullable zzck zzck) {
        this(str, str2, j, j2, list, list2, z, z2, list3, zzck == null ? null : zzck.asBinder());
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    @Nullable
    public String getSessionName() {
        return this.zzic;
    }

    @Nullable
    public String getSessionId() {
        return this.zzid;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public boolean includeSessionsFromAllApps() {
        return this.zzie;
    }

    public List<String> getExcludedPackages() {
        return this.zzif;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof SessionReadRequest) {
                SessionReadRequest sessionReadRequest = (SessionReadRequest) obj;
                if (Objects.equal(this.zzic, sessionReadRequest.zzic) && this.zzid.equals(sessionReadRequest.zzid) && this.zzs == sessionReadRequest.zzs && this.zzt == sessionReadRequest.zzt && Objects.equal(this.zzah, sessionReadRequest.zzah) && Objects.equal(this.zzgm, sessionReadRequest.zzgm) && this.zzie == sessionReadRequest.zzie && this.zzif.equals(sessionReadRequest.zzif) && this.zzgw == sessionReadRequest.zzgw) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzic, this.zzid, Long.valueOf(this.zzs), Long.valueOf(this.zzt));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("sessionName", this.zzic).add("sessionId", this.zzid).add("startTimeMillis", Long.valueOf(this.zzs)).add("endTimeMillis", Long.valueOf(this.zzt)).add("dataTypes", this.zzah).add("dataSources", this.zzgm).add("sessionsFromAllApps", Boolean.valueOf(this.zzie)).add("excludedPackages", this.zzif).add("useServer", Boolean.valueOf(this.zzgw)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getSessionName(), false);
        SafeParcelWriter.writeString(parcel, 2, getSessionId(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzs);
        SafeParcelWriter.writeLong(parcel, 4, this.zzt);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getDataSources(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, includeSessionsFromAllApps());
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzgw);
        SafeParcelWriter.writeStringList(parcel, 9, getExcludedPackages(), false);
        zzck zzck = this.zzig;
        SafeParcelWriter.writeIBinder(parcel, 10, zzck == null ? null : zzck.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
