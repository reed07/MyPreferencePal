package com.inmobi.commons.core.b;

import android.support.annotation.NonNull;
import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.c.b;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: EventProcessor */
public class d implements b {
    private static final String a = "d";
    private AtomicBoolean b = new AtomicBoolean(false);
    private AtomicBoolean c = new AtomicBoolean(false);
    private b d;
    private e e;
    private HashMap<String, a> f = new HashMap<>(1);
    private List<String> g = new LinkedList();
    private ScheduledExecutorService h;

    public d(@NonNull b bVar, @NonNull e eVar, @NonNull a aVar) {
        this.d = bVar;
        this.e = eVar;
        a(aVar);
    }

    public final void a() {
        ScheduledExecutorService scheduledExecutorService = this.h;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.h = null;
        }
        this.b.set(false);
        this.c.set(true);
        this.g.clear();
        this.f.clear();
    }

    @NonNull
    private a b(@NonNull String str) {
        return (a) this.f.get(str);
    }

    /* access modifiers changed from: private */
    public void a(String str, com.inmobi.commons.core.utilities.uid.d dVar) {
        int i;
        long j;
        if (!this.c.get() && !this.b.get()) {
            this.d.b(b(str).a, str);
            int a2 = this.d.a(str);
            int a3 = com.inmobi.commons.core.utilities.b.b.a();
            if (a3 != 1) {
                i = b(str).i;
            } else {
                i = b(str).g;
            }
            if (a3 != 1) {
                j = b(str).j;
            } else {
                j = b(str).h;
            }
            if (i <= a2 || this.d.a(b(str).c, str)) {
                c a4 = this.e.a(str);
                if (a4 != null) {
                    this.b.set(true);
                    a b2 = b(str);
                    int i2 = b2.d + 1;
                    a.a().a(a4, b2.e, i2, i2, j, dVar, this);
                }
            }
        }
    }

    public final void a(c cVar) {
        String b2 = this.d.b(((Integer) cVar.a.get(0)).intValue());
        this.d.a(cVar.a);
        if (b2 != null) {
            this.d.c(System.currentTimeMillis(), b2);
            this.b.set(false);
        }
    }

    public final void a(c cVar, boolean z) {
        String b2 = this.d.b(((Integer) cVar.a.get(0)).intValue());
        if (cVar.c && z) {
            this.d.a(cVar.a);
        }
        if (b2 != null) {
            this.d.c(System.currentTimeMillis(), b2);
            this.b.set(false);
        }
    }

    public final void a(@NonNull a aVar) {
        String str = aVar.b;
        if (str == null) {
            str = "default";
        }
        this.f.put(str, aVar);
    }

    public final void a(@NonNull final String str) {
        if (!this.c.get()) {
            if (str == null) {
                str = "default";
            }
            long j = b(str).f;
            if (j <= 0) {
                a(str, (com.inmobi.commons.core.utilities.uid.d) null);
            } else if (!this.g.contains(str)) {
                this.g.add(str);
                if (this.h == null) {
                    this.h = Executors.newSingleThreadScheduledExecutor();
                }
                ScheduledExecutorService scheduledExecutorService = this.h;
                AnonymousClass1 r3 = new Runnable() {
                    final /* synthetic */ com.inmobi.commons.core.utilities.uid.d b = null;

                    public final void run() {
                        d.this.a(str, this.b);
                    }
                };
                a b2 = b(str);
                long b3 = this.d.b(str);
                if (b3 == -1) {
                    this.d.c(System.currentTimeMillis(), str);
                }
                long seconds = TimeUnit.MILLISECONDS.toSeconds(b3) + b2.f;
                scheduledExecutorService.scheduleAtFixedRate(r3, seconds - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) > 0 ? seconds - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) : 0, j, TimeUnit.SECONDS);
            }
        }
    }
}
