package com.shinobicontrols.charts;

import com.shinobicontrols.charts.PieDonutSeries.DrawDirection;
import com.shinobicontrols.charts.PieDonutSeries.RadialEffect;

class bt extends n<PieDonutSeries<?>> {
    private final a a = new a();
    private final a b = new a();
    private float c;
    private float d;
    private DrawDirection e;
    private float f;

    private static class a {
        boolean a;
        boolean b;
        Integer[] c;
        Integer[] d;
        RadialEffect e;
        float f;

        private a() {
        }

        /* access modifiers changed from: 0000 */
        public int a(int i) {
            Integer[] numArr = this.c;
            return numArr[i % numArr.length].intValue();
        }

        /* access modifiers changed from: 0000 */
        public int b(int i) {
            Integer[] numArr = this.d;
            return numArr[i % numArr.length].intValue();
        }
    }

    bt(PieDonutSeries<?> pieDonutSeries) {
        super(pieDonutSeries);
    }

    public void a(ai aiVar, SChartGLDrawer sChartGLDrawer) {
        double d2;
        double d3;
        a((PieDonutSeries) this.k);
        float f2 = aiVar.c().density;
        float max = 1.0f / Math.max(aiVar.a(), aiVar.b());
        int length = this.l.c.length;
        int i = 0;
        while (i < length) {
            PieDonutSlice pieDonutSlice = (PieDonutSlice) this.l.c[i];
            a aVar = pieDonutSlice.h ? this.b : this.a;
            if (this.e == DrawDirection.ANTICLOCKWISE) {
                d3 = (double) (this.f + pieDonutSlice.n);
                d2 = (double) (this.f + pieDonutSlice.o);
            } else {
                d3 = (double) (this.f - pieDonutSlice.n);
                d2 = (double) (this.f - pieDonutSlice.o);
            }
            float f3 = pieDonutSlice.q;
            int a2 = aVar.a ? aVar.a(i) : 0;
            int b2 = aVar.b ? aVar.b(i) : 0;
            RadialEffect radialEffect = aVar.a ? aVar.e : RadialEffect.FLAT;
            float f4 = aVar.f * f2;
            int i2 = i;
            sChartGLDrawer.drawRadialSlice(i, this.k, (float) d3, (float) d2, this.d, this.c, f3, a2, b2, aVar.b, f4, radialEffect.getXmlValue(), max);
            i = i2 + 1;
        }
    }

    private void a(PieDonutSeries<?> pieDonutSeries) {
        PieDonutSeriesStyle pieDonutSeriesStyle = (PieDonutSeriesStyle) pieDonutSeries.q;
        PieDonutSeriesStyle pieDonutSeriesStyle2 = (PieDonutSeriesStyle) pieDonutSeries.r;
        this.c = pieDonutSeries.getOuterRadius();
        this.d = pieDonutSeries.getInnerRadius();
        this.e = pieDonutSeries.getDrawDirection();
        this.f = pieDonutSeries.getRotation();
        this.l = pieDonutSeries.n;
        this.a.a = pieDonutSeriesStyle.isFlavorShown();
        this.a.b = pieDonutSeriesStyle.isCrustShown();
        this.a.c = pieDonutSeriesStyle.b();
        this.a.d = pieDonutSeriesStyle.a();
        this.a.e = pieDonutSeriesStyle.getRadialEffect();
        this.a.f = pieDonutSeriesStyle.getCrustThickness();
        this.b.a = pieDonutSeriesStyle2.isFlavorShown();
        this.b.b = pieDonutSeriesStyle2.isCrustShown();
        this.b.c = pieDonutSeriesStyle2.b();
        this.b.d = pieDonutSeriesStyle2.a();
        this.b.e = pieDonutSeriesStyle2.getRadialEffect();
        this.b.f = pieDonutSeriesStyle2.getCrustThickness();
    }
}
