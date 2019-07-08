package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class p extends t<CandlestickSeries> {
    float[] a;
    private int[] b;
    private int[] c;
    private final int d;
    private float e;
    private float f;
    private int g;
    private int h;
    private int i;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private boolean u;
    private boolean v;
    private float w;

    public p(CandlestickSeries candlestickSeries) {
        super(candlestickSeries);
        this.d = candlestickSeries.j.a();
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        float[] fArr = this.a;
        if (fArr == null || fArr.length != i2 * 5) {
            this.a = new float[(i2 * 5)];
        }
        int[] iArr = this.b;
        if (iArr == null || iArr.length != i2) {
            this.b = new int[i2];
        }
        int[] iArr2 = this.c;
        if (iArr2 == null || iArr2.length != i2) {
            this.c = new int[i2];
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(SChartGLDrawer sChartGLDrawer, float[] fArr, float f2) {
        if (((CandlestickSeries) this.k).s()) {
            a((CandlestickSeries) this.k, (double) f2);
            for (int i2 = 0; i2 < this.l.c.length; i2++) {
                InternalDataPoint internalDataPoint = this.l.c[i2];
                if (internalDataPoint.a()) {
                    if (internalDataPoint.h) {
                        this.b[i2] = this.s;
                        this.c[i2] = this.t;
                    } else {
                        this.b[i2] = this.o;
                        this.c[i2] = this.p;
                    }
                } else if (internalDataPoint.h) {
                    this.b[i2] = this.q;
                    this.c[i2] = this.r;
                } else {
                    this.b[i2] = this.i;
                    this.c[i2] = this.n;
                }
            }
            sChartGLDrawer.drawCandlesticks(this.a, this.k, this.l.c.length, this.b, this.c, this.g, this.h, this.u, this.v, this.w, this.e, this.f, this.d, f2, fArr);
        }
    }

    private void a(CandlestickSeries candlestickSeries, double d2) {
        CandlestickSeriesStyle candlestickSeriesStyle = (CandlestickSeriesStyle) (candlestickSeries.d ? candlestickSeries.r : candlestickSeries.q);
        this.w = candlestickSeries.a();
        this.l = candlestickSeries.n;
        this.e = (float) (((double) candlestickSeriesStyle.getOutlineWidth()) * d2);
        this.f = (float) (((double) candlestickSeriesStyle.getStickWidth()) * d2);
        this.g = candlestickSeriesStyle.getOutlineColor();
        this.h = candlestickSeriesStyle.getStickColor();
        this.i = ((CandlestickSeriesStyle) candlestickSeries.q).getFallingColor();
        this.n = ((CandlestickSeriesStyle) candlestickSeries.q).getFallingColorGradient();
        this.o = ((CandlestickSeriesStyle) candlestickSeries.q).getRisingColor();
        this.p = ((CandlestickSeriesStyle) candlestickSeries.q).getRisingColorGradient();
        this.q = ((CandlestickSeriesStyle) candlestickSeries.r).getFallingColor();
        this.r = ((CandlestickSeriesStyle) candlestickSeries.r).getFallingColorGradient();
        this.s = ((CandlestickSeriesStyle) candlestickSeries.r).getRisingColor();
        this.t = ((CandlestickSeriesStyle) candlestickSeries.r).getRisingColorGradient();
        boolean z = true;
        this.u = this.g != 0 && this.e > BitmapDescriptorFactory.HUE_RED;
        if (this.h == 0 || this.f <= BitmapDescriptorFactory.HUE_RED) {
            z = false;
        }
        this.v = z;
    }
}
