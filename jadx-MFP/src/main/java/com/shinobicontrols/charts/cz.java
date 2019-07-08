package com.shinobicontrols.charts;

import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class cz {
    private final Handler a;
    private c b;
    private a c;
    private b d;
    private final OnGestureListener e;
    private final Queue<d> f = new ConcurrentLinkedQueue();
    private final v g;
    private final y h;
    private boolean i = false;

    private static class a extends d {
        a(v vVar, PointF pointF, Queue<d> queue, OnGestureListener onGestureListener) {
            super(vVar, pointF, queue, onGestureListener);
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.d.a(this.a);
            this.c.onDoubleTapDown(this.d, this.a);
        }

        /* access modifiers changed from: protected */
        public void b() {
            this.d.b(this.a);
            this.c.onDoubleTapUp(this.d, this.a);
        }
    }

    private static class b extends d {
        b(v vVar, PointF pointF, Queue<d> queue, OnGestureListener onGestureListener) {
            super(vVar, pointF, queue, onGestureListener);
        }

        public void run() {
            super.run();
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.d.c(this.a);
            this.c.onLongTouchDown(this.d, this.a);
            this.b.clear();
        }

        /* access modifiers changed from: protected */
        public void b() {
            this.d.d(this.a);
            this.c.onLongTouchUp(this.d, this.a);
        }
    }

    private static class c extends d {
        c(v vVar, PointF pointF, Queue<d> queue, OnGestureListener onGestureListener) {
            super(vVar, pointF, queue, onGestureListener);
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.d.e(this.a);
            this.c.onSingleTouchDown(this.d, this.a);
        }

        /* access modifiers changed from: protected */
        public void b() {
            this.d.f(this.a);
            this.c.onSingleTouchUp(this.d, this.a);
        }
    }

    private static abstract class d implements Runnable {
        protected final PointF a;
        protected final Queue<d> b;
        protected final OnGestureListener c;
        protected final v d;

        /* access modifiers changed from: protected */
        public abstract void a();

        /* access modifiers changed from: protected */
        public abstract void b();

        d(v vVar, PointF pointF, Queue<d> queue, OnGestureListener onGestureListener) {
            this.d = vVar;
            this.a = pointF;
            this.b = queue;
            this.c = onGestureListener;
        }

        public void run() {
            a();
            this.b.add(this);
        }
    }

    cz(v vVar, OnGestureListener onGestureListener, y yVar) {
        this.g = vVar;
        this.e = onGestureListener;
        this.h = yVar;
        this.a = new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i2, int i3, cb cbVar) {
        if (i2 == 0 && i3 == 1) {
            a(cbVar);
            return true;
        } else if (i2 == 1 && i3 == 1) {
            b(cbVar);
            return true;
        } else if (i2 == 1 && i3 == 0) {
            c(cbVar);
            return true;
        } else {
            if (i2 == 1 && i3 == 2) {
                b();
            }
            return false;
        }
    }

    private void a(cb cbVar) {
        long f2 = cbVar.f();
        if (f2 <= 0 || f2 >= ((long) this.h.d)) {
            this.b = new c(this.g, cbVar.e().a, this.f, this.e);
            this.b.run();
            this.d = new b(this.g, cbVar.e().a, this.f, this.e);
            this.a.postDelayed(this.d, (long) this.h.e);
            return;
        }
        this.c = new a(this.g, cbVar.e().a, this.f, this.e);
        this.c.run();
    }

    private void b(cb cbVar) {
        boolean z = this.i;
        this.i = a(cbVar.d().a());
        if (this.i) {
            this.a.removeCallbacks(this.d);
            this.f.clear();
            PointF pointF = (z ? cbVar.c() : cbVar.e()).a;
            this.g.c(pointF, cbVar.h().a);
            this.e.onSwipe(this.g, pointF, cbVar.h().a);
            cbVar.i();
        }
    }

    private boolean a(float f2) {
        return this.i || f2 > ((float) this.h.a());
    }

    private void c(cb cbVar) {
        this.a.removeCallbacks(this.d);
        boolean z = false;
        this.i = false;
        if (cbVar.a((float) this.h.a())) {
            VectorF g2 = cbVar.g();
            if (g2.a() > ((float) this.h.c)) {
                z = true;
            }
            this.g.b(cbVar.h().a, z, g2);
            this.e.onSwipeEnd(this.g, cbVar.h().a, z, g2);
        }
        c();
        cbVar.a();
    }

    private void b() {
        this.a.removeCallbacks(this.d);
        this.f.clear();
    }

    private void c() {
        while (!this.f.isEmpty()) {
            ((d) this.f.poll()).b();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.a.removeCallbacks(this.d);
    }
}
