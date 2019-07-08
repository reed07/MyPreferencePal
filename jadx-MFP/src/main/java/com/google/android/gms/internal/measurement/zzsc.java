package com.google.android.gms.internal.measurement;

import android.os.Binder;

public final /* synthetic */ class zzsc {
    public static <V> V zza(zzsd<V> zzsd) {
        long clearCallingIdentity;
        try {
            return zzsd.zzto();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V zzto = zzsd.zzto();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zzto;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }
}
