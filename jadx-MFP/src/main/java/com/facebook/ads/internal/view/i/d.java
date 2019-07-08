package com.facebook.ads.internal.view.i;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.i.c.g;
import com.facebook.ads.internal.view.i.d.c;
import com.facebook.ads.internal.w.b.x;
import java.lang.ref.WeakReference;

public class d extends RelativeLayout {
    private final c a;
    @Nullable
    private g b;
    private WeakReference<a> c;

    public interface a {
        void a();
    }

    public d(Context context, c cVar) {
        super(context);
        this.a = cVar;
        x.b((View) this.a);
        addView(this.a.getView(), new LayoutParams(-1, -1));
    }

    public void a(com.facebook.ads.internal.view.i.a.c cVar) {
        addView(cVar, new LayoutParams(-1, -1));
        this.b = (g) cVar;
    }

    public void b(com.facebook.ads.internal.view.i.a.c cVar) {
        x.b(cVar);
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ((View) this.a).layout(0, 0, getWidth(), getHeight());
        g gVar = this.b;
        if (gVar != null) {
            gVar.layout(0, 0, getWidth(), getHeight());
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        if (r3 > r9) goto L_0x0071;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            com.facebook.ads.internal.view.i.d.c r0 = r7.a
            int r0 = r0.getVideoWidth()
            com.facebook.ads.internal.view.i.d.c r1 = r7.a
            int r1 = r1.getVideoHeight()
            int r2 = getDefaultSize(r0, r8)
            int r3 = getDefaultSize(r1, r9)
            if (r0 <= 0) goto L_0x006d
            if (r1 <= 0) goto L_0x006d
            r2 = 1
            int r3 = android.view.View.MeasureSpec.getMode(r8)
            int r8 = android.view.View.MeasureSpec.getSize(r8)
            int r4 = android.view.View.MeasureSpec.getMode(r9)
            int r9 = android.view.View.MeasureSpec.getSize(r9)
            r5 = 1073741824(0x40000000, float:2.0)
            if (r3 != r5) goto L_0x003d
            if (r4 != r5) goto L_0x003d
            int r3 = r0 * r9
            int r4 = r8 * r1
            if (r3 >= r4) goto L_0x0038
            int r8 = r3 / r1
            goto L_0x0071
        L_0x0038:
            if (r3 <= r4) goto L_0x0071
            int r3 = r4 / r0
            goto L_0x004a
        L_0x003d:
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 != r5) goto L_0x004c
            int r1 = r1 * r8
            int r3 = r1 / r0
            if (r4 != r6) goto L_0x004a
            if (r3 <= r9) goto L_0x004a
            goto L_0x0071
        L_0x004a:
            r9 = r3
            goto L_0x0071
        L_0x004c:
            if (r4 != r5) goto L_0x0058
            int r0 = r0 * r9
            int r0 = r0 / r1
            if (r3 != r6) goto L_0x0056
            if (r0 <= r8) goto L_0x0056
            goto L_0x0071
        L_0x0056:
            r8 = r0
            goto L_0x0071
        L_0x0058:
            if (r4 != r6) goto L_0x0060
            if (r1 <= r9) goto L_0x0060
            int r4 = r9 * r0
            int r4 = r4 / r1
            goto L_0x0062
        L_0x0060:
            r4 = r0
            r9 = r1
        L_0x0062:
            if (r3 != r6) goto L_0x006b
            if (r4 <= r8) goto L_0x006b
            int r1 = r1 * r8
            int r3 = r1 / r0
            goto L_0x004a
        L_0x006b:
            r8 = r4
            goto L_0x0071
        L_0x006d:
            r8 = 0
            r8 = r2
            r9 = r3
            r2 = 0
        L_0x0071:
            r7.setMeasuredDimension(r8, r9)
            if (r2 == 0) goto L_0x008b
            java.lang.ref.WeakReference<com.facebook.ads.internal.view.i.d$a> r8 = r7.c
            if (r8 == 0) goto L_0x008b
            java.lang.Object r8 = r8.get()
            if (r8 == 0) goto L_0x008b
            java.lang.ref.WeakReference<com.facebook.ads.internal.view.i.d$a> r8 = r7.c
            java.lang.Object r8 = r8.get()
            com.facebook.ads.internal.view.i.d$a r8 = (com.facebook.ads.internal.view.i.d.a) r8
            r8.a()
        L_0x008b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.i.d.onMeasure(int, int):void");
    }

    public void setViewImplInflationListener(a aVar) {
        this.c = new WeakReference<>(aVar);
    }
}
