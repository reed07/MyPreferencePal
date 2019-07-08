package com.myfitnesspal.feature.appgallery.ui;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.settings.model.XPromoSettings;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class XPromoInterstitialFragment_MembersInjector implements MembersInjector<XPromoInterstitialFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<XPromoSettings> xpromoSettingsProvider;

    public XPromoInterstitialFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<XPromoSettings> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.xpromoSettingsProvider = provider4;
    }

    public static MembersInjector<XPromoInterstitialFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<XPromoSettings> provider4) {
        return new XPromoInterstitialFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(XPromoInterstitialFragment xPromoInterstitialFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(xPromoInterstitialFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(xPromoInterstitialFragment, (Glide) this.glideProvider.get());
        injectAnalyticsService(xPromoInterstitialFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectXpromoSettings(xPromoInterstitialFragment, (XPromoSettings) this.xpromoSettingsProvider.get());
    }

    public static void injectAnalyticsService(XPromoInterstitialFragment xPromoInterstitialFragment, Lazy<AnalyticsService> lazy) {
        xPromoInterstitialFragment.analyticsService = lazy;
    }

    public static void injectXpromoSettings(XPromoInterstitialFragment xPromoInterstitialFragment, XPromoSettings xPromoSettings) {
        xPromoInterstitialFragment.xpromoSettings = xPromoSettings;
    }
}
