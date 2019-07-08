package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class zzee extends zzeu {
    public zzee(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 24);
    }

    public final Void zzau() throws Exception {
        if (this.zzqo.isInitialized()) {
            return super.call();
        }
        if (this.zzqo.zzag()) {
            zzav();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        if (this.zzqo.zzag()) {
            zzav();
            return;
        }
        synchronized (this.zzun) {
            this.zzun.zzge = (String) this.zzuw.invoke(null, new Object[]{this.zzqo.getContext()});
        }
    }

    private final void zzav() {
        AdvertisingIdClient zzao = this.zzqo.zzao();
        if (zzao != null) {
            try {
                Info info = zzao.getInfo();
                String zzn = zzds.zzn(info.getId());
                if (zzn != null) {
                    synchronized (this.zzun) {
                        this.zzun.zzge = zzn;
                        this.zzun.zzgg = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                        this.zzun.zzgf = Integer.valueOf(5);
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    public final /* synthetic */ Object call() throws Exception {
        return call();
    }
}
