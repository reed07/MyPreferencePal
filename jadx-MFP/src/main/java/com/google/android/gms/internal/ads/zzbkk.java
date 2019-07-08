package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;

final class zzbkk implements zzbjt<zzbjm> {
    zzbkk() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    public final int getVersion() {
        return 0;
    }

    private static zzbjm zzd(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzc((zzbsl) zzbni.zzaj(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected KmsAeadKey proto", e);
        }
    }

    private static zzbjm zzc(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbni) {
            zzbni zzbni = (zzbni) zzbsl;
            zzbpd.zzs(zzbni.getVersion(), 0);
            String zzajl = zzbni.zzaji().zzajl();
            return zzbjw.zzfh(zzajl).zzfg(zzajl);
        }
        throw new GeneralSecurityException("expected KmsAeadKey proto");
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb((zzbsl) zzbnk.zzak(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized KmsAeadKeyFormat proto", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbnk) {
            return (zzbrd) zzbni.zzajj().zzb((zzbnk) zzbsl).zzed(0).zzana();
        }
        throw new GeneralSecurityException("expected KmsAeadKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.KmsAeadKey").zzai(((zzbni) zzb(zzbpu)).zzakf()).zzb(zzb.REMOTE).zzana());
    }

    public final /* synthetic */ Object zza(zzbsl zzbsl) throws GeneralSecurityException {
        return zzc(zzbsl);
    }

    public final /* synthetic */ Object zza(zzbpu zzbpu) throws GeneralSecurityException {
        return zzd(zzbpu);
    }
}
