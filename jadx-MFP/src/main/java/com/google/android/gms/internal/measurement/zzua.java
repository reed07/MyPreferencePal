package com.google.android.gms.internal.measurement;

final class zzua {
    private static final Class<?> zzbvb = zzvn();

    private static Class<?> zzvn() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzub zzvo() {
        if (zzbvb != null) {
            try {
                return zzge("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return zzub.zzbvf;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x000e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.measurement.zzub zzvp() {
        /*
            java.lang.Class<?> r0 = zzbvb
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = "loadGeneratedRegistry"
            com.google.android.gms.internal.measurement.zzub r0 = zzge(r0)     // Catch:{ Exception -> 0x000b }
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            if (r0 != 0) goto L_0x0012
            com.google.android.gms.internal.measurement.zzub r0 = com.google.android.gms.internal.measurement.zzub.zzvp()
        L_0x0012:
            if (r0 != 0) goto L_0x0018
            com.google.android.gms.internal.measurement.zzub r0 = zzvo()
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzua.zzvp():com.google.android.gms.internal.measurement.zzub");
    }

    private static final zzub zzge(String str) throws Exception {
        return (zzub) zzbvb.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }
}
