package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbne.zzb;
import java.security.GeneralSecurityException;

public final class zzbjy {
    @Deprecated
    public static final zzbju zzk(byte[] bArr) throws GeneralSecurityException {
        try {
            zzbne zzm = zzbne.zzm(bArr);
            for (zzb zzb : zzm.zzaiv()) {
                if (zzb.zzaiz().zzaii() == zzbmv.zzb.UNKNOWN_KEYMATERIAL || zzb.zzaiz().zzaii() == zzbmv.zzb.SYMMETRIC || zzb.zzaiz().zzaii() == zzbmv.zzb.ASYMMETRIC_PRIVATE) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                }
            }
            return zzbju.zza(zzm);
        } catch (zzbrl unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }
}
