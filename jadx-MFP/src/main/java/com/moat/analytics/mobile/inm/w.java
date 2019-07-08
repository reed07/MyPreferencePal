package com.moat.analytics.mobile.inm;

import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class w {
    private static w h;
    /* access modifiers changed from: private */
    public static final Queue<c> i = new ConcurrentLinkedQueue();
    volatile d a = d.OFF;
    volatile boolean b = false;
    volatile boolean c = false;
    volatile int d = 200;
    volatile int e = 10;
    private long f = 1800000;
    /* access modifiers changed from: private */
    public long g = DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS;
    /* access modifiers changed from: private */
    public Handler j;
    /* access modifiers changed from: private */
    public final AtomicBoolean k = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public volatile long l = 0;
    /* access modifiers changed from: private */
    public final AtomicInteger m = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public final AtomicBoolean n = new AtomicBoolean(false);

    private class a implements Runnable {
        private final Handler b;
        private final String c;
        /* access modifiers changed from: private */
        public final e d;

        private a(String str, Handler handler, e eVar) {
            this.d = eVar;
            this.b = handler;
            StringBuilder sb = new StringBuilder("https://z.moatads.com/");
            sb.append(str);
            sb.append("/android/");
            sb.append("c334ae83accfebb8da23104450c896463c9cfab7".substring(0, 7));
            sb.append("/status.json");
            this.c = sb.toString();
        }

        private void a() {
            String b2 = b();
            final l lVar = new l(b2);
            w.this.b = lVar.a();
            w.this.c = lVar.b();
            w.this.d = lVar.c();
            w.this.e = lVar.d();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        a.this.d.a(lVar);
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            });
            w.this.l = System.currentTimeMillis();
            w.this.n.compareAndSet(true, false);
            if (b2 != null) {
                w.this.m.set(0);
            } else if (w.this.m.incrementAndGet() < 10) {
                w wVar = w.this;
                wVar.a(wVar.g);
            }
        }

        private String b() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.c);
            sb.append("?ts=");
            sb.append(System.currentTimeMillis());
            sb.append("&v=2.5.0");
            try {
                return (String) q.a(sb.toString()).b();
            } catch (Exception unused) {
                return null;
            }
        }

        public void run() {
            try {
                a();
            } catch (Exception e) {
                m.a(e);
            }
            this.b.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    interface b {
        void c();

        void d();
    }

    private class c {
        final Long a;
        final b b;

        c(Long l, b bVar) {
            this.a = l;
            this.b = bVar;
        }
    }

    enum d {
        OFF,
        ON
    }

    interface e {
        void a(l lVar);
    }

    private w() {
        try {
            this.j = new Handler(Looper.getMainLooper());
        } catch (Exception e2) {
            m.a(e2);
        }
    }

    static synchronized w a() {
        w wVar;
        synchronized (w.class) {
            if (h == null) {
                h = new w();
            }
            wVar = h;
        }
        return wVar;
    }

    /* access modifiers changed from: private */
    public void a(final long j2) {
        if (this.n.compareAndSet(false, true)) {
            p.a(3, "OnOff", (Object) this, "Performing status check.");
            new Thread() {
                public void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    a aVar = new a("INM", handler, new e() {
                        public void a(l lVar) {
                            synchronized (w.i) {
                                boolean z = ((k) MoatAnalytics.getInstance()).a;
                                if (w.this.a != lVar.e() || (w.this.a == d.OFF && z)) {
                                    w.this.a = lVar.e();
                                    if (w.this.a == d.OFF && z) {
                                        w.this.a = d.ON;
                                    }
                                    if (w.this.a == d.ON) {
                                        p.a(3, "OnOff", (Object) this, "Moat enabled - Version 2.5.0");
                                    }
                                    for (c cVar : w.i) {
                                        if (w.this.a == d.ON) {
                                            cVar.b.c();
                                        } else {
                                            cVar.b.d();
                                        }
                                    }
                                }
                                while (!w.i.isEmpty()) {
                                    w.i.remove();
                                }
                            }
                        }
                    });
                    handler.postDelayed(aVar, j2);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        synchronized (i) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = i.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((c) it.next()).a.longValue() >= DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS) {
                    it.remove();
                }
            }
            if (i.size() >= 15) {
                for (int i2 = 0; i2 < 5; i2++) {
                    i.remove();
                }
            }
        }
    }

    private void e() {
        if (this.k.compareAndSet(false, true)) {
            this.j.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (w.i.size() > 0) {
                            w.this.d();
                            w.this.j.postDelayed(this, DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
                            return;
                        }
                        w.this.k.compareAndSet(true, false);
                        w.this.j.removeCallbacks(this);
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar) {
        if (this.a == d.ON) {
            bVar.c();
            return;
        }
        d();
        i.add(new c(Long.valueOf(System.currentTimeMillis()), bVar));
        e();
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        if (System.currentTimeMillis() - this.l > this.f) {
            a(0);
        }
    }
}
