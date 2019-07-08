package com.moat.analytics.mobile.und;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class i {
    private static final i a = new i();
    /* access modifiers changed from: private */
    public final Map<j, String> b = new WeakHashMap();
    /* access modifiers changed from: private */
    public final Map<b, String> c = new WeakHashMap();
    private final ScheduledExecutorService d = Executors.newScheduledThreadPool(1);
    /* access modifiers changed from: private */
    public ScheduledFuture<?> e;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> f;

    private i() {
    }

    static i a() {
        return a;
    }

    private void a(final Context context) {
        ScheduledFuture<?> scheduledFuture = this.f;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            p.a(3, "JSUpdateLooper", (Object) this, "Starting metadata reporting loop");
            this.f = this.d.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                        if (i.this.b.isEmpty()) {
                            i.this.f.cancel(true);
                        }
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 0, 50, TimeUnit.MILLISECONDS);
        }
    }

    private void b(final Context context) {
        ScheduledFuture<?> scheduledFuture = this.e;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            p.a(3, "JSUpdateLooper", (Object) this, "Starting view update loop");
            this.e = this.d.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                        if (i.this.c.isEmpty()) {
                            p.a(3, "JSUpdateLooper", (Object) i.this, "No more active trackers");
                            i.this.e.cancel(true);
                        }
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 0, (long) w.a().d, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context, b bVar) {
        if (bVar != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("addActiveTracker");
            sb.append(bVar.hashCode());
            p.a(3, "JSUpdateLooper", (Object) this, sb.toString());
            Map<b, String> map = this.c;
            if (map != null && !map.containsKey(bVar)) {
                this.c.put(bVar, "");
                b(context);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context, j jVar) {
        Map<j, String> map = this.b;
        if (map != null && jVar != null) {
            map.put(jVar, "");
            a(context);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar) {
        if (bVar != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("removeActiveTracker");
            sb.append(bVar.hashCode());
            p.a(3, "JSUpdateLooper", (Object) this, sb.toString());
            Map<b, String> map = this.c;
            if (map != null) {
                map.remove(bVar);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(j jVar) {
        if (jVar != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("removeSetupNeededBridge");
            sb.append(jVar.hashCode());
            p.a(3, "JSUpdateLooper", (Object) this, sb.toString());
            Map<j, String> map = this.b;
            if (map != null) {
                map.remove(jVar);
            }
        }
    }
}
