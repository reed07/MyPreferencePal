package com.shinobicontrols.charts;

import java.lang.Comparable;

public abstract class Range<T extends Comparable<T>> {
    double a;
    double b;

    /* access modifiers changed from: 0000 */
    public abstract T getMaximum();

    /* access modifiers changed from: 0000 */
    public abstract T getMinimum();

    static boolean a(Range<?> range) {
        return range == null || range.a();
    }

    static boolean b(Range<?> range) {
        return !a(range);
    }

    Range(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public double getSpan() {
        return b();
    }

    /* access modifiers changed from: 0000 */
    public double b() {
        return this.b - this.a;
    }

    /* access modifiers changed from: 0000 */
    public void a(double d) {
        if (d < this.a) {
            this.a = d;
        }
        if (d > this.b) {
            this.b = d;
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(Range<T> range) {
        if (!a(range)) {
            a(range.a);
            a(range.b);
        }
    }

    public boolean equals(Object obj) {
        Range range = (Range) obj;
        return (obj == null || range == null || !d(range)) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public boolean d(Range<T> range) {
        return range != null && this.a == range.a && this.b == range.b;
    }

    private boolean a() {
        return Double.isInfinite(this.a) || Double.isInfinite(this.b) || this.a > this.b;
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        return this.a == this.b;
    }

    /* access modifiers changed from: 0000 */
    public boolean b(double d, double d2) {
        boolean z;
        double b2 = b();
        if (b2 > d2 - d) {
            this.a = d;
            this.b = d2;
            b2 = this.b - this.a;
            z = true;
        } else {
            z = false;
        }
        if (this.a < d) {
            this.a = d;
            this.b = this.a + b2;
            z = true;
        }
        if (this.b <= d2) {
            return z;
        }
        this.b = d2;
        this.a = this.b - b2;
        return true;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.a);
        long doubleToLongBits2 = Double.doubleToLongBits(this.b);
        return ((527 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=com.shinobicontrols.charts.Range<T>, code=com.shinobicontrols.charts.Range, for r7v0, types: [com.shinobicontrols.charts.Range<T>, com.shinobicontrols.charts.Range] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.shinobicontrols.charts.Range r7, boolean r8) {
        /*
            r6 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            double r1 = r6.a
            double r3 = r7.a
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x000f
            r1 = r7
            r7 = r6
            goto L_0x0010
        L_0x000f:
            r1 = r6
        L_0x0010:
            r2 = 1
            if (r8 == 0) goto L_0x0022
            double r3 = r7.a
            double r7 = r7.b()
            double r3 = r3 + r7
            double r7 = r1.a
            int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r1 < 0) goto L_0x0021
            r0 = 1
        L_0x0021:
            return r0
        L_0x0022:
            double r3 = r7.a
            double r7 = r7.b()
            double r3 = r3 + r7
            double r7 = r1.a
            int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r1 <= 0) goto L_0x0030
            r0 = 1
        L_0x0030:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.Range.a(com.shinobicontrols.charts.Range, boolean):boolean");
    }

    public String toString() {
        return String.format("[%s - %s]", new Object[]{getMinimum().toString(), getMaximum().toString()});
    }
}
