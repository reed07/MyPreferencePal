package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.SystemClock;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public final class zzpf implements zzpk {
    /* access modifiers changed from: private */
    public IOException zzbci;
    /* access modifiers changed from: private */
    public final ExecutorService zzbgv;
    /* access modifiers changed from: private */
    public zzph<? extends zzpi> zzbgw;

    public zzpf(String str) {
        this.zzbgv = zzqe.zzah(str);
    }

    public final <T extends zzpi> long zza(T t, zzpg<T> zzpg, int i) {
        Looper myLooper = Looper.myLooper();
        zzpo.checkState(myLooper != null);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        zzph zzph = new zzph(this, myLooper, t, zzpg, i, elapsedRealtime);
        zzph.zzal(0);
        return elapsedRealtime;
    }

    public final boolean isLoading() {
        return this.zzbgw != null;
    }

    public final void zzgy() {
        this.zzbgw.zzj(false);
    }

    public final void zza(Runnable runnable) {
        zzph<? extends zzpi> zzph = this.zzbgw;
        if (zzph != null) {
            zzph.zzj(true);
        }
        if (runnable != null) {
            this.zzbgv.execute(runnable);
        }
        this.zzbgv.shutdown();
    }

    public final void zzev() throws IOException {
        IOException iOException = this.zzbci;
        if (iOException == null) {
            zzph<? extends zzpi> zzph = this.zzbgw;
            if (zzph != null) {
                zzph.zzbi(zzph.zzbgz);
                return;
            }
            return;
        }
        throw iOException;
    }

    public final void zzbi(int i) throws IOException {
        IOException iOException = this.zzbci;
        if (iOException == null) {
            zzph<? extends zzpi> zzph = this.zzbgw;
            if (zzph != null) {
                zzph.zzbi(zzph.zzbgz);
                return;
            }
            return;
        }
        throw iOException;
    }
}
