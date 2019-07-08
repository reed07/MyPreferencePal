package com.facebook.ads.internal.h;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.v.a.p;
import com.facebook.ads.internal.w.c.c;
import com.facebook.ads.internal.w.h.a;
import com.facebook.ads.internal.w.h.b;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class d {
    private static final String a = "d";
    private static d b;
    private final Context c;

    private d(Context context) {
        this.c = context;
    }

    private Bitmap a(String str) {
        byte[] d = com.facebook.ads.internal.w.e.d.a(this.c).a(str, (p) null).d();
        return BitmapFactory.decodeByteArray(d, 0, d.length);
    }

    public static d a(Context context) {
        if (b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (d.class) {
                if (b == null) {
                    b = new d(applicationContext);
                }
            }
        }
        return b;
    }

    private static void a(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:52:0x00b5=Splitter:B:52:0x00b5, B:39:0x0078=Splitter:B:39:0x0078} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r6, android.graphics.Bitmap r7) {
        /*
            r5 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0007
            r5.a(r0)
            return
        L_0x0007:
            java.io.File r1 = new java.io.File
            android.content.Context r2 = r5.c
            java.io.File r2 = r2.getCacheDir()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int r4 = r6.hashCode()
            r3.append(r4)
            java.lang.String r4 = ".png"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.<init>(r2, r3)
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ FileNotFoundException -> 0x00b3, IOException -> 0x0089, OutOfMemoryError -> 0x0076, all -> 0x0073 }
            r2.<init>()     // Catch:{ FileNotFoundException -> 0x00b3, IOException -> 0x0089, OutOfMemoryError -> 0x0076, all -> 0x0073 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
            r4 = 100
            r7.compress(r3, r4, r2)     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
            int r7 = r2.size()     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
            r3 = 3145728(0x300000, float:4.408104E-39)
            if (r7 < r3) goto L_0x0049
            java.lang.String r7 = a     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
            java.lang.String r3 = "Bitmap size exceeds max size for storage"
            android.util.Log.d(r7, r3)     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
            a(r2)
            a(r0)
            return
        L_0x0049:
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
            r7.<init>(r1)     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
            r2.writeTo(r7)     // Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x005c, OutOfMemoryError -> 0x005a, all -> 0x0058 }
            r7.flush()     // Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x005c, OutOfMemoryError -> 0x005a, all -> 0x0058 }
            a(r2)
            goto L_0x0085
        L_0x0058:
            r6 = move-exception
            goto L_0x0064
        L_0x005a:
            r6 = move-exception
            goto L_0x0069
        L_0x005c:
            r0 = move-exception
            r1 = r7
            r7 = r0
            goto L_0x006d
        L_0x0060:
            r6 = move-exception
            goto L_0x0071
        L_0x0062:
            r6 = move-exception
            r7 = r0
        L_0x0064:
            r0 = r2
            goto L_0x00da
        L_0x0067:
            r6 = move-exception
            r7 = r0
        L_0x0069:
            r0 = r2
            goto L_0x0078
        L_0x006b:
            r7 = move-exception
            r1 = r0
        L_0x006d:
            r0 = r2
            goto L_0x008b
        L_0x006f:
            r6 = move-exception
            r7 = r0
        L_0x0071:
            r0 = r2
            goto L_0x00b5
        L_0x0073:
            r6 = move-exception
            r7 = r0
            goto L_0x00da
        L_0x0076:
            r6 = move-exception
            r7 = r0
        L_0x0078:
            r5.a(r6)     // Catch:{ all -> 0x00d9 }
            java.lang.String r1 = a     // Catch:{ all -> 0x00d9 }
            java.lang.String r2 = "Unable to write bitmap to output stream"
            android.util.Log.e(r1, r2, r6)     // Catch:{ all -> 0x00d9 }
        L_0x0082:
            a(r0)
        L_0x0085:
            a(r7)
            goto L_0x00d8
        L_0x0089:
            r7 = move-exception
            r1 = r0
        L_0x008b:
            r5.a(r7)     // Catch:{ all -> 0x00b0 }
            java.lang.String r2 = a     // Catch:{ all -> 0x00b0 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b0 }
            r3.<init>()     // Catch:{ all -> 0x00b0 }
            java.lang.String r4 = "Unable to write bitmap to file (url="
            r3.append(r4)     // Catch:{ all -> 0x00b0 }
            r3.append(r6)     // Catch:{ all -> 0x00b0 }
            java.lang.String r6 = ")."
            r3.append(r6)     // Catch:{ all -> 0x00b0 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x00b0 }
            android.util.Log.e(r2, r6, r7)     // Catch:{ all -> 0x00b0 }
            a(r0)
            a(r1)
            goto L_0x00d8
        L_0x00b0:
            r6 = move-exception
            r7 = r1
            goto L_0x00da
        L_0x00b3:
            r6 = move-exception
            r7 = r0
        L_0x00b5:
            java.lang.String r2 = a     // Catch:{ all -> 0x00d9 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d9 }
            r3.<init>()     // Catch:{ all -> 0x00d9 }
            java.lang.String r4 = "Bad output destination (file="
            r3.append(r4)     // Catch:{ all -> 0x00d9 }
            java.lang.String r1 = r1.getAbsolutePath()     // Catch:{ all -> 0x00d9 }
            r3.append(r1)     // Catch:{ all -> 0x00d9 }
            java.lang.String r1 = ")."
            r3.append(r1)     // Catch:{ all -> 0x00d9 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x00d9 }
            android.util.Log.e(r2, r1, r6)     // Catch:{ all -> 0x00d9 }
            r5.a(r6)     // Catch:{ all -> 0x00d9 }
            goto L_0x0082
        L_0x00d8:
            return
        L_0x00d9:
            r6 = move-exception
        L_0x00da:
            a(r0)
            a(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.h.d.a(java.lang.String, android.graphics.Bitmap):void");
    }

    private void a(Throwable th) {
        a.b(this.c, "image", b.S, new com.facebook.ads.internal.protocol.b(AdErrorType.IMAGE_CACHE_ERROR, AdErrorType.IMAGE_CACHE_ERROR.getDefaultErrorMessage(), th));
    }

    private boolean a(int i, int i2) {
        return i > 0 && i2 > 0 && com.facebook.ads.internal.r.a.l(this.c);
    }

    @Nullable
    private Bitmap b(String str, int i, int i2) {
        try {
            Bitmap a2 = a(i, i2) ? c.a(str.substring(7), i, i2) : BitmapFactory.decodeStream(new FileInputStream(str.substring(7)), null, null);
            a(str, a2);
            return a2;
        } catch (IOException e) {
            String str2 = a;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to copy local image into cache (url=");
            sb.append(str);
            sb.append(").");
            Log.e(str2, sb.toString(), e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0052  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0045=Splitter:B:24:0x0045, B:17:0x003a=Splitter:B:17:0x003a} */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap c(java.lang.String r5, int r6, int r7) {
        /*
            r4 = this;
            java.lang.String r0 = "asset:///"
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L_0x0056
            r0 = 0
            android.content.Context r1 = r4.c     // Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
            r2 = 9
            int r3 = r5.length()     // Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
            java.lang.String r2 = r5.substring(r2, r3)     // Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
            boolean r2 = r4.a(r6, r7)     // Catch:{ IOException -> 0x0034, OutOfMemoryError -> 0x0032 }
            if (r2 == 0) goto L_0x0028
            android.graphics.Bitmap r6 = com.facebook.ads.internal.w.c.c.a(r1, r6, r7)     // Catch:{ IOException -> 0x0034, OutOfMemoryError -> 0x0032 }
            goto L_0x002c
        L_0x0028:
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ IOException -> 0x0034, OutOfMemoryError -> 0x0032 }
        L_0x002c:
            if (r1 == 0) goto L_0x0082
            a(r1)
            goto L_0x0082
        L_0x0032:
            r5 = move-exception
            goto L_0x003a
        L_0x0034:
            r5 = move-exception
            goto L_0x0045
        L_0x0036:
            r5 = move-exception
            goto L_0x0050
        L_0x0038:
            r5 = move-exception
            r1 = r0
        L_0x003a:
            r4.a(r5)     // Catch:{ all -> 0x004e }
            if (r1 == 0) goto L_0x0042
            a(r1)
        L_0x0042:
            return r0
        L_0x0043:
            r5 = move-exception
            r1 = r0
        L_0x0045:
            r4.a(r5)     // Catch:{ all -> 0x004e }
            if (r1 == 0) goto L_0x004d
            a(r1)
        L_0x004d:
            return r0
        L_0x004e:
            r5 = move-exception
            r0 = r1
        L_0x0050:
            if (r0 == 0) goto L_0x0055
            a(r0)
        L_0x0055:
            throw r5
        L_0x0056:
            boolean r0 = r4.a(r6, r7)
            if (r0 == 0) goto L_0x007e
            java.net.URL r0 = new java.net.URL     // Catch:{ IOException -> 0x007a }
            r0.<init>(r5)     // Catch:{ IOException -> 0x007a }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ IOException -> 0x007a }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x007a }
            r1 = 1
            r0.setDoInput(r1)     // Catch:{ IOException -> 0x007a }
            r0.connect()     // Catch:{ IOException -> 0x007a }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x007a }
            android.graphics.Bitmap r6 = com.facebook.ads.internal.w.c.c.a(r0, r6, r7)     // Catch:{ IOException -> 0x007a }
            a(r0)     // Catch:{ IOException -> 0x007a }
            goto L_0x0082
        L_0x007a:
            r6 = move-exception
            r4.a(r6)
        L_0x007e:
            android.graphics.Bitmap r6 = r4.a(r5)
        L_0x0082:
            r4.a(r5, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.h.d.c(java.lang.String, int, int):android.graphics.Bitmap");
    }

    @Nullable
    public Bitmap a(String str, int i, int i2) {
        File cacheDir = this.c.getCacheDir();
        StringBuilder sb = new StringBuilder();
        sb.append(str.hashCode());
        sb.append(".png");
        File file = new File(cacheDir, sb.toString());
        if (!file.exists()) {
            return str.startsWith("file://") ? b(str, i, i2) : c(str, i, i2);
        }
        return a(i, i2) ? c.a(file.getAbsolutePath(), i, i2) : BitmapFactory.decodeFile(file.getAbsolutePath());
    }
}
