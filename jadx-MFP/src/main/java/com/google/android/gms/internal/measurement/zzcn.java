package com.google.android.gms.internal.measurement;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzcn {
    private final long zzabb;
    private final int zzabc;
    private double zzabd;
    private long zzabe;
    private final Object zzabf;
    private final Clock zzrz;
    private final String zzul;

    private zzcn(int i, long j, String str, Clock clock) {
        this.zzabf = new Object();
        this.zzabc = 60;
        this.zzabd = (double) this.zzabc;
        this.zzabb = AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS;
        this.zzul = str;
        this.zzrz = clock;
    }

    public zzcn(String str, Clock clock) {
        this(60, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, str, clock);
    }

    public final boolean zzew() {
        synchronized (this.zzabf) {
            long currentTimeMillis = this.zzrz.currentTimeMillis();
            if (this.zzabd < ((double) this.zzabc)) {
                double d = ((double) (currentTimeMillis - this.zzabe)) / ((double) this.zzabb);
                if (d > 0.0d) {
                    this.zzabd = Math.min((double) this.zzabc, this.zzabd + d);
                }
            }
            this.zzabe = currentTimeMillis;
            if (this.zzabd >= 1.0d) {
                this.zzabd -= 1.0d;
                return true;
            }
            String str = this.zzul;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
            sb.append("Excessive ");
            sb.append(str);
            sb.append(" detected; call ignored.");
            zzco.zzab(sb.toString());
            return false;
        }
    }
}
