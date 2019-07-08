package com.shinobicontrols.charts;

import android.graphics.PointF;
import android.os.SystemClock;

class cc {
    final PointF a;
    final long b = SystemClock.uptimeMillis();

    cc(float f, float f2) {
        this.a = new PointF(f, f2);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cc)) {
            return false;
        }
        cc ccVar = (cc) obj;
        if (this.b != ccVar.b || !this.a.equals(ccVar)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long j = this.b;
        return ((527 + ((int) (j ^ (j >>> 32)))) * 31) + this.a.hashCode();
    }

    /* access modifiers changed from: 0000 */
    public long a(cc ccVar) {
        return ccVar.b - this.b;
    }
}
