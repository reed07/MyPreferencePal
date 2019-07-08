package com.shinobicontrols.charts;

import android.util.SparseArray;
import android.view.MotionEvent;
import java.util.ArrayList;

class cd {
    private final SparseArray<cb> a;
    private final ArrayList<cb> b;
    private final dk c;

    cd(int i, dk dkVar) {
        this.a = new SparseArray<>(i);
        this.b = new ArrayList<>(i);
        this.c = dkVar;
    }

    /* access modifiers changed from: 0000 */
    public cb a(int i) {
        if (i < 0 || i >= this.b.size()) {
            return null;
        }
        return (cb) this.b.get(i);
    }

    /* access modifiers changed from: 0000 */
    public void a(MotionEvent motionEvent) {
        b(motionEvent);
        c(motionEvent);
    }

    private void b(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            cb a2 = a(i, motionEvent);
            a2.a(new cc(motionEvent.getX(i), motionEvent.getY(i)));
            if (!this.b.contains(a2)) {
                this.b.add(a2);
            }
        }
    }

    private cb a(int i, MotionEvent motionEvent) {
        int pointerId = motionEvent.getPointerId(i);
        cb cbVar = (cb) this.a.get(pointerId);
        if (cbVar != null) {
            return cbVar;
        }
        cb cbVar2 = new cb(pointerId, this.c);
        this.a.put(pointerId, cbVar2);
        return cbVar2;
    }

    private void c(MotionEvent motionEvent) {
        cb cbVar = (cb) this.a.get(motionEvent.getPointerId(motionEvent.getActionIndex()));
        cbVar.b(motionEvent.getActionMasked());
        if (!cbVar.j()) {
            this.b.remove(cbVar);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            cb cbVar = (cb) this.a.valueAt(i);
            if (cbVar != null) {
                cbVar.b();
            }
        }
        this.b.clear();
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return this.b.size();
    }
}
