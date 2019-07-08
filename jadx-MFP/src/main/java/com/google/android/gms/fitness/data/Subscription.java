package com.google.android.gms.fitness.data;

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

@Class(creator = "SubscriptionCreator")
@Reserved({1000})
public class Subscription extends AbstractSafeParcelable {
    public static final Creator<Subscription> CREATOR = new zzah();
    @Field(getter = "getSamplingRateMicros", id = 3)
    private final long zzec;
    @Field(getter = "getAccuracyMode", id = 4)
    private final int zzed;
    @Field(getter = "getDataType", id = 2)
    private final DataType zzq;
    @Field(getter = "getDataSource", id = 1)
    private final DataSource zzr;

    public static class zza {
        /* access modifiers changed from: private */
        public long zzec = -1;
        /* access modifiers changed from: private */
        public int zzed = 2;
        /* access modifiers changed from: private */
        public DataType zzq;
        /* access modifiers changed from: private */
        public DataSource zzr;

        public final zza zza(DataSource dataSource) {
            this.zzr = dataSource;
            return this;
        }

        public final zza zza(DataType dataType) {
            this.zzq = dataType;
            return this;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
            if (r0.equals(r3.getDataType()) == false) goto L_0x0026;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.google.android.gms.fitness.data.Subscription zzr() {
            /*
                r4 = this;
                com.google.android.gms.fitness.data.DataSource r0 = r4.zzr
                r1 = 0
                r2 = 1
                if (r0 != 0) goto L_0x000d
                com.google.android.gms.fitness.data.DataType r0 = r4.zzq
                if (r0 == 0) goto L_0x000b
                goto L_0x000d
            L_0x000b:
                r0 = 0
                goto L_0x000e
            L_0x000d:
                r0 = 1
            L_0x000e:
                java.lang.String r3 = "Must call setDataSource() or setDataType()"
                com.google.android.gms.common.internal.Preconditions.checkState(r0, r3)
                com.google.android.gms.fitness.data.DataType r0 = r4.zzq
                if (r0 == 0) goto L_0x0025
                com.google.android.gms.fitness.data.DataSource r3 = r4.zzr
                if (r3 == 0) goto L_0x0025
                com.google.android.gms.fitness.data.DataType r3 = r3.getDataType()
                boolean r0 = r0.equals(r3)
                if (r0 == 0) goto L_0x0026
            L_0x0025:
                r1 = 1
            L_0x0026:
                java.lang.String r0 = "Specified data type is incompatible with specified data source"
                com.google.android.gms.common.internal.Preconditions.checkState(r1, r0)
                com.google.android.gms.fitness.data.Subscription r0 = new com.google.android.gms.fitness.data.Subscription
                r1 = 0
                r0.<init>(r4)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.Subscription.zza.zzr():com.google.android.gms.fitness.data.Subscription");
        }
    }

    @Constructor
    Subscription(@Param(id = 1) DataSource dataSource, @Param(id = 2) DataType dataType, @Param(id = 3) long j, @Param(id = 4) int i) {
        this.zzr = dataSource;
        this.zzq = dataType;
        this.zzec = j;
        this.zzed = i;
    }

    private Subscription(zza zza2) {
        this.zzq = zza2.zzq;
        this.zzr = zza2.zzr;
        this.zzec = zza2.zzec;
        this.zzed = zza2.zzed;
    }

    @Nullable
    public DataSource getDataSource() {
        return this.zzr;
    }

    @Nullable
    public DataType getDataType() {
        return this.zzq;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return Objects.equal(this.zzr, subscription.zzr) && Objects.equal(this.zzq, subscription.zzq) && this.zzec == subscription.zzec && this.zzed == subscription.zzed;
    }

    public int hashCode() {
        DataSource dataSource = this.zzr;
        return Objects.hashCode(dataSource, dataSource, Long.valueOf(this.zzec), Integer.valueOf(this.zzed));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zzr).add("dataType", this.zzq).add("samplingIntervalMicros", Long.valueOf(this.zzec)).add("accuracyMode", Integer.valueOf(this.zzed)).toString();
    }

    public String toDebugString() {
        String str = "Subscription{%s}";
        Object[] objArr = new Object[1];
        DataSource dataSource = this.zzr;
        objArr[0] = dataSource == null ? this.zzq.getName() : dataSource.toDebugString();
        return String.format(str, objArr);
    }

    public final DataType zzq() {
        DataType dataType = this.zzq;
        return dataType == null ? this.zzr.getDataType() : dataType;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getDataType(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzec);
        SafeParcelWriter.writeInt(parcel, 4, this.zzed);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
