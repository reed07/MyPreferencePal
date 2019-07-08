package com.myfitnesspal.feature.appgallery.ui;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class OurOtherAppsFragment_MembersInjector implements MembersInjector<OurOtherAppsFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public OurOtherAppsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<AppGalleryService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.appGalleryServiceProvider = provider4;
    }

    public static MembersInjector<OurOtherAppsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<AppGalleryService> provider4) {
        return new OurOtherAppsFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(OurOtherAppsFragment ourOtherAppsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(ourOtherAppsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(ourOtherAppsFragment, (Glide) this.glideProvider.get());
        injectAnalyticsService(ourOtherAppsFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectAppGalleryService(ourOtherAppsFragment, DoubleCheck.lazy(this.appGalleryServiceProvider));
    }

    public static void injectAnalyticsService(OurOtherAppsFragment ourOtherAppsFragment, Lazy<AnalyticsService> lazy) {
        ourOtherAppsFragment.analyticsService = lazy;
    }

    public static void injectAppGalleryService(OurOtherAppsFragment ourOtherAppsFragment, Lazy<AppGalleryService> lazy) {
        ourOtherAppsFragment.appGalleryService = lazy;
    }
}
