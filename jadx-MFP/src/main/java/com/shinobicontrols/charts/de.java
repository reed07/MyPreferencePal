package com.shinobicontrols.charts;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.Legend.SymbolAlignment;
import com.shinobicontrols.charts.PieDonutSeries.RadialEffect;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;
import com.shinobicontrols.charts.TickMark.Orientation;
import com.shinobicontrols.charts.Title.CentersOn;
import com.shinobicontrols.charts.Title.Position;

class de {
    private static int A = 0;
    private static int B = 0;
    private static int C = 0;
    private static int D = 0;
    private static int E = 0;
    private static int F = 0;
    private static int G = 0;
    private static int H = 0;
    private static int I = 0;
    private static int J = 0;
    private static int K = 0;
    private static int L = 0;
    private static int M = 0;
    private static int N = 0;
    private static int O = 0;
    private static int P = 0;
    private static int Q = 0;
    private static int R = 0;
    private static int S = 0;
    private static int T = 0;
    private static int U = 0;
    private static int V = 0;
    private static int W = 0;
    private static int X = 0;
    private static int Y = 0;
    private static int Z = 0;
    private static int a = 0;
    private static int aa = 0;
    private static int ab = 0;
    private static int ac = 0;
    private static int ad = 0;
    private static int ae = 0;
    private static int af = 0;
    private static int ag = -16777216;
    private static float ah;
    private static float ai;
    private static float aj;
    private static float ak;
    private static int al;
    private static int am;
    private static int an;
    private static int ao;
    private static int ap;
    private static int aq;
    private static float ar;
    private static float as;
    private static float at;
    private static float au;
    private static float av;
    private static float aw;
    private static final Typeface ax = Typeface.create(null, 0);
    private static float ay;
    private static float az;
    private static int b;
    private static float c;
    private static int d;
    private static int e;
    private static int f;
    private static boolean g;
    private static float[] h;
    private static boolean i;
    private static int j;
    private static int k;
    private static boolean l;
    private static int m;
    private static int n;
    private static int o;
    private static int p;
    private static int q;
    private static int r;
    private static int s;
    private static int t;
    private static int u;
    private static int v;
    private static int w;
    private static int x;
    private static int y;
    private static int z;

    static dd a(Context context, AttributeSet attributeSet) {
        a(context, attributeSet, 0);
        return a(a());
    }

    static dd a(Context context, int i2) {
        a(context, (AttributeSet) null, i2);
        return a(a());
    }

    static dd a(Context context, dd ddVar, int i2) {
        a(context, (AttributeSet) null, i2);
        return a(ddVar);
    }

    private static dd a(dd ddVar) {
        ChartStyle a2 = ddVar.a();
        a2.a.b(Integer.valueOf(p));
        a2.b.b(Integer.valueOf(p));
        a2.c.b(Integer.valueOf(0));
        a2.d.b(Float.valueOf(1.0f));
        a2.e.b(Integer.valueOf(0));
        a2.f.b(Integer.valueOf(q));
        a2.g.b(Integer.valueOf(r));
        a2.h.b(Float.valueOf(1.6f));
        MainTitleStyle b2 = ddVar.b();
        b2.d.b(ax);
        b2.h.b(Integer.valueOf(a));
        b2.c.b(Integer.valueOf(0));
        b2.e.b(Float.valueOf(16.0f));
        b2.f.b(Float.valueOf(16.0f));
        b2.a.b(Boolean.valueOf(false));
        b2.b.b(CentersOn.PLOTTING_AREA);
        b2.i.b(Float.valueOf(ay));
        b2.j.b(Float.valueOf(az));
        LegendStyle d2 = ddVar.d();
        d2.a.b(Integer.valueOf(u));
        d2.b.b(Integer.valueOf(t));
        d2.c.b(Float.valueOf(1.0f));
        d2.d.b(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        d2.e.b(ax);
        d2.f.b(Integer.valueOf(s));
        d2.g.b(Float.valueOf(16.0f));
        d2.i.b(Float.valueOf(20.0f));
        d2.r.b(Float.valueOf(15.0f));
        d2.k.b(SymbolAlignment.LEFT);
        d2.l.b(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        d2.h.b(Float.valueOf(15.0f));
        d2.j.b(Boolean.valueOf(true));
        d2.m.b(Float.valueOf(32.0f));
        d2.n.b(Integer.valueOf(17));
        d2.o.b(ax);
        d2.p.b(Integer.valueOf(s));
        d2.q.b(Float.valueOf(16.0f));
        d2.s.j.b(Float.valueOf(az));
        d2.s.i.b(Float.valueOf(ay));
        for (int i2 = 0; i2 < ddVar.a.size(); i2++) {
            LineSeriesStyle f2 = ddVar.f(i2, false);
            a(f2, a(f2.getPointStyle(), a(i2)), b(f2.getSelectedPointStyle(), a(i2)), a(i2), b(i2), c(i2), d(i2), ah);
        }
        for (int i3 = 0; i3 < ddVar.b.size(); i3++) {
            LineSeriesStyle f3 = ddVar.f(i3, true);
            a(f3, a(f3.getPointStyle(), a(i3)), f3.getSelectedPointStyle(), n, b(i3), c(i3), d(i3), 4.0f * ah);
        }
        for (int i4 = 0; i4 < ddVar.c.size(); i4++) {
            a(ddVar.d(i4, false), a(i4), b(i4), d(i4));
        }
        for (int i5 = 0; i5 < ddVar.d.size(); i5++) {
            b(ddVar.d(i5, true), a(i5), b(i5), d(i5));
        }
        for (int i6 = 0; i6 < ddVar.e.size(); i6++) {
            a(ddVar.e(i6, false), a(i6), b(i6), d(i6));
        }
        for (int i7 = 0; i7 < ddVar.f.size(); i7++) {
            b(ddVar.e(i7, true), a(i7), b(i7), d(i7));
        }
        a(ddVar.g(0, false));
        b(ddVar.g(0, true));
        a(ddVar.h(0, false));
        b(ddVar.h(0, true));
        AxisStyle e2 = ddVar.e();
        AxisTitleStyle titleStyle = e2.getTitleStyle();
        a(titleStyle);
        TickStyle tickStyle = e2.getTickStyle();
        a(tickStyle);
        a(e2.getGridlineStyle());
        a(e2.getGridStripeStyle());
        a(e2, titleStyle, tickStyle);
        AxisStyle f4 = ddVar.f();
        AxisTitleStyle titleStyle2 = f4.getTitleStyle();
        b(titleStyle2);
        TickStyle tickStyle2 = f4.getTickStyle();
        a(tickStyle2);
        a(f4.getGridlineStyle());
        a(f4.getGridStripeStyle());
        b(f4, titleStyle2, tickStyle2);
        BandSeriesStyle a3 = ddVar.a(0, false);
        a(a3, a3.getPointStyle(), a3.getSelectedPointStyle());
        BandSeriesStyle a4 = ddVar.a(0, true);
        a(a4, a4.getPointStyle(), a4.getSelectedPointStyle());
        a(ddVar.b(0, false));
        a(ddVar.b(0, true));
        a(ddVar.c(0, false));
        a(ddVar.c(0, true));
        a(ddVar.c());
        a(ddVar.g());
        return ddVar;
    }

    private static dd a() {
        dd ddVar = new dd();
        ddVar.a(new ChartStyle());
        ddVar.a(new MainTitleStyle());
        ddVar.a(new LegendStyle());
        for (int i2 = 0; i2 < 6; i2++) {
            LineSeriesStyle lineSeriesStyle = new LineSeriesStyle();
            lineSeriesStyle.setPointStyle(new PointStyle());
            lineSeriesStyle.setSelectedPointStyle(new PointStyle());
            ddVar.a(lineSeriesStyle, false);
            LineSeriesStyle lineSeriesStyle2 = new LineSeriesStyle();
            lineSeriesStyle2.setPointStyle(new PointStyle());
            lineSeriesStyle2.setSelectedPointStyle(new PointStyle());
            ddVar.a(lineSeriesStyle2, true);
            ddVar.a(new ColumnSeriesStyle(), false);
            ddVar.a(new ColumnSeriesStyle(), true);
            ddVar.a(new BarSeriesStyle(), false);
            ddVar.a(new BarSeriesStyle(), true);
        }
        ddVar.a(new PieSeriesStyle(), false);
        ddVar.a(new PieSeriesStyle(), true);
        ddVar.a(new DonutSeriesStyle(), false);
        ddVar.a(new DonutSeriesStyle(), true);
        ddVar.a(new AnnotationStyle());
        AxisStyle axisStyle = new AxisStyle();
        axisStyle.setTitleStyle(new AxisTitleStyle());
        axisStyle.setTickStyle(new TickStyle());
        axisStyle.setGridlineStyle(new GridlineStyle());
        axisStyle.setGridStripeStyle(new GridStripeStyle());
        ddVar.a(axisStyle);
        AxisStyle axisStyle2 = new AxisStyle();
        axisStyle2.setTitleStyle(new AxisTitleStyle());
        axisStyle2.setTickStyle(new TickStyle());
        axisStyle2.setGridlineStyle(new GridlineStyle());
        axisStyle2.setGridStripeStyle(new GridStripeStyle());
        ddVar.b(axisStyle2);
        BandSeriesStyle bandSeriesStyle = new BandSeriesStyle();
        bandSeriesStyle.setPointStyle(new PointStyle());
        bandSeriesStyle.setSelectedPointStyle(new PointStyle());
        ddVar.a(bandSeriesStyle, false);
        ddVar.a(new BandSeriesStyle(), true);
        ddVar.a(new CandlestickSeriesStyle(), false);
        ddVar.a(new CandlestickSeriesStyle(), true);
        ddVar.a(new OHLCSeriesStyle(), false);
        ddVar.a(new OHLCSeriesStyle(), true);
        ddVar.a(new CrosshairStyle());
        return ddVar;
    }

    private static void a(OHLCSeriesStyle oHLCSeriesStyle) {
        oHLCSeriesStyle.a.b(Integer.valueOf(al));
        oHLCSeriesStyle.c.b(Integer.valueOf(am));
        oHLCSeriesStyle.b.b(Integer.valueOf(al));
        oHLCSeriesStyle.d.b(Integer.valueOf(am));
        oHLCSeriesStyle.f.b(Float.valueOf(2.0f));
        oHLCSeriesStyle.e.b(Float.valueOf(2.0f));
    }

    private static void a(CandlestickSeriesStyle candlestickSeriesStyle) {
        candlestickSeriesStyle.a.b(Integer.valueOf(al));
        candlestickSeriesStyle.c.b(Integer.valueOf(am));
        candlestickSeriesStyle.b.b(Integer.valueOf(al));
        candlestickSeriesStyle.d.b(Integer.valueOf(am));
        candlestickSeriesStyle.g.b(Float.valueOf(2.0f));
        candlestickSeriesStyle.h.b(Float.valueOf(2.0f));
    }

    private static void a(BandSeriesStyle bandSeriesStyle, PointStyle pointStyle, PointStyle pointStyle2) {
        bandSeriesStyle.a.b(Boolean.valueOf(true));
        bandSeriesStyle.b.b(Integer.valueOf(an));
        bandSeriesStyle.c.b(Integer.valueOf(ao));
        bandSeriesStyle.d.b(Float.valueOf(2.0f));
        bandSeriesStyle.e.b(Integer.valueOf(ap));
        bandSeriesStyle.f.b(Integer.valueOf(aq));
        a(pointStyle, D);
        bandSeriesStyle.setPointStyle(pointStyle);
        b(pointStyle2, D);
        bandSeriesStyle.setSelectedPointStyle(pointStyle2);
    }

    private static void a(LineSeriesStyle lineSeriesStyle, PointStyle pointStyle, PointStyle pointStyle2, int i2, int i3, int i4, int i5, float f2) {
        lineSeriesStyle.k.b(Boolean.valueOf(true));
        lineSeriesStyle.j.b(Float.valueOf(f2));
        lineSeriesStyle.h.b(Integer.valueOf(i2));
        a(pointStyle, i3);
        lineSeriesStyle.setPointStyle(pointStyle);
        b(pointStyle2, i3);
        lineSeriesStyle.setSelectedPointStyle(pointStyle2);
        lineSeriesStyle.a.b(Integer.valueOf(i3));
        lineSeriesStyle.b.b(Integer.valueOf(i3));
        lineSeriesStyle.d.b(Integer.valueOf(i4));
        lineSeriesStyle.c.b(Integer.valueOf(i4));
        lineSeriesStyle.e.b(Integer.valueOf(i5));
        lineSeriesStyle.f.b(Integer.valueOf(i5));
        lineSeriesStyle.g.b(Float.valueOf(f2));
        lineSeriesStyle.l.b(FillStyle.NONE);
        lineSeriesStyle.i.b(Integer.valueOf(i2));
        lineSeriesStyle.n.b(Boolean.valueOf(false));
    }

    private static PointStyle a(PointStyle pointStyle, int i2) {
        pointStyle.a.b(Integer.valueOf(i2));
        pointStyle.b.b(Integer.valueOf(i2));
        pointStyle.h.b(Boolean.valueOf(false));
        if (!pointStyle.c.b) {
            pointStyle.a((float) BitmapDescriptorFactory.HUE_RED);
            pointStyle.c.b = false;
        }
        pointStyle.d.b(Integer.valueOf(q));
        pointStyle.e.b(Integer.valueOf(q));
        pointStyle.f.b(Float.valueOf(3.0f));
        pointStyle.g.b(Float.valueOf(5.0f));
        pointStyle.i.b(null);
        return pointStyle;
    }

    private static PointStyle b(PointStyle pointStyle, int i2) {
        pointStyle.a.b(Integer.valueOf(n));
        pointStyle.b.b(Integer.valueOf(n));
        pointStyle.h.b(Boolean.valueOf(false));
        if (!pointStyle.c.b) {
            pointStyle.a((float) BitmapDescriptorFactory.HUE_RED);
            pointStyle.c.b = false;
        }
        pointStyle.d.b(Integer.valueOf(q));
        pointStyle.e.b(Integer.valueOf(q));
        pointStyle.f.b(Float.valueOf(3.0f));
        pointStyle.g.b(Float.valueOf(7.5f));
        pointStyle.i.b(null);
        return pointStyle;
    }

    private static ColumnSeriesStyle a(ColumnSeriesStyle columnSeriesStyle, int i2, int i3, int i4) {
        columnSeriesStyle.h.b(Boolean.valueOf(true));
        columnSeriesStyle.g.b(Float.valueOf(ai));
        columnSeriesStyle.e.b(Integer.valueOf(i2));
        columnSeriesStyle.f.b(Integer.valueOf(i2));
        columnSeriesStyle.i.b(FillStyle.GRADIENT);
        columnSeriesStyle.a.b(Integer.valueOf(i3));
        columnSeriesStyle.d.b(Integer.valueOf(i4));
        columnSeriesStyle.b.b(Integer.valueOf(i3));
        columnSeriesStyle.c.b(Integer.valueOf(i4));
        if (!columnSeriesStyle.n.b) {
            columnSeriesStyle.a(false);
            columnSeriesStyle.n.b = false;
        }
        return columnSeriesStyle;
    }

    private static ColumnSeriesStyle b(ColumnSeriesStyle columnSeriesStyle, int i2, int i3, int i4) {
        columnSeriesStyle.h.b(Boolean.valueOf(true));
        columnSeriesStyle.i.b(FillStyle.GRADIENT);
        columnSeriesStyle.a.b(Integer.valueOf(i3));
        columnSeriesStyle.d.b(Integer.valueOf(i4));
        columnSeriesStyle.b.b(Integer.valueOf(i3));
        columnSeriesStyle.c.b(Integer.valueOf(i4));
        if (!columnSeriesStyle.n.b) {
            columnSeriesStyle.a(false);
            columnSeriesStyle.n.b = false;
        }
        columnSeriesStyle.e.b(Integer.valueOf(n));
        columnSeriesStyle.g.b(Float.valueOf(columnSeriesStyle.getLineWidth() * 4.0f));
        columnSeriesStyle.f.b(Integer.valueOf(n));
        return columnSeriesStyle;
    }

    private static void a(BarSeriesStyle barSeriesStyle, int i2, int i3, int i4) {
        barSeriesStyle.h.b(Boolean.valueOf(true));
        barSeriesStyle.g.b(Float.valueOf(aj));
        barSeriesStyle.e.b(Integer.valueOf(i2));
        barSeriesStyle.f.b(Integer.valueOf(i2));
        barSeriesStyle.i.b(FillStyle.GRADIENT);
        barSeriesStyle.a.b(Integer.valueOf(i3));
        barSeriesStyle.d.b(Integer.valueOf(i4));
        barSeriesStyle.b.b(Integer.valueOf(i3));
        barSeriesStyle.c.b(Integer.valueOf(i4));
        if (!barSeriesStyle.n.b) {
            barSeriesStyle.a(false);
            barSeriesStyle.n.b = false;
        }
    }

    private static void b(BarSeriesStyle barSeriesStyle, int i2, int i3, int i4) {
        barSeriesStyle.e.b(Integer.valueOf(n));
        barSeriesStyle.g.b(Float.valueOf(barSeriesStyle.getLineWidth() * 4.0f));
        barSeriesStyle.f.b(Integer.valueOf(n));
        barSeriesStyle.h.b(Boolean.valueOf(true));
        barSeriesStyle.i.b(FillStyle.GRADIENT);
        barSeriesStyle.a.b(Integer.valueOf(i3));
        barSeriesStyle.d.b(Integer.valueOf(i4));
        barSeriesStyle.b.b(Integer.valueOf(i3));
        barSeriesStyle.c.b(Integer.valueOf(i4));
        if (!barSeriesStyle.n.b) {
            barSeriesStyle.a(false);
            barSeriesStyle.n.b = false;
        }
    }

    private static void a(PieSeriesStyle pieSeriesStyle) {
        if (!pieSeriesStyle.h.b) {
            pieSeriesStyle.setFlavorColors(new int[]{aa, ab, ac, ad, ae, af});
            pieSeriesStyle.h.b = false;
        }
        if (!pieSeriesStyle.f.b) {
            pieSeriesStyle.setCrustColors(new int[]{ag});
            pieSeriesStyle.f.b = false;
        }
        pieSeriesStyle.a.b(Boolean.valueOf(true));
        pieSeriesStyle.b.b(Boolean.valueOf(true));
        pieSeriesStyle.c.b(Boolean.valueOf(true));
        pieSeriesStyle.d.b(RadialEffect.DEFAULT);
        pieSeriesStyle.e.b(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        pieSeriesStyle.g.b(Float.valueOf(ak));
        pieSeriesStyle.i.b(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        pieSeriesStyle.j.b(ax);
        pieSeriesStyle.k.b(Float.valueOf(16.0f));
        pieSeriesStyle.l.b(Integer.valueOf(o));
        pieSeriesStyle.m.b(Integer.valueOf(0));
    }

    private static void b(PieSeriesStyle pieSeriesStyle) {
        if (!pieSeriesStyle.h.b) {
            pieSeriesStyle.setFlavorColors(new int[]{aa, ab, ac, ad, ae, af});
            pieSeriesStyle.h.b = false;
        }
        if (!pieSeriesStyle.f.b) {
            pieSeriesStyle.setCrustColors(new int[]{ag});
            pieSeriesStyle.f.b = false;
        }
        pieSeriesStyle.a.b(Boolean.valueOf(true));
        pieSeriesStyle.b.b(Boolean.valueOf(true));
        pieSeriesStyle.c.b(Boolean.valueOf(true));
        pieSeriesStyle.d.b(RadialEffect.DEFAULT);
        pieSeriesStyle.e.b(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        pieSeriesStyle.g.b(Float.valueOf(ak));
        pieSeriesStyle.i.b(Float.valueOf(0.1f));
        pieSeriesStyle.j.b(ax);
        pieSeriesStyle.k.b(Float.valueOf(16.0f));
        pieSeriesStyle.l.b(Integer.valueOf(o));
        pieSeriesStyle.m.b(Integer.valueOf(0));
    }

    private static void a(DonutSeriesStyle donutSeriesStyle) {
        if (!donutSeriesStyle.h.b) {
            donutSeriesStyle.setFlavorColors(new int[]{aa, ab, ac, ad, ae, af});
            donutSeriesStyle.h.b = false;
        }
        if (!donutSeriesStyle.f.b) {
            donutSeriesStyle.setCrustColors(new int[]{ag});
            donutSeriesStyle.f.b = false;
        }
        donutSeriesStyle.a.b(Boolean.valueOf(true));
        donutSeriesStyle.b.b(Boolean.valueOf(true));
        donutSeriesStyle.c.b(Boolean.valueOf(true));
        donutSeriesStyle.d.b(RadialEffect.DEFAULT);
        donutSeriesStyle.e.b(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        donutSeriesStyle.g.b(Float.valueOf(ak));
        donutSeriesStyle.i.b(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        donutSeriesStyle.j.b(ax);
        donutSeriesStyle.k.b(Float.valueOf(16.0f));
        donutSeriesStyle.l.b(Integer.valueOf(o));
        donutSeriesStyle.m.b(Integer.valueOf(0));
    }

    private static void b(DonutSeriesStyle donutSeriesStyle) {
        if (!donutSeriesStyle.h.b) {
            donutSeriesStyle.setFlavorColors(new int[]{aa, ab, ac, ad, ae, af});
            donutSeriesStyle.h.b = false;
        }
        if (!donutSeriesStyle.f.b) {
            donutSeriesStyle.setCrustColors(new int[]{ag});
            donutSeriesStyle.f.b = false;
        }
        donutSeriesStyle.a.b(Boolean.valueOf(true));
        donutSeriesStyle.b.b(Boolean.valueOf(true));
        donutSeriesStyle.c.b(Boolean.valueOf(true));
        donutSeriesStyle.d.b(RadialEffect.DEFAULT);
        donutSeriesStyle.e.b(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        donutSeriesStyle.g.b(Float.valueOf(ak));
        donutSeriesStyle.i.b(Float.valueOf(0.1f));
        donutSeriesStyle.j.b(ax);
        donutSeriesStyle.k.b(Float.valueOf(16.0f));
        donutSeriesStyle.l.b(Integer.valueOf(o));
        donutSeriesStyle.m.b(Integer.valueOf(0));
    }

    private static void a(AxisStyle axisStyle, AxisTitleStyle axisTitleStyle, TickStyle tickStyle) {
        a(axisTitleStyle);
        axisStyle.setTitleStyle(axisTitleStyle);
        a(tickStyle);
        axisStyle.setTickStyle(tickStyle);
        axisStyle.d.b(Float.valueOf(1.6f));
        axisStyle.c.b(Integer.valueOf(m));
        axisStyle.setInterSeriesPadding(0.2f);
        axisStyle.a.b = false;
        axisStyle.setInterSeriesSetPadding(0.2f);
        axisStyle.b.b = false;
    }

    private static void b(AxisStyle axisStyle, AxisTitleStyle axisTitleStyle, TickStyle tickStyle) {
        b(axisTitleStyle);
        axisStyle.setTitleStyle(axisTitleStyle);
        a(tickStyle);
        axisStyle.setTickStyle(tickStyle);
        axisStyle.d.b(Float.valueOf(1.6f));
        axisStyle.c.b(Integer.valueOf(m));
        axisStyle.a.b(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        axisStyle.setInterSeriesSetPadding(0.2f);
        axisStyle.b.b = false;
    }

    private static void a(TickStyle tickStyle) {
        tickStyle.h.b(Boolean.valueOf(true));
        tickStyle.i.b(Boolean.valueOf(true));
        tickStyle.j.b(Boolean.valueOf(false));
        tickStyle.l.b(Orientation.HORIZONTAL);
        tickStyle.k.b(Float.valueOf(5.0f));
        tickStyle.f.b(Float.valueOf(10.0f));
        tickStyle.b.b(ax);
        tickStyle.c.b(Float.valueOf(16.0f));
        tickStyle.a.b(Integer.valueOf(e));
        tickStyle.d.b(Integer.valueOf(0));
        tickStyle.e.b(Integer.valueOf(d));
        tickStyle.g.b(Float.valueOf(1.0f));
    }

    private static void a(AxisTitleStyle axisTitleStyle) {
        c(axisTitleStyle);
        axisTitleStyle.a.b(Title.Orientation.HORIZONTAL);
    }

    private static void b(AxisTitleStyle axisTitleStyle) {
        c(axisTitleStyle);
        axisTitleStyle.a.b(Title.Orientation.VERTICAL);
    }

    private static void c(AxisTitleStyle axisTitleStyle) {
        axisTitleStyle.g.b(Position.CENTER);
        axisTitleStyle.d.b(ax);
        axisTitleStyle.e.b(Float.valueOf(c));
        axisTitleStyle.c.b(Integer.valueOf(0));
        axisTitleStyle.f.b(Float.valueOf(16.0f));
        axisTitleStyle.h.b(Integer.valueOf(b));
        axisTitleStyle.i.b(Float.valueOf(ay));
        axisTitleStyle.j.b(Float.valueOf(az));
    }

    private static void a(GridlineStyle gridlineStyle) {
        gridlineStyle.b.b(Boolean.valueOf(g));
        gridlineStyle.d.b(h);
        gridlineStyle.c.b(Integer.valueOf(f));
        gridlineStyle.a.b(Boolean.valueOf(i));
    }

    private static void a(GridStripeStyle gridStripeStyle) {
        gridStripeStyle.a.b(Integer.valueOf(j));
        gridStripeStyle.b.b(Integer.valueOf(k));
        gridStripeStyle.c.b(Boolean.valueOf(l));
    }

    private static void a(CrosshairStyle crosshairStyle) {
        crosshairStyle.b.b(Integer.valueOf(v));
        crosshairStyle.a.b(Float.valueOf(ar));
        crosshairStyle.c.b(Float.valueOf(as));
        crosshairStyle.d.b(ax);
        crosshairStyle.e.b(Float.valueOf(at));
        crosshairStyle.f.b(Integer.valueOf(w));
        crosshairStyle.g.b(Integer.valueOf(x));
        crosshairStyle.h.b(Integer.valueOf(y));
        crosshairStyle.i.b(Float.valueOf(au));
        crosshairStyle.j.b(Float.valueOf(av));
        crosshairStyle.k.b(Integer.valueOf(z));
    }

    private static void a(AnnotationStyle annotationStyle) {
        annotationStyle.d.b(Integer.valueOf(A));
        annotationStyle.a.b(Integer.valueOf(B));
        annotationStyle.b.b(Float.valueOf(aw));
        annotationStyle.c.b(ax);
    }

    private static int a(int i2) {
        switch (i2 % 6) {
            case 0:
                return C;
            case 1:
                return G;
            case 2:
                return K;
            case 3:
                return O;
            case 4:
                return S;
            default:
                return W;
        }
    }

    private static int b(int i2) {
        switch (i2 % 6) {
            case 0:
                return D;
            case 1:
                return H;
            case 2:
                return L;
            case 3:
                return P;
            case 4:
                return T;
            default:
                return X;
        }
    }

    private static int c(int i2) {
        switch (i2 % 6) {
            case 0:
                return F;
            case 1:
                return J;
            case 2:
                return N;
            case 3:
                return R;
            case 4:
                return V;
            default:
                return Z;
        }
    }

    private static int d(int i2) {
        switch (i2 % 6) {
            case 0:
                return E;
            case 1:
                return I;
            case 2:
                return M;
            case 3:
                return Q;
            case 4:
                return U;
            default:
                return Y;
        }
    }

    private static void a(Context context, AttributeSet attributeSet, int i2) {
        TypedArray typedArray;
        if (i2 != 0) {
            typedArray = context.getTheme().obtainStyledAttributes(i2, R.styleable.ChartTheme);
        } else {
            typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ChartTheme);
        }
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        a = typedArray.getColor(R.styleable.ChartTheme_sc_chartTitleColor, resources.getColor(R.color.sc_chartDefaultTitleColor));
        b = typedArray.getColor(R.styleable.ChartTheme_sc_axisTitleColor, resources.getColor(R.color.sc_axisDefaultTitleColor));
        resources.getValue(R.dimen.sc_axisTitleTextSize, typedValue, true);
        c = typedArray.getFloat(R.styleable.ChartTheme_sc_axisTitleTextSize, typedValue.getFloat());
        d = typedArray.getColor(R.styleable.ChartTheme_sc_tickLineColor, resources.getColor(R.color.sc_tickDefaultLineColor));
        e = typedArray.getColor(R.styleable.ChartTheme_sc_tickLabelColor, resources.getColor(R.color.sc_tickDefaultLabelColor));
        f = typedArray.getColor(R.styleable.ChartTheme_sc_gridLineColor, resources.getColor(R.color.sc_gridDefaultLineColor));
        i = typedArray.getBoolean(R.styleable.ChartTheme_sc_showGridLines, resources.getBoolean(R.bool.sc_defaultShowGridLines));
        g = typedArray.getBoolean(R.styleable.ChartTheme_sc_dashedGridLines, resources.getBoolean(R.bool.sc_dashedGridLines));
        l = typedArray.getBoolean(R.styleable.ChartTheme_sc_showGridStripes, resources.getBoolean(R.bool.sc_showGridStripes));
        j = typedArray.getColor(R.styleable.ChartTheme_sc_gridStripeColor, resources.getColor(R.color.sc_defaultGridStripeColor));
        h = new float[]{10.0f, 10.0f};
        k = typedArray.getColor(R.styleable.ChartTheme_sc_alternateGridStripeColor, resources.getColor(R.color.sc_defaultAlternateGridStripeColor));
        m = typedArray.getColor(R.styleable.ChartTheme_sc_axisLineColor, resources.getColor(R.color.sc_axisDefaultLineColor));
        n = typedArray.getColor(R.styleable.ChartTheme_sc_defaultSelectedSeriesColor, resources.getColor(R.color.sc_defaultSelectedSeriesColor));
        p = typedArray.getColor(R.styleable.ChartTheme_sc_chartBackgroundColor, resources.getColor(R.color.sc_chartDefaultBackgroundColor));
        q = typedArray.getColor(R.styleable.ChartTheme_sc_plotAreaColor, resources.getColor(R.color.sc_plotDefaultAreaColor));
        o = typedArray.getColor(R.styleable.ChartTheme_sc_pieDonutLabelColor, resources.getColor(R.color.sc_pieDonutLabelDefaultColor));
        r = resources.getColor(R.color.sc_plotDefaultAreaBorderColor);
        s = typedArray.getColor(R.styleable.ChartTheme_sc_legendTextColor, resources.getColor(R.color.sc_legendDefaultTextColor));
        t = typedArray.getColor(R.styleable.ChartTheme_sc_legendBorderColor, resources.getColor(R.color.sc_legendDefaultBorderColor));
        u = typedArray.getColor(R.styleable.ChartTheme_sc_legendBackgroundColor, resources.getColor(R.color.sc_legendDefaultBackgroundColor));
        v = typedArray.getColor(R.styleable.ChartTheme_sc_crosshairLineColor, resources.getColor(R.color.sc_crosshairDefaultLineColor));
        w = typedArray.getColor(R.styleable.ChartTheme_sc_crosshairTooltipTextColor, resources.getColor(R.color.sc_crosshairTooltipDefaultTextColor));
        x = typedArray.getColor(R.styleable.ChartTheme_sc_crosshairTooltipLabelBackgroundColor, resources.getColor(R.color.sc_crosshairTooltipDefaultLabelBackgroundColor));
        y = typedArray.getColor(R.styleable.ChartTheme_sc_crosshairTooltipBackgroundColor, resources.getColor(R.color.sc_crosshairTooltipDefaultBackgroundColor));
        z = typedArray.getColor(R.styleable.ChartTheme_sc_crosshairTooltipBorderColor, resources.getColor(R.color.sc_crosshairTooltipDefaultBorderColor));
        resources.getValue(R.dimen.sc_defaultLineWidth, typedValue, true);
        ah = typedArray.getFloat(R.styleable.ChartTheme_sc_lineWidth, typedValue.getFloat());
        resources.getValue(R.dimen.sc_defaultColumnLineWidth, typedValue, true);
        ai = typedArray.getFloat(R.styleable.ChartTheme_sc_columnLineWidth, typedValue.getFloat());
        resources.getValue(R.dimen.sc_defaultBarLineWidth, typedValue, true);
        aj = typedArray.getFloat(R.styleable.ChartTheme_sc_barLineWidth, typedValue.getFloat());
        resources.getValue(R.dimen.sc_defaultDonutCrustThickness, typedValue, true);
        ak = typedArray.getFloat(R.styleable.ChartTheme_sc_donutCrustThickness, typedValue.getFloat());
        resources.getValue(R.dimen.sc_defaultCrosshairLineWidth, typedValue, true);
        ar = typedArray.getFloat(R.styleable.ChartTheme_sc_crosshairLineWidth, typedValue.getFloat());
        resources.getValue(R.dimen.sc_defaultCrosshairPadding, typedValue, true);
        as = typedArray.getFloat(R.styleable.ChartTheme_sc_crosshairPadding, typedValue.getFloat());
        resources.getValue(R.dimen.sc_defaultCrosshairTooltipTextSize, typedValue, true);
        at = typedArray.getFloat(R.styleable.ChartTheme_sc_crosshairTooltipTextSize, typedValue.getFloat());
        resources.getValue(R.dimen.sc_defaultCrosshairTooltipCornerRadius, typedValue, true);
        au = typedArray.getFloat(R.styleable.ChartTheme_sc_crosshairTooltipCornerRadius, typedValue.getFloat());
        resources.getValue(R.dimen.sc_defaultCrosshairTooltipBorderWidth, typedValue, true);
        av = typedArray.getFloat(R.styleable.ChartTheme_sc_crosshairTooltipBorderWidth, typedValue.getFloat());
        C = typedArray.getColor(R.styleable.ChartTheme_sc_seriesLineColor1, resources.getColor(R.color.sc_purpleDark));
        D = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaColor1, resources.getColor(R.color.sc_purpleLightFill));
        E = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientColor1, resources.getColor(R.color.sc_purpleDarkFill));
        F = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientTransparentColor1, resources.getColor(R.color.sc_purpleDarkFillAlpha));
        G = typedArray.getColor(R.styleable.ChartTheme_sc_seriesLineColor2, resources.getColor(R.color.sc_blueDark));
        H = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaColor2, resources.getColor(R.color.sc_blueLightFill));
        I = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientColor2, resources.getColor(R.color.sc_blueDarkFill));
        J = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientTransparentColor2, resources.getColor(R.color.sc_blueDarkFillAlpha));
        K = typedArray.getColor(R.styleable.ChartTheme_sc_seriesLineColor3, resources.getColor(R.color.sc_orangeDark));
        L = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaColor3, resources.getColor(R.color.sc_orangeLightFill));
        M = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientColor3, resources.getColor(R.color.sc_orangeDarkFill));
        N = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientTransparentColor3, resources.getColor(R.color.sc_orangeDarkFillAlpha));
        O = typedArray.getColor(R.styleable.ChartTheme_sc_seriesLineColor4, resources.getColor(R.color.sc_greenDark));
        P = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaColor4, resources.getColor(R.color.sc_greenLightFill));
        Q = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientColor4, resources.getColor(R.color.sc_greenDarkFill));
        R = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientTransparentColor4, resources.getColor(R.color.sc_greenDarkFillAlpha));
        S = typedArray.getColor(R.styleable.ChartTheme_sc_seriesLineColor5, resources.getColor(R.color.sc_yellowDark));
        T = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaColor5, resources.getColor(R.color.sc_yellowLightFill));
        U = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientColor5, resources.getColor(R.color.sc_yellowDarkFill));
        V = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientTransparentColor5, resources.getColor(R.color.sc_yellowDarkFillAlpha));
        W = typedArray.getColor(R.styleable.ChartTheme_sc_seriesLineColor6, resources.getColor(R.color.sc_pinkDark));
        X = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaColor6, resources.getColor(R.color.sc_pinkLightFill));
        Y = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientColor6, resources.getColor(R.color.sc_pinkDarkFill));
        Z = typedArray.getColor(R.styleable.ChartTheme_sc_seriesAreaGradientTransparentColor6, resources.getColor(R.color.sc_pinkDarkFillAlpha));
        aa = typedArray.getColor(R.styleable.ChartTheme_sc_pieDonutFlavorColor1, resources.getColor(R.color.sc_radialPurple));
        ab = typedArray.getColor(R.styleable.ChartTheme_sc_pieDonutFlavorColor2, resources.getColor(R.color.sc_radialBlue));
        ac = typedArray.getColor(R.styleable.ChartTheme_sc_pieDonutFlavorColor3, resources.getColor(R.color.sc_radialOrange));
        ad = typedArray.getColor(R.styleable.ChartTheme_sc_pieDonutFlavorColor4, resources.getColor(R.color.sc_radialGreen));
        ae = typedArray.getColor(R.styleable.ChartTheme_sc_pieDonutFlavorColor5, resources.getColor(R.color.sc_radialYellow));
        af = typedArray.getColor(R.styleable.ChartTheme_sc_pieDonutFlavorColor6, resources.getColor(R.color.sc_radialPink));
        ag = typedArray.getColor(R.styleable.ChartTheme_sc_defaultCrustColor, resources.getColor(R.color.sc_crustColor));
        al = typedArray.getColor(R.styleable.ChartTheme_sc_financialRisingColor, resources.getColor(R.color.sc_financialGreen));
        am = typedArray.getColor(R.styleable.ChartTheme_sc_financialFallingColor, resources.getColor(R.color.sc_financialRed));
        an = typedArray.getColor(R.styleable.ChartTheme_sc_bandSeriesHighColor, resources.getColor(R.color.sc_financialBlue));
        ao = typedArray.getColor(R.styleable.ChartTheme_sc_bandSeriesLowColor, resources.getColor(R.color.sc_financialBlue));
        ap = typedArray.getColor(R.styleable.ChartTheme_sc_bandSeriesAreaColor, resources.getColor(R.color.sc_financialBlueAlpha));
        aq = typedArray.getColor(R.styleable.ChartTheme_sc_bandSeriesAreaInvertedColor, resources.getColor(R.color.sc_financialBlueAlpha));
        resources.getValue(R.dimen.sc_defaultTitlePadding, typedValue, true);
        ay = typedArray.getFloat(R.styleable.ChartTheme_sc_titlePadding, typedValue.getFloat());
        resources.getValue(R.dimen.sc_defaultTitleMargin, typedValue, true);
        az = typedArray.getFloat(R.styleable.ChartTheme_sc_titleMargin, typedValue.getFloat());
        A = typedArray.getColor(R.styleable.ChartTheme_sc_annotationBackgroundColor, resources.getColor(R.color.sc_annotationDefaultBackgroundColor));
        B = typedArray.getColor(R.styleable.ChartTheme_sc_annotationTextColor, resources.getColor(R.color.sc_annotationDefaultTextColor));
        resources.getValue(R.dimen.sc_defaultAnnotationTextSize, typedValue, true);
        aw = typedArray.getFloat(R.styleable.ChartTheme_sc_annotationTextSize, typedValue.getFloat());
        typedArray.recycle();
    }
}
