package com.google.ads.interactivemedia.v3.internal;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* compiled from: IMASDK */
final class aea extends WebViewClient {
    private final /* synthetic */ adz a;

    aea(adz adz) {
        this.a = adz;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith("gmsg://")) {
            return false;
        }
        this.a.b(str);
        return true;
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        String str2 = "Started ";
        String valueOf = String.valueOf(str);
        adz.c(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    public final void onPageFinished(WebView webView, String str) {
        String str2 = "Finished ";
        String valueOf = String.valueOf(str);
        adz.c(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20 + String.valueOf(str2).length());
        sb.append("Error: ");
        sb.append(i);
        sb.append(" ");
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        adz.c(sb.toString());
    }
}
