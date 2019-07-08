package com.google.android.gms.internal.measurement;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

abstract class zzbz {
    private static volatile Handler handler;
    /* access modifiers changed from: private */
    public final zzaw zzvy;
    private final Runnable zzyo = new zzca(this);
    /* access modifiers changed from: private */
    public volatile long zzyp;

    zzbz(zzaw zzaw) {
        Preconditions.checkNotNull(zzaw);
        this.zzvy = zzaw;
    }

    public abstract void run();

    public final void zzh(long j) {
        cancel();
        if (j >= 0) {
            this.zzyp = this.zzvy.zzbx().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzyo, j)) {
                this.zzvy.zzby().zze("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final void zzi(long j) {
        if (zzej()) {
            if (j < 0) {
                cancel();
                return;
            }
            long abs = j - Math.abs(this.zzvy.zzbx().currentTimeMillis() - this.zzyp);
            if (abs < 0) {
                abs = 0;
            }
            getHandler().removeCallbacks(this.zzyo);
            if (!getHandler().postDelayed(this.zzyo, abs)) {
                this.zzvy.zzby().zze("Failed to adjust delayed post. time", Long.valueOf(abs));
            }
        }
    }

    public final long zzei() {
        if (this.zzyp == 0) {
            return 0;
        }
        return Math.abs(this.zzvy.zzbx().currentTimeMillis() - this.zzyp);
    }

    public final boolean zzej() {
        return this.zzyp != 0;
    }

    public final void cancel() {
        this.zzyp = 0;
        getHandler().removeCallbacks(this.zzyo);
    }

    private final Handler getHandler() {
        Handler handler2;
        if (handler != null) {
            return handler;
        }
        synchronized (zzbz.class) {
            if (handler == null) {
                handler = new zzdl(this.zzvy.getContext().getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }
}
