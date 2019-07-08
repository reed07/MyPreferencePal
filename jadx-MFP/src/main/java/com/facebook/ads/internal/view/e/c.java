package com.facebook.ads.internal.view.e;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.b.m;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.w.b.x;

public class c extends LinearLayout {
    public static final int a = ((int) (x.b * 32.0f));
    private static final int b = ((int) (x.b * 8.0f));
    private f c;
    private TextView d;
    private TextView e;

    public c(Context context) {
        super(context);
        a(context);
    }

    public void a(int i, int i2) {
        this.d.setTextColor(i);
        this.e.setTextColor(i2);
    }

    public void a(Context context) {
        setGravity(16);
        this.c = new f(context);
        this.c.setFullCircleCorners(true);
        int i = a;
        LayoutParams layoutParams = new LayoutParams(i, i);
        layoutParams.setMargins(0, 0, b, 0);
        addView(this.c, layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.d = new TextView(context);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        x.a(this.d, true, 16);
        this.d.setEllipsize(TruncateAt.END);
        this.d.setSingleLine(true);
        this.e = new TextView(context);
        x.a(this.e, false, 14);
        linearLayout.addView(this.d);
        linearLayout.addView(this.e);
        addView(linearLayout, layoutParams2);
    }

    public void setPageDetails(m mVar) {
        d dVar = new d((ImageView) this.c);
        int i = a;
        dVar.a(i, i);
        dVar.a(mVar.b());
        this.d.setText(mVar.a());
        this.e.setText(mVar.d());
    }
}
