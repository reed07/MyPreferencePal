package com.google.ads.interactivemedia.v3.internal;

import android.webkit.WebView;

/* compiled from: IMASDK */
final class p implements Runnable {
    private final /* synthetic */ WebView a;
    private final /* synthetic */ String b;

    p(WebView webView, String str) {
        this.a = webView;
        this.b = str;
    }

    public final void run() {
        this.a.loadUrl(this.b);
    }
}
