package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import java.util.List;

/* compiled from: IMASDK */
final class acy extends WebChromeClient {
    final /* synthetic */ aeb a;
    final /* synthetic */ List b;
    private final /* synthetic */ Context c;

    acy(Context context, aeb aeb, List list) {
        this.c = context;
        this.a = aeb;
        this.b = list;
    }

    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebViewTransport webViewTransport = (WebViewTransport) message.obj;
        webViewTransport.setWebView(new WebView(this.c));
        webViewTransport.getWebView().setWebViewClient(new acz(this));
        message.sendToTarget();
        return true;
    }
}
