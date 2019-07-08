package com.inmobi.commons.core.network;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewNetworkTask {
    private static final String d = "WebViewNetworkTask";
    public c a;
    public WebViewClient b;
    public NetworkTaskWebView c;

    public class NetworkTaskWebView extends WebView {
        public boolean a;

        public NetworkTaskWebView(Context context) {
            super(context);
        }

        public void destroy() {
            this.a = true;
            super.destroy();
        }
    }

    public WebViewNetworkTask(c cVar, WebViewClient webViewClient) {
        this.b = webViewClient;
        this.a = cVar;
    }
}
