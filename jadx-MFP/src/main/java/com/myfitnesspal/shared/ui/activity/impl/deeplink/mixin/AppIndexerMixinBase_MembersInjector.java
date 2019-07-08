package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AppIndexerMixinBase_MembersInjector implements MembersInjector<AppIndexerMixinBase> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;

    public AppIndexerMixinBase_MembersInjector(Provider<AnalyticsService> provider, Provider<AppIndexerBot> provider2) {
        this.analyticsProvider = provider;
        this.appIndexerBotProvider = provider2;
    }

    public static MembersInjector<AppIndexerMixinBase> create(Provider<AnalyticsService> provider, Provider<AppIndexerBot> provider2) {
        return new AppIndexerMixinBase_MembersInjector(provider, provider2);
    }

    public void injectMembers(AppIndexerMixinBase appIndexerMixinBase) {
        DeepLinkMixinBase_MembersInjector.injectAnalytics(appIndexerMixinBase, DoubleCheck.lazy(this.analyticsProvider));
        injectAnalytics(appIndexerMixinBase, DoubleCheck.lazy(this.analyticsProvider));
        injectAppIndexerBot(appIndexerMixinBase, DoubleCheck.lazy(this.appIndexerBotProvider));
    }

    public static void injectAnalytics(AppIndexerMixinBase appIndexerMixinBase, Lazy<AnalyticsService> lazy) {
        appIndexerMixinBase.analytics = lazy;
    }

    public static void injectAppIndexerBot(AppIndexerMixinBase appIndexerMixinBase, Lazy<AppIndexerBot> lazy) {
        appIndexerMixinBase.appIndexerBot = lazy;
    }
}
