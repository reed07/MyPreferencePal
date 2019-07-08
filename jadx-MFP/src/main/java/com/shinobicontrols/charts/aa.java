package com.shinobicontrols.charts;

abstract class aa {
    boolean a;
    final boolean b;

    static class a extends aa {
        /* access modifiers changed from: 0000 */
        public DataPoint<?, ?> a(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z) {
            return dataPoint2;
        }

        a() {
            super(false);
        }

        /* access modifiers changed from: 0000 */
        public DataPoint<?, ?> b(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z) {
            return new DataPoint<>(dataPoint2.getX(), dataPoint.getY());
        }
    }

    static class b extends aa {
        /* access modifiers changed from: 0000 */
        public DataPoint<?, ?> a(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z) {
            return dataPoint2;
        }

        b() {
            super(false);
        }

        /* access modifiers changed from: 0000 */
        public DataPoint<?, ?> b(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z) {
            return new DataPoint<>(dataPoint.getX(), dataPoint2.getY());
        }
    }

    static class c extends aa {
        /* access modifiers changed from: 0000 */
        public DataPoint<?, ?> a(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z) {
            return z ? dataPoint3 : dataPoint2;
        }

        /* access modifiers changed from: 0000 */
        public DataPoint<?, ?> b(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z) {
            return z ? dataPoint3 : dataPoint2;
        }

        c() {
            super(true);
        }
    }

    static class d extends aa {
        /* access modifiers changed from: 0000 */
        public DataPoint<?, ?> a(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z) {
            return dataPoint2;
        }

        /* access modifiers changed from: 0000 */
        public DataPoint<?, ?> b(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z) {
            return dataPoint;
        }

        d() {
            super(false);
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract DataPoint<?, ?> a(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z);

    /* access modifiers changed from: 0000 */
    public abstract DataPoint<?, ?> b(DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3, boolean z);

    private aa(boolean z) {
        this.a = false;
        this.b = z;
    }
}
