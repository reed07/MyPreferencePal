package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzec extends zzeu {
    private static volatile Long zzfd;
    private static final Object zzum = new Object();

    public zzec(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 44);
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        if (zzfd == null) {
            synchronized (zzum) {
                if (zzfd == null) {
                    zzfd = (Long) this.zzuw.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzun) {
            this.zzun.zzfd = zzfd;
        }
    }
}
