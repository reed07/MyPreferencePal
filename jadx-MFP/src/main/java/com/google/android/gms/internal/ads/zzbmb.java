package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbmb extends zzbrd<zzbmb, zza> implements zzbsn {
    private static volatile zzbsw<zzbmb> zzcas;
    /* access modifiers changed from: private */
    public static final zzbmb zzffe = new zzbmb();
    private zzbmd zzffd;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmb, zza> implements zzbsn {
        private zza() {
            super(zzbmb.zzffe);
        }

        /* synthetic */ zza(zzbmc zzbmc) {
            this();
        }
    }

    private zzbmb() {
    }

    public final zzbmd zzahc() {
        zzbmd zzbmd = this.zzffd;
        return zzbmd == null ? zzbmd.zzahh() : zzbmd;
    }

    public static zzbmb zzw(zzbpu zzbpu) throws zzbrl {
        return (zzbmb) zzbrd.zza(zzffe, zzbpu);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbmc.zzcaq[i - 1]) {
            case 1:
                return new zzbmb();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzffd"};
                return zza((zzbsl) zzffe, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", objArr);
            case 4:
                return zzffe;
            case 5:
                zzbsw<zzbmb> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzbmb.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzffe);
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
        zzbrd.zza(zzbmb.class, zzffe);
    }
}
