package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzej extends zzeu {
    public zzej(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 3);
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzun) {
            zzcy zzcy = new zzcy((String) this.zzuw.invoke(null, new Object[]{this.zzqo.getContext()}));
            synchronized (this.zzun) {
                this.zzun.zzdx = Long.valueOf(zzcy.zzse);
                this.zzun.zzfs = Long.valueOf(zzcy.zzsf);
            }
        }
    }
}
