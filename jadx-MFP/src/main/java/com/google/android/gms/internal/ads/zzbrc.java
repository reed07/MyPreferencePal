package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;

final class zzbrc implements zzbsk {
    private static final zzbrc zzfpt = new zzbrc();

    private zzbrc() {
    }

    public static zzbrc zzamq() {
        return zzfpt;
    }

    public final boolean zzb(Class<?> cls) {
        return zzbrd.class.isAssignableFrom(cls);
    }

    public final zzbsj zzc(Class<?> cls) {
        if (!zzbrd.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (zzbsj) zzbrd.zzd(cls.asSubclass(zzbrd.class)).zza(zze.zzfqd, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
