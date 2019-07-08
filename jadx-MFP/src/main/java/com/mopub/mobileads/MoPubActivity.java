package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.exoplayer2.C;
import com.mopub.common.AdReport;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.WebViewCacheService.Config;
import com.mopub.mobileads.factories.HtmlInterstitialWebViewFactory;
import java.io.Serializable;

public class MoPubActivity extends BaseInterstitialActivity {
    @Nullable
    private ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    /* access modifiers changed from: private */
    @Nullable
    public HtmlInterstitialWebView mHtmlInterstitialWebView;

    class BroadcastingInterstitialListener implements CustomEventInterstitialListener {
        public void onInterstitialDismissed() {
        }

        public void onInterstitialImpression() {
        }

        public void onInterstitialShown() {
        }

        public void onLeaveApplication() {
        }

        BroadcastingInterstitialListener() {
        }

        public void onInterstitialLoaded() {
            if (MoPubActivity.this.mHtmlInterstitialWebView != null) {
                MoPubActivity.this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
            }
        }

        public void onInterstitialFailed(MoPubErrorCode moPubErrorCode) {
            MoPubActivity moPubActivity = MoPubActivity.this;
            EventForwardingBroadcastReceiver.broadcastAction(moPubActivity, moPubActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_FAIL);
            MoPubActivity.this.finish();
        }

        public void onInterstitialClicked() {
            MoPubActivity moPubActivity = MoPubActivity.this;
            EventForwardingBroadcastReceiver.broadcastAction(moPubActivity, moPubActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_CLICK);
        }
    }

    public static void start(Context context, String str, AdReport adReport, String str2, CreativeOrientation creativeOrientation, long j) {
        try {
            context.startActivity(createIntent(context, str, adReport, str2, creativeOrientation, j));
        } catch (ActivityNotFoundException unused) {
            Log.d("MoPubActivity", "MoPubActivity not found - did you declare it in AndroidManifest.xml?");
        }
    }

    static Intent createIntent(Context context, String str, AdReport adReport, String str2, CreativeOrientation creativeOrientation, long j) {
        Intent intent = new Intent(context, MoPubActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, str);
        intent.putExtra(DataKeys.CLICKTHROUGH_URL_KEY, str2);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.putExtra(DataKeys.CREATIVE_ORIENTATION_KEY, creativeOrientation);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        return intent;
    }

    static void preRenderHtml(Interstitial interstitial, Context context, AdReport adReport, final CustomEventInterstitialListener customEventInterstitialListener, String str, String str2, long j) {
        HtmlInterstitialWebView create = HtmlInterstitialWebViewFactory.create(context.getApplicationContext(), adReport, customEventInterstitialListener, str2);
        create.enablePlugins(false);
        create.enableJavascriptCaching();
        create.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if ("mopub://finishLoad".equals(str)) {
                    customEventInterstitialListener.onInterstitialLoaded();
                } else if ("mopub://failLoad".equals(str)) {
                    customEventInterstitialListener.onInterstitialFailed(null);
                }
                return true;
            }
        });
        ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(context);
        externalViewabilitySessionManager.createDisplaySession(context, create, true);
        create.loadHtmlResponse(str);
        WebViewCacheService.storeWebViewConfig(Long.valueOf(j), interstitial, create, externalViewabilitySessionManager);
    }

    public View getAdView() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(DataKeys.CLICKTHROUGH_URL_KEY);
        String stringExtra2 = intent.getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        Long broadcastIdentifier = getBroadcastIdentifier();
        if (broadcastIdentifier != null) {
            Config popWebViewConfig = WebViewCacheService.popWebViewConfig(broadcastIdentifier);
            if (popWebViewConfig != null && (popWebViewConfig.getWebView() instanceof HtmlInterstitialWebView)) {
                this.mHtmlInterstitialWebView = (HtmlInterstitialWebView) popWebViewConfig.getWebView();
                this.mHtmlInterstitialWebView.init(new BroadcastingInterstitialListener(), stringExtra, this.mAdReport != null ? this.mAdReport.getDspCreativeId() : null);
                this.mHtmlInterstitialWebView.enablePlugins(true);
                this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
                this.mExternalViewabilitySessionManager = popWebViewConfig.getViewabilityManager();
                return this.mHtmlInterstitialWebView;
            }
        }
        MoPubLog.d("WebView cache miss. Recreating the WebView.");
        this.mHtmlInterstitialWebView = HtmlInterstitialWebViewFactory.create(getApplicationContext(), this.mAdReport, new BroadcastingInterstitialListener(), stringExtra);
        this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(this);
        this.mExternalViewabilitySessionManager.createDisplaySession(this, this.mHtmlInterstitialWebView, true);
        this.mHtmlInterstitialWebView.loadHtmlResponse(stringExtra2);
        return this.mHtmlInterstitialWebView;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        CreativeOrientation creativeOrientation;
        super.onCreate(bundle);
        Serializable serializableExtra = getIntent().getSerializableExtra(DataKeys.CREATIVE_ORIENTATION_KEY);
        if (serializableExtra == null || !(serializableExtra instanceof CreativeOrientation)) {
            creativeOrientation = CreativeOrientation.UNDEFINED;
        } else {
            creativeOrientation = (CreativeOrientation) serializableExtra;
        }
        DeviceUtils.lockOrientation(this, creativeOrientation);
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.startDeferredDisplaySession(this);
        }
        EventForwardingBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_SHOW);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        HtmlInterstitialWebView htmlInterstitialWebView = this.mHtmlInterstitialWebView;
        if (htmlInterstitialWebView != null) {
            htmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getUrl());
            this.mHtmlInterstitialWebView.destroy();
        }
        EventForwardingBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_DISMISS);
        super.onDestroy();
    }
}
