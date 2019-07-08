package com.myfitnesspal.feature.profile.analytics;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MeAnalytics_MembersInjector implements MembersInjector<MeAnalytics> {
    private final Provider<AnalyticsService> analyticsProvider;

    public MeAnalytics_MembersInjector(Provider<AnalyticsService> provider) {
        this.analyticsProvider = provider;
    }

    public static MembersInjector<MeAnalytics> create(Provider<AnalyticsService> provider) {
        return new MeAnalytics_MembersInjector(provider);
    }

    public void injectMembers(MeAnalytics meAnalytics) {
        injectAnalytics(meAnalytics, (AnalyticsService) this.analyticsProvider.get());
    }

    public static void injectAnalytics(MeAnalytics meAnalytics, AnalyticsService analyticsService) {
        meAnalytics.analytics = analyticsService;
    }
}
