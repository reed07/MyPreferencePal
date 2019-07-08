package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzel extends zzeu {
    private final boolean zzur;

    public zzel(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 61);
        this.zzur = zzdl.zzaj();
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzuw.invoke(null, new Object[]{this.zzqo.getContext(), Boolean.valueOf(this.zzur)})).longValue();
        synchronized (this.zzun) {
            this.zzun.zzft = Long.valueOf(longValue);
        }
    }
}
