package com.facebook.ads.internal.v.b;

import android.util.Log;
import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicInteger;

class k {
    private final n a;
    private final a b;
    private final Object c = new Object();
    private final Object d = new Object();
    private final AtomicInteger e;
    private volatile Thread f;
    private volatile boolean g;
    private volatile int h = -1;

    private class a implements Runnable {
        private a() {
        }

        public void run() {
            k.a(k.this);
        }
    }

    public k(n nVar, a aVar) {
        this.a = (n) j.a(nVar);
        this.b = (a) j.a(aVar);
        this.e = new AtomicInteger();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        r1 = r1 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003a, code lost:
        r0 = r8.d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003c, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0041, code lost:
        if (r8.c() != false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004f, code lost:
        if (r8.b.a() != r8.a.a()) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0051, code lost:
        r8.b.c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0056, code lost:
        monitor-exit(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.facebook.ads.internal.v.b.k r8) {
        /*
            r0 = -1
            r1 = 0
            com.facebook.ads.internal.v.b.a r2 = r8.b     // Catch:{ Throwable -> 0x0061, all -> 0x005d }
            int r1 = r2.a()     // Catch:{ Throwable -> 0x0061, all -> 0x005d }
            com.facebook.ads.internal.v.b.n r2 = r8.a     // Catch:{ Throwable -> 0x0061, all -> 0x005d }
            r2.a(r1)     // Catch:{ Throwable -> 0x0061, all -> 0x005d }
            com.facebook.ads.internal.v.b.n r2 = r8.a     // Catch:{ Throwable -> 0x0061, all -> 0x005d }
            int r2 = r2.a()     // Catch:{ Throwable -> 0x0061, all -> 0x005d }
            r3 = 8192(0x2000, float:1.14794E-41)
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x005b }
        L_0x0017:
            com.facebook.ads.internal.v.b.n r4 = r8.a     // Catch:{ Throwable -> 0x005b }
            int r4 = r4.a(r3)     // Catch:{ Throwable -> 0x005b }
            if (r4 == r0) goto L_0x003a
            java.lang.Object r5 = r8.d     // Catch:{ Throwable -> 0x005b }
            monitor-enter(r5)     // Catch:{ Throwable -> 0x005b }
            boolean r6 = r8.c()     // Catch:{ all -> 0x0037 }
            if (r6 == 0) goto L_0x002a
            monitor-exit(r5)     // Catch:{ all -> 0x0037 }
            goto L_0x006c
        L_0x002a:
            com.facebook.ads.internal.v.b.a r6 = r8.b     // Catch:{ all -> 0x0037 }
            r6.a(r3, r4)     // Catch:{ all -> 0x0037 }
            monitor-exit(r5)     // Catch:{ all -> 0x0037 }
            int r1 = r1 + r4
            long r4 = (long) r1
            long r6 = (long) r2
            r8.b(r4, r6)     // Catch:{ Throwable -> 0x005b }
            goto L_0x0017
        L_0x0037:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0037 }
            throw r0     // Catch:{ Throwable -> 0x005b }
        L_0x003a:
            java.lang.Object r0 = r8.d     // Catch:{ Throwable -> 0x005b }
            monitor-enter(r0)     // Catch:{ Throwable -> 0x005b }
            boolean r3 = r8.c()     // Catch:{ all -> 0x0058 }
            if (r3 != 0) goto L_0x0056
            com.facebook.ads.internal.v.b.a r3 = r8.b     // Catch:{ all -> 0x0058 }
            int r3 = r3.a()     // Catch:{ all -> 0x0058 }
            com.facebook.ads.internal.v.b.n r4 = r8.a     // Catch:{ all -> 0x0058 }
            int r4 = r4.a()     // Catch:{ all -> 0x0058 }
            if (r3 != r4) goto L_0x0056
            com.facebook.ads.internal.v.b.a r3 = r8.b     // Catch:{ all -> 0x0058 }
            r3.c()     // Catch:{ all -> 0x0058 }
        L_0x0056:
            monitor-exit(r0)     // Catch:{ all -> 0x0058 }
            goto L_0x006c
        L_0x0058:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0058 }
            throw r3     // Catch:{ Throwable -> 0x005b }
        L_0x005b:
            r0 = move-exception
            goto L_0x0064
        L_0x005d:
            r2 = move-exception
            r0 = r2
            r2 = -1
            goto L_0x0076
        L_0x0061:
            r2 = move-exception
            r0 = r2
            r2 = -1
        L_0x0064:
            java.util.concurrent.atomic.AtomicInteger r3 = r8.e     // Catch:{ all -> 0x0075 }
            r3.incrementAndGet()     // Catch:{ all -> 0x0075 }
            r8.a(r0)     // Catch:{ all -> 0x0075 }
        L_0x006c:
            r8.d()
            long r0 = (long) r1
            long r2 = (long) r2
            r8.b(r0, r2)
            return
        L_0x0075:
            r0 = move-exception
        L_0x0076:
            r8.d()
            long r3 = (long) r1
            long r1 = (long) r2
            r8.b(r3, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.v.b.k.a(com.facebook.ads.internal.v.b.k):void");
    }

    private synchronized void b() {
        boolean z = (this.f == null || this.f.getState() == State.TERMINATED) ? false : true;
        if (!this.g && !this.b.d() && !z) {
            a aVar = new a();
            StringBuilder sb = new StringBuilder();
            sb.append("Source reader for ");
            sb.append(this.a);
            this.f = new Thread(aVar, sb.toString());
            this.f.start();
        }
    }

    private void b(long j, long j2) {
        a(j, j2);
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    private boolean c() {
        return Thread.currentThread().isInterrupted() || this.g;
    }

    private void d() {
        try {
            this.a.b();
        } catch (l | IllegalArgumentException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error closing source ");
            sb.append(this.a);
            a((Throwable) new l(sb.toString(), e2));
        }
    }

    public int a(byte[] bArr, long j, int i) {
        if (bArr != null) {
            j.a(j >= 0, "Data offset must be positive!");
            j.a(i >= 0 && i <= bArr.length, "Length must be in range [0..buffer.length]");
            while (!this.b.d() && ((long) this.b.a()) < ((long) i) + j && !this.g) {
                b();
                synchronized (this.c) {
                    try {
                        this.c.wait(1000);
                    } catch (InterruptedException e2) {
                        throw new l("Waiting source data is interrupted!", e2);
                    }
                }
                int i2 = this.e.get();
                if (i2 >= 1) {
                    this.e.set(0);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error reading source ");
                    sb.append(i2);
                    sb.append(" times");
                    throw new l(sb.toString());
                }
            }
            int a2 = this.b.a(bArr, j, i);
            if (this.b.d() && this.h != 100) {
                this.h = 100;
                a(100);
            }
            return a2;
        }
        throw new NullPointerException("Buffer must be not null!");
    }

    public void a() {
        synchronized (this.d) {
            String str = "ProxyCache";
            StringBuilder sb = new StringBuilder();
            sb.append("Shutdown proxy for ");
            sb.append(this.a);
            Log.d(str, sb.toString());
            try {
                this.g = true;
                if (this.f != null) {
                    this.f.interrupt();
                }
                this.b.b();
            } catch (l e2) {
                a((Throwable) e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
    }

    /* access modifiers changed from: protected */
    public void a(long j, long j2) {
        boolean z = true;
        int i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        int i2 = i == 0 ? 100 : (int) ((j * 100) / j2);
        boolean z2 = i2 != this.h;
        if (i < 0) {
            z = false;
        }
        if (z && z2) {
            a(i2);
        }
        this.h = i2;
    }

    /* access modifiers changed from: protected */
    public final void a(Throwable th) {
        if (th instanceof i) {
            Log.d("ProxyCache", "ProxyCache is interrupted");
        } else {
            Log.e("ProxyCache", "ProxyCache error", th);
        }
    }
}
