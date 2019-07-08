package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzalb implements Callable<zzakx> {
    private final /* synthetic */ zzaku zzdno;
    private final /* synthetic */ zzala zzdnp;

    zzalb(zzala zzala, zzaku zzaku) {
        this.zzdnp = zzala;
        this.zzdno = zzaku;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzus */
    public final zzakx call() throws Exception {
        synchronized (this.zzdnp.mLock) {
            if (this.zzdnp.zzdnk) {
                return null;
            }
            return this.zzdno.zzj(this.zzdnp.mStartTime, this.zzdnp.zzdni);
        }
    }
}
