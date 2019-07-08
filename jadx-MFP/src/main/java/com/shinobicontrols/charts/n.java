package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.Series;

abstract class n<T extends Series<?>> implements cu {
    private boolean a = true;
    protected final float[] j = {1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
    protected final T k;
    protected ao l;

    n(T t) {
        this.k = t;
    }

    public void a() {
        this.a = true;
    }

    public boolean b() {
        return this.a;
    }

    public void c() {
        this.a = false;
    }
}
