package com.google.android.gms.internal.icing;

public final class zzgs {

    public static final class zza extends zzdj<zza, C0036zza> implements zzes {
        private static volatile zzfa<zza> zzbc;
        /* access modifiers changed from: private */
        public static final zza zzqf = new zza();
        private int zzbd;
        private boolean zzqb;
        private int zzqc;
        private String zzqd = "";
        private zzdq<zzb> zzqe = zzbt();

        /* renamed from: com.google.android.gms.internal.icing.zzgs$zza$zza reason: collision with other inner class name */
        public static final class C0036zza extends com.google.android.gms.internal.icing.zzdj.zza<zza, C0036zza> implements zzes {
            private C0036zza() {
                super(zza.zzqf);
            }

            /* synthetic */ C0036zza(zzgt zzgt) {
                this();
            }
        }

        private zza() {
        }

        public final boolean zzdz() {
            return this.zzqb;
        }

        public final int getScore() {
            return this.zzqc;
        }

        public final String zzea() {
            return this.zzqd;
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zza>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zza>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zza>, com.google.android.gms.internal.icing.zzdj$zzb] */
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
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.icing.zzgt.zzaz
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0062;
                    case 2: goto L_0x005c;
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
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zza> r2 = zzbc
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.zzgs$zza> r3 = com.google.android.gms.internal.icing.zzgs.zza.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zza> r2 = zzbc     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.zzdj$zzb r2 = new com.google.android.gms.internal.icing.zzdj$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.zzgs$zza r4 = zzqf     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.icing.zzgs$zza r2 = zzqf
                return r2
            L_0x0033:
                r2 = 6
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbd"
                r2[r3] = r0
                java.lang.String r3 = "zzqb"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzqc"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzqd"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zzqe"
                r2[r3] = r4
                r3 = 5
                java.lang.Class<com.google.android.gms.internal.icing.zzgs$zzb> r4 = com.google.android.gms.internal.icing.zzgs.zzb.class
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u0007\u0000\u0002\u0004\u0001\u0003\b\u0002\u0004\u001b"
                com.google.android.gms.internal.icing.zzgs$zza r4 = zzqf
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x005c:
                com.google.android.gms.internal.icing.zzgs$zza$zza r2 = new com.google.android.gms.internal.icing.zzgs$zza$zza
                r2.<init>(r3)
                return r2
            L_0x0062:
                com.google.android.gms.internal.icing.zzgs$zza r2 = new com.google.android.gms.internal.icing.zzgs$zza
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzgs.zza.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public static zza zzeb() {
            return zzqf;
        }

        static {
            zzdj.zza(zza.class, zzqf);
        }
    }

    public static final class zzb extends zzdj<zzb, zza> implements zzes {
        private static volatile zzfa<zzb> zzbc;
        /* access modifiers changed from: private */
        public static final zzb zzqm = new zzb();
        private int zzbd;
        private String zzbi = "";
        private zzdm zzqg = zzbs();
        private zzdp zzqh = zzbq();
        private zzdq<String> zzqi = zzdj.zzbt();
        private zzdq<zzc> zzqj = zzbt();
        private zzce zzqk = zzce.zzfx;
        private zzdn zzql = zzbr();

        public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zzb, zza> implements zzes {
            private zza() {
                super(zzb.zzqm);
            }

            /* synthetic */ zza(zzgt zzgt) {
                this();
            }
        }

        private zzb() {
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzb>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzb>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzb>, com.google.android.gms.internal.icing.zzdj$zzb] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
  assigns: []
  uses: []
  mth insns count: 54
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
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.icing.zzgt.zzaz
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0073;
                    case 2: goto L_0x006d;
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
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzb> r2 = zzbc
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.zzgs$zzb> r3 = com.google.android.gms.internal.icing.zzgs.zzb.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzb> r2 = zzbc     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.zzdj$zzb r2 = new com.google.android.gms.internal.icing.zzdj$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.zzgs$zzb r4 = zzqm     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.icing.zzgs$zzb r2 = zzqm
                return r2
            L_0x0033:
                r2 = 9
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbd"
                r2[r3] = r0
                java.lang.String r3 = "zzbi"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzqg"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzqh"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zzqi"
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzqj"
                r2[r3] = r4
                r3 = 6
                java.lang.Class<com.google.android.gms.internal.icing.zzgs$zzc> r4 = com.google.android.gms.internal.icing.zzgs.zzc.class
                r2[r3] = r4
                r3 = 7
                java.lang.String r4 = "zzqk"
                r2[r3] = r4
                r3 = 8
                java.lang.String r4 = "zzql"
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0005\u0000\u0001\b\u0000\u0002\u0019\u0003\u0014\u0004\u001a\u0005\u001b\u0006\n\u0001\u0007\u0012"
                com.google.android.gms.internal.icing.zzgs$zzb r4 = zzqm
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x006d:
                com.google.android.gms.internal.icing.zzgs$zzb$zza r2 = new com.google.android.gms.internal.icing.zzgs$zzb$zza
                r2.<init>(r3)
                return r2
            L_0x0073:
                com.google.android.gms.internal.icing.zzgs$zzb r2 = new com.google.android.gms.internal.icing.zzgs$zzb
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzgs.zzb.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzdj.zza(zzb.class, zzqm);
        }
    }

    public static final class zzc extends zzdj<zzc, zza> implements zzes {
        private static volatile zzfa<zzc> zzbc;
        /* access modifiers changed from: private */
        public static final zzc zzqp = new zzc();
        private int zzbd;
        private String zzbl = "";
        private zzdq<zzb> zzqe = zzbt();
        private String zzqn = "";
        private zza zzqo;

        public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zzc, zza> implements zzes {
            private zza() {
                super(zzc.zzqp);
            }

            /* synthetic */ zza(zzgt zzgt) {
                this();
            }
        }

        private zzc() {
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzc>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzc>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzc>, com.google.android.gms.internal.icing.zzdj$zzb] */
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
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.icing.zzgt.zzaz
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0062;
                    case 2: goto L_0x005c;
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
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzc> r2 = zzbc
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.zzgs$zzc> r3 = com.google.android.gms.internal.icing.zzgs.zzc.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.zzfa<com.google.android.gms.internal.icing.zzgs$zzc> r2 = zzbc     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.zzdj$zzb r2 = new com.google.android.gms.internal.icing.zzdj$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.zzgs$zzc r4 = zzqp     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.icing.zzgs$zzc r2 = zzqp
                return r2
            L_0x0033:
                r2 = 6
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbd"
                r2[r3] = r0
                java.lang.String r3 = "zzbl"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzqn"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzqe"
                r2[r3] = r4
                r3 = 4
                java.lang.Class<com.google.android.gms.internal.icing.zzgs$zzb> r4 = com.google.android.gms.internal.icing.zzgs.zzb.class
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzqo"
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u001b\u0004\t\u0002"
                com.google.android.gms.internal.icing.zzgs$zzc r4 = zzqp
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x005c:
                com.google.android.gms.internal.icing.zzgs$zzc$zza r2 = new com.google.android.gms.internal.icing.zzgs$zzc$zza
                r2.<init>(r3)
                return r2
            L_0x0062:
                com.google.android.gms.internal.icing.zzgs$zzc r2 = new com.google.android.gms.internal.icing.zzgs$zzc
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzgs.zzc.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzdj.zza(zzc.class, zzqp);
        }
    }
}
