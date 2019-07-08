package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbno extends zzbrd<zzbno, zza> implements zzbsn {
    private static volatile zzbsw<zzbno> zzcas;
    /* access modifiers changed from: private */
    public static final zzbno zzfia = new zzbno();
    private String zzfhy = "";
    private zzbna zzfhz;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbno, zza> implements zzbsn {
        private zza() {
            super(zzbno.zzfia);
        }

        /* synthetic */ zza(zzbnp zzbnp) {
            this();
        }
    }

    private zzbno() {
    }

    public final String zzajr() {
        return this.zzfhy;
    }

    public final zzbna zzajs() {
        zzbna zzbna = this.zzfhz;
        return zzbna == null ? zzbna.zzaim() : zzbna;
    }

    public static zzbno zzam(zzbpu zzbpu) throws zzbrl {
        return (zzbno) zzbrd.zza(zzfia, zzbpu);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnp.zzcaq[i - 1]) {
            case 1:
                return new zzbno();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfhy", "zzfhz"};
                return zza((zzbsl) zzfia, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", objArr);
            case 4:
                return zzfia;
            case 5:
                zzbsw<zzbno> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzbno.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzfia);
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

    public static zzbno zzajt() {
        return zzfia;
    }

    static {
        zzbrd.zza(zzbno.class, zzfia);
    }
}
