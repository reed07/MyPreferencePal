package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzblr extends zzbrd<zzblr, zza> implements zzbsn {
    private static volatile zzbsw<zzblr> zzcas;
    /* access modifiers changed from: private */
    public static final zzblr zzfes = new zzblr();
    private int zzfea;
    private zzbpu zzfei = zzbpu.zzfli;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblr, zza> implements zzbsn {
        private zza() {
            super(zzblr.zzfes);
        }

        public final zza zzdn(int i) {
            zzamw();
            ((zzblr) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzs(zzbpu zzbpu) {
            zzamw();
            ((zzblr) this.zzfpy).zzk(zzbpu);
            return this;
        }

        /* synthetic */ zza(zzbls zzbls) {
            this();
        }
    }

    private zzblr() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzbpu zzagf() {
        return this.zzfei;
    }

    /* access modifiers changed from: private */
    public final void zzk(zzbpu zzbpu) {
        if (zzbpu != null) {
            this.zzfei = zzbpu;
            return;
        }
        throw new NullPointerException();
    }

    public static zzblr zzr(zzbpu zzbpu) throws zzbrl {
        return (zzblr) zzbrd.zza(zzfes, zzbpu);
    }

    public static zza zzagu() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfes.zza(zze.zzfqf, (Object) null, (Object) null));
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbls.zzcaq[i - 1]) {
            case 1:
                return new zzblr();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfea", "zzfei"};
                return zza((zzbsl) zzfes, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", objArr);
            case 4:
                return zzfes;
            case 5:
                zzbsw<zzblr> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzblr.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzfes);
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
        zzbrd.zza(zzblr.class, zzfes);
    }
}
