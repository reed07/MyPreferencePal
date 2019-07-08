package com.facebook.ads.internal.view.h;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.j;

public class a extends LinearLayout {
    public a(Context context, e eVar, j jVar, AdOptionsView adOptionsView) {
        super(context);
        TextView textView = new TextView(getContext());
        jVar.a(textView);
        textView.setText(eVar.a("advertiser_name"));
        textView.setEllipsize(TruncateAt.END);
        textView.setMaxLines(1);
        TextView textView2 = new TextView(getContext());
        jVar.b(textView2);
        textView2.setText(eVar.a("social_context"));
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        LayoutParams layoutParams = new LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        linearLayout.addView(textView, layoutParams);
        linearLayout.addView(adOptionsView, new LayoutParams(-2, -2));
        setOrientation(1);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        addView(linearLayout, layoutParams2);
        addView(textView2, layoutParams2);
    }
}
