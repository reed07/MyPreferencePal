package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;
import com.facebook.ads.internal.w.c.c;

public class f extends LinearLayout {
    private static final int a = ((int) (x.b * 16.0f));
    private static final int b = ((int) (x.b * 12.0f));
    private static final int c = ((int) (x.b * 12.0f));
    private static final int d = ((int) (x.b * 16.0f));
    private boolean e = false;
    private final ImageView f;
    private final TextView g;

    public f(Context context) {
        super(context);
        setOrientation(0);
        int i = a;
        int i2 = b;
        setPadding(i, i2, i, i2);
        this.f = new ImageView(getContext());
        int i3 = d;
        LayoutParams layoutParams = new LayoutParams(i3, i3);
        layoutParams.gravity = 17;
        this.g = new TextView(getContext());
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        addView(this.f, layoutParams);
        addView(this.g, layoutParams2);
        b();
    }

    private void b() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(this.e ? -13272859 : -1315344);
        gradientDrawable.setCornerRadius(50.0f);
        x.a((View) this, (Drawable) gradientDrawable);
        x.a(this.g, false, 14);
        int i = this.e ? -1 : -10459280;
        this.g.setTextColor(i);
        this.f.setColorFilter(i);
    }

    public void a() {
        setSelected(!this.e);
    }

    public void a(String str, @Nullable b bVar) {
        this.g.setText(str);
        if (bVar != null) {
            this.f.setImageBitmap(c.a(bVar));
            this.f.setVisibility(0);
            this.g.setPadding(c, 0, 0, 0);
        } else {
            this.f.setVisibility(8);
            this.g.setPadding(0, 0, 0, 0);
        }
        b();
    }

    public void setSelected(boolean z) {
        this.e = z;
        b();
    }
}
