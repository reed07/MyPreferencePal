package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.fitness.zzfa;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Class(creator = "GoalCreator")
@Reserved({1000})
public class Goal extends AbstractSafeParcelable {
    public static final Creator<Goal> CREATOR = new zzs();
    public static final int OBJECTIVE_TYPE_DURATION = 2;
    public static final int OBJECTIVE_TYPE_FREQUENCY = 3;
    public static final int OBJECTIVE_TYPE_METRIC = 1;
    @Field(getter = "getCreateTimeNanos", id = 1)
    private final long zzdj;
    @Field(getter = "getExpireTimeNanos", id = 2)
    private final long zzdk;
    @Field(getter = "getActivities", id = 3, type = "java.util.List")
    private final List<Integer> zzdl;
    @Field(getter = "getRecurrence", id = 4)
    private final Recurrence zzdm;
    @Field(getter = "getObjectiveType", id = 5)
    private final int zzdn;
    @Field(getter = "getMetricObjectiveWithOutChecking", id = 6)
    private final MetricObjective zzdo;
    @Field(getter = "getDurationObjectiveWithOutChecking", id = 7)
    private final DurationObjective zzdp;
    @Field(getter = "getFrequencyObjectiveWithOutChecking", id = 8)
    private final FrequencyObjective zzdq;

    @Class(creator = "DurationObjectiveCreator")
    @Reserved({1000})
    public static class DurationObjective extends AbstractSafeParcelable {
        public static final Creator<DurationObjective> CREATOR = new zzp();
        @Field(getter = "getDuration", id = 1)
        private final long zzdr;

        public long getDuration(TimeUnit timeUnit) {
            return timeUnit.convert(this.zzdr, TimeUnit.NANOSECONDS);
        }

        @Constructor
        DurationObjective(@Param(id = 1) long j) {
            this.zzdr = j;
        }

        public DurationObjective(long j, TimeUnit timeUnit) {
            this(timeUnit.toNanos(j));
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DurationObjective)) {
                return false;
            }
            return this.zzdr == ((DurationObjective) obj).zzdr;
        }

        public int hashCode() {
            return (int) this.zzdr;
        }

        public String toString() {
            return Objects.toStringHelper(this).add("duration", Long.valueOf(this.zzdr)).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeLong(parcel, 1, this.zzdr);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "FrequencyObjectiveCreator")
    @Reserved({1000})
    public static class FrequencyObjective extends AbstractSafeParcelable {
        public static final Creator<FrequencyObjective> CREATOR = new zzr();
        @Field(getter = "getFrequency", id = 1)
        private final int frequency;

        public int getFrequency() {
            return this.frequency;
        }

        @Constructor
        public FrequencyObjective(@Param(id = 1) int i) {
            this.frequency = i;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FrequencyObjective)) {
                return false;
            }
            return this.frequency == ((FrequencyObjective) obj).frequency;
        }

        public int hashCode() {
            return this.frequency;
        }

        public String toString() {
            return Objects.toStringHelper(this).add("frequency", Integer.valueOf(this.frequency)).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getFrequency());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "MetricObjectiveCreator")
    @Reserved({1000})
    public static class MetricObjective extends AbstractSafeParcelable {
        public static final Creator<MetricObjective> CREATOR = new zzx();
        @Field(getter = "getValue", id = 2)
        private final double value;
        @Field(getter = "getDataTypeName", id = 1)
        private final String zzds;
        @Field(getter = "getInitialValue", id = 3)
        private final double zzdt;

        public String getDataTypeName() {
            return this.zzds;
        }

        public double getValue() {
            return this.value;
        }

        @Constructor
        public MetricObjective(@Param(id = 1) String str, @Param(id = 2) double d, @Param(id = 3) double d2) {
            this.zzds = str;
            this.value = d;
            this.zzdt = d2;
        }

        public MetricObjective(String str, double d) {
            this(str, d, 0.0d);
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MetricObjective)) {
                return false;
            }
            MetricObjective metricObjective = (MetricObjective) obj;
            return Objects.equal(this.zzds, metricObjective.zzds) && this.value == metricObjective.value && this.zzdt == metricObjective.zzdt;
        }

        public int hashCode() {
            return this.zzds.hashCode();
        }

        public String toString() {
            return Objects.toStringHelper(this).add("dataTypeName", this.zzds).add("value", Double.valueOf(this.value)).add("initialValue", Double.valueOf(this.zzdt)).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, getDataTypeName(), false);
            SafeParcelWriter.writeDouble(parcel, 2, getValue());
            SafeParcelWriter.writeDouble(parcel, 3, this.zzdt);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static class MismatchedGoalException extends IllegalStateException {
        public MismatchedGoalException(String str) {
            super(str);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ObjectiveType {
    }

    @Class(creator = "RecurrenceCreator")
    @Reserved({1000})
    public static class Recurrence extends AbstractSafeParcelable {
        public static final Creator<Recurrence> CREATOR = new zzab();
        public static final int UNIT_DAY = 1;
        public static final int UNIT_MONTH = 3;
        public static final int UNIT_WEEK = 2;
        @Field(getter = "getCount", id = 1)
        private final int count;
        /* access modifiers changed from: private */
        @Field(getter = "getUnit", id = 2)
        public final int zzdu;

        @Retention(RetentionPolicy.SOURCE)
        public @interface RecurrenceUnit {
        }

        public int getCount() {
            return this.count;
        }

        public int getUnit() {
            return this.zzdu;
        }

        @Constructor
        public Recurrence(@Param(id = 1) int i, @Param(id = 2) int i2) {
            this.count = i;
            Preconditions.checkState(i2 > 0 && i2 <= 3);
            this.zzdu = i2;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Recurrence)) {
                return false;
            }
            Recurrence recurrence = (Recurrence) obj;
            return this.count == recurrence.count && this.zzdu == recurrence.zzdu;
        }

        public int hashCode() {
            return this.zzdu;
        }

        public String toString() {
            String str;
            ToStringHelper add = Objects.toStringHelper(this).add("count", Integer.valueOf(this.count));
            String str2 = "unit";
            switch (this.zzdu) {
                case 1:
                    str = "day";
                    break;
                case 2:
                    str = "week";
                    break;
                case 3:
                    str = Extras.MONTH;
                    break;
                default:
                    throw new IllegalArgumentException("invalid unit value");
            }
            return add.add(str2, str).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getCount());
            SafeParcelWriter.writeInt(parcel, 2, getUnit());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public long getCreateTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzdj, TimeUnit.NANOSECONDS);
    }

    public long getStartTime(Calendar calendar, TimeUnit timeUnit) {
        if (this.zzdm == null) {
            return timeUnit.convert(this.zzdj, TimeUnit.NANOSECONDS);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(calendar.getTime());
        switch (this.zzdm.zzdu) {
            case 1:
                instance.set(11, 0);
                return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
            case 2:
                instance.set(7, 2);
                instance.set(11, 0);
                return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
            case 3:
                instance.set(5, 1);
                instance.set(11, 0);
                return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
            default:
                int zza = this.zzdm.zzdu;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Invalid unit ");
                sb.append(zza);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public long getEndTime(Calendar calendar, TimeUnit timeUnit) {
        if (this.zzdm == null) {
            return timeUnit.convert(this.zzdk, TimeUnit.NANOSECONDS);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(calendar.getTime());
        switch (this.zzdm.zzdu) {
            case 1:
                instance.add(5, 1);
                instance.set(11, 0);
                return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
            case 2:
                instance.add(4, 1);
                instance.set(7, 2);
                instance.set(11, 0);
                return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
            case 3:
                instance.add(2, 1);
                instance.set(5, 1);
                instance.set(11, 0);
                return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
            default:
                int zza = this.zzdm.zzdu;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Invalid unit ");
                sb.append(zza);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    @Nullable
    public String getActivityName() {
        if (this.zzdl.isEmpty() || this.zzdl.size() > 1) {
            return null;
        }
        return zzfa.getName(((Integer) this.zzdl.get(0)).intValue());
    }

    @Nullable
    public Recurrence getRecurrence() {
        return this.zzdm;
    }

    public int getObjectiveType() {
        return this.zzdn;
    }

    private static String zze(int i) {
        switch (i) {
            case 0:
                return "unknown";
            case 1:
                return "metric";
            case 2:
                return "duration";
            case 3:
                return "frequency";
            default:
                throw new IllegalArgumentException("invalid objective type value");
        }
    }

    public MetricObjective getMetricObjective() {
        zzf(1);
        return this.zzdo;
    }

    public DurationObjective getDurationObjective() {
        zzf(2);
        return this.zzdp;
    }

    public FrequencyObjective getFrequencyObjective() {
        zzf(3);
        return this.zzdq;
    }

    @Constructor
    Goal(@Param(id = 1) long j, @Param(id = 2) long j2, @Param(id = 3) List<Integer> list, @Param(id = 4) Recurrence recurrence, @Param(id = 5) int i, @Param(id = 6) MetricObjective metricObjective, @Param(id = 7) DurationObjective durationObjective, @Param(id = 8) FrequencyObjective frequencyObjective) {
        this.zzdj = j;
        this.zzdk = j2;
        this.zzdl = list;
        this.zzdm = recurrence;
        this.zzdn = i;
        this.zzdo = metricObjective;
        this.zzdp = durationObjective;
        this.zzdq = frequencyObjective;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Goal)) {
            return false;
        }
        Goal goal = (Goal) obj;
        return this.zzdj == goal.zzdj && this.zzdk == goal.zzdk && Objects.equal(this.zzdl, goal.zzdl) && Objects.equal(this.zzdm, goal.zzdm) && this.zzdn == goal.zzdn && Objects.equal(this.zzdo, goal.zzdo) && Objects.equal(this.zzdp, goal.zzdp) && Objects.equal(this.zzdq, goal.zzdq);
    }

    public int hashCode() {
        return this.zzdn;
    }

    public String toString() {
        return Objects.toStringHelper(this).add(AbstractEvent.ACTIVITY, getActivityName()).add("recurrence", this.zzdm).add("metricObjective", this.zzdo).add("durationObjective", this.zzdp).add("frequencyObjective", this.zzdq).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzdj);
        SafeParcelWriter.writeLong(parcel, 2, this.zzdk);
        SafeParcelWriter.writeList(parcel, 3, this.zzdl, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getRecurrence(), i, false);
        SafeParcelWriter.writeInt(parcel, 5, getObjectiveType());
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzdo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzdp, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzdq, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private final void zzf(int i) throws MismatchedGoalException {
        int i2 = this.zzdn;
        if (i != i2) {
            throw new MismatchedGoalException(String.format("%s goal does not have %s objective", new Object[]{zze(i2), zze(i)}));
        }
    }
}
