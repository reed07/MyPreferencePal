package com.shinobicontrols.charts;

import android.graphics.PointF;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.Axis.DoubleTapBehavior;
import com.shinobicontrols.charts.Axis.MotionState;
import com.shinobicontrols.charts.Axis.Orientation;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;
import java.util.Locale;

class ch implements OnGestureListener {
    boolean a = true;
    boolean b = false;
    boolean c = true;
    boolean d = true;
    boolean e = false;
    boolean f = false;
    boolean g = false;
    boolean h = false;
    float i = 1.2f;
    float j = 0.75f;
    boolean k = true;
    MotionState l = MotionState.STOPPED;
    /* access modifiers changed from: private */
    public final Axis<?, ?> m;
    private final b n = new b();
    private final c o = new c(this);
    private final e p = new e(this);
    private final a q = new a(this);
    private final b r = new b(this);
    private double s = 1.0d;
    private final NumberRange t = new NumberRange();
    private final NumberRange u = new NumberRange();
    private final EaseOutAnimationCurve v = new EaseOutAnimationCurve();
    private final bm w = new bm();

    private static class a extends d implements a {
        a(ch chVar) {
            super(chVar);
        }

        /* access modifiers changed from: protected */
        public void a(double d) {
            b(d);
            this.a.d(this.f, this.g);
        }

        public void a() {
            this.a.m();
        }

        public void b() {
            this.a.n();
        }

        public void c() {
            this.a.o();
        }
    }

    private static class b extends d {
        b(ch chVar) {
            super(chVar);
        }

        /* access modifiers changed from: protected */
        public void a(double d) {
            b(d);
            this.a.e(this.f, this.g);
        }

        public void a() {
            this.a.p();
        }

        public void b() {
            this.a.q();
        }

        public void c() {
            this.a.r();
        }
    }

    private static class c extends d {
        public void a() {
        }

        c(ch chVar) {
            super(chVar);
        }

        /* access modifiers changed from: protected */
        public void a(double d) {
            b(d);
            this.a.b(this.f, this.g);
        }

        public void b() {
            this.a.i();
        }

        public void c() {
            this.a.j();
        }
    }

    private static abstract class d implements a {
        protected final ch a;
        protected double b;
        protected double c;
        protected double d;
        protected double e;
        protected double f;
        protected double g;
        protected boolean h;

        /* access modifiers changed from: protected */
        public abstract void a(double d2);

        d(ch chVar) {
            this.a = chVar;
        }

        public void a(Animation animation) {
            if (animation instanceof g) {
                a((double) ((g) animation).d());
            }
        }

        /* access modifiers changed from: protected */
        public void a(double d2, double d3) {
            this.b = d2;
            this.c = d3;
            this.f = this.b;
            this.g = this.c;
        }

        /* access modifiers changed from: protected */
        public void a(double d2, double d3, boolean z) {
            this.d = d2;
            this.e = d3;
            this.h = z;
        }

        /* access modifiers changed from: protected */
        public void b(double d2) {
            double d3 = this.b;
            this.f = d3 + ((this.d - d3) * d2);
            double d4 = this.c;
            this.g = d4 + (d2 * (this.e - d4));
            if (Double.isNaN(this.f) || Double.isInfinite(this.f)) {
                this.f = this.a.m.i.a;
            }
            if (Double.isNaN(this.g) || Double.isInfinite(this.g)) {
                this.g = this.a.m.i.b;
            }
            if (this.g < this.f) {
                this.f = this.d;
                this.g = this.e;
            }
        }
    }

    private static class e extends d {
        private double i;
        private double j;
        private double k;

        public void a() {
        }

        e(ch chVar) {
            super(chVar);
        }

        /* access modifiers changed from: protected */
        public void a(double d) {
            this.a.c(this.j - (d * this.k), this.i);
        }

        public void b() {
            this.a.k();
        }

        public void c() {
            this.a.l();
        }

        /* access modifiers changed from: 0000 */
        public void b(double d, double d2) {
            this.i = d;
            this.j = d2;
            this.k = this.j - 1.0d;
        }
    }

    public void onDoubleTapDown(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onLongTouchDown(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onLongTouchUp(ShinobiChart shinobiChart, PointF pointF) {
    }

    public void onSecondTouchDown(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
    }

    public void onSecondTouchUp(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
    }

    public void onSingleTouchUp(ShinobiChart shinobiChart, PointF pointF) {
    }

    public ch(Axis<?, ?> axis) {
        this.m = axis;
    }

    public void onDoubleTapUp(ShinobiChart shinobiChart, PointF pointF) {
        double d2;
        double d3;
        double d4;
        if (!this.m.b.l() && this.k) {
            if (this.m.getDoubleTapBehavior() == DoubleTapBehavior.RESET_TO_DEFAULT_RANGE && (this.m.isGesturePanningEnabled() || this.m.isGestureZoomingEnabled())) {
                if (this.m.l != null) {
                    d4 = this.m.l.a;
                    d3 = this.m.l.b;
                } else {
                    d4 = this.m.e();
                    d3 = this.m.d();
                }
                this.n.b();
                this.q.a(d4, d3, this.d);
                a((d) this.q);
            } else if (this.m.isGestureZoomingEnabled()) {
                double b2 = this.m.i.b() / 4.0d;
                if (this.m.c.equals(Orientation.HORIZONTAL)) {
                    d2 = this.m.e((double) pointF.x);
                } else {
                    d2 = this.m.e((double) pointF.y);
                }
                this.n.b();
                this.q.a(d2 - b2, d2 + b2, this.d);
                a((d) this.q);
            }
        }
    }

    public void onSingleTouchDown(ShinobiChart shinobiChart, PointF pointF) {
        this.n.b();
    }

    public void onSwipe(ShinobiChart shinobiChart, PointF pointF, PointF pointF2) {
        if (this.e && !Range.a((Range<?>) this.m.i)) {
            this.n.b();
            VectorF a2 = VectorF.a(pointF, pointF2);
            double d2 = this.m.d((double) a(a2.x, a2.y));
            if (this.m.a()) {
                d2 = -d2;
            }
            a(d2);
        }
    }

    public void onSwipeEnd(ShinobiChart shinobiChart, PointF pointF, boolean z, PointF pointF2) {
        if (this.e && !Range.a((Range<?>) this.m.i)) {
            if (z) {
                a(pointF2);
            } else {
                h();
            }
        }
    }

    public void onPinch(ShinobiChart shinobiChart, PointF pointF, PointF pointF2, PointF pointF3) {
        if (this.f && !Range.a((Range<?>) this.m.i)) {
            this.n.b();
            a(this.m.e((double) a(pointF.x, pointF.y)), this.m.e((double) a(pointF2.x, pointF2.y)), a(pointF3.x, pointF3.y));
        }
    }

    public void onPinchEnd(ShinobiChart shinobiChart, PointF pointF, boolean z, PointF pointF2) {
        if (this.f && !Range.a((Range<?>) this.m.i)) {
            if (z) {
                a(pointF2, pointF);
            } else {
                a(z);
            }
        }
    }

    public void a() {
        if (!Range.a((Range<?>) this.m.i) && !s()) {
            if (!Range.a((Range<?>) this.m.j) || !Range.a((Range<?>) this.m.l)) {
                this.t.a(this.m.i.a, this.m.i.b);
                this.t.b(c(), e());
                this.q.a(this.t.a, this.t.b, this.d);
                if (a((d) this.q)) {
                    a(MotionState.STOPPED);
                }
            } else {
                cx.a(this.m.b != null ? this.m.b.getContext().getString(R.string.RangeManagerUnableToSetRange) : "Unable to set axis range as axis has no associated data and no default range set");
            }
        }
    }

    public boolean a(double d2, double d3) {
        return a(d2, d3, this.d, this.c);
    }

    public boolean a(double d2, double d3, boolean z, boolean z2) {
        if (!f(d2, d3)) {
            cx.a(this.m.b != null ? this.m.b.getContext().getString(R.string.RangeManagerInvalidRangeRequested) : "Invalid axis range requested");
            return false;
        }
        this.n.b();
        boolean z3 = true;
        this.t.a(d2, d3);
        this.u.a(d2, d3);
        if (!s()) {
            if (!Range.a((Range<?>) this.m.j) || !Range.a((Range<?>) this.m.l)) {
                if (d2 < c() || d3 > e()) {
                    z3 = false;
                }
                this.t.b(c(), e());
                this.u.b(b(z2), c(z2));
            } else {
                cx.a(this.m.b != null ? this.m.b.getContext().getString(R.string.RangeManagerUnableToSetRange) : "Unable to set axis range as axis has no associated data and no default range set");
                return false;
            }
        }
        this.q.a(this.u.a, this.u.b, z);
        this.r.a(this.t.a, this.t.b, true);
        if (a((d) this.q) && a((d) this.r)) {
            a(MotionState.STOPPED);
        }
        return z3;
    }

    public boolean a(double d2, boolean z, boolean z2) {
        this.n.b();
        double b2 = b(d2);
        this.u.a(this.m.i.a + b2, this.m.i.b + b2);
        this.u.b(b(z2), c(z2));
        this.o.a(this.u.a, this.u.b, z);
        return a((d) this.o);
    }

    public boolean b(double d2, double d3, boolean z, boolean z2) {
        if (this.m.j == null) {
            this.s = d2;
            return true;
        }
        this.n.b();
        h(d2, d3);
        this.u.a(this.t.a, this.t.b);
        this.u.b(b(z2), c(z2));
        this.p.a(this.u.a, this.u.b, z);
        return a((d) this.p);
    }

    public double b() {
        if (Range.a((Range<?>) this.m.i)) {
            return this.s;
        }
        if (!Range.a((Range<?>) this.m.l) || !Range.a((Range<?>) this.m.j)) {
            return g() / this.m.i.b();
        }
        return this.s;
    }

    private double g() {
        if (this.m.l != null) {
            return this.m.l.b();
        }
        return this.m.d() - this.m.e();
    }

    private void a(double d2) {
        a(MotionState.GESTURE);
        this.m.a(d2, false, this.c);
    }

    private void h() {
        w();
    }

    private void a(PointF pointF) {
        if (!this.m.isMomentumPanningEnabled() || u() || v()) {
            w();
        } else {
            d(this.m.d((double) (this.m.c == Orientation.HORIZONTAL ? -pointF.x : pointF.y)));
        }
    }

    /* access modifiers changed from: private */
    public void b(double d2, double d3) {
        this.t.a(d2, d3);
        boolean b2 = this.t.b(c(), e());
        this.m.a(this.t.a, this.t.b);
        if (b2) {
            this.n.b();
        } else {
            a(MotionState.MOMENTUM);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        a(MotionState.STOPPED);
    }

    /* access modifiers changed from: private */
    public void j() {
        a(MotionState.STOPPED);
    }

    private boolean a(double d2, double d3, float f2) {
        boolean z;
        boolean z2;
        if (f2 > BitmapDescriptorFactory.HUE_RED) {
            a(MotionState.GESTURE);
            Axis<?, ?> axis = this.m;
            z = axis.a(axis.w() * ((double) f2), d2, false, this.c);
        } else {
            z = false;
        }
        if (this.e) {
            z2 = this.m.a(d2 - d3, false, this.c);
        } else {
            z2 = false;
        }
        if (z2 || z) {
            return true;
        }
        return false;
    }

    private void a(boolean z) {
        w();
    }

    private void a(PointF pointF, PointF pointF2) {
        if (!this.h || u() || v()) {
            w();
            return;
        }
        g(this.m.e((double) a(pointF2.x, pointF2.y)), (double) a(pointF.x, pointF.y));
    }

    /* access modifiers changed from: private */
    public void c(double d2, double d3) {
        h(b() * d2, d3);
        boolean b2 = this.t.b(c(), e());
        this.m.a(this.t.a, this.t.b);
        if (b2) {
            this.n.b();
        } else {
            a(MotionState.MOMENTUM);
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        a(MotionState.STOPPED);
    }

    /* access modifiers changed from: private */
    public void l() {
        a(MotionState.STOPPED);
    }

    /* access modifiers changed from: private */
    public void d(double d2, double d3) {
        this.m.a(d2, d3);
    }

    /* access modifiers changed from: private */
    public void m() {
        a(MotionState.ANIMATING);
    }

    /* access modifiers changed from: private */
    public void n() {
        w();
    }

    /* access modifiers changed from: private */
    public void o() {
        a(MotionState.STOPPED);
    }

    /* access modifiers changed from: private */
    public void e(double d2, double d3) {
        this.m.a(d2, d3);
    }

    /* access modifiers changed from: private */
    public void p() {
        a(MotionState.BOUNCING);
    }

    /* access modifiers changed from: private */
    public void q() {
        a(MotionState.STOPPED);
    }

    /* access modifiers changed from: private */
    public void r() {
        a(MotionState.STOPPED);
    }

    private double b(double d2) {
        return ((!u() || d2 >= 0.0d) && (!v() || d2 <= 0.0d)) ? d2 : d2 / 3.0d;
    }

    private float a(float f2, float f3) {
        return this.m.c == Orientation.HORIZONTAL ? f2 : f3;
    }

    private boolean a(d dVar) {
        if (!f(dVar.d, dVar.e)) {
            return true;
        }
        if (this.m.i.a == dVar.d && this.m.i.b == dVar.e) {
            return true;
        }
        if (dVar.h) {
            a(0.95f, (AnimationCurve) this.v, dVar);
            return false;
        }
        this.m.a(dVar.d, dVar.e);
        return true;
    }

    private void a(float f2, AnimationCurve animationCurve, d dVar) {
        dVar.a(this.m.i.a, this.m.i.b);
        g gVar = new g();
        gVar.setDuration(f2);
        gVar.a(animationCurve);
        this.n.a((Animation) gVar);
        this.n.a((a) dVar);
        this.n.a();
    }

    private boolean f(double d2, double d3) {
        if (c(d2) || c(d3)) {
            cx.a(this.m.b != null ? this.m.b.getContext().getString(R.string.RangeManagerLimitsCannotBeNaN) : "Range minimum and maximum cannot be infinite or NaNs");
            return false;
        } else if (d3 > d2) {
            return true;
        } else {
            cx.a(String.format(Locale.getDefault(), this.m.b != null ? this.m.b.getContext().getString(R.string.RangeManagerNonPositiveSpan) : "Ignoring range with %f span", new Object[]{Double.valueOf(d3 - d2)}));
            return false;
        }
    }

    private boolean s() {
        return this.a && this.b;
    }

    private double b(boolean z) {
        double c2 = c();
        return (c(c2) || !z) ? c2 : c2 - t();
    }

    /* access modifiers changed from: 0000 */
    public double c() {
        if (s()) {
            return Double.NEGATIVE_INFINITY;
        }
        if (this.a || this.m.l == null) {
            return d();
        }
        return this.m.l.a;
    }

    /* access modifiers changed from: 0000 */
    public double d() {
        if (this.m.l != null) {
            return Math.min(this.m.j.a, this.m.l.a);
        }
        return this.m.e();
    }

    /* access modifiers changed from: 0000 */
    public double e() {
        if (s()) {
            return Double.POSITIVE_INFINITY;
        }
        if (this.a || this.m.l == null) {
            return f();
        }
        return this.m.l.b;
    }

    /* access modifiers changed from: 0000 */
    public double f() {
        if (this.m.l != null) {
            return Math.max(this.m.j.b, this.m.l.b);
        }
        return this.m.d();
    }

    private double c(boolean z) {
        double e2 = e();
        return (c(e2) || !z) ? e2 : e2 + t();
    }

    private boolean c(double d2) {
        return Double.isInfinite(d2) || Double.isNaN(d2);
    }

    private double t() {
        if (Range.a((Range<?>) this.m.i)) {
            return 0.0d;
        }
        return this.m.i.b() / 4.0d;
    }

    private boolean g(double d2, double d3) {
        if (!this.h) {
            return false;
        }
        this.p.b(d2, d3);
        a(this.j, (AnimationCurve) this.w, (d) this.p);
        return true;
    }

    private boolean d(double d2) {
        if (!this.g) {
            return false;
        }
        double a2 = a(d2, e((double) this.i), (double) this.i);
        this.o.a(this.m.i.a + a2, this.m.i.b + a2, true);
        a(this.i, (AnimationCurve) this.w, (d) this.o);
        return true;
    }

    private double e(double d2) {
        return (-Math.log(0.012000000104308128d)) / d2;
    }

    private double a(double d2, double d3, double d4) {
        return d2 * ((1.0d - Math.pow(2.718281828459045d, (-d4) * d3)) / d3);
    }

    private boolean u() {
        return this.m.i.a < c();
    }

    private boolean v() {
        return this.m.i.b > e();
    }

    private void a(MotionState motionState) {
        boolean z = this.l != motionState;
        this.l = motionState;
        if (z && this.m.b != null) {
            this.m.b.a(this.m);
        }
    }

    private void w() {
        if (!s()) {
            this.u.a(this.m.i.a, this.m.i.b);
            this.u.b(c(), e());
            this.r.a(this.u.a, this.u.b, true);
            if (a((d) this.r)) {
                a(MotionState.STOPPED);
                return;
            }
            return;
        }
        a(MotionState.STOPPED);
    }

    private void h(double d2, double d3) {
        double b2 = (d3 - this.m.i.a) / this.m.i.b();
        double g2 = g() / d2;
        this.t.a(d3 - (b2 * g2), d3 + ((1.0d - b2) * g2));
    }
}
