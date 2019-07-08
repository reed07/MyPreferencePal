package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
public abstract class zzasc implements zzasa, zzazb<Void> {
    private final Object mLock = new Object();
    private final zzbcn<zzasi> zzdvz;
    private final zzasa zzdwa;

    public zzasc(zzbcn<zzasi> zzbcn, zzasa zzasa) {
        this.zzdvz = zzbcn;
        this.zzdwa = zzasa;
    }

    public abstract void zzwi();

    public abstract zzasq zzwj();

    public final void zza(zzasm zzasm) {
        synchronized (this.mLock) {
            this.zzdwa.zza(zzasm);
            zzwi();
        }
    }

    public final void cancel() {
        zzwi();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final boolean zza(zzasq zzasq, zzasi zzasi) {
        try {
            zzasq.zza(zzasi, (zzast) new zzasl(this));
            return true;
        } catch (Throwable th) {
            zzaxz.zzc("Could not fetch ad response from ad request service due to an Exception.", th);
            zzbv.zzlj().zza(th, "AdRequestClientTask.getAdResponseFromService");
            this.zzdwa.zza(new zzasm(0));
            return false;
        }
    }

    public final /* synthetic */ Object zzwa() {
        zzasq zzwj = zzwj();
        if (zzwj == null) {
            this.zzdwa.zza(new zzasm(0));
            zzwi();
            return null;
        }
        this.zzdvz.zza(new zzasd(this, zzwj), new zzase(this));
        return null;
    }
}
