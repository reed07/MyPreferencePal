package com.google.android.gms.internal.ads;

@zzark
public abstract class zzaxv implements zzazb<zzbcb> {
    /* access modifiers changed from: private */
    public volatile Thread zzeke;
    private boolean zzekf = false;
    private final Runnable zzy = new zzaxw(this);

    public zzaxv() {
    }

    public abstract void onStop();

    public abstract void zzki();

    public zzaxv(boolean z) {
    }

    public final zzbcb zzyz() {
        if (!this.zzekf) {
            return zzayf.zzc(this.zzy);
        }
        return zzayf.zzekz.zze(this.zzy);
    }

    public final void cancel() {
        onStop();
        if (this.zzeke != null) {
            this.zzeke.interrupt();
        }
    }

    public final /* synthetic */ Object zzwa() {
        if (!this.zzekf) {
            return zzayf.zzc(this.zzy);
        }
        return zzayf.zzekz.zze(this.zzy);
    }
}
