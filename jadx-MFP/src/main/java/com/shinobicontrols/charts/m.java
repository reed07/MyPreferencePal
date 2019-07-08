package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;
import java.util.Arrays;

class m extends t<BarColumnSeries<?>> {
    float[] a;
    float[] b;
    int c = 0;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private float n;
    private boolean o;
    private FillStyle p;
    private float q;
    private final int r;

    m(BarColumnSeries<?> barColumnSeries) {
        super(barColumnSeries);
        this.r = barColumnSeries.j.a();
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        float[] fArr = this.a;
        if (fArr == null || fArr.length != i2) {
            this.a = new float[i2];
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2) {
        float[] fArr = this.b;
        if (fArr == null || fArr.length != i2) {
            this.b = new float[i2];
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(SChartGLDrawer sChartGLDrawer, float[] fArr, float f2) {
        if (((BarColumnSeries) this.k).s()) {
            a((BarColumnSeries) this.k, (double) f2);
            boolean z = !(this.d == 0 && this.g == 0) && this.o;
            boolean z2 = this.p != FillStyle.NONE;
            if (this.p != FillStyle.GRADIENT) {
                this.f = this.e;
                this.i = this.h;
            }
            if (z && !z2) {
                this.e = 0;
                int i2 = this.e;
                this.h = i2;
                this.f = i2;
                this.i = i2;
                z2 = true;
            }
            if (z2) {
                sChartGLDrawer.drawBarColumnFill(this.a, this.k, this.b, this.c, this.e, this.h, this.f, this.i, this.q, this.m, this.r, z, fArr);
            }
            if (z) {
                float[] fArr2 = this.a;
                Series series = this.k;
                float[] fArr3 = this.b;
                int i3 = this.c;
                int i4 = this.d;
                sChartGLDrawer.drawBarColumnLine(fArr2, series, fArr3, i3, i4, this.g, i4 != 0, this.g != 0, this.q, this.n, this.m, this.r, fArr);
            }
            float[] fArr4 = this.b;
            if (fArr4 != null) {
                Arrays.fill(fArr4, BitmapDescriptorFactory.HUE_RED);
            }
        }
    }

    private void a(BarColumnSeries<?> barColumnSeries, double d2) {
        BarColumnSeriesStyle barColumnSeriesStyle = (BarColumnSeriesStyle) (barColumnSeries.d ? barColumnSeries.r : barColumnSeries.q);
        this.q = barColumnSeries.a();
        this.l = barColumnSeries.n;
        this.d = barColumnSeriesStyle.getLineColor();
        this.n = barColumnSeriesStyle.getLineWidth();
        this.e = barColumnSeriesStyle.getAreaColor();
        this.f = barColumnSeriesStyle.getAreaColorGradient();
        this.g = barColumnSeriesStyle.getLineColorBelowBaseline();
        this.h = barColumnSeriesStyle.getAreaColorBelowBaseline();
        this.i = barColumnSeriesStyle.getAreaColorGradientBelowBaseline();
        this.o = barColumnSeriesStyle.isLineShown();
        this.p = barColumnSeriesStyle.getFillStyle();
        this.n = (float) (((double) this.n) * d2);
    }
}
