package com.google.android.gms.internal.measurement;

final class zzvm implements zzvu {
    private zzvu[] zzcaj;

    zzvm(zzvu... zzvuArr) {
        this.zzcaj = zzvuArr;
    }

    public final boolean zze(Class<?> cls) {
        for (zzvu zze : this.zzcaj) {
            if (zze.zze(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzvt zzf(Class<?> cls) {
        zzvu[] zzvuArr;
        for (zzvu zzvu : this.zzcaj) {
            if (zzvu.zze(cls)) {
                return zzvu.zzf(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
