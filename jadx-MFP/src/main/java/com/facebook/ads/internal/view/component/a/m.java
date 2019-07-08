package com.facebook.ads.internal.view.component.a;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.view.component.a;
import com.facebook.ads.internal.view.component.a.C0016a;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;
import com.facebook.ads.internal.w.c.c;
import java.util.HashMap;
import java.util.Map;

public class m extends FrameLayout {
    private static final int a = ((int) (x.b * 21.0f));
    private static final int b = ((int) (x.b * 8.0f));
    private static final int c = ((int) (x.b * 3.0f));
    private final a d;

    public m(e eVar, String str, h hVar, C0016a aVar) {
        super(eVar.a());
        LinearLayout linearLayout = new LinearLayout(eVar.a());
        addView(linearLayout, new LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        linearLayout.setClickable(false);
        ImageView imageView = new ImageView(eVar.a());
        imageView.setImageBitmap(c.a(b.BACK_ARROW));
        imageView.setRotation(90.0f);
        imageView.setClickable(false);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setShape(1);
        imageView.setBackgroundDrawable(gradientDrawable);
        int i = a;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        layoutParams.bottomMargin = b;
        int i2 = c;
        imageView.setPadding(i2, i2, i2, i2);
        layoutParams.gravity = 1;
        linearLayout.addView(imageView, layoutParams);
        TextView textView = new TextView(eVar.a());
        textView.setTextSize(14.0f);
        textView.setText(str);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        textView.setTextColor(hVar.a(true));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 1;
        layoutParams2.bottomMargin = b;
        linearLayout.addView(textView, layoutParams2);
        textView.setClickable(false);
        a aVar2 = new a(eVar.a(), true, false, "com.facebook.ads.interstitial.clicked", null, eVar.b(), eVar.c(), eVar.e(), eVar.f());
        this.d = aVar2;
        this.d.a(((l) eVar.g().d().get(0)).b(), eVar.g().c(), (Map<String, String>) new HashMap<String,String>(), aVar);
        addView(this.d, new LayoutParams(-1, -1));
    }

    public boolean performClick() {
        return this.d.performClick();
    }
}
