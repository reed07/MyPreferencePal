package com.inmobi.ads.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

/* compiled from: AdAsset */
public class a {
    /* access modifiers changed from: private */
    public static final String m = "a";
    public long a = 0;
    int b;
    int c;
    public String d;
    public String e;
    long f;
    long g;
    public long h;
    long i;
    public boolean j;
    public String k;
    public int l;

    /* renamed from: com.inmobi.ads.cache.a$a reason: collision with other inner class name */
    /* compiled from: AdAsset */
    public static final class C0044a {
        int a = (new Random().nextInt() & Integer.MAX_VALUE);
        int b;
        String c;
        String d;
        long e = System.currentTimeMillis();
        long f = System.currentTimeMillis();
        long g;
        long h;

        public final C0044a a(String str, int i, long j) {
            this.c = str;
            this.b = i;
            this.g = System.currentTimeMillis() + j;
            return this;
        }

        private static long a(String str) {
            try {
                return new SimpleDateFormat("EEE,dd MMM yyyy HH:mm:ss z", Locale.ENGLISH).parse(str).getTime();
            } catch (ParseException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                return 0;
            }
        }

        @NonNull
        public final a a() {
            a aVar = new a(this.a, this.c, this.d, this.b, this.e, this.f, this.g, this.h);
            return aVar;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0053  */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x00e8  */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x010e  */
        /* JADX WARNING: Removed duplicated region for block: B:64:0x0130  */
        /* JADX WARNING: Removed duplicated region for block: B:69:0x0145  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.inmobi.ads.cache.a.C0044a a(java.lang.String r20, java.lang.String r21, com.inmobi.commons.core.network.d r22, int r23, long r24) {
            /*
                r19 = this;
                r1 = r19
                r0 = r22
                java.util.Map<java.lang.String, java.util.List<java.lang.String>> r2 = r0.d
                long r3 = java.lang.System.currentTimeMillis()
                java.lang.String r0 = "Date"
                java.lang.Object r0 = r2.get(r0)
                java.util.List r0 = (java.util.List) r0
                r5 = 0
                r7 = 0
                if (r0 == 0) goto L_0x0032
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x0032
                java.lang.String r0 = "Date"
                java.lang.Object r0 = r2.get(r0)
                java.util.List r0 = (java.util.List) r0
                java.lang.Object r0 = r0.get(r7)
                java.lang.String r0 = (java.lang.String) r0
                if (r0 == 0) goto L_0x0032
                long r8 = a(r0)
                goto L_0x0033
            L_0x0032:
                r8 = r5
            L_0x0033:
                java.lang.String r0 = "Cache-Control"
                java.lang.Object r0 = r2.get(r0)
                java.util.List r0 = (java.util.List) r0
                if (r0 == 0) goto L_0x00c3
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x00c3
                java.lang.String r0 = "Cache-Control"
                java.lang.Object r0 = r2.get(r0)
                java.util.List r0 = (java.util.List) r0
                java.lang.Object r0 = r0.get(r7)
                java.lang.String r0 = (java.lang.String) r0
                if (r0 == 0) goto L_0x00c3
                java.lang.String r11 = ","
                java.lang.String[] r11 = r0.split(r11)
                int r12 = r11.length
                r15 = r5
                r17 = r15
                r13 = 0
                r14 = 0
            L_0x005f:
                if (r13 >= r12) goto L_0x00c1
                r0 = r11[r13]
                java.lang.String r0 = r0.trim()
                java.lang.String r10 = "no-cache"
                boolean r10 = r0.equals(r10)
                if (r10 != 0) goto L_0x00be
                java.lang.String r10 = "no-store"
                boolean r10 = r0.equals(r10)
                if (r10 != 0) goto L_0x00be
                java.lang.String r10 = "max-age="
                boolean r10 = r0.startsWith(r10)
                if (r10 == 0) goto L_0x0092
                r10 = 8
                java.lang.String r0 = r0.substring(r10)     // Catch:{ Exception -> 0x008a }
                long r15 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x008a }
                goto L_0x00be
            L_0x008a:
                r0 = move-exception
                com.inmobi.ads.cache.a.m
                r0.getMessage()
                goto L_0x00be
            L_0x0092:
                java.lang.String r10 = "stale-while-revalidate="
                boolean r10 = r0.startsWith(r10)
                if (r10 == 0) goto L_0x00ad
                r10 = 23
                java.lang.String r0 = r0.substring(r10)     // Catch:{ Exception -> 0x00a5 }
                long r17 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x00a5 }
                goto L_0x00be
            L_0x00a5:
                r0 = move-exception
                com.inmobi.ads.cache.a.m
                r0.getMessage()
                goto L_0x00be
            L_0x00ad:
                java.lang.String r10 = "must-revalidate"
                boolean r10 = r0.equals(r10)
                if (r10 != 0) goto L_0x00bd
                java.lang.String r10 = "proxy-revalidate"
                boolean r0 = r0.equals(r10)
                if (r0 == 0) goto L_0x00be
            L_0x00bd:
                r14 = 1
            L_0x00be:
                int r13 = r13 + 1
                goto L_0x005f
            L_0x00c1:
                r0 = 1
                goto L_0x00c8
            L_0x00c3:
                r15 = r5
                r17 = r15
                r0 = 0
                r14 = 0
            L_0x00c8:
                java.lang.String r10 = "Expires"
                java.lang.Object r10 = r2.get(r10)
                java.util.List r10 = (java.util.List) r10
                if (r10 == 0) goto L_0x00ed
                int r10 = r10.size()
                if (r10 <= 0) goto L_0x00ed
                java.lang.String r10 = "Expires"
                java.lang.Object r10 = r2.get(r10)
                java.util.List r10 = (java.util.List) r10
                java.lang.Object r10 = r10.get(r7)
                java.lang.String r10 = (java.lang.String) r10
                if (r10 == 0) goto L_0x00ed
                long r10 = a(r10)
                goto L_0x00ee
            L_0x00ed:
                r10 = r5
            L_0x00ee:
                java.lang.String r12 = "Last-Modified"
                java.lang.Object r12 = r2.get(r12)
                java.util.List r12 = (java.util.List) r12
                if (r12 == 0) goto L_0x0111
                int r12 = r12.size()
                if (r12 <= 0) goto L_0x0111
                java.lang.String r12 = "Last-Modified"
                java.lang.Object r12 = r2.get(r12)
                java.util.List r12 = (java.util.List) r12
                java.lang.Object r12 = r12.get(r7)
                java.lang.String r12 = (java.lang.String) r12
                if (r12 == 0) goto L_0x0111
                a(r12)
            L_0x0111:
                java.lang.String r12 = "ETag"
                java.lang.Object r12 = r2.get(r12)
                java.util.List r12 = (java.util.List) r12
                if (r12 == 0) goto L_0x012c
                int r12 = r12.size()
                if (r12 <= 0) goto L_0x012c
                java.lang.String r12 = "ETag"
                java.lang.Object r2 = r2.get(r12)
                java.util.List r2 = (java.util.List) r2
                r2.get(r7)
            L_0x012c:
                r12 = 1000(0x3e8, double:4.94E-321)
                if (r0 == 0) goto L_0x0145
                long r15 = r15 * r12
                long r5 = r3 + r15
                if (r14 == 0) goto L_0x0139
                r17 = r5
                goto L_0x0140
            L_0x0139:
                java.lang.Long.signum(r17)
                long r17 = r17 * r12
                long r17 = r5 + r17
            L_0x0140:
                r2 = r20
                r7 = r17
                goto L_0x0157
            L_0x0145:
                int r0 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
                if (r0 <= 0) goto L_0x0154
                int r0 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r0 < 0) goto L_0x0154
                long r10 = r10 - r8
                long r5 = r3 + r10
                r2 = r20
                r7 = r5
                goto L_0x0157
            L_0x0154:
                r2 = r20
                r7 = r5
            L_0x0157:
                r1.c = r2
                r2 = r21
                r1.d = r2
                r2 = r23
                r1.b = r2
                long r9 = r24 * r12
                long r3 = r3 + r9
                r1.g = r3
                r1.h = r5
                long r2 = r1.g
                long r2 = java.lang.Math.min(r2, r7)
                r1.g = r2
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.cache.a.C0044a.a(java.lang.String, java.lang.String, com.inmobi.commons.core.network.d, int, long):com.inmobi.ads.cache.a$a");
        }
    }

    a(int i2, @NonNull String str, @Nullable String str2, int i3, long j2, long j3, long j4, long j5) {
        this.b = i2;
        this.d = str;
        this.e = str2;
        this.c = i3;
        this.f = j2;
        this.g = j3;
        this.h = j4;
        this.i = j5;
        this.j = false;
        this.k = null;
    }

    public final boolean a() {
        String str = this.e;
        return (str == null || str.length() == 0 || !new File(this.e).exists()) ? false : true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.d.equals(((a) obj).d);
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AdAsset{url='");
        sb.append(this.d);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }
}
