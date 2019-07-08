package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzbai {
    private Object mLock = new Object();
    private long zzeno;
    @GuardedBy("mLock")
    private long zzenp = Long.MIN_VALUE;

    public zzbai(long j) {
        this.zzeno = j;
    }

    public final boolean tryAcquire() {
        synchronized (this.mLock) {
            long elapsedRealtime = zzbv.zzlm().elapsedRealtime();
            if (this.zzenp + this.zzeno > elapsedRealtime) {
                return false;
            }
            this.zzenp = elapsedRealtime;
            return true;
        }
    }
}
