package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.List;

class h {
    Axis<?, ?>[] a = new Axis[0];
    private final String b;

    h(String str) {
        this.b = str;
    }

    /* access modifiers changed from: 0000 */
    public Axis<?, ?> a() {
        Axis<?, ?>[] axisArr = this.a;
        if (axisArr.length == 0) {
            return null;
        }
        return axisArr[0];
    }

    /* access modifiers changed from: 0000 */
    public boolean b() {
        return this.a.length > 0;
    }

    /* access modifiers changed from: 0000 */
    public void a(Axis<?, ?> axis) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(axis);
        a(arrayList, axis);
        this.a = new Axis[0];
        this.a = (Axis[]) arrayList.toArray(this.a);
    }

    /* access modifiers changed from: 0000 */
    public void b(Axis<?, ?> axis) {
        ArrayList arrayList = new ArrayList();
        a(arrayList, axis);
        arrayList.add(axis);
        this.a = new Axis[0];
        this.a = (Axis[]) arrayList.toArray(this.a);
    }

    /* access modifiers changed from: 0000 */
    public void c(Axis<?, ?> axis) {
        ArrayList arrayList = new ArrayList();
        a(arrayList, axis);
        this.a = new Axis[0];
        this.a = (Axis[]) arrayList.toArray(this.a);
    }

    private void a(List<Axis<?, ?>> list, Axis<?, ?> axis) {
        int i = 0;
        while (true) {
            Axis<?, ?>[] axisArr = this.a;
            if (i < axisArr.length) {
                if (axis != axisArr[i]) {
                    list.add(axisArr[i]);
                }
                i++;
            } else {
                return;
            }
        }
    }
}
