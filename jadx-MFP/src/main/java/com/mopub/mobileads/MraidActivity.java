package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.exoplayer2.C;
import com.mopub.common.AdReport;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mraid.MraidBridge.MraidWebView;
import com.mopub.mraid.MraidController;
import com.mopub.mraid.MraidController.MraidListener;
import com.mopub.mraid.MraidController.MraidWebViewCacheListener;
import com.mopub.mraid.MraidController.UseCustomCloseListener;
import com.mopub.mraid.MraidWebViewClient;
import com.mopub.mraid.MraidWebViewDebugListener;
import com.mopub.mraid.PlacementType;
import com.mopub.network.Networking;

public class MraidActivity extends BaseInterstitialActivity {
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    /* access modifiers changed from: private */
    @Nullable
    public ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    /* access modifiers changed from: private */
    @Nullable
    public MraidController mMraidController;

    public static void preRenderHtml(@NonNull Interstitial interstitial, @NonNull Context context, @NonNull CustomEventInterstitialListener customEventInterstitialListener, @Nullable String str, @NonNull Long l) {
        Preconditions.checkNotNull(interstitial);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(customEventInterstitialListener);
        Preconditions.checkNotNull(l);
        preRenderHtml(interstitial, customEventInterstitialListener, str, (BaseWebView) new MraidWebView(context), l);
    }

    @VisibleForTesting
    static void preRenderHtml(@NonNull Interstitial interstitial, @NonNull final CustomEventInterstitialListener customEventInterstitialListener, @Nullable String str, @NonNull BaseWebView baseWebView, @NonNull Long l) {
        Preconditions.checkNotNull(interstitial);
        Preconditions.checkNotNull(customEventInterstitialListener);
        Preconditions.checkNotNull(baseWebView);
        Preconditions.checkNotNull(l);
        baseWebView.enablePlugins(false);
        baseWebView.enableJavascriptCaching();
        baseWebView.setWebViewClient(new MraidWebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if ("mopub://failLoad".equals(str)) {
                    customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
                }
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                customEventInterstitialListener.onInterstitialLoaded();
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
            }
        });
        Context context = baseWebView.getContext();
        ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(context);
        externalViewabilitySessionManager.createDisplaySession(context, baseWebView, true);
        StringBuilder sb = new StringBuilder();
        sb.append(Networking.getBaseUrlScheme());
        sb.append("://");
        sb.append(Constants.HOST);
        sb.append("/");
        baseWebView.loadDataWithBaseURL(sb.toString(), str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
        WebViewCacheService.storeWebViewConfig(l, interstitial, baseWebView, externalViewabilitySessionManager);
    }

    public static void start(@NonNull Context context, @Nullable AdReport adReport, @Nullable String str, long j) {
        try {
            context.startActivity(createIntent(context, adReport, str, j));
        } catch (ActivityNotFoundException unused) {
            Log.d("MraidInterstitial", "MraidActivity.class not found. Did you declare MraidActivity in your manifest?");
        }
    }

    @VisibleForTesting
    protected static Intent createIntent(@NonNull Context context, @Nullable AdReport adReport, @Nullable String str, long j) {
        Intent intent = new Intent(context, MraidActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, str);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        return intent;
    }

    public View getAdView() {
        String stringExtra = getIntent().getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        if (stringExtra == null) {
            MoPubLog.w("MraidActivity received a null HTML body. Finishing the activity.");
            finish();
            return new View(this);
        }
        this.mMraidController = new MraidController(this, this.mAdReport, PlacementType.INTERSTITIAL);
        this.mMraidController.setDebugListener(this.mDebugListener);
        this.mMraidController.setMraidListener(new MraidListener() {
            public void onExpand() {
            }

            public void onLoaded(View view) {
                MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
            }

            public void onFailedToLoad() {
                MoPubLog.d("MraidActivity failed to load. Finishing the activity");
                if (MraidActivity.this.getBroadcastIdentifier() != null) {
                    MraidActivity mraidActivity = MraidActivity.this;
                    EventForwardingBroadcastReceiver.broadcastAction(mraidActivity, mraidActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_FAIL);
                }
                MraidActivity.this.finish();
            }

            public void onClose() {
                MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getJavascript());
                MraidActivity.this.finish();
            }

            public void onOpen() {
                if (MraidActivity.this.getBroadcastIdentifier() != null) {
                    MraidActivity mraidActivity = MraidActivity.this;
                    EventForwardingBroadcastReceiver.broadcastAction(mraidActivity, mraidActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_CLICK);
                }
            }
        });
        this.mMraidController.setUseCustomCloseListener(new UseCustomCloseListener() {
            public void useCustomCloseChanged(boolean z) {
                if (z) {
                    MraidActivity.this.hideInterstitialCloseButton();
                } else {
                    MraidActivity.this.showInterstitialCloseButton();
                }
            }
        });
        this.mMraidController.fillContent(getBroadcastIdentifier(), stringExtra, new MraidWebViewCacheListener() {
            public void onReady(@NonNull MraidWebView mraidWebView, @Nullable ExternalViewabilitySessionManager externalViewabilitySessionManager) {
                if (externalViewabilitySessionManager != null) {
                    MraidActivity.this.mExternalViewabilitySessionManager = externalViewabilitySessionManager;
                    return;
                }
                MraidActivity mraidActivity = MraidActivity.this;
                mraidActivity.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(mraidActivity);
                MraidActivity.this.mExternalViewabilitySessionManager.createDisplaySession(MraidActivity.this, mraidWebView, true);
            }
        });
        return this.mMraidController.getAdContainer();
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.startDeferredDisplaySession(this);
        }
        if (getBroadcastIdentifier() != null) {
            EventForwardingBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_SHOW);
        }
        getWindow().setFlags(C.DEFAULT_MUXED_BUFFER_SIZE, C.DEFAULT_MUXED_BUFFER_SIZE);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.pause(isFinishing());
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.resume();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.destroy();
        }
        if (getBroadcastIdentifier() != null) {
            EventForwardingBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_DISMISS);
        }
        super.onDestroy();
    }

    @VisibleForTesting
    public void setDebugListener(@Nullable MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.setDebugListener(mraidWebViewDebugListener);
        }
    }
}
