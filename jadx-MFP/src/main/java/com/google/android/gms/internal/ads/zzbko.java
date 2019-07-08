package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzbko implements zzbjt<zzbjs> {
    zzbko() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
    }

    public final int getVersion() {
        return 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzg */
    public final zzbjs zza(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzbmh zzab = zzbmh.zzab(zzbpu);
            if (zzab instanceof zzbmh) {
                zzbmh zzbmh = zzab;
                zzbpd.zzs(zzbmh.getVersion(), 0);
                zzbku.zza(zzbmh.zzahc());
                zzbmd zzahc = zzbmh.zzahc();
                zzbmj zzahe = zzahc.zzahe();
                zzboc zzboc = new zzboc(zzbog.zza(zzbku.zza(zzahe.zzahr()), zzbmh.zzahm().toByteArray(), zzbmh.zzahn().toByteArray()), zzahe.zzaht().toByteArray(), zzbku.zza(zzahe.zzahs()), zzbku.zza(zzahc.zzahg()), new zzbkw(zzahc.zzahf().zzagz()));
                return zzboc;
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPublicKey proto", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final /* synthetic */ Object zza(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbmh) {
            zzbmh zzbmh = (zzbmh) zzbsl;
            zzbpd.zzs(zzbmh.getVersion(), 0);
            zzbku.zza(zzbmh.zzahc());
            zzbmd zzahc = zzbmh.zzahc();
            zzbmj zzahe = zzahc.zzahe();
            zzboc zzboc = new zzboc(zzbog.zza(zzbku.zza(zzahe.zzahr()), zzbmh.zzahm().toByteArray(), zzbmh.zzahn().toByteArray()), zzahe.zzaht().toByteArray(), zzbku.zza(zzahe.zzahs()), zzbku.zza(zzahc.zzahg()), new zzbkw(zzahc.zzahf().zzagz()));
            return zzboc;
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
    }
}
