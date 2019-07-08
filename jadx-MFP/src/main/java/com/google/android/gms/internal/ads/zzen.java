package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzen extends zzeu {
    public zzen(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 51);
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzun) {
            zzdk zzdk = new zzdk((String) this.zzuw.invoke(null, new Object[0]));
            this.zzun.zzfh = zzdk.zzsn;
            this.zzun.zzfi = zzdk.zzso;
        }
    }
}
