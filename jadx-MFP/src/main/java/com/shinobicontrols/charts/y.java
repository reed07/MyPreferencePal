package com.shinobicontrols.charts;

import android.view.ViewConfiguration;

class y {
    final int a = this.g.getScaledTouchSlop();
    final int b;
    final int c;
    final int d;
    int e;
    final int f;
    private final ViewConfiguration g;
    private final v h;

    y(v vVar) {
        this.h = vVar;
        this.g = ViewConfiguration.get(vVar.getContext());
        float f2 = vVar.getResources().getDisplayMetrics().density;
        this.b = at.a(f2, 5.0f);
        this.c = this.g.getScaledMinimumFlingVelocity();
        this.d = ViewConfiguration.getDoubleTapTimeout();
        this.e = ViewConfiguration.getLongPressTimeout();
        this.f = at.a(f2, 20.0f);
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.h.r() ? this.a : this.b;
    }
}
