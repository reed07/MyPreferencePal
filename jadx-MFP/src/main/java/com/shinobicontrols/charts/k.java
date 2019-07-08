package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class k extends t<BandSeries> {
    private static float[] I = new float[0];
    private static int[] J = new int[0];
    private int A;
    private float B;
    private float C;
    private float D;
    private float E;
    private float F;
    private boolean G;
    private boolean H;
    float[] a;
    float[] b;
    float[] c;
    float[] d;
    float[] e;
    float[] f;
    int g;
    int h;
    int i;
    private float[] n;
    private float[] o;
    private float[] p;
    private int[] q;
    private int[] r;
    private int[] s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public k(BandSeries bandSeries) {
        super(bandSeries);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        float[] fArr = this.a;
        if (fArr == null || fArr.length != i2 * 2) {
            this.a = new float[(i2 * 2)];
        }
        float[] fArr2 = this.d;
        if (fArr2 == null || fArr2.length != i2 * 2) {
            this.d = new float[(i2 * 2)];
        }
        float[] fArr3 = this.n;
        if (fArr3 == null || fArr3.length != i2) {
            this.n = new float[i2];
        }
        int[] iArr = this.q;
        if (iArr == null || iArr.length != i2) {
            this.q = new int[i2];
        }
        float[] fArr4 = this.b;
        if (fArr4 == null || fArr4.length != i2 * 2) {
            this.b = new float[(i2 * 2)];
        }
        float[] fArr5 = this.e;
        if (fArr5 == null || fArr5.length != i2 * 2) {
            this.e = new float[(i2 * 2)];
        }
        float[] fArr6 = this.o;
        if (fArr6 == null || fArr6.length != i2) {
            this.o = new float[i2];
        }
        int[] iArr2 = this.r;
        if (iArr2 == null || iArr2.length != i2) {
            this.r = new int[i2];
        }
        float[] fArr7 = this.c;
        if (fArr7 == null || fArr7.length != i2 * 2) {
            this.c = new float[(i2 * 2)];
        }
        float[] fArr8 = this.f;
        if (fArr8 == null || fArr8.length != i2 * 2) {
            this.f = new float[(i2 * 2)];
        }
        float[] fArr9 = this.p;
        if (fArr9 == null || fArr9.length != i2) {
            this.p = new float[i2];
        }
        int[] iArr3 = this.s;
        if (iArr3 == null || iArr3.length != i2) {
            this.s = new int[i2];
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(SChartGLDrawer sChartGLDrawer, float[] fArr, float f2) {
        if (((BandSeries) this.k).s()) {
            a((BandSeries) this.k, (double) f2);
            sChartGLDrawer.drawBandSeriesFill(this.a, this.k, this.d, this.l.c.length * 2, this.z, this.A, ((BandSeries) this.k).j.a(), this.i, fArr);
            float[] fArr2 = this.d;
            Series series = this.k;
            int length = this.l.c.length * 2;
            int i2 = this.u;
            sChartGLDrawer.drawLineStrip(fArr2, series, length, i2, i2, this.B, this.m, ((BandSeries) this.k).j.a(), fArr);
            float[] fArr3 = this.a;
            Series series2 = this.k;
            int length2 = this.l.c.length * 2;
            int i3 = this.t;
            sChartGLDrawer.drawLineStrip(fArr3, series2, length2, i3, i3, this.B, this.m, ((BandSeries) this.k).j.a(), fArr);
            int i4 = 0;
            if (this.g > 0) {
                if (sChartGLDrawer.getPerformCalculations()) {
                    if (d()) {
                        int i5 = 0;
                        for (InternalDataPoint internalDataPoint : this.l.c) {
                            if (!internalDataPoint.h) {
                                a(i5, internalDataPoint, this.r);
                                a(i5, internalDataPoint, this.o);
                                i5++;
                            }
                        }
                        float[] fArr4 = this.e;
                        Series series3 = this.k;
                        int[] iArr = this.r;
                        int i6 = this.h * 2;
                        int i7 = this.w;
                        sChartGLDrawer.drawDataPoints(fArr4, series3, iArr, i6, i7, i7, this.C, this.o, this.D, this.m, ((BandSeries) this.k).j.a(), fArr, false);
                        float[] fArr5 = this.b;
                        Series series4 = this.k;
                        int[] iArr2 = this.r;
                        int i8 = this.h * 2;
                        int i9 = this.v;
                        sChartGLDrawer.drawDataPoints(fArr5, series4, iArr2, i8, i9, i9, this.C, this.o, this.D, this.m, ((BandSeries) this.k).j.a(), fArr, !e());
                    }
                    if (e()) {
                        int i10 = 0;
                        while (i4 < this.l.c.length) {
                            InternalDataPoint internalDataPoint2 = this.l.c[i4];
                            if (internalDataPoint2.h) {
                                a(i10, internalDataPoint2, this.s);
                                a(i10, internalDataPoint2, this.p);
                                i10++;
                            }
                            i4++;
                        }
                        float[] fArr6 = this.f;
                        Series series5 = this.k;
                        int[] iArr3 = this.s;
                        int i11 = this.g * 2;
                        int i12 = this.y;
                        sChartGLDrawer.drawDataPoints(fArr6, series5, iArr3, i11, i12, i12, this.E, this.p, this.F, this.m, ((BandSeries) this.k).j.a(), fArr, false);
                        float[] fArr7 = this.c;
                        Series series6 = this.k;
                        int[] iArr4 = this.s;
                        int i13 = this.g * 2;
                        int i14 = this.x;
                        sChartGLDrawer.drawDataPoints(fArr7, series6, iArr4, i13, i14, i14, this.E, this.p, this.F, this.m, ((BandSeries) this.k).j.a(), fArr, true);
                    }
                } else if (d() || e()) {
                    float[] fArr8 = I;
                    int[] iArr5 = J;
                    int i15 = this.w;
                    sChartGLDrawer.drawDataPoints(fArr8, null, iArr5, 0, i15, i15, BitmapDescriptorFactory.HUE_RED, fArr8, this.D, BitmapDescriptorFactory.HUE_RED, ((BandSeries) this.k).j.a(), fArr, false);
                }
            } else if (d()) {
                if (sChartGLDrawer.getPerformCalculations()) {
                    int i16 = 0;
                    while (i4 < this.l.c.length) {
                        InternalDataPoint internalDataPoint3 = this.l.c[i4];
                        a(i16, internalDataPoint3, this.q);
                        a(i16, internalDataPoint3, this.n);
                        i16++;
                        i4++;
                    }
                }
                float[] fArr9 = this.d;
                Series series7 = this.k;
                int[] iArr6 = this.q;
                int length3 = this.l.c.length * 2;
                int i17 = this.w;
                sChartGLDrawer.drawDataPoints(fArr9, series7, iArr6, length3, i17, i17, this.C, this.n, this.D, this.m, ((BandSeries) this.k).j.a(), fArr, true);
                float[] fArr10 = this.a;
                Series series8 = this.k;
                int[] iArr7 = this.q;
                int length4 = this.l.c.length * 2;
                int i18 = this.v;
                sChartGLDrawer.drawDataPoints(fArr10, series8, iArr7, length4, i18, i18, this.C, this.n, this.D, this.m, ((BandSeries) this.k).j.a(), fArr, true);
            }
        }
    }

    private void a(int i2, InternalDataPoint internalDataPoint, float[] fArr) {
        if (fArr == null) {
            return;
        }
        if (internalDataPoint.f > 0.0d) {
            fArr[i2] = (float) internalDataPoint.f;
        } else {
            fArr[i2] = internalDataPoint.h ? this.E : this.C;
        }
    }

    private void a(int i2, InternalDataPoint internalDataPoint, int[] iArr) {
        if (internalDataPoint.e != -1) {
            iArr[i2] = internalDataPoint.e;
        }
    }

    private boolean d() {
        return this.G && this.h > 0;
    }

    private boolean e() {
        return this.H && this.g > 0;
    }

    private void a(BandSeries bandSeries, double d2) {
        BandSeriesStyle bandSeriesStyle = (BandSeriesStyle) (bandSeries.d ? bandSeries.r : bandSeries.q);
        this.l = bandSeries.n;
        this.G = bandSeriesStyle.getPointStyle().arePointsShown();
        this.H = bandSeriesStyle.getSelectedPointStyle().arePointsShown();
        this.t = bandSeriesStyle.getLineColorHigh();
        this.u = bandSeriesStyle.getLineColorLow();
        int i2 = 0;
        this.z = bandSeriesStyle.isFillShown() ? bandSeriesStyle.getAreaColorNormal() : 0;
        this.A = bandSeriesStyle.isFillShown() ? bandSeriesStyle.getAreaColorInverted() : 0;
        float f2 = (float) d2;
        this.B = bandSeriesStyle.getLineWidth() * f2;
        this.v = bandSeriesStyle.getPointStyle().arePointsShown() ? bandSeriesStyle.getPointStyle().getColor() : 0;
        this.w = bandSeriesStyle.getPointStyle().arePointsShown() ? bandSeriesStyle.getPointStyle().getColorBelowBaseline() : 0;
        this.C = bandSeriesStyle.getPointStyle().getRadius() * f2;
        this.D = bandSeriesStyle.getPointStyle().a();
        this.x = bandSeriesStyle.getSelectedPointStyle().arePointsShown() ? bandSeriesStyle.getSelectedPointStyle().getColor() : 0;
        if (bandSeriesStyle.getSelectedPointStyle().arePointsShown()) {
            i2 = bandSeriesStyle.getSelectedPointStyle().getColorBelowBaseline();
        }
        this.y = i2;
        this.E = bandSeriesStyle.getSelectedPointStyle().getRadius() * f2;
        this.F = bandSeriesStyle.getSelectedPointStyle().a();
    }
}
