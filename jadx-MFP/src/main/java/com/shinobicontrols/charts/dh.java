package com.shinobicontrols.charts;

import android.graphics.PointF;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.Series.Orientation;

abstract class dh {
    static dh f = new dh() {
        /* access modifiers changed from: 0000 */
        public void a(Tooltip tooltip, DataPoint<?, ?> dataPoint) {
            DefaultTooltipView defaultTooltipView = (DefaultTooltipView) tooltip.getView();
            this.c = defaultTooltipView.getChildCount();
            CartesianSeries trackedSeries = tooltip.getTrackedSeries();
            Axis xAxis = trackedSeries.getXAxis();
            Axis yAxis = trackedSeries.getYAxis();
            this.a.setLength(0);
            StringBuilder sb = this.a;
            sb.append(xAxis.a(xAxis.translatePoint(dataPoint.getX())));
            sb.append(", ");
            sb.append(yAxis.a(yAxis.translatePoint(dataPoint.getY())));
            defaultTooltipView.a.setText(this.a.toString());
            defaultTooltipView.a.setVisibility(0);
            defaultTooltipView.b.setVisibility(8);
            defaultTooltipView.c.setVisibility(8);
            defaultTooltipView.d.setVisibility(8);
            defaultTooltipView.e.setVisibility(8);
            defaultTooltipView.f.setVisibility(8);
            defaultTooltipView.g.setVisibility(8);
            defaultTooltipView.h.setVisibility(8);
            defaultTooltipView.i.setVisibility(8);
        }
    };
    static dh g = new dh() {
        /* access modifiers changed from: 0000 */
        public void a(Tooltip tooltip, DataPoint<?, ?> dataPoint) {
            DefaultTooltipView defaultTooltipView = (DefaultTooltipView) tooltip.getView();
            this.c = defaultTooltipView.getChildCount();
            a(new TextView[]{defaultTooltipView.a, defaultTooltipView.b, defaultTooltipView.f, defaultTooltipView.c, defaultTooltipView.g}, dataPoint, tooltip);
            defaultTooltipView.d.setVisibility(8);
            defaultTooltipView.e.setVisibility(8);
            defaultTooltipView.h.setVisibility(8);
            defaultTooltipView.i.setVisibility(8);
            a(defaultTooltipView, tooltip);
        }
    };
    static dh h = new dh() {
        /* access modifiers changed from: 0000 */
        public void a(Tooltip tooltip, DataPoint<?, ?> dataPoint) {
            DefaultTooltipView defaultTooltipView = (DefaultTooltipView) tooltip.getView();
            this.c = defaultTooltipView.getChildCount();
            Axis a = dh.c(tooltip.getTrackedSeries());
            a(new TextView[]{defaultTooltipView.a, defaultTooltipView.c, defaultTooltipView.g, defaultTooltipView.d, defaultTooltipView.h}, dataPoint, tooltip);
            MultiValueData multiValueData = (MultiValueData) dataPoint;
            defaultTooltipView.b.setText("open : ");
            defaultTooltipView.f.setText(a.a(a.translatePoint(multiValueData.getOpen())));
            defaultTooltipView.b.setVisibility(0);
            defaultTooltipView.f.setVisibility(0);
            defaultTooltipView.e.setText("  close : ");
            defaultTooltipView.i.setText(a.a(a.translatePoint(multiValueData.getClose())));
            defaultTooltipView.e.setVisibility(0);
            defaultTooltipView.i.setVisibility(0);
            a(defaultTooltipView, tooltip);
        }
    };
    static dh i = new dh() {
        /* access modifiers changed from: 0000 */
        public void a(Tooltip tooltip, DataPoint<?, ?> dataPoint) {
        }
    };
    final StringBuilder a = new StringBuilder();
    ar b = new ar();
    int c;
    int d;
    int e;

    /* access modifiers changed from: 0000 */
    public abstract void a(Tooltip tooltip, DataPoint<?, ?> dataPoint);

    dh() {
    }

    static dh a(Tooltip tooltip) {
        View view = tooltip.getView();
        CartesianSeries trackedSeries = tooltip.getTrackedSeries();
        if (trackedSeries == null || !(view instanceof DefaultTooltipView)) {
            return i;
        }
        if ((trackedSeries instanceof CandlestickSeries) || (trackedSeries instanceof OHLCSeries)) {
            return h;
        }
        if (trackedSeries instanceof BandSeries) {
            return g;
        }
        return f;
    }

    private static Axis<?, ?> b(CartesianSeries<?> cartesianSeries) {
        return d(cartesianSeries) ? cartesianSeries.getYAxis() : cartesianSeries.getXAxis();
    }

    /* access modifiers changed from: private */
    public static Axis<?, ?> c(CartesianSeries<?> cartesianSeries) {
        return d(cartesianSeries) ? cartesianSeries.getXAxis() : cartesianSeries.getYAxis();
    }

    private static boolean d(CartesianSeries<?> cartesianSeries) {
        return cartesianSeries.j == Orientation.VERTICAL;
    }

    /* access modifiers changed from: 0000 */
    public void a(TextView[] textViewArr, DataPoint<?, ?> dataPoint, Tooltip tooltip) {
        CartesianSeries trackedSeries = tooltip.getTrackedSeries();
        Axis c2 = c(trackedSeries);
        Axis b2 = b(trackedSeries);
        this.a.setLength(0);
        StringBuilder sb = this.a;
        sb.append("x : ");
        sb.append(b2.a(b2.translatePoint(dataPoint.getX())));
        textViewArr[0].setText(this.a.toString());
        textViewArr[0].setVisibility(0);
        MultiValueData multiValueData = (MultiValueData) dataPoint;
        textViewArr[1].setText("high : ");
        textViewArr[2].setText(c2.a(c2.translatePoint(multiValueData.getHigh())));
        textViewArr[1].setVisibility(0);
        textViewArr[2].setVisibility(0);
        textViewArr[3].setText("low : ");
        textViewArr[4].setText(c2.a(c2.translatePoint(multiValueData.getLow())));
        textViewArr[3].setVisibility(0);
        textViewArr[4].setVisibility(0);
    }

    /* access modifiers changed from: 0000 */
    public void a(DefaultTooltipView defaultTooltipView, Tooltip tooltip) {
        ShinobiChart chart = tooltip.getTrackedSeries().getChart();
        b(defaultTooltipView, a(chart.getCrosshair().getStyle(), chart));
        a(defaultTooltipView, a(defaultTooltipView, chart.getCrosshair().getStyle(), chart));
    }

    /* access modifiers changed from: 0000 */
    public int a(CrosshairStyle crosshairStyle, ShinobiChart shinobiChart) {
        PointF pointF = new PointF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        this.b.a(pointF, "  close : ", crosshairStyle.getTooltipTextSize(), crosshairStyle.getTooltipTypeface(), (v) shinobiChart);
        this.d = (int) pointF.x;
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public int a(DefaultTooltipView defaultTooltipView, CrosshairStyle crosshairStyle, ShinobiChart shinobiChart) {
        PointF pointF = new PointF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        float f2 = BitmapDescriptorFactory.HUE_RED;
        for (int i2 = 5; i2 < this.c; i2++) {
            PointF pointF2 = pointF;
            this.b.a(pointF2, (String) ((TextView) defaultTooltipView.getChildAt(i2)).getText(), crosshairStyle.getTooltipTextSize(), crosshairStyle.getTooltipTypeface(), (v) shinobiChart);
            if (pointF.x > f2) {
                f2 = pointF.x;
            }
        }
        this.e = (int) f2;
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public void a(DefaultTooltipView defaultTooltipView, int i2) {
        for (int i3 = 5; i3 < this.c; i3++) {
            ((TextView) defaultTooltipView.getChildAt(i3)).setWidth(i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(DefaultTooltipView defaultTooltipView, int i2) {
        for (int i3 = 1; i3 < 5; i3++) {
            ((TextView) defaultTooltipView.getChildAt(i3)).setWidth(i2);
        }
    }
}
