package com.shinobicontrols.charts;

import android.view.MotionEvent;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;

class cy {
    private final dk a = new dk();
    private final cd b = new cd(2, this.a);
    private final cz c;
    private final af d;
    private final y e;

    cy(v vVar, OnGestureListener onGestureListener) {
        this.e = vVar.m;
        this.c = new cz(vVar, onGestureListener, this.e);
        this.d = new af(vVar, onGestureListener, this.e);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent.getActionMasked() == 3) {
            this.b.a();
        } else {
            this.a.a().addMovement(motionEvent);
            cb a2 = this.b.a(0);
            cb a3 = this.b.a(1);
            int b2 = this.b.b();
            this.b.a(motionEvent);
            if (a2 == null) {
                a2 = this.b.a(0);
            }
            if (a3 == null) {
                a3 = this.b.a(1);
            }
            int b3 = this.b.b();
            if (!this.c.a(b2, b3, a2) && !this.d.a(b2, b3, a2, a3)) {
                z = false;
            }
        }
        if (this.b.b() == 0) {
            this.a.c();
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.c.a();
    }
}
