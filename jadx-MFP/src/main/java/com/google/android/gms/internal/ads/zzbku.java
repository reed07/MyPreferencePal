package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

final class zzbku {
    public static void zza(zzbmd zzbmd) throws GeneralSecurityException {
        zzbog.zza(zza(zzbmd.zzahe().zzahr()));
        zza(zzbmd.zzahe().zzahs());
        if (zzbmd.zzahg() != zzblx.UNKNOWN_FORMAT) {
            zzbkb.zza(zzbmd.zzahf().zzagz());
            return;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }

    public static String zza(zzbmn zzbmn) throws NoSuchAlgorithmException {
        switch (zzbkv.zzfds[zzbmn.ordinal()]) {
            case 1:
                return "HmacSha1";
            case 2:
                return "HmacSha256";
            case 3:
                return "HmacSha512";
            default:
                String valueOf = String.valueOf(zzbmn);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
                sb.append("hash unsupported for HMAC: ");
                sb.append(valueOf);
                throw new NoSuchAlgorithmException(sb.toString());
        }
    }

    public static zzboi zza(zzbml zzbml) throws GeneralSecurityException {
        switch (zzbkv.zzfdt[zzbml.ordinal()]) {
            case 1:
                return zzboi.NIST_P256;
            case 2:
                return zzboi.NIST_P384;
            case 3:
                return zzboi.NIST_P521;
            default:
                String valueOf = String.valueOf(zzbml);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
                sb.append("unknown curve type: ");
                sb.append(valueOf);
                throw new GeneralSecurityException(sb.toString());
        }
    }

    public static zzboj zza(zzblx zzblx) throws GeneralSecurityException {
        switch (zzbkv.zzfdu[zzblx.ordinal()]) {
            case 1:
                return zzboj.UNCOMPRESSED;
            case 2:
                return zzboj.DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
            case 3:
                return zzboj.COMPRESSED;
            default:
                String valueOf = String.valueOf(zzblx);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
                sb.append("unknown point format: ");
                sb.append(valueOf);
                throw new GeneralSecurityException(sb.toString());
        }
    }
}
