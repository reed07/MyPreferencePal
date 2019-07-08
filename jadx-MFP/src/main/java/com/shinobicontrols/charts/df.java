package com.shinobicontrols.charts;

import com.shinobicontrols.charts.TickMark.ClippingMode;

class df {
    df() {
    }

    /* access modifiers changed from: 0000 */
    public boolean a(TickMark tickMark, c cVar, Axis<?, ?> axis, double d) {
        boolean z = true;
        switch (cVar.F) {
            case TICKS_AND_LABELS_PERSIST:
                if (d <= ((double) cVar.C)) {
                    z = false;
                }
                return z;
            case NEITHER_PERSIST:
            case TICKS_PERSIST:
                if (d + (((double) cVar.E) * 0.5d) <= ((double) cVar.C)) {
                    z = false;
                }
                return z;
            default:
                return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean b(TickMark tickMark, c cVar, Axis<?, ?> axis, double d) {
        boolean z = true;
        switch (cVar.G) {
            case TICKS_AND_LABELS_PERSIST:
                if (d >= ((double) cVar.D)) {
                    z = false;
                }
                return z;
            case NEITHER_PERSIST:
            case TICKS_PERSIST:
                if (d - (((double) cVar.E) * 0.5d) >= ((double) cVar.D)) {
                    z = false;
                }
                return z;
            default:
                return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(TickMark tickMark, c cVar, Axis<?, ?> axis, boolean z, boolean z2) {
        boolean z3 = true;
        if (z2 && !z) {
            if (cVar.G == ClippingMode.TICKS_PERSIST) {
                z3 = false;
            }
            return z3;
        } else if (!z || z2) {
            return false;
        } else {
            if (cVar.F == ClippingMode.TICKS_PERSIST) {
                z3 = false;
            }
            return z3;
        }
    }
}
