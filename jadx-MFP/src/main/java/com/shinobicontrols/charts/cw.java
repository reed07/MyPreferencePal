package com.shinobicontrols.charts;

import android.graphics.PointF;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;

class cw implements OnGestureListener {
    private final v a;
    private final cn b;

    public void onDoubleTapDown(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onDoubleTapUp(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onLongTouchUp(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onPinch(ShinobiChart shinobiChart, PointF pointF, PointF pointF2, PointF pointF3) {
    }

    public void onPinchEnd(ShinobiChart shinobiChart, PointF pointF, boolean z, PointF pointF2) {
    }

    public void onSecondTouchDown(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
    }

    public void onSecondTouchUp(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
    }

    public void onSingleTouchDown(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onSwipeEnd(ShinobiChart shinobiChart, PointF pointF, boolean z, PointF pointF2) {
    }

    cw(v vVar) {
        this.a = vVar;
        this.b = new cn(vVar);
    }

    public void onLongTouchDown(ShinobiChart shinobiChart, PointF pointF) {
        a(pointF);
    }

    public void onSingleTouchUp(ShinobiChart shinobiChart, PointF pointF) {
        c(pointF);
    }

    public void onSwipe(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
        b(pointF2);
    }

    private void a(PointF pointF) {
        Crosshair crosshair = this.a.h;
        if (crosshair != null && this.a.r()) {
            if (this.a.m.e >= 60 || !crosshair.isShown()) {
                a a2 = this.b.a(pointF, b.CROSSHAIR_ENABLED);
                if (a.b(a2)) {
                    a(a2);
                }
            } else if (crosshair.isShown()) {
                crosshair.e();
            }
        }
    }

    private void b(PointF pointF) {
        a aVar;
        Crosshair crosshair = this.a.h;
        if (crosshair != null) {
            switch (crosshair.c) {
                case SINGLE_SERIES:
                    aVar = this.b.a(crosshair.getTrackedSeries(), pointF, true);
                    break;
                case FLOATING:
                    aVar = this.b.a(pointF, b.CROSSHAIR_ENABLED);
                    break;
                default:
                    aVar = null;
                    break;
            }
            if (a.b(aVar)) {
                a(aVar);
            }
        }
    }

    private void a(a aVar) {
        CartesianSeries cartesianSeries = (CartesianSeries) aVar.c();
        InternalDataPoint b2 = aVar.b();
        DataPoint a2 = ab.a(b2, cartesianSeries);
        DataPoint b3 = ab.b(b2, cartesianSeries);
        bz d = aVar.d();
        this.a.h.a(cartesianSeries, a2, b3, d != null ? ab.a(d, b2, cartesianSeries) : null);
    }

    private void c(PointF pointF) {
        if (this.a.m.e >= 60) {
            Crosshair crosshair = this.a.h;
            if (crosshair != null) {
                crosshair.e();
            }
        }
    }
}
