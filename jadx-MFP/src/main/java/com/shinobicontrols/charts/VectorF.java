package com.shinobicontrols.charts;

import android.graphics.PointF;

class VectorF extends PointF {
    VectorF(float f, float f2) {
        super(f, f2);
    }

    static VectorF a(PointF pointF, PointF pointF2) {
        return new VectorF(pointF2.x - pointF.x, pointF2.y - pointF.y);
    }

    /* access modifiers changed from: 0000 */
    public float a() {
        return Math.max(Math.abs(this.x), Math.abs(this.y));
    }

    /* access modifiers changed from: 0000 */
    public VectorF b() {
        return new VectorF(Math.abs(this.x), Math.abs(this.y));
    }
}
