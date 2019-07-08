package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzeu implements Callable {
    private final String TAG = getClass().getSimpleName();
    private final String className;
    protected final zzdl zzqo;
    protected final zzbl zzun;
    private final String zzuu;
    protected Method zzuw;
    private final int zzva;
    private final int zzvb;

    public zzeu(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        this.zzqo = zzdl;
        this.className = str;
        this.zzuu = str2;
        this.zzun = zzbl;
        this.zzva = i;
        this.zzvb = i2;
    }

    /* access modifiers changed from: protected */
    public abstract void zzas() throws IllegalAccessException, InvocationTargetException;

    /* renamed from: zzau */
    public Void call() throws Exception {
        try {
            long nanoTime = System.nanoTime();
            this.zzuw = this.zzqo.zza(this.className, this.zzuu);
            if (this.zzuw == null) {
                return null;
            }
            zzas();
            zzco zzah = this.zzqo.zzah();
            if (!(zzah == null || this.zzva == Integer.MIN_VALUE)) {
                zzah.zza(this.zzvb, this.zzva, (System.nanoTime() - nanoTime) / 1000);
            }
            return null;
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }
}
