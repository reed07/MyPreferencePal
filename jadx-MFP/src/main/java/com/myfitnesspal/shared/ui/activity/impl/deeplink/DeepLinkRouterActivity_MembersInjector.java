package com.myfitnesspal.shared.ui.activity.impl.deeplink;

import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.framework.deeplink.DeepLinkRouter;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class DeepLinkRouterActivity_MembersInjector implements MembersInjector<DeepLinkRouterActivity> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<FoodSearchActivityFactory> foodSearchRouterProvider;
    private final Provider<DeepLinkRouter> routerProvider;
    private final Provider<Session> sessionProvider;

    public DeepLinkRouterActivity_MembersInjector(Provider<DeepLinkRouter> provider, Provider<AnalyticsService> provider2, Provider<AppIndexerBot> provider3, Provider<Session> provider4, Provider<FoodSearchActivityFactory> provider5) {
        this.routerProvider = provider;
        this.analyticsServiceProvider = provider2;
        this.appIndexerBotProvider = provider3;
        this.sessionProvider = provider4;
        this.foodSearchRouterProvider = provider5;
    }

    public static MembersInjector<DeepLinkRouterActivity> create(Provider<DeepLinkRouter> provider, Provider<AnalyticsService> provider2, Provider<AppIndexerBot> provider3, Provider<Session> provider4, Provider<FoodSearchActivityFactory> provider5) {
        DeepLinkRouterActivity_MembersInjector deepLinkRouterActivity_MembersInjector = new DeepLinkRouterActivity_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return deepLinkRouterActivity_MembersInjector;
    }

    public void injectMembers(DeepLinkRouterActivity deepLinkRouterActivity) {
        injectRouter(deepLinkRouterActivity, DoubleCheck.lazy(this.routerProvider));
        injectAnalyticsService(deepLinkRouterActivity, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectAppIndexerBot(deepLinkRouterActivity, DoubleCheck.lazy(this.appIndexerBotProvider));
        injectSession(deepLinkRouterActivity, DoubleCheck.lazy(this.sessionProvider));
        injectFoodSearchRouter(deepLinkRouterActivity, DoubleCheck.lazy(this.foodSearchRouterProvider));
    }

    public static void injectRouter(DeepLinkRouterActivity deepLinkRouterActivity, Lazy<DeepLinkRouter> lazy) {
        deepLinkRouterActivity.router = lazy;
    }

    public static void injectAnalyticsService(DeepLinkRouterActivity deepLinkRouterActivity, Lazy<AnalyticsService> lazy) {
        deepLinkRouterActivity.analyticsService = lazy;
    }

    public static void injectAppIndexerBot(DeepLinkRouterActivity deepLinkRouterActivity, Lazy<AppIndexerBot> lazy) {
        deepLinkRouterActivity.appIndexerBot = lazy;
    }

    public static void injectSession(DeepLinkRouterActivity deepLinkRouterActivity, Lazy<Session> lazy) {
        deepLinkRouterActivity.session = lazy;
    }

    public static void injectFoodSearchRouter(DeepLinkRouterActivity deepLinkRouterActivity, Lazy<FoodSearchActivityFactory> lazy) {
        deepLinkRouterActivity.foodSearchRouter = lazy;
    }
}
