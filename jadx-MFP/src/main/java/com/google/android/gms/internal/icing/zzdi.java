package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj.zzd;

final class zzdi implements zzep {
    private static final zzdi zzjs = new zzdi();

    private zzdi() {
    }

    public static zzdi zzbp() {
        return zzjs;
    }

    public final boolean zza(Class<?> cls) {
        return zzdj.class.isAssignableFrom(cls);
    }

    public final zzeo zzb(Class<?> cls) {
        if (!zzdj.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (zzeo) zzdj.zzc(cls.asSubclass(zzdj.class)).zza(zzd.zzkc, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
