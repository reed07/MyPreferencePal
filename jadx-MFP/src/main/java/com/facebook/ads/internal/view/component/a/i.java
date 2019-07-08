package com.facebook.ads.internal.view.component.a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class i extends c {
    private static final int c = Resources.getSystem().getDisplayMetrics().widthPixels;

    public i(e eVar, h hVar) {
        super(eVar, hVar, true);
        RelativeLayout relativeLayout = new RelativeLayout(eVar.a());
        LayoutParams layoutParams = new LayoutParams(c / 2, -2);
        layoutParams.addRule(12);
        GradientDrawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
        gradientDrawable.setCornerRadius(BitmapDescriptorFactory.HUE_RED);
        gradientDrawable.setGradientType(0);
        x.a((View) relativeLayout, (Drawable) gradientDrawable);
        new LinearLayout.LayoutParams(-2, -2).setMargins(a, 0, 0, 0);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2);
        layoutParams2.setMargins(0, 0, 0, 0);
        layoutParams2.weight = 1.0f;
        if (eVar.h() != null) {
            LayoutParams layoutParams3 = new LayoutParams(-1, -2);
            layoutParams3.setMargins(0, 0, 0, 0);
            relativeLayout.addView(eVar.h(), layoutParams3);
        }
        View d = eVar.d();
        LayoutParams layoutParams4 = new LayoutParams(c / 2, -1);
        layoutParams4.addRule(13);
        layoutParams4.addRule(9);
        addView(d, layoutParams4);
        addView(relativeLayout, layoutParams);
        if (eVar.i() != null) {
            LayoutParams layoutParams5 = new LayoutParams(b, b);
            layoutParams5.addRule(12);
            layoutParams5.setMargins(a, a + eVar.j(), a, a);
            addView(eVar.i(), layoutParams5);
        }
    }

    public boolean a() {
        return true;
    }

    public int getExactMediaWidthIfAvailable() {
        return c / 2;
    }
}
