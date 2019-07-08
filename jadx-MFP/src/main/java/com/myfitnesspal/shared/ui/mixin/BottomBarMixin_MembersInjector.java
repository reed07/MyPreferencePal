package com.myfitnesspal.shared.ui.mixin;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BottomBarMixin_MembersInjector implements MembersInjector<BottomBarMixin> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Session> sessionProvider;

    public BottomBarMixin_MembersInjector(Provider<AnalyticsService> provider, Provider<ConfigService> provider2, Provider<Session> provider3) {
        this.analyticsProvider = provider;
        this.configServiceProvider = provider2;
        this.sessionProvider = provider3;
    }

    public static MembersInjector<BottomBarMixin> create(Provider<AnalyticsService> provider, Provider<ConfigService> provider2, Provider<Session> provider3) {
        return new BottomBarMixin_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(BottomBarMixin bottomBarMixin) {
        injectAnalytics(bottomBarMixin, (AnalyticsService) this.analyticsProvider.get());
        injectConfigService(bottomBarMixin, (ConfigService) this.configServiceProvider.get());
        injectSession(bottomBarMixin, (Session) this.sessionProvider.get());
    }

    public static void injectAnalytics(BottomBarMixin bottomBarMixin, AnalyticsService analyticsService) {
        bottomBarMixin.analytics = analyticsService;
    }

    public static void injectConfigService(BottomBarMixin bottomBarMixin, ConfigService configService) {
        bottomBarMixin.configService = configService;
    }

    public static void injectSession(BottomBarMixin bottomBarMixin, Session session) {
        bottomBarMixin.session = session;
    }
}
