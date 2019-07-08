package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzeg extends zzeu {
    private static final Object zzum = new Object();
    private static volatile Long zzuo;

    public zzeg(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 22);
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        if (zzuo == null) {
            synchronized (zzum) {
                if (zzuo == null) {
                    zzuo = (Long) this.zzuw.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzun) {
            this.zzun.zzep = zzuo;
        }
    }
}
