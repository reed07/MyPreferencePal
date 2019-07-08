package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzeh extends zzeu {
    private long zzup = -1;

    public zzeh(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 12);
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        this.zzun.zzeg = Long.valueOf(-1);
        this.zzun.zzeg = (Long) this.zzuw.invoke(null, new Object[]{this.zzqo.getContext()});
    }
}
