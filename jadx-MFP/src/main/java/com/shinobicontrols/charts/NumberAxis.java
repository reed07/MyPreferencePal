package com.shinobicontrols.charts;

import android.graphics.PointF;
import java.text.DecimalFormat;

public class NumberAxis extends Axis<Double, Double> {
    private int A;
    private int B = -1;
    private final bq C = new bq();
    private DecimalFormat D;
    private final PointF E = new PointF(1.2f, 1.2f);

    /* access modifiers changed from: 0000 */
    public double G() {
        return 0.0d;
    }

    /* access modifiers changed from: 0000 */
    public void g() {
    }

    /* access modifiers changed from: protected */
    public Double transformChartValueToUserValue(Double d) {
        return d;
    }

    /* access modifiers changed from: protected */
    public Double transformUserValueToChartValue(Double d) {
        return d;
    }

    /* access modifiers changed from: 0000 */
    public double x() {
        return 1.0d;
    }

    public NumberAxis() {
    }

    public NumberAxis(NumberRange numberRange) {
        setDefaultRange(numberRange);
    }

    /* access modifiers changed from: 0000 */
    public boolean isDataValid(Object obj) {
        return obj instanceof Number;
    }

    /* access modifiers changed from: 0000 */
    public double convertPoint(Object obj) {
        return translatePoint(obj);
    }

    /* access modifiers changed from: 0000 */
    public double translatePoint(Object obj) {
        validateUserData(obj);
        return transformUserValueToInternal(Double.valueOf(convertUserValueTypeToInternalDataType(obj)));
    }

    /* access modifiers changed from: 0000 */
    public double transformExternalValueToInternal(Double d) {
        return d.doubleValue();
    }

    /* access modifiers changed from: 0000 */
    public double convertUserValueTypeToInternalDataType(Object obj) {
        return Double.valueOf(((Number) obj).doubleValue()).doubleValue();
    }

    /* access modifiers changed from: 0000 */
    public Double transformInternalValueToExternal(double d) {
        return Double.valueOf(d);
    }

    /* access modifiers changed from: 0000 */
    public Range<Double> createRange(Double d, Double d2) {
        return new NumberRange(d, d2);
    }

    public DecimalFormat getLabelFormat() {
        return this.D;
    }

    public void setLabelFormat(DecimalFormat decimalFormat) {
        this.D = decimalFormat;
    }

    /* access modifiers changed from: 0000 */
    public String i() {
        if (m()) {
            H();
        }
        if (k()) {
            return this.x;
        }
        if (I()) {
            String format = this.D.format(this.i.a);
            String format2 = this.D.format(this.i.b);
            if (format2.length() > format.length()) {
                format = format2;
            }
            this.w = format;
        } else {
            this.C.a(this.A);
            if (this.C.b(this.B)) {
                this.u.c();
            }
            this.w = this.C.a();
        }
        return this.w;
    }

    private void H() {
        int h = h(((Double) this.r).doubleValue());
        int h2 = h(G());
        this.A = g(Math.max(Math.abs(this.i.a), Math.abs(this.i.b)));
        this.B = Math.max(h2, h);
    }

    private static int g(double d) {
        double log10 = Math.log10(d);
        if (log10 >= 1.0d) {
            return 1 + ((int) Math.floor(log10));
        }
        return 1;
    }

    private static int h(double d) {
        if (d == 0.0d) {
            return 0;
        }
        double log10 = Math.log10(d);
        if (log10 < 0.0d) {
            return (int) Math.ceil(-log10);
        }
        return 0;
    }

    public String getFormattedString(Double d) {
        return I() ? this.D.format(d) : this.C.format(d);
    }

    private boolean I() {
        return this.D != null;
    }

    /* access modifiers changed from: 0000 */
    public void c(int i) {
        setCurrentMajorTickFrequency(Double.valueOf(this.i.b() / 20.0d));
        double pow = Math.pow(10.0d, Math.floor(Math.log10(((Double) this.r).doubleValue())));
        do {
            if (pow > ((Double) this.r).doubleValue()) {
                this.r = Double.valueOf(pow);
                this.s = Double.valueOf(pow / 2.0d);
            } else {
                double d = 5.0d * pow;
                if (d > ((Double) this.r).doubleValue()) {
                    this.r = Double.valueOf(d);
                    this.s = Double.valueOf(pow);
                } else {
                    double d2 = pow * 10.0d;
                    if (d2 > ((Double) this.r).doubleValue()) {
                        this.r = Double.valueOf(d2);
                        this.s = Double.valueOf(d);
                    } else {
                        pow = d2;
                    }
                }
            }
            r();
        } while (!a((int) Math.floor(this.i.b() / ((Double) this.r).doubleValue()), i, this.E));
    }

    /* access modifiers changed from: 0000 */
    public double a(int i) {
        double d = this.i.a;
        double G = G();
        double floor = G + (Math.floor((d - G) / ((Double) this.r).doubleValue()) * ((Double) this.r).doubleValue());
        while (floor < d) {
            floor = a(floor, true);
        }
        while (true) {
            if (a(floor, (double) i, this.i.b())) {
                return floor;
            }
            floor += ((Double) this.r).doubleValue();
        }
    }

    /* access modifiers changed from: 0000 */
    public double b(int i) {
        if (!o()) {
            return Double.NaN;
        }
        double a = a(i);
        double b = b(a, false);
        while (true) {
            double d = a;
            a = b;
            double d2 = d;
            if (a < this.i.a) {
                return d2;
            }
            b = b(a, false);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean b(double d) {
        double IEEEremainder = Math.IEEEremainder((d - G()) / ((Double) this.r).doubleValue(), 2.0d);
        return (IEEEremainder < -0.5d && IEEEremainder > -1.5d) || (IEEEremainder > 0.5d && IEEEremainder < 1.5d);
    }

    /* access modifiers changed from: 0000 */
    public double a(double d, boolean z) {
        double doubleValue = d + ((Double) (z ? this.r : getCurrentMinorTickFrequency())).doubleValue();
        if (doubleValue >= 0.0d || (Math.pow(10.0d, (double) (-(this.B + 1))) * 0.9998999834060669d) + doubleValue <= 0.0d) {
            return doubleValue;
        }
        return 0.0d;
    }

    /* access modifiers changed from: 0000 */
    public double b(double d, boolean z) {
        double doubleValue = d - ((Double) (z ? this.r : getCurrentMinorTickFrequency())).doubleValue();
        if (doubleValue >= 0.0d || (Math.pow(10.0d, (double) (-(this.B + 1))) * 0.9998999834060669d) + doubleValue <= 0.0d) {
            return doubleValue;
        }
        return 0.0d;
    }

    /* access modifiers changed from: 0000 */
    public double transformExternalFrequencyToInternal(Double d) {
        return d.doubleValue();
    }

    /* access modifiers changed from: 0000 */
    public void setMajorTickFrequencyInternal(Double d) {
        if (d == null) {
            this.p = null;
            return;
        }
        if (d.doubleValue() > 0.0d) {
            this.p = d;
        } else {
            cx.b(this.b != null ? this.b.getContext().getString(R.string.NumberAxisInvalidFrequency) : "The frequency is invalid and will be ignored");
            this.p = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void setMinorTickFrequencyInternal(Double d) {
        if (d == null) {
            this.q = null;
            return;
        }
        if (d.doubleValue() > 0.0d) {
            this.q = d;
        } else {
            cx.b(this.b != null ? this.b.getContext().getString(R.string.NumberAxisInvalidFrequency) : "The frequency is invalid and will be ignored");
            this.q = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void p() {
        if (this.p != null) {
            this.r = Double.valueOf(((Double) this.p).doubleValue());
        }
    }

    /* access modifiers changed from: 0000 */
    public void q() {
        if (l() && this.q != null) {
            this.s = Double.valueOf(((Double) this.q).doubleValue());
        }
    }

    /* access modifiers changed from: 0000 */
    public Double getDefaultBaseline() {
        return Double.valueOf(0.0d);
    }

    /* access modifiers changed from: 0000 */
    public Double applyMappingForSkipRangesToUserValue(Double d) {
        return Double.valueOf(this.y.a(d.doubleValue()));
    }

    /* access modifiers changed from: 0000 */
    public Double removeMappingForSkipRangesFromChartValue(Double d) {
        return Double.valueOf(this.z.a(d.doubleValue()));
    }
}
