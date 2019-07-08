package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbmt extends zzbrd<zzbmt, zza> implements zzbsn {
    private static volatile zzbsw<zzbmt> zzcas;
    /* access modifiers changed from: private */
    public static final zzbmt zzfgj = new zzbmt();
    private int zzfgh;
    private int zzfgi;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmt, zza> implements zzbsn {
        private zza() {
            super(zzbmt.zzfgj);
        }

        /* synthetic */ zza(zzbmu zzbmu) {
            this();
        }
    }

    private zzbmt() {
    }

    public final zzbmn zzaic() {
        zzbmn zzdt = zzbmn.zzdt(this.zzfgh);
        return zzdt == null ? zzbmn.UNRECOGNIZED : zzdt;
    }

    public final int zzaid() {
        return this.zzfgi;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbmu.zzcaq[i - 1]) {
            case 1:
                return new zzbmt();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfgh", "zzfgi"};
                return zza((zzbsl) zzfgj, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", objArr);
            case 4:
                return zzfgj;
            case 5:
                zzbsw<zzbmt> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzbmt.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzfgj);
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

    public static zzbmt zzaie() {
        return zzfgj;
    }

    static {
        zzbrd.zza(zzbmt.class, zzfgj);
    }
}
