package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

@SuppressLint({"SetJavaScriptEnabled", "NewApi"})
/* compiled from: IMASDK */
public final class adz {
    private final aeb a;
    private final WebView b;

    public adz(Context context, aeb aeb) {
        this(new WebView(context), aeb);
    }

    private adz(WebView webView, aeb aeb) {
        this.a = aeb;
        this.b = webView;
        this.b.setBackgroundColor(0);
        if (VERSION.SDK_INT == 15) {
            this.b.setLayerType(1, null);
        }
        if (VERSION.SDK_INT > 19) {
            webView.getSettings().setMixedContentMode(0);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new aea(this));
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings settings = webView.getSettings();
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        if (VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(webView, true);
        }
    }

    public final WebView a() {
        return this.b;
    }

    public final void a(String str) {
        this.b.loadUrl(str);
    }

    @TargetApi(19)
    public final void a(ado ado) {
        String e = ado.e();
        a(true, ado, e);
        if (VERSION.SDK_INT >= 19) {
            try {
                this.b.evaluateJavascript(e, null);
                return;
            } catch (IllegalStateException unused) {
            }
        }
        this.b.loadUrl(e);
    }

    /* access modifiers changed from: protected */
    public final void b(String str) {
        try {
            ado a2 = ado.a(str);
            a(false, a2, str);
            this.a.a(a2);
        } catch (IllegalArgumentException unused) {
            String str2 = "IMASDK";
            String str3 = "Invalid internal message, ignoring. Please make sure the Google IMA SDK library is up to date. Message: ";
            String valueOf = String.valueOf(str);
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        } catch (Exception e) {
            String str4 = "IMASDK";
            String str5 = "An internal error occured parsing message from javascript.  Message to be parsed: ";
            String valueOf2 = String.valueOf(str);
            Log.e(str4, valueOf2.length() != 0 ? str5.concat(valueOf2) : new String(str5), e);
        }
    }

    private static final void a(boolean z, ado ado, String str) {
        String str2 = z ? "Sending javascript msg: " : "Received msg: ";
        if (adk.a(adk.VERBOSE)) {
            String valueOf = String.valueOf(ado);
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 7 + String.valueOf(valueOf).length() + String.valueOf(str).length());
            sb.append(str2);
            sb.append(valueOf);
            sb.append("; URL: ");
            sb.append(str);
            Log.d("IMASDK", sb.toString());
            return;
        }
        if (adk.a(adk.ABRIDGED)) {
            String name = ado.a().name();
            String name2 = ado.b().name();
            StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 17 + String.valueOf(name).length() + String.valueOf(name2).length());
            sb2.append(str2);
            sb2.append("Channel: ");
            sb2.append(name);
            sb2.append("; type: ");
            sb2.append(name2);
            Log.d("IMASDK", sb2.toString());
        }
    }

    static final void c(String str) {
        if (adk.a(adk.LIFECYCLE)) {
            Log.d("IMASDK", str);
        }
    }
}
