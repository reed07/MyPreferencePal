package com.myfitnesspal.feature.home.util;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.video.util.VideoUtil;
import com.myfitnesspal.shared.constants.Constants.ABTest.SharethroughTest201608;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.uacf.core.util.Ln;
import dagger.Lazy;

public class NativeAdDataObserverUtil {
    private static final int DEFAULT_DISPLAY_INTERVAL_ADS = 20;
    private static final int DEFAULT_INDEX_MOD_ADS = 1;
    /* access modifiers changed from: private */
    public Adapter<RecyclerViewHolder<NewsFeedItem>> adapter;
    private final int adsDisplayInterval;
    private final int adsIndexMod;
    private AdapterDataObserver dataObserver = new AdapterDataObserver() {
        public void onChanged() {
            super.onChanged();
            NativeAdDataObserverUtil.this.adapter.notifyDataSetChanged();
        }

        public void onItemRangeChanged(int i, int i2) {
            super.onItemRangeChanged(i, i2);
            NativeAdDataObserverUtil.this.adapter.notifyItemRangeChanged(i + NativeAdDataObserverUtil.this.getNumberOfAdsShown(i), i2);
        }

        public void onItemRangeInserted(int i, int i2) {
            super.onItemRangeInserted(i, i2);
            NativeAdDataObserverUtil.this.adapter.notifyItemRangeInserted(i + NativeAdDataObserverUtil.this.getNumberOfAdsShown(i), i2);
        }

        public void onItemRangeRemoved(int i, int i2) {
            super.onItemRangeRemoved(i, i2);
            NativeAdDataObserverUtil.this.adapter.notifyItemRangeRemoved(i + NativeAdDataObserverUtil.this.getNumberOfAdsShown(i), i2);
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            super.onItemRangeMoved(i, i2, i3);
            NativeAdDataObserverUtil.this.adapter.notifyItemRangeChanged(i + NativeAdDataObserverUtil.this.getNumberOfAdsShown(i), i2 + NativeAdDataObserverUtil.this.getNumberOfAdsShown(i2) + i3);
        }
    };
    private final boolean isAdAutoPlay;
    private final Lazy<PremiumService> premiumService;

    public NativeAdDataObserverUtil(Adapter<RecyclerViewHolder<NewsFeedItem>> adapter2, Lazy<ConfigService> lazy, Lazy<PremiumService> lazy2, Lazy<LocalSettingsService> lazy3, Lazy<UserApplicationSettingsService> lazy4) {
        this.adapter = adapter2;
        this.premiumService = lazy2;
        this.adsDisplayInterval = getAdsPropertyValue(lazy, SharethroughTest201608.DISPLAY_INTERVAL_PROPERTY, 20);
        boolean z = true;
        this.adsIndexMod = getAdsPropertyValue(lazy, SharethroughTest201608.START_INDEX_PROPERTY, 1) + 1;
        if (!((LocalSettingsService) lazy3.get()).shouldNativeAdVideoAutoPlay() || VideoUtil.isVideoAutoPlayOn(lazy4, lazy)) {
            z = false;
        }
        this.isAdAutoPlay = z;
    }

    private int getAdsPropertyValue(Lazy<ConfigService> lazy, String str, int i) {
        String aBTestPropertyValueIfVariantEnabled = ((ConfigService) lazy.get()).getABTestPropertyValueIfVariantEnabled(SharethroughTest201608.NAME, str);
        try {
            return Integer.parseInt(aBTestPropertyValueIfVariantEnabled);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Value for ad property: ");
            sb.append(str);
            sb.append(" isn't an integer: ");
            sb.append(aBTestPropertyValueIfVariantEnabled);
            Ln.e(sb.toString(), new Object[0]);
            return i;
        }
    }

    public int getItemCount(int i) {
        return i + (isAdsFreeSubscribed() ? 0 : getNumberOfAdsShown(i));
    }

    public boolean shouldReturnAd(int i, boolean z) {
        boolean z2 = false;
        if (this.adsDisplayInterval <= 0) {
            return false;
        }
        if (!isAdsFreeSubscribed() && i % this.adsDisplayInterval == this.adsIndexMod && !z) {
            z2 = true;
        }
        return z2;
    }

    public int getNewsFeedItemPosition(int i) {
        return Math.max(i - getNumberOfAdsShown(i), 0);
    }

    public int getNumberOfAdsShown(int i) {
        if (!isAdsFreeSubscribed()) {
            int i2 = this.adsDisplayInterval;
            if (i2 > 0) {
                return i % i2 < this.adsIndexMod ? i / i2 : (i / i2) + 1;
            }
        }
        return 0;
    }

    private boolean isAdsFreeSubscribed() {
        return ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.AdFree);
    }

    public AdapterDataObserver getObserver() {
        return this.dataObserver;
    }

    public boolean isAdAutoPlay() {
        return this.isAdAutoPlay;
    }
}
