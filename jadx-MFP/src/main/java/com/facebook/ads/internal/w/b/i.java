package com.facebook.ads.internal.w.b;

import com.google.common.base.Ascii;

public class i {
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002a */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r4) {
        /*
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x003e }
            r0.<init>(r4)     // Catch:{ FileNotFoundException -> 0x003e }
            java.lang.String r4 = "MD5"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
        L_0x000f:
            int r2 = r0.read(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
            if (r2 <= 0) goto L_0x0019
            r3 = 0
            r4.update(r1, r3, r2)     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
        L_0x0019:
            r3 = -1
            if (r2 != r3) goto L_0x000f
            byte[] r4 = r4.digest()     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
            java.lang.String r4 = b(r4)     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
            r0.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0027:
            return r4
        L_0x0028:
            r4 = move-exception
            goto L_0x003a
        L_0x002a:
            java.lang.Exception r4 = new java.lang.Exception     // Catch:{ all -> 0x0028 }
            java.lang.String r1 = "IO exception."
            r4.<init>(r1)     // Catch:{ all -> 0x0028 }
            throw r4     // Catch:{ all -> 0x0028 }
        L_0x0032:
            java.lang.Exception r4 = new java.lang.Exception     // Catch:{ all -> 0x0028 }
            java.lang.String r1 = "No such algorithm."
            r4.<init>(r1)     // Catch:{ all -> 0x0028 }
            throw r4     // Catch:{ all -> 0x0028 }
        L_0x003a:
            r0.close()     // Catch:{ IOException -> 0x003d }
        L_0x003d:
            throw r4
        L_0x003e:
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r0 = "File not found or not accessible."
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.w.b.i.a(java.io.File):java.lang.String");
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            byte b2 = (b >>> 4) & Ascii.SI;
            int i = 0;
            while (true) {
                sb.append((char) ((b2 < 0 || b2 > 9) ? (b2 - 10) + 97 : b2 + 48));
                b2 = b & Ascii.SI;
                int i2 = i + 1;
                if (i >= 1) {
                    break;
                }
                i = i2;
            }
        }
        return sb.toString();
    }

    private static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return sb.toString();
    }
}
