package com.shinobicontrols.charts;

class bs extends t<OHLCSeries> {
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
    private float s;

    public bs(OHLCSeries oHLCSeries) {
        super(oHLCSeries);
        this.d = oHLCSeries.j.a();
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
        if (((OHLCSeries) this.k).s()) {
            a((OHLCSeries) this.k, (double) f2);
            for (int i2 = 0; i2 < this.l.c.length; i2++) {
                InternalDataPoint internalDataPoint = this.l.c[i2];
                if (internalDataPoint.a()) {
                    if (internalDataPoint.h) {
                        this.b[i2] = this.q;
                        this.c[i2] = this.r;
                    } else {
                        this.b[i2] = this.i;
                        this.c[i2] = this.n;
                    }
                } else if (internalDataPoint.h) {
                    this.b[i2] = this.o;
                    this.c[i2] = this.p;
                } else {
                    this.b[i2] = this.g;
                    this.c[i2] = this.h;
                }
            }
            sChartGLDrawer.drawOHLCPoints(this.a, this.k, this.l.c.length, this.b, this.c, this.d, this.s, this.f, this.e, fArr);
        }
    }

    private void a(OHLCSeries oHLCSeries, double d2) {
        OHLCSeriesStyle oHLCSeriesStyle = (OHLCSeriesStyle) (oHLCSeries.d ? oHLCSeries.r : oHLCSeries.q);
        this.s = oHLCSeries.a();
        this.l = oHLCSeries.n;
        this.e = (float) (((double) oHLCSeriesStyle.getArmWidth()) * d2);
        this.f = (float) (((double) oHLCSeriesStyle.getTrunkWidth()) * d2);
        this.g = ((OHLCSeriesStyle) oHLCSeries.q).getFallingColor();
        this.h = ((OHLCSeriesStyle) oHLCSeries.q).getFallingColorGradient();
        this.i = ((OHLCSeriesStyle) oHLCSeries.q).getRisingColor();
        this.n = ((OHLCSeriesStyle) oHLCSeries.q).getRisingColorGradient();
        this.o = ((OHLCSeriesStyle) oHLCSeries.r).getFallingColor();
        this.p = ((OHLCSeriesStyle) oHLCSeries.r).getFallingColorGradient();
        this.q = ((OHLCSeriesStyle) oHLCSeries.r).getRisingColor();
        this.r = ((OHLCSeriesStyle) oHLCSeries.r).getRisingColorGradient();
    }
}
