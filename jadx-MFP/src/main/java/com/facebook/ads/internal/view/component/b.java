package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.view.v;
import com.facebook.ads.internal.view.w;

public class b extends LinearLayout {
    private v a = new v(getContext(), 2);
    private int b;

    public b(Context context, e eVar, j jVar, AdOptionsView adOptionsView) {
        super(context);
        setOrientation(1);
        setVerticalGravity(16);
        this.a.setMinTextSize((float) (jVar.h() - 2));
        this.a.setText(eVar.a("headline"));
        jVar.a((TextView) this.a);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        LayoutParams layoutParams = new LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.a, layoutParams);
        linearLayout.addView(adOptionsView, new LayoutParams(-2, -2));
        int i = 21;
        if (eVar.a("headline") != null) {
            i = Math.min(eVar.a("headline").length(), 21);
        }
        this.b = i;
        addView(linearLayout, new LayoutParams(-1, -2));
        LinearLayout linearLayout2 = new LinearLayout(context);
        w wVar = new w(context);
        wVar.setText(eVar.a("social_context"));
        jVar.b((TextView) wVar);
        linearLayout2.addView(wVar);
        addView(linearLayout2);
    }

    public int getMinVisibleTitleCharacters() {
        return this.b;
    }

    public TextView getTitleTextView() {
        return this.a;
    }
}
