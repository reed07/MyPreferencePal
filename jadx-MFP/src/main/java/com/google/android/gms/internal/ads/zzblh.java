package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzblh extends zzbrd<zzblh, zza> implements zzbsn {
    private static volatile zzbsw<zzblh> zzcas;
    /* access modifiers changed from: private */
    public static final zzblh zzfel = new zzblh();
    private zzblj zzfeh;
    private int zzfek;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblh, zza> implements zzbsn {
        private zza() {
            super(zzblh.zzfel);
        }

        /* synthetic */ zza(zzbli zzbli) {
            this();
        }
    }

    private zzblh() {
    }

    public final zzblj zzage() {
        zzblj zzblj = this.zzfeh;
        return zzblj == null ? zzblj.zzagm() : zzblj;
    }

    public final int getKeySize() {
        return this.zzfek;
    }

    public static zzblh zzn(zzbpu zzbpu) throws zzbrl {
        return (zzblh) zzbrd.zza(zzfel, zzbpu);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbli.zzcaq[i - 1]) {
            case 1:
                return new zzblh();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfeh", "zzfek"};
                return zza((zzbsl) zzfel, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", objArr);
            case 4:
                return zzfel;
            case 5:
                zzbsw<zzblh> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzblh.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzfel);
                            zzcas = zzbsw;
                        }
                    }
                }
                return zzbsw;
            case 6:
                return Byte.valueOf(1);
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static zzblh zzagj() {
        return zzfel;
    }

    static {
        zzbrd.zza(zzblh.class, zzfel);
    }
}
