package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;

final class zzbsa implements zzbtd {
    private static final zzbsk zzfrx = new zzbsb();
    private final zzbsk zzfrw;

    public zzbsa() {
        this(new zzbsc(zzbrc.zzamq(), zzans()));
    }

    private zzbsa(zzbsk zzbsk) {
        this.zzfrw = (zzbsk) zzbrf.zza(zzbsk, "messageInfoFactory");
    }

    public final <T> zzbtc<T> zze(Class<T> cls) {
        zzbte.zzg(cls);
        zzbsj zzc = this.zzfrw.zzc(cls);
        if (zzc.zzaoa()) {
            if (zzbrd.class.isAssignableFrom(cls)) {
                return zzbsr.zza(zzbte.zzaom(), zzbqt.zzamg(), zzc.zzaob());
            }
            return zzbsr.zza(zzbte.zzaok(), zzbqt.zzamh(), zzc.zzaob());
        } else if (zzbrd.class.isAssignableFrom(cls)) {
            if (zza(zzc)) {
                return zzbsp.zza(cls, zzc, zzbsv.zzaoe(), zzbrv.zzanr(), zzbte.zzaom(), zzbqt.zzamg(), zzbsi.zzanx());
            }
            return zzbsp.zza(cls, zzc, zzbsv.zzaoe(), zzbrv.zzanr(), zzbte.zzaom(), null, zzbsi.zzanx());
        } else if (zza(zzc)) {
            return zzbsp.zza(cls, zzc, zzbsv.zzaod(), zzbrv.zzanq(), zzbte.zzaok(), zzbqt.zzamh(), zzbsi.zzanw());
        } else {
            return zzbsp.zza(cls, zzc, zzbsv.zzaod(), zzbrv.zzanq(), zzbte.zzaol(), null, zzbsi.zzanw());
        }
    }

    private static boolean zza(zzbsj zzbsj) {
        return zzbsj.zzanz() == zze.zzfqj;
    }

    private static zzbsk zzans() {
        try {
            return (zzbsk) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzfrx;
        }
    }
}
