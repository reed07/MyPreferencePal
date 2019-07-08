package com.facebook.ads.internal.w.e;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public abstract class a extends WebView {
    private static final String a = "a";
    private boolean b;

    public a(Context context) {
        super(context);
        setWebChromeClient(a());
        setWebViewClient(b());
        WebSettings settings = getSettings();
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        } else {
            try {
                WebSettings.class.getMethod("setMixedContentMode", new Class[0]).invoke(settings, new Object[]{Integer.valueOf(0)});
            } catch (Exception unused) {
            }
        }
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        if (VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        if (VERSION.SDK_INT >= 21) {
            try {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
            } catch (Exception unused2) {
                Log.w(a, "Failed to initialize CookieManager.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public WebChromeClient a() {
        return new WebChromeClient();
    }

    /* access modifiers changed from: protected */
    public WebViewClient b() {
        return new WebViewClient();
    }

    public boolean c() {
        return this.b;
    }

    public void destroy() {
        this.b = true;
        super.destroy();
    }
}
