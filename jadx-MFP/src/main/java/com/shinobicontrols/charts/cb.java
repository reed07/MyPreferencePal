package com.shinobicontrols.charts;

import android.view.VelocityTracker;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;

class cb {
    private final int a;
    private int b;
    private final dk c;
    private final ArrayList<cc> d = new ArrayList<>();
    private boolean e;
    private cc f;

    cb(int i, dk dkVar) {
        this.a = i;
        this.c = dkVar;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.b = 0;
        this.d.clear();
        if (this.c.b() != null) {
            this.c.b().clear();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.f = null;
        a();
    }

    /* access modifiers changed from: 0000 */
    public cc c() {
        return a(this.b);
    }

    /* access modifiers changed from: 0000 */
    public VectorF d() {
        return a(this.b, this.d.size() - 1);
    }

    /* access modifiers changed from: 0000 */
    public VectorF a(int i, int i2) {
        int size = this.d.size();
        if (i >= i2 || i < 0 || size <= i2) {
            return new VectorF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        }
        return VectorF.a(((cc) this.d.get(i)).a, ((cc) this.d.get(i2)).a);
    }

    /* access modifiers changed from: 0000 */
    public cc e() {
        return a(0);
    }

    /* access modifiers changed from: 0000 */
    public long f() {
        cc ccVar = this.f;
        if (ccVar != null) {
            return ccVar.a(e());
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public cc a(int i) {
        return (cc) this.d.get(i);
    }

    /* access modifiers changed from: 0000 */
    public VectorF g() {
        float f2;
        VelocityTracker b2 = this.c.b();
        float f3 = BitmapDescriptorFactory.HUE_RED;
        if (b2 != null) {
            b2.computeCurrentVelocity(1000);
            f3 = b2.getXVelocity(this.a);
            f2 = b2.getYVelocity(this.a);
        } else {
            f2 = BitmapDescriptorFactory.HUE_RED;
        }
        return new VectorF(f3, f2);
    }

    /* access modifiers changed from: 0000 */
    public void b(int i) {
        switch (i) {
            case 0:
            case 5:
                this.e = true;
                return;
            case 1:
            case 3:
            case 6:
                this.f = h();
                this.e = false;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(cc ccVar) {
        if (!this.d.isEmpty() && ccVar.equals(h())) {
            return false;
        }
        this.d.add(ccVar);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(float f2) {
        int i = 0;
        float f3 = BitmapDescriptorFactory.HUE_RED;
        float f4 = BitmapDescriptorFactory.HUE_RED;
        while (i < this.d.size() - 2) {
            int i2 = i + 1;
            VectorF b2 = a(i, i2).b();
            f3 += b2.x;
            f4 += b2.y;
            if (f3 > f2 || f4 > f2) {
                return true;
            }
            i = i2;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public cc h() {
        if (this.d.isEmpty()) {
            return null;
        }
        return a(this.d.size() - 1);
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        this.b = this.d.size() - 1;
    }

    /* access modifiers changed from: 0000 */
    public boolean j() {
        return this.e;
    }
}
