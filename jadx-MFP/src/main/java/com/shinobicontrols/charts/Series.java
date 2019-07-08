package com.shinobicontrols.charts;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.shinobicontrols.charts.PropertyChangedEvent.Handler;
import com.shinobicontrols.charts.SeriesStyle;
import java.util.HashMap;
import java.util.Map;

public abstract class Series<T extends SeriesStyle> {
    private final al a = new al();
    private final Map<Axis<?, ?>, am> b = new HashMap();
    private am c;
    private final c d = new c<>(this);
    private final b e = new b<>();
    private final d f = new d<>(this);
    private am g;
    private am h;
    private boolean i = true;
    private String j;
    DataAdapter<?, ?> m;
    final ao n = new ao();
    v o;
    cu p;
    T q;
    T r;
    SelectionMode s = SelectionMode.NONE;
    cr t;
    SeriesAnimation u;
    SeriesAnimation v;
    SeriesAnimation w;
    boolean x;
    boolean y;
    final ae z = ae.a(this);

    public enum Orientation {
        HORIZONTAL(0),
        VERTICAL(1);
        
        private int a;

        private Orientation(int i) {
            this.a = i;
        }

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.a;
        }
    }

    public enum SelectionMode {
        NONE,
        SERIES,
        POINT_SINGLE,
        POINT_MULTIPLE
    }

    static class a {
        private double a = Double.MAX_VALUE;
        private InternalDataPoint b = null;
        private final Series<?> c;
        private bz d = null;

        public a(Series<?> series) {
            this.c = series;
        }

        public double a() {
            return this.a;
        }

        public void a(double d2) {
            this.a = d2;
        }

        public InternalDataPoint b() {
            return this.b;
        }

        public void a(InternalDataPoint internalDataPoint) {
            this.b = internalDataPoint;
        }

        public Series<?> c() {
            return this.c;
        }

        public bz d() {
            return this.d;
        }

        public void a(bz bzVar) {
            this.d = bzVar;
        }

        public void e() {
            this.b = null;
        }

        public boolean a(a aVar) {
            return b(this) && (aVar == null || this.a < aVar.a());
        }

        public static boolean b(a aVar) {
            boolean z = false;
            if (aVar == null) {
                return false;
            }
            if (aVar.b != null) {
                z = true;
            }
            return z;
        }
    }

    private class b implements Handler {
        private b() {
        }

        public void onPropertyChanged() {
            Series.this.a_();
        }
    }

    private class c implements a {
        private final Series<?> b;

        public c(Series<?> series) {
            this.b = series;
        }

        public final void a() {
            this.b.q();
        }
    }

    private class d implements a {
        private final Series<?> b;

        public d(Series<?> series) {
            this.b = series;
        }

        public final void a() {
            this.b.a();
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract Drawable a(float f2);

    /* access modifiers changed from: 0000 */
    public void a(Canvas canvas, Rect rect) {
    }

    /* access modifiers changed from: 0000 */
    public void a(Axis<?, ?> axis) {
    }

    /* access modifiers changed from: 0000 */
    public abstract double b();

    /* access modifiers changed from: 0000 */
    public abstract T b(dd ddVar, int i2, boolean z2);

    /* access modifiers changed from: 0000 */
    public abstract T d();

    /* access modifiers changed from: 0000 */
    public abstract double h();

    public boolean isSelected() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public abstract void m();

    public void setSelected(boolean z2) {
    }

    Series() {
    }

    public final DataAdapter<?, ?> getDataAdapter() {
        return this.m;
    }

    public final void setDataAdapter(DataAdapter<?, ?> dataAdapter) {
        if (dataAdapter == null) {
            v vVar = this.o;
            if (vVar == null) {
                throw new IllegalArgumentException("Trying to set a null DataAdapter, DataAdapter cannot be null");
            }
            throw new IllegalArgumentException(vVar.getContext().getString(R.string.SeriesNullDataAdapter));
        }
        if (this.m != null) {
            this.c.a();
        }
        this.m = dataAdapter;
        this.c = this.m.a(this.d);
        m();
        r();
    }

    public boolean isPointSelected(int i2) {
        if (i2 < this.n.c.length && i2 >= 0) {
            return this.n.c[i2].h;
        }
        v vVar = this.o;
        cx.a(vVar != null ? vVar.getContext().getString(R.string.SeriesDataPointOutOfRange) : "Attempting to access data point out of range");
        return false;
    }

    public void setPointSelected(boolean z2, int i2) {
        if (i2 >= this.n.c.length || i2 < 0) {
            v vVar = this.o;
            cx.a(vVar != null ? vVar.getContext().getString(R.string.SeriesDataPointOutOfRange) : "Attempting to access data point out of range");
            return;
        }
        a(z2, i2);
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z2, int i2) {
        a(this.n.c[i2], z2);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(InternalDataPoint internalDataPoint, boolean z2) {
        boolean z3 = z2 != internalDataPoint.h;
        if (z3) {
            internalDataPoint.h = z2;
            v vVar = this.o;
            if (vVar != null) {
                vVar.a(this, internalDataPoint.i);
                this.p.a();
            }
        }
        return z3;
    }

    public T getStyle() {
        return this.q;
    }

    public final void setStyle(T t2) {
        if (t2 == null) {
            v vVar = this.o;
            if (vVar == null) {
                throw new IllegalArgumentException("Styles may not be null");
            }
            throw new IllegalArgumentException(vVar.getContext().getString(R.string.SeriesStyleIsNull));
        }
        a(t2);
    }

    private void a(T t2) {
        synchronized (x.a) {
            if (this.q != null) {
                this.g.a();
            }
            this.q = t2;
            if (this.q != null) {
                this.g = this.q.a((Handler) this.e);
                a_();
            }
        }
    }

    public T getSelectedStyle() {
        return this.r;
    }

    public final void setSelectedStyle(T t2) {
        if (t2 == null) {
            v vVar = this.o;
            if (vVar == null) {
                throw new IllegalArgumentException("Styles may not be null");
            }
            throw new IllegalArgumentException(vVar.getContext().getString(R.string.SeriesStyleIsNull));
        }
        b(t2);
    }

    private void b(T t2) {
        synchronized (x.a) {
            if (this.r != null) {
                this.h.a();
            }
            this.r = t2;
            if (this.r != null) {
                this.h = this.r.a((Handler) this.e);
                a_();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar) {
        this.o = vVar;
        m();
        r();
    }

    public final ShinobiChart getChart() {
        return this.o;
    }

    /* access modifiers changed from: 0000 */
    public void c(dd ddVar, int i2, boolean z2) {
        if (ddVar != null) {
            SeriesStyle b2 = b(ddVar, i2, false);
            SeriesStyle b3 = b(ddVar, i2, true);
            if (z2 || this.q == null) {
                this.q = d();
            }
            if (z2 || this.r == null) {
                this.r = d();
            }
            this.q.a(b2);
            this.r.a(b3);
            a(this.q);
            b(this.r);
        }
    }

    /* access modifiers changed from: 0000 */
    public void k() {
        m();
        r();
    }

    public final Axis<?, ?> getXAxis() {
        v vVar = this.o;
        if (vVar != null) {
            return vVar.getXAxisForSeries(this);
        }
        return null;
    }

    public final Axis<?, ?> getYAxis() {
        v vVar = this.o;
        if (vVar != null) {
            return vVar.getYAxisForSeries(this);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void a_() {
        this.p.a();
    }

    /* access modifiers changed from: 0000 */
    public void q() {
        m();
        r();
        v vVar = this.o;
        if (vVar != null && vVar.h != null) {
            this.o.h.b(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void r() {
        this.a.a(new di());
    }

    /* access modifiers changed from: 0000 */
    public am a(a aVar) {
        return this.a.a(di.a, (a) aVar);
    }

    /* access modifiers changed from: 0000 */
    public void a(cr crVar) {
        this.t = crVar;
    }

    public SelectionMode getSelectionMode() {
        return this.s;
    }

    public void setSelectionMode(SelectionMode selectionMode) {
        this.s = selectionMode;
    }

    public boolean isShownInLegend() {
        return this.i;
    }

    public void setShownInLegend(boolean z2) {
        this.i = z2;
    }

    public String getTitle() {
        return this.j;
    }

    public void setTitle(String str) {
        this.j = str;
    }

    /* access modifiers changed from: 0000 */
    public boolean s() {
        DataAdapter<?, ?> dataAdapter = this.m;
        return dataAdapter != null && !dataAdapter.isEmpty();
    }

    public boolean isHidden() {
        return this.y;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0031, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setHidden(boolean r3) {
        /*
            r2 = this;
            java.lang.Object r0 = com.shinobicontrols.charts.x.a
            monitor-enter(r0)
            boolean r1 = r2.x     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003a
            com.shinobicontrols.charts.v r1 = r2.o     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003a
            if (r3 == 0) goto L_0x0022
            boolean r3 = r2.y     // Catch:{ all -> 0x003f }
            if (r3 != 0) goto L_0x0020
            com.shinobicontrols.charts.SeriesAnimation r3 = r2.u     // Catch:{ all -> 0x003f }
            com.shinobicontrols.charts.SeriesAnimation r1 = r2.w     // Catch:{ all -> 0x003f }
            if (r3 != r1) goto L_0x0018
            goto L_0x0020
        L_0x0018:
            com.shinobicontrols.charts.v r3 = r2.o     // Catch:{ all -> 0x003f }
            com.shinobicontrols.charts.cp r3 = r3.j     // Catch:{ all -> 0x003f }
            r3.a(r2)     // Catch:{ all -> 0x003f }
            goto L_0x003d
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0022:
            boolean r3 = r2.y     // Catch:{ all -> 0x003f }
            if (r3 != 0) goto L_0x002a
            com.shinobicontrols.charts.SeriesAnimation r3 = r2.u     // Catch:{ all -> 0x003f }
            if (r3 == 0) goto L_0x0030
        L_0x002a:
            com.shinobicontrols.charts.SeriesAnimation r3 = r2.u     // Catch:{ all -> 0x003f }
            com.shinobicontrols.charts.SeriesAnimation r1 = r2.v     // Catch:{ all -> 0x003f }
            if (r3 != r1) goto L_0x0032
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0032:
            com.shinobicontrols.charts.v r3 = r2.o     // Catch:{ all -> 0x003f }
            com.shinobicontrols.charts.cp r3 = r3.j     // Catch:{ all -> 0x003f }
            r3.b(r2)     // Catch:{ all -> 0x003f }
            goto L_0x003d
        L_0x003a:
            r2.a(r3)     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.Series.setHidden(boolean):void");
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z2) {
        this.y = z2;
        v vVar = this.o;
        if (vVar != null) {
            vVar.b.invalidate();
            this.p.a();
            this.o.b.f();
        }
        r();
    }

    public boolean isAnimationEnabled() {
        return this.x;
    }

    public void enableAnimation(boolean z2) {
        this.x = z2;
    }

    public SeriesAnimation getEntryAnimation() {
        return this.v;
    }

    public void setEntryAnimation(SeriesAnimation seriesAnimation) {
        if (seriesAnimation != null) {
            this.v = seriesAnimation;
            return;
        }
        throw new IllegalArgumentException("Series entry animations may not be null");
    }

    public SeriesAnimation getExitAnimation() {
        return this.w;
    }

    public void setExitAnimation(SeriesAnimation seriesAnimation) {
        if (seriesAnimation != null) {
            this.w = seriesAnimation;
            return;
        }
        throw new IllegalArgumentException("Series exit animations may not be null");
    }

    public boolean isAnimating() {
        return this.u != null;
    }

    /* access modifiers changed from: 0000 */
    public void d(Axis<?, ?> axis) {
        this.b.put(axis, axis.a((a) this.f));
    }

    /* access modifiers changed from: 0000 */
    public void e(Axis<?, ?> axis) {
        am amVar = (am) this.b.get(axis);
        if (amVar != null) {
            amVar.a();
            this.b.remove(axis);
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        m();
    }
}
