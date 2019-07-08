package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuo.zze;

final class zzvk implements zzwm {
    private static final zzvu zzcai = new zzvl();
    private final zzvu zzcah;

    public zzvk() {
        this(new zzvm(zzun.zzwe(), zzxf()));
    }

    private zzvk(zzvu zzvu) {
        this.zzcah = (zzvu) zzuq.zza(zzvu, "messageInfoFactory");
    }

    public final <T> zzwl<T> zzh(Class<T> cls) {
        zzwn.zzj(cls);
        zzvt zzf = this.zzcah.zzf(cls);
        if (zzf.zzxn()) {
            if (zzuo.class.isAssignableFrom(cls)) {
                return zzwa.zza(zzwn.zzxz(), zzue.zzvu(), zzf.zzxo());
            }
            return zzwa.zza(zzwn.zzxx(), zzue.zzvv(), zzf.zzxo());
        } else if (zzuo.class.isAssignableFrom(cls)) {
            if (zza(zzf)) {
                return zzvz.zza(cls, zzf, zzwe.zzxr(), zzvf.zzxe(), zzwn.zzxz(), zzue.zzvu(), zzvs.zzxk());
            }
            return zzvz.zza(cls, zzf, zzwe.zzxr(), zzvf.zzxe(), zzwn.zzxz(), null, zzvs.zzxk());
        } else if (zza(zzf)) {
            return zzvz.zza(cls, zzf, zzwe.zzxq(), zzvf.zzxd(), zzwn.zzxx(), zzue.zzvv(), zzvs.zzxj());
        } else {
            return zzvz.zza(cls, zzf, zzwe.zzxq(), zzvf.zzxd(), zzwn.zzxy(), null, zzvs.zzxj());
        }
    }

    private static boolean zza(zzvt zzvt) {
        return zzvt.zzxm() == zze.zzbyu;
    }

    private static zzvu zzxf() {
        try {
            return (zzvu) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzcai;
        }
    }
}
