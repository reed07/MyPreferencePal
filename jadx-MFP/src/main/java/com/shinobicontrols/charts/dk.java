package com.shinobicontrols.charts;

import android.view.VelocityTracker;

class dk {
    private VelocityTracker a;

    dk() {
    }

    /* access modifiers changed from: 0000 */
    public VelocityTracker a() {
        if (this.a == null) {
            this.a = VelocityTracker.obtain();
        }
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public VelocityTracker b() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        VelocityTracker velocityTracker = this.a;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.a = null;
        }
    }
}
