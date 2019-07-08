package com.mopub.common.privacy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.mopub.common.CloseableLayout;
import com.mopub.common.CloseableLayout.OnCloseListener;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;

class ConsentDialogLayout extends CloseableLayout {
    static int FINISHED_LOADING = 101;
    /* access modifiers changed from: private */
    @Nullable
    public ConsentListener mConsentListener;
    /* access modifiers changed from: private */
    @Nullable
    public PageLoadListener mLoadListener;
    @NonNull
    private final WebView mWebView = initWebView();
    private final WebViewClient webViewClient = new WebViewClient() {
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (ConsentDialogLayout.this.mLoadListener != null) {
                ConsentDialogLayout.this.mLoadListener.onLoadProgress(0);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (ConsentDialogLayout.this.mLoadListener != null) {
                ConsentDialogLayout.this.mLoadListener.onLoadProgress(ConsentDialogLayout.FINISHED_LOADING);
            }
            super.onPageFinished(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if ("mopub://consent?yes".equals(str)) {
                if (ConsentDialogLayout.this.mConsentListener != null) {
                    ConsentDialogLayout.this.mConsentListener.onConsentClick(ConsentStatus.EXPLICIT_YES);
                }
                return true;
            } else if ("mopub://consent?no".equals(str)) {
                if (ConsentDialogLayout.this.mConsentListener != null) {
                    ConsentDialogLayout.this.mConsentListener.onConsentClick(ConsentStatus.EXPLICIT_NO);
                }
                return true;
            } else if ("mopub://close".equals(str)) {
                if (ConsentDialogLayout.this.mConsentListener != null) {
                    ConsentDialogLayout.this.mConsentListener.onCloseClick();
                }
                return true;
            } else {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        Context context = ConsentDialogLayout.this.getContext();
                        Uri parse = Uri.parse(str);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Cannot open native browser for ");
                        sb.append(str);
                        Intents.launchActionViewIntent(context, parse, sb.toString());
                        return true;
                    } catch (IntentNotResolvableException e) {
                        MoPubLog.e(e.getMessage());
                    }
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        }
    };

    interface ConsentListener {
        void onCloseClick();

        void onConsentClick(ConsentStatus consentStatus);
    }

    interface PageLoadListener {
        void onLoadProgress(int i);
    }

    public ConsentDialogLayout(@NonNull Context context) {
        super(context);
    }

    public ConsentDialogLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ConsentDialogLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: 0000 */
    public void startLoading(@NonNull String str, @Nullable PageLoadListener pageLoadListener) {
        Preconditions.checkNotNull(str);
        this.mLoadListener = pageLoadListener;
        setupEventsListeners(this.mWebView);
        this.mWebView.loadDataWithBaseURL("https://ads.mopub.com/", str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
    }

    /* access modifiers changed from: 0000 */
    public void setConsentClickListener(@NonNull ConsentListener consentListener) {
        Preconditions.checkNotNull(consentListener);
        this.mConsentListener = consentListener;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private WebView initWebView() {
        WebView webView = new WebView(getContext());
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (VERSION.SDK_INT >= 17) {
            webView.setId(View.generateViewId());
        }
        setCloseVisible(false);
        addView(webView, new LayoutParams(-1, -1));
        return webView;
    }

    private void setupEventsListeners(@NonNull WebView webView) {
        webView.setWebViewClient(this.webViewClient);
        setOnCloseListener(new OnCloseListener() {
            public void onClose() {
                if (ConsentDialogLayout.this.mConsentListener != null) {
                    ConsentDialogLayout.this.mConsentListener.onCloseClick();
                }
            }
        });
    }
}
