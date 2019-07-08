package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbns.zza;
import java.security.GeneralSecurityException;

public final class zzbla {
    @Deprecated
    public static final zzbns zzfdl = ((zzbns) ((zzbrd) zzbns.zzajw().zzft("TINK_MAC_1_0_0").zzb(zzbjo.zza("TinkMac", "Mac", "HmacKey", 0, true)).zzana()));
    @Deprecated
    private static final zzbns zzfdm = ((zzbns) ((zzbrd) ((zza) zzbns.zzajw().zza(zzfdl)).zzft("TINK_MAC_1_1_0").zzana()));
    public static final zzbns zzfdn = ((zzbns) ((zzbrd) ((zza) zzbns.zzajw().zza(zzfdl)).zzft("TINK_MAC").zzana()));

    public static void zzafu() throws GeneralSecurityException {
        zzbkb.zza("TinkMac", (zzbjn<P>) new zzbkz<P>());
        zzbjo.zza(zzfdn);
    }

    static {
        try {
            zzafu();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
