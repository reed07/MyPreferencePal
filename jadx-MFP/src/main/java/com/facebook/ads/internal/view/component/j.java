package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils.TruncateAt;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.w.b.x;

public class j extends LinearLayout {
    private static final float a = Resources.getSystem().getDisplayMetrics().density;
    private static final int b;
    private static final int c;
    private final TextView d;
    private final TextView e;
    private final TextView f;

    static {
        float f2 = a;
        b = (int) (6.0f * f2);
        c = (int) (f2 * 8.0f);
    }

    public j(Context context, h hVar, boolean z, int i, int i2, int i3) {
        super(context);
        setOrientation(1);
        this.d = new TextView(context);
        x.a(this.d, true, i);
        this.d.setTextColor(hVar.c(z));
        this.d.setEllipsize(TruncateAt.END);
        this.d.setLineSpacing((float) b, 1.0f);
        this.f = new TextView(context);
        this.f.setTextColor(hVar.a(z));
        this.e = new TextView(context);
        x.a(this.e, false, i2);
        this.e.setTextColor(hVar.b(z));
        this.e.setEllipsize(TruncateAt.END);
        this.e.setLineSpacing((float) b, 1.0f);
        addView(this.d, new LayoutParams(-1, -2));
        addView(this.f, new LayoutParams(-1, -2));
        this.f.setVisibility(8);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, i3, 0, 0);
        addView(this.e, layoutParams);
    }

    public j(Context context, h hVar, boolean z, boolean z2, boolean z3) {
        this(context, hVar, z, z2 ? 18 : 22, z2 ? 14 : 16, z3 ? c / 2 : c);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        if (r9 != false) goto L_0x0048;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5, java.lang.String r6, @android.support.annotation.Nullable java.lang.String r7, boolean r8, boolean r9) {
        /*
            r4 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            r1 = 1
            r0 = r0 ^ r1
            boolean r2 = android.text.TextUtils.isEmpty(r6)
            r2 = r2 ^ r1
            android.widget.TextView r3 = r4.d
            if (r0 == 0) goto L_0x0010
            goto L_0x0011
        L_0x0010:
            r5 = r6
        L_0x0011:
            r3.setText(r5)
            if (r7 == 0) goto L_0x001b
            android.widget.TextView r5 = r4.f
            r5.setText(r7)
        L_0x001b:
            android.widget.TextView r5 = r4.e
            if (r0 == 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            java.lang.String r6 = ""
        L_0x0022:
            r5.setText(r6)
            r5 = 3
            r6 = 2
            if (r0 == 0) goto L_0x003f
            if (r2 != 0) goto L_0x002c
            goto L_0x003f
        L_0x002c:
            android.widget.TextView r7 = r4.d
            if (r8 == 0) goto L_0x0032
            r0 = 1
            goto L_0x0033
        L_0x0032:
            r0 = 2
        L_0x0033:
            r7.setMaxLines(r0)
            android.widget.TextView r7 = r4.e
            if (r8 == 0) goto L_0x003c
            r5 = 1
            goto L_0x0048
        L_0x003c:
            if (r9 == 0) goto L_0x0043
            goto L_0x0048
        L_0x003f:
            android.widget.TextView r7 = r4.d
            if (r8 == 0) goto L_0x0045
        L_0x0043:
            r5 = 2
            goto L_0x0048
        L_0x0045:
            if (r9 == 0) goto L_0x0048
            r5 = 4
        L_0x0048:
            r7.setMaxLines(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.component.j.a(java.lang.String, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    public void a(boolean z, int i) {
        int i2;
        TextView textView;
        if (z) {
            this.f.setGravity(i);
            textView = this.f;
            i2 = 0;
        } else {
            textView = this.f;
            i2 = 8;
        }
        textView.setVisibility(i2);
    }

    public TextView getDescriptionTextView() {
        return this.e;
    }

    public TextView getTitleTextView() {
        return this.d;
    }

    public void setAlignment(int i) {
        this.d.setGravity(i);
        this.e.setGravity(i);
    }

    public void setDescriptionGravity(int i) {
        this.e.setGravity(i);
    }

    public void setTitleGravity(int i) {
        this.d.setGravity(i);
    }
}
