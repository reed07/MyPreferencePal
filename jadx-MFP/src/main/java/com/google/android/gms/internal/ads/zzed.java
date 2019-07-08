package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzed extends zzeu {
    private long startTime;

    public zzed(zzdl zzdl, String str, String str2, zzbl zzbl, long j, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 25);
        this.startTime = j;
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzuw.invoke(null, new Object[0])).longValue();
        synchronized (this.zzun) {
            this.zzun.zzgi = Long.valueOf(longValue);
            if (this.startTime != 0) {
                this.zzun.zzel = Long.valueOf(longValue - this.startTime);
                this.zzun.zzeq = Long.valueOf(this.startTime);
            }
        }
    }
}
