package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

public final class zzef implements Callable {
    private final zzdl zzqo;
    private final zzbl zzun;

    public zzef(zzdl zzdl, zzbl zzbl) {
        this.zzqo = zzdl;
        this.zzun = zzbl;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzau */
    public final Void call() throws Exception {
        if (this.zzqo.zzal() != null) {
            this.zzqo.zzal().get();
        }
        zzbl zzak = this.zzqo.zzak();
        if (zzak != null) {
            try {
                synchronized (this.zzun) {
                    zzbuz.zza(this.zzun, zzbuz.zzb(zzak));
                }
            } catch (zzbuy unused) {
            }
        }
        return null;
    }
}
