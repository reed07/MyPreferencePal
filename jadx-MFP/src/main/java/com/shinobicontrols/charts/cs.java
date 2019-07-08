package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class cs {
    Map<Class<? extends Series>, a> a = new HashMap();

    static class a extends ArrayList<Series<?>> {
        private static final long serialVersionUID = -6971678076805971571L;

        a() {
        }

        /* access modifiers changed from: 0000 */
        public void a(Series<?> series, Series<?> series2) {
            int indexOf = indexOf(series);
            if (indexOf != -1) {
                remove(series);
                add(indexOf, series2);
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(Series<?> series) {
            a(series, null);
        }

        /* access modifiers changed from: 0000 */
        public boolean a() {
            return indexOf(null) != -1;
        }
    }

    cs() {
    }

    /* access modifiers changed from: 0000 */
    public int a(Series<?> series) {
        a aVar;
        Class cls = series.getClass();
        if (this.a.containsKey(cls)) {
            aVar = (a) this.a.get(cls);
            if (aVar.a()) {
                b(aVar, series);
            } else {
                a(aVar, series);
            }
        } else {
            a aVar2 = new a();
            this.a.put(cls, aVar2);
            a(aVar2, series);
            aVar = aVar2;
        }
        return c(aVar, series);
    }

    private void a(a aVar, Series<?> series) {
        aVar.add(series);
    }

    private void b(a aVar, Series<?> series) {
        aVar.a(null, series);
    }

    /* access modifiers changed from: 0000 */
    public void b(Series<?> series) {
        Class cls = series.getClass();
        if (this.a.containsKey(cls)) {
            a aVar = (a) this.a.get(cls);
            if (aVar.contains(series)) {
                aVar.a(series);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int c(Series<?> series) {
        Class cls = series.getClass();
        if (this.a.containsKey(cls)) {
            return c((a) this.a.get(cls), series);
        }
        return -1;
    }

    private int c(a aVar, Series<?> series) {
        return aVar.indexOf(series);
    }
}
