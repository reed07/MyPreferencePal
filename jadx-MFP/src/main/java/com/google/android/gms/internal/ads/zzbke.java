package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbns.zza;
import java.security.GeneralSecurityException;

public final class zzbke {
    @Deprecated
    public static final zzbns zzfdl = ((zzbns) ((zzbrd) ((zza) zzbns.zzajw().zza(zzbla.zzfdl)).zzb(zzbjo.zza("TinkAead", "Aead", "AesCtrHmacAeadKey", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "AesEaxKey", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "AesGcmKey", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "ChaCha20Poly1305Key", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "KmsAeadKey", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "KmsEnvelopeAeadKey", 0, true)).zzft("TINK_AEAD_1_0_0").zzana()));
    @Deprecated
    private static final zzbns zzfdm = ((zzbns) ((zzbrd) ((zza) zzbns.zzajw().zza(zzfdl)).zzft("TINK_AEAD_1_1_0").zzana()));
    public static final zzbns zzfdn = ((zzbns) ((zzbrd) ((zza) zzbns.zzajw().zza(zzbla.zzfdn)).zzb(zzbjo.zza("TinkAead", "Aead", "AesCtrHmacAeadKey", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "AesEaxKey", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "AesGcmKey", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "ChaCha20Poly1305Key", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "KmsAeadKey", 0, true)).zzb(zzbjo.zza("TinkAead", "Aead", "KmsEnvelopeAeadKey", 0, true)).zzft("TINK_AEAD").zzana()));

    public static void zzafu() throws GeneralSecurityException {
        zzbla.zzafu();
        zzbkb.zza("TinkAead", (zzbjn<P>) new zzbkd<P>());
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
