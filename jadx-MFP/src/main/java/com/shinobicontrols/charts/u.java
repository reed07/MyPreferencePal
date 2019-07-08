package com.shinobicontrols.charts;

class u extends df {
    private boolean a(double d, double d2, double d3) {
        return d > d2 + d3;
    }

    u() {
    }

    /* access modifiers changed from: 0000 */
    public boolean a(TickMark tickMark, c cVar, Axis<?, ?> axis, double d) {
        return a(tickMark.a, axis.j.b, 0.0d) || super.a(tickMark, cVar, axis, d);
    }

    /* access modifiers changed from: 0000 */
    public boolean b(TickMark tickMark, c cVar, Axis<?, ?> axis, double d) {
        return a(tickMark.a, axis.j.b, 0.0d) || super.b(tickMark, cVar, axis, d);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(TickMark tickMark, c cVar, Axis<?, ?> axis, boolean z, boolean z2) {
        return a(tickMark.a, axis.j.b, 0.0d) || super.a(tickMark, cVar, axis, z, z2);
    }
}
