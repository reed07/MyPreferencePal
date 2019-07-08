package com.shinobicontrols.charts;

import com.shinobicontrols.charts.PropertyChangedEvent.Handler;

public abstract class SeriesStyle {
    private final al a = new al();
    final dj<Boolean> n = new dj<>(Boolean.valueOf(false));

    public enum FillStyle {
        NONE,
        FLAT,
        GRADIENT
    }

    SeriesStyle() {
    }

    /* access modifiers changed from: 0000 */
    public void a(SeriesStyle seriesStyle) {
        if (seriesStyle != null) {
            this.n.b(Boolean.valueOf(seriesStyle.c()));
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        return ((Boolean) this.n.a).booleanValue();
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        synchronized (x.a) {
            this.n.a(Boolean.valueOf(z));
            d();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void d() {
        this.a.a(new PropertyChangedEvent());
    }

    /* access modifiers changed from: 0000 */
    public am a(Handler handler) {
        return this.a.a(PropertyChangedEvent.a, (a) handler);
    }
}
