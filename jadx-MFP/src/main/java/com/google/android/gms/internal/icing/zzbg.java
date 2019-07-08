package com.google.android.gms.internal.icing;

import android.os.Binder;

public final /* synthetic */ class zzbg {
    public static <V> V zza(zzbh<V> zzbh) {
        long clearCallingIdentity;
        try {
            return zzbh.zzv();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V zzv = zzbh.zzv();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zzv;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }
}
