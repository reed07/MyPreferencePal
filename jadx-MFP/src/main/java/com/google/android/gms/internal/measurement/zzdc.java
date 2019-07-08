package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

final class zzdc {
    private long startTime;
    private final Clock zzrz;

    public zzdc(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.zzrz = clock;
    }

    public zzdc(Clock clock, long j) {
        Preconditions.checkNotNull(clock);
        this.zzrz = clock;
        this.startTime = j;
    }

    public final void start() {
        this.startTime = this.zzrz.elapsedRealtime();
    }

    public final void clear() {
        this.startTime = 0;
    }

    public final boolean zzj(long j) {
        if (this.startTime != 0 && this.zzrz.elapsedRealtime() - this.startTime <= j) {
            return false;
        }
        return true;
    }
}
