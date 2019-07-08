package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzeo extends zzeu {
    private final zzdu zzrt;
    private long zzue;

    public zzeo(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2, zzdu zzdu) {
        super(zzdl, str, str2, zzbl, i, 53);
        this.zzrt = zzdu;
        if (zzdu != null) {
            this.zzue = zzdu.zzaq();
        }
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        if (this.zzrt != null) {
            this.zzun.zzfj = (Long) this.zzuw.invoke(null, new Object[]{Long.valueOf(this.zzue)});
        }
    }
}
