package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.webkit.WebView;

/* compiled from: IMASDK */
public class v {
    private ap a;
    private w b;
    private double c;

    public v() {
        e();
        this.a = new ap((WebView) null);
    }

    public void a() {
    }

    public void b() {
        this.a.clear();
    }

    /* access modifiers changed from: 0000 */
    public final void a(WebView webView) {
        this.a = new ap(webView);
    }

    public final WebView c() {
        return (WebView) this.a.get();
    }

    public final boolean d() {
        return this.a.get() != null;
    }

    public final void a(String str, double d) {
        if (d > this.c) {
            this.b = w.AD_STATE_VISIBLE;
            o.a().b(c(), str);
        }
    }

    public final void b(String str, double d) {
        if (d > this.c && this.b != w.AD_STATE_HIDDEN) {
            this.b = w.AD_STATE_HIDDEN;
            o.a().b(c(), str);
        }
    }

    public final void a(float f) {
        o.a().a(c(), f);
    }

    public final void e() {
        this.c = ho.f();
        this.b = w.AD_STATE_IDLE;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public v(WebView webView) {
        this();
        if (webView != null && !webView.getSettings().getJavaScriptEnabled()) {
            webView.getSettings().setJavaScriptEnabled(true);
        }
        a(webView);
    }
}
