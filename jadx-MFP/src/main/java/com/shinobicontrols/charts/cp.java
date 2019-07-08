package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.List;

class cp {
    private final v a;

    enum a {
        SHOW,
        HIDE,
        REMOVE,
        ADD,
        STACK_ID
    }

    cp(v vVar) {
        this.a = vVar;
    }

    /* access modifiers changed from: 0000 */
    public void a(Series<?> series) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(series);
        a((List<Series<?>>) arrayList);
    }

    /* access modifiers changed from: 0000 */
    public void a(List<Series<?>> list) {
        a(list, a.HIDE);
    }

    /* access modifiers changed from: 0000 */
    public void b(Series<?> series) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(series);
        b((List<Series<?>>) arrayList);
    }

    /* access modifiers changed from: 0000 */
    public void b(List<Series<?>> list) {
        a(list, a.SHOW);
    }

    /* access modifiers changed from: 0000 */
    public void a(Series<?> series, v vVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(series);
        a((List<Series<?>>) arrayList, a.ADD);
    }

    /* access modifiers changed from: 0000 */
    public void b(Series<?> series, v vVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(series);
        a((List<Series<?>>) arrayList, a.REMOVE);
    }

    private void a(List<Series<?>> list, a aVar) {
        ArrayList arrayList = new ArrayList();
        ArrayList<co> arrayList2 = new ArrayList<>();
        for (Series series : list) {
            if (!series.isAnimating() && !arrayList.contains(series)) {
                co a2 = co.a(series, list, this.a, aVar);
                if (!a2.d()) {
                    a2.a();
                    arrayList2.add(a2);
                }
                arrayList.addAll(a2.c());
            }
        }
        for (co b : arrayList2) {
            b.b();
        }
    }
}
