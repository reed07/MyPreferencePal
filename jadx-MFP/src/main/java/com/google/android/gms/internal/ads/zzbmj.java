package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbmj extends zzbrd<zzbmj, zza> implements zzbsn {
    private static volatile zzbsw<zzbmj> zzcas;
    /* access modifiers changed from: private */
    public static final zzbmj zzffr = new zzbmj();
    private int zzffo;
    private int zzffp;
    private zzbpu zzffq = zzbpu.zzfli;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmj, zza> implements zzbsn {
        private zza() {
            super(zzbmj.zzffr);
        }

        /* synthetic */ zza(zzbmk zzbmk) {
            this();
        }
    }

    private zzbmj() {
    }

    public final zzbml zzahr() {
        zzbml zzds = zzbml.zzds(this.zzffo);
        return zzds == null ? zzbml.UNRECOGNIZED : zzds;
    }

    public final zzbmn zzahs() {
        zzbmn zzdt = zzbmn.zzdt(this.zzffp);
        return zzdt == null ? zzbmn.UNRECOGNIZED : zzdt;
    }

    public final zzbpu zzaht() {
        return this.zzffq;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbmk.zzcaq[i - 1]) {
            case 1:
                return new zzbmj();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzffo", "zzffp", "zzffq"};
                return zza((zzbsl) zzffr, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", objArr);
            case 4:
                return zzffr;
            case 5:
                zzbsw<zzbmj> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzbmj.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzffr);
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

    public static zzbmj zzahu() {
        return zzffr;
    }

    static {
        zzbrd.zza(zzbmj.class, zzffr);
    }
}
