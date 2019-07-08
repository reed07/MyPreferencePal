package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.Preconditions;
import com.mopub.network.AdLoader.Listener;
import com.mopub.network.AdResponse;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;
import java.util.HashMap;

class RewardedAdsLoaders {
    @NonNull
    private final HashMap<String, AdLoaderRewardedVideo> mAdUnitToAdLoader = new HashMap<>();
    /* access modifiers changed from: private */
    @NonNull
    public final MoPubRewardedVideoManager moPubRewardedVideoManager;

    public class RewardedVideoRequestListener implements Listener {
        public final String adUnitId;

        RewardedVideoRequestListener(String str) {
            this.adUnitId = str;
        }

        public void onSuccess(AdResponse adResponse) {
            RewardedAdsLoaders.this.moPubRewardedVideoManager.onAdSuccess(adResponse);
        }

        public void onErrorResponse(VolleyError volleyError) {
            RewardedAdsLoaders.this.moPubRewardedVideoManager.onAdError(volleyError, this.adUnitId);
        }
    }

    RewardedAdsLoaders(@NonNull MoPubRewardedVideoManager moPubRewardedVideoManager2) {
        this.moPubRewardedVideoManager = moPubRewardedVideoManager2;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Request<?> loadNextAd(@NonNull Context context, @NonNull String str, @NonNull String str2, @Nullable MoPubErrorCode moPubErrorCode) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(context);
        AdLoaderRewardedVideo adLoaderRewardedVideo = (AdLoaderRewardedVideo) this.mAdUnitToAdLoader.get(str);
        if (adLoaderRewardedVideo == null || !adLoaderRewardedVideo.hasMoreAds()) {
            adLoaderRewardedVideo = new AdLoaderRewardedVideo(str2, AdFormat.REWARDED_VIDEO, str, context, new RewardedVideoRequestListener(str));
            this.mAdUnitToAdLoader.put(str, adLoaderRewardedVideo);
        }
        return adLoaderRewardedVideo.loadNextAd(moPubErrorCode);
    }

    /* access modifiers changed from: 0000 */
    public boolean isLoading(@NonNull String str) {
        return this.mAdUnitToAdLoader.containsKey(str) && ((AdLoaderRewardedVideo) this.mAdUnitToAdLoader.get(str)).isRunning();
    }

    /* access modifiers changed from: 0000 */
    public void markFail(@NonNull String str) {
        Preconditions.checkNotNull(str);
        if (this.mAdUnitToAdLoader.containsKey(str)) {
            this.mAdUnitToAdLoader.remove(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public void markPlayed(@NonNull String str) {
        Preconditions.checkNotNull(str);
        if (this.mAdUnitToAdLoader.containsKey(str)) {
            this.mAdUnitToAdLoader.remove(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public void onRewardedVideoStarted(@NonNull String str, @NonNull Context context) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(context);
        AdLoaderRewardedVideo adLoaderRewardedVideo = (AdLoaderRewardedVideo) this.mAdUnitToAdLoader.get(str);
        if (adLoaderRewardedVideo != null) {
            adLoaderRewardedVideo.trackImpression(context);
        }
    }

    /* access modifiers changed from: 0000 */
    public void onRewardedVideoClicked(@NonNull String str, @NonNull Context context) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(context);
        AdLoaderRewardedVideo adLoaderRewardedVideo = (AdLoaderRewardedVideo) this.mAdUnitToAdLoader.get(str);
        if (adLoaderRewardedVideo != null) {
            adLoaderRewardedVideo.trackClick(context);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean canPlay(@NonNull String str) {
        AdLoaderRewardedVideo adLoaderRewardedVideo = (AdLoaderRewardedVideo) this.mAdUnitToAdLoader.get(str);
        boolean z = false;
        if (adLoaderRewardedVideo == null) {
            return false;
        }
        if (adLoaderRewardedVideo.getLastDeliveredResponse() != null) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasMoreAds(@NonNull String str) {
        AdLoaderRewardedVideo adLoaderRewardedVideo = (AdLoaderRewardedVideo) this.mAdUnitToAdLoader.get(str);
        return adLoaderRewardedVideo != null && adLoaderRewardedVideo.hasMoreAds();
    }

    /* access modifiers changed from: 0000 */
    public void creativeDownloadSuccess(@NonNull String str) {
        AdLoaderRewardedVideo adLoaderRewardedVideo = (AdLoaderRewardedVideo) this.mAdUnitToAdLoader.get(str);
        if (adLoaderRewardedVideo != null) {
            adLoaderRewardedVideo.creativeDownloadSuccess();
        }
    }
}
