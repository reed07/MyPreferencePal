package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.w.b.x;

public class h extends LinearLayout {
    public h(Context context, e eVar, j jVar) {
        super(context);
        float f = x.b;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setVerticalGravity(16);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        float f2 = f * 15.0f;
        layoutParams.setMargins(Math.round(f2), Math.round(f2), Math.round(f2), Math.round(f2));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        String a = eVar.a("headline");
        TextView textView = new TextView(getContext());
        if (TextUtils.isEmpty(a)) {
            a = eVar.a("headline");
        }
        textView.setText(a);
        jVar.a(textView);
        textView.setEllipsize(TruncateAt.END);
        textView.setSingleLine(true);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(getContext());
        textView2.setText(eVar.l());
        jVar.b(textView2);
        textView2.setEllipsize(TruncateAt.END);
        textView2.setMaxLines(2);
        linearLayout.addView(textView2);
    }
}
