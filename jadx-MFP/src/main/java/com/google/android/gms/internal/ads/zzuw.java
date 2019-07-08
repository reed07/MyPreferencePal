package com.google.android.gms.internal.ads;

public final class zzuw {

    public static final class zza extends zzbrd<zza, zzb> implements zzbsn {
        private static volatile zzbsw<zza> zzcas;
        /* access modifiers changed from: private */
        public static final zza zzccj = new zza();
        private int zzccg;
        private int zzcch;
        private zzl zzcci;

        /* renamed from: com.google.android.gms.internal.ads.zzuw$zza$zza reason: collision with other inner class name */
        public enum C0033zza implements zzbrg {
            AD_FORMAT_TYPE_UNSPECIFIED(0),
            BANNER(1),
            INTERSTITIAL(2),
            NATIVE_EXPRESS(3),
            NATIVE_CONTENT(4),
            NATIVE_APP_INSTALL(5),
            NATIVE_CUSTOM_TEMPLATE(6),
            DFP_BANNER(7),
            DFP_INTERSTITIAL(8),
            REWARD_BASED_VIDEO_AD(9),
            BANNER_SEARCH_ADS(10);
            
            private static final zzbrh<C0033zza> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            public static C0033zza zzca(int i) {
                switch (i) {
                    case 0:
                        return AD_FORMAT_TYPE_UNSPECIFIED;
                    case 1:
                        return BANNER;
                    case 2:
                        return INTERSTITIAL;
                    case 3:
                        return NATIVE_EXPRESS;
                    case 4:
                        return NATIVE_CONTENT;
                    case 5:
                        return NATIVE_APP_INSTALL;
                    case 6:
                        return NATIVE_CUSTOM_TEMPLATE;
                    case 7:
                        return DFP_BANNER;
                    case 8:
                        return DFP_INTERSTITIAL;
                    case 9:
                        return REWARD_BASED_VIDEO_AD;
                    case 10:
                        return BANNER_SEARCH_ADS;
                    default:
                        return null;
                }
            }

            public static zzbri zzop() {
                return zzuz.zzccw;
            }

            private C0033zza(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzuy();
            }
        }

        public static final class zzb extends com.google.android.gms.internal.ads.zzbrd.zza<zza, zzb> implements zzbsn {
            private zzb() {
                super(zza.zzccj);
            }

            /* synthetic */ zzb(zzux zzux) {
                this();
            }
        }

        private zza() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzb(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcch", C0033zza.zzop(), "zzcci"};
                    return zza((zzbsl) zzccj, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001", objArr);
                case 4:
                    return zzccj;
                case 5:
                    zzbsw<zza> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zza.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzccj);
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

        public static zzbsw<zza> zzon() {
            return (zzbsw) zzccj.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zza.class, zzccj);
        }
    }

    public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
        private static volatile zzbsw<zzb> zzcas;
        /* access modifiers changed from: private */
        public static final zzb zzcda = new zzb();
        private int zzccg;
        private String zzccx = "";
        private zzbrk<zza> zzccy = zzams();
        private int zzccz;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
            private zza() {
                super(zzb.zzcda);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzb() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzccx", "zzccy", zza.class, "zzccz", zzvc.zzop()};
                    return zza((zzbsl) zzcda, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b\u0003\f\u0001", objArr);
                case 4:
                    return zzcda;
                case 5:
                    zzbsw<zzb> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzb.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcda);
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

        public static zzbsw<zzb> zzon() {
            return (zzbsw) zzcda.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzb.class, zzcda);
        }
    }

    public static final class zzc extends zzbrd<zzc, zza> implements zzbsn {
        private static volatile zzbsw<zzc> zzcas;
        /* access modifiers changed from: private */
        public static final zzc zzcdh = new zzc();
        private int zzccg;
        private int zzcdb;
        private zzn zzcdc;
        private zzn zzcdd;
        private zzn zzcde;
        private zzbrk<zzn> zzcdf = zzams();
        private int zzcdg;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzc, zza> implements zzbsn {
            private zza() {
                super(zzc.zzcdh);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzc() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcdb", "zzcdc", "zzcdd", "zzcde", "zzcdf", zzn.class, "zzcdg"};
                    return zza((zzbsl) zzcdh, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001\u0004\u0000\u0002\t\u0001\u0003\t\u0002\u0004\t\u0003\u0005\u001b\u0006\u0004\u0004", objArr);
                case 4:
                    return zzcdh;
                case 5:
                    zzbsw<zzc> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzc.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcdh);
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

        public static zzbsw<zzc> zzon() {
            return (zzbsw) zzcdh.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzc.class, zzcdh);
        }
    }

    public static final class zzd extends zzbrd<zzd, zza> implements zzbsn {
        private static volatile zzbsw<zzd> zzcas;
        /* access modifiers changed from: private */
        public static final zzd zzcdm = new zzd();
        private int zzccg;
        private int zzcdi;
        private zzo zzcdj;
        private String zzcdk = "";
        private String zzcdl = "";

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzd, zza> implements zzbsn {
            private zza() {
                super(zzd.zzcdm);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        public enum zzb implements zzbrg {
            PLATFORM_UNSPECIFIED(0),
            IOS(1),
            ANDROID(2);
            
            private static final zzbrh<zzb> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            public static zzb zzcc(int i) {
                switch (i) {
                    case 0:
                        return PLATFORM_UNSPECIFIED;
                    case 1:
                        return IOS;
                    case 2:
                        return ANDROID;
                    default:
                        return null;
                }
            }

            public static zzbri zzop() {
                return zzvb.zzccw;
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzva();
            }
        }

        private zzd() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcdi", zzb.zzop(), "zzcdj", "zzcdk", "zzcdl"};
                    return zza((zzbsl) zzcdm, "\u0001\u0004\u0000\u0001\u0005\b\u0004\u0000\u0000\u0000\u0005\f\u0000\u0006\t\u0001\u0007\b\u0002\b\b\u0003", objArr);
                case 4:
                    return zzcdm;
                case 5:
                    zzbsw<zzd> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzd.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcdm);
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
            zzbrd.zza(zzd.class, zzcdm);
        }
    }

    public static final class zze extends zzbrd<zze, zza> implements zzbsn {
        private static volatile zzbsw<zze> zzcas;
        /* access modifiers changed from: private */
        public static final zze zzcdz = new zze();
        private int zzccg;
        private String zzcdv = "";
        private int zzcdw;
        private zzbrj zzcdx = zzamr();
        private zzn zzcdy;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zze, zza> implements zzbsn {
            private zza() {
                super(zze.zzcdz);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zze() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcdv", "zzcdw", zzvc.zzop(), "zzcdx", "zzcdy"};
                    return zza((zzbsl) zzcdz, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\b\u0000\u0002\f\u0001\u0003\u0016\u0004\t\u0002", objArr);
                case 4:
                    return zzcdz;
                case 5:
                    zzbsw<zze> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zze.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcdz);
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
            zzbrd.zza(zze.class, zzcdz);
        }
    }

    public static final class zzf extends zzbrd<zzf, zza> implements zzbsn {
        private static volatile zzbsw<zzf> zzcas;
        /* access modifiers changed from: private */
        public static final zzf zzceb = new zzf();
        private int zzccg;
        private zzbrj zzcdx = zzamr();
        private int zzcea;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzf, zza> implements zzbsn {
            private zza() {
                super(zzf.zzceb);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzf() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcea", zzvc.zzop(), "zzcdx"};
                    return zza((zzbsl) zzceb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0000\u0002\u0016", objArr);
                case 4:
                    return zzceb;
                case 5:
                    zzbsw<zzf> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzf.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzceb);
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

        public static zzbsw<zzf> zzon() {
            return (zzbsw) zzceb.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzf.class, zzceb);
        }
    }

    public static final class zzg extends zzbrd<zzg, zza> implements zzbsn {
        private static volatile zzbsw<zzg> zzcas;
        /* access modifiers changed from: private */
        public static final zzg zzcee = new zzg();
        private int zzccg;
        private zzn zzcdy;
        private int zzcea;
        private zze zzcec;
        private zzbrk<zzm> zzced = zzams();

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzg, zza> implements zzbsn {
            private zza() {
                super(zzg.zzcee);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzg() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcec", "zzced", zzm.class, "zzcea", zzvc.zzop(), "zzcdy"};
                    return zza((zzbsl) zzcee, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\t\u0000\u0002\u001b\u0003\f\u0001\u0004\t\u0002", objArr);
                case 4:
                    return zzcee;
                case 5:
                    zzbsw<zzg> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzg.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcee);
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

        public static zzbsw<zzg> zzon() {
            return (zzbsw) zzcee.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzg.class, zzcee);
        }
    }

    public static final class zzh extends zzbrd<zzh, zza> implements zzbsn {
        private static volatile zzbsw<zzh> zzcas;
        /* access modifiers changed from: private */
        public static final zzh zzceg = new zzh();
        private int zzccg;
        private int zzcch;
        private int zzcef;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzh, zza> implements zzbsn {
            private zza() {
                super(zzh.zzceg);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        public enum zzb implements zzbrg {
            CELLULAR_NETWORK_TYPE_UNSPECIFIED(0),
            TWO_G(1),
            THREE_G(2),
            LTE(4);
            
            private static final zzbrh<zzb> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            public static zzb zzce(int i) {
                if (i == 4) {
                    return LTE;
                }
                switch (i) {
                    case 0:
                        return CELLULAR_NETWORK_TYPE_UNSPECIFIED;
                    case 1:
                        return TWO_G;
                    case 2:
                        return THREE_G;
                    default:
                        return null;
                }
            }

            public static zzbri zzop() {
                return zzvg.zzccw;
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzvf();
            }
        }

        public enum zzc implements zzbrg {
            NETWORKTYPE_UNSPECIFIED(0),
            CELL(1),
            WIFI(2);
            
            private static final zzbrh<zzc> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            public static zzc zzcf(int i) {
                switch (i) {
                    case 0:
                        return NETWORKTYPE_UNSPECIFIED;
                    case 1:
                        return CELL;
                    case 2:
                        return WIFI;
                    default:
                        return null;
                }
            }

            public static zzbri zzop() {
                return zzvi.zzccw;
            }

            private zzc(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzvh();
            }
        }

        private zzh() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcch", zzc.zzop(), "zzcef", zzb.zzop()};
                    return zza((zzbsl) zzceg, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001", objArr);
                case 4:
                    return zzceg;
                case 5:
                    zzbsw<zzh> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzh.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzceg);
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

        public static zzbsw<zzh> zzon() {
            return (zzbsw) zzceg.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzh.class, zzceg);
        }
    }

    public static final class zzi extends zzbrd<zzi, zza> implements zzbsn {
        private static volatile zzbsw<zzi> zzcas;
        /* access modifiers changed from: private */
        public static final zzi zzces = new zzi();
        private int zzccg;
        private int zzceq;
        private zzn zzcer;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzi, zza> implements zzbsn {
            private zza() {
                super(zzi.zzces);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzi() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzceq", zzvc.zzop(), "zzcer"};
                    return zza((zzbsl) zzces, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001", objArr);
                case 4:
                    return zzces;
                case 5:
                    zzbsw<zzi> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzi.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzces);
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

        public static zzbsw<zzi> zzon() {
            return (zzbsw) zzces.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzi.class, zzces);
        }
    }

    public static final class zzj extends zzbrd<zzj, zza> implements zzbsn {
        private static volatile zzbsw<zzj> zzcas;
        /* access modifiers changed from: private */
        public static final zzj zzcfe = new zzj();
        private int zzccg;
        private int zzcet = 1000;
        private int zzceu = 1000;
        private int zzcev;
        private int zzcew;
        private int zzcex;
        private int zzcey;
        private int zzcez;
        private int zzcfa;
        private int zzcfb;
        private int zzcfc;
        private zzk zzcfd;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzj, zza> implements zzbsn {
            private zza() {
                super(zzj.zzcfe);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzj() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcet", zzvc.zzop(), "zzceu", zzvc.zzop(), "zzcev", "zzcew", "zzcex", "zzcey", "zzcez", "zzcfa", "zzcfb", "zzcfc", "zzcfd"};
                    return zza((zzbsl) zzcfe, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u0004\u0004\u0006\u0004\u0005\u0007\u0004\u0006\b\u0004\u0007\t\u0004\b\n\u0004\t\u000b\t\n", objArr);
                case 4:
                    return zzcfe;
                case 5:
                    zzbsw<zzj> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzj.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcfe);
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

        public static zzbsw<zzj> zzon() {
            return (zzbsw) zzcfe.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzj.class, zzcfe);
        }
    }

    public static final class zzk extends zzbrd<zzk, zza> implements zzbsn {
        private static volatile zzbsw<zzk> zzcas;
        /* access modifiers changed from: private */
        public static final zzk zzcfh = new zzk();
        private int zzccg;
        private int zzcff;
        private int zzcfg;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzk, zza> implements zzbsn {
            private zza() {
                super(zzk.zzcfh);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzk() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcff", "zzcfg"};
                    return zza((zzbsl) zzcfh, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", objArr);
                case 4:
                    return zzcfh;
                case 5:
                    zzbsw<zzk> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzk.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcfh);
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
            zzbrd.zza(zzk.class, zzcfh);
        }
    }

    public static final class zzl extends zzbrd<zzl, zza> implements zzbsn {
        private static volatile zzbsw<zzl> zzcas;
        /* access modifiers changed from: private */
        public static final zzl zzcfk = new zzl();
        private int zzccg;
        private int zzcfi;
        private int zzcfj;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzl, zza> implements zzbsn {
            private zza() {
                super(zzl.zzcfk);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzl() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcfi", "zzcfj"};
                    return zza((zzbsl) zzcfk, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", objArr);
                case 4:
                    return zzcfk;
                case 5:
                    zzbsw<zzl> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzl.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcfk);
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
            zzbrd.zza(zzl.class, zzcfk);
        }
    }

    public static final class zzm extends zzbrd<zzm, zza> implements zzbsn {
        private static volatile zzbsw<zzm> zzcas;
        /* access modifiers changed from: private */
        public static final zzm zzcfl = new zzm();
        private int zzccg;
        private String zzcdv = "";
        private int zzcdw;
        private zzn zzcdy;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzm, zza> implements zzbsn {
            private zza() {
                super(zzm.zzcfl);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzm() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcdv", "zzcdw", zzvc.zzop(), "zzcdy"};
                    return zza((zzbsl) zzcfl, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\f\u0001\u0003\t\u0002", objArr);
                case 4:
                    return zzcfl;
                case 5:
                    zzbsw<zzm> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzm.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcfl);
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
            zzbrd.zza(zzm.class, zzcfl);
        }
    }

    public static final class zzn extends zzbrd<zzn, zza> implements zzbsn {
        private static volatile zzbsw<zzn> zzcas;
        /* access modifiers changed from: private */
        public static final zzn zzcfo = new zzn();
        private int zzccg;
        private int zzcfm;
        private int zzcfn;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzn, zza> implements zzbsn {
            private zza() {
                super(zzn.zzcfo);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzn() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcfm", "zzcfn"};
                    return zza((zzbsl) zzcfo, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", objArr);
                case 4:
                    return zzcfo;
                case 5:
                    zzbsw<zzn> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzn.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcfo);
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

        public static zzbsw<zzn> zzon() {
            return (zzbsw) zzcfo.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzn.class, zzcfo);
        }
    }

    public static final class zzo extends zzbrd<zzo, zza> implements zzbsn {
        private static volatile zzbsw<zzo> zzcas;
        /* access modifiers changed from: private */
        public static final zzo zzcfs = new zzo();
        private int zzccg;
        private int zzcfp;
        private int zzcfq;
        private int zzcfr;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzo, zza> implements zzbsn {
            private zza() {
                super(zzo.zzcfs);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzo() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcfp", "zzcfq", "zzcfr"};
                    return zza((zzbsl) zzcfs, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003\u0004\u0002", objArr);
                case 4:
                    return zzcfs;
                case 5:
                    zzbsw<zzo> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzo.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcfs);
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
            zzbrd.zza(zzo.class, zzcfs);
        }
    }

    public static final class zzp extends zzbrd<zzp, zza> implements zzbsn {
        private static volatile zzbsw<zzp> zzcas;
        /* access modifiers changed from: private */
        public static final zzp zzcfv = new zzp();
        private int zzccg;
        private int zzcea = 1000;
        private zzq zzcft;
        private zzn zzcfu;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzp, zza> implements zzbsn {
            private zza() {
                super(zzp.zzcfv);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzp() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzp();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcea", zzvc.zzop(), "zzcft", "zzcfu"};
                    return zza((zzbsl) zzcfv, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\t\u0002", objArr);
                case 4:
                    return zzcfv;
                case 5:
                    zzbsw<zzp> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzp.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcfv);
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

        public static zzbsw<zzp> zzon() {
            return (zzbsw) zzcfv.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzp.class, zzcfv);
        }
    }

    public static final class zzq extends zzbrd<zzq, zza> implements zzbsn {
        private static volatile zzbsw<zzq> zzcas;
        /* access modifiers changed from: private */
        public static final zzq zzcfx = new zzq();
        private int zzccg;
        private int zzcfw;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzq, zza> implements zzbsn {
            private zza() {
                super(zzq.zzcfx);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        public enum zzb implements zzbrg {
            VIDEO_ERROR_CODE_UNSPECIFIED(0),
            OPENGL_RENDERING_FAILED(1),
            CACHE_LOAD_FAILED(2),
            ANDROID_TARGET_API_TOO_LOW(3);
            
            private static final zzbrh<zzb> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            public static zzb zzcg(int i) {
                switch (i) {
                    case 0:
                        return VIDEO_ERROR_CODE_UNSPECIFIED;
                    case 1:
                        return OPENGL_RENDERING_FAILED;
                    case 2:
                        return CACHE_LOAD_FAILED;
                    case 3:
                        return ANDROID_TARGET_API_TOO_LOW;
                    default:
                        return null;
                }
            }

            public static zzbri zzop() {
                return zzvk.zzccw;
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzvj();
            }
        }

        private zzq() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzq();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcfw", zzb.zzop()};
                    return zza((zzbsl) zzcfx, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f\u0000", objArr);
                case 4:
                    return zzcfx;
                case 5:
                    zzbsw<zzq> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzq.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcfx);
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

        public static zzbsw<zzq> zzon() {
            return (zzbsw) zzcfx.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzq.class, zzcfx);
        }
    }

    public static final class zzr extends zzbrd<zzr, zza> implements zzbsn {
        private static volatile zzbsw<zzr> zzcas;
        /* access modifiers changed from: private */
        public static final zzr zzcgg = new zzr();
        private int zzccg;
        private int zzcea = 1000;
        private zzq zzcft;
        private int zzcgd;
        private int zzcge;
        private int zzcgf;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzr, zza> implements zzbsn {
            private zza() {
                super(zzr.zzcgg);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzr() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzr();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcea", zzvc.zzop(), "zzcft", "zzcgd", "zzcge", "zzcgf"};
                    return zza((zzbsl) zzcgg, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u0004\u0004", objArr);
                case 4:
                    return zzcgg;
                case 5:
                    zzbsw<zzr> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzr.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcgg);
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

        public static zzbsw<zzr> zzon() {
            return (zzbsw) zzcgg.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzr.class, zzcgg);
        }
    }

    public static final class zzs extends zzbrd<zzs, zza> implements zzbsn {
        private static volatile zzbsw<zzs> zzcas;
        /* access modifiers changed from: private */
        public static final zzs zzcgh = new zzs();
        private int zzccg;
        private int zzcea = 1000;
        private zzq zzcft;
        private zzn zzcfu;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzs, zza> implements zzbsn {
            private zza() {
                super(zzs.zzcgh);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzs() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzs();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcea", zzvc.zzop(), "zzcft", "zzcfu"};
                    return zza((zzbsl) zzcgh, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\t\u0002", objArr);
                case 4:
                    return zzcgh;
                case 5:
                    zzbsw<zzs> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzs.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcgh);
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

        public static zzbsw<zzs> zzon() {
            return (zzbsw) zzcgh.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzs.class, zzcgh);
        }
    }

    public static final class zzt extends zzbrd<zzt, zza> implements zzbsn {
        private static volatile zzbsw<zzt> zzcas;
        /* access modifiers changed from: private */
        public static final zzt zzcgj = new zzt();
        private int zzccg;
        private int zzcea = 1000;
        private zzq zzcft;
        private int zzcgd;
        private int zzcge;
        private int zzcgf;
        private long zzcgi;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzt, zza> implements zzbsn {
            private zza() {
                super(zzt.zzcgj);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzt() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzt();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcea", zzvc.zzop(), "zzcft", "zzcgd", "zzcge", "zzcgf", "zzcgi"};
                    return zza((zzbsl) zzcgj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u0004\u0004\u0006\u0003\u0005", objArr);
                case 4:
                    return zzcgj;
                case 5:
                    zzbsw<zzt> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzt.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcgj);
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

        public static zzbsw<zzt> zzon() {
            return (zzbsw) zzcgj.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzt.class, zzcgj);
        }
    }

    public static final class zzu extends zzbrd<zzu, zza> implements zzbsn {
        private static volatile zzbsw<zzu> zzcas;
        /* access modifiers changed from: private */
        public static final zzu zzcgk = new zzu();
        private int zzccg;
        private int zzcea = 1000;
        private zzq zzcft;
        private zzn zzcfu;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzu, zza> implements zzbsn {
            private zza() {
                super(zzu.zzcgk);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzu() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzu();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcea", zzvc.zzop(), "zzcft", "zzcfu"};
                    return zza((zzbsl) zzcgk, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\t\u0002", objArr);
                case 4:
                    return zzcgk;
                case 5:
                    zzbsw<zzu> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzu.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcgk);
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

        public static zzbsw<zzu> zzon() {
            return (zzbsw) zzcgk.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzu.class, zzcgk);
        }
    }

    public static final class zzv extends zzbrd<zzv, zza> implements zzbsn {
        private static volatile zzbsw<zzv> zzcas;
        /* access modifiers changed from: private */
        public static final zzv zzcgl = new zzv();
        private int zzccg;
        private int zzcea = 1000;
        private zzq zzcft;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzv, zza> implements zzbsn {
            private zza() {
                super(zzv.zzcgl);
            }

            /* synthetic */ zza(zzux zzux) {
                this();
            }
        }

        private zzv() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzux.zzcaq[i - 1]) {
                case 1:
                    return new zzv();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zzccg", "zzcea", zzvc.zzop(), "zzcft"};
                    return zza((zzbsl) zzcgl, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001", objArr);
                case 4:
                    return zzcgl;
                case 5:
                    zzbsw<zzv> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zzv.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcgl);
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

        public static zzbsw<zzv> zzon() {
            return (zzbsw) zzcgl.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, (Object) null, (Object) null);
        }

        static {
            zzbrd.zza(zzv.class, zzcgl);
        }
    }
}
