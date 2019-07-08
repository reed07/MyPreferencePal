package com.google.android.gms.internal.icing;

final class zzei implements zzep {
    private zzep[] zzlx;

    zzei(zzep... zzepArr) {
        this.zzlx = zzepArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzep zza : this.zzlx) {
            if (zza.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzeo zzb(Class<?> cls) {
        zzep[] zzepArr;
        for (zzep zzep : this.zzlx) {
            if (zzep.zza(cls)) {
                return zzep.zzb(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
