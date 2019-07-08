package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;
import com.facebook.ads.internal.w.c.c;

public class a extends RelativeLayout {
    private static final int a = ((int) (x.b * 16.0f));
    private static final int b = ((int) (x.b * 8.0f));
    private static final int c = ((int) (x.b * 44.0f));
    private static final int d = ((int) (x.b * 10.0f));
    private static final int e = (a - d);
    private static final int f = ((int) (x.b * 75.0f));
    private static final int g = ((int) (x.b * 25.0f));
    private static final int h = ((int) (x.b * 45.0f));
    private static final int i = ((int) (x.b * 15.0f));
    private static final int j = ((int) (x.b * 16.0f));
    /* access modifiers changed from: private */
    @Nullable
    public final e k;
    private final int l;
    private final int m;
    /* access modifiers changed from: private */
    public final boolean n;

    /* renamed from: com.facebook.ads.internal.view.a.a$a reason: collision with other inner class name */
    public static class C0013a {
        /* access modifiers changed from: private */
        public final Context a;
        /* access modifiers changed from: private */
        public e b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public b f;
        /* access modifiers changed from: private */
        public int g;
        /* access modifiers changed from: private */
        public boolean h = true;
        /* access modifiers changed from: private */
        public boolean i = true;
        /* access modifiers changed from: private */
        public boolean j = true;
        /* access modifiers changed from: private */
        public boolean k = true;
        /* access modifiers changed from: private */
        public boolean l = true;

        public C0013a(Context context) {
            this.a = context;
        }

        public C0013a a(int i2) {
            this.g = i2;
            return this;
        }

        public C0013a a(e eVar) {
            this.b = eVar;
            return this;
        }

        public C0013a a(b bVar) {
            this.f = bVar;
            return this;
        }

        public C0013a a(String str) {
            this.c = str;
            return this;
        }

        public C0013a a(boolean z) {
            this.h = z;
            return this;
        }

        public a a() {
            return new a(this);
        }

        public C0013a b(String str) {
            this.d = str;
            return this;
        }

        public C0013a b(boolean z) {
            this.i = z;
            return this;
        }

        public C0013a c(String str) {
            this.e = str;
            return this;
        }

        public C0013a c(boolean z) {
            this.j = z;
            return this;
        }

        public C0013a d(boolean z) {
            this.k = z;
            return this;
        }

        public C0013a e(boolean z) {
            this.l = z;
            return this;
        }
    }

    private a(C0013a aVar) {
        super(aVar.a);
        this.k = aVar.b;
        this.l = aVar.i ? f : h;
        this.m = aVar.i ? g : i;
        this.n = aVar.k;
        setClickable(true);
        LinearLayout linearLayout = new LinearLayout(getContext());
        int i2 = 0;
        linearLayout.setOrientation(0);
        if (aVar.h) {
            ImageView imageView = new ImageView(getContext());
            int i3 = d;
            imageView.setPadding(i3, i3, i3, i3);
            imageView.setScaleType(ScaleType.FIT_CENTER);
            imageView.setImageBitmap(c.a(b.CROSS));
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (a.this.k == null) {
                        return;
                    }
                    if (a.this.n) {
                        a.this.k.b();
                    } else {
                        a.this.k.a(true);
                    }
                }
            });
            int i4 = c;
            LayoutParams layoutParams = new LayoutParams(i4, i4);
            int i5 = e;
            layoutParams.setMargins(i5, i5, i5, i5);
            linearLayout.addView(imageView, layoutParams);
        }
        ImageView imageView2 = new ImageView(getContext());
        int i6 = this.m;
        imageView2.setPadding(i6, i6, i6, i6);
        imageView2.setImageBitmap(c.a(aVar.f));
        imageView2.setColorFilter(-1);
        int i7 = this.l;
        LayoutParams layoutParams2 = new LayoutParams(i7, i7);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(aVar.g);
        x.a((View) imageView2, (Drawable) gradientDrawable);
        layoutParams2.gravity = 17;
        int i8 = a;
        layoutParams2.setMargins(i8, 0, i8, i8);
        TextView textView = new TextView(getContext());
        x.a(textView, true, 20);
        textView.setTextColor(-14934495);
        textView.setText(aVar.c);
        textView.setGravity(17);
        LayoutParams layoutParams3 = new LayoutParams(-1, -2);
        int i9 = a;
        layoutParams3.setMargins(i9, 0, i9, i9);
        TextView textView2 = new TextView(getContext());
        x.a(textView2, false, 16);
        textView2.setTextColor(-10459280);
        textView2.setText(aVar.d);
        textView2.setGravity(17);
        LayoutParams layoutParams4 = new LayoutParams(-1, -2);
        int i10 = a;
        layoutParams4.setMargins(i10, 0, i10, i10);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        linearLayout2.addView(imageView2, layoutParams2);
        linearLayout2.addView(textView, layoutParams3);
        linearLayout2.addView(textView2, layoutParams4);
        if (aVar.j) {
            f fVar = new f(getContext());
            fVar.a(aVar.e, b.CHECKMARK);
            fVar.setSelected(true);
            linearLayout2.addView(fVar, new LayoutParams(-2, -2));
        }
        View footerView = getFooterView();
        x.a((View) linearLayout);
        x.a((View) linearLayout2);
        x.a(footerView);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams5.addRule(10);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams6.addRule(13);
        layoutParams6.addRule(3, linearLayout.getId());
        layoutParams6.addRule(2, footerView.getId());
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams7.addRule(12);
        int i11 = a;
        layoutParams7.setMargins(i11, 0, i11, i11);
        addView(linearLayout, layoutParams5);
        addView(linearLayout2, layoutParams6);
        addView(footerView, layoutParams7);
        if (!aVar.l) {
            i2 = 8;
        }
        footerView.setVisibility(i2);
    }

    private View getFooterView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(c.a(b.SETTINGS));
        imageView.setColorFilter(-13272859);
        int i2 = j;
        LayoutParams layoutParams = new LayoutParams(i2, i2);
        layoutParams.gravity = 17;
        TextView textView = new TextView(getContext());
        x.a(textView, false, 16);
        textView.setTextColor(-13272859);
        int i3 = b;
        textView.setPadding(i3, i3, i3, i3);
        textView.setText(com.facebook.ads.internal.f.a.h(getContext()));
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (a.this.k != null) {
                    a.this.k.c();
                }
            }
        });
        linearLayout.addView(imageView, layoutParams);
        linearLayout.addView(textView, layoutParams2);
        return linearLayout;
    }
}
