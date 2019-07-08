package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.b.e.a;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.a.b;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.c;

public class k extends LinearLayout {
    private static final int a = ((int) (x.b * 14.0f));
    private static final int b = ((int) (x.b * 8.0f));
    private static final int c = ((int) (x.b * 10.0f));
    private static final int d = ((int) (x.b * 8.0f));
    private static final int e = ((int) (x.b * 17.0f));
    private TextView f = new TextView(getContext());
    private TextView g;
    private TextView h;
    private TextView i;
    private ImageView j;
    private ImageView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private LinearLayout o;
    private final LinearLayout p;
    /* access modifiers changed from: private */
    public final String q;
    private final a r;
    /* access modifiers changed from: private */
    public final C0012a s;
    /* access modifiers changed from: private */
    @Nullable
    public b t;

    public k(Context context, String str, a aVar, C0012a aVar2) {
        super(context);
        setOrientation(1);
        this.q = str;
        this.r = aVar;
        this.s = aVar2;
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.topMargin = b;
        this.f.setLayoutParams(layoutParams);
        addView(this.f);
        this.p = new LinearLayout(context);
        this.p.setOrientation(0);
        this.p.setGravity(16);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.topMargin = b / 2;
        addView(this.p, layoutParams2);
        LinearLayout linearLayout = this.p;
        this.h = new TextView(getContext());
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        layoutParams3.leftMargin = b / 2;
        this.h.setLayoutParams(layoutParams3);
        this.j = new ImageView(getContext());
        this.j.setScaleType(ScaleType.FIT_CENTER);
        this.j.setColorFilter(-1);
        this.j.setImageBitmap(c.a(com.facebook.ads.internal.w.c.b.RATINGS));
        int i2 = a;
        linearLayout.addView(this.j, new LayoutParams(i2, i2));
        linearLayout.addView(this.h);
        this.l = a();
        this.p.addView(this.l);
        LinearLayout linearLayout2 = this.p;
        this.i = new TextView(getContext());
        this.i.setEllipsize(TruncateAt.END);
        this.i.setMaxLines(1);
        LayoutParams layoutParams4 = new LayoutParams(-2, -2);
        layoutParams4.leftMargin = b / 2;
        this.i.setLayoutParams(layoutParams4);
        this.k = new ImageView(getContext());
        this.k.setScaleType(ScaleType.FIT_CENTER);
        this.k.setColorFilter(-1);
        this.k.setImageBitmap(c.a(this.r.equals(a.CONTEXTUAL_APP) ? com.facebook.ads.internal.w.c.b.GOOGLE : com.facebook.ads.internal.w.c.b.GLOBE));
        int i3 = a;
        linearLayout2.addView(this.k, new LayoutParams(i3, i3));
        linearLayout2.addView(this.i);
        this.n = a();
        this.p.addView(this.n);
        LinearLayout linearLayout3 = this.p;
        this.g = new TextView(getContext());
        this.g.setEllipsize(TruncateAt.END);
        this.g.setMaxLines(1);
        this.g.setLayoutParams(new LayoutParams(-2, -2));
        linearLayout3.addView(this.g);
        this.m = a();
        this.p.addView(this.m);
        LinearLayout linearLayout4 = this.p;
        this.o = new LinearLayout(getContext());
        this.o.setOrientation(0);
        this.o.setGravity(16);
        linearLayout4.addView(this.o, new LayoutParams(-2, -1));
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(c.a(com.facebook.ads.internal.w.c.b.INFO_ICON));
        imageView.setColorFilter(-1);
        LinearLayout linearLayout5 = this.o;
        int i4 = a;
        linearLayout5.addView(imageView, new LayoutParams(i4, i4));
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageBitmap(c.a(com.facebook.ads.internal.w.c.b.AD_CHOICES_ICON));
        imageView2.setColorFilter(-1);
        int i5 = a;
        LayoutParams layoutParams5 = new LayoutParams(i5, i5);
        layoutParams5.leftMargin = c;
        this.o.addView(imageView2, layoutParams5);
        this.o.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                k.this.s.a(k.this.q, false, k.this.t);
            }
        });
        x.a(this, this.o, d, e);
    }

    private TextView a() {
        TextView textView = new TextView(getContext());
        textView.setText("·");
        textView.setTextColor(-1);
        x.a(textView, false, 14);
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        int i2 = b;
        layoutParams.leftMargin = i2;
        layoutParams.rightMargin = i2;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public void a(String str, boolean z, int i2, int i3) {
        this.f.setText(str);
        this.f.setTextColor(i3);
        x.a(this.f, z, i2);
        this.f.setMaxLines(2);
        this.f.setEllipsize(TruncateAt.END);
    }

    public void a(boolean z) {
        TextView textView;
        if (z) {
            if (!TextUtils.isEmpty(this.h.getText())) {
                this.j.setVisibility(0);
                this.h.setVisibility(0);
                this.l.setVisibility(0);
            }
            if (!TextUtils.isEmpty(this.g.getText())) {
                this.g.setVisibility(0);
                this.m.setVisibility(0);
            }
            this.k.setVisibility(8);
            this.i.setVisibility(8);
            textView = this.n;
        } else {
            if (!TextUtils.isEmpty(this.i.getText())) {
                this.k.setVisibility(0);
                this.i.setVisibility(0);
                this.n.setVisibility(0);
            }
            this.j.setVisibility(8);
            this.h.setVisibility(8);
            this.l.setVisibility(8);
            this.g.setVisibility(8);
            textView = this.m;
        }
        textView.setVisibility(8);
    }

    public void b(String str, boolean z, int i2, int i3) {
        this.h.setText(str);
        this.h.setTextColor(i3);
        x.a(this.h, z, i2);
        int i4 = 8;
        this.j.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        this.h.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        TextView textView = this.l;
        if (!TextUtils.isEmpty(str)) {
            i4 = 0;
        }
        textView.setVisibility(i4);
    }

    public void c(String str, boolean z, int i2, int i3) {
        this.i.setText(str);
        this.i.setTextColor(i3);
        x.a(this.i, z, i2);
        int i4 = 8;
        this.k.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        this.i.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        TextView textView = this.n;
        if (!TextUtils.isEmpty(str)) {
            i4 = 0;
        }
        textView.setVisibility(i4);
    }

    public void d(String str, boolean z, int i2, int i3) {
        this.g.setText(str);
        this.g.setTextColor(i3);
        x.a(this.g, z, i2);
        int i4 = 8;
        this.g.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        TextView textView = this.m;
        if (!TextUtils.isEmpty(str)) {
            i4 = 0;
        }
        textView.setVisibility(i4);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            int size = MeasureSpec.getSize(0);
            this.p.measure(size, size);
            int measuredWidth = this.p.getMeasuredWidth();
            int i6 = measuredWidth - i4;
            if (i6 > 0) {
                TextView textView = this.i;
                textView.setMaxWidth(textView.getWidth() - i6);
                TextView textView2 = this.g;
                textView2.setMaxWidth(textView2.getWidth() - i6);
            } else {
                this.i.setMaxWidth(measuredWidth);
                this.g.setMaxWidth(measuredWidth);
            }
        }
    }

    public void setAdReportingFlowListener(b bVar) {
        this.t = bVar;
    }
}
