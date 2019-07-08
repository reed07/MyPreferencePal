package com.google.ads.interactivemedia.v3.internal;

import android.webkit.WebView;

/* compiled from: IMASDK */
final class y implements Runnable {
    private WebView a = this.b.a;
    private final /* synthetic */ x b;

    y(x xVar) {
        this.b = xVar;
    }

    public final void run() {
        this.a.destroy();
    }
}
