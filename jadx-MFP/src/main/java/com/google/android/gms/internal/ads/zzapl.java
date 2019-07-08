package com.google.android.gms.internal.ads;

import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzapl {
    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.android.gms.internal.ads.zzazb, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v1, types: [com.google.android.gms.internal.ads.zzapo] */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.google.android.gms.internal.ads.zzapt] */
    /* JADX WARNING: type inference failed for: r7v3, types: [com.google.android.gms.internal.ads.zzapn] */
    /* JADX WARNING: type inference failed for: r7v4, types: [com.google.android.gms.internal.ads.zzapq] */
    /* JADX WARNING: type inference failed for: r0v9, types: [com.google.android.gms.internal.ads.zzapr] */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r0v10, types: [com.google.android.gms.internal.ads.zzapt] */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r0v11, types: [com.google.android.gms.internal.ads.zzapr] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v6
  assigns: [com.google.android.gms.internal.ads.zzapt, com.google.android.gms.internal.ads.zzapr]
  uses: [java.lang.Object, com.google.android.gms.internal.ads.zzazb, com.google.android.gms.internal.ads.zzapt, com.google.android.gms.internal.ads.zzapr]
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
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzazb zza(android.content.Context r8, com.google.android.gms.ads.internal.zza r9, com.google.android.gms.internal.ads.zzaxg r10, com.google.android.gms.internal.ads.zzcu r11, @android.support.annotation.Nullable com.google.android.gms.internal.ads.zzbgg r12, com.google.android.gms.internal.ads.zzalg r13, com.google.android.gms.internal.ads.zzapm r14, com.google.android.gms.internal.ads.zzaba r15) {
        /*
            com.google.android.gms.internal.ads.zzasm r2 = r10.zzehy
            boolean r4 = r2.zzdyd
            if (r4 == 0) goto L_0x0013
            com.google.android.gms.internal.ads.zzapr r7 = new com.google.android.gms.internal.ads.zzapr
            r0 = r7
            r1 = r8
            r2 = r10
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6)
            goto L_0x005c
        L_0x0013:
            boolean r4 = r2.zzckn
            if (r4 != 0) goto L_0x0040
            boolean r4 = r9 instanceof com.google.android.gms.ads.internal.zzbb
            if (r4 == 0) goto L_0x001c
            goto L_0x0040
        L_0x001c:
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastKitKat()
            if (r0 == 0) goto L_0x003a
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastLollipop()
            if (r0 != 0) goto L_0x003a
            if (r12 == 0) goto L_0x003a
            com.google.android.gms.internal.ads.zzbht r0 = r12.zzadj()
            boolean r0 = r0.zzafb()
            if (r0 == 0) goto L_0x003a
            com.google.android.gms.internal.ads.zzapq r7 = new com.google.android.gms.internal.ads.zzapq
            r7.<init>(r8, r10, r12, r14)
            goto L_0x005c
        L_0x003a:
            com.google.android.gms.internal.ads.zzapn r7 = new com.google.android.gms.internal.ads.zzapn
            r7.<init>(r8, r10, r12, r14)
            goto L_0x005c
        L_0x0040:
            boolean r2 = r2.zzckn
            if (r2 == 0) goto L_0x0057
            boolean r2 = r9 instanceof com.google.android.gms.ads.internal.zzbb
            if (r2 == 0) goto L_0x0057
            com.google.android.gms.internal.ads.zzapt r7 = new com.google.android.gms.internal.ads.zzapt
            r2 = r9
            com.google.android.gms.ads.internal.zzbb r2 = (com.google.android.gms.ads.internal.zzbb) r2
            r0 = r7
            r1 = r8
            r3 = r10
            r4 = r11
            r5 = r14
            r6 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6)
            goto L_0x005c
        L_0x0057:
            com.google.android.gms.internal.ads.zzapo r7 = new com.google.android.gms.internal.ads.zzapo
            r7.<init>(r10, r14)
        L_0x005c:
            java.lang.String r0 = "AdRenderer: "
            java.lang.Class r1 = r7.getClass()
            java.lang.String r1 = r1.getName()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0075
            java.lang.String r0 = r0.concat(r1)
            goto L_0x007b
        L_0x0075:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            r0 = r1
        L_0x007b:
            com.google.android.gms.internal.ads.zzaxz.zzdn(r0)
            r7.zzwa()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapl.zza(android.content.Context, com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.ads.zzaxg, com.google.android.gms.internal.ads.zzcu, com.google.android.gms.internal.ads.zzbgg, com.google.android.gms.internal.ads.zzalg, com.google.android.gms.internal.ads.zzapm, com.google.android.gms.internal.ads.zzaba):com.google.android.gms.internal.ads.zzazb");
    }
}
