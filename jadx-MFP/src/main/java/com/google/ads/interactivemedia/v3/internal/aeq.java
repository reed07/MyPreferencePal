package com.google.ads.interactivemedia.v3.internal;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.impl.data.c;

/* compiled from: IMASDK */
public final class aeq {
    private c a;
    private final WebView b;
    private final ViewGroup c;

    public aeq(WebView webView, ViewGroup viewGroup) {
        this.b = webView;
        this.c = viewGroup;
    }

    @Deprecated
    public final void a(c cVar) {
        if (this.a != null) {
            b();
        }
        if (cVar.isLinear()) {
            this.a = cVar;
            ViewGroup viewGroup = (ViewGroup) this.b.getParent();
            if (viewGroup != null) {
                this.b.setVisibility(4);
                viewGroup.removeView(this.b);
            }
            this.c.addView(this.b, new LayoutParams(-1, -1));
            this.b.setVisibility(0);
        }
    }

    @Deprecated
    public final void a() {
        this.b.setVisibility(4);
        this.a = null;
    }

    @Deprecated
    public final void b() {
        a();
        this.c.removeView(this.b);
    }

    public final void c() {
        if (!(((ViewGroup) this.b.getParent()) != null)) {
            this.c.addView(this.b, new LayoutParams(-1, -1));
        }
        this.b.setVisibility(0);
        this.c.bringChildToFront(this.b);
    }

    public final void d() {
        this.b.setVisibility(4);
    }
}
