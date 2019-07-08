package com.samsung.accessory.a.a;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.SparseArray;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.internal.NativeProtocol;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import java.util.LinkedList;
import java.util.TreeMap;

final class d {
    /* access modifiers changed from: private */
    public static int a;
    private static int b;
    private static int c;
    private static int d;
    private static int e;
    private static boolean f;
    private static String g;
    private static final TreeMap<Integer, Object> h = new TreeMap<>();
    private static final SparseArray<a> i = new SparseArray<>();
    /* access modifiers changed from: private */
    public static final Object j = new Object();

    static class a {
        private static int a;
        private LinkedList<byte[]> b;
        private int c;
        private int d;
        private int e;
        private final int f;

        private a(int i) {
            this.f = i;
            this.c = 0;
            this.d = 0;
            this.b = null;
            this.e = 1;
        }

        /* synthetic */ a(int i, byte b2) {
            this(i);
        }

        public static void a(a aVar) {
            aVar.f();
            a++;
        }

        private synchronized void f() {
            this.c++;
        }

        /* access modifiers changed from: 0000 */
        public final synchronized LinkedList<byte[]> a() {
            if (this.b == null) {
                this.b = new LinkedList<>();
            }
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public final synchronized LinkedList<byte[]> b() {
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public final synchronized int c() {
            return this.f;
        }

        /* access modifiers changed from: 0000 */
        public final float d() {
            float c2;
            synchronized (d.j) {
                c2 = ((float) d.a) * (((float) this.c) / ((float) a));
            }
            return c2;
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
            return true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final synchronized boolean e() {
            /*
                r3 = this;
                monitor-enter(r3)
                int r0 = r3.d     // Catch:{ all -> 0x0039 }
                r1 = 1
                int r0 = r0 + r1
                r3.d = r0     // Catch:{ all -> 0x0039 }
                java.util.LinkedList<byte[]> r0 = r3.b     // Catch:{ all -> 0x0039 }
                int r0 = r0.size()     // Catch:{ all -> 0x0039 }
                int r2 = r3.e     // Catch:{ all -> 0x0039 }
                if (r0 != r2) goto L_0x0037
                int r0 = r3.d     // Catch:{ all -> 0x0039 }
                float r0 = (float) r0     // Catch:{ all -> 0x0039 }
                r2 = 0
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 != 0) goto L_0x001d
                int r0 = r3.c     // Catch:{ all -> 0x0039 }
                float r0 = (float) r0     // Catch:{ all -> 0x0039 }
                goto L_0x0024
            L_0x001d:
                int r0 = r3.c     // Catch:{ all -> 0x0039 }
                float r0 = (float) r0     // Catch:{ all -> 0x0039 }
                int r2 = r3.d     // Catch:{ all -> 0x0039 }
                float r2 = (float) r2
                float r0 = r0 / r2
            L_0x0024:
                r2 = 1063675494(0x3f666666, float:0.9)
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 >= 0) goto L_0x002e
                r0 = 0
                monitor-exit(r3)
                return r0
            L_0x002e:
                int r0 = r3.e     // Catch:{ all -> 0x0039 }
                int r0 = r0 * 3
                int r0 = r0 / 2
                int r0 = r0 + r1
                r3.e = r0     // Catch:{ all -> 0x0039 }
            L_0x0037:
                monitor-exit(r3)
                return r1
            L_0x0039:
                r0 = move-exception
                monitor-exit(r3)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.accessory.a.a.d.a.e():boolean");
        }
    }

    public static a a(int i2) {
        if (a()) {
            return c(i2);
        }
        throw new RuntimeException("Bufferpool not initialised!");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.samsung.accessory.a.a.a a(int r4, int r5) {
        /*
            java.lang.Object r0 = j
            monitor-enter(r0)
            com.samsung.accessory.a.a.d$a r4 = d(r4)     // Catch:{ all -> 0x0033 }
            r1 = 0
            if (r4 != 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return r1
        L_0x000c:
            java.util.LinkedList r2 = r4.b()     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x0031
            boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x0033 }
            if (r3 == 0) goto L_0x0019
            goto L_0x0031
        L_0x0019:
            java.lang.Object r2 = r2.removeLast()     // Catch:{ all -> 0x0033 }
            byte[] r2 = (byte[]) r2     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x002f
            int r1 = e     // Catch:{ all -> 0x0033 }
            int r3 = r2.length     // Catch:{ all -> 0x0033 }
            int r1 = r1 - r3
            e = r1     // Catch:{ all -> 0x0033 }
            com.samsung.accessory.a.a.d.a.a(r4)     // Catch:{ all -> 0x0033 }
            com.samsung.accessory.a.a.a r1 = new com.samsung.accessory.a.a.a     // Catch:{ all -> 0x0033 }
            r1.<init>(r2, r5)     // Catch:{ all -> 0x0033 }
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return r1
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return r1
        L_0x0033:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.accessory.a.a.d.a(int, int):com.samsung.accessory.a.a.a");
    }

    private static void a(int i2, String str) {
        switch (i2) {
            case 2:
                Log.v(g, str);
                break;
            case 3:
                Log.d(g, str);
                return;
            case 4:
                Log.i(g, str);
                return;
            case 5:
                Log.w(g, str);
                return;
            case 6:
                Log.e(g, str);
                break;
        }
    }

    public static void a(e eVar) {
        if (a()) {
            a(5, "BufferPool already initialised!");
            return;
        }
        synchronized (j) {
            e = 0;
            if (eVar.c < 24 || eVar.b < eVar.c) {
                StringBuilder sb = new StringBuilder("Failed to initialise the Bufferpool! [Cache size=");
                sb.append(eVar.b);
                sb.append("; Max chunk size=");
                sb.append(eVar.c);
                sb.append("]");
                throw new RuntimeException(sb.toString());
            }
            g = eVar.a;
            a = eVar.b;
            int i2 = eVar.c;
            b = i2;
            int i3 = i2 <= 66560 ? b : 66560;
            c = a / 4;
            d = a / 2;
            int i4 = 24;
            int i5 = 36;
            while (i4 <= i3) {
                if (i4 <= i3) {
                    e(i4);
                }
                if (i4 != 24 && i5 <= i3) {
                    e(i5);
                }
                i4 <<= 1;
                i5 <<= 1;
            }
            e();
            if (b > 66560) {
                i3 = b;
            }
            e(i3);
            f = true;
            StringBuilder sb2 = new StringBuilder("BufferPool[v1.0.2] initialised with capacity ");
            sb2.append(a / ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES);
            sb2.append("MB");
            a(4, sb2.toString());
        }
    }

    public static boolean a() {
        boolean z;
        synchronized (j) {
            z = f;
        }
        return z;
    }

    public static boolean a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(AbstractEvent.ACTIVITY);
        if (activityManager != null) {
            return VERSION.SDK_INT >= 19 ? activityManager.isLowRamDevice() : activityManager.getMemoryClass() < 128;
        }
        a(5, "isLowMemoryDevice(): ActivityManager is null!");
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0022, code lost:
        r3 = (java.lang.Integer) h.floorKey(java.lang.Integer.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r3 != null) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
        r3 = r3.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        r4 = j;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r5 = d(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
        if (r5 == null) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003f, code lost:
        r6 = r5.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0047, code lost:
        if (r5.e() != false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0049, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004a, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0050, code lost:
        if ((e + r0) <= a) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0052, code lost:
        r5 = ((int) r5.d()) / r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005c, code lost:
        if (r6.size() < r5) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005e, code lost:
        r9 = new java.lang.StringBuilder("Cannot recycle buffer '");
        r9.append(r3);
        r9.append("', Buffer chunk count(");
        r9.append(r6.size());
        r9.append(") exceeded the limit");
        r9.append(r5);
        r9.append("!");
        a(5, r9.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0088, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0089, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008a, code lost:
        d();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0092, code lost:
        if ((e + r0) <= a) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0094, code lost:
        r9 = new java.lang.StringBuilder("Cannot recycle buffer '");
        r9.append(r3);
        r9.append("', Buffer cache limit exceeded!!!");
        a(5, r9.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00aa, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ab, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ac, code lost:
        if (r3 != r0) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ae, code lost:
        r6.addLast(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b2, code lost:
        r6.addFirst(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b5, code lost:
        e += r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ba, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00bc, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00bd, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00be, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(byte[] r9) {
        /*
            boolean r0 = a()
            r1 = 5
            r2 = 0
            if (r0 != 0) goto L_0x000e
            java.lang.String r9 = "Failed to recycle buffer - Bufferpool not initialised!"
        L_0x000a:
            a(r1, r9)
            return r2
        L_0x000e:
            if (r9 != 0) goto L_0x0013
            java.lang.String r9 = "Cannot recycle null buffer!"
            goto L_0x000a
        L_0x0013:
            int r0 = r9.length
            java.lang.Object r3 = j
            monitor-enter(r3)
            r4 = 24
            if (r0 < r4) goto L_0x00c2
            int r4 = b     // Catch:{ all -> 0x00da }
            if (r0 <= r4) goto L_0x0021
            goto L_0x00c2
        L_0x0021:
            monitor-exit(r3)     // Catch:{ all -> 0x00da }
            java.util.TreeMap<java.lang.Integer, java.lang.Object> r3 = h
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            java.lang.Object r3 = r3.floorKey(r4)
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 != 0) goto L_0x0032
            r3 = r0
            goto L_0x0036
        L_0x0032:
            int r3 = r3.intValue()
        L_0x0036:
            java.lang.Object r4 = j
            monitor-enter(r4)
            com.samsung.accessory.a.a.d$a r5 = d(r3)     // Catch:{ all -> 0x00bf }
            if (r5 == 0) goto L_0x00bd
            java.util.LinkedList r6 = r5.a()     // Catch:{ all -> 0x00bf }
            boolean r7 = r5.e()     // Catch:{ all -> 0x00bf }
            if (r7 != 0) goto L_0x004b
            monitor-exit(r4)     // Catch:{ all -> 0x00bf }
            return r2
        L_0x004b:
            int r7 = e     // Catch:{ all -> 0x00bf }
            int r7 = r7 + r0
            int r8 = a     // Catch:{ all -> 0x00bf }
            if (r7 <= r8) goto L_0x00ac
            float r5 = r5.d()     // Catch:{ all -> 0x00bf }
            int r5 = (int) r5     // Catch:{ all -> 0x00bf }
            int r5 = r5 / r3
            int r7 = r6.size()     // Catch:{ all -> 0x00bf }
            if (r7 < r5) goto L_0x008a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "Cannot recycle buffer '"
            r9.<init>(r0)     // Catch:{ all -> 0x00bf }
            r9.append(r3)     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "', Buffer chunk count("
            r9.append(r0)     // Catch:{ all -> 0x00bf }
            int r0 = r6.size()     // Catch:{ all -> 0x00bf }
            r9.append(r0)     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = ") exceeded the limit"
            r9.append(r0)     // Catch:{ all -> 0x00bf }
            r9.append(r5)     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "!"
            r9.append(r0)     // Catch:{ all -> 0x00bf }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00bf }
            a(r1, r9)     // Catch:{ all -> 0x00bf }
            monitor-exit(r4)     // Catch:{ all -> 0x00bf }
            return r2
        L_0x008a:
            d()     // Catch:{ all -> 0x00bf }
            int r5 = e     // Catch:{ all -> 0x00bf }
            int r5 = r5 + r0
            int r7 = a     // Catch:{ all -> 0x00bf }
            if (r5 <= r7) goto L_0x00ac
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "Cannot recycle buffer '"
            r9.<init>(r0)     // Catch:{ all -> 0x00bf }
            r9.append(r3)     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "', Buffer cache limit exceeded!!!"
            r9.append(r0)     // Catch:{ all -> 0x00bf }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00bf }
            a(r1, r9)     // Catch:{ all -> 0x00bf }
            monitor-exit(r4)     // Catch:{ all -> 0x00bf }
            return r2
        L_0x00ac:
            if (r3 != r0) goto L_0x00b2
            r6.addLast(r9)     // Catch:{ all -> 0x00bf }
            goto L_0x00b5
        L_0x00b2:
            r6.addFirst(r9)     // Catch:{ all -> 0x00bf }
        L_0x00b5:
            int r9 = e     // Catch:{ all -> 0x00bf }
            int r9 = r9 + r0
            e = r9     // Catch:{ all -> 0x00bf }
            monitor-exit(r4)     // Catch:{ all -> 0x00bf }
            r9 = 1
            return r9
        L_0x00bd:
            monitor-exit(r4)     // Catch:{ all -> 0x00bf }
            return r2
        L_0x00bf:
            r9 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00bf }
            throw r9
        L_0x00c2:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00da }
            java.lang.String r4 = "Cannot recycle buffer '"
            r9.<init>(r4)     // Catch:{ all -> 0x00da }
            r9.append(r0)     // Catch:{ all -> 0x00da }
            java.lang.String r0 = "', Non-matcing size!"
            r9.append(r0)     // Catch:{ all -> 0x00da }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00da }
            a(r1, r9)     // Catch:{ all -> 0x00da }
            monitor-exit(r3)     // Catch:{ all -> 0x00da }
            return r2
        L_0x00da:
            r9 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00da }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.accessory.a.a.d.a(byte[]):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0053 A[Catch:{ all -> 0x0029 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0066 A[Catch:{ all -> 0x0029 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(int r7) {
        /*
            boolean r0 = a()
            r1 = 5
            r2 = 0
            if (r0 != 0) goto L_0x000e
            java.lang.String r7 = "Failed to clear cache - Bufferpool not initialised!"
            a(r1, r7)
            return r2
        L_0x000e:
            java.lang.Object r0 = j
            monitor-enter(r0)
            if (r7 == r1) goto L_0x0031
            r3 = 10
            if (r7 == r3) goto L_0x002e
            r3 = 15
            if (r7 == r3) goto L_0x002c
            r3 = 40
            if (r7 == r3) goto L_0x0031
            r3 = 60
            if (r7 == r3) goto L_0x002e
            r3 = 80
            if (r7 == r3) goto L_0x002c
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return r2
        L_0x0029:
            r7 = move-exception
            goto L_0x00b3
        L_0x002c:
            r3 = 0
            goto L_0x0033
        L_0x002e:
            int r3 = d     // Catch:{ all -> 0x0029 }
            goto L_0x0033
        L_0x0031:
            int r3 = c     // Catch:{ all -> 0x0029 }
        L_0x0033:
            r4 = 2
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0029 }
            java.lang.String r6 = "ClearCache["
            r5.<init>(r6)     // Catch:{ all -> 0x0029 }
            r5.append(r7)     // Catch:{ all -> 0x0029 }
            java.lang.String r7 = "] : Cache Size BEFORE = "
            r5.append(r7)     // Catch:{ all -> 0x0029 }
            int r7 = e     // Catch:{ all -> 0x0029 }
            r5.append(r7)     // Catch:{ all -> 0x0029 }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x0029 }
            a(r4, r7)     // Catch:{ all -> 0x0029 }
            int r7 = e     // Catch:{ all -> 0x0029 }
            if (r7 > r3) goto L_0x0066
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0029 }
            java.lang.String r4 = "ClearCache : Current cache size is lesser than the threshold of "
            r7.<init>(r4)     // Catch:{ all -> 0x0029 }
            r7.append(r3)     // Catch:{ all -> 0x0029 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0029 }
            a(r1, r7)     // Catch:{ all -> 0x0029 }
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return r2
        L_0x0066:
            android.util.SparseArray<com.samsung.accessory.a.a.d$a> r7 = i     // Catch:{ all -> 0x0029 }
            int r7 = r7.size()     // Catch:{ all -> 0x0029 }
        L_0x006c:
            if (r2 < r7) goto L_0x006f
            goto L_0x009c
        L_0x006f:
            android.util.SparseArray<com.samsung.accessory.a.a.d$a> r1 = i     // Catch:{ all -> 0x0029 }
            java.lang.Object r1 = r1.valueAt(r2)     // Catch:{ all -> 0x0029 }
            com.samsung.accessory.a.a.d$a r1 = (com.samsung.accessory.a.a.d.a) r1     // Catch:{ all -> 0x0029 }
            java.util.LinkedList r1 = r1.b()     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x0095
        L_0x007d:
            boolean r4 = r1.isEmpty()     // Catch:{ all -> 0x0029 }
            if (r4 != 0) goto L_0x0095
            int r4 = e     // Catch:{ all -> 0x0029 }
            if (r4 > r3) goto L_0x0088
            goto L_0x0095
        L_0x0088:
            java.lang.Object r4 = r1.removeLast()     // Catch:{ all -> 0x0029 }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x0029 }
            int r5 = e     // Catch:{ all -> 0x0029 }
            int r4 = r4.length     // Catch:{ all -> 0x0029 }
            int r5 = r5 - r4
            e = r5     // Catch:{ all -> 0x0029 }
            goto L_0x007d
        L_0x0095:
            int r1 = e     // Catch:{ all -> 0x0029 }
            if (r1 <= r3) goto L_0x009c
            int r2 = r2 + 1
            goto L_0x006c
        L_0x009c:
            r7 = 3
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0029 }
            java.lang.String r2 = "ClearCache : Cache Size AFTER = "
            r1.<init>(r2)     // Catch:{ all -> 0x0029 }
            int r2 = e     // Catch:{ all -> 0x0029 }
            r1.append(r2)     // Catch:{ all -> 0x0029 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0029 }
            a(r7, r1)     // Catch:{ all -> 0x0029 }
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            r7 = 1
            return r7
        L_0x00b3:
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.accessory.a.a.d.b(int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0086, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.samsung.accessory.a.a.a c(int r6) {
        /*
            java.lang.Object r0 = j
            monitor-enter(r0)
            int r1 = b     // Catch:{ all -> 0x0087 }
            if (r6 <= r1) goto L_0x0027
            r1 = 5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0087 }
            java.lang.String r3 = "Buffer '"
            r2.<init>(r3)     // Catch:{ all -> 0x0087 }
            r2.append(r6)     // Catch:{ all -> 0x0087 }
            java.lang.String r3 = "' is not matching with the pool sizes! creating new..."
            r2.append(r3)     // Catch:{ all -> 0x0087 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0087 }
            a(r1, r2)     // Catch:{ all -> 0x0087 }
            com.samsung.accessory.a.a.a r1 = new com.samsung.accessory.a.a.a     // Catch:{ all -> 0x0087 }
            byte[] r2 = new byte[r6]     // Catch:{ all -> 0x0087 }
            r1.<init>(r2, r6)     // Catch:{ all -> 0x0087 }
            monitor-exit(r0)     // Catch:{ all -> 0x0087 }
            return r1
        L_0x0027:
            java.util.TreeMap<java.lang.Integer, java.lang.Object> r1 = h     // Catch:{ all -> 0x0087 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0087 }
            java.lang.Object r1 = r1.ceilingKey(r2)     // Catch:{ all -> 0x0087 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x0087 }
            if (r1 != 0) goto L_0x0037
            r1 = r6
            goto L_0x003b
        L_0x0037:
            int r1 = r1.intValue()     // Catch:{ all -> 0x0087 }
        L_0x003b:
            com.samsung.accessory.a.a.a r2 = a(r1, r6)     // Catch:{ all -> 0x0087 }
            if (r2 != 0) goto L_0x0085
            r3 = 1
            r4 = r1
        L_0x0043:
            if (r2 != 0) goto L_0x0073
            r5 = 3
            if (r3 <= r5) goto L_0x0049
            goto L_0x0073
        L_0x0049:
            java.util.TreeMap<java.lang.Integer, java.lang.Object> r5 = h     // Catch:{ all -> 0x0087 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0087 }
            java.lang.Object r4 = r5.higherKey(r4)     // Catch:{ all -> 0x0087 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ all -> 0x0087 }
            if (r4 != 0) goto L_0x005b
            r4 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x005f
        L_0x005b:
            int r4 = r4.intValue()     // Catch:{ all -> 0x0087 }
        L_0x005f:
            r5 = 24
            if (r4 < r5) goto L_0x0073
            r5 = 66560(0x10400, float:9.327E-41)
            if (r4 > r5) goto L_0x0073
            int r5 = b     // Catch:{ all -> 0x0087 }
            if (r4 > r5) goto L_0x0073
            com.samsung.accessory.a.a.a r2 = a(r4, r6)     // Catch:{ all -> 0x0087 }
            int r3 = r3 + 1
            goto L_0x0043
        L_0x0073:
            if (r2 != 0) goto L_0x0085
            com.samsung.accessory.a.a.a r2 = new com.samsung.accessory.a.a.a     // Catch:{ all -> 0x0087 }
            byte[] r3 = new byte[r1]     // Catch:{ all -> 0x0087 }
            r2.<init>(r3, r6)     // Catch:{ all -> 0x0087 }
            com.samsung.accessory.a.a.d$a r6 = d(r1)     // Catch:{ all -> 0x0087 }
            if (r6 == 0) goto L_0x0085
            com.samsung.accessory.a.a.d.a.a(r6)     // Catch:{ all -> 0x0087 }
        L_0x0085:
            monitor-exit(r0)     // Catch:{ all -> 0x0087 }
            return r2
        L_0x0087:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0087 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.accessory.a.a.d.c(int):com.samsung.accessory.a.a.a");
    }

    private static int d() {
        int i2;
        synchronized (j) {
            int i3 = e;
            int size = i.size();
            for (int i4 = 0; i4 < size; i4++) {
                a aVar = (a) i.valueAt(i4);
                if (aVar != null) {
                    int d2 = (int) (aVar.d() / ((float) aVar.c()));
                    LinkedList b2 = aVar.b();
                    int size2 = b2 == null ? 0 : b2.size();
                    while (size2 > d2) {
                        if (b2 != null) {
                            e -= ((byte[]) b2.removeLast()).length;
                            size2--;
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder("Pool Stabilized; Cache size reduced from  ");
            sb.append(i3);
            sb.append(" -> ");
            sb.append(e);
            a(5, sb.toString());
            i2 = i3 - e;
        }
        return i2;
    }

    private static a d(int i2) {
        a aVar;
        synchronized (j) {
            aVar = (a) i.get(i2);
        }
        return aVar;
    }

    private static void e() {
        int[] iArr = {30731, 32779, 61451, NativeProtocol.MESSAGE_GET_INSTALL_DATA_REPLY};
        synchronized (j) {
            for (int i2 = 0; i2 < 4; i2++) {
                e(iArr[i2]);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean e(int r5) {
        /*
            java.lang.Object r0 = j
            monitor-enter(r0)
            int r1 = b     // Catch:{ all -> 0x0029 }
            r2 = 0
            if (r5 > r1) goto L_0x0027
            android.util.SparseArray<com.samsung.accessory.a.a.d$a> r1 = i     // Catch:{ all -> 0x0029 }
            int r1 = r1.indexOfKey(r5)     // Catch:{ all -> 0x0029 }
            if (r1 >= 0) goto L_0x0027
            java.util.TreeMap<java.lang.Integer, java.lang.Object> r1 = h     // Catch:{ all -> 0x0029 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0029 }
            r4 = 0
            r1.put(r3, r4)     // Catch:{ all -> 0x0029 }
            android.util.SparseArray<com.samsung.accessory.a.a.d$a> r1 = i     // Catch:{ all -> 0x0029 }
            com.samsung.accessory.a.a.d$a r3 = new com.samsung.accessory.a.a.d$a     // Catch:{ all -> 0x0029 }
            r3.<init>(r5, r2)     // Catch:{ all -> 0x0029 }
            r1.put(r5, r3)     // Catch:{ all -> 0x0029 }
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            r5 = 1
            return r5
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return r2
        L_0x0029:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.accessory.a.a.d.e(int):boolean");
    }
}
