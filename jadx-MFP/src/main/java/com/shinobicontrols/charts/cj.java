package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class cj {
    private static cj a = new cj() {
        /* access modifiers changed from: 0000 */
        public List<Series<?>> c(Series<?> series) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(series);
            return arrayList;
        }
    };
    private static cj b = new cj() {
        /* access modifiers changed from: 0000 */
        public List<Series<?>> c(Series<?> series) {
            ArrayList arrayList = new ArrayList();
            for (Series add : series.t.c(series)) {
                arrayList.add(add);
            }
            return arrayList;
        }
    };

    /* access modifiers changed from: 0000 */
    public abstract List<Series<?>> c(Series<?> series);

    static List<Series<?>> a(Series<?> series) {
        if (series.t == null || !d(series)) {
            return a.c(series);
        }
        return b.c(series);
    }

    static List<Series<?>> b(Series<?> series) {
        return a(a(series));
    }

    private cj() {
    }

    private static boolean d(Series<?> series) {
        boolean z = false;
        if (!(series instanceof CartesianSeries)) {
            return false;
        }
        if (((CartesianSeries) series).c != null) {
            z = true;
        }
        return z;
    }

    private static List<Series<?>> a(List<Series<?>> list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (e((Series) it.next())) {
                it.remove();
            }
        }
        return list;
    }

    private static boolean e(Series<?> series) {
        return series.o == null || series.y || series.isAnimating();
    }
}
