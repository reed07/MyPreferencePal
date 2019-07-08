package com.shinobicontrols.charts;

import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;
import java.util.ArrayList;
import java.util.List;

class bn implements OnGestureListener {
    private final a a = new a(this);
    private final Handler b;
    private final List<OnGestureListener> c = new ArrayList();
    private OnGestureListener d;
    private OnGestureListener e;
    private final v f;
    private b g = b.NO_GESTURE;
    private final y h;

    private static class a implements Runnable {
        final bn a;
        ShinobiChart b;
        PointF c;

        public a(bn bnVar) {
            this.a = bnVar;
        }

        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    private enum b {
        NO_GESTURE,
        PANNING,
        ZOOMING
    }

    public void onLongTouchUp(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onSecondTouchDown(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
    }

    public void onSecondTouchUp(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
    }

    public bn(v vVar) {
        this.f = vVar;
        this.h = vVar.m;
        this.b = new Handler(Looper.getMainLooper());
    }

    public void onDoubleTapDown(ShinobiChart shinobiChart, PointF pointF) {
        this.b.removeCallbacks(this.a);
    }

    public void onDoubleTapUp(ShinobiChart shinobiChart, PointF pointF) {
        for (OnGestureListener onDoubleTapUp : this.c) {
            onDoubleTapUp.onDoubleTapUp(shinobiChart, pointF);
        }
    }

    public void onLongTouchDown(ShinobiChart shinobiChart, PointF pointF) {
        this.d.onLongTouchDown(this.f, pointF);
    }

    public void onSingleTouchDown(ShinobiChart shinobiChart, PointF pointF) {
        if (this.f.h == null || !this.f.h.isActive()) {
            for (OnGestureListener onSingleTouchDown : this.c) {
                onSingleTouchDown.onSingleTouchDown(shinobiChart, pointF);
            }
        }
    }

    public void onSingleTouchUp(ShinobiChart shinobiChart, PointF pointF) {
        if (this.f.q()) {
            a aVar = this.a;
            aVar.b = shinobiChart;
            aVar.c = pointF;
            this.b.postDelayed(aVar, (long) this.h.d);
            return;
        }
        a(shinobiChart, pointF);
    }

    /* access modifiers changed from: private */
    public void a(ShinobiChart shinobiChart, PointF pointF) {
        if (this.f.h == null || !this.f.h.isActive()) {
            this.e.onSingleTouchUp(shinobiChart, pointF);
        } else {
            this.d.onSingleTouchUp(shinobiChart, pointF);
        }
    }

    public void onSwipe(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
        if (this.f.h == null || !this.f.h.isActive()) {
            if (this.g != b.PANNING) {
                this.g = b.PANNING;
            }
            for (OnGestureListener onSwipe : this.c) {
                onSwipe.onSwipe(shinobiChart, pointF, pointF2);
            }
            return;
        }
        this.d.onSwipe(shinobiChart, pointF, pointF2);
    }

    public void onSwipeEnd(ShinobiChart shinobiChart, PointF pointF, boolean z, PointF pointF2) {
        if (this.f.h == null || !this.f.h.isActive()) {
            this.g = b.NO_GESTURE;
            for (OnGestureListener onSwipeEnd : this.c) {
                onSwipeEnd.onSwipeEnd(shinobiChart, pointF, z, pointF2);
            }
        }
    }

    public void onPinch(ShinobiChart shinobiChart, PointF pointF, PointF pointF2, PointF pointF3) {
        if (this.g != b.ZOOMING) {
            this.g = b.ZOOMING;
        }
        for (OnGestureListener onPinch : this.c) {
            onPinch.onPinch(shinobiChart, pointF, pointF2, pointF3);
        }
    }

    public void onPinchEnd(ShinobiChart shinobiChart, PointF pointF, boolean z, PointF pointF2) {
        this.g = b.NO_GESTURE;
        for (OnGestureListener onPinchEnd : this.c) {
            onPinchEnd.onPinchEnd(shinobiChart, pointF, z, pointF2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(OnGestureListener onGestureListener) {
        this.c.add(onGestureListener);
    }

    /* access modifiers changed from: 0000 */
    public void b(OnGestureListener onGestureListener) {
        this.c.remove(onGestureListener);
    }

    /* access modifiers changed from: 0000 */
    public void c(OnGestureListener onGestureListener) {
        this.d = onGestureListener;
    }

    /* access modifiers changed from: 0000 */
    public void d(OnGestureListener onGestureListener) {
        this.e = onGestureListener;
    }
}
