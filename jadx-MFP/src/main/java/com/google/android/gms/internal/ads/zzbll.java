package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbll extends zzbrd<zzbll, zza> implements zzbsn {
    private static volatile zzbsw<zzbll> zzcas;
    /* access modifiers changed from: private */
    public static final zzbll zzfep = new zzbll();
    private int zzfea;
    private zzbpu zzfei = zzbpu.zzfli;
    private zzblp zzfeo;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbll, zza> implements zzbsn {
        private zza() {
            super(zzbll.zzfep);
        }

        public final zza zzdm(int i) {
            zzamw();
            ((zzbll) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzb(zzblp zzblp) {
            zzamw();
            ((zzbll) this.zzfpy).zza(zzblp);
            return this;
        }

        public final zza zzp(zzbpu zzbpu) {
            zzamw();
            ((zzbll) this.zzfpy).zzk(zzbpu);
            return this;
        }

        /* synthetic */ zza(zzblm zzblm) {
            this();
        }
    }

    private zzbll() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzblp zzago() {
        zzblp zzblp = this.zzfeo;
        return zzblp == null ? zzblp.zzags() : zzblp;
    }

    /* access modifiers changed from: private */
    public final void zza(zzblp zzblp) {
        if (zzblp != null) {
            this.zzfeo = zzblp;
            return;
        }
        throw new NullPointerException();
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

    public static zzbll zzo(zzbpu zzbpu) throws zzbrl {
        return (zzbll) zzbrd.zza(zzfep, zzbpu);
    }

    public static zza zzagp() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfep.zza(zze.zzfqf, (Object) null, (Object) null));
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzblm.zzcaq[i - 1]) {
            case 1:
                return new zzbll();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfea", "zzfeo", "zzfei"};
                return zza((zzbsl) zzfep, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", objArr);
            case 4:
                return zzfep;
            case 5:
                zzbsw<zzbll> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzbll.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzfep);
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
        zzbrd.zza(zzbll.class, zzfep);
    }
}
