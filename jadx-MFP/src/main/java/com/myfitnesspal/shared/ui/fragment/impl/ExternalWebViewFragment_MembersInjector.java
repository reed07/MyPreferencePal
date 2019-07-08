package com.myfitnesspal.shared.ui.fragment.impl;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ExternalWebViewFragment_MembersInjector implements MembersInjector<ExternalWebViewFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Glide> glideProvider;

    public ExternalWebViewFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<ConfigService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.configServiceProvider = provider4;
    }

    public static MembersInjector<ExternalWebViewFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<ConfigService> provider4) {
        return new ExternalWebViewFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(ExternalWebViewFragment externalWebViewFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(externalWebViewFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(externalWebViewFragment, (Glide) this.glideProvider.get());
        injectAnalyticsService(externalWebViewFragment, (AnalyticsService) this.analyticsServiceProvider.get());
        injectConfigService(externalWebViewFragment, (ConfigService) this.configServiceProvider.get());
    }

    public static void injectAnalyticsService(ExternalWebViewFragment externalWebViewFragment, AnalyticsService analyticsService) {
        externalWebViewFragment.analyticsService = analyticsService;
    }

    public static void injectConfigService(ExternalWebViewFragment externalWebViewFragment, ConfigService configService) {
        externalWebViewFragment.configService = configService;
    }
}
