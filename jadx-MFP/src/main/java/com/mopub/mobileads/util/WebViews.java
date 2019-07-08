package com.mopub.mobileads.util;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.webkit.CookieManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;

public class WebViews {
    public static void onPause(@NonNull WebView webView, boolean z) {
        if (z) {
            webView.stopLoading();
            webView.loadUrl("");
        }
        webView.onPause();
    }

    public static void setDisableJSChromeClient(@NonNull WebView webView) {
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(@NonNull WebView webView, @NonNull String str, @NonNull String str2, @NonNull JsResult jsResult) {
                MoPubLog.d(str2);
                jsResult.confirm();
                return true;
            }

            public boolean onJsConfirm(@NonNull WebView webView, @NonNull String str, @NonNull String str2, @NonNull JsResult jsResult) {
                MoPubLog.d(str2);
                jsResult.confirm();
                return true;
            }

            public boolean onJsPrompt(@NonNull WebView webView, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull JsPromptResult jsPromptResult) {
                MoPubLog.d(str2);
                jsPromptResult.confirm();
                return true;
            }

            public boolean onJsBeforeUnload(@NonNull WebView webView, @NonNull String str, @NonNull String str2, @NonNull JsResult jsResult) {
                MoPubLog.d(str2);
                jsResult.confirm();
                return true;
            }
        });
    }

    public static void manageWebCookies() {
        CookieManager instance = CookieManager.getInstance();
        if (MoPub.canCollectPersonalInformation()) {
            instance.setAcceptCookie(true);
            CookieManager.setAcceptFileSchemeCookies(true);
            return;
        }
        instance.setAcceptCookie(false);
        CookieManager.setAcceptFileSchemeCookies(false);
        if (VERSION.SDK_INT >= 21) {
            instance.removeSessionCookies(null);
            instance.removeAllCookies(null);
            instance.flush();
        } else {
            instance.removeSessionCookie();
            instance.removeAllCookie();
        }
    }

    public static void manageThirdPartyCookies(@NonNull WebView webView) {
        Preconditions.checkNotNull(webView);
        CookieManager instance = CookieManager.getInstance();
        if (VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(webView, MoPub.canCollectPersonalInformation());
        }
    }
}
