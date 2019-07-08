package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;

final class zzbkn implements zzbjt<zzbjr> {
    zzbkn() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    public final int getVersion() {
        return 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final zzbjr zza(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzbmf zzx = zzbmf.zzx(zzbpu);
            if (zzx instanceof zzbmf) {
                zzbmf zzbmf = zzx;
                zzbpd.zzs(zzbmf.getVersion(), 0);
                zzbku.zza(zzbmf.zzahj().zzahc());
                zzbmd zzahc = zzbmf.zzahj().zzahc();
                zzbmj zzahe = zzahc.zzahe();
                zzboi zza = zzbku.zza(zzahe.zzahr());
                byte[] byteArray = zzbmf.zzagf().toByteArray();
                zzbob zzbob = new zzbob((ECPrivateKey) ((KeyFactory) zzbol.zzfjx.zzfu("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, byteArray), zzbog.zza(zza))), zzahe.zzaht().toByteArray(), zzbku.zza(zzahe.zzahs()), zzbku.zza(zzahc.zzahg()), new zzbkw(zzahc.zzahf().zzagz()));
                return zzbob;
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPrivateKey proto", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb((zzbsl) zzbmb.zzw(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("invalid EciesAeadHkdf key format", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbmb) {
            zzbmb zzbmb = (zzbmb) zzbsl;
            zzbku.zza(zzbmb.zzahc());
            KeyPair zza = zzbog.zza(zzbog.zza(zzbku.zza(zzbmb.zzahc().zzahe().zzahr())));
            ECPublicKey eCPublicKey = (ECPublicKey) zza.getPublic();
            ECPrivateKey eCPrivateKey = (ECPrivateKey) zza.getPrivate();
            ECPoint w = eCPublicKey.getW();
            return (zzbrd) zzbmf.zzahk().zzdq(0).zzb((zzbmh) ((zzbrd) zzbmh.zzaho().zzdr(0).zzc(zzbmb.zzahc()).zzac(zzbpu.zzr(w.getAffineX().toByteArray())).zzad(zzbpu.zzr(w.getAffineY().toByteArray())).zzana())).zzy(zzbpu.zzr(eCPrivateKey.getS().toByteArray())).zzana();
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").zzai(((zzbmf) zzb(zzbpu)).zzakf()).zzb(zzb.ASYMMETRIC_PRIVATE).zzana());
    }

    public final /* synthetic */ Object zza(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbmf) {
            zzbmf zzbmf = (zzbmf) zzbsl;
            zzbpd.zzs(zzbmf.getVersion(), 0);
            zzbku.zza(zzbmf.zzahj().zzahc());
            zzbmd zzahc = zzbmf.zzahj().zzahc();
            zzbmj zzahe = zzahc.zzahe();
            zzboi zza = zzbku.zza(zzahe.zzahr());
            byte[] byteArray = zzbmf.zzagf().toByteArray();
            zzbob zzbob = new zzbob((ECPrivateKey) ((KeyFactory) zzbol.zzfjx.zzfu("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, byteArray), zzbog.zza(zza))), zzahe.zzaht().toByteArray(), zzbku.zza(zzahe.zzahs()), zzbku.zza(zzahc.zzahg()), new zzbkw(zzahc.zzahf().zzagz()));
            return zzbob;
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
    }
}
