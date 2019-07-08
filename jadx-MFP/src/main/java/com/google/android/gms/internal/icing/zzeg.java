package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj.zzd;

final class zzeg implements zzfg {
    private static final zzep zzlw = new zzeh();
    private final zzep zzlv;

    public zzeg() {
        this(new zzei(zzdi.zzbp(), zzcl()));
    }

    private zzeg(zzep zzep) {
        this.zzlv = (zzep) zzdl.zza(zzep, "messageInfoFactory");
    }

    public final <T> zzff<T> zzd(Class<T> cls) {
        zzfh.zzf(cls);
        zzeo zzb = this.zzlv.zzb(cls);
        if (zzb.zzcs()) {
            if (zzdj.class.isAssignableFrom(cls)) {
                return zzev.zza(zzfh.zzde(), zzdb.zzbf(), zzb.zzct());
            }
            return zzev.zza(zzfh.zzdc(), zzdb.zzbg(), zzb.zzct());
        } else if (zzdj.class.isAssignableFrom(cls)) {
            if (zza(zzb)) {
                return zzeu.zza(cls, zzb, zzez.zzcw(), zzeb.zzcj(), zzfh.zzde(), zzdb.zzbf(), zzen.zzcp());
            }
            return zzeu.zza(cls, zzb, zzez.zzcw(), zzeb.zzcj(), zzfh.zzde(), null, zzen.zzcp());
        } else if (zza(zzb)) {
            return zzeu.zza(cls, zzb, zzez.zzcv(), zzeb.zzci(), zzfh.zzdc(), zzdb.zzbg(), zzen.zzco());
        } else {
            return zzeu.zza(cls, zzb, zzez.zzcv(), zzeb.zzci(), zzfh.zzdd(), null, zzen.zzco());
        }
    }

    private static boolean zza(zzeo zzeo) {
        return zzeo.zzcr() == zzd.zzki;
    }

    private static zzep zzcl() {
        try {
            return (zzep) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzlw;
        }
    }
}
