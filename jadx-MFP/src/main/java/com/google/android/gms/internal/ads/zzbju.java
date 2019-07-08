package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzbju {
    private zzbne zzfda;

    private zzbju(zzbne zzbne) {
        this.zzfda = zzbne;
    }

    static final zzbju zza(zzbne zzbne) throws GeneralSecurityException {
        if (zzbne != null && zzbne.zzaiw() > 0) {
            return new zzbju(zzbne);
        }
        throw new GeneralSecurityException("empty keyset");
    }

    /* access modifiers changed from: 0000 */
    public final zzbne zzafp() {
        return this.zzfda;
    }

    public final String toString() {
        return zzbkc.zzb(this.zzfda).toString();
    }
}
