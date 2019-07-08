package com.myfitnesspal.shared.util;

import android.webkit.WebView;
import com.mopub.mobileads.MoPubView;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ViewUtils;

public class MoPubUtil {
    public static void resume(MoPubView moPubView) {
        applyOperationToAllWebViewsOfMoPubView(moPubView, new Function1<WebView>() {
            public void execute(WebView webView) throws RuntimeException {
                webView.onResume();
            }
        });
        if (moPubView != null) {
            moPubView.loadAd();
        }
    }

    public static void pause(MoPubView moPubView) {
        applyOperationToAllWebViewsOfMoPubView(moPubView, new Function1<WebView>() {
            public void execute(WebView webView) throws RuntimeException {
                webView.onPause();
                webView.loadUrl("about:blank");
            }
        });
    }

    private static void applyOperationToAllWebViewsOfMoPubView(MoPubView moPubView, Function1<WebView> function1) {
        if (moPubView != null) {
            for (WebView invokeIfValid : ViewUtils.findByType(moPubView, WebView.class)) {
                FunctionUtils.invokeIfValid(function1, invokeIfValid);
            }
        }
    }
}
