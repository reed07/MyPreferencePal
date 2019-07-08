package com.myfitnesspal.feature.home.ui.adapter;

import android.view.ViewGroup;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.model.NewsFeedViewTypes;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.ui.adapter.NewsFeedAdapter.AdapterOperationListener;
import com.myfitnesspal.feature.home.ui.view.DfpNativeViewHolder;
import com.myfitnesspal.feature.home.util.NativeAdDataObserverUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import dagger.Lazy;
import java.util.List;

public class DfpNativeAdsAdapter extends NativeAdsAdapter<RecyclerViewHolder<NewsFeedItem>> {
    public static final int TIME_IGNORED_AT_RELODING_AD = 3000;
    private final Lazy<AdUnitService> adUnitService;
    private final Lazy<AdsSettings> adsSettings;
    private Lazy<ConfigService> configService;
    private boolean isCountryUS;
    private int lastShowedPosition;
    private Lazy<LocalSettingsService> localSettingsService;
    private Lazy<LocationService> locationService;
    private NativeAdDataObserverUtil nativeAdDataObserverUtil;
    private final NewsFeedAdapter newsFeedAdapter;
    private final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    private long onBindAdTimestamp;
    private Lazy<PremiumService> premiumService;
    private Lazy<UserApplicationSettingsService> userApplicationSettingsService;

    public DfpNativeAdsAdapter(Lazy<PremiumService> lazy, NewsFeedAdapter newsFeedAdapter2, Lazy<ConfigService> lazy2, Lazy<UserApplicationSettingsService> lazy3, Lazy<LocalSettingsService> lazy4, Lazy<LocationService> lazy5, Lazy<AdsSettings> lazy6, Lazy<NewsFeedAnalyticsHelper> lazy7, Lazy<AdUnitService> lazy8, Boolean bool) {
        super(lazy);
        this.premiumService = lazy;
        this.newsFeedAdapter = newsFeedAdapter2;
        this.configService = lazy2;
        this.userApplicationSettingsService = lazy3;
        this.localSettingsService = lazy4;
        this.locationService = lazy5;
        this.adsSettings = lazy6;
        this.newsFeedAnalyticsHelper = lazy7;
        this.adUnitService = lazy8;
        this.isCountryUS = bool.booleanValue();
    }

    public void onCreate() {
        registerAdapterObserver();
    }

    public void onPause() {
        this.newsFeedAdapter.onPause();
    }

    public void resetDailySummary() {
        this.newsFeedAdapter.resetDailySummary();
    }

    public void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        this.newsFeedAdapter.onCardCloseClick(mfpNewsFeedActivityEntry);
    }

    public void refreshItems(List<NewsFeedItem> list) {
        this.newsFeedAdapter.refreshItems(list);
    }

    public void setAdapterOperationListener(AdapterOperationListener adapterOperationListener) {
        this.newsFeedAdapter.setAdapterOperationListener(adapterOperationListener);
    }

    public void setNewsFeedItemActionListener(NewsFeedItemActionListener newsFeedItemActionListener) {
        this.newsFeedAdapter.setNewsFeedItemActionListener(newsFeedItemActionListener);
    }

    public void onResume(MfpFragment mfpFragment) {
        this.newsFeedAdapter.onResume(mfpFragment);
    }

    public void destroy() {
        if (this.newsFeedAdapter.hasObservers()) {
            this.newsFeedAdapter.unregisterAdapterDataObserver(this.nativeAdDataObserverUtil.getObserver());
        }
    }

    public int getItemViewType(int i) {
        if (!this.nativeAdDataObserverUtil.shouldReturnAd(i, isStopped()) || this.nativeAdDataObserverUtil.getNumberOfAdsShown(i) > 2) {
            return this.newsFeedAdapter.getItemViewType(this.nativeAdDataObserverUtil.getNewsFeedItemPosition(i));
        }
        return NewsFeedViewTypes.DfpNativeAd.ordinal();
    }

    public RecyclerViewHolder<NewsFeedItem> onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != NewsFeedViewTypes.DfpNativeAd.ordinal()) {
            return this.newsFeedAdapter.onCreateViewHolder(viewGroup, i);
        }
        DfpNativeViewHolder dfpNativeViewHolder = new DfpNativeViewHolder(viewGroup, this.adsSettings, this.configService, this.localSettingsService, this.locationService, this.newsFeedAnalyticsHelper, this.adUnitService, this.isCountryUS);
        return dfpNativeViewHolder;
    }

    public void onBindViewHolder(RecyclerViewHolder<NewsFeedItem> recyclerViewHolder, int i) {
        if (recyclerViewHolder instanceof DfpNativeViewHolder) {
            tryToUpdateHolderData(recyclerViewHolder, i);
        } else {
            this.newsFeedAdapter.onBindViewHolder(recyclerViewHolder, this.nativeAdDataObserverUtil.getNewsFeedItemPosition(i));
        }
    }

    private void tryToUpdateHolderData(RecyclerViewHolder<NewsFeedItem> recyclerViewHolder, int i) {
        if (System.currentTimeMillis() - this.onBindAdTimestamp > 3000 || i != this.lastShowedPosition) {
            recyclerViewHolder.setData(null, i);
        }
        this.onBindAdTimestamp = System.currentTimeMillis();
        this.lastShowedPosition = i;
    }

    public int getItemCount() {
        if (this.nativeAdDataObserverUtil == null) {
            registerAdapterObserver();
        }
        return this.nativeAdDataObserverUtil.getItemCount(this.newsFeedAdapter.getItemCount());
    }

    private void registerAdapterObserver() {
        NativeAdDataObserverUtil nativeAdDataObserverUtil2 = new NativeAdDataObserverUtil(this, this.configService, this.premiumService, this.localSettingsService, this.userApplicationSettingsService);
        this.nativeAdDataObserverUtil = nativeAdDataObserverUtil2;
        this.newsFeedAdapter.registerAdapterDataObserver(this.nativeAdDataObserverUtil.getObserver());
    }
}
