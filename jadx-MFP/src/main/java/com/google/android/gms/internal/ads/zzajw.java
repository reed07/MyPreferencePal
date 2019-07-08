package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzark
@ParametersAreNonnullByDefault
public final class zzajw {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private zzakd zzdka;

    public final zzakd zzb(Context context, zzbbi zzbbi) {
        zzakd zzakd;
        synchronized (this.mLock) {
            if (this.zzdka == null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
                this.zzdka = new zzakd(context, zzbbi, (String) zzwu.zzpz().zzd(zzaan.zzcnw));
            }
            zzakd = this.zzdka;
        }
        return zzakd;
    }
}
