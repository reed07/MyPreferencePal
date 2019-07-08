package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

final class zzfl implements zzej {
    private final long zzabb;
    private final int zzabc;
    private double zzabd;
    private final Object zzabf;
    private long zzbfy;
    private final Clock zzrz;

    private zzfl(int i, long j) {
        this.zzabf = new Object();
        this.zzabc = 60;
        this.zzabd = (double) this.zzabc;
        this.zzabb = AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS;
        this.zzrz = DefaultClock.getInstance();
    }

    public zzfl() {
        this(60, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    public final boolean zzew() {
        synchronized (this.zzabf) {
            long currentTimeMillis = this.zzrz.currentTimeMillis();
            if (this.zzabd < ((double) this.zzabc)) {
                double d = ((double) (currentTimeMillis - this.zzbfy)) / ((double) this.zzabb);
                if (d > 0.0d) {
                    this.zzabd = Math.min((double) this.zzabc, this.zzabd + d);
                }
            }
            this.zzbfy = currentTimeMillis;
            if (this.zzabd >= 1.0d) {
                this.zzabd -= 1.0d;
                return true;
            }
            zzdi.zzab("No more tokens available.");
            return false;
        }
    }
}
