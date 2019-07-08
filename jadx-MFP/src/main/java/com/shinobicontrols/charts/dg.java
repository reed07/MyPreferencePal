package com.shinobicontrols.charts;

import android.graphics.Canvas;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

final class dg {
    boolean a = false;
    private final Axis<?, ?> b;
    private final List<TickMark> c = new ArrayList();
    private final List<TickMark> d = new ArrayList();
    private final Stack<TickMark> e = new Stack<>();
    private final df f;
    private final a g = new a();
    private TickMark[] h = new TickMark[0];

    private static class a {
        public int a;

        private a() {
        }
    }

    public dg(Axis<?, ?> axis) {
        this.b = axis;
        this.f = axis.B();
    }

    public void a(c cVar) {
        if (this.a) {
            b();
            this.a = false;
        } else {
            d();
        }
        if (!Range.a((Range<?>) this.b.i)) {
            if (cVar.b || cVar.c || cVar.e || cVar.f || cVar.d) {
                d(cVar);
            }
        }
    }

    public void a() {
        this.c.clear();
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.c.clear();
        this.d.clear();
        this.e.clear();
    }

    private void d() {
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            this.e.push((TickMark) this.d.get(i));
        }
        this.d.clear();
        int size2 = this.c.size();
        this.h = (TickMark[]) this.c.toArray(this.h);
        for (int i2 = 0; i2 < size2; i2++) {
            TickMark tickMark = this.h[i2];
            if (tickMark.b) {
                this.d.add(tickMark);
            } else {
                this.e.push(tickMark);
            }
        }
        this.c.clear();
    }

    public void a(Canvas canvas, c cVar) {
        int size = this.c.size();
        this.h = (TickMark[]) this.c.toArray(this.h);
        for (int i = 0; i < size; i++) {
            this.h[i].a(canvas, this, i, cVar);
        }
    }

    private TickMark a(double d2, c cVar) {
        TickMark tickMark;
        int size = this.d.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                tickMark = null;
                break;
            }
            tickMark = (TickMark) this.d.get(i);
            if (tickMark.a == d2) {
                break;
            }
            i++;
        }
        if (tickMark == null) {
            tickMark = c(cVar);
        } else {
            this.d.remove(tickMark);
        }
        tickMark.b = true;
        tickMark.e = cVar.c;
        return tickMark;
    }

    private TickMark b(c cVar) {
        TickMark c2 = c(cVar);
        c2.b = false;
        c2.e = cVar.d;
        return c2;
    }

    private TickMark c(c cVar) {
        if (this.e.isEmpty()) {
            return new TickMark(this.b);
        }
        return (TickMark) this.e.pop();
    }

    private void d(c cVar) {
        if (this.b.t != null) {
            a(this.b.t, cVar);
        } else {
            e(cVar);
        }
    }

    private void a(double[] dArr, c cVar) {
        this.g.a = 0;
        int a2 = a(dArr);
        if (a2 != -1) {
            a(cVar, this.g, a2, dArr, false);
        }
    }

    private void a(c cVar, a aVar, int i, double[] dArr, boolean z) {
        while (aVar.a < 2 && i < dArr.length) {
            a(dArr[i], cVar, z, cVar.b, aVar);
            z = !z;
            i++;
        }
    }

    private int a(double[] dArr) {
        for (int i = 0; i < dArr.length; i++) {
            if (dArr[i] >= this.b.i.a) {
                return i;
            }
        }
        return -1;
    }

    private void e(c cVar) {
        this.g.a = 0;
        double a2 = this.b.a(cVar.A);
        c cVar2 = cVar;
        double d2 = a2;
        boolean b2 = this.b.b(a2);
        a(cVar2, this.g, d2, b2);
        b(cVar2, this.g, d2, b2);
    }

    private void a(c cVar, a aVar, double d2, boolean z) {
        int i;
        c cVar2 = cVar;
        boolean j = this.b.j();
        int a2 = a(j, this.b.n(), cVar2);
        if (j) {
            i = 0;
        } else {
            i = this.b.a(cVar2.A, a2);
        }
        a aVar2 = aVar;
        double d3 = d2;
        boolean z2 = z;
        int i2 = 0;
        while (aVar2.a < 2) {
            int i3 = i;
            double d4 = d3;
            a(d3, cVar, z2, cVar2.b && i2 == i, aVar);
            i2++;
            if (i2 == a2) {
                i2 = 0;
            }
            d3 = this.b.a(d4, true);
            z2 = !z2;
            i = i3;
        }
    }

    private int a(boolean z, double d2, c cVar) {
        int i;
        if (z) {
            return 1;
        }
        int ceil = (int) Math.ceil(this.b.i.b() / d2);
        if (this.b.a()) {
            i = (int) Math.ceil((double) (1.0f / ((float) ((cVar.A / cVar.y.x) / ceil))));
        } else {
            i = (int) Math.ceil((double) (1.0f / ((float) ((cVar.A / cVar.y.y) / ceil))));
        }
        return i;
    }

    private void b(c cVar, a aVar, double d2, boolean z) {
        boolean j = this.b.j();
        this.b.q();
        if (this.b.o() && cVar.d && this.b.b(cVar.A) != Double.NaN) {
            double b2 = this.b.b(cVar.A);
            while (b2 <= this.b.i.b) {
                a(b2, cVar, z, j);
                b2 = this.b.a(b2, false);
            }
        }
    }

    private void a(double d2, c cVar, boolean z, boolean z2, a aVar) {
        c cVar2 = cVar;
        a aVar2 = aVar;
        TickMark a2 = a(d2, cVar);
        this.c.add(a2);
        a2.a = d2;
        a2.c = z;
        a2.d = z2;
        double b2 = this.b.b(d2, (double) cVar2.A, cVar2.B);
        boolean a3 = this.f.a(a2, cVar, this.b, b2);
        if (this.b.a() && a3) {
            aVar2.a++;
        }
        boolean b3 = this.f.b(a2, cVar, this.b, b2);
        if (a3 || b3) {
            if (!this.b.a()) {
                aVar2.a++;
            }
            a2.d = false;
            a2.e = !this.f.a(a2, cVar, this.b, a3, b3);
        }
    }

    private void a(double d2, c cVar, boolean z, boolean z2) {
        TickMark b2 = b(cVar);
        this.c.add(b2);
        b2.a = d2;
        b2.c = z;
        b2.d = false;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i, PointF pointF, TickMark tickMark, PointF pointF2, c cVar) {
        int i2 = i - 1;
        while (i2 > 0 && !((TickMark) this.c.get(i2)).b) {
            i2--;
        }
        if (i2 < 0 || !((TickMark) this.c.get(i2)).b) {
            pointF.x = (float) cVar.a.left;
            pointF.y = (float) cVar.a.bottom;
        } else {
            pointF.x = ((TickMark) this.c.get(i2)).g.exactCenterX();
            pointF.y = ((TickMark) this.c.get(i2)).g.exactCenterY();
        }
        pointF2.x = tickMark.g.exactCenterX();
        pointF2.y = tickMark.g.exactCenterY();
        if (pointF2.x > ((float) cVar.a.right)) {
            pointF2.x = (float) cVar.a.right;
        }
        if (pointF2.y < ((float) cVar.a.top)) {
            pointF2.y = (float) cVar.a.top;
        }
        if (pointF.x > ((float) cVar.a.right)) {
            pointF.x = (float) cVar.a.right;
        }
        if (pointF.y < ((float) cVar.a.top)) {
            pointF.y = (float) cVar.a.top;
        }
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        int size = this.c.size();
        this.h = (TickMark[]) this.c.toArray(this.h);
        for (int i = 0; i < size; i++) {
            this.h[i].a();
        }
    }
}
