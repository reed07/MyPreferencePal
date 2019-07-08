package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzblb extends zzbrd<zzblb, zza> implements zzbsn {
    private static volatile zzbsw<zzblb> zzcas;
    /* access modifiers changed from: private */
    public static final zzblb zzfed = new zzblb();
    private int zzfea;
    private zzblf zzfeb;
    private zzbmp zzfec;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblb, zza> implements zzbsn {
        private zza() {
            super(zzblb.zzfed);
        }

        public final zza zzdk(int i) {
            zzamw();
            ((zzblb) this.zzfpy).setVersion(i);
            return this;
        }

        public final zza zzb(zzblf zzblf) {
            zzamw();
            ((zzblb) this.zzfpy).zza(zzblf);
            return this;
        }

        public final zza zzb(zzbmp zzbmp) {
            zzamw();
            ((zzblb) this.zzfpy).zza(zzbmp);
            return this;
        }

        /* synthetic */ zza(zzblc zzblc) {
            this();
        }
    }

    private zzblb() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzblf zzafx() {
        zzblf zzblf = this.zzfeb;
        return zzblf == null ? zzblf.zzagh() : zzblf;
    }

    /* access modifiers changed from: private */
    public final void zza(zzblf zzblf) {
        if (zzblf != null) {
            this.zzfeb = zzblf;
            return;
        }
        throw new NullPointerException();
    }

    public final zzbmp zzafy() {
        zzbmp zzbmp = this.zzfec;
        return zzbmp == null ? zzbmp.zzahy() : zzbmp;
    }

    /* access modifiers changed from: private */
    public final void zza(zzbmp zzbmp) {
        if (zzbmp != null) {
            this.zzfec = zzbmp;
            return;
        }
        throw new NullPointerException();
    }

    public static zzblb zzi(zzbpu zzbpu) throws zzbrl {
        return (zzblb) zzbrd.zza(zzfed, zzbpu);
    }

    public static zza zzafz() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfed.zza(zze.zzfqf, (Object) null, (Object) null));
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzblc.zzcaq[i - 1]) {
            case 1:
                return new zzblb();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfea", "zzfeb", "zzfec"};
                return zza((zzbsl) zzfed, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", objArr);
            case 4:
                return zzfed;
            case 5:
                zzbsw<zzblb> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzblb.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new zzb<>(zzfed);
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
        zzbrd.zza(zzblb.class, zzfed);
    }
}
