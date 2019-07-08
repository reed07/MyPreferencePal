package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import com.mopub.common.AdReport;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;

public class HtmlInterstitialWebView extends BaseHtmlWebView {
    private Handler mHandler = new Handler();

    static class HtmlInterstitialWebViewListener implements HtmlWebViewListener {
        private final CustomEventInterstitialListener mCustomEventInterstitialListener;

        public void onCollapsed() {
        }

        public HtmlInterstitialWebViewListener(CustomEventInterstitialListener customEventInterstitialListener) {
            this.mCustomEventInterstitialListener = customEventInterstitialListener;
        }

        public void onLoaded(BaseHtmlWebView baseHtmlWebView) {
            this.mCustomEventInterstitialListener.onInterstitialLoaded();
        }

        public void onFailed(MoPubErrorCode moPubErrorCode) {
            this.mCustomEventInterstitialListener.onInterstitialFailed(moPubErrorCode);
        }

        public void onClicked() {
            this.mCustomEventInterstitialListener.onInterstitialClicked();
        }
    }

    public HtmlInterstitialWebView(Context context, AdReport adReport) {
        super(context, adReport);
    }

    public void init(CustomEventInterstitialListener customEventInterstitialListener, String str, String str2) {
        super.init();
        setWebViewClient(new HtmlWebViewClient(new HtmlInterstitialWebViewListener(customEventInterstitialListener), this, str, str2));
    }
}
