package com.shinobicontrols.charts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.shinobicontrols.charts.Axis.Orientation;
import com.shinobicontrols.charts.Axis.Position;
import com.shinobicontrols.charts.Legend.Placement;
import com.shinobicontrols.charts.ShinobiChart.OnAxisMotionStateChangeListener;
import com.shinobicontrols.charts.ShinobiChart.OnAxisRangeChangeListener;
import com.shinobicontrols.charts.ShinobiChart.OnCrosshairActivationStateChangedListener;
import com.shinobicontrols.charts.ShinobiChart.OnCrosshairDrawListener;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;
import com.shinobicontrols.charts.ShinobiChart.OnInternalLayoutListener;
import com.shinobicontrols.charts.ShinobiChart.OnPieDonutSliceLabelDrawListener;
import com.shinobicontrols.charts.ShinobiChart.OnPieDonutSliceUpdateListener;
import com.shinobicontrols.charts.ShinobiChart.OnSeriesAnimationListener;
import com.shinobicontrols.charts.ShinobiChart.OnSeriesSelectionListener;
import com.shinobicontrols.charts.ShinobiChart.OnSnapshotDoneListener;
import com.shinobicontrols.charts.ShinobiChart.OnTickMarkDrawListener;
import com.shinobicontrols.charts.ShinobiChart.OnTickMarkUpdateListener;
import com.shinobicontrols.charts.ShinobiChart.OnTrackingInfoChangedForCrosshairListener;
import com.shinobicontrols.charts.ShinobiChart.OnTrackingInfoChangedForTooltipListener;
import com.shinobicontrols.charts.Title.CentersOn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

abstract class v extends ViewGroup implements ShinobiChart {
    private static final boolean n = (VERSION.SDK_INT >= 14);
    private boolean A = false;
    private final float B = getResources().getDisplayMetrics().density;
    private OnGestureListener C;
    private OnAxisMotionStateChangeListener D;
    private OnAxisRangeChangeListener E;
    private OnInternalLayoutListener F;
    private OnSeriesSelectionListener G;
    private OnSeriesAnimationListener H;
    private OnCrosshairActivationStateChangedListener I;
    private OnPieDonutSliceLabelDrawListener J;
    private OnPieDonutSliceUpdateListener K;
    private OnTickMarkUpdateListener L;
    private OnTickMarkDrawListener M;
    private OnCrosshairDrawListener N;
    private OnTrackingInfoChangedForTooltipListener O;
    private OnTrackingInfoChangedForCrosshairListener P;
    private boolean Q;
    private float R = -1.0f;
    private float S = -1.0f;
    private boolean T = false;
    final Rect a = new Rect();
    w b;
    final List<Series<?>> c = new ArrayList();
    final cv d = new cv();
    final h e = new h(AvidJSONUtil.KEY_X);
    final h f = new h("y");
    cm g = new cm(this);
    Crosshair h;
    final cw i = new cw(this);
    final cp j = new cp(this);
    OnSnapshotDoneListener k;
    final AnnotationsManager l;
    final y m = new y(this);
    private final as o = new as();
    private final bl p = new bl();
    private dd q;
    private ChartStyle r = new ChartStyle();
    private MainTitleStyle s = new MainTitleStyle();
    private Title t;
    private Legend u;
    private final au v = new au(this);
    private final cs w = new cs();
    private final dl x = b();
    private dm y;
    private String z;

    /* access modifiers changed from: 0000 */
    public final void a(Bundle bundle) {
    }

    /* access modifiers changed from: 0000 */
    public abstract dl b();

    /* access modifiers changed from: 0000 */
    public final void e() {
    }

    /* access modifiers changed from: 0000 */
    public List<cr> a() {
        return this.d.a();
    }

    v(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.q = de.a(context, attributeSet);
        this.r = this.q.a();
        this.s = this.q.b();
        t();
        b(context);
        c(context);
        d(context);
        u();
        a(context);
        this.b.d(this.g);
        v();
        this.l = new AnnotationsManager(this);
    }

    public void setLicenseKey(String str) {
        this.z = str;
    }

    private void t() {
        ChartStyle chartStyle = this.r;
        if (chartStyle == null) {
            return;
        }
        if (chartStyle.getBackgroundColor() == 0) {
            a.a((View) this, (Drawable) null);
        } else {
            setBackgroundColor(this.r.getBackgroundColor());
        }
    }

    private void a(Context context) {
        if (this.x.a()) {
            this.y = new dm(context);
            addView(this.y);
        }
    }

    private void b(Context context) {
        this.t = new Title(context);
        this.t.setLayoutParams(new MarginLayoutParams(-2, -2));
        this.t.a((TitleStyle) this.s);
        this.t.setVisibility(8);
        addView(this.t);
    }

    private void c(Context context) {
        this.b = new w(context, this);
        this.b.setLayoutParams(new MarginLayoutParams(-2, -2));
        this.b.a();
        this.b.a(this.r);
        addView(this.b);
    }

    private void d(Context context) {
        this.u = new Legend(context);
        this.u.a(this.v);
        this.u.setVisibility(8);
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(-2, -2);
        int a2 = at.a(this.B, 10.0f);
        marginLayoutParams.setMargins(a2, a2, a2, a2);
        this.u.setLayoutParams(marginLayoutParams);
        this.u.setStyle(this.q.d());
        addView(this.u);
    }

    private void u() {
        this.h = new Crosshair();
        this.h.a(this);
        this.h.setStyle(this.q.c());
        this.b.c((OnGestureListener) this.i);
    }

    private void v() {
        if (this.x.b() == a.PREMIUM) {
            this.Q = true;
        } else if (this.x.b() == a.STANDARD) {
            this.Q = false;
        } else if (this.x.b() == a.TRIAL) {
            this.Q = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        this.b.i();
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        this.b.j();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        this.p.a();
        a(i2, i3);
        b(i2, i3);
        c(i2, i3);
        this.p.b(getPaddingLeft() + getPaddingRight());
        this.p.a(getPaddingTop() + getPaddingBottom());
        setMeasuredDimension(View.resolveSize(this.p.b, i2), View.resolveSize(this.p.a, i3));
    }

    private void a(int i2, int i3) {
        if (!w()) {
            measureChildWithMargins(this.t, i2, this.p.b, i3, this.p.a);
            this.p.a(at.a(this.t));
        }
    }

    private boolean w() {
        Title title = this.t;
        return title == null || title.getVisibility() == 8;
    }

    private void b(int i2, int i3) {
        if (!x()) {
            measureChildWithMargins(this.u, i2, this.p.b, i3, this.p.a);
            this.p.b(y());
            this.p.a(z());
        }
    }

    private boolean x() {
        Legend legend = this.u;
        return legend == null || legend.getVisibility() == 8;
    }

    public boolean isSeriesSelectionSingle() {
        return this.g.a;
    }

    public void setSeriesSelectionSingle(boolean z2) {
        this.g.a = z2;
    }

    /* access modifiers changed from: 0000 */
    public boolean f() {
        return this.e.b() && this.f.b();
    }

    private int y() {
        return this.u.a.b(at.b(this.u), this.r.e(), a(j(), (Position) null), a(j(), 0), (MarginLayoutParams) this.b.getLayoutParams());
    }

    private Position a(Axis<?, ?> axis, Position position) {
        return axis != null ? axis.d : position;
    }

    private int a(Axis<?, ?> axis, int i2) {
        return axis != null ? axis.o : i2;
    }

    private int z() {
        return this.u.a.a(at.a(this.u), this.r.e(), a(i(), (Position) null), a(i(), 0), (MarginLayoutParams) this.b.getLayoutParams());
    }

    private void c(int i2, int i3) {
        measureChildWithMargins(this.b, i2, this.p.b, i3, this.p.a);
        this.p.b(at.b(this.b));
        this.p.a(at.a(this.b));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (!this.A) {
            this.x.a(this.z);
            this.A = true;
        }
        int paddingRight = (i4 - getPaddingRight()) - (i2 + getPaddingLeft());
        int paddingBottom = (i5 - getPaddingBottom()) - (i3 + getPaddingTop());
        this.o.a(0, 0, paddingRight, paddingBottom);
        if (!this.s.getOverlapsChart()) {
            this.o.a(at.a(this.t));
        } else {
            this.t.bringToFront();
        }
        B();
        G();
        this.o.a(0, 0, paddingRight, paddingBottom);
        A();
        D();
        n();
    }

    private void A() {
        if (!w()) {
            this.o.a();
            if (this.s.getCentersOn() != CentersOn.CHART) {
                F();
            }
            if (this.s.getCentersOn() == CentersOn.PLOTTING_AREA) {
                this.o.a.left += this.b.e;
                this.o.a.right -= this.b.f;
            }
            int b2 = at.b(this.t);
            int a2 = at.a(this.t);
            Gravity.apply(this.s.getPosition().a() | 48, b2, a2, this.o.a, this.o.b());
            this.o.c();
            at.a((View) this.t, this.o.a);
            at.b(this.t, this.o.a);
            this.o.a(a2);
        }
    }

    private void B() {
        this.o.a();
        E();
        this.a.set(this.o.a);
        at.b(this.b, this.o.a);
    }

    private void C() {
        boolean z2;
        ArrayList arrayList = new ArrayList();
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            Series series = (Series) this.c.get(i2);
            if (series instanceof PieDonutSeries) {
                arrayList.add((PieDonutSeries) series);
            }
        }
        int size2 = arrayList.size();
        if (size2 != 0) {
            float f2 = (float) size2;
            float f3 = 0.4f / f2;
            PieDonutSeries pieDonutSeries = null;
            int i3 = 0;
            while (i3 < size2) {
                PieDonutSeries pieDonutSeries2 = (PieDonutSeries) arrayList.get(i3);
                float c2 = pieDonutSeries != null ? pieDonutSeries.c() : BitmapDescriptorFactory.HUE_RED;
                if (!pieDonutSeries2.a.b) {
                    pieDonutSeries2.a.b(Float.valueOf(pieDonutSeries != null ? pieDonutSeries.getOuterRadius() + c2 : pieDonutSeries2.b(f3)));
                    z2 = true;
                } else {
                    z2 = false;
                }
                float c3 = pieDonutSeries2.c();
                if (!pieDonutSeries2.b.b) {
                    pieDonutSeries2.b.b(Float.valueOf(((((float) (i3 + 1)) * ((0.95f - f3) / f2)) + f3) - c3));
                    z2 = true;
                }
                if (z2) {
                    pieDonutSeries2.a_();
                }
                i3++;
                pieDonutSeries = pieDonutSeries2;
            }
        }
    }

    private void D() {
        if (this.y != null) {
            Rect plotAreaRect = getPlotAreaRect();
            int i2 = plotAreaRect.bottom - plotAreaRect.top;
            this.y.measure(MeasureSpec.makeMeasureSpec(plotAreaRect.right - plotAreaRect.left, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE));
            int measuredWidth = this.y.getMeasuredWidth();
            int measuredHeight = this.y.getMeasuredHeight();
            int i3 = plotAreaRect.left;
            int i4 = plotAreaRect.top;
            this.y.layout(i3, i4, measuredWidth + i3, measuredHeight + i4);
        }
    }

    private void E() {
        if (!x()) {
            this.u.a.a(this.o.a, y(), z());
        }
        at.a((View) this.b, this.o.a);
    }

    private void F() {
        if (!x()) {
            this.u.a.a(this.o.a, y(), 0);
        }
        at.a((View) this.b, this.o.a);
    }

    private void G() {
        if (!x()) {
            this.o.a();
            if (this.u.getPlacement() == Placement.INSIDE_PLOT_AREA || this.u.getPlacement() == Placement.ON_PLOT_AREA_BORDER) {
                E();
            }
            this.u.a.a(this.o.a, this.b.e, this.b.d, this.b.f, this.b.c);
            Gravity.apply(this.u.getPosition().a(), at.b(this.u), at.a(this.u), this.o.a, this.o.b());
            this.o.c();
            at.a((View) this.u, this.o.a);
            this.u.a.a(this.o.a, (MarginLayoutParams) this.u.getLayoutParams(), this.u.getMeasuredWidth(), this.u.getMeasuredHeight(), this.r.e());
            at.b(this.u, this.o.a);
        }
    }

    /* renamed from: a */
    public MarginLayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public MarginLayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new MarginLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public MarginLayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (l()) {
            C();
        }
    }

    public void addSeries(Series<?> series) {
        addSeries(series, null, null);
    }

    public void addSeries(Series<?> series, Axis<?, ?> axis, Axis<?, ?> axis2) {
        if (e(series)) {
            K();
        }
        synchronized (x.a) {
            if (series != null) {
                try {
                    if (series instanceof PieDonutSeries) {
                        d(series);
                    } else {
                        a(series, axis, axis2);
                    }
                } finally {
                }
            } else {
                throw new IllegalArgumentException(getContext().getString(R.string.ChartCannotAddNullSeries));
            }
        }
    }

    private void d(Series<?> series) {
        if (H()) {
            throw new IllegalStateException(getContext().getString(R.string.ChartPieDonutInCartesian));
        } else if (this.e.b() || this.f.b()) {
            throw new IllegalStateException(getContext().getString(R.string.ChartPieDonutCannotHaveAxes));
        } else {
            synchronized (x.a) {
                if (series.x) {
                    this.j.a(series, this);
                } else {
                    a(series);
                }
            }
        }
    }

    private boolean H() {
        return this.c.size() > 0 && !l();
    }

    /* access modifiers changed from: 0000 */
    public void a(Series<?> series) {
        this.c.add(series);
        series.c(this.q, this.w.a(series), false);
        if (series instanceof PieDonutSeries) {
            C();
        }
        series.a(this);
    }

    private void a(Series<?> series, Axis<?, ?> axis, Axis<?, ?> axis2) {
        if (!I()) {
            Axis c2 = c(axis);
            Axis d2 = d(axis2);
            if (c2 == null) {
                cx.a(getContext().getString(R.string.ChartNoPrimaryX));
            }
            if (d2 == null) {
                cx.a(getContext().getString(R.string.ChartNoPrimaryY));
            }
            this.d.a((CartesianSeries) series, c2, d2);
            synchronized (x.a) {
                if (series.x) {
                    this.j.a(series, this);
                } else {
                    a(series);
                }
            }
            return;
        }
        throw new IllegalStateException(getContext().getString(R.string.ChartCartesianInPieDonut));
    }

    private boolean I() {
        return this.c.size() > 0 && l();
    }

    private Axis<?, ?> c(Axis<?, ?> axis) {
        if (axis == null) {
            return i();
        }
        if (axis.b == this) {
            return axis;
        }
        addXAxis(axis);
        return axis;
    }

    private Axis<?, ?> d(Axis<?, ?> axis) {
        if (axis == null) {
            return j();
        }
        if (axis.b == this) {
            return axis;
        }
        addYAxis(axis);
        return axis;
    }

    public boolean removeSeries(Series<?> series) {
        synchronized (x.a) {
            if (series.x) {
                this.j.b(series, this);
                return false;
            }
            b(series);
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Series<?> series) {
        Axis axis;
        Axis axis2;
        boolean z2 = series instanceof PieDonutSeries;
        if (!z2) {
            axis2 = series.getXAxis();
            axis = series.getYAxis();
        } else {
            axis2 = null;
            axis = null;
        }
        this.c.remove(series);
        this.d.a(series);
        this.w.b(series);
        if (z2) {
            C();
        } else {
            if (axis2 != null) {
                axis2.f();
            }
            if (axis != null) {
                axis.f();
            }
        }
        Crosshair crosshair = this.h;
        if (crosshair != null) {
            crosshair.a(series);
        }
        series.a((v) null);
        this.b.e();
        this.b.invalidate();
        redrawChart();
    }

    public List<Series<?>> getSeries() {
        return Collections.unmodifiableList(this.c);
    }

    /* access modifiers changed from: 0000 */
    public List<CartesianSeries<?>> h() {
        ArrayList arrayList = new ArrayList();
        for (Series series : this.c) {
            if (series instanceof CartesianSeries) {
                arrayList.add((CartesianSeries) series);
            }
        }
        return arrayList;
    }

    public Axis<?, ?> getXAxisForSeries(Series<?> series) {
        return this.d.b(series);
    }

    public Axis<?, ?> getYAxisForSeries(Series<?> series) {
        return this.d.c(series);
    }

    public List<Series<?>> getSeriesForAxis(Axis<?, ?> axis) {
        Set e2 = this.d.e(axis);
        if (e2 == null) {
            return new ArrayList();
        }
        return Arrays.asList(e2.toArray(new Series[0]));
    }

    public void setHidden(List<Series<?>> list, boolean z2) {
        if (list == null) {
            cx.b(getResources().getString(R.string.ChartCannotPassInNullSeriesList));
            return;
        }
        synchronized (x.a) {
            ArrayList arrayList = new ArrayList();
            for (Series series : list) {
                if (!series.x || series.o == null) {
                    series.a(z2);
                } else if (z2) {
                    if (!series.y) {
                        if (series.u != series.w) {
                            arrayList.add(series);
                        }
                    }
                } else if (series.y || series.u != null) {
                    if (series.u != series.v) {
                        arrayList.add(series);
                    }
                }
            }
            if (z2) {
                this.j.a((List<Series<?>>) arrayList);
            } else {
                this.j.b((List<Series<?>>) arrayList);
            }
        }
    }

    public List<Axis<?, ?>> getAllXAxes() {
        ArrayList arrayList = new ArrayList();
        for (Axis<?, ?> add : this.e.a) {
            arrayList.add(add);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public List<Axis<?, ?>> getAllYAxes() {
        ArrayList arrayList = new ArrayList();
        for (Axis<?, ?> add : this.f.a) {
            arrayList.add(add);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Rect getPlotAreaRect() {
        Rect rect = new Rect(this.b.b);
        rect.offset(this.a.left, this.a.top);
        return rect;
    }

    public Rect getCanvasRect() {
        return this.a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0068, code lost:
        if (r4 == null) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0035, code lost:
        if (r4 != null) goto L_0x0037;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x004b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getInfo() {
        /*
            r7 = this;
            java.lang.String r0 = "NOT KNOWN"
            java.lang.String r1 = "NOT KNOWN"
            java.lang.String r2 = "NOT KNOWN"
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r3 = r3.getContextClassLoader()
            r4 = 0
            java.lang.String r5 = "version.properties"
            java.io.InputStream r4 = r3.getResourceAsStream(r5)     // Catch:{ NullPointerException -> 0x005b, IOException -> 0x004b }
            java.util.Properties r3 = new java.util.Properties     // Catch:{ NullPointerException -> 0x005b, IOException -> 0x004b }
            r3.<init>()     // Catch:{ NullPointerException -> 0x005b, IOException -> 0x004b }
            r3.load(r4)     // Catch:{ NullPointerException -> 0x005b, IOException -> 0x004b }
            java.lang.String r5 = "flavor"
            java.lang.String r6 = "NOT KNOWN"
            java.lang.String r0 = r3.getProperty(r5, r6)     // Catch:{ NullPointerException -> 0x005b, IOException -> 0x004b }
            java.lang.String r5 = "version"
            java.lang.String r6 = "NOT KNOWN"
            java.lang.String r1 = r3.getProperty(r5, r6)     // Catch:{ NullPointerException -> 0x005b, IOException -> 0x004b }
            java.lang.String r5 = "builddate"
            java.lang.String r6 = "NOT KNOWN"
            java.lang.String r2 = r3.getProperty(r5, r6)     // Catch:{ NullPointerException -> 0x005b, IOException -> 0x004b }
            if (r4 == 0) goto L_0x006b
        L_0x0037:
            r4.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x006b
        L_0x003b:
            android.content.Context r3 = r7.getContext()
            int r4 = com.shinobicontrols.charts.R.string.ChartFailedToCloseInputStream
            java.lang.String r3 = r3.getString(r4)
            com.shinobicontrols.charts.cx.b(r3)
            goto L_0x006b
        L_0x0049:
            r0 = move-exception
            goto L_0x007e
        L_0x004b:
            android.content.Context r3 = r7.getContext()     // Catch:{ all -> 0x0049 }
            int r5 = com.shinobicontrols.charts.R.string.ChartNoVersionProperties     // Catch:{ all -> 0x0049 }
            java.lang.String r3 = r3.getString(r5)     // Catch:{ all -> 0x0049 }
            com.shinobicontrols.charts.cx.b(r3)     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x006b
            goto L_0x0037
        L_0x005b:
            android.content.Context r3 = r7.getContext()     // Catch:{ all -> 0x0049 }
            int r5 = com.shinobicontrols.charts.R.string.ChartNoVersionProperties     // Catch:{ all -> 0x0049 }
            java.lang.String r3 = r3.getString(r5)     // Catch:{ all -> 0x0049 }
            com.shinobicontrols.charts.cx.b(r3)     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x006b
            goto L_0x0037
        L_0x006b:
            java.lang.String r3 = "Version: ShinobiCharts for Android %s Version %s, built on %s"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r5 = 0
            r4[r5] = r0
            r0 = 1
            r4[r0] = r1
            r0 = 2
            r4[r0] = r2
            java.lang.String r0 = java.lang.String.format(r3, r4)
            return r0
        L_0x007e:
            if (r4 == 0) goto L_0x0091
            r4.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0091
        L_0x0084:
            android.content.Context r1 = r7.getContext()
            int r2 = com.shinobicontrols.charts.R.string.ChartFailedToCloseInputStream
            java.lang.String r1 = r1.getString(r2)
            com.shinobicontrols.charts.cx.b(r1)
        L_0x0091:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.v.getInfo():java.lang.String");
    }

    public Legend getLegend() {
        return this.u;
    }

    public Crosshair getCrosshair() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public Axis<?, ?> i() {
        return this.e.a();
    }

    /* access modifiers changed from: 0000 */
    public Axis<?, ?> j() {
        return this.f.a();
    }

    public Axis<?, ?> getXAxis() {
        return i();
    }

    public void setXAxis(Axis<?, ?> axis) {
        a(axis, this.e);
        e(axis);
    }

    private void a(Axis<?, ?> axis, h hVar) {
        if (axis != null) {
            if (b(hVar)) {
                K();
            }
            if (l()) {
                throw new IllegalStateException(getContext().getString(R.string.ChartPieDonutCannotHaveAxes));
            } else if (axis.b == this) {
                throw new IllegalStateException(getContext().getString(R.string.ChartAlreadyHasThisAxis));
            } else if (axis.b != null) {
                throw new IllegalArgumentException(getContext().getString(R.string.ChartAxisBelongsToAnotherChart));
            }
        } else {
            throw new IllegalArgumentException(getContext().getString(R.string.ChartCannotAddNullAxis));
        }
    }

    private void e(Axis<?, ?> axis) {
        Axis i2 = i();
        if (i2 != null) {
            removeXAxis(i2);
        }
        a(axis, Orientation.HORIZONTAL, this.q.e());
        this.e.a(axis);
        this.d.a(axis);
    }

    public Axis<?, ?> getYAxis() {
        return j();
    }

    public void setYAxis(Axis<?, ?> axis) {
        a(axis, this.f);
        f(axis);
    }

    private void f(Axis<?, ?> axis) {
        Axis j2 = j();
        if (j2 != null) {
            removeYAxis(j2);
        }
        a(axis, Orientation.VERTICAL, this.q.f());
        this.f.a(axis);
        this.d.b(axis);
    }

    public void applyTheme(int i2, boolean z2) {
        if (z2) {
            this.q = de.a(getContext(), i2);
        } else {
            this.q = de.a(getContext(), this.q, i2);
        }
        a(z2);
    }

    public String getTitle() {
        Title title = this.t;
        if (title == null) {
            return null;
        }
        return title.getText().toString();
    }

    public void setTitle(String str) {
        if (this.t == null) {
            b(getContext());
        }
        this.t.setText(str);
        if (str != null) {
            this.t.setVisibility(0);
        } else {
            this.t.setVisibility(8);
        }
    }

    public void addXAxis(Axis<?, ?> axis) {
        a(axis, this.e);
        if (this.e.b()) {
            g(axis);
        } else {
            e(axis);
        }
    }

    private void g(Axis<?, ?> axis) {
        this.e.b(axis);
        a(axis, Orientation.HORIZONTAL, this.q.e());
    }

    public void addYAxis(Axis<?, ?> axis) {
        a(axis, this.f);
        if (this.f.b()) {
            h(axis);
        } else {
            f(axis);
        }
    }

    private void h(Axis<?, ?> axis) {
        this.f.b(axis);
        a(axis, Orientation.VERTICAL, this.q.f());
    }

    private void a(Axis<?, ?> axis, Orientation orientation, AxisStyle axisStyle) {
        axis.a(orientation);
        AxisStyle axisStyle2 = axis.getStyle() == null ? new AxisStyle() : axis.getStyle();
        axisStyle2.a(axisStyle);
        axis.setStyle(axisStyle2);
        axis.a(this);
        this.b.a(axis.z());
        this.b.addView(axis.t());
    }

    public ChartStyle getStyle() {
        return this.r;
    }

    public void setStyle(ChartStyle chartStyle) {
        this.r = chartStyle;
    }

    public MainTitleStyle getTitleStyle() {
        return this.s;
    }

    public void setTitleStyle(MainTitleStyle mainTitleStyle) {
        this.s = mainTitleStyle;
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z2) {
        if (this.q != null) {
            if (z2 || this.r == null) {
                this.r = new ChartStyle();
            }
            this.r.a(this.q.a());
            if (z2 || this.s == null) {
                this.s = new MainTitleStyle();
            }
            this.s.a(this.q.b());
            for (int i2 = 0; i2 < this.e.a.length; i2++) {
                Axis<?, ?> axis = this.e.a[i2];
                AxisStyle axisStyle = (z2 || axis.getStyle() == null) ? new AxisStyle() : axis.getStyle();
                axisStyle.a(this.q.e());
                axis.setStyle(axisStyle);
            }
            for (int i3 = 0; i3 < this.f.a.length; i3++) {
                Axis<?, ?> axis2 = this.f.a[i3];
                AxisStyle axisStyle2 = (z2 || axis2.getStyle() == null) ? new AxisStyle() : axis2.getStyle();
                axisStyle2.a(this.q.f());
                axis2.setStyle(axisStyle2);
            }
            Legend legend = this.u;
            if (legend != null) {
                if (z2 || legend.getStyle() == null) {
                    this.u.setStyle(new LegendStyle());
                }
                this.u.getStyle().a(this.q.d());
            }
            Crosshair crosshair = this.h;
            if (crosshair != null) {
                if (z2 || crosshair.getStyle() == null) {
                    this.h.setStyle(new CrosshairStyle());
                }
                this.h.getStyle().a(this.q.c());
            }
            for (Series series : this.c) {
                series.c(this.q, this.w.c(series), z2);
            }
            for (Annotation annotation : this.l.getAnnotations()) {
                if (z2 || annotation.getStyle() == null) {
                    annotation.setStyle(new AnnotationStyle());
                }
                annotation.getStyle().a(this.q.g());
            }
        }
    }

    public void redrawChart() {
        Legend legend = this.u;
        if (legend != null) {
            legend.a();
        }
        t();
        this.b.a(this.r);
        this.t.a((TitleStyle) this.s);
        Crosshair crosshair = this.h;
        if (crosshair != null) {
            crosshair.f();
        }
        this.l.b();
        J();
    }

    private void J() {
        invalidate();
        requestLayout();
        this.b.invalidate();
        this.b.requestLayout();
        for (Axis<?, ?> axis : this.e.a) {
            axis.u.c();
        }
        for (Axis<?, ?> axis2 : this.f.a) {
            axis2.u.c();
        }
    }

    public void removeXAxis(Axis<?, ?> axis) {
        this.d.c(axis);
        b(axis, this.e);
    }

    public void removeYAxis(Axis<?, ?> axis) {
        this.d.d(axis);
        b(axis, this.f);
    }

    private void b(Axis<?, ?> axis, h hVar) {
        hVar.c(axis);
        this.b.b(axis.z());
        this.b.removeView(axis.t());
        axis.h();
        this.l.removeAllAnnotations(axis);
    }

    /* access modifiers changed from: 0000 */
    public w k() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public boolean l() {
        Object[] array = this.c.toArray();
        for (Object obj : array) {
            if (((Series) obj) instanceof PieDonutSeries) {
                return true;
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (f()) {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    this.R = motionEvent.getX();
                    this.S = motionEvent.getY();
                    break;
                case 1:
                    break;
                case 2:
                    if (this.T || a(motionEvent)) {
                        this.T = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    }
                case 3:
                    this.b.m();
                    break;
            }
            this.R = -1.0f;
            this.S = -1.0f;
            this.T = false;
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private boolean a(MotionEvent motionEvent) {
        Crosshair crosshair = this.h;
        if (crosshair != null && crosshair.isActive()) {
            return true;
        }
        if (a(this.e) && a(this.f)) {
            return true;
        }
        if (a(this.e) && a(this.R, motionEvent.getX())) {
            return true;
        }
        if (!a(this.f) || !a(this.S, motionEvent.getY())) {
            return false;
        }
        return true;
    }

    private boolean a(float f2, float f3) {
        return Math.abs(f3 - f2) > ((float) this.m.b);
    }

    private boolean a(h hVar) {
        for (Axis<?, ?> axis : hVar.a) {
            if (axis.isGesturePanningEnabled() || axis.isGestureZoomingEnabled()) {
                return true;
            }
        }
        return false;
    }

    public void setOnGestureListener(OnGestureListener onGestureListener) {
        this.C = onGestureListener;
    }

    public void setOnAxisMotionStateChangeListener(OnAxisMotionStateChangeListener onAxisMotionStateChangeListener) {
        this.D = onAxisMotionStateChangeListener;
    }

    public void setOnAxisRangeChangeListener(OnAxisRangeChangeListener onAxisRangeChangeListener) {
        this.E = onAxisRangeChangeListener;
    }

    public void setOnInternalLayoutListener(OnInternalLayoutListener onInternalLayoutListener) {
        this.F = onInternalLayoutListener;
    }

    public void setOnSeriesSelectionListener(OnSeriesSelectionListener onSeriesSelectionListener) {
        this.G = onSeriesSelectionListener;
    }

    public void setOnSnapshotDoneListener(OnSnapshotDoneListener onSnapshotDoneListener) {
        this.k = onSnapshotDoneListener;
    }

    public void setOnSeriesAnimationListener(OnSeriesAnimationListener onSeriesAnimationListener) {
        this.H = onSeriesAnimationListener;
    }

    /* access modifiers changed from: 0000 */
    public void a(Axis<?, ?> axis) {
        OnAxisMotionStateChangeListener onAxisMotionStateChangeListener = this.D;
        if (onAxisMotionStateChangeListener != null) {
            onAxisMotionStateChangeListener.onAxisMotionStateChange(axis);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Axis<?, ?> axis) {
        OnAxisRangeChangeListener onAxisRangeChangeListener = this.E;
        if (onAxisRangeChangeListener != null) {
            onAxisRangeChangeListener.onAxisRangeChange(axis);
        }
    }

    /* access modifiers changed from: 0000 */
    public void m() {
        OnCrosshairActivationStateChangedListener onCrosshairActivationStateChangedListener = this.I;
        if (onCrosshairActivationStateChangedListener != null) {
            onCrosshairActivationStateChangedListener.onCrosshairActivationStateChanged(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public void n() {
        OnInternalLayoutListener onInternalLayoutListener = this.F;
        if (onInternalLayoutListener != null) {
            onInternalLayoutListener.onInternalLayout(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(PointF pointF) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onDoubleTapDown(this, g(pointF));
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(PointF pointF) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onDoubleTapUp(this, g(pointF));
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(PointF pointF) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onLongTouchDown(this, g(pointF));
        }
    }

    /* access modifiers changed from: 0000 */
    public void d(PointF pointF) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onLongTouchUp(this, g(pointF));
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(PointF pointF, PointF pointF2, PointF pointF3) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onPinch(this, g(pointF), g(pointF2), pointF3);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(PointF pointF, boolean z2, PointF pointF2) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onPinchEnd(this, g(pointF), z2, pointF2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(PointF pointF, PointF pointF2) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onSecondTouchDown(this, g(pointF), g(pointF2));
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(PointF pointF, PointF pointF2) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onSecondTouchUp(this, g(pointF), g(pointF2));
        }
    }

    /* access modifiers changed from: 0000 */
    public void e(PointF pointF) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onSingleTouchDown(this, g(pointF));
        }
    }

    /* access modifiers changed from: 0000 */
    public void f(PointF pointF) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onSingleTouchUp(this, g(pointF));
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(PointF pointF, PointF pointF2) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onSwipe(this, g(pointF), g(pointF2));
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(PointF pointF, boolean z2, PointF pointF2) {
        OnGestureListener onGestureListener = this.C;
        if (onGestureListener != null) {
            onGestureListener.onSwipeEnd(this, g(pointF), z2, pointF2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(CartesianSeries<?> cartesianSeries) {
        OnSeriesSelectionListener onSeriesSelectionListener = this.G;
        if (onSeriesSelectionListener != null) {
            onSeriesSelectionListener.onSeriesSelectionStateChanged(cartesianSeries);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Series<?> series, int i2) {
        OnSeriesSelectionListener onSeriesSelectionListener = this.G;
        if (onSeriesSelectionListener != null) {
            onSeriesSelectionListener.onPointSelectionStateChanged(series, i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(Series<?> series) {
        OnSeriesAnimationListener onSeriesAnimationListener = this.H;
        if (onSeriesAnimationListener != null) {
            onSeriesAnimationListener.onSeriesAnimationFinished(series);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(PieDonutSlice pieDonutSlice, PieDonutSeries<?> pieDonutSeries) {
        OnPieDonutSliceUpdateListener onPieDonutSliceUpdateListener = this.K;
        if (onPieDonutSliceUpdateListener != null) {
            onPieDonutSliceUpdateListener.onUpdateSlice(pieDonutSlice, pieDonutSeries);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(TickMark tickMark, Axis<?, ?> axis) {
        OnTickMarkUpdateListener onTickMarkUpdateListener = this.L;
        if (onTickMarkUpdateListener != null) {
            onTickMarkUpdateListener.onUpdateTickMark(tickMark, axis);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Canvas canvas, TickMark tickMark, Rect rect, Rect rect2, Axis<?, ?> axis) {
        boolean z2 = this.M != null;
        if (z2) {
            this.M.onDrawTickMark(canvas, tickMark, rect, rect2, axis);
        }
        return z2;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Canvas canvas, PieDonutSlice pieDonutSlice, Rect rect, PieDonutSeries<?> pieDonutSeries) {
        boolean z2 = this.J != null;
        if (z2) {
            this.J.onDrawLabel(canvas, pieDonutSlice, rect, pieDonutSeries);
        }
        return z2;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Canvas canvas, Rect rect, float f2, float f3, float f4, Paint paint) {
        boolean z2 = this.N != null;
        if (z2) {
            this.N.onDrawCrosshair(this, canvas, rect, f2, f3, f4, paint);
        }
        return z2;
    }

    private PointF g(PointF pointF) {
        PointF pointF2 = new PointF(pointF.x, pointF.y);
        pointF2.offset((float) (this.a.left + this.b.b.left), (float) (this.a.top + this.b.b.top));
        return pointF2;
    }

    private void K() {
        if (!this.Q) {
            throw new UnsupportedOperationException(getContext().getString(R.string.ChartPremiumOnly));
        }
    }

    private boolean e(Series<?> series) {
        return (series instanceof BandSeries) || (series instanceof OHLCSeries) || (series instanceof CandlestickSeries);
    }

    private boolean b(h hVar) {
        return hVar.b();
    }

    public void setOnPieDonutSliceLabelDrawListener(OnPieDonutSliceLabelDrawListener onPieDonutSliceLabelDrawListener) {
        this.J = onPieDonutSliceLabelDrawListener;
    }

    public void setOnPieDonutSliceUpdateListener(OnPieDonutSliceUpdateListener onPieDonutSliceUpdateListener) {
        this.K = onPieDonutSliceUpdateListener;
    }

    public void setOnTickMarkUpdateListener(OnTickMarkUpdateListener onTickMarkUpdateListener) {
        for (Axis<?, ?> axis : this.e.a) {
            axis.u.a = true;
        }
        for (Axis<?, ?> axis2 : this.f.a) {
            axis2.u.a = true;
        }
        this.L = onTickMarkUpdateListener;
    }

    public void setOnTickMarkDrawListener(OnTickMarkDrawListener onTickMarkDrawListener) {
        for (Axis<?, ?> axis : this.e.a) {
            axis.u.a = true;
        }
        for (Axis<?, ?> axis2 : this.f.a) {
            axis2.u.a = true;
        }
        this.M = onTickMarkDrawListener;
    }

    public AnnotationsManager getAnnotationsManager() {
        return this.l;
    }

    /* access modifiers changed from: 0000 */
    public void o() {
        K();
    }

    /* access modifiers changed from: 0000 */
    public void a(final Bitmap bitmap) {
        if (this.k != null) {
            if (n) {
                this.b.a(bitmap);
            }
            post(new Runnable() {
                public void run() {
                    v.this.k.onSnapshotDone(v.this.b(bitmap));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public Bitmap b(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        draw(canvas);
        if (!n) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            bitmapDrawable.setBounds(getPlotAreaRect());
            bitmapDrawable.draw(canvas);
        }
        BitmapDrawable bitmapDrawable2 = new BitmapDrawable(getResources(), createBitmap);
        bitmapDrawable2.setAntiAlias(true);
        return bitmapDrawable2.getBitmap();
    }

    public void requestSnapshot() {
        this.b.l();
        redrawChart();
    }

    /* access modifiers changed from: 0000 */
    public dd p() {
        return this.q;
    }

    /* access modifiers changed from: 0000 */
    public boolean q() {
        for (Axis<?, ?> isDoubleTapEnabled : this.e.a) {
            if (isDoubleTapEnabled.isDoubleTapEnabled()) {
                return true;
            }
        }
        for (Axis<?, ?> isDoubleTapEnabled2 : this.f.a) {
            if (isDoubleTapEnabled2.isDoubleTapEnabled()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean r() {
        for (CartesianSeries cartesianSeries : h()) {
            if (cartesianSeries.l.a) {
                return true;
            }
        }
        return false;
    }

    public void setOnCrosshairDrawListener(OnCrosshairDrawListener onCrosshairDrawListener) {
        this.N = onCrosshairDrawListener;
    }

    public void setOnTrackingInfoChangedForTooltipListener(OnTrackingInfoChangedForTooltipListener onTrackingInfoChangedForTooltipListener) {
        this.O = onTrackingInfoChangedForTooltipListener;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(CartesianSeries<?> cartesianSeries, DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3) {
        boolean z2 = this.O != null;
        if (z2) {
            this.O.onTrackingInfoChanged(this.h.g, dataPoint, dataPoint2, dataPoint3);
        }
        return z2;
    }

    public void setOnTrackingInfoChangedForCrosshairListener(OnTrackingInfoChangedForCrosshairListener onTrackingInfoChangedForCrosshairListener) {
        this.P = onTrackingInfoChangedForCrosshairListener;
    }

    /* access modifiers changed from: 0000 */
    public boolean b(CartesianSeries<?> cartesianSeries, DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3) {
        boolean z2 = this.P != null;
        if (z2) {
            this.P.onTrackingInfoChanged(this.h, dataPoint, dataPoint2, dataPoint3);
        }
        return z2;
    }

    public void setOnCrosshairActivationStateChangedListener(OnCrosshairActivationStateChangedListener onCrosshairActivationStateChangedListener) {
        this.I = onCrosshairActivationStateChangedListener;
    }

    /* access modifiers changed from: 0000 */
    public void s() {
        K();
    }

    public int getLongTouchTimeout() {
        return this.m.e;
    }

    public void setLongTouchTimeout(int i2) {
        this.m.e = i2;
    }
}
