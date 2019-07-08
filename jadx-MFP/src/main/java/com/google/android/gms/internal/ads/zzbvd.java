package com.google.android.gms.internal.ads;

public final class zzbvd {

    public static final class zza extends zzbrd<zza, C0025zza> implements zzbsn {
        private static volatile zzbsw<zza> zzcas;
        /* access modifiers changed from: private */
        public static final zza zzfxm = new zza();
        private int zzccg;
        private int zzfxf;
        private zzb zzfxg;
        private zzbpu zzfxh = zzbpu.zzfli;
        private zzbpu zzfxi = zzbpu.zzfli;
        private boolean zzfxj;
        private boolean zzfxk;
        private byte zzfxl = 2;

        /* renamed from: com.google.android.gms.internal.ads.zzbvd$zza$zza reason: collision with other inner class name */
        public static final class C0025zza extends com.google.android.gms.internal.ads.zzbrd.zza<zza, C0025zza> implements zzbsn {
            private C0025zza() {
                super(zza.zzfxm);
            }

            /* synthetic */ C0025zza(zzbve zzbve) {
                this();
            }
        }

        public static final class zzb extends zzbrd<zzb, C0026zza> implements zzbsn {
            private static volatile zzbsw<zzb> zzcas;
            /* access modifiers changed from: private */
            public static final zzb zzfxr = new zzb();
            private int zzccg;
            private String zzfxn = "";
            private String zzfxo = "";
            private String zzfxp = "";
            private int zzfxq;

            /* renamed from: com.google.android.gms.internal.ads.zzbvd$zza$zzb$zza reason: collision with other inner class name */
            public static final class C0026zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, C0026zza> implements zzbsn {
                private C0026zza() {
                    super(zzb.zzfxr);
                }

                /* synthetic */ C0026zza(zzbve zzbve) {
                    this();
                }
            }

            private zzb() {
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new C0026zza(null);
                    case 3:
                        Object[] objArr = {"zzccg", "zzfxn", "zzfxo", "zzfxp", "zzfxq"};
                        return zza((zzbsl) zzfxr, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0004\u0003", objArr);
                    case 4:
                        return zzfxr;
                    case 5:
                        zzbsw<zzb> zzbsw = zzcas;
                        if (zzbsw == null) {
                            synchronized (zzb.class) {
                                zzbsw = zzcas;
                                if (zzbsw == null) {
                                    zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfxr);
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
                zzbrd.zza(zzb.class, zzfxr);
            }
        }

        public enum zzc implements zzbrg {
            SAFE(0),
            DANGEROUS(1),
            UNKNOWN(2),
            POTENTIALLY_UNWANTED(3),
            DANGEROUS_HOST(4);
            
            private static final zzbrh<zzc> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            public static zzc zzgi(int i) {
                switch (i) {
                    case 0:
                        return SAFE;
                    case 1:
                        return DANGEROUS;
                    case 2:
                        return UNKNOWN;
                    case 3:
                        return POTENTIALLY_UNWANTED;
                    case 4:
                        return DANGEROUS_HOST;
                    default:
                        return null;
                }
            }

            public static zzbri zzop() {
                return zzbvg.zzccw;
            }

            private zzc(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzbvf();
            }
        }

        private zza() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzbve.zzcaq[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0025zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzfxf", zzc.zzop(), "zzfxg", "zzfxh", "zzfxi", "zzfxj", "zzfxk"};
                    return zza((zzbsl) zzfxm, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0001\u0001Ԍ\u0000\u0002\t\u0001\u0003\n\u0002\u0004\n\u0003\u0005\u0007\u0004\u0006\u0007\u0005", objArr);
                case 4:
                    return zzfxm;
                case 5:
                    zzbsw<zza> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zza.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfxm);
                                zzcas = zzbsw;
                            }
                        }
                    }
                    return zzbsw;
                case 6:
                    return Byte.valueOf(this.zzfxl);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzfxl = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzbrd.zza(zza.class, zzfxm);
        }
    }

    public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
        private static volatile zzbsw<zzb> zzcas;
        /* access modifiers changed from: private */
        public static final zzb zzfyn = new zzb();
        private int zzccg;
        private int zzcch;
        private zzbpu zzfxh = zzbpu.zzfli;
        private byte zzfxl = 2;
        private String zzfxo = "";
        private int zzfxy;
        private String zzfxz = "";
        private String zzfya = "";
        private C0027zzb zzfyb;
        private zzbrk<zzh> zzfyc = zzams();
        private String zzfyd = "";
        private zzf zzfye;
        private boolean zzfyf;
        private zzbrk<String> zzfyg = zzbrd.zzams();
        private String zzfyh = "";
        private boolean zzfyi;
        private boolean zzfyj;
        private zzi zzfyk;
        private zzbrk<String> zzfyl = zzbrd.zzams();
        private zzbrk<String> zzfym = zzbrd.zzams();

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
            private zza() {
                super(zzb.zzfyn);
            }

            /* synthetic */ zza(zzbve zzbve) {
                this();
            }
        }

        /* renamed from: com.google.android.gms.internal.ads.zzbvd$zzb$zzb reason: collision with other inner class name */
        public static final class C0027zzb extends zzbrd<C0027zzb, zza> implements zzbsn {
            private static volatile zzbsw<C0027zzb> zzcas;
            /* access modifiers changed from: private */
            public static final C0027zzb zzfyp = new C0027zzb();
            private int zzccg;
            private String zzfyo = "";

            /* renamed from: com.google.android.gms.internal.ads.zzbvd$zzb$zzb$zza */
            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<C0027zzb, zza> implements zzbsn {
                private zza() {
                    super(C0027zzb.zzfyp);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            private C0027zzb() {
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new C0027zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        Object[] objArr = {"zzccg", "zzfyo"};
                        return zza((zzbsl) zzfyp, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", objArr);
                    case 4:
                        return zzfyp;
                    case 5:
                        zzbsw<C0027zzb> zzbsw = zzcas;
                        if (zzbsw == null) {
                            synchronized (C0027zzb.class) {
                                zzbsw = zzcas;
                                if (zzbsw == null) {
                                    zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfyp);
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
                zzbrd.zza(C0027zzb.class, zzfyp);
            }
        }

        public static final class zzc extends zzbrd<zzc, zza> implements zzbsn {
            private static volatile zzbsw<zzc> zzcas;
            /* access modifiers changed from: private */
            public static final zzc zzfyr = new zzc();
            private int zzccg;
            private zzbpu zzfgl = zzbpu.zzfli;
            private byte zzfxl = 2;
            private zzbpu zzfyq = zzbpu.zzfli;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzc, zza> implements zzbsn {
                private zza() {
                    super(zzc.zzfyr);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            private zzc() {
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 0;
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new zza(null);
                    case 3:
                        Object[] objArr = {"zzccg", "zzfyq", "zzfgl"};
                        return zza((zzbsl) zzfyr, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001Ԋ\u0000\u0002\n\u0001", objArr);
                    case 4:
                        return zzfyr;
                    case 5:
                        zzbsw<zzc> zzbsw = zzcas;
                        if (zzbsw == null) {
                            synchronized (zzc.class) {
                                zzbsw = zzcas;
                                if (zzbsw == null) {
                                    zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfyr);
                                    zzcas = zzbsw;
                                }
                            }
                        }
                        return zzbsw;
                    case 6:
                        return Byte.valueOf(this.zzfxl);
                    case 7:
                        if (obj != null) {
                            i2 = 1;
                        }
                        this.zzfxl = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzbrd.zza(zzc.class, zzfyr);
            }
        }

        public static final class zzd extends zzbrd<zzd, zza> implements zzbsn {
            private static volatile zzbsw<zzd> zzcas;
            /* access modifiers changed from: private */
            public static final zzd zzfyx = new zzd();
            private int zzccg;
            private byte zzfxl = 2;
            private C0028zzb zzfys;
            private zzbrk<zzc> zzfyt = zzams();
            private zzbpu zzfyu = zzbpu.zzfli;
            private zzbpu zzfyv = zzbpu.zzfli;
            private int zzfyw;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzd, zza> implements zzbsn {
                private zza() {
                    super(zzd.zzfyx);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            /* renamed from: com.google.android.gms.internal.ads.zzbvd$zzb$zzd$zzb reason: collision with other inner class name */
            public static final class C0028zzb extends zzbrd<C0028zzb, zza> implements zzbsn {
                private static volatile zzbsw<C0028zzb> zzcas;
                /* access modifiers changed from: private */
                public static final C0028zzb zzfzb = new C0028zzb();
                private int zzccg;
                private zzbpu zzfyy = zzbpu.zzfli;
                private zzbpu zzfyz = zzbpu.zzfli;
                private zzbpu zzfza = zzbpu.zzfli;

                /* renamed from: com.google.android.gms.internal.ads.zzbvd$zzb$zzd$zzb$zza */
                public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<C0028zzb, zza> implements zzbsn {
                    private zza() {
                        super(C0028zzb.zzfzb);
                    }

                    /* synthetic */ zza(zzbve zzbve) {
                        this();
                    }
                }

                private C0028zzb() {
                }

                /* access modifiers changed from: protected */
                public final Object zza(int i, Object obj, Object obj2) {
                    switch (zzbve.zzcaq[i - 1]) {
                        case 1:
                            return new C0028zzb();
                        case 2:
                            return new zza(null);
                        case 3:
                            Object[] objArr = {"zzccg", "zzfyy", "zzfyz", "zzfza"};
                            return zza((zzbsl) zzfzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\n\u0000\u0002\n\u0001\u0003\n\u0002", objArr);
                        case 4:
                            return zzfzb;
                        case 5:
                            zzbsw<C0028zzb> zzbsw = zzcas;
                            if (zzbsw == null) {
                                synchronized (C0028zzb.class) {
                                    zzbsw = zzcas;
                                    if (zzbsw == null) {
                                        zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfzb);
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

                public static zzbsw<C0028zzb> zzon() {
                    return (zzbsw) zzfzb.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
                }

                static {
                    zzbrd.zza(C0028zzb.class, zzfzb);
                }
            }

            private zzd() {
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 0;
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzd();
                    case 2:
                        return new zza(null);
                    case 3:
                        Object[] objArr = {"zzccg", "zzfys", "zzfyt", zzc.class, "zzfyu", "zzfyv", "zzfyw"};
                        return zza((zzbsl) zzfyx, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0001\u0001\t\u0000\u0002Л\u0003\n\u0001\u0004\n\u0002\u0005\u0004\u0003", objArr);
                    case 4:
                        return zzfyx;
                    case 5:
                        zzbsw<zzd> zzbsw = zzcas;
                        if (zzbsw == null) {
                            synchronized (zzd.class) {
                                zzbsw = zzcas;
                                if (zzbsw == null) {
                                    zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfyx);
                                    zzcas = zzbsw;
                                }
                            }
                        }
                        return zzbsw;
                    case 6:
                        return Byte.valueOf(this.zzfxl);
                    case 7:
                        if (obj != null) {
                            i2 = 1;
                        }
                        this.zzfxl = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzbrd.zza(zzd.class, zzfyx);
            }
        }

        public static final class zze extends zzbrd<zze, zza> implements zzbsn {
            private static volatile zzbsw<zze> zzcas;
            /* access modifiers changed from: private */
            public static final zze zzfze = new zze();
            private int zzccg;
            private byte zzfxl = 2;
            private zzbrk<zzc> zzfyt = zzams();
            private zzbpu zzfyu = zzbpu.zzfli;
            private zzbpu zzfyv = zzbpu.zzfli;
            private int zzfyw;
            private C0029zzb zzfzc;
            private zzbpu zzfzd = zzbpu.zzfli;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zze, zza> implements zzbsn {
                private zza() {
                    super(zze.zzfze);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            /* renamed from: com.google.android.gms.internal.ads.zzbvd$zzb$zze$zzb reason: collision with other inner class name */
            public static final class C0029zzb extends zzbrd<C0029zzb, zza> implements zzbsn {
                private static volatile zzbsw<C0029zzb> zzcas;
                /* access modifiers changed from: private */
                public static final C0029zzb zzfzh = new C0029zzb();
                private int zzccg;
                private zzbpu zzfza = zzbpu.zzfli;
                private int zzfzf;
                private zzbpu zzfzg = zzbpu.zzfli;

                /* renamed from: com.google.android.gms.internal.ads.zzbvd$zzb$zze$zzb$zza */
                public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<C0029zzb, zza> implements zzbsn {
                    private zza() {
                        super(C0029zzb.zzfzh);
                    }

                    /* synthetic */ zza(zzbve zzbve) {
                        this();
                    }
                }

                private C0029zzb() {
                }

                /* access modifiers changed from: protected */
                public final Object zza(int i, Object obj, Object obj2) {
                    switch (zzbve.zzcaq[i - 1]) {
                        case 1:
                            return new C0029zzb();
                        case 2:
                            return new zza(null);
                        case 3:
                            Object[] objArr = {"zzccg", "zzfzf", "zzfzg", "zzfza"};
                            return zza((zzbsl) zzfzh, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\n\u0001\u0003\n\u0002", objArr);
                        case 4:
                            return zzfzh;
                        case 5:
                            zzbsw<C0029zzb> zzbsw = zzcas;
                            if (zzbsw == null) {
                                synchronized (C0029zzb.class) {
                                    zzbsw = zzcas;
                                    if (zzbsw == null) {
                                        zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfzh);
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

                public static zzbsw<C0029zzb> zzon() {
                    return (zzbsw) zzfzh.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
                }

                static {
                    zzbrd.zza(C0029zzb.class, zzfzh);
                }
            }

            private zze() {
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 0;
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zze();
                    case 2:
                        return new zza(null);
                    case 3:
                        Object[] objArr = {"zzccg", "zzfzc", "zzfyt", zzc.class, "zzfyu", "zzfyv", "zzfyw", "zzfzd"};
                        return zza((zzbsl) zzfze, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001\t\u0000\u0002Л\u0003\n\u0001\u0004\n\u0002\u0005\u0004\u0003\u0006\n\u0004", objArr);
                    case 4:
                        return zzfze;
                    case 5:
                        zzbsw<zze> zzbsw = zzcas;
                        if (zzbsw == null) {
                            synchronized (zze.class) {
                                zzbsw = zzcas;
                                if (zzbsw == null) {
                                    zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfze);
                                    zzcas = zzbsw;
                                }
                            }
                        }
                        return zzbsw;
                    case 6:
                        return Byte.valueOf(this.zzfxl);
                    case 7:
                        if (obj != null) {
                            i2 = 1;
                        }
                        this.zzfxl = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzbrd.zza(zze.class, zzfze);
            }
        }

        public static final class zzf extends zzbrd<zzf, zza> implements zzbsn {
            private static volatile zzbsw<zzf> zzcas;
            /* access modifiers changed from: private */
            public static final zzf zzfzk = new zzf();
            private int zzccg;
            private int zzcch;
            private String zzfzi = "";
            private zzbpu zzfzj = zzbpu.zzfli;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzf, zza> implements zzbsn {
                private zza() {
                    super(zzf.zzfzk);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            /* renamed from: com.google.android.gms.internal.ads.zzbvd$zzb$zzf$zzb reason: collision with other inner class name */
            public enum C0030zzb implements zzbrg {
                TYPE_UNKNOWN(0),
                TYPE_CREATIVE(1);
                
                private static final zzbrh<C0030zzb> zzcbx = null;
                private final int value;

                public final int zzom() {
                    return this.value;
                }

                public static C0030zzb zzgj(int i) {
                    switch (i) {
                        case 0:
                            return TYPE_UNKNOWN;
                        case 1:
                            return TYPE_CREATIVE;
                        default:
                            return null;
                    }
                }

                public static zzbri zzop() {
                    return zzbvi.zzccw;
                }

                private C0030zzb(int i) {
                    this.value = i;
                }

                static {
                    zzcbx = new zzbvh();
                }
            }

            private zzf() {
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzf();
                    case 2:
                        return new zza(null);
                    case 3:
                        Object[] objArr = {"zzccg", "zzcch", C0030zzb.zzop(), "zzfzi", "zzfzj"};
                        return zza((zzbsl) zzfzk, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\n\u0002", objArr);
                    case 4:
                        return zzfzk;
                    case 5:
                        zzbsw<zzf> zzbsw = zzcas;
                        if (zzbsw == null) {
                            synchronized (zzf.class) {
                                zzbsw = zzcas;
                                if (zzbsw == null) {
                                    zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfzk);
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
                zzbrd.zza(zzf.class, zzfzk);
            }
        }

        public enum zzg implements zzbrg {
            UNKNOWN(0),
            URL_PHISHING(1),
            URL_MALWARE(2),
            URL_UNWANTED(3),
            CLIENT_SIDE_PHISHING_URL(4),
            CLIENT_SIDE_MALWARE_URL(5),
            DANGEROUS_DOWNLOAD_RECOVERY(6),
            DANGEROUS_DOWNLOAD_WARNING(7),
            OCTAGON_AD(8),
            OCTAGON_AD_SB_MATCH(9);
            
            private static final zzbrh<zzg> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            public static zzg zzgk(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return URL_PHISHING;
                    case 2:
                        return URL_MALWARE;
                    case 3:
                        return URL_UNWANTED;
                    case 4:
                        return CLIENT_SIDE_PHISHING_URL;
                    case 5:
                        return CLIENT_SIDE_MALWARE_URL;
                    case 6:
                        return DANGEROUS_DOWNLOAD_RECOVERY;
                    case 7:
                        return DANGEROUS_DOWNLOAD_WARNING;
                    case 8:
                        return OCTAGON_AD;
                    case 9:
                        return OCTAGON_AD_SB_MATCH;
                    default:
                        return null;
                }
            }

            public static zzbri zzop() {
                return zzbvk.zzccw;
            }

            private zzg(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzbvj();
            }
        }

        public static final class zzh extends zzbrd<zzh, C0031zzb> implements zzbsn {
            private static volatile zzbsw<zzh> zzcas;
            /* access modifiers changed from: private */
            public static final zzh zzgah = new zzh();
            private int zzccg;
            private byte zzfxl = 2;
            private String zzfxo = "";
            private int zzfzz;
            private zzd zzgaa;
            private zze zzgab;
            private int zzgac;
            private zzbrj zzgad = zzamr();
            private String zzgae = "";
            private int zzgaf;
            private zzbrk<String> zzgag = zzbrd.zzams();

            public enum zza implements zzbrg {
                AD_RESOURCE_UNKNOWN(0),
                AD_RESOURCE_CREATIVE(1),
                AD_RESOURCE_POST_CLICK(2),
                AD_RESOURCE_AUTO_CLICK_DESTINATION(3);
                
                private static final zzbrh<zza> zzcbx = null;
                private final int value;

                public final int zzom() {
                    return this.value;
                }

                public static zza zzgl(int i) {
                    switch (i) {
                        case 0:
                            return AD_RESOURCE_UNKNOWN;
                        case 1:
                            return AD_RESOURCE_CREATIVE;
                        case 2:
                            return AD_RESOURCE_POST_CLICK;
                        case 3:
                            return AD_RESOURCE_AUTO_CLICK_DESTINATION;
                        default:
                            return null;
                    }
                }

                public static zzbri zzop() {
                    return zzbvm.zzccw;
                }

                private zza(int i) {
                    this.value = i;
                }

                static {
                    zzcbx = new zzbvl();
                }
            }

            /* renamed from: com.google.android.gms.internal.ads.zzbvd$zzb$zzh$zzb reason: collision with other inner class name */
            public static final class C0031zzb extends com.google.android.gms.internal.ads.zzbrd.zza<zzh, C0031zzb> implements zzbsn {
                private C0031zzb() {
                    super(zzh.zzgah);
                }

                /* synthetic */ C0031zzb(zzbve zzbve) {
                    this();
                }
            }

            private zzh() {
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 0;
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzh();
                    case 2:
                        return new C0031zzb(null);
                    case 3:
                        Object[] objArr = {"zzccg", "zzfzz", "zzfxo", "zzgaa", "zzgab", "zzgac", "zzgad", "zzgae", "zzgaf", zza.zzop(), "zzgag"};
                        return zza((zzbsl) zzgah, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0003\u0001Ԅ\u0000\u0002\b\u0001\u0003Љ\u0002\u0004Љ\u0003\u0005\u0004\u0004\u0006\u0016\u0007\b\u0005\b\f\u0006\t\u001a", objArr);
                    case 4:
                        return zzgah;
                    case 5:
                        zzbsw<zzh> zzbsw = zzcas;
                        if (zzbsw == null) {
                            synchronized (zzh.class) {
                                zzbsw = zzcas;
                                if (zzbsw == null) {
                                    zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzgah);
                                    zzcas = zzbsw;
                                }
                            }
                        }
                        return zzbsw;
                    case 6:
                        return Byte.valueOf(this.zzfxl);
                    case 7:
                        if (obj != null) {
                            i2 = 1;
                        }
                        this.zzfxl = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzbrd.zza(zzh.class, zzgah);
            }
        }

        public static final class zzi extends zzbrd<zzi, zza> implements zzbsn {
            private static volatile zzbsw<zzi> zzcas;
            /* access modifiers changed from: private */
            public static final zzi zzgaq = new zzi();
            private int zzccg;
            private String zzgan = "";
            private long zzgao;
            private boolean zzgap;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzi, zza> implements zzbsn {
                private zza() {
                    super(zzi.zzgaq);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            private zzi() {
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzi();
                    case 2:
                        return new zza(null);
                    case 3:
                        Object[] objArr = {"zzccg", "zzgan", "zzgao", "zzgap"};
                        return zza((zzbsl) zzgaq, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\u0007\u0002", objArr);
                    case 4:
                        return zzgaq;
                    case 5:
                        zzbsw<zzi> zzbsw = zzcas;
                        if (zzbsw == null) {
                            synchronized (zzi.class) {
                                zzbsw = zzcas;
                                if (zzbsw == null) {
                                    zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzgaq);
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
                zzbrd.zza(zzi.class, zzgaq);
            }
        }

        private zzb() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzbve.zzcaq[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzfxo", "zzfxz", "zzfya", "zzfyc", zzh.class, "zzfyf", "zzfyg", "zzfyh", "zzfyi", "zzfyj", "zzcch", zzg.zzop(), "zzfxy", zzc.zzop(), "zzfyb", "zzfyd", "zzfye", "zzfxh", "zzfyk", "zzfyl", "zzfym"};
                    return zza((zzbsl) zzfyn, "\u0001\u0012\u0000\u0001\u0001\u0015\u0012\u0000\u0004\u0001\u0001\b\u0002\u0002\b\u0003\u0003\b\u0004\u0004Л\u0005\u0007\b\u0006\u001a\u0007\b\t\b\u0007\n\t\u0007\u000b\n\f\u0000\u000b\f\u0001\f\t\u0005\r\b\u0006\u000e\t\u0007\u000f\n\f\u0011\t\r\u0014\u001a\u0015\u001a", objArr);
                case 4:
                    return zzfyn;
                case 5:
                    zzbsw<zzb> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzb.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzfyn);
                                zzcas = zzbsw;
                            }
                        }
                    }
                    return zzbsw;
                case 6:
                    return Byte.valueOf(this.zzfxl);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzfxl = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzbrd.zza(zzb.class, zzfyn);
        }
    }
}
