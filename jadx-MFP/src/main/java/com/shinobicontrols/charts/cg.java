package com.shinobicontrols.charts;

import com.shinobicontrols.charts.PieDonutSeries.DrawDirection;

abstract class cg {
    static cg a = new cg() {
        /* access modifiers changed from: 0000 */
        public float a(float f) {
            return f;
        }

        /* access modifiers changed from: 0000 */
        public float a(float f, float f2) {
            return bk.a(f + f2);
        }

        /* access modifiers changed from: 0000 */
        public float b(float f, float f2) {
            return bk.a(f + f2);
        }

        /* access modifiers changed from: 0000 */
        public float a(double d, double d2) {
            return b(d, d2);
        }
    };
    static cg b = new cg() {
        /* access modifiers changed from: 0000 */
        public float a(float f) {
            return (float) (6.283185307179586d - ((double) f));
        }

        /* access modifiers changed from: 0000 */
        public float a(float f, float f2) {
            return bk.a(f2 - f);
        }

        /* access modifiers changed from: 0000 */
        public float b(float f, float f2) {
            return bk.a(f2 - f);
        }

        /* access modifiers changed from: 0000 */
        public float a(double d, double d2) {
            return 6.2831855f - b(d, d2);
        }
    };

    /* access modifiers changed from: 0000 */
    public abstract float a(double d, double d2);

    /* access modifiers changed from: 0000 */
    public abstract float a(float f);

    /* access modifiers changed from: 0000 */
    public abstract float a(float f, float f2);

    /* access modifiers changed from: 0000 */
    public abstract float b(float f, float f2);

    cg() {
    }

    /* access modifiers changed from: 0000 */
    public float b(double d, double d2) {
        return bk.a((float) (Math.atan2(d2, d) + 1.5707963267948966d));
    }

    static cg a(DrawDirection drawDirection) {
        if (drawDirection == DrawDirection.ANTICLOCKWISE) {
            return a;
        }
        return b;
    }
}
