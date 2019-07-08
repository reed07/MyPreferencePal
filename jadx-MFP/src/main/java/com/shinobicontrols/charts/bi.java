package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;

class bi extends t<LineSeries> {
    private static float[] P = new float[0];
    private static int[] Q = new int[0];
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private float H;
    private FillStyle I;
    private boolean J;
    private boolean K;
    private float L;
    private int[] M;
    private float[] N;
    private float[] O;
    int a;
    int b = 0;
    float c;
    float d;
    boolean e = false;
    float[] f;
    float[] g;
    int h;
    int i;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    private boolean f(int i2) {
        return i2 != 0;
    }

    bi(LineSeries lineSeries) {
        super(lineSeries);
    }

    /* access modifiers changed from: 0000 */
    public void a(SChartGLDrawer sChartGLDrawer, float[] fArr, float f2) {
        if (((LineSeries) this.k).s()) {
            a((LineSeries) this.k, (double) f2);
            this.L = f2;
            a(sChartGLDrawer, this.b);
            b(sChartGLDrawer, this.b);
            a(sChartGLDrawer);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        float[] fArr = this.f;
        if (fArr == null || fArr.length != i2) {
            this.f = new float[i2];
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2) {
        float[] fArr = this.g;
        if (fArr == null || fArr.length != i2) {
            this.g = new float[i2];
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(int i2) {
        float[] fArr = this.N;
        if (fArr == null || fArr.length != i2) {
            this.N = new float[i2];
        }
    }

    /* access modifiers changed from: 0000 */
    public void d(int i2) {
        float[] fArr = this.O;
        if (fArr == null || fArr.length != i2) {
            this.O = new float[i2];
        }
    }

    /* access modifiers changed from: 0000 */
    public void e(int i2) {
        int[] iArr = this.M;
        if (iArr == null || iArr.length != i2) {
            this.M = new int[i2];
        }
    }

    private void a(SChartGLDrawer sChartGLDrawer, int i2) {
        if (this.I == FillStyle.NONE) {
            return;
        }
        if (this.K) {
            sChartGLDrawer.drawBandSeriesFill(this.f, this.k, this.g, i2, this.B, this.E, ((LineSeries) this.k).j.a(), this.a, this.j);
            return;
        }
        float[] fArr = this.f;
        Series series = this.k;
        int i3 = this.B;
        int i4 = this.E;
        int i5 = this.C;
        sChartGLDrawer.drawHorizontalFill(fArr, series, i2, i3, i4, i5, this.F, f(i5), f(this.F), this.m, this.c, this.d, 1 - ((LineSeries) this.k).j.a(), this.I == FillStyle.GRADIENT, this.j);
    }

    private void b(SChartGLDrawer sChartGLDrawer, int i2) {
        if (f(this.D) && this.J) {
            sChartGLDrawer.drawLineStrip(this.f, this.k, i2, this.D, this.G, this.H, this.m, 1 - ((LineSeries) this.k).j.a(), this.j);
        }
    }

    private void a(SChartGLDrawer sChartGLDrawer) {
        boolean z2;
        float[] fArr;
        if (sChartGLDrawer.getPerformCalculations()) {
            boolean z3 = false;
            if (this.e) {
                if (e()) {
                    int i2 = 0;
                    for (InternalDataPoint internalDataPoint : this.l.c) {
                        if (!internalDataPoint.h) {
                            this.f[i2] = (float) internalDataPoint.c;
                            this.f[i2 + 1] = (float) internalDataPoint.d;
                            a(internalDataPoint, i2);
                            i2 += 2;
                        }
                    }
                    boolean z4 = this.o > BitmapDescriptorFactory.HUE_RED && this.u != 0;
                    float[] fArr2 = this.f;
                    Series series = this.k;
                    int[] iArr = this.M;
                    int i3 = this.t;
                    int i4 = this.v;
                    float f2 = this.n;
                    float[] fArr3 = this.N;
                    float f3 = this.r;
                    float f4 = this.m;
                    int a2 = 1 - ((LineSeries) this.k).j.a();
                    float[] fArr4 = this.j;
                    if (z4 || f()) {
                        fArr = fArr4;
                        z2 = false;
                    } else {
                        fArr = fArr4;
                        z2 = true;
                    }
                    int i5 = i2;
                    sChartGLDrawer.drawDataPoints(fArr2, series, iArr, i2, i3, i4, f2, fArr3, f3, f4, a2, fArr, z2);
                    if (z4) {
                        sChartGLDrawer.drawDataPoints(this.f, this.k, this.M, i5, this.u, this.w, this.n, this.O, BitmapDescriptorFactory.HUE_RED, this.m, 1 - ((LineSeries) this.k).j.a(), this.j, !f());
                    }
                }
                if (f()) {
                    int i6 = 0;
                    for (InternalDataPoint internalDataPoint2 : this.l.c) {
                        if (internalDataPoint2.h) {
                            this.f[i6] = (float) internalDataPoint2.c;
                            this.f[i6 + 1] = (float) internalDataPoint2.d;
                            a(internalDataPoint2, i6);
                            i6 += 2;
                        }
                    }
                    if (this.q > BitmapDescriptorFactory.HUE_RED && this.y != 0) {
                        z3 = true;
                    }
                    int i7 = i6;
                    sChartGLDrawer.drawDataPoints(this.f, this.k, this.M, i6, this.x, this.z, this.p, this.N, this.s, this.m, 1 - ((LineSeries) this.k).j.a(), this.j, !z3);
                    if (z3) {
                        sChartGLDrawer.drawDataPoints(this.f, this.k, this.M, i7, this.y, this.A, this.p, this.O, BitmapDescriptorFactory.HUE_RED, this.m, 1 - ((LineSeries) this.k).j.a(), this.j, true);
                    }
                }
            } else if (e()) {
                float f5 = this.n;
                int i8 = 0;
                for (InternalDataPoint internalDataPoint3 : this.l.c) {
                    if (!internalDataPoint3.h) {
                        this.f[i8] = (float) internalDataPoint3.c;
                        this.f[i8 + 1] = (float) internalDataPoint3.d;
                        a(internalDataPoint3, i8);
                        i8 += 2;
                    }
                }
                if (this.o > BitmapDescriptorFactory.HUE_RED && this.u != 0) {
                    z3 = true;
                }
                int i9 = i8;
                sChartGLDrawer.drawDataPoints(this.f, this.k, this.M, i8, this.t, this.v, f5, this.N, this.r, this.m, 1 - ((LineSeries) this.k).j.a(), this.j, !z3);
                if (z3) {
                    sChartGLDrawer.drawDataPoints(this.f, this.k, this.M, i9, this.u, this.w, f5, this.O, BitmapDescriptorFactory.HUE_RED, this.m, 1 - ((LineSeries) this.k).j.a(), this.j, true);
                }
            }
        } else if (d()) {
            float[] fArr5 = P;
            sChartGLDrawer.drawDataPoints(fArr5, null, Q, 0, this.t, this.v, BitmapDescriptorFactory.HUE_RED, fArr5, this.r, BitmapDescriptorFactory.HUE_RED, 0, this.j, true);
        }
    }

    private void a(LineSeries lineSeries, double d2) {
        LineSeriesStyle lineSeriesStyle = (LineSeriesStyle) (lineSeries.d ? lineSeries.r : lineSeries.q);
        this.K = lineSeries.l();
        this.l = lineSeries.n;
        this.J = lineSeriesStyle.isLineShown();
        this.I = lineSeriesStyle.getFillStyle();
        this.B = lineSeriesStyle.getAreaColor();
        this.C = lineSeriesStyle.getAreaColorGradient();
        this.D = lineSeriesStyle.getLineColor();
        this.H = lineSeriesStyle.getLineWidth();
        this.E = lineSeriesStyle.getAreaColorBelowBaseline();
        this.F = lineSeriesStyle.getAreaColorGradientBelowBaseline();
        this.G = lineSeriesStyle.getLineColorBelowBaseline();
        int areaLineColor = lineSeriesStyle.getAreaLineColor();
        float areaLineWidth = lineSeriesStyle.getAreaLineWidth();
        int areaLineColorBelowBaseline = lineSeriesStyle.getAreaLineColorBelowBaseline();
        PointStyle pointStyle = lineSeriesStyle.getPointStyle();
        PointStyle selectedPointStyle = lineSeriesStyle.getSelectedPointStyle();
        this.n = pointStyle.getRadius();
        this.o = pointStyle.getInnerRadius();
        this.t = pointStyle.getColor();
        this.u = pointStyle.getInnerColor();
        this.v = pointStyle.getColorBelowBaseline();
        this.w = pointStyle.getInnerColorBelowBaseline();
        this.r = pointStyle.a();
        this.p = selectedPointStyle.getRadius();
        this.q = selectedPointStyle.getInnerRadius();
        this.x = selectedPointStyle.getColor();
        this.y = selectedPointStyle.getInnerColor();
        this.z = selectedPointStyle.getColorBelowBaseline();
        this.A = selectedPointStyle.getInnerColorBelowBaseline();
        this.s = selectedPointStyle.a();
        if (this.I != FillStyle.NONE && f(this.B)) {
            this.D = areaLineColor;
            this.G = areaLineColorBelowBaseline;
            this.H = areaLineWidth;
        }
        if (this.I != FillStyle.NONE && f(this.E)) {
            this.G = areaLineColorBelowBaseline;
        }
        this.H = (float) (((double) this.H) * d2);
        this.n = (float) (((double) this.n) * d2);
        this.o = (float) (((double) this.o) * d2);
        this.p = (float) (((double) this.p) * d2);
        this.q = (float) (((double) this.q) * d2);
    }

    private void a(InternalDataPoint internalDataPoint, int i2) {
        int i3 = i2 / 2;
        double d2 = internalDataPoint.f * ((double) this.L);
        double d3 = internalDataPoint.g * ((double) this.L);
        if (internalDataPoint.e != -1) {
            this.M[i3] = internalDataPoint.e;
        }
        float[] fArr = this.N;
        if (fArr.length > 0) {
            if (d2 > 0.0d) {
                fArr[i3] = (float) d2;
            } else if (internalDataPoint.h) {
                this.N[i3] = this.p;
            } else {
                this.N[i3] = this.n;
            }
        }
        float[] fArr2 = this.O;
        if (fArr2.length <= 0) {
            return;
        }
        if (d3 > 0.0d && d3 < d2) {
            fArr2[i3] = (float) d3;
        } else if (internalDataPoint.h) {
            this.O[i3] = this.q;
        } else {
            this.O[i3] = this.o;
        }
    }

    private boolean e() {
        return ((LineSeriesStyle) (((LineSeries) this.k).d ? ((LineSeries) this.k).r : ((LineSeries) this.k).q)).getPointStyle().arePointsShown() && this.i > 0;
    }

    private boolean f() {
        return ((LineSeriesStyle) (((LineSeries) this.k).d ? ((LineSeries) this.k).r : ((LineSeries) this.k).q)).getSelectedPointStyle().arePointsShown() && this.h > 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        return e() || f();
    }
}
