package com.google.android.gms.internal.ads;

final class zzbsc implements zzbsk {
    private zzbsk[] zzfry;

    zzbsc(zzbsk... zzbskArr) {
        this.zzfry = zzbskArr;
    }

    public final boolean zzb(Class<?> cls) {
        for (zzbsk zzb : this.zzfry) {
            if (zzb.zzb(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzbsj zzc(Class<?> cls) {
        zzbsk[] zzbskArr;
        for (zzbsk zzbsk : this.zzfry) {
            if (zzbsk.zzb(cls)) {
                return zzbsk.zzc(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
