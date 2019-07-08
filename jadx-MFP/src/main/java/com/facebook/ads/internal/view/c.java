package com.facebook.ads.internal.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.t.f;
import com.facebook.ads.internal.w.b.j;
import com.facebook.ads.internal.w.b.x;

public class c extends f {
    public static final int a = (((int) x.b) * 500);
    private static final int b = ((int) (x.b * 500.0f));
    private static final int c = ((int) (x.b * 4.0f));
    private static final int d = ((int) (x.b * 8.0f));
    private static final int e = ((int) (x.b * 8.0f));
    private static final int f = ((int) (x.b * 4.0f));
    private static final int g = ((int) x.b);
    private static final int h = ((int) (x.b * 4.0f));
    private static final int i = ((int) (((double) x.b) * 0.5d));
    private final TextView j;
    private final TextView k;
    private final TextView l;
    private final RelativeLayout m;
    private final LinearLayout n;
    private final RelativeLayout o;
    private final t p;

    public c(Context context) {
        super(context);
        this.j = new TextView(context);
        this.k = new TextView(context);
        this.l = new TextView(context);
        this.m = new RelativeLayout(context);
        this.n = new LinearLayout(context);
        this.o = new RelativeLayout(context);
        this.p = new t(context);
        setLayoutParams(new LayoutParams(-2, -1));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius((float) h);
        gradientDrawable.setStroke(1, -10459280);
        setBackgroundDrawable(gradientDrawable);
        int i2 = i;
        setPadding(i2, i2, i2, i2);
        this.n.setOrientation(1);
        x.a((View) this.n);
        addView(this.n, new LinearLayout.LayoutParams(-2, -2));
        x.a((View) this.o);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.weight = 1.0f;
        layoutParams.gravity = 1;
        this.n.addView(this.o, layoutParams);
        this.p.setScaleType(ScaleType.FIT_XY);
        t tVar = this.p;
        int i3 = h;
        tVar.setRadius(new float[]{(float) i3, (float) i3, (float) i3, (float) i3, 0.0f, 0.0f, 0.0f, 0.0f});
        this.p.setAdjustViewBounds(true);
        j.a(this.p, j.INTERNAL_AD_MEDIA);
        x.a((View) this.p);
        this.o.addView(this.p, new LinearLayout.LayoutParams(-2, -1));
        x.a((View) this.o);
        TextView textView = this.l;
        int i4 = g;
        textView.setPadding(i4, i4, i4, i4);
        this.l.setTextSize(14.0f);
        x.a((View) this.l);
        x.a((View) this.m);
        this.j.setTextSize(14.0f);
        x.a((View) this.j);
        this.j.setMaxLines(1);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.setMargins(0, 0, 0, f);
        this.m.addView(this.j, layoutParams2);
        this.k.setTextSize(12.0f);
        x.a((View) this.k);
        this.k.setMaxLines(1);
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        layoutParams3.addRule(3, this.j.getId());
        layoutParams3.setMargins(0, 0, 0, f);
        this.m.addView(this.k, layoutParams3);
    }

    /* access modifiers changed from: protected */
    public View getAdContentsView() {
        return this.p;
    }

    public TextView getCTAButton() {
        return this.l;
    }

    public ImageView getImageCardView() {
        return this.p;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (MeasureSpec.getSize(i3) >= ((int) x.b) * a.r(getContext()) || MeasureSpec.getMode(i3) == 0) {
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(3, this.k.getId());
            x.b(this.l);
            this.m.addView(this.l, layoutParams);
            LayoutParams layoutParams2 = new LayoutParams(-1, -2);
            RelativeLayout relativeLayout = this.m;
            int i4 = e;
            relativeLayout.setPadding(i4, i4, i4, i4);
            x.b(this.m);
            this.n.addView(this.m, layoutParams2);
            this.p.setMaxWidth(b);
            this.j.setTextColor(-10459280);
            this.k.setTextColor(-10459280);
            this.l.setTextColor(-13272859);
            ((LinearLayout.LayoutParams) this.o.getLayoutParams()).gravity = 1;
        } else {
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            int i5 = d;
            int i6 = c;
            layoutParams3.setMargins(i5, i6, i5, i6);
            x.b(this.l);
            this.n.addView(this.l, layoutParams3);
            LayoutParams layoutParams4 = new LayoutParams(-2, -2);
            layoutParams4.addRule(8, this.p.getId());
            layoutParams4.addRule(5, this.p.getId());
            layoutParams4.addRule(7, this.p.getId());
            RelativeLayout relativeLayout2 = this.m;
            int i7 = e;
            relativeLayout2.setPadding(i7, 0, i7, 0);
            this.m.setBackgroundDrawable(new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-872415232, 0}));
            x.b(this.m);
            this.o.addView(this.m, layoutParams4);
            this.j.setTextColor(-1);
            this.k.setTextColor(-1);
            this.l.setTextColor(-13272859);
        }
        super.onMeasure(i2, i3);
    }

    public void setButtonText(String str) {
        if (str == null || str.trim().isEmpty()) {
            this.l.setVisibility(8);
            return;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
        this.l.setText(spannableString);
    }

    public void setSubtitle(String str) {
        if (str == null || str.trim().isEmpty()) {
            this.k.setVisibility(8);
        }
        this.k.setText(str);
    }

    public void setTitle(String str) {
        if (str == null || str.trim().isEmpty()) {
            this.j.setVisibility(8);
        }
        this.j.setText(str);
    }
}
