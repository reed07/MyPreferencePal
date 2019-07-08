package com.facebook.ads.internal.view.component.a;

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

public class b extends c {
    public b(e eVar, h hVar, boolean z) {
        super(eVar, hVar, true);
        RelativeLayout relativeLayout = new RelativeLayout(eVar.a());
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12);
        GradientDrawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
        gradientDrawable.setCornerRadius(BitmapDescriptorFactory.HUE_RED);
        gradientDrawable.setGradientType(0);
        x.a((View) relativeLayout, (Drawable) gradientDrawable);
        LinearLayout linearLayout = new LinearLayout(eVar.a());
        linearLayout.setOrientation(z ^ true ? 1 : 0);
        linearLayout.setGravity(80);
        x.a((View) linearLayout);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.setMargins(a, 0, a, eVar.h() == null ? a : a / 2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(z ? -2 : -1, -2);
        layoutParams3.setMargins(z ? a : 0, z ? 0 : a, 0, 0);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(z ? 0 : -1, -2);
        layoutParams4.setMargins(0, 0, 0, 0);
        layoutParams4.weight = 1.0f;
        linearLayout.addView(getTitleDescContainer(), layoutParams4);
        linearLayout.addView(getCtaButton(), layoutParams3);
        relativeLayout.addView(linearLayout, layoutParams2);
        if (eVar.h() != null) {
            LayoutParams layoutParams5 = new LayoutParams(-1, -2);
            layoutParams5.setMargins(0, 0, 0, 0);
            layoutParams5.addRule(3, linearLayout.getId());
            relativeLayout.addView(eVar.h(), layoutParams5);
        }
        addView(eVar.d(), new LayoutParams(-1, -1));
        addView(relativeLayout, layoutParams);
        if (eVar.i() != null) {
            LayoutParams layoutParams6 = new LayoutParams(b, b);
            layoutParams6.addRule(10);
            layoutParams6.addRule(11);
            layoutParams6.setMargins(a, a + eVar.j(), a, a);
            addView(eVar.i(), layoutParams6);
        }
    }

    public boolean a() {
        return true;
    }
}
