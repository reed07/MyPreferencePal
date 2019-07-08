package com.facebook.ads.internal.w.b;

import android.content.Context;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;

public class f {
    private static final String a = "f";

    public enum a {
        UNKNOWN(0),
        UNROOTED(1),
        ROOTED(2);
        
        public final int d;

        private a(int i) {
            this.d = i;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        if (a("su") == false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.ads.internal.w.b.f.a a() {
        /*
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0031 }
            java.lang.String r1 = "/system/app/Superuser.apk"
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0031 }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x0031 }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0028
            java.lang.String r0 = android.os.Build.TAGS     // Catch:{ Throwable -> 0x0031 }
            if (r0 == 0) goto L_0x001d
            java.lang.String r3 = "test-keys"
            boolean r0 = r0.contains(r3)     // Catch:{ Throwable -> 0x0031 }
            if (r0 == 0) goto L_0x001d
            r0 = 1
            goto L_0x001e
        L_0x001d:
            r0 = 0
        L_0x001e:
            if (r0 != 0) goto L_0x0028
            java.lang.String r0 = "su"
            boolean r0 = a(r0)     // Catch:{ Throwable -> 0x0031 }
            if (r0 == 0) goto L_0x0029
        L_0x0028:
            r1 = 1
        L_0x0029:
            if (r1 == 0) goto L_0x002e
            com.facebook.ads.internal.w.b.f$a r0 = com.facebook.ads.internal.w.b.f.a.ROOTED     // Catch:{ Throwable -> 0x0031 }
            return r0
        L_0x002e:
            com.facebook.ads.internal.w.b.f$a r0 = com.facebook.ads.internal.w.b.f.a.UNROOTED     // Catch:{ Throwable -> 0x0031 }
            return r0
        L_0x0031:
            com.facebook.ads.internal.w.b.f$a r0 = com.facebook.ads.internal.w.b.f.a.UNKNOWN
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.w.b.f.a():com.facebook.ads.internal.w.b.f$a");
    }

    @Nullable
    public static String a(Context context) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Signature byteArray : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
                sb.append(i.a(MessageDigest.getInstance("SHA1").digest(CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(byteArray.toByteArray())).getPublicKey().getEncoded())));
                sb.append(";");
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean a(String str) {
        for (String file : System.getenv("PATH").split(":")) {
            File file2 = new File(file);
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles == null) {
                    continue;
                } else {
                    for (File name : listFiles) {
                        if (name.getName().equals(str)) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }
}
