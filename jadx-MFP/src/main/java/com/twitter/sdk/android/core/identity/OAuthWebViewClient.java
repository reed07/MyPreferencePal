package com.twitter.sdk.android.core.identity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.twitter.sdk.android.core.internal.network.UrlUtils;
import java.net.URI;
import java.util.Map.Entry;
import java.util.TreeMap;

class OAuthWebViewClient extends WebViewClient {
    private final String completeUrl;
    private final Listener listener;

    interface Listener {
        void onError(WebViewException webViewException);

        void onPageFinished(WebView webView, String str);

        void onSuccess(Bundle bundle);
    }

    public OAuthWebViewClient(String str, Listener listener2) {
        this.completeUrl = str;
        this.listener = listener2;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.listener.onPageFinished(webView, str);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith(this.completeUrl)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        TreeMap queryParams = UrlUtils.getQueryParams(URI.create(str), false);
        Bundle bundle = new Bundle(queryParams.size());
        for (Entry entry : queryParams.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        this.listener.onSuccess(bundle);
        return true;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        this.listener.onError(new WebViewException(i, str, str2));
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        this.listener.onError(new WebViewException(sslError.getPrimaryError(), null, null));
    }
}
