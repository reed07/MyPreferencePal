package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzei extends zzeu {
    private static volatile String zzdw;
    private static final Object zzum = new Object();

    public zzei(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 1);
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        this.zzun.zzdw = "E";
        if (zzdw == null) {
            synchronized (zzum) {
                if (zzdw == null) {
                    zzdw = (String) this.zzuw.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzun) {
            this.zzun.zzdw = zzdw;
        }
    }
}
