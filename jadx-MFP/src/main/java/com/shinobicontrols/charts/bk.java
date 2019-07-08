package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class bk {
    static float a(float f, float f2, float f3) {
        while (f < f2) {
            f += 6.2831855f;
        }
        while (f > f3) {
            f -= 6.2831855f;
        }
        return f;
    }

    static float a(float f) {
        return a(f, BitmapDescriptorFactory.HUE_RED, 6.2831855f);
    }

    static float b(float f) {
        return a(f, -3.1415927f, 3.1415927f);
    }
}
