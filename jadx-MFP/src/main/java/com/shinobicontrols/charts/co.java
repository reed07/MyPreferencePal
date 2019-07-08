package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.List;

class co {
    private final b a = new b();
    private final c b = new c();
    private final List<Series<?>> c;
    private final List<Series<?>> d;
    private final List<Series<?>> e;
    private final ct f;
    private final v g;

    private static class a extends b {
        private final List<Series<?>> b;
        private final ct c;

        public a(v vVar, List<Series<?>> list, ct ctVar) {
            super(vVar);
            this.b = list;
            this.c = ctVar;
        }

        public void a() {
            for (Series series : this.c.b()) {
                series.z.a();
            }
        }

        public void b() {
            synchronized (x.a) {
                for (Series series : this.c.b()) {
                    series.u = null;
                    series.a(true);
                }
            }
            this.c.a();
            d();
        }

        private void d() {
            b bVar = new b();
            c cVar = new c();
            List<Series> b2 = this.c.b();
            for (Series series : b2) {
                series.v.a(false);
                series.u = series.v;
                synchronized (x.a) {
                    series.a(false);
                }
                cVar.a((Animation) series.u);
            }
            bVar.a((Animation) cVar);
            bVar.a((a) new c(this.a, this.b, b2));
            bVar.a();
        }
    }

    private static abstract class b implements a {
        final v a;

        public void a() {
        }

        public void b() {
        }

        public void c() {
        }

        public b(v vVar) {
            this.a = vVar;
        }

        public void a(Animation animation) {
            synchronized (x.a) {
                this.a.b.f();
            }
        }
    }

    private static class c extends b {
        private final List<Series<?>> b;
        private final List<Series<?>> c;

        public c(v vVar, List<Series<?>> list, List<Series<?>> list2) {
            super(vVar);
            this.b = list;
            this.c = list2;
        }

        public void b() {
            synchronized (x.a) {
                for (Series series : this.c) {
                    series.u = null;
                    series.p.a();
                    series.z.a();
                }
            }
            for (Series c2 : this.b) {
                this.a.c(c2);
            }
        }
    }

    static co a(Series<?> series, List<Series<?>> list, v vVar, a aVar) {
        List a2 = cj.a(series);
        List a3 = a(a2, list);
        List b2 = cj.b(series);
        co coVar = new co(a2, a3, b2, ct.a(a3, b2, vVar, aVar), vVar);
        return coVar;
    }

    private static List<Series<?>> a(List<Series<?>> list, List<Series<?>> list2) {
        ArrayList arrayList = new ArrayList(list2);
        arrayList.retainAll(list);
        return arrayList;
    }

    private co(List<Series<?>> list, List<Series<?>> list2, List<Series<?>> list3, ct ctVar, v vVar) {
        this.c = list;
        this.d = list2;
        this.e = list3;
        this.f = ctVar;
        this.g = vVar;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        for (Series series : this.e) {
            series.w.a(true);
            series.u = series.w;
            series.p.a();
            this.b.a((Animation) series.u);
        }
        this.a.a((Animation) this.b);
        this.a.a((a) new a(this.g, this.d, this.f));
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.a.a();
    }

    /* access modifiers changed from: 0000 */
    public List<Series<?>> c() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        for (Series isAnimating : this.c) {
            if (isAnimating.isAnimating()) {
                return true;
            }
        }
        return false;
    }
}
