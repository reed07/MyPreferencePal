package com.myfitnesspal.feature.explore.analytics;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ExploreAnalytics_MembersInjector implements MembersInjector<ExploreAnalytics> {
    private final Provider<AnalyticsService> analyticsProvider;

    public ExploreAnalytics_MembersInjector(Provider<AnalyticsService> provider) {
        this.analyticsProvider = provider;
    }

    public static MembersInjector<ExploreAnalytics> create(Provider<AnalyticsService> provider) {
        return new ExploreAnalytics_MembersInjector(provider);
    }

    public void injectMembers(ExploreAnalytics exploreAnalytics) {
        injectAnalytics(exploreAnalytics, (AnalyticsService) this.analyticsProvider.get());
    }

    public static void injectAnalytics(ExploreAnalytics exploreAnalytics, AnalyticsService analyticsService) {
        exploreAnalytics.analytics = analyticsService;
    }
}
