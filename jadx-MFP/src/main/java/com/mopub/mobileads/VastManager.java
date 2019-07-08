package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.common.CacheService;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;

public class VastManager implements VastXmlManagerAggregatorListener {
    @Nullable
    private String mDspCreativeId;
    private int mScreenAreaDp;
    private double mScreenAspectRatio;
    private final boolean mShouldPreCacheVideo;
    /* access modifiers changed from: private */
    @Nullable
    public VastManagerListener mVastManagerListener;
    @Nullable
    private VastXmlManagerAggregator mVastXmlManagerAggregator;

    public interface VastManagerListener {
        void onVastVideoConfigurationPrepared(@Nullable VastVideoConfig vastVideoConfig);
    }

    public VastManager(@NonNull Context context, boolean z) {
        initializeScreenDimensions(context);
        this.mShouldPreCacheVideo = z;
    }

    public void prepareVastVideoConfiguration(@Nullable String str, @NonNull VastManagerListener vastManagerListener, @Nullable String str2, @NonNull Context context) {
        Preconditions.checkNotNull(vastManagerListener, "vastManagerListener cannot be null");
        Preconditions.checkNotNull(context, "context cannot be null");
        if (this.mVastXmlManagerAggregator == null) {
            this.mVastManagerListener = vastManagerListener;
            VastXmlManagerAggregator vastXmlManagerAggregator = new VastXmlManagerAggregator(this, this.mScreenAspectRatio, this.mScreenAreaDp, context.getApplicationContext());
            this.mVastXmlManagerAggregator = vastXmlManagerAggregator;
            this.mDspCreativeId = str2;
            try {
                AsyncTasks.safeExecuteOnExecutor(this.mVastXmlManagerAggregator, str);
            } catch (Exception e) {
                MoPubLog.d("Failed to aggregate vast xml", e);
                this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
            }
        }
    }

    public void cancel() {
        VastXmlManagerAggregator vastXmlManagerAggregator = this.mVastXmlManagerAggregator;
        if (vastXmlManagerAggregator != null) {
            vastXmlManagerAggregator.cancel(true);
            this.mVastXmlManagerAggregator = null;
        }
    }

    public void onAggregationComplete(@Nullable final VastVideoConfig vastVideoConfig) {
        VastManagerListener vastManagerListener = this.mVastManagerListener;
        if (vastManagerListener == null) {
            throw new IllegalStateException("mVastManagerListener cannot be null here. Did you call prepareVastVideoConfiguration()?");
        } else if (vastVideoConfig == null) {
            vastManagerListener.onVastVideoConfigurationPrepared(null);
        } else {
            if (!TextUtils.isEmpty(this.mDspCreativeId)) {
                vastVideoConfig.setDspCreativeId(this.mDspCreativeId);
            }
            if (!this.mShouldPreCacheVideo || updateDiskMediaFileUrl(vastVideoConfig)) {
                this.mVastManagerListener.onVastVideoConfigurationPrepared(vastVideoConfig);
                return;
            }
            VideoDownloader.cache(vastVideoConfig.getNetworkMediaFileUrl(), new VideoDownloaderListener() {
                public void onComplete(boolean z) {
                    if (!z || !VastManager.this.updateDiskMediaFileUrl(vastVideoConfig)) {
                        MoPubLog.d("Failed to download VAST video.");
                        VastManager.this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
                        return;
                    }
                    VastManager.this.mVastManagerListener.onVastVideoConfigurationPrepared(vastVideoConfig);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean updateDiskMediaFileUrl(@NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        String networkMediaFileUrl = vastVideoConfig.getNetworkMediaFileUrl();
        if (!CacheService.containsKeyDiskCache(networkMediaFileUrl)) {
            return false;
        }
        vastVideoConfig.setDiskMediaFileUrl(CacheService.getFilePathDiskCache(networkMediaFileUrl));
        return true;
    }

    private void initializeScreenDimensions(@NonNull Context context) {
        Preconditions.checkNotNull(context, "context cannot be null");
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        float f = context.getResources().getDisplayMetrics().density;
        if (f <= BitmapDescriptorFactory.HUE_RED) {
            f = 1.0f;
        }
        int max = Math.max(width, height);
        int min = Math.min(width, height);
        this.mScreenAspectRatio = ((double) max) / ((double) min);
        this.mScreenAreaDp = (int) ((((float) max) / f) * (((float) min) / f));
    }
}
