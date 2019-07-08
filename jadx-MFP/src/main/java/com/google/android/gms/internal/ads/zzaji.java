package com.google.android.gms.internal.ads;

public final class zzaji extends zzbcr<zzajr> {
    private final Object mLock = new Object();
    /* access modifiers changed from: private */
    public final zzajm zzdjp;
    private boolean zzdjq;

    public zzaji(zzajm zzajm) {
        this.zzdjp = zzajm;
    }

    public final void release() {
        synchronized (this.mLock) {
            if (!this.zzdjq) {
                this.zzdjq = true;
                zza(new zzajj(this), new zzbcp());
                zza(new zzajk(this), new zzajl(this));
            }
        }
    }
}
