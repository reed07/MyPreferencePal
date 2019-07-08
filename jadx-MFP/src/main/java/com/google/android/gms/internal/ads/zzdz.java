package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

public final class zzdz extends zzeu {
    private static zzev<String> zzui = new zzev<>();
    private final Context zzuj;

    public zzdz(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2, Context context) {
        super(zzdl, str, str2, zzbl, i, 29);
        this.zzuj = context;
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        this.zzun.zzer = "E";
        AtomicReference zzp = zzui.zzp(this.zzuj.getPackageName());
        if (zzp.get() == null) {
            synchronized (zzp) {
                if (zzp.get() == null) {
                    zzp.set((String) this.zzuw.invoke(null, new Object[]{this.zzuj}));
                }
            }
        }
        String str = (String) zzp.get();
        synchronized (this.zzun) {
            this.zzun.zzer = zzbu.zza(str.getBytes(), true);
        }
    }
}
