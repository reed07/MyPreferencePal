package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;

final class zzbkm implements zzbjt<zzbjm> {
    zzbkm() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    public final int getVersion() {
        return 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzbjm zza(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzbnm zzal = zzbnm.zzal(zzbpu);
            if (zzal instanceof zzbnm) {
                zzbnm zzbnm = zzal;
                zzbpd.zzs(zzbnm.getVersion(), 0);
                String zzajr = zzbnm.zzajo().zzajr();
                return new zzbkl(zzbnm.zzajo().zzajs(), zzbjw.zzfh(zzajr).zzfg(zzajr));
            }
            throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized KmSEnvelopeAeadKey proto", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb((zzbsl) zzbno.zzam(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized KmsEnvelopeAeadKeyFormat proto", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbno) {
            return (zzbrd) zzbnm.zzajp().zzb((zzbno) zzbsl).zzee(0).zzana();
        }
        throw new GeneralSecurityException("expected KmsEnvelopeAeadKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").zzai(((zzbnm) zzb(zzbpu)).zzakf()).zzb(zzb.REMOTE).zzana());
    }

    public final /* synthetic */ Object zza(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbnm) {
            zzbnm zzbnm = (zzbnm) zzbsl;
            zzbpd.zzs(zzbnm.getVersion(), 0);
            String zzajr = zzbnm.zzajo().zzajr();
            return new zzbkl(zzbnm.zzajo().zzajs(), zzbjw.zzfh(zzajr).zzfg(zzajr));
        }
        throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
    }
}
