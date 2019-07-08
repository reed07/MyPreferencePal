package com.shinobicontrols.charts;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class DefaultTooltipView extends RelativeLayout {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    TextView f;
    TextView g;
    TextView h;
    TextView i;

    public DefaultTooltipView(Context context) {
        super(context);
        a(context);
        b();
    }

    public final void setText(CharSequence charSequence) {
        this.a.setText(charSequence);
        a();
    }

    public final void setText(int i2) {
        this.a.setText(i2);
        a();
    }

    private void a() {
        for (int i2 = 1; i2 < 9; i2++) {
            getChildAt(i2).setVisibility(8);
        }
    }

    private void a(Context context) {
        this.a = new TextView(context);
        this.a.setId(1);
        this.b = new TextView(context);
        this.b.setId(2);
        this.c = new TextView(context);
        this.c.setId(3);
        this.d = new TextView(context);
        this.d.setId(4);
        this.e = new TextView(context);
        this.e.setId(5);
        this.f = new TextView(context);
        this.f.setId(6);
        this.g = new TextView(context);
        this.g.setId(7);
        this.h = new TextView(context);
        this.h.setId(8);
        this.i = new TextView(context);
        this.i.setId(9);
        addView(this.a);
        addView(this.b);
        addView(this.c);
        addView(this.d);
        addView(this.e);
        addView(this.f);
        addView(this.g);
        addView(this.h);
        addView(this.i);
        for (int i2 = 0; i2 < 5; i2++) {
            ((TextView) getChildAt(i2)).setGravity(5);
        }
        for (int i3 = 5; i3 < 9; i3++) {
            ((TextView) getChildAt(i3)).setGravity(3);
        }
        ((TextView) getChildAt(0)).setGravity(1);
    }

    private void b() {
        c();
        d();
        e();
        f();
        g();
    }

    private void c() {
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        layoutParams.addRule(10);
        layoutParams.addRule(14);
    }

    private void d() {
        ((LayoutParams) this.b.getLayoutParams()).addRule(3, 1);
        LayoutParams layoutParams = (LayoutParams) this.f.getLayoutParams();
        layoutParams.addRule(3, 1);
        layoutParams.addRule(1, 2);
    }

    private void e() {
        LayoutParams layoutParams = (LayoutParams) this.c.getLayoutParams();
        layoutParams.addRule(3, 1);
        layoutParams.addRule(1, 6);
        LayoutParams layoutParams2 = (LayoutParams) this.g.getLayoutParams();
        layoutParams2.addRule(3, 1);
        layoutParams2.addRule(1, 3);
    }

    private void f() {
        ((LayoutParams) this.d.getLayoutParams()).addRule(3, 2);
        LayoutParams layoutParams = (LayoutParams) this.h.getLayoutParams();
        layoutParams.addRule(3, 6);
        layoutParams.addRule(1, 4);
    }

    private void g() {
        LayoutParams layoutParams = (LayoutParams) this.e.getLayoutParams();
        layoutParams.addRule(3, 3);
        layoutParams.addRule(1, 8);
        LayoutParams layoutParams2 = (LayoutParams) this.i.getLayoutParams();
        layoutParams2.addRule(3, 7);
        layoutParams2.addRule(1, 5);
    }
}
