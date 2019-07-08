package com.shinobicontrols.charts;

abstract class ae {
    final Series<?> a;

    private static class a extends ae {
        public void a() {
        }

        public a(Series<?> series) {
            super(series);
        }
    }

    private static class b extends ae {
        public void a() {
            this.a.o.b.invalidate();
        }

        public b(Series<?> series) {
            super(series);
        }
    }

    public abstract void a();

    public static ae a(Series<?> series) {
        return series instanceof PieDonutSeries ? new b(series) : new a(series);
    }

    public ae(Series<?> series) {
        this.a = series;
    }
}
