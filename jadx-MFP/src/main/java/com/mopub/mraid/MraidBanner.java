package com.mopub.mraid;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.AdViewController;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.factories.MraidControllerFactory;
import com.mopub.mraid.MraidBridge.MraidWebView;
import com.mopub.mraid.MraidController.MraidListener;
import com.mopub.mraid.MraidController.MraidWebViewCacheListener;
import java.util.Map;

class MraidBanner extends CustomEventBanner {
    /* access modifiers changed from: private */
    public boolean mBannerImpressionPixelCountEnabled = false;
    /* access modifiers changed from: private */
    @Nullable
    public CustomEventBannerListener mBannerListener;
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    /* access modifiers changed from: private */
    @Nullable
    public ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    @Nullable
    private MraidController mMraidController;

    MraidBanner() {
    }

    /* access modifiers changed from: protected */
    public void loadBanner(@NonNull final Context context, @NonNull CustomEventBannerListener customEventBannerListener, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) {
        this.mBannerListener = customEventBannerListener;
        if (extrasAreValid(map2)) {
            String str = (String) map2.get(DataKeys.HTML_RESPONSE_BODY_KEY);
            Object obj = map.get(DataKeys.BANNER_IMPRESSION_PIXEL_COUNT_ENABLED);
            if (obj instanceof Boolean) {
                this.mBannerImpressionPixelCountEnabled = ((Boolean) obj).booleanValue();
            }
            try {
                this.mMraidController = MraidControllerFactory.create(context, (AdReport) map.get(DataKeys.AD_REPORT_KEY), PlacementType.INLINE);
                this.mMraidController.setDebugListener(this.mDebugListener);
                this.mMraidController.setMraidListener(new MraidListener() {
                    public void onLoaded(View view) {
                        AdViewController.setShouldHonorServerDimensions(view);
                        MraidBanner.this.mBannerListener.onBannerLoaded(view);
                    }

                    public void onFailedToLoad() {
                        MraidBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
                    }

                    public void onExpand() {
                        MraidBanner.this.mBannerListener.onBannerExpanded();
                        MraidBanner.this.mBannerListener.onBannerClicked();
                    }

                    public void onOpen() {
                        MraidBanner.this.mBannerListener.onBannerClicked();
                    }

                    public void onClose() {
                        MraidBanner.this.mBannerListener.onBannerCollapsed();
                    }
                });
                this.mMraidController.fillContent(null, str, new MraidWebViewCacheListener() {
                    public void onReady(@NonNull MraidWebView mraidWebView, @Nullable ExternalViewabilitySessionManager externalViewabilitySessionManager) {
                        mraidWebView.getSettings().setJavaScriptEnabled(true);
                        Context context = context;
                        if (context instanceof Activity) {
                            MraidBanner.this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(context);
                            MraidBanner.this.mExternalViewabilitySessionManager.createDisplaySession(context, mraidWebView, MraidBanner.this.mBannerImpressionPixelCountEnabled);
                        }
                    }
                });
            } catch (ClassCastException e) {
                MoPubLog.w("MRAID banner creating failed:", e);
                this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
            }
        } else {
            this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.setMraidListener(null);
            this.mMraidController.destroy();
        }
    }

    /* access modifiers changed from: protected */
    public void trackMpxAndThirdPartyImpressions() {
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
            if (this.mBannerImpressionPixelCountEnabled && this.mExternalViewabilitySessionManager != null) {
                Activity activity = (Activity) this.mMraidController.getWeakActivity().get();
                if (activity != null) {
                    this.mExternalViewabilitySessionManager.startDeferredDisplaySession(activity);
                } else {
                    MoPubLog.d("Lost the activity for deferred Viewability tracking. Dropping session.");
                }
            }
        }
    }

    private boolean extrasAreValid(@NonNull Map<String, String> map) {
        return map.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
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
