package com.mopub.mobileads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Views;
import com.mopub.mobileads.util.WebViews;

public class BaseWebView extends WebView {
    private static boolean sDeadlockCleared = false;
    protected boolean mIsDestroyed;

    public BaseWebView(Context context) {
        super(context.getApplicationContext());
        enablePlugins(false);
        restrictDeviceContentAccess();
        WebViews.setDisableJSChromeClient(this);
        if (!sDeadlockCleared) {
            clearWebViewDeadlock(getContext());
            sDeadlockCleared = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        WebViews.manageThirdPartyCookies(this);
    }

    public void destroy() {
        if (!this.mIsDestroyed) {
            this.mIsDestroyed = true;
            Views.removeFromParent(this);
            removeAllViews();
            super.destroy();
        }
    }

    public void enablePlugins(boolean z) {
        if (VERSION.SDK_INT < 18) {
            if (z) {
                getSettings().setPluginState(PluginState.ON);
            } else {
                getSettings().setPluginState(PluginState.OFF);
            }
        }
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void enableJavascriptCaching() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setAppCachePath(getContext().getCacheDir().getAbsolutePath());
    }

    private void restrictDeviceContentAccess() {
        getSettings().setAllowFileAccess(false);
        getSettings().setAllowContentAccess(false);
        getSettings().setAllowFileAccessFromFileURLs(false);
        getSettings().setAllowUniversalAccessFromFileURLs(false);
    }

    private void clearWebViewDeadlock(@NonNull Context context) {
        if (VERSION.SDK_INT == 19) {
            WebView webView = new WebView(context.getApplicationContext());
            webView.setBackgroundColor(0);
            webView.loadDataWithBaseURL(null, "", AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
            LayoutParams layoutParams = new LayoutParams();
            layoutParams.width = 1;
            layoutParams.height = 1;
            layoutParams.type = 2005;
            layoutParams.flags = 16777240;
            layoutParams.format = -2;
            layoutParams.gravity = 8388659;
            ((WindowManager) context.getSystemService("window")).addView(webView, layoutParams);
        }
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    @VisibleForTesting
    public void setIsDestroyed(boolean z) {
        this.mIsDestroyed = z;
    }
}
