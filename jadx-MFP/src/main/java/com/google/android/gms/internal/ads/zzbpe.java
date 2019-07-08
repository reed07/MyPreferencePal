package com.google.android.gms.internal.ads;

import java.io.PrintWriter;

public final class zzbpe {
    private static final zzbpf zzfkn;
    private static final int zzfko;

    static final class zza extends zzbpf {
        zza() {
        }

        public final void zze(Throwable th) {
            th.printStackTrace();
        }

        public final void zza(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    public static void zze(Throwable th) {
        zzfkn.zze(th);
    }

    public static void zza(Throwable th, PrintWriter printWriter) {
        zzfkn.zza(th, printWriter);
    }

    private static Integer zzake() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    static {
        /*
            r0 = 1
            java.lang.Integer r1 = zzake()     // Catch:{ Throwable -> 0x002c }
            if (r1 == 0) goto L_0x0015
            int r2 = r1.intValue()     // Catch:{ Throwable -> 0x002a }
            r3 = 19
            if (r2 < r3) goto L_0x0015
            com.google.android.gms.internal.ads.zzbpj r2 = new com.google.android.gms.internal.ads.zzbpj     // Catch:{ Throwable -> 0x002a }
            r2.<init>()     // Catch:{ Throwable -> 0x002a }
            goto L_0x0063
        L_0x0015:
            java.lang.String r2 = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic"
            boolean r2 = java.lang.Boolean.getBoolean(r2)     // Catch:{ Throwable -> 0x002a }
            r2 = r2 ^ r0
            if (r2 == 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzbpi r2 = new com.google.android.gms.internal.ads.zzbpi     // Catch:{ Throwable -> 0x002a }
            r2.<init>()     // Catch:{ Throwable -> 0x002a }
            goto L_0x0063
        L_0x0024:
            com.google.android.gms.internal.ads.zzbpe$zza r2 = new com.google.android.gms.internal.ads.zzbpe$zza     // Catch:{ Throwable -> 0x002a }
            r2.<init>()     // Catch:{ Throwable -> 0x002a }
            goto L_0x0063
        L_0x002a:
            r2 = move-exception
            goto L_0x002e
        L_0x002c:
            r2 = move-exception
            r1 = 0
        L_0x002e:
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.Class<com.google.android.gms.internal.ads.zzbpe$zza> r4 = com.google.android.gms.internal.ads.zzbpe.zza.class
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = java.lang.String.valueOf(r4)
            int r5 = r5.length()
            int r5 = r5 + 132
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "An error has occured when initializing the try-with-resources desuguring strategy. The default strategy "
            r6.append(r5)
            r6.append(r4)
            java.lang.String r4 = "will be used. The error is: "
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r3.println(r4)
            java.io.PrintStream r3 = java.lang.System.err
            r2.printStackTrace(r3)
            com.google.android.gms.internal.ads.zzbpe$zza r2 = new com.google.android.gms.internal.ads.zzbpe$zza
            r2.<init>()
        L_0x0063:
            zzfkn = r2
            if (r1 != 0) goto L_0x0068
            goto L_0x006c
        L_0x0068:
            int r0 = r1.intValue()
        L_0x006c:
            zzfko = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbpe.<clinit>():void");
    }
}
