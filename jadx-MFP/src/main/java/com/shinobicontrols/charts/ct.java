package com.shinobicontrols.charts;

import java.util.List;

abstract class ct {
    List<Series<?>> a;
    List<Series<?>> b;

    private static class a extends ct {
        private final v c;

        public a(List<Series<?>> list, List<Series<?>> list2, v vVar) {
            super(list, list2);
            this.c = vVar;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            synchronized (x.a) {
                for (Series series : this.a) {
                    if (!series.y) {
                        this.b.add(series);
                    }
                    this.c.a(series);
                }
            }
        }
    }

    private static class b extends ct {
        public b(List<Series<?>> list, List<Series<?>> list2) {
            super(list, list2);
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.b.removeAll(this.a);
        }
    }

    private static class c extends ct {
        private final v c;

        public c(List<Series<?>> list, List<Series<?>> list2, v vVar) {
            super(list, list2);
            this.c = vVar;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.b.removeAll(this.a);
            synchronized (x.a) {
                for (Series b : this.a) {
                    this.c.b(b);
                }
            }
        }
    }

    private static class d extends ct {
        public d(List<Series<?>> list, List<Series<?>> list2) {
            super(list, list2);
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.b.addAll(this.a);
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void a();

    static ct a(List<Series<?>> list, List<Series<?>> list2, v vVar, a aVar) {
        switch (aVar) {
            case HIDE:
                return new b(list, list2);
            case SHOW:
                return new d(list, list2);
            case ADD:
                return new a(list, list2, vVar);
            case REMOVE:
                return new c(list, list2, vVar);
            default:
                return null;
        }
    }

    public ct(List<Series<?>> list, List<Series<?>> list2) {
        this.a = list;
        this.b = list2;
    }

    /* access modifiers changed from: 0000 */
    public List<Series<?>> b() {
        return this.b;
    }
}
