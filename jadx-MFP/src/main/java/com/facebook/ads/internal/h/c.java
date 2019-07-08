package com.facebook.ads.internal.h;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class c {
    /* access modifiers changed from: private */
    public static final String a = "c";
    private static c b;
    private final Future<a> c;

    private static class a {
        private static final Map<String, File> a = new HashMap();
        private final Context b;

        a(Context context) {
            this.b = context;
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public String a(String str) {
            File file = (File) a.get(str);
            if (file == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("file://");
            sb.append(file.getPath());
            return sb.toString();
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0086 A[SYNTHETIC, Splitter:B:29:0x0086] */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0097 A[SYNTHETIC, Splitter:B:35:0x0097] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean b(java.lang.String r8) {
            /*
                r7 = this;
                r0 = 0
                android.content.Context r1 = r7.b     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                java.io.File r1 = com.facebook.ads.internal.v.b.o.a(r1)     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                com.facebook.ads.internal.v.b.a.f r2 = new com.facebook.ads.internal.v.b.a.f     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r2.<init>()     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                java.lang.String r2 = r2.a(r8)     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r3.<init>(r1, r2)     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                com.facebook.ads.internal.v.b.a.b r1 = new com.facebook.ads.internal.v.b.a.b     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                com.facebook.ads.internal.v.b.a.g r2 = new com.facebook.ads.internal.v.b.a.g     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r4 = 67108864(0x4000000, double:3.31561842E-316)
                r2.<init>(r4)     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r1.<init>(r3, r2)     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                boolean r2 = r1.d()     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r4 = 1
                if (r2 == 0) goto L_0x0032
                java.util.Map<java.lang.String, java.io.File> r2 = a     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r2.put(r8, r3)     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r1.b()     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                return r4
            L_0x0032:
                java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r2.<init>(r8)     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                java.net.URLConnection r2 = r2.openConnection()     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r2.connect()     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                java.io.InputStream r2 = r2.getInputStream()     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r5.<init>(r2)     // Catch:{ IOException -> 0x0079, l -> 0x0077 }
                r0 = 8192(0x2000, float:1.14794E-41)
                byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0072, l -> 0x0070, all -> 0x006d }
            L_0x004b:
                int r2 = r5.read(r0)     // Catch:{ IOException -> 0x0072, l -> 0x0070, all -> 0x006d }
                r6 = -1
                if (r2 == r6) goto L_0x0056
                r1.a(r0, r2)     // Catch:{ IOException -> 0x0072, l -> 0x0070, all -> 0x006d }
                goto L_0x004b
            L_0x0056:
                r1.c()     // Catch:{ IOException -> 0x0072, l -> 0x0070, all -> 0x006d }
                java.util.Map<java.lang.String, java.io.File> r0 = a     // Catch:{ IOException -> 0x0072, l -> 0x0070, all -> 0x006d }
                r0.put(r8, r3)     // Catch:{ IOException -> 0x0072, l -> 0x0070, all -> 0x006d }
                r5.close()     // Catch:{ IOException -> 0x0062 }
                goto L_0x006c
            L_0x0062:
                r8 = move-exception
                java.lang.String r0 = com.facebook.ads.internal.h.c.a
                java.lang.String r1 = "Error closing the file"
                android.util.Log.e(r0, r1, r8)
            L_0x006c:
                return r4
            L_0x006d:
                r8 = move-exception
                r0 = r5
                goto L_0x0095
            L_0x0070:
                r8 = move-exception
                goto L_0x0073
            L_0x0072:
                r8 = move-exception
            L_0x0073:
                r0 = r5
                goto L_0x007a
            L_0x0075:
                r8 = move-exception
                goto L_0x0095
            L_0x0077:
                r8 = move-exception
                goto L_0x007a
            L_0x0079:
                r8 = move-exception
            L_0x007a:
                java.lang.String r1 = com.facebook.ads.internal.h.c.a     // Catch:{ all -> 0x0075 }
                java.lang.String r2 = "Error caching the file"
                android.util.Log.e(r1, r2, r8)     // Catch:{ all -> 0x0075 }
                r8 = 0
                if (r0 == 0) goto L_0x0094
                r0.close()     // Catch:{ IOException -> 0x008a }
                goto L_0x0094
            L_0x008a:
                r0 = move-exception
                java.lang.String r1 = com.facebook.ads.internal.h.c.a
                java.lang.String r2 = "Error closing the file"
                android.util.Log.e(r1, r2, r0)
            L_0x0094:
                return r8
            L_0x0095:
                if (r0 == 0) goto L_0x00a5
                r0.close()     // Catch:{ IOException -> 0x009b }
                goto L_0x00a5
            L_0x009b:
                r0 = move-exception
                java.lang.String r1 = com.facebook.ads.internal.h.c.a
                java.lang.String r2 = "Error closing the file"
                android.util.Log.e(r1, r2, r0)
            L_0x00a5:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.h.c.a.b(java.lang.String):boolean");
        }
    }

    private c(final Context context) {
        this.c = Executors.newSingleThreadExecutor().submit(new Callable<a>() {
            /* renamed from: a */
            public a call() {
                return new a(context);
            }
        });
    }

    public static c a(Context context) {
        if (b == null) {
            synchronized (e.class) {
                if (b == null) {
                    b = new c(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    @Nullable
    private a b() {
        try {
            return (a) this.c.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e(a, "Timed out waiting for cache server.", e);
            return null;
        }
    }

    public boolean a(String str) {
        a b2 = b();
        return b2 != null && b2.b(str);
    }

    @Nullable
    public String b(String str) {
        a b2 = b();
        if (b2 == null) {
            return null;
        }
        return b2.a(str);
    }
}
