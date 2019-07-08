package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: IMASDK */
public final class lr {
    public final int a;
    public final lo b;
    private final CopyOnWriteArrayList<mb> c;
    private final long d;

    public lr() {
        this(new CopyOnWriteArrayList(), 0, null, 0);
    }

    private lr(CopyOnWriteArrayList<mb> copyOnWriteArrayList, int i, lo loVar, long j) {
        this.c = copyOnWriteArrayList;
        this.a = i;
        this.b = loVar;
        this.d = j;
    }

    public final lr a(int i, lo loVar, long j) {
        lr lrVar = new lr(this.c, 0, loVar, j);
        return lrVar;
    }

    public final void a(Handler handler, lq lqVar) {
        qi.b((handler == null || lqVar == null) ? false : true);
        this.c.add(new mb(handler, lqVar));
    }

    public final void a(lq lqVar) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            if (mbVar.b == lqVar) {
                this.c.remove(mbVar);
            }
        }
    }

    public final void a() {
        lo loVar = (lo) qi.a(this.b);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            a(mbVar.a, (Runnable) new ls(this, mbVar.b, loVar));
        }
    }

    public final void b() {
        lo loVar = (lo) qi.a(this.b);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            a(mbVar.a, (Runnable) new lt(this, mbVar.b, loVar));
        }
    }

    public final void a(sr srVar, int i, long j) {
        a(srVar, i, -1, (bs) null, 0, (Object) null, -9223372036854775807L, -9223372036854775807L, j);
    }

    public final void a(sr srVar, int i, int i2, bs bsVar, int i3, Object obj, long j, long j2, long j3) {
        sr srVar2 = srVar;
        mc mcVar = new mc(srVar2, srVar2.a, Collections.emptyMap(), j3, 0, 0);
        md mdVar = new md(i, i2, bsVar, i3, obj, a(j), a(j2));
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            a(mbVar.a, (Runnable) new lu(this, mbVar.b, mcVar, mdVar));
        }
    }

    public final void a(sr srVar, Uri uri, Map<String, List<String>> map, int i, long j, long j2, long j3) {
        a(srVar, uri, map, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
    }

    public final void a(sr srVar, Uri uri, Map<String, List<String>> map, int i, int i2, bs bsVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        mc mcVar = new mc(srVar, uri, map, j3, j4, j5);
        md mdVar = new md(i, i2, bsVar, i3, obj, a(j), a(j2));
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            a(mbVar.a, (Runnable) new lv(this, mbVar.b, mcVar, mdVar));
        }
    }

    public final void b(sr srVar, Uri uri, Map<String, List<String>> map, int i, long j, long j2, long j3) {
        b(srVar, uri, map, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
    }

    public final void b(sr srVar, Uri uri, Map<String, List<String>> map, int i, int i2, bs bsVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        mc mcVar = new mc(srVar, uri, map, j3, j4, j5);
        md mdVar = new md(i, i2, bsVar, i3, obj, a(j), a(j2));
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            a(mbVar.a, (Runnable) new lw(this, mbVar.b, mcVar, mdVar));
        }
    }

    public final void a(sr srVar, Uri uri, Map<String, List<String>> map, int i, long j, long j2, long j3, IOException iOException, boolean z) {
        a(srVar, uri, map, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3, iOException, z);
    }

    public final void a(sr srVar, Uri uri, Map<String, List<String>> map, int i, int i2, bs bsVar, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
        mc mcVar = new mc(srVar, uri, map, j3, j4, j5);
        md mdVar = new md(i, i2, bsVar, i3, obj, a(j), a(j2));
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            lq lqVar = mbVar.b;
            Handler handler = mbVar.a;
            lx lxVar = new lx(this, lqVar, mcVar, mdVar, iOException, z);
            a(handler, (Runnable) lxVar);
        }
    }

    public final void c() {
        lo loVar = (lo) qi.a(this.b);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            a(mbVar.a, (Runnable) new ly(this, mbVar.b, loVar));
        }
    }

    public final void a(int i, long j, long j2) {
        long j3 = j;
        md mdVar = new md(1, i, null, 3, null, a(j), a(j2));
        lo loVar = (lo) qi.a(this.b);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            a(mbVar.a, (Runnable) new lz(this, mbVar.b, loVar, mdVar));
        }
    }

    public final void a(int i, bs bsVar, int i2, Object obj, long j) {
        md mdVar = new md(1, i, bsVar, i2, obj, a(j), -9223372036854775807L);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            mb mbVar = (mb) it.next();
            a(mbVar.a, (Runnable) new ma(this, mbVar.b, mdVar));
        }
    }

    private final long a(long j) {
        long a2 = at.a(j);
        if (a2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return this.d + a2;
    }

    private static void a(Handler handler, Runnable runnable) {
        if (handler.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }
}
