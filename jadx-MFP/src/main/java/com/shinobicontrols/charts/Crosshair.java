package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.PropertyChangedEvent.Handler;

public class Crosshair {
    a a;
    boolean b = true;
    Mode c = Mode.SINGLE_SERIES;
    OutOfRangeBehavior d = OutOfRangeBehavior.HIDE;
    DrawLinesBehavior e = DrawLinesBehavior.SERIES_DEFAULT;
    CartesianSeries<?> f;
    Tooltip g;
    private v h;
    private CrosshairStyle i;
    private final Paint j = new Paint();
    private final bz k = new bz();
    private float l;
    private Data<?, ?> m;
    private float n = BitmapDescriptorFactory.HUE_RED;
    private final ci o = new ci();
    private final Rect p = new Rect();
    private final b q = new b(this);
    private boolean r = true;

    public enum DrawLinesBehavior {
        ALWAYS,
        NEVER,
        SERIES_DEFAULT
    }

    public enum Mode {
        SINGLE_SERIES,
        FLOATING
    }

    public enum OutOfRangeBehavior {
        KEEP_AT_EDGE,
        HIDE,
        REMOVE
    }

    enum a {
        SHOWN,
        HIDDEN,
        REMOVED
    }

    private static class b implements Handler {
        private final Crosshair a;

        public b(Crosshair crosshair) {
            this.a = crosshair;
        }

        public void onPropertyChanged() {
            this.a.f();
        }
    }

    public Mode getMode() {
        return this.c;
    }

    public void setMode(Mode mode) {
        this.c = mode;
    }

    public OutOfRangeBehavior getOutOfRangeBehavior() {
        return this.d;
    }

    public void setOutOfRangeBehavior(OutOfRangeBehavior outOfRangeBehavior) {
        this.d = outOfRangeBehavior;
    }

    public DrawLinesBehavior getDrawLinesBehavior() {
        return this.e;
    }

    public void setDrawLinesBehavior(DrawLinesBehavior drawLinesBehavior) {
        this.e = drawLinesBehavior;
    }

    Crosshair() {
        this.j.setStyle(Style.STROKE);
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar) {
        l();
        this.h = vVar;
        if (vVar != null) {
            this.n = vVar.getResources().getDisplayMetrics().density;
            this.l = (float) at.a(this.n, 2.5f);
            this.g = new Tooltip(vVar.getContext());
            this.g.a((Handler) this.q);
            this.g.a(this.i);
            k();
        }
    }

    public CrosshairStyle getStyle() {
        return this.i;
    }

    public void setStyle(CrosshairStyle crosshairStyle) {
        this.i = crosshairStyle;
    }

    public float getPixelXValue() {
        if (this.h == null) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return ((float) this.k.b) + ((float) this.h.a.left);
    }

    public float getPixelYValue() {
        if (this.h == null) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return ((float) this.k.c) + ((float) this.h.a.top);
    }

    public Tooltip getTooltip() {
        return this.g;
    }

    public CartesianSeries<?> getTrackedSeries() {
        return this.f;
    }

    public Data<?, ?> getFocus() {
        return this.m;
    }

    public void setFocus(Data<?, ?> data) {
        this.m = data;
        if (data == null || data.getX() == null || data.getY() == null) {
            throw new IllegalArgumentException(this.g.getContext().getString(R.string.CrosshairNullXOrYInFocusPoint));
        }
        h();
    }

    public boolean isShown() {
        return this.a == a.SHOWN;
    }

    public void setLineSeriesInterpolationEnabled(boolean z) {
        this.b = z;
    }

    public boolean isLineSeriesInterpolationEnabled() {
        return this.b;
    }

    public boolean isActive() {
        return this.a == a.SHOWN || this.a == a.HIDDEN;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.h.b.d();
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        Tooltip tooltip = this.g;
        if (tooltip != null) {
            tooltip.requestLayout();
        }
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        v vVar = this.h;
        if (vVar != null) {
            vVar.b.g();
        }
        this.g.forceLayout();
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        switch (this.e) {
            case ALWAYS:
                return true;
            case NEVER:
                return false;
            case SERIES_DEFAULT:
                return this.f.l.b;
            default:
                throw new UnsupportedOperationException("drawLinesBehavior set incorrectly");
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Canvas canvas, Rect rect) {
        this.p.set(rect);
        if (!this.h.a(canvas, this.p, (float) this.k.b, (float) this.k.c, this.l, this.j)) {
            ChartUtils.drawCrosshair(this.h, canvas, this.p, (float) this.k.b, (float) this.k.c, this.l, this.j);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3) {
        Tooltip tooltip = this.g;
        if (tooltip != null) {
            tooltip.measure(i2, i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3, int i4, int i5) {
        Tooltip tooltip = this.g;
        if (tooltip != null) {
            int measuredWidth = tooltip.getMeasuredWidth();
            int measuredHeight = this.g.getMeasuredHeight();
            this.o.a(this.g.a.b, this.g.a.c, this.g.a.b + ((double) measuredWidth), this.g.a.c + ((double) measuredHeight));
            this.o.a(((double) (-measuredWidth)) / 2.0d, ((double) (-measuredHeight)) / 2.0d);
            int i6 = i2;
            a(this.o, i2, i4);
            c(this.o, i3, i5);
            at.a((View) this.g, this.o);
        }
    }

    /* access modifiers changed from: 0000 */
    @SuppressLint({"WrongCall"})
    public void b(Canvas canvas, Rect rect) {
        a(canvas, rect);
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        boolean isActive = isActive();
        this.a = a.REMOVED;
        this.f = null;
        this.m = null;
        this.g.b();
        a();
        if (isActive) {
            v vVar = this.h;
            if (vVar != null) {
                vVar.m();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(CartesianSeries<?> cartesianSeries, DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3) {
        this.f = cartesianSeries;
        if (!this.h.b(cartesianSeries, dataPoint, dataPoint2, dataPoint3)) {
            setFocus(cartesianSeries.l.a(dataPoint, dataPoint2, dataPoint3, this.b));
        }
        b(cartesianSeries, dataPoint, dataPoint2, dataPoint3);
        i();
        a();
        b();
    }

    private void h() {
        if (this.f != null) {
            Data<?, ?> data = this.m;
            if (data == null) {
                v vVar = this.h;
                throw new IllegalStateException(vVar != null ? vVar.getContext().getString(R.string.CrosshairNullFocus) : "Unable to determine Crosshair position: must have non-null focus. Have you called setFocus on the crosshair?");
            }
            this.k.b = a(data.getX(), this.f.getXAxis(), this.f);
            this.k.c = a(this.m.getY(), this.f.getYAxis(), this.f);
        }
    }

    static double a(Object obj, Axis<?, ?> axis, CartesianSeries<?> cartesianSeries) {
        return axis.a(axis.translatePoint(obj), cartesianSeries);
    }

    private void b(CartesianSeries<?> cartesianSeries, DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3) {
        Tooltip tooltip = this.g;
        if (tooltip != null) {
            tooltip.a(cartesianSeries);
            if (!this.h.a(cartesianSeries, dataPoint, dataPoint2, dataPoint3)) {
                this.g.a(cartesianSeries, dataPoint, dataPoint2, dataPoint3);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        CrosshairStyle crosshairStyle = this.i;
        if (crosshairStyle != null) {
            this.j.setColor(crosshairStyle.getLineColor());
            this.j.setStrokeWidth((float) at.a(this.n, this.i.getLineWidth()));
            Tooltip tooltip = this.g;
            if (tooltip != null) {
                tooltip.a(this.i);
            }
        }
    }

    private void i() {
        if (j()) {
            m();
            return;
        }
        switch (this.d) {
            case HIDE:
                o();
                return;
            case REMOVE:
                e();
                return;
            default:
                return;
        }
    }

    private boolean j() {
        Rect rect = this.h.b.b;
        return !rect.isEmpty() && this.k.b >= ((double) rect.left) && this.k.b <= ((double) rect.right) && this.k.c >= ((double) rect.top) && this.k.c <= ((double) rect.bottom);
    }

    private void k() {
        this.h.b.a(this.g);
    }

    private void l() {
        v vVar = this.h;
        if (vVar != null) {
            vVar.b.b(this.g);
        }
    }

    private void m() {
        boolean isActive = isActive();
        this.a = a.SHOWN;
        n();
        if (!isActive) {
            v vVar = this.h;
            if (vVar != null) {
                vVar.m();
            }
        }
    }

    private void n() {
        if (this.r) {
            this.g.c();
        } else {
            this.g.d();
        }
    }

    private void o() {
        boolean isActive = isActive();
        this.a = a.HIDDEN;
        this.g.d();
        if (!isActive) {
            v vVar = this.h;
            if (vVar != null) {
                vVar.m();
            }
        }
    }

    private void a(ci ciVar, int i2, int i3) {
        if (ciVar.a() > ((double) (i3 - i2))) {
            b(ciVar, i2, i3);
            return;
        }
        a(ciVar, i2);
        b(ciVar, i3);
    }

    private void b(ci ciVar, int i2, int i3) {
        ciVar.b(((double) i2) - ((ciVar.a() - ((double) (i3 - i2))) / 2.0d), ciVar.b);
    }

    private void a(ci ciVar, int i2) {
        double d2 = (double) i2;
        if (ciVar.a < d2) {
            ciVar.b(d2, ciVar.b);
        }
    }

    private void b(ci ciVar, int i2) {
        double d2 = (double) i2;
        if (ciVar.c > d2) {
            ciVar.b(d2 - ciVar.a(), ciVar.b);
        }
    }

    private void c(ci ciVar, int i2, int i3) {
        if (ciVar.b() > ((double) (i3 - i2))) {
            d(ciVar, i2, i3);
            return;
        }
        c(ciVar, i2);
        d(ciVar, i3);
    }

    private void d(ci ciVar, int i2, int i3) {
        ciVar.b(ciVar.a, ((double) i2) - ((ciVar.b() - ((double) (i3 - i2))) / 2.0d));
    }

    private void c(ci ciVar, int i2) {
        double d2 = (double) i2;
        if (ciVar.b < d2) {
            ciVar.b(ciVar.a, d2);
        }
    }

    private void d(ci ciVar, int i2) {
        double d2 = (double) i2;
        if (ciVar.d > d2) {
            ciVar.a(0.0d, -(ciVar.d - d2));
            ciVar.b(ciVar.a, d2 - ciVar.b());
        }
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        h();
        Tooltip tooltip = this.g;
        if (tooltip != null) {
            tooltip.a();
        }
        i();
    }

    /* access modifiers changed from: 0000 */
    public void a(Series<?> series) {
        if (this.f == series) {
            e();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Series<?> series) {
        if (this.f == series) {
            e();
        }
    }

    public boolean isTooltipEnabled() {
        return this.r;
    }

    public void enableTooltip(boolean z) {
        this.r = z;
    }
}
