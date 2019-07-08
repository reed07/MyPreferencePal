package com.google.ads.interactivemedia.v3.internal;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot.ClickListener;

/* compiled from: IMASDK */
final class acz extends WebViewClient {
    private final /* synthetic */ acy a;

    acz(acy acy) {
        this.a = acy;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        this.a.a.c(str);
        for (ClickListener onCompanionAdClick : this.a.b) {
            onCompanionAdClick.onCompanionAdClick();
        }
        return true;
    }
}
