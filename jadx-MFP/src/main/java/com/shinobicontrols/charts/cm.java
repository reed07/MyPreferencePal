package com.shinobicontrols.charts;

import android.graphics.PointF;
import com.shinobicontrols.charts.Series.SelectionMode;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;

class cm implements OnGestureListener {
    boolean a;
    private final v b;
    private final cn c;

    public void onDoubleTapDown(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onDoubleTapUp(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onLongTouchDown(ShinobiChart shinobiChart, PointF pointF) {
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

    public void onSwipe(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
    }

    public void onSwipeEnd(ShinobiChart shinobiChart, PointF pointF, boolean z, PointF pointF2) {
    }

    cm(v vVar) {
        this.b = vVar;
        this.c = new cn(vVar);
    }

    public void onSingleTouchUp(ShinobiChart shinobiChart, PointF pointF) {
        a(pointF);
    }

    private void a(PointF pointF) {
        if (this.b.l()) {
            c(pointF);
        } else {
            b(pointF);
        }
    }

    private void b(PointF pointF) {
        a a2 = this.c.a(pointF, b.SELECTION_MODE_NOT_NONE);
        if (a.b(a2)) {
            CartesianSeries cartesianSeries = (CartesianSeries) a2.c();
            InternalDataPoint b2 = a2.b();
            boolean z = true;
            switch (cartesianSeries.s) {
                case POINT_SINGLE:
                case POINT_MULTIPLE:
                    synchronized (x.a) {
                        if (cartesianSeries.s != SelectionMode.POINT_MULTIPLE) {
                            if (!b2.h) {
                                for (InternalDataPoint internalDataPoint : cartesianSeries.n.c) {
                                    if (internalDataPoint != b2) {
                                        cartesianSeries.a(internalDataPoint, false);
                                    }
                                }
                                cartesianSeries.a(b2, true);
                            }
                        }
                        if (b2.h) {
                            z = false;
                        }
                        cartesianSeries.a(b2, z);
                    }
                    break;
                case SERIES:
                    synchronized (x.a) {
                        if (!this.a) {
                            if (cartesianSeries.d) {
                                z = false;
                            }
                            cartesianSeries.setSelected(z);
                        } else if (cartesianSeries.d) {
                            cartesianSeries.setSelected(false);
                        } else {
                            for (CartesianSeries cartesianSeries2 : this.b.h()) {
                                if (cartesianSeries2 != cartesianSeries) {
                                    cartesianSeries2.setSelected(false);
                                }
                            }
                            cartesianSeries.setSelected(true);
                        }
                    }
                    break;
            }
            if (cartesianSeries.s != SelectionMode.NONE) {
                this.b.b.e();
                this.b.b.invalidate();
                this.b.redrawChart();
            }
        }
    }

    private void c(PointF pointF) {
        a a2 = this.c.a(pointF);
        if (a.b(a2)) {
            PieDonutSeries pieDonutSeries = (PieDonutSeries) a2.c();
            PieDonutSlice pieDonutSlice = (PieDonutSlice) a2.b();
            switch (pieDonutSeries.s) {
                case POINT_SINGLE:
                case POINT_MULTIPLE:
                    a(!pieDonutSlice.h, pieDonutSeries, pieDonutSlice, pieDonutSeries.s, true);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z, PieDonutSeries<?> pieDonutSeries, PieDonutSlice pieDonutSlice, SelectionMode selectionMode, boolean z2) {
        boolean a2;
        synchronized (x.a) {
            boolean z3 = false;
            if (selectionMode == SelectionMode.POINT_SINGLE && !pieDonutSlice.h) {
                boolean z4 = false;
                for (InternalDataPoint internalDataPoint : pieDonutSeries.n.c) {
                    PieDonutSlice pieDonutSlice2 = (PieDonutSlice) internalDataPoint;
                    if (pieDonutSlice2 != pieDonutSlice) {
                        z4 |= pieDonutSeries.a((InternalDataPoint) pieDonutSlice2, false);
                    }
                }
                z3 = z4;
            }
            a2 = pieDonutSeries.a((InternalDataPoint) pieDonutSlice, z) | z3;
        }
        if (a2) {
            pieDonutSeries.a(pieDonutSlice, z2);
        }
    }
}
