package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

final class zzbkx implements zzbjt<zzbjx> {
    zzbkx() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    public final int getVersion() {
        return 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzh */
    public final zzbjx zza(zzbpu zzbpu) throws GeneralSecurityException {
        zzbow zzbow;
        try {
            zzbmp zzae = zzbmp.zzae(zzbpu);
            if (zzae instanceof zzbmp) {
                zzbmp zzbmp = zzae;
                zzbpd.zzs(zzbmp.getVersion(), 0);
                if (zzbmp.zzagf().size() >= 16) {
                    zza(zzbmp.zzahw());
                    zzbmn zzaic = zzbmp.zzahw().zzaic();
                    SecretKeySpec secretKeySpec = new SecretKeySpec(zzbmp.zzagf().toByteArray(), "HMAC");
                    int zzaid = zzbmp.zzahw().zzaid();
                    switch (zzbky.zzfds[zzaic.ordinal()]) {
                        case 1:
                            zzbow = new zzbow("HMACSHA1", secretKeySpec, zzaid);
                            break;
                        case 2:
                            zzbow = new zzbow("HMACSHA256", secretKeySpec, zzaid);
                            break;
                        case 3:
                            zzbow = new zzbow("HMACSHA512", secretKeySpec, zzaid);
                            break;
                        default:
                            throw new GeneralSecurityException("unknown hash");
                    }
                    return zzbow;
                }
                throw new GeneralSecurityException("key too short");
            }
            throw new GeneralSecurityException("expected HmacKey proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized HmacKey proto", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb((zzbsl) zzbmr.zzag(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized HmacKeyFormat proto", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbmr) {
            zzbmr zzbmr = (zzbmr) zzbsl;
            if (zzbmr.getKeySize() >= 16) {
                zza(zzbmr.zzahw());
                return (zzbrd) zzbmp.zzahx().zzdu(0).zzc(zzbmr.zzahw()).zzaf(zzbpu.zzr(zzboy.zzeg(zzbmr.getKeySize()))).zzana();
            }
            throw new GeneralSecurityException("key too short");
        }
        throw new GeneralSecurityException("expected HmacKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.HmacKey").zzai(((zzbmp) zzb(zzbpu)).zzakf()).zzb(zzb.SYMMETRIC).zzana());
    }

    private static void zza(zzbmt zzbmt) throws GeneralSecurityException {
        if (zzbmt.zzaid() >= 10) {
            switch (zzbky.zzfds[zzbmt.zzaic().ordinal()]) {
                case 1:
                    if (zzbmt.zzaid() > 20) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 2:
                    if (zzbmt.zzaid() > 32) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 3:
                    if (zzbmt.zzaid() > 64) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                default:
                    throw new GeneralSecurityException("unknown hash type");
            }
        } else {
            throw new GeneralSecurityException("tag size too small");
        }
    }

    public final /* synthetic */ Object zza(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbmp) {
            zzbmp zzbmp = (zzbmp) zzbsl;
            zzbpd.zzs(zzbmp.getVersion(), 0);
            if (zzbmp.zzagf().size() >= 16) {
                zza(zzbmp.zzahw());
                zzbmn zzaic = zzbmp.zzahw().zzaic();
                SecretKeySpec secretKeySpec = new SecretKeySpec(zzbmp.zzagf().toByteArray(), "HMAC");
                int zzaid = zzbmp.zzahw().zzaid();
                switch (zzbky.zzfds[zzaic.ordinal()]) {
                    case 1:
                        return new zzbow("HMACSHA1", secretKeySpec, zzaid);
                    case 2:
                        return new zzbow("HMACSHA256", secretKeySpec, zzaid);
                    case 3:
                        return new zzbow("HMACSHA512", secretKeySpec, zzaid);
                    default:
                        throw new GeneralSecurityException("unknown hash");
                }
            } else {
                throw new GeneralSecurityException("key too short");
            }
        } else {
            throw new GeneralSecurityException("expected HmacKey proto");
        }
    }
}
