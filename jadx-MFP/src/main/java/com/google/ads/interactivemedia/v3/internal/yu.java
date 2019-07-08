package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Writer;

/* compiled from: IMASDK */
public class yu {
    public final ju[] a;
    public final long[] b;
    public final String c;
    public final String d;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        throw new com.google.ads.interactivemedia.v3.internal.xa((java.lang.Throwable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        throw new com.google.ads.interactivemedia.v3.internal.xh((java.lang.Throwable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002a, code lost:
        return com.google.ads.interactivemedia.v3.internal.xb.a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        throw new com.google.ads.interactivemedia.v3.internal.xh((java.lang.Throwable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        throw new com.google.ads.interactivemedia.v3.internal.xh((java.lang.Throwable) r2);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0016 A[ExcHandler: IOException (r2v5 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001d A[ExcHandler: aby (r2v4 'e' com.google.ads.interactivemedia.v3.internal.aby A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000f A[ExcHandler: NumberFormatException (r2v6 'e' java.lang.NumberFormatException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.ads.interactivemedia.v3.internal.wz a(com.google.ads.interactivemedia.v3.internal.abu r2) throws com.google.ads.interactivemedia.v3.internal.xd {
        /*
            r2.f()     // Catch:{ EOFException -> 0x0024, aby -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
            r0 = 0
            com.google.ads.interactivemedia.v3.internal.xj<com.google.ads.interactivemedia.v3.internal.wz> r1 = com.google.ads.interactivemedia.v3.internal.aac.C     // Catch:{ EOFException -> 0x000d, aby -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
            java.lang.Object r2 = r1.read(r2)     // Catch:{ EOFException -> 0x000d, aby -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
            com.google.ads.interactivemedia.v3.internal.wz r2 = (com.google.ads.interactivemedia.v3.internal.wz) r2     // Catch:{ EOFException -> 0x000d, aby -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
            return r2
        L_0x000d:
            r2 = move-exception
            goto L_0x0026
        L_0x000f:
            r2 = move-exception
            com.google.ads.interactivemedia.v3.internal.xh r0 = new com.google.ads.interactivemedia.v3.internal.xh
            r0.<init>(r2)
            throw r0
        L_0x0016:
            r2 = move-exception
            com.google.ads.interactivemedia.v3.internal.xa r0 = new com.google.ads.interactivemedia.v3.internal.xa
            r0.<init>(r2)
            throw r0
        L_0x001d:
            r2 = move-exception
            com.google.ads.interactivemedia.v3.internal.xh r0 = new com.google.ads.interactivemedia.v3.internal.xh
            r0.<init>(r2)
            throw r0
        L_0x0024:
            r2 = move-exception
            r0 = 1
        L_0x0026:
            if (r0 == 0) goto L_0x002b
            com.google.ads.interactivemedia.v3.internal.xb r2 = com.google.ads.interactivemedia.v3.internal.xb.a
            return r2
        L_0x002b:
            com.google.ads.interactivemedia.v3.internal.xh r0 = new com.google.ads.interactivemedia.v3.internal.xh
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.yu.a(com.google.ads.interactivemedia.v3.internal.abu):com.google.ads.interactivemedia.v3.internal.wz");
    }

    public static void a(wz wzVar, abx abx) throws IOException {
        aac.C.write(abx, wzVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new yv(appendable);
    }

    public yu(String str, String str2, long j, long[] jArr, ju[] juVarArr) {
        this.c = str;
        this.d = str2;
        this.b = jArr;
        this.a = juVarArr;
    }

    public String a() {
        String str = this.c;
        String str2 = this.d;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }
}
