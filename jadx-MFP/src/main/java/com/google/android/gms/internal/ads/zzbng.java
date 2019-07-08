package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbng extends zzbrd<zzbng, zza> implements zzbsn {
    private static volatile zzbsw<zzbng> zzcas;
    /* access modifiers changed from: private */
    public static final zzbng zzfhq = new zzbng();
    private int zzccg;
    private int zzfhi;
    private zzbrk<zzb> zzfhp = zzams();

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbng, zza> implements zzbsn {
        private zza() {
            super(zzbng.zzfhq);
        }

        public final zza zzea(int i) {
            zzamw();
            ((zzbng) this.zzfpy).zzdz(i);
            return this;
        }

        public final zza zzb(zzb zzb) {
            zzamw();
            ((zzbng) this.zzfpy).zza(zzb);
            return this;
        }

        /* synthetic */ zza(zzbnh zzbnh) {
            this();
        }
    }

    public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
        private static volatile zzbsw<zzb> zzcas;
        /* access modifiers changed from: private */
        public static final zzb zzfhr = new zzb();
        private String zzfgk = "";
        private int zzfhb;
        private int zzfhm;
        private int zzfhn;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
            private zza() {
                super(zzb.zzfhr);
            }

            public final zza zzfr(String str) {
                zzamw();
                ((zzb) this.zzfpy).zzfk(str);
                return this;
            }

            public final zza zzb(zzbmy zzbmy) {
                zzamw();
                ((zzb) this.zzfpy).zza(zzbmy);
                return this;
            }

            public final zza zzec(int i) {
                zzamw();
                ((zzb) this.zzfpy).zzeb(i);
                return this;
            }

            public final zza zzb(zzbnq zzbnq) {
                zzamw();
                ((zzb) this.zzfpy).zza(zzbnq);
                return this;
            }

            /* synthetic */ zza(zzbnh zzbnh) {
                this();
            }
        }

        private zzb() {
        }

        /* access modifiers changed from: private */
        public final void zzfk(String str) {
            if (str != null) {
                this.zzfgk = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzbmy zzbmy) {
            if (zzbmy != null) {
                this.zzfhm = zzbmy.zzom();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzeb(int i) {
            this.zzfhn = i;
        }

        /* access modifiers changed from: private */
        public final void zza(zzbnq zzbnq) {
            if (zzbnq != null) {
                this.zzfhb = zzbnq.zzom();
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzajg() {
            return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfhr.zza(zze.zzfqf, (Object) null, (Object) null));
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzbnh.zzcaq[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzfgk", "zzfhm", "zzfhn", "zzfhb"};
                    return zza((zzbsl) zzfhr, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", objArr);
                case 4:
                    return zzfhr;
                case 5:
                    zzbsw<zzb> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzb.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfhr);
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
            zzbrd.zza(zzb.class, zzfhr);
        }
    }

    private zzbng() {
    }

    /* access modifiers changed from: private */
    public final void zzdz(int i) {
        this.zzfhi = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zzb zzb2) {
        if (zzb2 != null) {
            if (!this.zzfhp.zzaki()) {
                zzbrk<zzb> zzbrk = this.zzfhp;
                int size = zzbrk.size();
                this.zzfhp = zzbrk.zzel(size == 0 ? 10 : size << 1);
            }
            this.zzfhp.add(zzb2);
            return;
        }
        throw new NullPointerException();
    }

    public static zza zzaje() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfhq.zza(zze.zzfqf, (Object) null, (Object) null));
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnh.zzcaq[i - 1]) {
            case 1:
                return new zzbng();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzccg", "zzfhi", "zzfhp", zzb.class};
                return zza((zzbsl) zzfhq, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", objArr);
            case 4:
                return zzfhq;
            case 5:
                zzbsw<zzbng> zzbsw = zzcas;
                if (zzbsw == null) {
                    synchronized (zzbng.class) {
                        zzbsw = zzcas;
                        if (zzbsw == null) {
                            zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfhq);
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
        zzbrd.zza(zzbng.class, zzfhq);
    }
}
