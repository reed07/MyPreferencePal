package com.myfitnesspal.shared.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IoUtil {
    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r6v1, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r6v2 */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r6v5 */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r6v9 */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String gunzip(byte[] r6) {
        /*
            r0 = 0
            if (r6 == 0) goto L_0x0046
            int r1 = r6.length
            if (r1 != 0) goto L_0x0007
            goto L_0x0046
        L_0x0007:
            java.util.zip.GZIPInputStream r1 = new java.util.zip.GZIPInputStream     // Catch:{ Exception -> 0x003d, all -> 0x0034 }
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x003d, all -> 0x0034 }
            r2.<init>(r6)     // Catch:{ Exception -> 0x003d, all -> 0x0034 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x003d, all -> 0x0034 }
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            r6.<init>()     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x003f, all -> 0x002b }
        L_0x001a:
            int r3 = r2.length     // Catch:{ Exception -> 0x003f, all -> 0x002b }
            r4 = 0
            int r3 = r1.read(r2, r4, r3)     // Catch:{ Exception -> 0x003f, all -> 0x002b }
            if (r3 <= 0) goto L_0x0026
            r6.write(r2, r4, r3)     // Catch:{ Exception -> 0x003f, all -> 0x002b }
            goto L_0x001a
        L_0x0026:
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x003f, all -> 0x002b }
            goto L_0x003f
        L_0x002b:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L_0x0036
        L_0x0030:
            r6 = move-exception
            goto L_0x0036
        L_0x0032:
            r6 = r0
            goto L_0x003f
        L_0x0034:
            r6 = move-exception
            r1 = r0
        L_0x0036:
            close(r1)
            close(r0)
            throw r6
        L_0x003d:
            r6 = r0
            r1 = r6
        L_0x003f:
            close(r1)
            close(r6)
            return r0
        L_0x0046:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.util.IoUtil.gunzip(byte[]):java.lang.String");
    }
}
