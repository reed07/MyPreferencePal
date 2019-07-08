package com.mopub.common;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Drawables;
import java.util.EnumSet;

class BrowserWebViewClient extends WebViewClient {
    private static final EnumSet<UrlAction> SUPPORTED_URL_ACTIONS = EnumSet.of(UrlAction.HANDLE_PHONE_SCHEME, new UrlAction[]{UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK});
    /* access modifiers changed from: private */
    @NonNull
    public MoPubBrowser mMoPubBrowser;

    public BrowserWebViewClient(@NonNull MoPubBrowser moPubBrowser) {
        this.mMoPubBrowser = moPubBrowser;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("MoPubBrowser error: ");
        sb.append(str);
        MoPubLog.d(sb.toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new Builder().withSupportedUrlActions(SUPPORTED_URL_ACTIONS).withoutMoPubBrowser().withResultActions(new ResultActions() {
            public void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction) {
            }

            public void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction) {
                if (urlAction.equals(UrlAction.OPEN_IN_APP_BROWSER)) {
                    BrowserWebViewClient.this.mMoPubBrowser.getWebView().loadUrl(str);
                } else {
                    BrowserWebViewClient.this.mMoPubBrowser.finish();
                }
            }
        }).build().handleResolvedUrl(this.mMoPubBrowser.getApplicationContext(), str, true, null);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public void onPageFinished(WebView webView, String str) {
        Drawable drawable;
        Drawable drawable2;
        super.onPageFinished(webView, str);
        if (webView.canGoBack()) {
            drawable = Drawables.LEFT_ARROW.createDrawable(this.mMoPubBrowser);
        } else {
            drawable = Drawables.UNLEFT_ARROW.createDrawable(this.mMoPubBrowser);
        }
        this.mMoPubBrowser.getBackButton().setImageDrawable(drawable);
        if (webView.canGoForward()) {
            drawable2 = Drawables.RIGHT_ARROW.createDrawable(this.mMoPubBrowser);
        } else {
            drawable2 = Drawables.UNRIGHT_ARROW.createDrawable(this.mMoPubBrowser);
        }
        this.mMoPubBrowser.getForwardButton().setImageDrawable(drawable2);
    }
}
