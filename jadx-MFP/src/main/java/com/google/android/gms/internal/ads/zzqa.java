package com.google.android.gms.internal.ads;

import android.os.SystemClock;

public final class zzqa implements zzps {
    private boolean started;
    private long zzbia;
    private long zzbib;
    private zzfy zzxm = zzfy.zzaaf;

    public final void start() {
        if (!this.started) {
            this.zzbib = SystemClock.elapsedRealtime();
            this.started = true;
        }
    }

    public final void stop() {
        if (this.started) {
            zzam(zzde());
            this.started = false;
        }
    }

    public final void zzam(long j) {
        this.zzbia = j;
        if (this.started) {
            this.zzbib = SystemClock.elapsedRealtime();
        }
    }

    public final void zza(zzps zzps) {
        zzam(zzps.zzde());
        this.zzxm = zzps.zzcx();
    }

    public final long zzde() {
        long j = this.zzbia;
        if (!this.started) {
            return j;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.zzbib;
        if (this.zzxm.zzaag == 1.0f) {
            return j + zzfe.zzg(elapsedRealtime);
        }
        return j + this.zzxm.zzl(elapsedRealtime);
    }

    public final zzfy zzb(zzfy zzfy) {
        if (this.started) {
            zzam(zzde());
        }
        this.zzxm = zzfy;
        return zzfy;
    }

    public final zzfy zzcx() {
        return this.zzxm;
    }
}
