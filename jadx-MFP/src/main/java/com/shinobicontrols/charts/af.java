package com.shinobicontrols.charts;

import android.graphics.PointF;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;

class af {
    private final OnGestureListener a;
    private final v b;
    private final y c;
    private VectorF d = new VectorF(1.0f, 1.0f);
    private boolean e = false;

    af(v vVar, OnGestureListener onGestureListener, y yVar) {
        this.b = vVar;
        this.a = onGestureListener;
        this.c = yVar;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i, int i2, cb cbVar, cb cbVar2) {
        if (i == 1 && i2 == 2) {
            a(cbVar, cbVar2);
            return true;
        } else if (i == 2 && i2 == 2) {
            b(cbVar, cbVar2);
            return true;
        } else if (i != 2 || i2 != 1) {
            return false;
        } else {
            c(cbVar, cbVar2);
            return true;
        }
    }

    private void a(cb cbVar, cb cbVar2) {
        this.b.a(cbVar.e().a, cbVar2.e().a);
        this.a.onSecondTouchDown(this.b, cbVar.e().a, cbVar2.e().a);
    }

    private void b(cb cbVar, cb cbVar2) {
        boolean z = this.e;
        this.e = a(cbVar.d().a(), cbVar2.d().a());
        if (this.e) {
            PointF pointF = (z ? cbVar.c() : cbVar.e()).a;
            PointF pointF2 = (z ? cbVar2.c() : cbVar2.e()).a;
            PointF a2 = a(pointF, pointF2);
            cbVar.i();
            cbVar2.i();
            PointF a3 = a(cbVar.h().a, cbVar2.h().a);
            this.d = a(VectorF.a(pointF, pointF2).b(), VectorF.a(cbVar.h().a, cbVar2.h().a).b());
            this.b.a(a2, a3, (PointF) this.d);
            this.a.onPinch(this.b, a2, a3, this.d);
        }
    }

    private boolean a(float f, float f2) {
        return this.e || f > ((float) this.c.b) || f2 > ((float) this.c.b);
    }

    private PointF a(PointF pointF, PointF pointF2) {
        return new PointF((pointF.x + pointF2.x) / 2.0f, (pointF.y + pointF2.y) / 2.0f);
    }

    private VectorF a(VectorF vectorF, VectorF vectorF2) {
        float f = 1.0f;
        float f2 = (vectorF.x <= ((float) this.c.f) || vectorF2.x <= ((float) this.c.f)) ? 1.0f : vectorF2.x / vectorF.x;
        if (vectorF.y > ((float) this.c.f) && vectorF2.y > ((float) this.c.f)) {
            f = vectorF2.y / vectorF.y;
        }
        return new VectorF(f2, f);
    }

    private void c(cb cbVar, cb cbVar2) {
        boolean z = false;
        this.e = false;
        float f = (float) this.c.b;
        if (cbVar.a(f) || cbVar2.a(f)) {
            VectorF g = cbVar.g();
            VectorF g2 = cbVar2.g();
            if (g.a() > ((float) this.c.c) || g2.a() > ((float) this.c.c)) {
                z = true;
            }
            PointF a2 = a(cbVar.h().a, cbVar2.h().a);
            this.b.a(a2, z, (PointF) this.d);
            this.a.onPinchEnd(this.b, a2, z, this.d);
        } else {
            this.b.b(cbVar.h().a, cbVar2.h().a);
            this.a.onSecondTouchUp(this.b, cbVar.h().a, cbVar2.h().a);
        }
        cbVar.a();
        cbVar2.a();
    }
}
