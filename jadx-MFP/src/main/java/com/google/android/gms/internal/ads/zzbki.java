package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;

final class zzbki implements zzbjt<zzbjm> {
    zzbki() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public final int getVersion() {
        return 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzbjm zza(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzblr zzr = zzblr.zzr(zzbpu);
            if (zzr instanceof zzblr) {
                zzblr zzblr = zzr;
                zzbpd.zzs(zzblr.getVersion(), 0);
                zzbpd.zzeh(zzblr.zzagf().size());
                return new zzbnw(zzblr.zzagf().toByteArray());
            }
            throw new GeneralSecurityException("expected AesGcmKey proto");
        } catch (zzbrl unused) {
            throw new GeneralSecurityException("expected AesGcmKey proto");
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb((zzbsl) zzblt.zzt(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized AesGcmKeyFormat proto", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzblt) {
            zzblt zzblt = (zzblt) zzbsl;
            zzbpd.zzeh(zzblt.getKeySize());
            return (zzbrd) zzblr.zzagu().zzs(zzbpu.zzr(zzboy.zzeg(zzblt.getKeySize()))).zzdn(0).zzana();
        }
        throw new GeneralSecurityException("expected AesGcmKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.AesGcmKey").zzai(((zzblr) zzb(zzbpu)).zzakf()).zzb(zzb.SYMMETRIC).zzana());
    }

    public final /* synthetic */ Object zza(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzblr) {
            zzblr zzblr = (zzblr) zzbsl;
            zzbpd.zzs(zzblr.getVersion(), 0);
            zzbpd.zzeh(zzblr.zzagf().size());
            return new zzbnw(zzblr.zzagf().toByteArray());
        }
        throw new GeneralSecurityException("expected AesGcmKey proto");
    }
}
