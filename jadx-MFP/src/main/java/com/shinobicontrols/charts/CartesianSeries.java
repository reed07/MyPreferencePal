package com.shinobicontrols.charts;

import com.shinobicontrols.charts.Series.Orientation;
import com.shinobicontrols.charts.SeriesStyle;
import java.util.ArrayList;
import java.util.List;

public abstract class CartesianSeries<T extends SeriesStyle> extends Series<T> {
    private CartesianSeries<?> A;
    private NumberRange B = new NumberRange();
    private NumberRange C = new NumberRange();
    private final List<InternalDataPoint> D = new ArrayList();
    private int a;
    Object b = null;
    Integer c = null;
    boolean d;
    boolean e = false;
    boolean f = false;
    boolean g = false;
    boolean h = false;
    boolean i = false;
    Orientation j = Orientation.HORIZONTAL;
    cq k;
    aa l = c();

    enum a {
        CROW_FLIES,
        HORIZONTAL,
        VERTICAL
    }

    /* access modifiers changed from: 0000 */
    public abstract aa c();

    CartesianSeries() {
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3, CartesianSeries<?> cartesianSeries) {
        this.a = i2;
        this.A = cartesianSeries;
    }

    /* access modifiers changed from: 0000 */
    public int i() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public CartesianSeries<?> j() {
        return this.A;
    }

    public Object getBaseline() {
        return this.b;
    }

    public void setBaseline(Object obj) {
        this.b = obj;
        this.p.a();
        Axis yAxis = this.j == Orientation.HORIZONTAL ? getYAxis() : getXAxis();
        if (yAxis != null) {
            yAxis.v.a();
        }
    }

    /* access modifiers changed from: 0000 */
    public void k() {
        super.k();
        this.p.a();
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar) {
        super.a(vVar);
        this.p.a();
    }

    public Integer getStackId() {
        return this.c;
    }

    public void setStackId(Integer num) {
        synchronized (x.a) {
            this.c = num;
            this.p.a();
            if (this.t != null) {
                this.t.b();
            }
            r();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean l() {
        return this.A != null;
    }

    /* access modifiers changed from: 0000 */
    public void m() {
        if (this.o != null && this.m != null) {
            Axis xAxisForSeries = this.o.getXAxisForSeries(this);
            Axis yAxisForSeries = this.o.getYAxisForSeries(this);
            if (xAxisForSeries != null && yAxisForSeries != null) {
                if (this.m.size() > 0) {
                    a(xAxisForSeries, yAxisForSeries);
                }
                synchronized (x.a) {
                    a(this.n, this.n.a(this.m));
                }
                this.B = new NumberRange();
                this.C = new NumberRange();
                this.B.c(this.n.a);
                this.C.c(this.n.b);
                this.p.a();
                this.o.redrawChart();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Axis<?, ?> axis, Axis<?, ?> axis2) {
        if (axis.isDataValid(this.m.get(0).getX()) && axis2.isDataValid(this.m.get(0).getY())) {
            return;
        }
        if (this.o == null) {
            throw new IllegalStateException("Chart is null and data is invalid for assigned x/y axes.");
        }
        throw new IllegalStateException(this.o.getContext().getString(R.string.CartesianInvalidDataXY));
    }

    /* access modifiers changed from: 0000 */
    public void a(ao aoVar, Object[] objArr) {
        Axis xAxisForSeries = this.o.getXAxisForSeries(this);
        Axis yAxisForSeries = this.o.getYAxisForSeries(this);
        xAxisForSeries.A();
        yAxisForSeries.A();
        this.D.clear();
        int length = objArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            try {
                Data data = objArr[i2];
                if ((!xAxisForSeries.F() && !yAxisForSeries.F()) || !a(xAxisForSeries, yAxisForSeries, data)) {
                    InternalDataPoint a2 = a(data, xAxisForSeries, yAxisForSeries);
                    a2.i = i3;
                    this.D.add(a2);
                    a(aoVar, a2);
                    i3++;
                }
                i2++;
            } catch (IllegalArgumentException e2) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.o.getContext().getString(R.string.CartesianCannotAddPoint));
                sb.append(" ");
                sb.append(e2.getMessage());
                cx.b(sb.toString());
                throw e2;
            }
        }
        aoVar.a(this.D.size());
        aoVar.c = (InternalDataPoint[]) this.D.toArray(aoVar.c);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Axis<?, ?> axis, Axis<?, ?> axis2, Data<?, ?> data) {
        return axis.isUserDataPointWithinASkipRange(data.getX()) || axis2.isUserDataPointWithinASkipRange(data.getY());
    }

    /* access modifiers changed from: 0000 */
    public void a(ao aoVar, InternalDataPoint internalDataPoint) {
        aoVar.a.a(internalDataPoint.a);
        aoVar.b.a(internalDataPoint.b);
    }

    /* access modifiers changed from: 0000 */
    public InternalDataPoint a(Data<?, ?> data, Axis<?, ?> axis, Axis<?, ?> axis2) {
        InternalDataPoint internalDataPoint = new InternalDataPoint(axis.convertPoint(data.getX()), axis2.convertPoint(data.getY()));
        if (data instanceof SelectableData) {
            internalDataPoint.h = ((SelectableData) data).getSelected();
        }
        return internalDataPoint;
    }

    /* access modifiers changed from: 0000 */
    public void n() {
        if (this.m.get(0) instanceof MultiValueData) {
            return;
        }
        if (this.o == null) {
            throw new IllegalStateException("Chart is null and this kind of chart requires MultiValueData data points");
        }
        throw new IllegalStateException(this.o.getContext().getString(R.string.CartesianNeedMultiValueData));
    }

    /* access modifiers changed from: 0000 */
    public final NumberRange o() {
        return this.B;
    }

    /* access modifiers changed from: 0000 */
    public final NumberRange p() {
        return this.C;
    }

    /* access modifiers changed from: 0000 */
    public NumberRange b(Axis<?, ?> axis) {
        return (NumberRange) (axis.a() ? this.B : this.C).a();
    }

    /* access modifiers changed from: 0000 */
    public final NumberRange c(Axis<?, ?> axis) {
        return (NumberRange) (axis.a() ? this.B : this.C).a();
    }

    public boolean isSelected() {
        return this.d;
    }

    public void setSelected(boolean z) {
        if (this.d != z) {
            synchronized (x.a) {
                this.d = z;
                if (this.n != null) {
                    for (InternalDataPoint internalDataPoint : this.n.c) {
                        internalDataPoint.h = z;
                    }
                }
            }
            if (this.o != null) {
                this.p.a();
                this.o.a(this);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar, bz bzVar, boolean z) {
        cn.a(this, aVar, bzVar, z);
    }

    /* access modifiers changed from: 0000 */
    public a f() {
        return a.CROW_FLIES;
    }

    /* access modifiers changed from: 0000 */
    public a g() {
        return a.HORIZONTAL;
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar, bz bzVar) {
        aVar.a((bz) null);
    }

    public boolean isCrosshairEnabled() {
        return this.l.a;
    }

    public void setCrosshairEnabled(boolean z) {
        this.l.a = z;
    }

    public Orientation getOrientation() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public double h() {
        if (this.u.a != null) {
            return (getXAxis().i.a * (1.0d - ((double) this.u.a.floatValue()))) + (getXAxis().i.b * ((double) this.u.a.floatValue()));
        }
        return (getXAxis().i.a + getXAxis().i.b) * 0.5d;
    }

    /* access modifiers changed from: 0000 */
    public double b() {
        if (this.u.b != null) {
            return (getYAxis().i.a * (1.0d - ((double) this.u.b.floatValue()))) + (getYAxis().i.b * ((double) this.u.b.floatValue()));
        }
        return (getYAxis().i.a + getYAxis().i.b) * 0.5d;
    }
}
