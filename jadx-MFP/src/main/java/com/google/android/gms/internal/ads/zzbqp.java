package com.google.android.gms.internal.ads;

final class zzbqp {
    private static final Class<?> zzfmp = zzalz();

    private static Class<?> zzalz() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzbqq zzama() {
        if (zzfmp != null) {
            try {
                return zzga("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return zzbqq.zzfmt;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x000e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.ads.zzbqq zzamb() {
        /*
            java.lang.Class<?> r0 = zzfmp
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = "loadGeneratedRegistry"
            com.google.android.gms.internal.ads.zzbqq r0 = zzga(r0)     // Catch:{ Exception -> 0x000b }
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            if (r0 != 0) goto L_0x0012
            com.google.android.gms.internal.ads.zzbqq r0 = com.google.android.gms.internal.ads.zzbqq.zzamb()
        L_0x0012:
            if (r0 != 0) goto L_0x0018
            com.google.android.gms.internal.ads.zzbqq r0 = zzama()
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbqp.zzamb():com.google.android.gms.internal.ads.zzbqq");
    }

    private static final zzbqq zzga(String str) throws Exception {
        return (zzbqq) zzfmp.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }
}
