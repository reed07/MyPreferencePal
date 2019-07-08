package com.google.android.gms.internal.icing;

public final class zzak {

    public static final class zza extends zzdj<zza, C0034zza> implements zzes {
        /* access modifiers changed from: private */
        public static final zza zzbb = new zza();
        private static volatile zzfa<zza> zzbc;
        private zzdq<zzb> zzba = zzbt();

        /* renamed from: com.google.android.gms.internal.icing.zzak$zza$zza reason: collision with other inner class name */
        public static final class C0034zza extends com.google.android.gms.internal.icing.zzdj.zza<zza, C0034zza> implements zzes {
            private C0034zza() {
                super(zza.zzbb);
            }

            public final C0034zza zzb(Iterable<? extends zzb> iterable) {
                zzbw();
                ((zza) this.zzjx).zza(iterable);
                return this;
            }

            /* synthetic */ C0034zza(zzal zzal) {
                this();
            }
        }

        public static final class zzb extends zzdj<zzb, C0035zza> implements zzes {
            private static volatile zzfa<zzb> zzbc;
            /* access modifiers changed from: private */
            public static final zzb zzbh = new zzb();
            private int zzbd;
            private String zzbe = "";
            private String zzbf = "";
            private int zzbg;

            /* renamed from: com.google.android.gms.internal.icing.zzak$zza$zzb$zza reason: collision with other inner class name */
            public static final class C0035zza extends com.google.android.gms.internal.icing.zzdj.zza<zzb, C0035zza> implements zzes {
                private C0035zza() {
                    super(zzb.zzbh);
                }

                public final C0035zza zzg(String str) {
                    zzbw();
                    ((zzb) this.zzjx).zze(str);
                    return this;
                }

                public final C0035zza zzh(String str) {
                    zzbw();
                    ((zzb) this.zzjx).zzf(str);
                    return this;
                }

                public final C0035zza zze(int i) {
                    zzbw();
                    ((zzb) this.zzjx).zzd(i);
                    return this;
                }

                /* synthetic */ C0035zza(zzal zzal) {
                    this();
                }
            }

            private zzb() {
            }

            /* access modifiers changed from: private */
            public final void zze(String str) {
                if (str != null) {
                    this.zzbd |= 1;
                    this.zzbe = str;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            public final void zzf(String str) {
                if (str != null) {
                    this.zzbd |= 2;
                    this.zzbf = str;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            public final void zzd(int i) {
                this.zzbd |= 4;
                this.zzbg = i;
            }

            public static C0035zza zzh() {
                return (C0035zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbh.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, (Object) null, (Object) null));
            }

            /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza$zzb>] */
            /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
            /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza$zzb>] */
            /* JADX WARNING: type inference failed for: r2v13 */
            /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdj$zzb, com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza$zzb>] */
            /* JADX WARNING: type inference failed for: r2v17 */
            /* JADX WARNING: type inference failed for: r2v18 */
            /* access modifiers changed from: protected */
            /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
  assigns: []
  uses: []
  mth insns count: 44
            	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
            	at jadx.core.ProcessClass.process(ProcessClass.java:30)
            	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
            	at jadx.core.ProcessClass.process(ProcessClass.java:35)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
             */
            /* JADX WARNING: Unknown variable types count: 3 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
                /*
                    r1 = this;
                    int[] r3 = com.google.android.gms.internal.icing.zzal.zzaz
                    r4 = 1
                    int r2 = r2 - r4
                    r2 = r3[r2]
                    r3 = 0
                    switch(r2) {
                        case 1: goto L_0x0058;
                        case 2: goto L_0x0052;
                        case 3: goto L_0x0033;
                        case 4: goto L_0x0030;
                        case 5: goto L_0x0016;
                        case 6: goto L_0x0011;
                        case 7: goto L_0x0010;
                        default: goto L_0x000a;
                    }
                L_0x000a:
                    java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                    r2.<init>()
                    throw r2
                L_0x0010:
                    return r3
                L_0x0011:
                    java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                    return r2
                L_0x0016:
                    com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza$zzb> r2 = zzbc
                    if (r2 != 0) goto L_0x002f
                    java.lang.Class<com.google.android.gms.internal.icing.zzak$zza$zzb> r3 = com.google.android.gms.internal.icing.zzak.zza.zzb.class
                    monitor-enter(r3)
                    com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza$zzb> r2 = zzbc     // Catch:{ all -> 0x002c }
                    if (r2 != 0) goto L_0x002a
                    com.google.android.gms.internal.icing.zzdj$zzb r2 = new com.google.android.gms.internal.icing.zzdj$zzb     // Catch:{ all -> 0x002c }
                    com.google.android.gms.internal.icing.zzak$zza$zzb r4 = zzbh     // Catch:{ all -> 0x002c }
                    r2.<init>(r4)     // Catch:{ all -> 0x002c }
                    zzbc = r2     // Catch:{ all -> 0x002c }
                L_0x002a:
                    monitor-exit(r3)     // Catch:{ all -> 0x002c }
                    goto L_0x002f
                L_0x002c:
                    r2 = move-exception
                    monitor-exit(r3)     // Catch:{ all -> 0x002c }
                    throw r2
                L_0x002f:
                    return r2
                L_0x0030:
                    com.google.android.gms.internal.icing.zzak$zza$zzb r2 = zzbh
                    return r2
                L_0x0033:
                    r2 = 4
                    java.lang.Object[] r2 = new java.lang.Object[r2]
                    r3 = 0
                    java.lang.String r0 = "zzbd"
                    r2[r3] = r0
                    java.lang.String r3 = "zzbe"
                    r2[r4] = r3
                    r3 = 2
                    java.lang.String r4 = "zzbf"
                    r2[r3] = r4
                    r3 = 3
                    java.lang.String r4 = "zzbg"
                    r2[r3] = r4
                    java.lang.String r3 = "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0004\u0002"
                    com.google.android.gms.internal.icing.zzak$zza$zzb r4 = zzbh
                    java.lang.Object r2 = zza(r4, r3, r2)
                    return r2
                L_0x0052:
                    com.google.android.gms.internal.icing.zzak$zza$zzb$zza r2 = new com.google.android.gms.internal.icing.zzak$zza$zzb$zza
                    r2.<init>(r3)
                    return r2
                L_0x0058:
                    com.google.android.gms.internal.icing.zzak$zza$zzb r2 = new com.google.android.gms.internal.icing.zzak$zza$zzb
                    r2.<init>()
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzak.zza.zzb.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
            }

            static {
                zzdj.zza(zzb.class, zzbh);
            }
        }

        private zza() {
        }

        /* access modifiers changed from: private */
        public final void zza(Iterable<? extends zzb> iterable) {
            if (!this.zzba.zzai()) {
                zzdq<zzb> zzdq = this.zzba;
                int size = zzdq.size();
                this.zzba = zzdq.zzj(size == 0 ? 10 : size << 1);
            }
            zzbx.zza(iterable, this.zzba);
        }

        public static C0034zza zzf() {
            return (C0034zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbb.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, (Object) null, (Object) null));
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdj$zzb, com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
  assigns: []
  uses: []
  mth insns count: 40
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.icing.zzal.zzaz
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x004e;
                    case 2: goto L_0x0048;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza> r2 = zzbc
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.zzak$zza> r3 = com.google.android.gms.internal.icing.zzak.zza.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zza> r2 = zzbc     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.zzdj$zzb r2 = new com.google.android.gms.internal.icing.zzdj$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.zzak$zza r4 = zzbb     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbc = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.icing.zzak$zza r2 = zzbb
                return r2
            L_0x0033:
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzba"
                r2[r3] = r0
                java.lang.Class<com.google.android.gms.internal.icing.zzak$zza$zzb> r3 = com.google.android.gms.internal.icing.zzak.zza.zzb.class
                r2[r4] = r3
                java.lang.String r3 = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b"
                com.google.android.gms.internal.icing.zzak$zza r4 = zzbb
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x0048:
                com.google.android.gms.internal.icing.zzak$zza$zza r2 = new com.google.android.gms.internal.icing.zzak$zza$zza
                r2.<init>(r3)
                return r2
            L_0x004e:
                com.google.android.gms.internal.icing.zzak$zza r2 = new com.google.android.gms.internal.icing.zzak$zza
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzak.zza.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzdj.zza(zza.class, zzbb);
        }
    }

    public static final class zzb extends zzdj<zzb, zza> implements zzes {
        private static volatile zzfa<zzb> zzbc;
        /* access modifiers changed from: private */
        public static final zzb zzbk = new zzb();
        private int zzbd;
        private String zzbi = "";
        private zzd zzbj;

        public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zzb, zza> implements zzes {
            private zza() {
                super(zzb.zzbk);
            }

            public final zza zzi(String str) {
                zzbw();
                ((zzb) this.zzjx).setName(str);
                return this;
            }

            public final zza zzb(zzd zzd) {
                zzbw();
                ((zzb) this.zzjx).zza(zzd);
                return this;
            }

            /* synthetic */ zza(zzal zzal) {
                this();
            }
        }

        private zzb() {
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            if (str != null) {
                this.zzbd |= 1;
                this.zzbi = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzd zzd) {
            if (zzd != null) {
                this.zzbj = zzd;
                this.zzbd |= 2;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzj() {
            return (zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbk.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, (Object) null, (Object) null));
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzb>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzb>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzb>, com.google.android.gms.internal.icing.zzdj$zzb] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
  assigns: []
  uses: []
  mth insns count: 42
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.icing.zzal.zzaz
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0053;
                    case 2: goto L_0x004d;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzb> r2 = zzbc
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.zzak$zzb> r3 = com.google.android.gms.internal.icing.zzak.zzb.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzb> r2 = zzbc     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.zzdj$zzb r2 = new com.google.android.gms.internal.icing.zzdj$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.zzak$zzb r4 = zzbk     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbc = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.icing.zzak$zzb r2 = zzbk
                return r2
            L_0x0033:
                r2 = 3
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbd"
                r2[r3] = r0
                java.lang.String r3 = "zzbi"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzbj"
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\t\u0001"
                com.google.android.gms.internal.icing.zzak$zzb r4 = zzbk
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x004d:
                com.google.android.gms.internal.icing.zzak$zzb$zza r2 = new com.google.android.gms.internal.icing.zzak$zzb$zza
                r2.<init>(r3)
                return r2
            L_0x0053:
                com.google.android.gms.internal.icing.zzak$zzb r2 = new com.google.android.gms.internal.icing.zzak$zzb
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzak.zzb.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzdj.zza(zzb.class, zzbk);
        }
    }

    public static final class zzc extends zzdj<zzc, zza> implements zzes {
        private static volatile zzfa<zzc> zzbc;
        /* access modifiers changed from: private */
        public static final zzc zzbn = new zzc();
        private int zzbd;
        private String zzbl = "";
        private zzdq<zzb> zzbm = zzbt();

        public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zzc, zza> implements zzes {
            private zza() {
                super(zzc.zzbn);
            }

            public final zza zzk(String str) {
                zzbw();
                ((zzc) this.zzjx).zzj(str);
                return this;
            }

            public final zza zzb(zzb zzb) {
                zzbw();
                ((zzc) this.zzjx).zza(zzb);
                return this;
            }

            /* synthetic */ zza(zzal zzal) {
                this();
            }
        }

        private zzc() {
        }

        /* access modifiers changed from: private */
        public final void zzj(String str) {
            if (str != null) {
                this.zzbd |= 1;
                this.zzbl = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzb zzb) {
            if (zzb != null) {
                if (!this.zzbm.zzai()) {
                    zzdq<zzb> zzdq = this.zzbm;
                    int size = zzdq.size();
                    this.zzbm = zzdq.zzj(size == 0 ? 10 : size << 1);
                }
                this.zzbm.add(zzb);
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzl() {
            return (zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbn.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, (Object) null, (Object) null));
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzc>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzc>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzc>, com.google.android.gms.internal.icing.zzdj$zzb] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
  assigns: []
  uses: []
  mth insns count: 44
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.icing.zzal.zzaz
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0058;
                    case 2: goto L_0x0052;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzc> r2 = zzbc
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.zzak$zzc> r3 = com.google.android.gms.internal.icing.zzak.zzc.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzc> r2 = zzbc     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.zzdj$zzb r2 = new com.google.android.gms.internal.icing.zzdj$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.zzak$zzc r4 = zzbn     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbc = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.icing.zzak$zzc r2 = zzbn
                return r2
            L_0x0033:
                r2 = 4
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbd"
                r2[r3] = r0
                java.lang.String r3 = "zzbl"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzbm"
                r2[r3] = r4
                r3 = 3
                java.lang.Class<com.google.android.gms.internal.icing.zzak$zzb> r4 = com.google.android.gms.internal.icing.zzak.zzb.class
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b"
                com.google.android.gms.internal.icing.zzak$zzc r4 = zzbn
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x0052:
                com.google.android.gms.internal.icing.zzak$zzc$zza r2 = new com.google.android.gms.internal.icing.zzak$zzc$zza
                r2.<init>(r3)
                return r2
            L_0x0058:
                com.google.android.gms.internal.icing.zzak$zzc r2 = new com.google.android.gms.internal.icing.zzak$zzc
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzak.zzc.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzdj.zza(zzc.class, zzbn);
        }
    }

    public static final class zzd extends zzdj<zzd, zza> implements zzes {
        private static volatile zzfa<zzd> zzbc;
        /* access modifiers changed from: private */
        public static final zzd zzbt = new zzd();
        private int zzbd;
        private boolean zzbo;
        private String zzbp = "";
        private long zzbq;
        private double zzbr;
        private zzc zzbs;

        public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zzd, zza> implements zzes {
            private zza() {
                super(zzd.zzbt);
            }

            public final zza zzf(boolean z) {
                zzbw();
                ((zzd) this.zzjx).zze(z);
                return this;
            }

            public final zza zzm(String str) {
                zzbw();
                ((zzd) this.zzjx).zzl(str);
                return this;
            }

            public final zza zzb(zzc zzc) {
                zzbw();
                ((zzd) this.zzjx).zza(zzc);
                return this;
            }

            /* synthetic */ zza(zzal zzal) {
                this();
            }
        }

        private zzd() {
        }

        /* access modifiers changed from: private */
        public final void zze(boolean z) {
            this.zzbd |= 1;
            this.zzbo = z;
        }

        /* access modifiers changed from: private */
        public final void zzl(String str) {
            if (str != null) {
                this.zzbd |= 2;
                this.zzbp = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzc zzc) {
            if (zzc != null) {
                this.zzbs = zzc;
                this.zzbd |= 16;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzn() {
            return (zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbt.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, (Object) null, (Object) null));
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzd>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzd>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzd>, com.google.android.gms.internal.icing.zzdj$zzb] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
  assigns: []
  uses: []
  mth insns count: 48
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.icing.zzal.zzaz
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0068;
                    case 2: goto L_0x0062;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzd> r2 = zzbc
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.zzak$zzd> r3 = com.google.android.gms.internal.icing.zzak.zzd.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzak$zzd> r2 = zzbc     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.zzdj$zzb r2 = new com.google.android.gms.internal.icing.zzdj$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.zzak$zzd r4 = zzbt     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbc = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.icing.zzak$zzd r2 = zzbt
                return r2
            L_0x0033:
                r2 = 6
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbd"
                r2[r3] = r0
                java.lang.String r3 = "zzbo"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzbp"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzbq"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zzbr"
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzbs"
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u0007\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0000\u0003\u0005\t\u0004"
                com.google.android.gms.internal.icing.zzak$zzd r4 = zzbt
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x0062:
                com.google.android.gms.internal.icing.zzak$zzd$zza r2 = new com.google.android.gms.internal.icing.zzak$zzd$zza
                r2.<init>(r3)
                return r2
            L_0x0068:
                com.google.android.gms.internal.icing.zzak$zzd r2 = new com.google.android.gms.internal.icing.zzak$zzd
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzak.zzd.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzdj.zza(zzd.class, zzbt);
        }
    }
}
