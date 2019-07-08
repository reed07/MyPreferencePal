package com.shinobicontrols.charts;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.PropertyChangedEvent.Handler;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;
import com.shinobicontrols.charts.TickMark.ClippingMode;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public abstract class Axis<T extends Comparable<T>, U> {
    private final Map<Series<?>, am> A = new HashMap();
    private final a B = new a<>(this);
    private final al C = new al();
    private double D;
    private boolean E = false;
    private boolean F;
    private U G;
    private U H;
    private double I;
    private double J;
    private am K;
    private final b L = new b<>();
    private ClippingMode M = ClippingMode.TICKS_AND_LABELS_PERSIST;
    private ClippingMode N = ClippingMode.TICKS_AND_LABELS_PERSIST;
    private String O;
    private Float P = null;
    private boolean Q = false;
    private Title R;
    private final as S = new as();
    private final Paint T = new Paint();
    private double U;
    private final Point V = new Point();
    private final c W = new c();
    private final Rect X = new Rect();
    private final ar Y = new ar();
    private final PointF Z = new PointF();
    float a = 1.0f;
    private DoubleTapBehavior aa = DoubleTapBehavior.ZOOM_IN;
    private final List<Range<T>> ab = new ArrayList();
    private final da<T, U> ac = new da<>(this);
    private final aq<T> ad = new f();
    private final aq<T> ae = new ck();
    v b;
    Orientation c;
    Position d = Position.NORMAL;
    double e;
    double f = 0.0d;
    AxisStyle g;
    int h;
    NumberRange i = new NumberRange();
    NumberRange j = new NumberRange();
    NumberRange k = new NumberRange();
    NumberRange l = null;
    Range<T> m;
    i n;
    int o;
    U p = null;
    U q = null;
    U r = null;
    U s = null;
    double[] t;
    final dg u = new dg(this);
    final ch v = new ch(this);
    String w = null;
    String x = null;
    final bv y = bv.a();
    final bv z = bv.a();

    public enum DoubleTapBehavior {
        RESET_TO_DEFAULT_RANGE,
        ZOOM_IN
    }

    public enum MotionState {
        STOPPED,
        ANIMATING,
        GESTURE,
        MOMENTUM,
        BOUNCING
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public enum Position {
        NORMAL,
        REVERSE
    }

    private class a implements a {
        private final Axis<?, ?> b;

        public a(Axis<?, ?> axis) {
            this.b = axis;
        }

        public final void a() {
            this.b.f();
        }
    }

    private class b implements Handler {
        private b() {
        }

        public void onPropertyChanged() {
            Axis.this.f();
            Axis.this.O();
        }
    }

    static class c {
        int A;
        double B;
        float C;
        float D;
        float E;
        ClippingMode F;
        ClippingMode G;
        float H;
        float I;
        boolean J;
        Rect K = new Rect();
        Rect L = new Rect();
        Point M = new Point();
        Rect a;
        boolean b;
        boolean c;
        boolean d;
        boolean e;
        boolean f;
        float g;
        float h;
        float i;
        float j;
        float k;
        float l;
        float m;
        int n;
        int o;
        int p;
        int q;
        Typeface r;
        float s;
        com.shinobicontrols.charts.TickMark.Orientation t;
        boolean u;
        DashPathEffect v;
        int w;
        int x;
        Point y;
        float z;

        c() {
        }
    }

    private boolean G() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void A() {
    }

    /* access modifiers changed from: 0000 */
    public abstract double a(double d2, boolean z2);

    /* access modifiers changed from: 0000 */
    public abstract double a(int i2);

    /* access modifiers changed from: 0000 */
    public int a(int i2, int i3) {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public abstract T applyMappingForSkipRangesToUserValue(T t2);

    /* access modifiers changed from: 0000 */
    public abstract double b(int i2);

    /* access modifiers changed from: 0000 */
    public abstract boolean b(double d2);

    /* access modifiers changed from: 0000 */
    public abstract void c(int i2);

    /* access modifiers changed from: 0000 */
    public abstract double convertPoint(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract double convertUserValueTypeToInternalDataType(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract Range<T> createRange(T t2, T t3);

    /* access modifiers changed from: 0000 */
    public abstract void g();

    /* access modifiers changed from: 0000 */
    public abstract T getDefaultBaseline();

    /* access modifiers changed from: 0000 */
    public abstract String getFormattedString(T t2);

    /* access modifiers changed from: 0000 */
    public abstract String i();

    /* access modifiers changed from: 0000 */
    public abstract boolean isDataValid(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract void p();

    /* access modifiers changed from: 0000 */
    public abstract void q();

    /* access modifiers changed from: 0000 */
    public abstract T removeMappingForSkipRangesFromChartValue(T t2);

    /* access modifiers changed from: 0000 */
    public abstract void setMajorTickFrequencyInternal(U u2);

    /* access modifiers changed from: 0000 */
    public abstract void setMinorTickFrequencyInternal(U u2);

    /* access modifiers changed from: 0000 */
    public abstract T transformChartValueToUserValue(T t2);

    /* access modifiers changed from: 0000 */
    public abstract double transformExternalFrequencyToInternal(U u2);

    /* access modifiers changed from: 0000 */
    public abstract double transformExternalValueToInternal(T t2);

    /* access modifiers changed from: 0000 */
    public abstract T transformInternalValueToExternal(double d2);

    /* access modifiers changed from: 0000 */
    public abstract T transformUserValueToChartValue(T t2);

    /* access modifiers changed from: 0000 */
    public abstract double translatePoint(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract double x();

    static boolean a(String str) {
        return TextUtils.isEmpty(str) || TextUtils.getTrimmedLength(str) == 0;
    }

    Axis() {
        setStyle(new AxisStyle());
        this.n = i.a(this);
    }

    /* access modifiers changed from: 0000 */
    public void a(Series<?> series) {
        this.A.put(series, series.a((a) this.B));
    }

    /* access modifiers changed from: 0000 */
    public void b(Series<?> series) {
        am amVar = (am) this.A.get(series);
        if (amVar != null) {
            amVar.a();
            this.A.remove(series);
        }
    }

    public void specifyBarColumnSpacing(U u2) {
        if (u2 != null) {
            this.f = transformExternalFrequencyToInternal(u2);
            this.E = true;
        } else {
            this.E = false;
            P();
        }
        if (this.b != null) {
            Q();
            O();
        }
    }

    public final Orientation getOrientation() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final boolean a() {
        return this.c == Orientation.HORIZONTAL;
    }

    /* access modifiers changed from: 0000 */
    public final void a(Orientation orientation) {
        this.c = orientation;
        this.n = i.a(this);
    }

    public final Position getPosition() {
        return this.d;
    }

    public final void setPosition(Position position) {
        this.d = position;
        this.n = i.a(this);
    }

    /* access modifiers changed from: 0000 */
    public float b() {
        for (Axis<?, ?> axis : this.b.e.a) {
            if (axis.d == Position.REVERSE) {
                return ((Float) axis.g.d.a).floatValue();
            }
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    /* access modifiers changed from: 0000 */
    public final double c() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public double transformUserValueToInternal(T t2) {
        return transformExternalValueToInternal(transformUserValueToChartValue(applyMappingForSkipRangesToUserValue(t2)));
    }

    /* access modifiers changed from: 0000 */
    public T transformInternalValueToUser(double d2) {
        return transformChartValueToUserValue(removeMappingForSkipRangesFromChartValue(transformInternalValueToExternal(d2)));
    }

    /* access modifiers changed from: 0000 */
    public void validateUserData(Object obj) {
        if (obj == null) {
            v vVar = this.b;
            throw new IllegalArgumentException(vVar != null ? vVar.getContext().getString(R.string.AxisDataPointsNotNull) : "You must supply all DataPoint parameter arguments, non-null");
        }
    }

    /* access modifiers changed from: 0000 */
    public String a(double d2) {
        return getFormattedString(transformInternalValueToUser(d2));
    }

    public final Range<T> getCurrentDisplayedRange() {
        return b(this.i);
    }

    /* access modifiers changed from: 0000 */
    public void a(NumberRange numberRange) {
        boolean z2 = (this.i.a == numberRange.a && this.i.b == numberRange.b) ? false : true;
        synchronized (x.a) {
            this.i = numberRange;
        }
        if (z2) {
            v vVar = this.b;
            if (vVar != null) {
                vVar.b(this);
            }
        }
    }

    public final Range<T> getDataRange() {
        return b(this.k);
    }

    public final Range<T> getDefaultRange() {
        NumberRange numberRange = this.l;
        if (numberRange != null) {
            return b(numberRange);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public double d() {
        double d2 = this.j.b + this.I;
        Double H2 = H();
        if (H2 != null && H2.doubleValue() > d2) {
            return H2.doubleValue();
        }
        if (J()) {
            d2 += x() / 2.0d;
        }
        return d2;
    }

    private Double H() {
        v vVar = this.b;
        Double d2 = null;
        if (vVar == null) {
            return null;
        }
        for (Series series : vVar.getSeriesForAxis(this)) {
            CartesianSeries cartesianSeries = (CartesianSeries) series;
            if (!a(cartesianSeries.j) && cartesianSeries.b != null) {
                double b2 = cartesianSeries.t.b(cartesianSeries);
                if (d2 == null || b2 > d2.doubleValue()) {
                    d2 = Double.valueOf(b2);
                }
            }
        }
        return d2;
    }

    /* access modifiers changed from: 0000 */
    public double e() {
        double d2 = this.j.a - this.J;
        Double I2 = I();
        if (I2 != null && I2.doubleValue() < d2) {
            return I2.doubleValue();
        }
        if (J()) {
            d2 -= x() / 2.0d;
        }
        return d2;
    }

    private Double I() {
        v vVar = this.b;
        Double d2 = null;
        if (vVar == null) {
            return null;
        }
        for (Series series : vVar.getSeriesForAxis(this)) {
            CartesianSeries cartesianSeries = (CartesianSeries) series;
            if (!a(cartesianSeries.j) && cartesianSeries.b != null) {
                double b2 = cartesianSeries.t.b(cartesianSeries);
                if (d2 == null || b2 < d2.doubleValue()) {
                    d2 = Double.valueOf(b2);
                }
            }
        }
        return d2;
    }

    private boolean J() {
        return this.j.c() && this.I == 0.0d && this.J == 0.0d;
    }

    public final void setDefaultRange(Range<T> range) {
        c(range);
        this.m = range;
        a(range);
        g();
    }

    private void c(Range<T> range) {
        if (range != null) {
            if (!Range.b(range)) {
                throw new IllegalArgumentException("Cannot set an undefined range as the default range: infinite minimum or maximum values or negative span not allowed.");
            } else if (range.c()) {
                v vVar = this.b;
                throw new IllegalArgumentException(vVar != null ? vVar.getContext().getString(R.string.AxisDefaultRangeIsEmpty) : "Cannot set a default range with equal minimum and maximum values.");
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(Range<T> range) {
        this.l = range == null ? null : d(range);
        NumberRange numberRange = this.l;
        if (numberRange == null || !numberRange.c()) {
            if (range == null || !Range.a((Range<?>) this.i)) {
                this.v.a();
            } else {
                a((NumberRange) this.l.a());
            }
            O();
            return;
        }
        v vVar = this.b;
        throw new IllegalStateException(vVar != null ? vVar.getContext().getString(R.string.AxisDefaultRangeInternalIsEmpty) : "Cannot set default range due to transformations applied to this axis: have you set skip ranges that completely cover the data or default ranges?");
    }

    public final boolean isCurrentDisplayedRangePreservedOnUpdate() {
        return this.Q;
    }

    public final void setCurrentDisplayedRangePreservedOnUpdate(boolean z2) {
        this.Q = z2;
    }

    public boolean requestCurrentDisplayedRange(T t2, T t3) {
        return this.v.a(transformUserValueToInternal(t2), transformUserValueToInternal(t3));
    }

    public boolean requestCurrentDisplayedRange(T t2, T t3, boolean z2, boolean z3) {
        return this.v.a(transformUserValueToInternal(t2), transformUserValueToInternal(t3), z2, z3);
    }

    /* access modifiers changed from: 0000 */
    public void a(double d2, double d3) {
        boolean z2 = (this.i.a == d2 && this.i.b == d3) ? false : true;
        synchronized (x.a) {
            this.i.a(d2, d3);
        }
        if (z2) {
            v vVar = this.b;
            if (vVar != null) {
                vVar.b.b();
                this.b.b(this);
            }
        }
    }

    public DoubleTapBehavior getDoubleTapBehavior() {
        return this.aa;
    }

    public void setDoubleTapBehavior(DoubleTapBehavior doubleTapBehavior) {
        this.aa = doubleTapBehavior;
    }

    public boolean isDoubleTapEnabled() {
        return this.v.k;
    }

    public void enableDoubleTap(boolean z2) {
        this.v.k = z2;
    }

    public final Range<T> getVisibleRange() {
        return b(this.j);
    }

    /* access modifiers changed from: 0000 */
    public final void f() {
        if (this.b != null) {
            P();
            this.j = L();
            this.k = M();
            N();
            Q();
            if (!K()) {
                g();
            }
        }
    }

    private boolean K() {
        if (this.b != null && Range.a((Range<?>) this.j)) {
            Set<CartesianSeries> e2 = this.b.d.e(this);
            if (e2 != null) {
                for (CartesianSeries s2 : e2) {
                    if (s2.s()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private NumberRange L() {
        NumberRange numberRange = new NumberRange();
        for (cr crVar : this.b.a()) {
            if (crVar.a(this)) {
                numberRange.c(crVar.b(this));
            }
        }
        return numberRange;
    }

    private NumberRange M() {
        NumberRange numberRange = new NumberRange();
        for (cr crVar : this.b.a()) {
            if (crVar.a(this)) {
                numberRange.c(crVar.c(this));
            }
        }
        return numberRange;
    }

    private void N() {
        NumberRange numberRange;
        if (!this.Q || Range.a((Range<?>) this.i)) {
            if (Range.b(this.l)) {
                numberRange = (NumberRange) this.l.a();
            } else if (Range.b(this.j)) {
                numberRange = new NumberRange(Double.valueOf(e()), Double.valueOf(d()));
            } else {
                numberRange = new NumberRange();
            }
            a(numberRange);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void h() {
        this.u.a();
        this.b = null;
    }

    private NumberRange d(Range<T> range) {
        if (range == null) {
            return null;
        }
        return new NumberRange(Double.valueOf(transformUserValueToInternal(range.getMinimum())), Double.valueOf(transformUserValueToInternal(range.getMaximum())));
    }

    private Range<T> b(NumberRange numberRange) {
        if (numberRange != null) {
            return createRange(transformInternalValueToUser(numberRange.a), transformInternalValueToUser(numberRange.b));
        }
        return null;
    }

    public final U getRangePaddingHigh() {
        return this.G;
    }

    public final void setRangePaddingHigh(U u2) {
        this.G = u2;
        if (u2 == null) {
            this.I = 0.0d;
        } else {
            this.I = transformExternalFrequencyToInternal(u2);
        }
        f();
        if (this.b != null) {
            O();
            this.b.b.b();
        }
    }

    public final U getRangePaddingLow() {
        return this.H;
    }

    public final void setRangePaddingLow(U u2) {
        this.H = u2;
        if (u2 == null) {
            this.J = 0.0d;
        } else {
            this.J = transformExternalFrequencyToInternal(u2);
        }
        f();
        if (this.b != null) {
            O();
            this.b.b.b();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean j() {
        return !l();
    }

    public String getExpectedLongestLabel() {
        return this.x;
    }

    public void setExpectedLongestLabel(String str) {
        this.x = str;
    }

    /* access modifiers changed from: 0000 */
    public boolean k() {
        return this.x != null;
    }

    public void setMajorTickMarkValues(List<T> list) {
        if (list == null) {
            this.t = null;
        } else if (list.contains(null)) {
            v vVar = this.b;
            throw new IllegalArgumentException(vVar != null ? vVar.getContext().getString(R.string.AxisNullCustomTickMarkValues) : "Custom tick mark values cannot contain null.");
        } else {
            TreeSet<Comparable> treeSet = new TreeSet<>(list);
            this.t = new double[treeSet.size()];
            int i2 = 0;
            for (Comparable transformUserValueToInternal : treeSet) {
                this.t[i2] = transformUserValueToInternal(transformUserValueToInternal);
                i2++;
            }
        }
    }

    public final U getMajorTickFrequency() {
        return this.p;
    }

    public final void setMajorTickFrequency(U u2) {
        setMajorTickFrequencyInternal(u2);
    }

    /* access modifiers changed from: 0000 */
    public boolean l() {
        return this.p != null;
    }

    public final U getMinorTickFrequency() {
        return this.q;
    }

    public final void setMinorTickFrequency(U u2) {
        setMinorTickFrequencyInternal(u2);
    }

    public final U getCurrentMajorTickFrequency() {
        return this.r;
    }

    /* access modifiers changed from: 0000 */
    public boolean m() {
        return this.r != null;
    }

    /* access modifiers changed from: 0000 */
    public double n() {
        U u2 = this.r;
        if (u2 != null) {
            return transformExternalFrequencyToInternal(u2);
        }
        v vVar = this.b;
        throw new IllegalStateException(vVar != null ? vVar.getContext().getString(R.string.AxisNullMajorTickFrequency) : "Null currentMajorTickFrequency");
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentMajorTickFrequency(U u2) {
        this.r = u2;
    }

    public final U getCurrentMinorTickFrequency() {
        return this.s;
    }

    /* access modifiers changed from: 0000 */
    public boolean o() {
        return this.s != null;
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentMinorTickFrequency(U u2) {
        this.s = u2;
    }

    /* access modifiers changed from: 0000 */
    public void d(int i2) {
        if (Range.a((Range<?>) this.i)) {
            v vVar = this.b;
            cx.b(vVar != null ? vVar.getContext().getString(R.string.AxisUndefinedRange) : "The axis has an undefined data range and cannot be displayed");
            return;
        }
        a(j(), i2);
    }

    private void a(boolean z2, int i2) {
        if (z2) {
            c(i2);
            this.U = n();
            return;
        }
        p();
        r();
    }

    /* access modifiers changed from: 0000 */
    public void r() {
        String i2 = i();
        if (i2 != null) {
            a(this.Z, i2);
        }
        a(this.Z);
    }

    /* access modifiers changed from: 0000 */
    public void a(PointF pointF, String str) {
        this.Y.b(pointF, str, ((Float) this.g.g.c.a).floatValue(), (Typeface) this.g.g.b.a, this.b);
    }

    /* access modifiers changed from: 0000 */
    public void a(PointF pointF) {
        double d2;
        double d3;
        String str;
        switch ((com.shinobicontrols.charts.TickMark.Orientation) this.g.g.l.a) {
            case HORIZONTAL:
                d3 = (double) pointF.x;
                d2 = (double) pointF.y;
                break;
            case DIAGONAL:
                d3 = (double) ((pointF.x + pointF.y) * 0.70710677f);
                d2 = (double) ((pointF.x + pointF.y) * 0.70710677f);
                break;
            case VERTICAL:
                d3 = (double) pointF.y;
                d2 = (double) pointF.x;
                break;
            default:
                v vVar = this.b;
                if (vVar != null) {
                    str = vVar.getContext().getString(R.string.AxisUnrecognisedOrientation);
                } else {
                    str = "tickLabel orientation not recognised";
                }
                throw new IllegalStateException(str);
        }
        Point point = this.V;
        point.x = (int) (d3 + 0.5d);
        point.y = (int) (d2 + 0.5d);
    }

    /* access modifiers changed from: 0000 */
    public void s() {
        if (!this.F && !this.b.l()) {
            v vVar = this.b;
            cx.b(vVar != null ? vVar.getContext().getString(R.string.AxisInsufficientWidth) : "Axis width does not provide enough space to fit the tickmarks and ticklabels.");
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(double d2, double d3, double d4) {
        boolean z2 = true;
        if (a()) {
            if (b(d2, d3, d4) + ((double) i.a(this.b.f, Position.NORMAL)) <= 0.0d) {
                z2 = false;
            }
            return z2;
        }
        if (b(d2, d3, d4) > ((double) (this.b.getHeight() - i.a(this.b.e, Position.REVERSE)))) {
            z2 = false;
        }
        return z2;
    }

    /* access modifiers changed from: 0000 */
    public double b(double d2, double d3, double d4) {
        if (this.c == Orientation.HORIZONTAL) {
            return (d3 * (d2 - this.i.a)) / d4;
        }
        return (d3 * (this.i.b - d2)) / d4;
    }

    public AxisStyle getStyle() {
        return this.g;
    }

    public final void setStyle(AxisStyle axisStyle) {
        if (this.g != null) {
            this.K.a();
        }
        this.g = axisStyle;
        AxisStyle axisStyle2 = this.g;
        if (axisStyle2 != null) {
            this.K = axisStyle2.a((Handler) this.L);
            if (this.b != null) {
                P();
                Q();
            }
        }
    }

    /* access modifiers changed from: private */
    public void O() {
        v vVar = this.b;
        if (vVar != null) {
            for (Series series : vVar.getSeriesForAxis(this)) {
                series.p.a();
            }
        }
    }

    public ClippingMode getTickMarkClippingModeHigh() {
        return this.M;
    }

    public ClippingMode getTickMarkClippingModeLow() {
        return this.N;
    }

    public void setTickMarkClippingModeHigh(ClippingMode clippingMode) {
        this.M = clippingMode;
    }

    public void setTickMarkClippingModeLow(ClippingMode clippingMode) {
        this.N = clippingMode;
    }

    public final String getTitle() {
        return this.O;
    }

    public final void setTitle(String str) {
        this.O = str;
        Title title = this.R;
        if (title != null) {
            title.setText(str);
            a(this.R);
        }
    }

    private void a(Title title) {
        title.setVisibility((title == null || a(title.getText().toString())) ? 8 : 0);
    }

    /* access modifiers changed from: 0000 */
    public final Title t() {
        return u();
    }

    /* access modifiers changed from: 0000 */
    public Title u() {
        if (this.R == null) {
            v vVar = this.b;
            if (vVar != null) {
                this.R = new Title(vVar.getContext());
                this.R.setLayoutParams(new MarginLayoutParams(-2, -2));
                v();
                this.R.setText(this.O);
                a(this.R);
            }
        }
        return this.R;
    }

    /* access modifiers changed from: 0000 */
    public void v() {
        if (this.R != null && this.g.h != null) {
            AxisTitleStyle axisTitleStyle = this.g.h;
            this.R.a(axisTitleStyle.getOrientation());
            this.R.a((TitleStyle) axisTitleStyle);
        }
    }

    public final Float getWidth() {
        return this.P;
    }

    public final void setWidth(Float f2) {
        this.P = f2;
    }

    public boolean isPanningOutOfDefaultRangeAllowed() {
        return this.v.a;
    }

    public boolean isPanningOutOfMaxRangeAllowed() {
        return this.v.b;
    }

    public boolean isBouncingAtLimitsEnabled() {
        return this.v.c;
    }

    public boolean isAnimationEnabled() {
        return this.v.d;
    }

    public boolean isGesturePanningEnabled() {
        return this.v.e;
    }

    public boolean isGestureZoomingEnabled() {
        return this.v.f;
    }

    public boolean isMomentumPanningEnabled() {
        return this.v.g;
    }

    public boolean isMomentumZoomingEnabled() {
        return this.v.h;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(double d2, boolean z2, boolean z3) {
        if (d2 == 0.0d) {
            return false;
        }
        return this.v.a(d2, z2, z3);
    }

    public void allowPanningOutOfDefaultRange(boolean z2) {
        ch chVar = this.v;
        chVar.a = z2;
        if (!z2) {
            chVar.a();
        }
    }

    public void allowPanningOutOfMaxRange(boolean z2) {
        ch chVar = this.v;
        chVar.b = z2;
        if (!z2) {
            chVar.a();
        }
    }

    public void enableBouncingAtLimits(boolean z2) {
        this.v.c = z2;
    }

    public void enableAnimation(boolean z2) {
        this.v.d = z2;
    }

    public void enableGesturePanning(boolean z2) {
        this.v.e = z2;
    }

    public void enableGestureZooming(boolean z2) {
        this.v.f = z2;
    }

    public void enableMomentumPanning(boolean z2) {
        this.v.g = z2;
    }

    public void enableMomentumZooming(boolean z2) {
        this.v.h = z2;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(double d2, double d3, boolean z2, boolean z3) {
        if (d2 > 0.0d && !Double.isInfinite(d2) && !Double.isNaN(d2)) {
            return this.v.b(d2, d3, z2, z3);
        }
        cx.a("Zoom must be greater than 0 and a real number");
        return false;
    }

    /* access modifiers changed from: 0000 */
    public double w() {
        return this.v.b();
    }

    /* access modifiers changed from: 0000 */
    public boolean c(double d2) {
        return d2 > n();
    }

    private void e(int i2) {
        c cVar = this.W;
        cVar.A = i2;
        cVar.B = this.i.b();
        this.W.b = ((Boolean) this.g.g.h.a).booleanValue();
        this.W.c = ((Boolean) this.g.g.i.a).booleanValue();
        this.W.d = ((Boolean) this.g.g.j.a).booleanValue();
        this.W.e = ((Boolean) this.g.f.a.a).booleanValue();
        this.W.f = ((Boolean) this.g.e.c.a).booleanValue();
        this.W.g = ((Float) this.g.g.f.a).floatValue();
        this.W.h = ((Float) this.g.g.g.a).floatValue();
        c cVar2 = this.W;
        cVar2.k = cVar2.h / 2.0f;
        this.W.m = ((Float) this.g.g.k.a).floatValue();
        this.W.n = ((Integer) this.g.g.e.a).intValue();
        this.W.o = ((Integer) this.g.f.c.a).intValue();
        this.W.t = (com.shinobicontrols.charts.TickMark.Orientation) this.g.g.l.a;
        this.W.p = ((Integer) this.g.g.a.a).intValue();
        this.W.q = ((Integer) this.g.g.d.a).intValue();
        this.W.r = (Typeface) this.g.g.b.a;
        this.W.s = ((Float) this.g.g.c.a).floatValue() * this.b.getResources().getDisplayMetrics().scaledDensity;
        this.W.u = ((Boolean) this.g.f.b.a).booleanValue();
        this.W.v = S();
        this.W.w = ((Integer) this.g.e.a.a).intValue();
        this.W.x = ((Integer) this.g.e.b.a).intValue();
        c cVar3 = this.W;
        cVar3.y = this.V;
        cVar3.C = (float) cVar3.A;
        c cVar4 = this.W;
        cVar4.D = BitmapDescriptorFactory.HUE_RED;
        cVar4.E = (float) (a() ? this.W.y.x : this.W.y.y);
        this.W.F = a() ? this.M : this.N;
        this.W.G = a() ? this.N : this.M;
        c cVar5 = this.W;
        cVar5.H = BitmapDescriptorFactory.HUE_RED;
        if (cVar5.c || this.W.d) {
            c cVar6 = this.W;
            cVar6.H = cVar6.g + this.W.m;
        }
        c cVar7 = this.W;
        cVar7.I = (float) this.h;
        cVar7.J = G();
        this.W.l = getStyle().getLineWidth();
        this.W.i = getStyle().getGridlineStyle().getLineWidth();
    }

    /* access modifiers changed from: 0000 */
    public void a(Canvas canvas, Rect rect) {
        this.n.a(this.X, rect, this.g.getLineWidth(), this.h, b());
        this.T.setColor(((Integer) this.g.c.a).intValue());
        canvas.drawRect(this.X, this.T);
        e(this.c == Orientation.HORIZONTAL ? rect.width() : rect.height());
        c cVar = this.W;
        cVar.a = rect;
        this.n.a(cVar);
        this.u.a(this.W);
        s();
        this.u.a(canvas, this.W);
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z2) {
        float f2;
        float max = Math.max(((Float) this.g.d.a).floatValue(), BitmapDescriptorFactory.HUE_RED);
        if (Range.a((Range<?>) this.i)) {
            this.o = this.n.a(0, max);
            return;
        }
        if (((Boolean) this.g.g.i.a).booleanValue() || ((Boolean) this.g.g.j.a).booleanValue()) {
            f2 = Math.max(((Float) this.g.g.k.a).floatValue(), BitmapDescriptorFactory.HUE_RED) + Math.max(((Float) this.g.g.f.a).floatValue(), BitmapDescriptorFactory.HUE_RED);
        } else {
            f2 = BitmapDescriptorFactory.HUE_RED;
        }
        this.o = this.n.a(0, max + f2);
        if (((Boolean) this.g.g.h.a).booleanValue()) {
            this.o += a() ? this.V.y : this.V.x;
        }
        if (u().getVisibility() == 0 && z2) {
            this.o += a() ? at.a(u()) : at.b(u());
        }
        Float f3 = this.P;
        boolean z3 = true;
        if (f3 != null) {
            float max2 = Math.max(BitmapDescriptorFactory.HUE_RED, f3.floatValue());
            if (max2 < ((float) this.o)) {
                z3 = false;
            }
            this.F = z3;
            this.o = Math.round(max2);
        } else {
            this.F = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i2, int i3, PointF pointF) {
        float f2 = ((float) this.V.x) * pointF.x;
        float f3 = ((float) this.V.y) * pointF.y;
        if (c(this.U)) {
            f2 *= 1.3f;
            f3 *= 1.3f;
        }
        return i2 >= 0 && (!a() || ((float) i3) - (f2 * ((float) i2)) >= BitmapDescriptorFactory.HUE_RED) && (a() || ((float) i3) - (f3 * ((float) i2)) >= BitmapDescriptorFactory.HUE_RED);
    }

    /* access modifiers changed from: 0000 */
    public w y() {
        return this.b.k();
    }

    private void P() {
        if (!this.E) {
            this.f = 0.0d;
            List seriesForAxis = this.b.getSeriesForAxis(this);
            if (!seriesForAxis.isEmpty()) {
                List b2 = b(seriesForAxis);
                if (b2.size() != 0) {
                    int i2 = 0;
                    this.e = a((InternalDataPoint) b2.get(0));
                    this.D = a((InternalDataPoint) b2.get(b2.size() - 1));
                    if (this.D - this.e == 0.0d) {
                        this.f = x();
                        return;
                    }
                    boolean z2 = false;
                    while (i2 < b2.size() - 1) {
                        double a2 = a((InternalDataPoint) b2.get(i2));
                        i2++;
                        double a3 = a((InternalDataPoint) b2.get(i2));
                        double abs = Math.abs(a3 - a2);
                        if (a2 != a3 && (!z2 || abs < this.f)) {
                            this.f = abs;
                            z2 = true;
                        }
                    }
                }
            }
        }
    }

    private List<InternalDataPoint> b(List<Series<?>> list) {
        ArrayList arrayList = new ArrayList();
        for (Series series : list) {
            if ((series instanceof BarColumnSeries) && a(((BarColumnSeries) series).j)) {
                for (InternalDataPoint add : series.n.c) {
                    arrayList.add(add);
                }
            }
        }
        if (a()) {
            Collections.sort(arrayList, InternalDataPoint.k);
        } else {
            Collections.sort(arrayList, InternalDataPoint.l);
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(com.shinobicontrols.charts.Series.Orientation orientation) {
        return (orientation == com.shinobicontrols.charts.Series.Orientation.HORIZONTAL && this.c == Orientation.HORIZONTAL) || (orientation == com.shinobicontrols.charts.Series.Orientation.VERTICAL && this.c == Orientation.VERTICAL);
    }

    private double a(InternalDataPoint internalDataPoint) {
        return this.c == Orientation.HORIZONTAL ? internalDataPoint.a : internalDataPoint.b;
    }

    private void Q() {
        List<Series> seriesForAxis = this.b.getSeriesForAxis(this);
        ArrayList<cr> arrayList = new ArrayList<>();
        for (Series series : seriesForAxis) {
            cr crVar = series.t;
            if (crVar != null && !arrayList.contains(crVar)) {
                arrayList.add(crVar);
            }
        }
        for (cr d2 : arrayList) {
            d2.d(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public OnGestureListener z() {
        return this.v;
    }

    /* access modifiers changed from: 0000 */
    public double d(double d2) {
        Rect rect = y().b;
        return (d2 / ((double) (this.c == Orientation.HORIZONTAL ? rect.width() : rect.height()))) * this.i.b();
    }

    /* access modifiers changed from: 0000 */
    public double e(double d2) {
        Rect rect = y().b;
        if (this.c != Orientation.HORIZONTAL) {
            d2 = ((double) rect.height()) - d2;
        }
        return this.i.a + d(d2);
    }

    /* access modifiers changed from: 0000 */
    public double a(double d2, CartesianSeries<?> cartesianSeries) {
        double d3;
        if (this.c == Orientation.VERTICAL) {
            d3 = (double) this.b.b.b.top;
        } else {
            d3 = (double) this.b.b.b.left;
        }
        return d3 + this.n.a(d2, this.b.b.b.width(), this.b.b.b.height());
    }

    public float getPixelValueForUserValue(T t2) {
        if (!Range.a((Range<?>) this.i)) {
            return (float) f(transformUserValueToInternal(t2));
        }
        v vVar = this.b;
        cx.a(vVar != null ? vVar.getContext().getString(R.string.AxisRangeNotSetPixelCall) : "Calling getPixelValueForUserValue before an axisRange has been set.");
        return BitmapDescriptorFactory.HUE_RED;
    }

    /* access modifiers changed from: 0000 */
    public double f(double d2) {
        return this.n.a(d2, this.b.b.b.width(), this.b.b.b.height()) + ((double) R());
    }

    public T getUserValueForPixelValue(float f2) {
        if (!Range.a((Range<?>) this.i)) {
            return transformInternalValueToUser(this.n.a((double) (f2 - ((float) R())), this.b.b.b));
        }
        v vVar = this.b;
        cx.a(vVar != null ? vVar.getContext().getString(R.string.AxisRangeNotSetUserCall) : "Calling getUserValueForPixelValue before an axisRange has been set.");
        return null;
    }

    private int R() {
        if (this.c == Orientation.HORIZONTAL) {
            return this.b.a.left + this.b.b.b.left;
        }
        return this.b.a.top + this.b.b.b.top;
    }

    /* access modifiers changed from: 0000 */
    public df B() {
        return new df();
    }

    public MotionState getMotionState() {
        return this.v.l;
    }

    public final ShinobiChart getChart() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public void a(Rect rect, int i2, int i3) {
        Title title = this.R;
        if (title != null) {
            this.n.a(rect, this.o, this.h, title, this.S.a);
            Gravity.apply(this.n.a((com.shinobicontrols.charts.Title.Position) this.g.h.g.a), this.R.getMeasuredWidth(), this.R.getMeasuredHeight(), this.S.a, this.S.b());
            at.b(this.R, this.S.b);
        }
    }

    private DashPathEffect S() {
        if (this.g.f.d.a == null || ((float[]) this.g.f.d.a).length < 1) {
            return null;
        }
        float[] fArr = new float[((float[]) this.g.f.d.a).length];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = (float) at.a(this.a, 0, ((float[]) this.g.f.d.a)[i2]);
        }
        return new DashPathEffect(fArr, BitmapDescriptorFactory.HUE_RED);
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar) {
        this.b = vVar;
        if (vVar != null) {
            this.a = vVar.getContext().getResources().getDisplayMetrics().density;
            if (this.ab.size() > 0) {
                vVar.s();
            }
        }
    }

    public void removeAllSkipRanges() {
        this.ab.clear();
        D();
    }

    public List<Range<T>> getSkipRanges() {
        return Collections.unmodifiableList(this.ab);
    }

    /* access modifiers changed from: 0000 */
    public am a(a aVar) {
        return this.C.a(db.a, (a) aVar);
    }

    /* access modifiers changed from: 0000 */
    public final void C() {
        this.C.a(new db());
    }

    public void addSkipRange(Range<T> range) {
        v vVar = this.b;
        if (vVar != null) {
            vVar.s();
        }
        if (!b(range)) {
            T();
            return;
        }
        this.ab.add(range);
        D();
    }

    public void addSkipRanges(List<? extends Range<T>> list) {
        v vVar = this.b;
        if (vVar != null) {
            vVar.s();
        }
        ArrayList arrayList = new ArrayList();
        for (Range range : list) {
            if (b(range)) {
                arrayList.add(range);
            } else {
                T();
            }
        }
        if (!arrayList.isEmpty()) {
            this.ab.addAll(arrayList);
            D();
        }
    }

    private void T() {
        v vVar = this.b;
        cx.b(vVar != null ? vVar.getContext().getString(R.string.CannotAddNullUndefinedOrEmptySkip) : "Cannot add a null skip range or one with a zero or negative span.");
    }

    public void removeSkipRange(Range<T> range) {
        this.ab.remove(range);
        D();
    }

    public void removeSkipRanges(List<? extends Range<T>> list) {
        this.ab.removeAll(list);
        D();
    }

    /* access modifiers changed from: 0000 */
    public void D() {
        Range currentDisplayedRange = getCurrentDisplayedRange();
        a(E());
        if (Range.b(this.m) && !this.m.c()) {
            a(this.m);
        }
        C();
        U();
        v vVar = this.b;
        if (vVar != null) {
            vVar.redrawChart();
            e(currentDisplayedRange);
        }
    }

    private void U() {
        HashSet<Axis> hashSet = new HashSet<>();
        hashSet.add(this);
        v vVar = this.b;
        if (vVar != null) {
            Set<CartesianSeries> e2 = vVar.d.e(this);
            if (e2 != null) {
                for (CartesianSeries cartesianSeries : e2) {
                    hashSet.add(cartesianSeries.getXAxis());
                    hashSet.add(cartesianSeries.getYAxis());
                }
            }
        }
        for (Axis f2 : hashSet) {
            f2.f();
        }
    }

    /* access modifiers changed from: 0000 */
    public List<Range<T>> E() {
        return this.ab;
    }

    /* access modifiers changed from: 0000 */
    public boolean F() {
        return E().size() > 0;
    }

    private void e(final Range<T> range) {
        if (Range.b(range)) {
            this.b.post(new Runnable() {
                public void run() {
                    Axis.this.requestCurrentDisplayedRange(range.getMinimum(), range.getMaximum(), false, false);
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(List<Range<T>> list) {
        List b2 = this.ac.b(this.ac.a(list));
        this.y.a(this.ad.a(b2));
        this.z.a(this.ae.a(b2));
    }

    /* access modifiers changed from: 0000 */
    public boolean b(Range<T> range) {
        return Range.b(range) && !range.c();
    }

    /* access modifiers changed from: 0000 */
    public boolean isUserDataPointWithinASkipRange(Object obj) {
        ap b2 = this.y.b(convertUserValueTypeToInternalDataType(obj));
        return b2 != null && b2.c.a == 0.0d;
    }
}
