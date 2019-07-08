package com.shinobicontrols.charts;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.PieDonutSeriesStyle;
import com.shinobicontrols.charts.Series.SelectionMode;

public abstract class PieDonutSeries<T extends PieDonutSeriesStyle> extends Series<T> {
    private final a A;
    final dj<Float> a;
    final dj<Float> b;
    private DrawDirection c;
    private final ChartUtils d;
    private boolean e;
    /* access modifiers changed from: private */
    public float f;
    private NumberRange g;
    private final BounceAnimationCurve h;
    private final BounceAnimationCurve i;
    private final EaseInOutAnimationCurve j;
    private Float k;
    /* access modifiers changed from: private */
    public final b l;

    public enum DrawDirection {
        CLOCKWISE,
        ANTICLOCKWISE
    }

    public enum RadialEffect {
        FLAT(0),
        BEVELLED(1),
        BEVELLED_LIGHT(2),
        ROUNDED(3),
        ROUNDED_LIGHT(4),
        DEFAULT(5);
        
        private final int a;

        private RadialEffect(int i) {
            this.a = i;
        }

        public int getXmlValue() {
            return this.a;
        }
    }

    private static class a implements a {
        float a;
        float b;
        float c;
        float d;
        private final PieDonutSeries<?> e;

        public void b() {
        }

        public void c() {
        }

        public a(PieDonutSeries<?> pieDonutSeries) {
            this.e = pieDonutSeries;
        }

        public void a() {
            for (InternalDataPoint internalDataPoint : this.e.n.c) {
                PieDonutSlice pieDonutSlice = (PieDonutSlice) internalDataPoint;
                pieDonutSlice.p = pieDonutSlice.q;
            }
        }

        public void a(Animation animation) {
            bu buVar = (bu) animation;
            a((float) buVar.f(), buVar.d(), buVar.e());
        }

        public void d() {
            a(1.0f, 1.0f, 1.0f);
        }

        private void a(float f, float f2, float f3) {
            float f4;
            float f5;
            if (this.e.o == null) {
                this.e.l.b();
                return;
            }
            synchronized (x.a) {
                this.e.f = bk.a(this.c + (this.d * f));
                for (InternalDataPoint internalDataPoint : this.e.n.c) {
                    PieDonutSlice pieDonutSlice = (PieDonutSlice) internalDataPoint;
                    if (pieDonutSlice.h) {
                        f4 = pieDonutSlice.p;
                        f5 = (this.a - pieDonutSlice.p) * f2;
                    } else {
                        f4 = pieDonutSlice.p;
                        f5 = (this.b - pieDonutSlice.p) * f3;
                    }
                    pieDonutSlice.q = f4 + f5;
                }
            }
            this.e.o.b.invalidate();
            this.e.p.a();
            this.e.o.b.f();
        }
    }

    /* access modifiers changed from: 0000 */
    public Drawable a(float f2) {
        return null;
    }

    /* access modifiers changed from: 0000 */
    public double b() {
        return 0.0d;
    }

    /* access modifiers changed from: 0000 */
    public abstract float b(float f2);

    /* access modifiers changed from: 0000 */
    public double h() {
        return 0.0d;
    }

    PieDonutSeries() {
        this.c = DrawDirection.ANTICLOCKWISE;
        this.a = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        this.b = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        this.d = new ChartUtils();
        this.e = true;
        this.h = new BounceAnimationCurve();
        this.i = new BounceAnimationCurve();
        this.j = new EaseInOutAnimationCurve();
        this.k = null;
        this.l = new b();
        this.A = new a(this);
        this.v = SeriesAnimation.createGrowAnimation();
        this.w = SeriesAnimation.createGrowAnimation();
    }

    /* access modifiers changed from: 0000 */
    public void m() {
        DataAdapter dataAdapter = getDataAdapter();
        if (this.o != null && dataAdapter != null && dataAdapter.size() >= 0) {
            if (dataAdapter.size() > 0) {
                a(dataAdapter);
            }
            synchronized (x.a) {
                Object[] a2 = this.n.a(dataAdapter);
                this.n.a(a2.length);
                a(this.n, a2);
            }
            this.g = new NumberRange();
            this.g.a(this.n.b.a);
            this.g.a(this.n.b.b);
            r();
            this.p.a();
            this.o.redrawChart();
        }
    }

    private void a(DataAdapter<?, ?> dataAdapter) {
        if (!(dataAdapter.get(0).getY() instanceof Number)) {
            throw new IllegalStateException(this.o.getContext().getString(R.string.PieDonutSeriesInvalidData));
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(ao aoVar, Object[] objArr) {
        int length = objArr.length;
        int i2 = 0;
        while (i2 < length) {
            Data data = objArr[i2];
            double d2 = (double) i2;
            double doubleValue = ((Number) data.getY()).doubleValue();
            if (doubleValue >= 0.0d) {
                PieDonutSlice a2 = a(d2, doubleValue, data.getX().toString());
                if (data instanceof SelectableData) {
                    a2.h = ((SelectableData) data).getSelected();
                    a2.q = ((PieDonutSeriesStyle) (a2.h ? this.r : this.q)).getProtrusion();
                }
                a2.i = i2;
                aoVar.c[i2] = a2;
                aoVar.b.a(doubleValue);
                i2++;
            } else {
                throw new IllegalArgumentException(this.o.getContext().getString(R.string.PieDonutSeriesYDataMustBePositive));
            }
        }
        e();
    }

    /* access modifiers changed from: 0000 */
    public float c() {
        return Math.max(((PieDonutSeriesStyle) this.q).getProtrusion(), ((PieDonutSeriesStyle) this.r).getProtrusion());
    }

    public DrawDirection getDrawDirection() {
        return this.c;
    }

    public void setDrawDirection(DrawDirection drawDirection) {
        synchronized (x.a) {
            this.c = drawDirection;
        }
    }

    /* access modifiers changed from: 0000 */
    public float getInnerRadius() {
        return ((Float) this.a.a).floatValue();
    }

    public float getOuterRadius() {
        return ((Float) this.b.a).floatValue();
    }

    public void setOuterRadius(float f2) {
        synchronized (x.a) {
            this.b.a(Float.valueOf(f2));
            a_();
        }
    }

    public float getRotation() {
        if (this.e) {
            this.f = ((PieDonutSeriesStyle) this.q).getInitialRotation();
            this.e = false;
        }
        return this.f;
    }

    public void setRotation(float f2) {
        synchronized (x.a) {
            this.f = f2;
        }
    }

    public Float getSelectedPosition() {
        return this.k;
    }

    public void setSelectedPosition(Float f2) {
        synchronized (x.a) {
            this.k = f2;
        }
    }

    public Point getCenter() {
        if (this.o == null) {
            return null;
        }
        Point point = new Point();
        point.set((int) ((((float) this.o.getPlotAreaRect().width()) / 2.0f) + 0.5f), (int) ((((float) this.o.getPlotAreaRect().height()) / 2.0f) + 0.5f));
        return point;
    }

    /* access modifiers changed from: 0000 */
    public Point a(Rect rect, int i2) {
        float f2;
        PieDonutSlice pieDonutSlice = (PieDonutSlice) this.n.c[i2];
        float floatValue = ((((Float) this.b.a).floatValue() + ((Float) this.a.a).floatValue()) / 2.0f) + pieDonutSlice.q;
        if (this.c == DrawDirection.ANTICLOCKWISE) {
            f2 = (-(pieDonutSlice.n + pieDonutSlice.o)) / 2.0f;
        } else {
            f2 = (pieDonutSlice.n + pieDonutSlice.o) / 2.0f;
        }
        float cos = ((float) Math.cos(((double) ((-getRotation()) + f2)) - 1.5707963267948966d)) * floatValue;
        float sin = floatValue * ((float) Math.sin(((double) ((-getRotation()) + f2)) - 1.5707963267948966d));
        float min = (float) (Math.min(rect.width(), rect.height()) / 2);
        return new Point(rect.centerX() + ((int) (cos * min)), rect.centerY() + ((int) (min * sin)));
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z, int i2) {
        boolean z2 = z;
        this.o.g.a(z2, this, (PieDonutSlice) this.n.c[i2], SelectionMode.POINT_MULTIPLE, false);
    }

    /* access modifiers changed from: 0000 */
    public void a(Canvas canvas, Rect rect) {
        if (!((PieDonutSeriesStyle) this.q).areLabelsShown()) {
            return;
        }
        if (this.u == null || this.u.c()) {
            int length = this.n.c.length;
            for (int i2 = 0; i2 < length; i2++) {
                PieDonutSlice pieDonutSlice = (PieDonutSlice) this.n.c[i2];
                PieDonutSeriesStyle pieDonutSeriesStyle = (PieDonutSeriesStyle) (pieDonutSlice.h ? this.r : this.q);
                pieDonutSlice.r = a(rect, i2);
                pieDonutSlice.s = new Point(pieDonutSlice.r.x, pieDonutSlice.r.y);
                pieDonutSlice.u.setColor(pieDonutSeriesStyle.getLabelBackgroundColor());
                pieDonutSlice.t.setTextAlign(Align.CENTER);
                pieDonutSlice.t.setAntiAlias(true);
                pieDonutSlice.t.setColor(pieDonutSeriesStyle.getLabelTextColor());
                pieDonutSlice.t.setTypeface(pieDonutSeriesStyle.getLabelTypeface());
                pieDonutSlice.t.setTextSize(pieDonutSeriesStyle.getLabelTextSize() * this.o.getResources().getDisplayMetrics().scaledDensity);
                this.o.a(pieDonutSlice, this);
                Rect a2 = this.d.a(pieDonutSlice.s.x, pieDonutSlice.s.y, pieDonutSlice.m, pieDonutSeriesStyle.getLabelTextSize(), pieDonutSeriesStyle.getLabelTypeface(), this.o);
                if (!this.o.a(canvas, pieDonutSlice, a2, this)) {
                    if (pieDonutSlice.u.getColor() != 0) {
                        ChartUtils.drawTextBackground(canvas, a2, pieDonutSlice.u);
                    }
                    ChartUtils.drawText(canvas, pieDonutSlice.m, pieDonutSlice.s.x, pieDonutSlice.s.y, pieDonutSlice.t);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public String a(int i2) {
        return ((PieDonutSlice) this.n.c[i2]).m;
    }

    /* access modifiers changed from: 0000 */
    public Drawable a(int i2, float f2) {
        PieDonutSeriesStyle pieDonutSeriesStyle = (PieDonutSeriesStyle) ((!this.n.c[i2].h || this.r == null) ? this.q : this.r);
        if (pieDonutSeriesStyle.c()) {
            return new be();
        }
        int i3 = 0;
        int flavorColorAtIndex = pieDonutSeriesStyle.isFlavorShown() ? pieDonutSeriesStyle.flavorColorAtIndex(i2) : 0;
        if (pieDonutSeriesStyle.isCrustShown()) {
            i3 = pieDonutSeriesStyle.crustColorAtIndex(i2);
        }
        return new ba(flavorColorAtIndex, i3, f2);
    }

    /* access modifiers changed from: 0000 */
    public PieDonutSlice a(double d2, double d3, String str) {
        PieDonutSlice pieDonutSlice = new PieDonutSlice(d2, d3);
        pieDonutSlice.m = str;
        return pieDonutSlice;
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        float f2 = BitmapDescriptorFactory.HUE_RED;
        float f3 = BitmapDescriptorFactory.HUE_RED;
        for (InternalDataPoint internalDataPoint : this.n.c) {
            f3 = (float) (((double) f3) + internalDataPoint.b);
        }
        for (InternalDataPoint internalDataPoint2 : this.n.c) {
            PieDonutSlice pieDonutSlice = (PieDonutSlice) internalDataPoint2;
            pieDonutSlice.n = f2;
            pieDonutSlice.o = (float) (((double) f2) + ((pieDonutSlice.b / ((double) f3)) * 3.141592653589793d * 2.0d));
            f2 = pieDonutSlice.o;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(PieDonutSlice pieDonutSlice, boolean z) {
        a(pieDonutSlice);
        if (z) {
            a(1.5f, this.h, this.i, this.j, this.A);
            return;
        }
        this.A.d();
    }

    private void a(PieDonutSlice pieDonutSlice) {
        a aVar = this.A;
        aVar.c = this.f;
        aVar.a = ((PieDonutSeriesStyle) this.r).getProtrusion();
        this.A.b = ((PieDonutSeriesStyle) this.q).getProtrusion();
        if (this.k == null || !pieDonutSlice.h) {
            this.A.d = BitmapDescriptorFactory.HUE_RED;
            return;
        }
        float a2 = this.f + g().a(pieDonutSlice.getCenterAngle());
        this.A.d = this.k.floatValue() - a2;
        a aVar2 = this.A;
        aVar2.d = bk.b(aVar2.d);
    }

    private void a(float f2, AnimationCurve animationCurve, AnimationCurve animationCurve2, AnimationCurve animationCurve3, a aVar) {
        bu buVar = new bu();
        buVar.setDuration(f2);
        buVar.a(animationCurve);
        buVar.b(animationCurve2);
        buVar.c(animationCurve3);
        this.l.a((Animation) buVar);
        this.l.a((a) aVar);
        this.l.a();
    }

    /* access modifiers changed from: 0000 */
    public void a_() {
        for (InternalDataPoint internalDataPoint : this.n.c) {
            PieDonutSlice pieDonutSlice = (PieDonutSlice) internalDataPoint;
            pieDonutSlice.q = ((PieDonutSeriesStyle) (pieDonutSlice.h ? this.r : this.q)).getProtrusion();
        }
        super.a_();
    }

    /* access modifiers changed from: 0000 */
    public cg g() {
        return cg.a(this.c);
    }
}
