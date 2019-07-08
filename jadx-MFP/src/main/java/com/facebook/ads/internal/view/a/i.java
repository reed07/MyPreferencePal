package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;
import com.facebook.ads.internal.w.c.c;

public class i extends LinearLayout {
    public static final LayoutParams a = new LayoutParams(-1, -2);
    private static final int b = ((int) (x.b * 8.0f));
    private static final int c = ((int) (((double) x.b) * 14.5d));
    private static final int d = ((int) (x.b * 20.0f));
    private final LinearLayout e;
    private final ImageView f;
    private final TextView g;

    public i(Context context) {
        super(context);
        this.f = new ImageView(context);
        this.f.setColorFilter(-10459280);
        int i = d;
        LayoutParams layoutParams = new LayoutParams(i, i);
        layoutParams.gravity = 16;
        this.f.setLayoutParams(layoutParams);
        this.e = new LinearLayout(context);
        this.e.setOrientation(1);
        this.e.setPadding(b * 2, 0, 0, 0);
        this.e.setLayoutParams(a);
        this.g = new TextView(context);
        x.a(this.g, true, 16);
        this.g.setTextColor(-14934495);
        this.e.addView(this.g, a);
        setOrientation(0);
        addView(this.f);
        addView(this.e);
    }

    public void a(b bVar, String str, String str2) {
        int i;
        this.f.setImageBitmap(c.a(bVar));
        this.g.setText(str);
        if (!TextUtils.isEmpty(str2)) {
            TextView textView = new TextView(getContext());
            x.a(textView, false, 14);
            textView.setTextColor(-10459280);
            textView.setText(str2);
            this.e.addView(textView, a);
            i = b;
        } else {
            i = c;
        }
        setPadding(0, i, 0, i);
    }
}
