package com.myfitnesspal.shared.util;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.DTBAdCallback;
import com.amazon.device.ads.DTBAdRequest;
import com.amazon.device.ads.DTBAdResponse;
import com.amazon.device.ads.DTBAdSize;
import com.amazon.device.ads.DTBAdUtil;
import com.amazon.device.ads.MRAIDPolicy;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.moat.analytics.mobile.und.MoatFactory;
import com.moat.analytics.mobile.und.WebAdTracker;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.constants.Constants.ABTest.AppNavUpdates;
import com.myfitnesspal.shared.constants.Constants.ABTest.MoatSDKRefreshRateAndroid;
import com.myfitnesspal.shared.constants.Constants.ABTest.MoatSdkIntegration;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.model.AdNetworkType;
import com.myfitnesspal.shared.model.AdType;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class AdsHelper {
    private static final long ADS_AUTO_REFRESH_INTERVAL_MS = TimeUnit.SECONDS.toMillis(25);
    private static final String AMAZON_TAG = "AMAZON";
    private static final String ATTRIBUTE_KEY_ROLLOUT_NAME = "rollout_name";
    private static final String ATTRIBUTE_KEY_ROLLOUT_VARIANT = "rollout_variant";
    private static final String BANNER_AD_REQUEST_TIME = "banner_ad_request_time";
    private static final String BASE_AD_TAG = "ADS";
    private static final String DFP_CUSTOM_TARGETING_FOOD_CATEGORY = "food_cat";
    private static final String DFP_TAG = "DFP";
    private static final String OG_HB_MOBILE_TARGER_KEY = "og_hb_mobile";
    private static final String OG_HB_MOBILE_TARGER_VLAUE = "yes";
    private static final long TIME_TO_WAIT_FOR_AD_MS = TimeUnit.SECONDS.toMillis(10);
    private static final long TIME_TO_WAIT_FOR_AMAZON_AD_MS = TimeUnit.SECONDS.toMillis(1);
    private final int adContainerHeight;
    /* access modifiers changed from: private */
    public AdListener adListener;
    /* access modifiers changed from: private */
    public AdNetworkType adNetworkType;
    /* access modifiers changed from: private */
    public long adRequestedTime;
    /* access modifiers changed from: private */
    public final AdSize adSize;
    /* access modifiers changed from: private */
    public final AdUnit adUnit;
    /* access modifiers changed from: private */
    public PublisherAdView adView;
    /* access modifiers changed from: private */
    public final LayoutParams adViewParams;
    /* access modifiers changed from: private */
    public final Lazy<AdsAnalyticsHelper> adsAnalytics;
    /* access modifiers changed from: private */
    public AdsLoadState adsLoadState;
    private final View adsProgress;
    private final View adsProgressContainer;
    private final Lazy<AdsSettings> adsSettings;
    private boolean allowPremiumUpsell;
    /* access modifiers changed from: private */
    public final Lazy<ConfigService> configService;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final Map<String, String> customTargeting;
    /* access modifiers changed from: private */
    public DfpAdsListener dfpAdListener;
    private final View dfpAdsPremiumBanner;
    /* access modifiers changed from: private */
    public final String displayAnalyticsEventName;
    /* access modifiers changed from: private */
    public int failureAdsCounter;
    /* access modifiers changed from: private */
    public final Handler handler;
    private boolean isAutoRefreshOn;
    private boolean isCountryUS;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<LocationService> locationService;
    /* access modifiers changed from: private */
    public WebAdTracker moatAdTracker;
    /* access modifiers changed from: private */
    public final NavigationHelper navigationHelper;
    /* access modifiers changed from: private */
    public final String openAnalyticsEventName;
    private Runnable premiumAdsRunnable;
    private OnClickListener premiumBannerClickListener;
    private RefreshAdRunnable refreshAdRunnable;
    private final String requestedAdAnalyticsEventName;
    /* access modifiers changed from: private */
    public final ViewGroup rootView;
    /* access modifiers changed from: private */
    public final String screenName;
    private final boolean shouldHideProgressSpinner;
    private Runnable switchToDFPRunnable;
    private final String waterSponsor;

    private enum AdsLoadState {
        NotLoaded,
        Loading,
        Loaded,
        Failed
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public final int adContainerHeight;
        /* access modifiers changed from: private */
        public final AdSize adSize;
        /* access modifiers changed from: private */
        public final AdUnit adUnit;
        /* access modifiers changed from: private */
        public final LayoutParams adViewParams;
        /* access modifiers changed from: private */
        public final Lazy<AdsAnalyticsHelper> adsAnalytics;
        /* access modifiers changed from: private */
        public final ViewGroup adsContainer;
        /* access modifiers changed from: private */
        public final Lazy<AdsSettings> adsSettings;
        /* access modifiers changed from: private */
        public final Lazy<ConfigService> configService;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public Map<String, String> customTargeting;
        /* access modifiers changed from: private */
        public AdNetworkType defaultAdNetworkType;
        /* access modifiers changed from: private */
        public final String displayAnalyticsEventName;
        /* access modifiers changed from: private */
        public boolean isCountryUS;
        /* access modifiers changed from: private */
        public final Lazy<LocalSettingsService> localSettingsService;
        /* access modifiers changed from: private */
        public final Lazy<LocationService> locationService;
        /* access modifiers changed from: private */
        public final NavigationHelper navigationHelper;
        /* access modifiers changed from: private */
        public final String openAnalyticsEventName;
        /* access modifiers changed from: private */
        public final String requestedAdAnalyticsEventName;
        /* access modifiers changed from: private */
        public final String screenName;
        /* access modifiers changed from: private */
        public final boolean shouldHideProgressSpinner;
        /* access modifiers changed from: private */
        public String waterSponsor;

        public Builder(ViewGroup viewGroup, @NonNull AdUnit adUnit2, String str, AdNetworkType adNetworkType, Lazy<ConfigService> lazy, Lazy<LocalSettingsService> lazy2, Lazy<LocationService> lazy3, Lazy<AdsSettings> lazy4, Lazy<AdsAnalyticsHelper> lazy5, NavigationHelper navigationHelper2, boolean z) {
            this(viewGroup, adUnit2, str, adNetworkType, lazy, lazy2, lazy3, lazy4, lazy5, navigationHelper2, false, z);
        }

        public Builder(ViewGroup viewGroup, @NonNull AdUnit adUnit2, String str, AdNetworkType adNetworkType, Lazy<ConfigService> lazy, Lazy<LocalSettingsService> lazy2, Lazy<LocationService> lazy3, Lazy<AdsSettings> lazy4, Lazy<AdsAnalyticsHelper> lazy5, NavigationHelper navigationHelper2, boolean z, boolean z2) {
            this.adsContainer = viewGroup;
            this.adUnit = adUnit2;
            this.context = viewGroup.getContext();
            this.screenName = str;
            this.configService = lazy;
            this.localSettingsService = lazy2;
            this.locationService = lazy3;
            this.adsSettings = lazy4;
            this.adsAnalytics = lazy5;
            this.navigationHelper = navigationHelper2;
            this.customTargeting = new HashMap();
            this.adContainerHeight = adUnit2.getAdType().getAdContainerHeightResId();
            this.adSize = adUnit2.getAdType().getAdSize();
            this.adViewParams = new LinearLayout.LayoutParams(AdsHelper.getDimens(this.context, adUnit2.getAdType().getAdViewWidthResId()), AdsHelper.getDimens(this.context, adUnit2.getAdType().getAdViewHeightResId()));
            this.displayAnalyticsEventName = adUnit2.getAdType().getDisplayAnalyticsEventName();
            this.openAnalyticsEventName = adUnit2.getAdType().getOpenAnalyticsEventName();
            this.requestedAdAnalyticsEventName = adUnit2.getAdType().getRequestedAdAnalyticsEventName();
            this.defaultAdNetworkType = adNetworkType;
            this.shouldHideProgressSpinner = z;
            this.isCountryUS = z2;
        }

        public Builder setCustomTargeting(@Nullable Map<String, String> map) {
            this.customTargeting = map;
            return this;
        }

        public Builder setWaterSponsor(@Nullable String str) {
            this.waterSponsor = str;
            return this;
        }

        public AdsHelper build() {
            return new AdsHelper(this);
        }
    }

    public interface DfpAdsListener {
        void onAdFailedToLoad();

        void onAdLoaded();
    }

    private static class LoggingAdsHelperException extends RuntimeException {
        LoggingAdsHelperException(@Nullable String str) {
            super(str);
        }
    }

    private class RefreshAdRunnable implements Runnable {
        private WeakReference<ViewGroup> adsContainer;

        RefreshAdRunnable(ViewGroup viewGroup) {
            this.adsContainer = new WeakReference<>(viewGroup);
        }

        public void run() {
            ViewGroup viewGroup = (ViewGroup) this.adsContainer.get();
            if (viewGroup != null) {
                Context context = viewGroup.getContext();
                if ((context instanceof MfpActivity) && ((MfpActivity) context).isEnabled()) {
                    AdsHelper.this.failureAdsCounter = 0;
                    AdsHelper.this.adsLoadState = AdsLoadState.Loading;
                    AdsHelper.this.loadAds();
                }
            }
        }
    }

    private AdsHelper(Builder builder) {
        this.allowPremiumUpsell = true;
        this.failureAdsCounter = 0;
        this.adListener = new AdListener() {
            public void onAdClosed() {
                AdsHelper adsHelper = AdsHelper.this;
                adsHelper.logAdEvent(adsHelper.adNetworkType, "onAdClosed");
            }

            public void onAdFailedToLoad(int i) {
                AdsHelper adsHelper = AdsHelper.this;
                AdNetworkType access$2100 = adsHelper.adNetworkType;
                StringBuilder sb = new StringBuilder();
                sb.append("onAdFailedToLoad, error code: ");
                sb.append(i);
                adsHelper.logAdEvent(access$2100, sb.toString());
                AdsHelper.this.handleAdLoadingFailure();
            }

            public void onAdLeftApplication() {
                AdsHelper adsHelper = AdsHelper.this;
                adsHelper.logAdEvent(adsHelper.adNetworkType, "onAdLeftApplication");
            }

            public void onAdOpened() {
                AdsHelper adsHelper = AdsHelper.this;
                adsHelper.logAdEvent(adsHelper.adNetworkType, "onAdOpened");
                ((AdsAnalyticsHelper) AdsHelper.this.adsAnalytics.get()).reportAdEvent(AdsHelper.this.openAnalyticsEventName, AdsHelper.this.screenName, AdsHelper.this.adUnit.getDfpAdUnitId(), AdsHelper.this.getExtraAttributesMap());
            }

            public void onAdLoaded() {
                AdsHelper.this.handler.removeCallbacksAndMessages(null);
                AdsHelper.this.adsLoadState = AdsLoadState.Loaded;
                AdsHelper.this.failureAdsCounter = 0;
                AdsHelper adsHelper = AdsHelper.this;
                adsHelper.logAdEvent(adsHelper.adNetworkType, "onAdLoaded");
                AdsHelper.this.adsLoadState = AdsLoadState.Loaded;
                AdsHelper.this.updateViewVisibility();
                ((AdsAnalyticsHelper) AdsHelper.this.adsAnalytics.get()).reportAdEvent(AdsHelper.this.displayAnalyticsEventName, AdsHelper.this.screenName, AdsHelper.this.adUnit.getDfpAdUnitId(), AdsHelper.this.getExtraAttributesMap());
                if (AdsHelper.this.dfpAdListener != null) {
                    AdsHelper.this.dfpAdListener.onAdLoaded();
                }
                if (AdsHelper.this.adUnit.getAdType() == AdType.BANNER && AdsHelper.this.adRequestedTime > 0) {
                    ((AdsAnalyticsHelper) AdsHelper.this.adsAnalytics.get()).reportAdEvent(AdsHelper.BANNER_AD_REQUEST_TIME, AdsHelper.this.screenName, System.currentTimeMillis() - AdsHelper.this.adRequestedTime, AdsHelper.this.getExtraAttributesMap());
                    AdsHelper.this.adRequestedTime = 0;
                }
                if (((ConfigService) AdsHelper.this.configService.get()).isVariantEnabled(MoatSdkIntegration.NAME)) {
                    if (AdsHelper.this.moatAdTracker != null) {
                        AdsHelper.this.moatAdTracker.stopTracking();
                    }
                    AdsHelper.this.moatAdTracker = MoatFactory.create().createWebAdTracker((ViewGroup) AdsHelper.this.adView);
                    AdsHelper.this.moatAdTracker.startTracking();
                }
                AdsHelper.this.scheduleRefresh();
            }
        };
        this.premiumBannerClickListener = new OnClickListener() {
            public void onClick(View view) {
                String str = Strings.equals(AdsHelper.this.screenName, "Diary") ? Events.PREMIUM_HOUSE_AD_DIARY : null;
                if (Strings.equals(AdsHelper.this.screenName, Screens.PROGRESS)) {
                    str = Events.PREMIUM_HOUSE_AD_PROGRESS;
                }
                if (Strings.equals(AdsHelper.this.screenName, Screens.DIARY_COMPLETE)) {
                    str = Events.PREMIUM_HOUSE_AD_COMPLETE_DIARY;
                }
                AdsHelper.this.navigationHelper.withContext(AdsHelper.this.context).withIntent(PremiumUpsellActivity.newStartIntent(AdsHelper.this.context, str)).startActivity();
            }
        };
        this.premiumAdsRunnable = new Runnable() {
            public void run() {
                if (AdsHelper.this.adsLoadState != AdsLoadState.Loaded) {
                    AdsHelper.this.handleAllAdsLoadingFailed();
                }
            }
        };
        this.switchToDFPRunnable = new Runnable() {
            public void run() {
                if (AdsHelper.this.adsLoadState == AdsLoadState.Loading) {
                    AdsHelper.this.logAdEvent(AdNetworkType.AMAZON, "Long loading, switch to DFP");
                    AdsHelper.this.adNetworkType = AdNetworkType.DFP;
                    AdsHelper.this.loadAds();
                }
            }
        };
        this.screenName = builder.screenName;
        this.adContainerHeight = builder.adContainerHeight;
        this.adSize = builder.adSize;
        this.adViewParams = builder.adViewParams;
        this.displayAnalyticsEventName = builder.displayAnalyticsEventName;
        this.openAnalyticsEventName = builder.openAnalyticsEventName;
        this.requestedAdAnalyticsEventName = builder.requestedAdAnalyticsEventName;
        this.customTargeting = builder.customTargeting;
        this.configService = builder.configService;
        this.localSettingsService = builder.localSettingsService;
        this.locationService = builder.locationService;
        this.adsSettings = builder.adsSettings;
        this.adsAnalytics = builder.adsAnalytics;
        this.navigationHelper = builder.navigationHelper;
        this.context = builder.context;
        this.shouldHideProgressSpinner = builder.shouldHideProgressSpinner;
        this.waterSponsor = builder.waterSponsor;
        this.handler = new Handler();
        this.adUnit = builder.adUnit;
        this.rootView = builder.adsContainer;
        this.adNetworkType = builder.defaultAdNetworkType;
        this.isCountryUS = builder.isCountryUS;
        if (this.adSize != AdSize.FLUID) {
            setContainerHeight(this.rootView);
        }
        this.isAutoRefreshOn = ((ConfigService) this.configService.get()).isVariantEnabled(MoatSDKRefreshRateAndroid.NAME);
        this.refreshAdRunnable = new RefreshAdRunnable(this.rootView);
        this.adsProgress = ViewUtils.findById(this.rootView, R.id.ads_progress);
        View view = this.adsProgress;
        if (view == null || (view instanceof ProgressBar)) {
            this.dfpAdsPremiumBanner = ViewUtils.findById(this.rootView, R.id.ads_premium_banner);
            this.adsProgressContainer = ViewUtils.findById(this.rootView, R.id.ads_progress_container);
            this.adsLoadState = AdsLoadState.NotLoaded;
            updateViewVisibility();
            return;
        }
        throw new IllegalStateException("R.id.ads_progress should always be assigned to a ProgressBar!");
    }

    public View getView() {
        return this.rootView;
    }

    private void setContainerHeight(ViewGroup viewGroup) {
        int dimens = getDimens(this.context, this.adContainerHeight);
        LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.height = dimens + viewGroup.getPaddingTop() + viewGroup.getPaddingBottom();
        viewGroup.setLayoutParams(layoutParams);
    }

    private void addDfpAdsView(@NonNull String str) {
        this.adView = new PublisherAdView(this.context);
        this.adView.setAdSizes(this.adSize);
        this.adView.setAdUnitId(str);
        this.rootView.addView(this.adView, 0, this.adViewParams);
    }

    public void loadAds() {
        this.customTargeting.put(OG_HB_MOBILE_TARGER_KEY, "yes");
        if (this.failureAdsCounter >= AdNetworkType.values().length - 1) {
            this.handler.postDelayed(this.premiumAdsRunnable, TIME_TO_WAIT_FOR_AD_MS);
        }
        if (this.adNetworkType != AdNetworkType.AMAZON) {
            loadDfpAds();
        } else if (canLoadAmazonAds()) {
            loadAmazonAds(this.context, this.rootView, this.adUnit);
        } else {
            handleAdLoadingFailure();
        }
        updateViewVisibility();
    }

    private boolean canLoadAmazonAds() {
        return !Strings.isEmpty(this.adUnit.getAmazonSlotUuid());
    }

    /* access modifiers changed from: private */
    public String getAdsHelperDebugInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Activity: ");
        sb.append(this.context.getClass().getCanonicalName());
        sb.append(" Screen name: ");
        sb.append(this.screenName);
        sb.append(" adUnit: ");
        sb.append(this.adUnit);
        return sb.toString();
    }

    public void loadAdWithCategory(@Nullable String str) {
        this.customTargeting.put(DFP_CUSTOM_TARGETING_FOOD_CATEGORY, str);
        this.isAutoRefreshOn = false;
        loadAds();
    }

    private void loadAmazonAds(final Context context2, @NonNull final ViewGroup viewGroup, @NonNull final AdUnit adUnit2) {
        this.adsLoadState = AdsLoadState.Loading;
        logAdEvent(AdNetworkType.AMAZON, "Loading ad");
        AdRegistration.getInstance(((AdsSettings) this.adsSettings.get()).getAmazonAppId(), context2);
        AdRegistration.useGeoLocation(true);
        AdRegistration.setMRAIDSupportedVersions(new String[]{"1.0", "2.0", "3.0"});
        AdRegistration.setMRAIDPolicy(MRAIDPolicy.CUSTOM);
        DTBAdRequest dTBAdRequest = new DTBAdRequest();
        dTBAdRequest.setSizes(new DTBAdSize(this.adSize.getWidth(), this.adSize.getHeight(), adUnit2.getAmazonSlotUuid()));
        this.handler.postDelayed(this.switchToDFPRunnable, TIME_TO_WAIT_FOR_AMAZON_AD_MS);
        dTBAdRequest.loadAd(new DTBAdCallback() {
            public void onFailure(@NonNull AdError adError) {
                if (AdsHelper.this.adNetworkType == AdNetworkType.AMAZON) {
                    AdsHelper.this.handleAdLoadingFailure();
                }
                if (adError.getCode() == null || adError.getMessage() == null) {
                    AdsHelper.this.logAdEvent(AdNetworkType.AMAZON, "onFailure");
                    return;
                }
                AdsHelper.this.logAdEvent(AdNetworkType.AMAZON, String.format("onFailure: %s %s", new Object[]{adError.getCode().name(), adError.getMessage()}));
            }

            public void onSuccess(@NonNull DTBAdResponse dTBAdResponse) {
                if (AdsHelper.this.adNetworkType == AdNetworkType.AMAZON) {
                    AdsHelper.this.handler.removeCallbacksAndMessages(null);
                    String amazonAdUnitId = adUnit2.getAmazonAdUnitId();
                    AdsHelper.this.logAdEvent(AdNetworkType.AMAZON, "onSuccess");
                    if (amazonAdUnitId == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("AmazonAdUnitId is null but amazonRequest completed successfully. ");
                        sb.append(AdsHelper.this.getAdsHelperDebugInfo());
                        CrashlyticsUtil.logIfEnabled((Throwable) new LoggingAdsHelperException(sb.toString()));
                        AdsHelper.this.logAdEvent(AdNetworkType.AMAZON, "amazonAdUnitId == null");
                        AdsHelper.this.handleAdLoadingFailure();
                        return;
                    }
                    AdsHelper.this.adsLoadState = AdsLoadState.Loaded;
                    AdsHelper.this.rootView.removeView(AdsHelper.this.adView);
                    AdsHelper.this.adViewCleanup();
                    AdsHelper.this.adView = new PublisherAdView(context2);
                    AdsHelper.this.adView.setAdUnitId(amazonAdUnitId);
                    AdsHelper.this.adView.setAdListener(AdsHelper.this.adListener);
                    AdsHelper.this.adView.setAdSizes(new AdSize(AdsHelper.this.adSize.getWidth(), AdsHelper.this.adSize.getHeight()));
                    com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder createPublisherAdRequestBuilder = DTBAdUtil.INSTANCE.createPublisherAdRequestBuilder(dTBAdResponse);
                    for (Entry entry : AdsHelper.this.customTargeting.entrySet()) {
                        createPublisherAdRequestBuilder.addCustomTargeting((String) entry.getKey(), (String) entry.getValue());
                    }
                    AdsHelper.this.adView.loadAd(createPublisherAdRequestBuilder.build());
                    viewGroup.addView(AdsHelper.this.adView, 0, AdsHelper.this.adViewParams);
                    AdsHelper.this.adRequestedTime = System.currentTimeMillis();
                }
            }
        });
    }

    private void loadDfpAds() {
        this.adsLoadState = AdsLoadState.Loading;
        com.myfitnesspal.shared.util.BasicDfpPublisherAdRequest.Builder builder = new com.myfitnesspal.shared.util.BasicDfpPublisherAdRequest.Builder(this.adsSettings, this.configService, this.localSettingsService, this.locationService, this.isCountryUS);
        PublisherAdRequest request = builder.setCustomTargeting(this.customTargeting).getRequest();
        this.rootView.removeView(this.adView);
        adViewCleanup();
        addDfpAdsView(this.adUnit.getDfpAdUnitId());
        this.adView.setAdListener(this.adListener);
        try {
            logAdEvent(AdNetworkType.DFP, "Loading ad");
            this.adView.loadAd(request);
        } catch (Exception e) {
            Ln.e(e);
        }
        ((AdsAnalyticsHelper) this.adsAnalytics.get()).reportAdEvent(this.requestedAdAnalyticsEventName, this.screenName, getExtraAttributesMap());
        this.adRequestedTime = System.currentTimeMillis();
    }

    public void setAllowPremiumUpsell(boolean z) {
        this.allowPremiumUpsell = z;
    }

    /* access modifiers changed from: private */
    public void scheduleRefresh() {
        if (this.isAutoRefreshOn && this.adUnit.isAutoRefresh()) {
            this.handler.postDelayed(this.refreshAdRunnable, ADS_AUTO_REFRESH_INTERVAL_MS);
        }
    }

    /* access modifiers changed from: private */
    public void updateViewVisibility() {
        ViewUtils.setVisible(this.adsLoadState == AdsLoadState.Loaded || this.adUnit.getAdType() == AdType.WATER, this.adView);
        ViewUtils.setVisible(this.allowPremiumUpsell && this.adsLoadState == AdsLoadState.Failed, this.dfpAdsPremiumBanner);
        boolean z = !this.shouldHideProgressSpinner && this.adsLoadState == AdsLoadState.Loading;
        ViewUtils.setVisible(z, this.adsProgress);
        ViewUtils.setVisible(z, this.adsProgressContainer);
        View view = this.dfpAdsPremiumBanner;
        if (view != null) {
            view.setOnClickListener(this.premiumBannerClickListener);
        }
    }

    /* access modifiers changed from: private */
    public void handleAdLoadingFailure() {
        this.handler.removeCallbacksAndMessages(null);
        this.failureAdsCounter++;
        if (this.failureAdsCounter == AdNetworkType.values().length) {
            handleAllAdsLoadingFailed();
            switchAdNetwork();
            scheduleRefresh();
            return;
        }
        switchAdNetwork();
        loadAds();
    }

    private void switchAdNetwork() {
        this.adNetworkType = (this.adNetworkType != AdNetworkType.DFP || !canLoadAmazonAds()) ? AdNetworkType.DFP : AdNetworkType.AMAZON;
    }

    /* access modifiers changed from: private */
    public void handleAllAdsLoadingFailed() {
        this.adsLoadState = AdsLoadState.Failed;
        PublisherAdView publisherAdView = this.adView;
        if (publisherAdView != null) {
            this.rootView.removeView(publisherAdView);
            adViewCleanup();
        }
        ((AdsAnalyticsHelper) this.adsAnalytics.get()).reportAdEvent(Events.PREMIUM_HOUSE_AD_DISPLAYED, this.screenName, getExtraAttributesMap());
        updateViewVisibility();
        DfpAdsListener dfpAdsListener = this.dfpAdListener;
        if (dfpAdsListener != null) {
            dfpAdsListener.onAdFailedToLoad();
        }
    }

    public void resume() {
        resume(null);
    }

    public void resume(DfpAdsListener dfpAdsListener) {
        this.dfpAdListener = dfpAdsListener;
        PublisherAdView publisherAdView = this.adView;
        if (publisherAdView != null) {
            publisherAdView.resume();
        }
    }

    public void pause() {
        this.dfpAdListener = null;
        PublisherAdView publisherAdView = this.adView;
        if (publisherAdView != null) {
            publisherAdView.pause();
        }
    }

    public void destroy() {
        this.handler.removeCallbacksAndMessages(null);
        adViewCleanup();
    }

    /* access modifiers changed from: private */
    public Map<String, String> getExtraAttributesMap() {
        Map<String, String> createMap = MapUtil.createMap(new String[0]);
        if (Strings.notEmpty(this.waterSponsor)) {
            createMap.put("sponsor", this.waterSponsor);
        }
        createMap.put(ATTRIBUTE_KEY_ROLLOUT_NAME, AppNavUpdates.BOTTOM_BAR);
        createMap.put(ATTRIBUTE_KEY_ROLLOUT_VARIANT, ((ConfigService) this.configService.get()).getVariant(AppNavUpdates.BOTTOM_BAR));
        return createMap;
    }

    /* access modifiers changed from: private */
    public static int getDimens(Context context2, int i) {
        return context2.getResources().getDimensionPixelSize(i);
    }

    /* access modifiers changed from: private */
    public void adViewCleanup() {
        PublisherAdView publisherAdView = this.adView;
        if (publisherAdView != null) {
            publisherAdView.destroy();
            this.adView.setAdListener(null);
            WebAdTracker webAdTracker = this.moatAdTracker;
            if (webAdTracker != null) {
                webAdTracker.stopTracking();
            }
        }
    }

    /* access modifiers changed from: private */
    public void logAdEvent(@NotNull AdNetworkType adNetworkType2, @NotNull String str) {
        switch (adNetworkType2) {
            case AMAZON:
                StringBuilder sb = new StringBuilder();
                sb.append("ADS AMAZON ");
                sb.append(str);
                sb.append(" ");
                sb.append(this.adUnit.getAmazonAdUnitId());
                sb.append(" ");
                sb.append(this.adUnit.getAmazonSlotUuid());
                Ln.d(sb.toString(), new Object[0]);
                return;
            case DFP:
                StringBuilder sb2 = new StringBuilder();
                sb2.append("ADS DFP ");
                sb2.append(str);
                sb2.append(" ");
                sb2.append(this.adUnit.getDfpAdUnitId());
                Ln.d(sb2.toString(), new Object[0]);
                return;
            default:
                return;
        }
    }
}
