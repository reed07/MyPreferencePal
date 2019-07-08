package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbld extends zzbrd<zzbld, zza> implements zzbsn {
    private static volatile zzbsw<zzbld> zzcas;
    /* access modifiers changed from: private */
    public static final zzbld zzfeg = new zzbld();
    private zzblh zzfee;
    private zzbmr zzfef;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbld, zza> implements zzbsn {
        private zza() {
            super(zzbld.zzfeg);
        }

        /* synthetic */ zza(zzble zzble) {
            this();
        }
    }

    private zzbld() {
    }

    public final zzblh zzagb() {
        zzblh zzblh = this.zzfee;
        return zzblh == null ? zzblh.zzagj() : zzblh;
    }

    public final zzbmr zzagc() {
        zzbmr zzbmr = this.zzfef;
        return zzbmr == null ? zzbmr.zzaia() : zzbmr;
    }

    public static zzbld zzj(zzbpu zzbpu) throws zzbrl {
        return (zzbld) zzbrd.zza(zzfeg, zzbpu);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzble.zzcaq[i - 1]) {
            case 1:
                return new zzbld();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfee", "zzfef"};
                return zza((zzbsl) zzfeg, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", objArr);
            case 4:
                return zzfeg;
            case 5:
                zzbsw<zzbld> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzbld.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzfeg);
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

    static {
        zzbrd.zza(zzbld.class, zzfeg);
    }
}
