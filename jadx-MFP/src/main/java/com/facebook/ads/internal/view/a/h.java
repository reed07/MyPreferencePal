package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.f.b.a;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;

public class h extends c {
    private static final int c = ((int) (x.b * 4.0f));
    private static final int d = ((int) (x.b * 10.0f));
    private static final int e = ((int) (x.b * 44.0f));
    private final LinearLayout f;
    private final ImageView g = new ImageView(getContext());
    private final HorizontalScrollView h;
    private final LinearLayout i;

    public h(Context context, c cVar, String str, int i2, int i3) {
        super(context, cVar, str);
        ImageView imageView = this.g;
        int i4 = d;
        imageView.setPadding(i4, i4, i4, i4);
        this.g.setScaleType(ScaleType.FIT_CENTER);
        this.g.setColorFilter(-10459280);
        int i5 = e;
        LayoutParams layoutParams = new LayoutParams(i5, i5);
        layoutParams.gravity = 16;
        this.i = new LinearLayout(getContext());
        this.i.setOrientation(0);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        this.h = new HorizontalScrollView(getContext());
        this.h.setHorizontalScrollBarEnabled(false);
        this.h.setLayoutParams(layoutParams2);
        this.h.addView(this.i, layoutParams2);
        this.f = new LinearLayout(getContext());
        this.f.setOrientation(0);
        x.a((View) this.f, -218103809);
        this.f.setMotionEventSplittingEnabled(false);
        this.f.addView(this.g, layoutParams);
        this.f.addView(this.h, layoutParams2);
        addView(this.f, new FrameLayout.LayoutParams(i2, i3));
    }

    public void a(com.facebook.ads.internal.f.c cVar, a aVar) {
        x.a((ViewGroup) this.f);
        this.g.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b.BACK_ARROW));
        this.g.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                h.this.b.a();
            }
        });
        this.i.removeAllViews();
        this.h.fullScroll(17);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        int i2 = c;
        layoutParams.setMargins(0, i2, i2, i2);
        for (final com.facebook.ads.internal.f.c cVar2 : cVar.d()) {
            final f fVar = new f(getContext());
            fVar.a(cVar2.b(), null);
            fVar.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fVar.a();
                    h.this.b.a(cVar2);
                }
            });
            this.i.addView(fVar, layoutParams);
        }
    }

    public void b(com.facebook.ads.internal.f.c cVar, a aVar) {
        this.g.setOnClickListener(null);
        TextView textView = new TextView(getContext());
        x.a(textView, true, 14);
        textView.setText(com.facebook.ads.internal.f.a.k(getContext()));
        textView.setGravity(17);
        x.a((ViewGroup) this.f);
        this.f.removeAllViews();
        this.f.addView(textView, new LayoutParams(-1, -1));
        textView.setClickable(true);
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        x.c(this);
        x.b(this);
    }

    public void d() {
        this.g.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b.CROSS));
        this.g.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                h.this.b.a();
            }
        });
        final f fVar = new f(getContext());
        fVar.a(com.facebook.ads.internal.f.a.b(getContext()), b.HIDE_AD);
        fVar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                fVar.a();
                h.this.b.a(a.HIDE);
            }
        });
        final f fVar2 = new f(getContext());
        fVar2.a(com.facebook.ads.internal.f.a.e(getContext()), b.REPORT_AD);
        fVar2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                fVar2.a();
                h.this.b.a(a.REPORT);
            }
        });
        final f fVar3 = new f(getContext());
        fVar3.a(com.facebook.ads.internal.f.a.l(getContext()), b.AD_CHOICES_ICON);
        fVar3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                fVar3.a();
                h.this.b.d();
            }
        });
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        int i2 = c;
        layoutParams.setMargins(0, i2, i2, i2);
        x.a((ViewGroup) this.f);
        this.i.removeAllViews();
        this.i.addView(fVar, layoutParams);
        this.i.addView(fVar2, layoutParams);
        this.i.addView(fVar3, layoutParams);
    }

    /* access modifiers changed from: 0000 */
    public boolean e() {
        return true;
    }
}
