package com.facebook.ads.internal.view.component.a;

import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.w.b.x;

public class a {
    private static final int a = x.a.heightPixels;
    private static final int b = x.a.widthPixels;

    public static float a(l lVar) {
        int h = lVar.c().h();
        int i = lVar.c().i();
        if (i > 0) {
            return ((float) h) / ((float) i);
        }
        return -1.0f;
    }

    public static boolean a(double d) {
        return d < 0.9d;
    }

    public static boolean a(int i, int i2, double d) {
        if (i != 2) {
            if (!((a - i2) - ((x.a(16) + (com.facebook.ads.internal.view.component.a.a * 2)) + (f.a * 2)) < ((int) (((double) (b - (f.a * 2))) / d)))) {
                return false;
            }
        }
        return true;
    }
}
