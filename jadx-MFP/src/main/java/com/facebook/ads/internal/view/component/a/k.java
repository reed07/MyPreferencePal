package com.facebook.ads.internal.view.component.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.component.g;
import com.facebook.ads.internal.view.component.j;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class k extends RelativeLayout {
    private final View a;
    private final g b;

    public k(Context context, View view) {
        super(context);
        this.a = view;
        this.b = new g(context);
        x.a((View) this.b);
    }

    public void a(int i) {
        this.a.setLayoutParams(new LayoutParams(-1, i));
    }

    public void a(@Nullable View view, @Nullable View view2) {
        a(view, view2, 8, null, false);
    }

    public void a(@Nullable View view, @Nullable View view2, int i, @Nullable j jVar, boolean z) {
        this.b.addView(this.a, new LayoutParams(-1, -2));
        if (view2 != null) {
            LayoutParams layoutParams = new LayoutParams(c.b, c.b);
            layoutParams.addRule(i, this.a.getId());
            layoutParams.addRule(7, this.a.getId());
            layoutParams.setMargins(c.a, c.a, c.a, c.a);
            this.b.addView(view2, layoutParams);
        }
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.addRule(8, this.a.getId());
        if (jVar != null) {
            if (z) {
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                jVar.setAlignment(3);
                layoutParams3.setMargins(c.a / 2, c.a / 2, c.a / 2, c.a / 2);
                linearLayout.addView(jVar, layoutParams3);
                GradientDrawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
                gradientDrawable.setCornerRadius(BitmapDescriptorFactory.HUE_RED);
                gradientDrawable.setGradientType(0);
                x.a((View) linearLayout, (Drawable) gradientDrawable);
            } else {
                LayoutParams layoutParams4 = new LayoutParams(-1, -2);
                layoutParams4.addRule(3, this.b.getId());
                layoutParams4.setMargins(0, c.a, 0, 0);
                jVar.setAlignment(17);
                addView(jVar, layoutParams4);
            }
        }
        if (view != null) {
            linearLayout.addView(view, new LayoutParams(-1, -2));
        }
        this.b.addView(linearLayout, layoutParams2);
        addView(this.b, new LayoutParams(-1, -2));
    }
}
