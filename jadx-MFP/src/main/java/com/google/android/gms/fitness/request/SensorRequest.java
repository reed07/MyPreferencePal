package com.google.android.gms.fitness.request;

import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.concurrent.TimeUnit;

public class SensorRequest {
    public static final int ACCURACY_MODE_DEFAULT = 2;
    public static final int ACCURACY_MODE_HIGH = 3;
    public static final int ACCURACY_MODE_LOW = 1;
    private final long zzec;
    private final int zzed;
    private final long zzhs;
    private final long zzht;
    private final long zzhx;
    @Nullable
    private final DataType zzq;
    @Nullable
    private final DataSource zzr;

    public static class Builder {
        /* access modifiers changed from: private */
        public long zzec = -1;
        /* access modifiers changed from: private */
        public int zzed = 2;
        /* access modifiers changed from: private */
        public long zzhs = 0;
        /* access modifiers changed from: private */
        public long zzht = 0;
        /* access modifiers changed from: private */
        public long zzhx = Long.MAX_VALUE;
        private boolean zzhy = false;
        /* access modifiers changed from: private */
        public DataType zzq;
        /* access modifiers changed from: private */
        public DataSource zzr;

        public Builder setDataSource(DataSource dataSource) {
            this.zzr = dataSource;
            return this;
        }

        public Builder setDataType(DataType dataType) {
            this.zzq = dataType;
            return this;
        }

        public Builder setSamplingRate(long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 0, "Cannot use a negative sampling interval");
            this.zzec = timeUnit.toMicros(j);
            if (!this.zzhy) {
                this.zzht = this.zzec / 2;
            }
            return this;
        }

        public Builder setFastestRate(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative interval");
            this.zzhy = true;
            this.zzht = timeUnit.toMicros((long) i);
            return this;
        }

        public Builder setMaxDeliveryLatency(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative delivery interval");
            this.zzhs = timeUnit.toMicros((long) i);
            return this;
        }

        public Builder setAccuracyMode(int i) {
            if (!(i == 1 || i == 3)) {
                i = 2;
            }
            this.zzed = i;
            return this;
        }

        public Builder setTimeout(long j, TimeUnit timeUnit) {
            boolean z = true;
            Preconditions.checkArgument(j > 0, "Invalid time out value specified: %d", Long.valueOf(j));
            if (timeUnit == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "Invalid time unit specified");
            this.zzhx = timeUnit.toMicros(j);
            return this;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
            if (r0.equals(r3.getDataType()) == false) goto L_0x0026;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.android.gms.fitness.request.SensorRequest build() {
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
                com.google.android.gms.fitness.request.SensorRequest r0 = new com.google.android.gms.fitness.request.SensorRequest
                r1 = 0
                r0.<init>(r4)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.SensorRequest.Builder.build():com.google.android.gms.fitness.request.SensorRequest");
        }
    }

    private SensorRequest(Builder builder) {
        this.zzr = builder.zzr;
        this.zzq = builder.zzq;
        this.zzec = builder.zzec;
        this.zzht = builder.zzht;
        this.zzhs = builder.zzhs;
        this.zzed = builder.zzed;
        this.zzhx = builder.zzhx;
    }

    private SensorRequest(DataSource dataSource, LocationRequest locationRequest) {
        this.zzec = TimeUnit.MILLISECONDS.toMicros(locationRequest.getInterval());
        this.zzht = TimeUnit.MILLISECONDS.toMicros(locationRequest.getFastestInterval());
        this.zzhs = this.zzec;
        this.zzq = dataSource.getDataType();
        int priority = locationRequest.getPriority();
        int i = priority != 100 ? priority != 104 ? 2 : 1 : 3;
        this.zzed = i;
        this.zzr = dataSource;
        long expirationTime = locationRequest.getExpirationTime();
        if (expirationTime == Long.MAX_VALUE) {
            this.zzhx = Long.MAX_VALUE;
        } else {
            this.zzhx = TimeUnit.MILLISECONDS.toMicros(expirationTime - SystemClock.elapsedRealtime());
        }
    }

    @Deprecated
    public static SensorRequest fromLocationRequest(DataSource dataSource, LocationRequest locationRequest) {
        return new SensorRequest(dataSource, locationRequest);
    }

    @Nullable
    public DataSource getDataSource() {
        return this.zzr;
    }

    @Nullable
    public DataType getDataType() {
        return this.zzq;
    }

    public long getSamplingRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzec, TimeUnit.MICROSECONDS);
    }

    public long getFastestRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzht, TimeUnit.MICROSECONDS);
    }

    public long getMaxDeliveryLatency(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhs, TimeUnit.MICROSECONDS);
    }

    public int getAccuracyMode() {
        return this.zzed;
    }

    public final long zzx() {
        return this.zzhx;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zzr).add("dataType", this.zzq).add("samplingRateMicros", Long.valueOf(this.zzec)).add("deliveryLatencyMicros", Long.valueOf(this.zzhs)).add("timeOutMicros", Long.valueOf(this.zzhx)).toString();
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof SensorRequest) {
                SensorRequest sensorRequest = (SensorRequest) obj;
                if (Objects.equal(this.zzr, sensorRequest.zzr) && Objects.equal(this.zzq, sensorRequest.zzq) && this.zzec == sensorRequest.zzec && this.zzht == sensorRequest.zzht && this.zzhs == sensorRequest.zzhs && this.zzed == sensorRequest.zzed && this.zzhx == sensorRequest.zzhx) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzr, this.zzq, Long.valueOf(this.zzec), Long.valueOf(this.zzht), Long.valueOf(this.zzhs), Integer.valueOf(this.zzed), Long.valueOf(this.zzhx));
    }
}
