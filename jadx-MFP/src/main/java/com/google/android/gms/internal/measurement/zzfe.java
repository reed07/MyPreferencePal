package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuo.zze;

public final class zzfe {

    public static final class zza extends zzuo<zza, C0038zza> implements zzvx {
        /* access modifiers changed from: private */
        public static final zza zzauy = new zza();
        private static volatile zzwf<zza> zznw;
        private String zzauw = "";
        private long zzaux;
        private int zznr;

        /* renamed from: com.google.android.gms.internal.measurement.zzfe$zza$zza reason: collision with other inner class name */
        public static final class C0038zza extends com.google.android.gms.internal.measurement.zzuo.zza<zza, C0038zza> implements zzvx {
            private C0038zza() {
                super(zza.zzauy);
            }

            public final C0038zza zzda(String str) {
                zzwk();
                ((zza) this.zzbyj).setName(str);
                return this;
            }

            public final C0038zza zzan(long j) {
                zzwk();
                ((zza) this.zzbyj).zzam(j);
                return this;
            }

            /* synthetic */ C0038zza(zzff zzff) {
                this();
            }
        }

        private zza() {
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            if (str != null) {
                this.zznr |= 1;
                this.zzauw = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzam(long j) {
            this.zznr |= 2;
            this.zzaux = j;
        }

        public static C0038zza zzmn() {
            return (C0038zza) ((com.google.android.gms.internal.measurement.zzuo.zza) zzauy.zza(zze.zzbyq, (Object) null, (Object) null));
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzff.zznq[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0038zza(null);
                case 3:
                    Object[] objArr = {"zznr", "zzauw", "zzaux"};
                    return zza((zzvv) zzauy, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001", objArr);
                case 4:
                    return zzauy;
                case 5:
                    zzwf<zza> zzwf = zznw;
                    if (zzwf == null) {
                        synchronized (zza.class) {
                            zzwf = zznw;
                            if (zzwf == null) {
                                zzwf = new com.google.android.gms.internal.measurement.zzuo.zzb<>(zzauy);
                                zznw = zzwf;
                            }
                        }
                    }
                    return zzwf;
                case 6:
                    return Byte.valueOf(1);
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzuo.zza(zza.class, zzauy);
        }
    }

    public static final class zzb extends zzuo<zzb, zza> implements zzvx {
        /* access modifiers changed from: private */
        public static final zzb zzavb = new zzb();
        private static volatile zzwf<zzb> zznw;
        private int zzauz = 1;
        private zzuu<zza> zzava = zzwg();
        private int zznr;

        public static final class zza extends com.google.android.gms.internal.measurement.zzuo.zza<zzb, zza> implements zzvx {
            private zza() {
                super(zzb.zzavb);
            }

            public final zza zzb(zza zza) {
                zzwk();
                ((zzb) this.zzbyj).zza(zza);
                return this;
            }

            /* synthetic */ zza(zzff zzff) {
                this();
            }
        }

        /* renamed from: com.google.android.gms.internal.measurement.zzfe$zzb$zzb reason: collision with other inner class name */
        public enum C0039zzb implements zzur {
            RADS(1),
            PROVISIONING(2);
            
            private static final zzus<C0039zzb> zzoa = null;
            private final int value;

            public final int zzc() {
                return this.value;
            }

            public static C0039zzb zzt(int i) {
                switch (i) {
                    case 1:
                        return RADS;
                    case 2:
                        return PROVISIONING;
                    default:
                        return null;
                }
            }

            public static zzut zzd() {
                return zzfh.zzoc;
            }

            private C0039zzb(int i) {
                this.value = i;
            }

            static {
                zzoa = new zzfg();
            }
        }

        private zzb() {
        }

        /* access modifiers changed from: private */
        public final void zza(zza zza2) {
            if (zza2 != null) {
                if (!this.zzava.zztz()) {
                    zzuu<zza> zzuu = this.zzava;
                    int size = zzuu.size();
                    this.zzava = zzuu.zzal(size == 0 ? 10 : size << 1);
                }
                this.zzava.add(zza2);
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzmp() {
            return (zza) ((com.google.android.gms.internal.measurement.zzuo.zza) zzavb.zza(zze.zzbyq, (Object) null, (Object) null));
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzff.zznq[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    Object[] objArr = {"zznr", "zzauz", C0039zzb.zzd(), "zzava", zza.class};
                    return zza((zzvv) zzavb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0000\u0002\u001b", objArr);
                case 4:
                    return zzavb;
                case 5:
                    zzwf<zzb> zzwf = zznw;
                    if (zzwf == null) {
                        synchronized (zzb.class) {
                            zzwf = zznw;
                            if (zzwf == null) {
                                zzwf = new com.google.android.gms.internal.measurement.zzuo.zzb<>(zzavb);
                                zznw = zzwf;
                            }
                        }
                    }
                    return zzwf;
                case 6:
                    return Byte.valueOf(1);
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzwf<zzb> zza() {
            return (zzwf) zzavb.zza(zze.zzbys, (Object) null, (Object) null);
        }

        static {
            zzuo.zza(zzb.class, zzavb);
        }
    }
}
