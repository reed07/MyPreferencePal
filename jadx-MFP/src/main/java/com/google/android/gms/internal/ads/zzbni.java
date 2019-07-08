package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbni extends zzbrd<zzbni, zza> implements zzbsn {
    private static volatile zzbsw<zzbni> zzcas;
    /* access modifiers changed from: private */
    public static final zzbni zzfht = new zzbni();
    private int zzfea;
    private zzbnk zzfhs;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbni, zza> implements zzbsn {
        private zza() {
            super(zzbni.zzfht);
        }

        public final zza zzed(int i) {
            zzamw();
            ((zzbni) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzb(zzbnk zzbnk) {
            zzamw();
            ((zzbni) this.zzfpy).zza(zzbnk);
            return this;
        }

        /* synthetic */ zza(zzbnj zzbnj) {
            this();
        }
    }

    private zzbni() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzbnk zzaji() {
        zzbnk zzbnk = this.zzfhs;
        return zzbnk == null ? zzbnk.zzajm() : zzbnk;
    }

    /* access modifiers changed from: private */
    public final void zza(zzbnk zzbnk) {
        if (zzbnk != null) {
            this.zzfhs = zzbnk;
            return;
        }
        throw new NullPointerException();
    }

    public static zzbni zzaj(zzbpu zzbpu) throws zzbrl {
        return (zzbni) zzbrd.zza(zzfht, zzbpu);
    }

    public static zza zzajj() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfht.zza(zze.zzfqf, (Object) null, (Object) null));
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnj.zzcaq[i - 1]) {
            case 1:
                return new zzbni();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfea", "zzfhs"};
                return zza((zzbsl) zzfht, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", objArr);
            case 4:
                return zzfht;
            case 5:
                zzbsw<zzbni> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzbni.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzfht);
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
        zzbrd.zza(zzbni.class, zzfht);
    }
}
