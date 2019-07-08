package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzblz extends zzbrd<zzblz, zza> implements zzbsn {
    private static volatile zzbsw<zzblz> zzcas;
    /* access modifiers changed from: private */
    public static final zzblz zzffc = new zzblz();
    private zzbna zzffb;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblz, zza> implements zzbsn {
        private zza() {
            super(zzblz.zzffc);
        }

        /* synthetic */ zza(zzbma zzbma) {
            this();
        }
    }

    private zzblz() {
    }

    public final zzbna zzagz() {
        zzbna zzbna = this.zzffb;
        return zzbna == null ? zzbna.zzaim() : zzbna;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbma.zzcaq[i - 1]) {
            case 1:
                return new zzblz();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzffb"};
                return zza((zzbsl) zzffc, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", objArr);
            case 4:
                return zzffc;
            case 5:
                zzbsw<zzblz> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzblz.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzffc);
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

    public static zzblz zzaha() {
        return zzffc;
    }

    static {
        zzbrd.zza(zzblz.class, zzffc);
    }
}
