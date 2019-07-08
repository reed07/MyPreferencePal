package com.mopub.mobileads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.CacheService;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Json;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.VastManager.VastManagerListener;
import com.mopub.mobileads.factories.VastManagerFactory;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class VastVideoInterstitial extends ResponseBodyInterstitial implements VastManagerListener {
    private CustomEventInterstitialListener mCustomEventInterstitialListener;
    @Nullable
    private Map<String, String> mExternalViewabilityTrackers;
    private VastManager mVastManager;
    private String mVastResponse;
    private VastVideoConfig mVastVideoConfig;
    @Nullable
    private JSONObject mVideoTrackers;

    VastVideoInterstitial() {
    }

    /* access modifiers changed from: protected */
    public void extractExtras(Map<String, String> map) {
        this.mVastResponse = (String) map.get(DataKeys.HTML_RESPONSE_BODY_KEY);
        String str = (String) map.get(DataKeys.EXTERNAL_VIDEO_VIEWABILITY_TRACKERS_KEY);
        try {
            this.mExternalViewabilityTrackers = Json.jsonStringToMap(str);
        } catch (JSONException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to parse video viewability trackers to JSON: ");
            sb.append(str);
            MoPubLog.d(sb.toString());
        }
        String str2 = (String) map.get(DataKeys.VIDEO_TRACKERS_KEY);
        if (!TextUtils.isEmpty(str2)) {
            try {
                this.mVideoTrackers = new JSONObject(str2);
            } catch (JSONException e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Failed to parse video trackers to JSON: ");
                sb2.append(str2);
                MoPubLog.d(sb2.toString(), e);
                this.mVideoTrackers = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void preRenderHtml(CustomEventInterstitialListener customEventInterstitialListener) {
        this.mCustomEventInterstitialListener = customEventInterstitialListener;
        if (!CacheService.initializeDiskCache(this.mContext)) {
            this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.VIDEO_CACHE_ERROR);
            return;
        }
        this.mVastManager = VastManagerFactory.create(this.mContext);
        this.mVastManager.prepareVastVideoConfiguration(this.mVastResponse, this, this.mAdReport.getDspCreativeId(), this.mContext);
    }

    public void showInterstitial() {
        MraidVideoPlayerActivity.startVast(this.mContext, this.mVastVideoConfig, this.mBroadcastIdentifier);
    }

    public void onInvalidate() {
        VastManager vastManager = this.mVastManager;
        if (vastManager != null) {
            vastManager.cancel();
        }
        super.onInvalidate();
    }

    public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig) {
        if (vastVideoConfig == null) {
            this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.VIDEO_DOWNLOAD_ERROR);
            return;
        }
        this.mVastVideoConfig = vastVideoConfig;
        this.mVastVideoConfig.addVideoTrackers(this.mVideoTrackers);
        this.mVastVideoConfig.addExternalViewabilityTrackers(this.mExternalViewabilityTrackers);
        this.mCustomEventInterstitialListener.onInterstitialLoaded();
    }
}
