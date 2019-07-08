package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzep extends zzeu {
    private static final Object zzum = new Object();
    private static volatile Long zzut;

    public zzep(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 33);
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        if (zzut == null) {
            synchronized (zzum) {
                if (zzut == null) {
                    zzut = (Long) this.zzuw.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzun) {
            this.zzun.zzeu = zzut;
        }
    }
}
