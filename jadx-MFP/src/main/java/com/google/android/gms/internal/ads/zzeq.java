package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzeq extends zzeu {
    public zzeq(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 48);
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        this.zzun.zzff = Integer.valueOf(2);
        boolean booleanValue = ((Boolean) this.zzuw.invoke(null, new Object[]{this.zzqo.getContext()})).booleanValue();
        synchronized (this.zzun) {
            if (booleanValue) {
                this.zzun.zzff = Integer.valueOf(1);
            } else {
                this.zzun.zzff = Integer.valueOf(0);
            }
        }
    }
}
