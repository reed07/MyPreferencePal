package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import com.facebook.ads.internal.f.b.a;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a.a.C0013a;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;

public class k extends c {
    private static final int c = ((int) (x.b * 8.0f));
    private static final int d = ((int) (x.b * 10.0f));
    private static final int e = ((int) (x.b * 44.0f));
    private final ScrollView f;
    private final LinearLayout g;
    private final ImageView h = new ImageView(getContext());

    public k(Context context, c cVar, String str, int i, int i2) {
        super(context, cVar, str);
        ImageView imageView = this.h;
        int i3 = d;
        imageView.setPadding(i3, i3, i3, i3);
        this.h.setColorFilter(-10459280);
        int i4 = e;
        LayoutParams layoutParams = new LayoutParams(i4, i4);
        layoutParams.gravity = 3;
        this.h.setLayoutParams(layoutParams);
        this.f = new ScrollView(getContext());
        this.f.setFillViewport(true);
        x.a((View) this.f, -218103809);
        this.g = new LinearLayout(getContext());
        this.g.setOrientation(1);
        LinearLayout linearLayout = this.g;
        int i5 = c;
        linearLayout.setPadding(i5, i5, i5, i5);
        this.f.addView(this.g, new FrameLayout.LayoutParams(-1, -1));
        addView(this.f, new LayoutParams(i, i2));
    }

    /* access modifiers changed from: 0000 */
    public void a(com.facebook.ads.internal.f.c cVar, a aVar) {
        j jVar = new j(getContext(), cVar, this.b, aVar == a.REPORT ? b.REPORT_AD : b.HIDE_AD);
        LayoutParams layoutParams = new LayoutParams(-2, 0);
        layoutParams.gravity = 17;
        layoutParams.weight = 1.0f;
        this.h.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b.BACK_ARROW));
        this.h.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                k.this.b.a();
            }
        });
        x.a((ViewGroup) this.g);
        this.f.fullScroll(33);
        this.g.removeAllViews();
        this.g.addView(this.h);
        this.g.addView(jVar, layoutParams);
    }

    /* access modifiers changed from: 0000 */
    public void b(com.facebook.ads.internal.f.c cVar, a aVar) {
        String str;
        int i;
        b bVar;
        this.h.setOnClickListener(null);
        if (aVar == a.REPORT) {
            str = com.facebook.ads.internal.f.a.j(getContext());
            bVar = b.REPORT_AD;
            i = -552389;
        } else {
            str = com.facebook.ads.internal.f.a.b(getContext());
            bVar = b.HIDE_AD;
            i = -13272859;
        }
        a a = new C0013a(getContext()).a(this.b).a(str).b(com.facebook.ads.internal.f.a.k(getContext())).c(cVar.b()).a(false).a(bVar).a(i).b(false).c(false).a();
        LayoutParams layoutParams = new LayoutParams(-1, 0);
        layoutParams.gravity = 17;
        layoutParams.weight = 1.0f;
        x.a((ViewGroup) this.g);
        this.f.fullScroll(33);
        this.g.removeAllViews();
        this.g.addView(a, layoutParams);
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        x.c(this);
        x.b(this);
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        this.h.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b.CROSS));
        this.h.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                k.this.b.a();
            }
        });
        final f fVar = new f(getContext());
        fVar.a(com.facebook.ads.internal.f.a.b(getContext()), b.HIDE_AD);
        fVar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                fVar.a();
                k.this.b.a(a.HIDE);
            }
        });
        final f fVar2 = new f(getContext());
        fVar2.a(com.facebook.ads.internal.f.a.e(getContext()), b.REPORT_AD);
        fVar2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                fVar2.a();
                k.this.b.a(a.REPORT);
            }
        });
        final f fVar3 = new f(getContext());
        fVar3.a(com.facebook.ads.internal.f.a.l(getContext()), b.AD_CHOICES_ICON);
        fVar3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                fVar3.a();
                k.this.b.d();
            }
        });
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        int i = c;
        layoutParams.setMargins(i, i, i, i);
        layoutParams.gravity = 17;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        LayoutParams layoutParams2 = new LayoutParams(-2, 0);
        layoutParams2.gravity = 17;
        layoutParams2.weight = 1.0f;
        x.a((ViewGroup) this.g);
        this.g.removeAllViews();
        this.g.addView(this.h);
        this.g.addView(linearLayout, layoutParams2);
        linearLayout.addView(fVar, layoutParams);
        linearLayout.addView(fVar2, layoutParams);
        linearLayout.addView(fVar3, layoutParams);
    }

    /* access modifiers changed from: 0000 */
    public boolean e() {
        return true;
    }
}
