package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.view.component.a.e;
import com.facebook.ads.internal.view.component.j;
import com.facebook.ads.internal.view.e.a.a;
import com.facebook.ads.internal.w.b.x;

public class d extends b {
    private static final int c = ((int) (x.b * 20.0f));
    private static final int d = ((int) (x.b * 16.0f));

    d(e eVar, h hVar, String str, a aVar) {
        super(eVar, hVar, false, str, aVar);
    }

    /* access modifiers changed from: protected */
    public void a(Context context) {
        j titleDescContainer = getTitleDescContainer();
        titleDescContainer.setAlignment(3);
        titleDescContainer.setLayoutParams(new LayoutParams(-1, -2));
        titleDescContainer.setPadding(0, 0, 0, c);
        getCtaButton().setLayoutParams(new LayoutParams(-1, -2));
        LinearLayout linearLayout = new LinearLayout(context);
        x.a((View) linearLayout, (Drawable) new ColorDrawable(-1));
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(3, getMediaContainer().getId());
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        int i = d;
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(titleDescContainer);
        linearLayout.addView(getCtaButton());
        addView(getMediaContainer());
        addView(linearLayout);
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return false;
    }
}
