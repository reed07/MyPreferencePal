package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;

@ShowFirstParty
public final class zzdi {
    @VisibleForTesting
    private static zzdj zzbdo = new zzba();
    static int zzyn;

    public static void setLogLevel(int i) {
        zzyn = i;
        zzbdo.setLogLevel(i);
    }

    public static void e(String str) {
        zzbdo.e(str);
    }

    public static void zza(String str, Throwable th) {
        zzbdo.zza(str, th);
    }

    public static void zzab(String str) {
        zzbdo.zzab(str);
    }

    public static void zzb(String str, Throwable th) {
        zzbdo.zzb(str, th);
    }

    public static void zzdm(String str) {
        zzbdo.zzdm(str);
    }

    public static void zzdn(String str) {
        zzbdo.zzdn(str);
    }

    public static void v(String str) {
        zzbdo.v(str);
    }
}
