package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbnc.zza;
import java.security.GeneralSecurityException;

public final class zzbjo {
    public static zzbnc zza(String str, String str2, String str3, int i, boolean z) {
        zza zzfo = zzbnc.zzais().zzfo(str2);
        String str4 = "type.googleapis.com/google.crypto.tink.";
        String valueOf = String.valueOf(str3);
        return (zzbnc) ((zzbrd) zzfo.zzfp(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4)).zzdy(0).zzbc(true).zzfq(str).zzana());
    }

    public static void zza(zzbns zzbns) throws GeneralSecurityException {
        for (zzbnc zzbnc : zzbns.zzajv()) {
            if (zzbnc.zzaig().isEmpty()) {
                throw new GeneralSecurityException("Missing type_url.");
            } else if (zzbnc.zzaio().isEmpty()) {
                throw new GeneralSecurityException("Missing primitive_name.");
            } else if (!zzbnc.zzair().isEmpty()) {
                zzbkb.zza(zzbkb.zzfi(zzbnc.zzair()).zzb(zzbnc.zzaig(), zzbnc.zzaio(), zzbnc.zzaip()), zzbnc.zzaiq());
            } else {
                throw new GeneralSecurityException("Missing catalogue_name.");
            }
        }
    }
}
