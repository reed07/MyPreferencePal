package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.view.component.a.e;
import com.facebook.ads.internal.view.component.j;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class a extends b {
    private static final int c = ((int) (x.b * 12.0f));

    a(e eVar, h hVar, String str, com.facebook.ads.internal.view.e.a.a aVar) {
        super(eVar, hVar, true, str, aVar);
    }

    /* access modifiers changed from: protected */
    public void a(Context context) {
        j titleDescContainer = getTitleDescContainer();
        titleDescContainer.setAlignment(3);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(8, getMediaContainer().getId());
        titleDescContainer.setLayoutParams(layoutParams);
        int i = c;
        titleDescContainer.setPadding(i, i, i, i);
        GradientDrawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(BitmapDescriptorFactory.HUE_RED);
        x.a((View) titleDescContainer, (Drawable) gradientDrawable);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.addRule(3, getMediaContainer().getId());
        getCtaButton().setLayoutParams(layoutParams2);
        addView(getMediaContainer());
        addView(titleDescContainer);
        addView(getCtaButton());
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return false;
    }
}
