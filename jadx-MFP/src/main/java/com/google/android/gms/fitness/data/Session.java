package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzfa;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import java.util.concurrent.TimeUnit;

@Class(creator = "SessionCreator")
@Reserved({1000})
public class Session extends AbstractSafeParcelable {
    public static final Creator<Session> CREATOR = new zzad();
    public static final String EXTRA_SESSION = "vnd.google.fitness.session";
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.session/";
    @Field(getter = "getDescription", id = 5)
    private final String description;
    @Nullable
    @Field(getter = "getName", id = 3)
    private final String name;
    @Field(getter = "getActivityType", id = 7)
    private final int zzai;
    @Field(getter = "getApplication", id = 8)
    private final zzb zzay;
    @Field(getter = "getIdentifier", id = 4)
    private final String zzdz;
    @Nullable
    @Field(getter = "getActiveTimeMillis", id = 9)
    private final Long zzea;
    @Field(getter = "getStartTimeMillis", id = 1)
    private final long zzs;
    @Field(getter = "getEndTimeMillis", id = 2)
    private final long zzt;

    public static class Builder {
        /* access modifiers changed from: private */
        public String description = "";
        /* access modifiers changed from: private */
        @Nullable
        public String name = null;
        /* access modifiers changed from: private */
        public int zzai = 4;
        /* access modifiers changed from: private */
        @Nullable
        public String zzdz = null;
        /* access modifiers changed from: private */
        @Nullable
        public Long zzea;
        /* access modifiers changed from: private */
        public long zzs = 0;
        /* access modifiers changed from: private */
        public long zzt = 0;

        public Builder setStartTime(long j, TimeUnit timeUnit) {
            Preconditions.checkState(j > 0, "Start time should be positive.");
            this.zzs = timeUnit.toMillis(j);
            return this;
        }

        public Builder setEndTime(long j, TimeUnit timeUnit) {
            Preconditions.checkState(j >= 0, "End time should be positive.");
            this.zzt = timeUnit.toMillis(j);
            return this;
        }

        public Builder setName(String str) {
            Preconditions.checkArgument(str.length() <= 100, "Session name cannot exceed %d characters", Integer.valueOf(100));
            this.name = str;
            return this;
        }

        public Builder setIdentifier(String str) {
            Preconditions.checkArgument(str != null && TextUtils.getTrimmedLength(str) > 0);
            this.zzdz = str;
            return this;
        }

        public Builder setDescription(String str) {
            Preconditions.checkArgument(str.length() <= 1000, "Session description cannot exceed %d characters", Integer.valueOf(1000));
            this.description = str;
            return this;
        }

        public Builder setActivity(String str) {
            this.zzai = zzfa.zzl(str);
            return this;
        }

        public Builder setActiveTime(long j, TimeUnit timeUnit) {
            this.zzea = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        public Session build() {
            boolean z = true;
            Preconditions.checkState(this.zzs > 0, "Start time should be specified.");
            long j = this.zzt;
            if (j != 0 && j <= this.zzs) {
                z = false;
            }
            Preconditions.checkState(z, "End time should be later than start time.");
            if (this.zzdz == null) {
                String str = this.name;
                if (str == null) {
                    str = "";
                }
                long j2 = this.zzs;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20);
                sb.append(str);
                sb.append(j2);
                this.zzdz = sb.toString();
            }
            return new Session(this);
        }
    }

    @Constructor
    public Session(@Param(id = 1) long j, @Param(id = 2) long j2, @Nullable @Param(id = 3) String str, @Param(id = 4) String str2, @Param(id = 5) String str3, @Param(id = 7) int i, @Param(id = 8) zzb zzb, @Nullable @Param(id = 9) Long l) {
        this.zzs = j;
        this.zzt = j2;
        this.name = str;
        this.zzdz = str2;
        this.description = str3;
        this.zzai = i;
        this.zzay = zzb;
        this.zzea = l;
    }

    private Session(Builder builder) {
        this(builder.zzs, builder.zzt, builder.name, builder.zzdz, builder.description, builder.zzai, null, builder.zzea);
    }

    @Nullable
    public static Session extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (Session) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_SESSION, CREATOR);
    }

    public static String getMimeType(String str) {
        String valueOf = String.valueOf(MIME_TYPE_PREFIX);
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public long getActiveTime(TimeUnit timeUnit) {
        Preconditions.checkState(this.zzea != null, "Active time is not set");
        return timeUnit.convert(this.zzea.longValue(), TimeUnit.MILLISECONDS);
    }

    public boolean hasActiveTime() {
        return this.zzea != null;
    }

    public boolean isOngoing() {
        return this.zzt == 0;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    public String getIdentifier() {
        return this.zzdz;
    }

    public String getDescription() {
        return this.description;
    }

    public String getActivity() {
        return zzfa.getName(this.zzai);
    }

    @Nullable
    public String getAppPackageName() {
        zzb zzb = this.zzay;
        if (zzb == null) {
            return null;
        }
        return zzb.getPackageName();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Session)) {
            return false;
        }
        Session session = (Session) obj;
        return this.zzs == session.zzs && this.zzt == session.zzt && Objects.equal(this.name, session.name) && Objects.equal(this.zzdz, session.zzdz) && Objects.equal(this.description, session.description) && Objects.equal(this.zzay, session.zzay) && this.zzai == session.zzai;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt), this.zzdz);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzs)).add(AbstractEvent.END_TIME, Long.valueOf(this.zzt)).add("name", this.name).add(Columns.IDENTIFIER, this.zzdz).add("description", this.description).add(AbstractEvent.ACTIVITY, Integer.valueOf(this.zzai)).add("application", this.zzay).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getIdentifier(), false);
        SafeParcelWriter.writeString(parcel, 5, getDescription(), false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzai);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzay, i, false);
        SafeParcelWriter.writeLongObject(parcel, 9, this.zzea, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
