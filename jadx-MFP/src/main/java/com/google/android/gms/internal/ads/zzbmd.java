package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbmd extends zzbrd<zzbmd, zza> implements zzbsn {
    private static volatile zzbsw<zzbmd> zzcas;
    /* access modifiers changed from: private */
    public static final zzbmd zzffi = new zzbmd();
    private zzbmj zzfff;
    private zzblz zzffg;
    private int zzffh;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmd, zza> implements zzbsn {
        private zza() {
            super(zzbmd.zzffi);
        }

        /* synthetic */ zza(zzbme zzbme) {
            this();
        }
    }

    private zzbmd() {
    }

    public final zzbmj zzahe() {
        zzbmj zzbmj = this.zzfff;
        return zzbmj == null ? zzbmj.zzahu() : zzbmj;
    }

    public final zzblz zzahf() {
        zzblz zzblz = this.zzffg;
        return zzblz == null ? zzblz.zzaha() : zzblz;
    }

    public final zzblx zzahg() {
        zzblx zzdp = zzblx.zzdp(this.zzffh);
        return zzdp == null ? zzblx.UNRECOGNIZED : zzdp;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbme.zzcaq[i - 1]) {
            case 1:
                return new zzbmd();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfff", "zzffg", "zzffh"};
                return zza((zzbsl) zzffi, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", objArr);
            case 4:
                return zzffi;
            case 5:
                zzbsw<zzbmd> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzbmd.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzffi);
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

    public static zzbmd zzahh() {
        return zzffi;
    }

    static {
        zzbrd.zza(zzbmd.class, zzffi);
    }
}
