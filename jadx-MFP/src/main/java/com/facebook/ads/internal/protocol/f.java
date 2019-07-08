package com.facebook.ads.internal.protocol;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import java.util.HashMap;
import java.util.Map;

public class f {
    private static final Map<d, e> a = new HashMap();

    static {
        a.put(d.RECTANGLE_HEIGHT_250, e.WEBVIEW_BANNER_250);
        a.put(d.BANNER_HEIGHT_90, e.WEBVIEW_BANNER_90);
        a.put(d.BANNER_HEIGHT_50, e.WEBVIEW_BANNER_50);
    }

    public static e a(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
        int i2 = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        return i >= 640 && i2 >= 640 ? e.WEBVIEW_INTERSTITIAL_TABLET : i2 > i ? e.WEBVIEW_INTERSTITIAL_VERTICAL : e.WEBVIEW_INTERSTITIAL_HORIZONTAL;
    }

    public static e a(d dVar) {
        e eVar = (e) a.get(dVar);
        return eVar == null ? e.WEBVIEW_BANNER_LEGACY : eVar;
    }

    public static void a(DisplayMetrics displayMetrics, View view, d dVar) {
        LayoutParams layoutParams = new LayoutParams(((int) (((float) displayMetrics.widthPixels) / displayMetrics.density)) >= dVar.a() ? displayMetrics.widthPixels : (int) Math.ceil((double) (((float) dVar.a()) * displayMetrics.density)), (int) Math.ceil((double) (((float) dVar.b()) * displayMetrics.density)));
        layoutParams.addRule(14, -1);
        view.setLayoutParams(layoutParams);
    }
}
