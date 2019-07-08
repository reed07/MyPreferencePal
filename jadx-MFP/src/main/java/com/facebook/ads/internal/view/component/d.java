package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.view.w;
import com.facebook.ads.internal.w.b.x;

public class d extends LinearLayout {
    private MediaView a;
    private b b;
    private TextView c;
    private LinearLayout d = new LinearLayout(getContext());

    public d(Context context, e eVar, j jVar, MediaView mediaView, AdOptionsView adOptionsView, boolean z, int i) {
        e eVar2 = eVar;
        j jVar2 = jVar;
        super(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setVerticalGravity(16);
        setOrientation(1);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(displayMetrics.density * 15.0f), Math.round(displayMetrics.density * 15.0f), Math.round(displayMetrics.density * 15.0f), Math.round(displayMetrics.density * 15.0f));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        LayoutParams layoutParams2 = new LayoutParams(-1, 0);
        this.d.setOrientation(0);
        this.d.setGravity(16);
        layoutParams2.weight = 3.0f;
        this.d.setLayoutParams(layoutParams2);
        linearLayout.addView(this.d);
        this.a = mediaView;
        double d2 = 3.0d / ((double) ((z ? 1 : 0) + true));
        float f = (float) ((int) (((double) (i - 30)) * d2));
        LayoutParams layoutParams3 = new LayoutParams(Math.round(displayMetrics.density * f), Math.round(f * displayMetrics.density));
        layoutParams3.setMargins(0, 0, Math.round(displayMetrics.density * 15.0f), 0);
        this.a.setLayoutParams(layoutParams3);
        this.d.addView(this.a);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        this.d.addView(linearLayout2);
        this.b = new b(getContext(), eVar2, jVar2, adOptionsView);
        LayoutParams layoutParams4 = new LayoutParams(-2, -1);
        layoutParams4.setMargins(0, 0, Math.round(displayMetrics.density * 15.0f), 0);
        layoutParams4.weight = 0.5f;
        this.b.setLayoutParams(layoutParams4);
        linearLayout2.addView(this.b);
        this.c = new TextView(getContext());
        this.c.setPadding(Math.round(displayMetrics.density * 6.0f), Math.round(displayMetrics.density * 6.0f), Math.round(displayMetrics.density * 6.0f), Math.round(displayMetrics.density * 6.0f));
        this.c.setText(eVar2.a("call_to_action"));
        this.c.setTextColor(jVar.f());
        this.c.setTextSize(14.0f);
        this.c.setTypeface(jVar.a(), 1);
        this.c.setMaxLines(2);
        this.c.setEllipsize(TruncateAt.END);
        this.c.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(jVar.e());
        gradientDrawable.setCornerRadius(displayMetrics.density * 5.0f);
        gradientDrawable.setStroke(1, jVar.g());
        x.a((View) this.c, (Drawable) gradientDrawable);
        LayoutParams layoutParams5 = new LayoutParams(-2, -2);
        layoutParams5.weight = 0.25f;
        this.c.setLayoutParams(layoutParams5);
        if (!eVar.h()) {
            this.c.setVisibility(4);
        }
        linearLayout2.addView(this.c);
        if (z) {
            w wVar = new w(getContext());
            wVar.setText(eVar.l());
            jVar2.b((TextView) wVar);
            wVar.setMinTextSize((float) (jVar.i() - 1));
            LayoutParams layoutParams6 = new LayoutParams(-1, 0);
            layoutParams6.weight = 1.0f;
            wVar.setLayoutParams(layoutParams6);
            wVar.setGravity(80);
            linearLayout.addView(wVar);
        }
    }

    public TextView getCallToActionView() {
        return this.c;
    }

    public MediaView getIconView() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        TextView titleTextView = this.b.getTitleTextView();
        if (titleTextView.getLayout().getLineEnd(titleTextView.getLineCount() - 1) < this.b.getMinVisibleTitleCharacters()) {
            this.d.removeView(this.a);
            super.onMeasure(i, i2);
        }
    }
}
