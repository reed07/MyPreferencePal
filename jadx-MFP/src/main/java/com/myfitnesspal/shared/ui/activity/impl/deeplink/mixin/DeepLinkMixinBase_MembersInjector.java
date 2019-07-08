package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class DeepLinkMixinBase_MembersInjector implements MembersInjector<DeepLinkMixinBase> {
    private final Provider<AnalyticsService> analyticsProvider;

    public DeepLinkMixinBase_MembersInjector(Provider<AnalyticsService> provider) {
        this.analyticsProvider = provider;
    }

    public static MembersInjector<DeepLinkMixinBase> create(Provider<AnalyticsService> provider) {
        return new DeepLinkMixinBase_MembersInjector(provider);
    }

    public void injectMembers(DeepLinkMixinBase deepLinkMixinBase) {
        injectAnalytics(deepLinkMixinBase, DoubleCheck.lazy(this.analyticsProvider));
    }

    public static void injectAnalytics(DeepLinkMixinBase deepLinkMixinBase, Lazy<AnalyticsService> lazy) {
        deepLinkMixinBase.analytics = lazy;
    }
}
