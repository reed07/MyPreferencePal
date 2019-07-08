package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.framework.deeplink.DeepLinkRouter;
import com.myfitnesspal.framework.deeplink.Dispatcher;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesDeepLinkRouterFactory implements Factory<DeepLinkRouter> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<Dispatcher> dispatcherProvider;
    private final ApplicationModule module;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesDeepLinkRouterFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Dispatcher> provider2, Provider<DeepLinkManager> provider3, Provider<NavigationHelper> provider4, Provider<AppSettings> provider5, Provider<Session> provider6, Provider<AnalyticsService> provider7) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.dispatcherProvider = provider2;
        this.deepLinkManagerProvider = provider3;
        this.navigationHelperProvider = provider4;
        this.appSettingsProvider = provider5;
        this.sessionProvider = provider6;
        this.analyticsServiceProvider = provider7;
    }

    public DeepLinkRouter get() {
        return provideInstance(this.module, this.contextProvider, this.dispatcherProvider, this.deepLinkManagerProvider, this.navigationHelperProvider, this.appSettingsProvider, this.sessionProvider, this.analyticsServiceProvider);
    }

    public static DeepLinkRouter provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Dispatcher> provider2, Provider<DeepLinkManager> provider3, Provider<NavigationHelper> provider4, Provider<AppSettings> provider5, Provider<Session> provider6, Provider<AnalyticsService> provider7) {
        return proxyProvidesDeepLinkRouter(applicationModule, (Context) provider.get(), (Dispatcher) provider2.get(), (DeepLinkManager) provider3.get(), (NavigationHelper) provider4.get(), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7));
    }

    public static ApplicationModule_ProvidesDeepLinkRouterFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Dispatcher> provider2, Provider<DeepLinkManager> provider3, Provider<NavigationHelper> provider4, Provider<AppSettings> provider5, Provider<Session> provider6, Provider<AnalyticsService> provider7) {
        ApplicationModule_ProvidesDeepLinkRouterFactory applicationModule_ProvidesDeepLinkRouterFactory = new ApplicationModule_ProvidesDeepLinkRouterFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return applicationModule_ProvidesDeepLinkRouterFactory;
    }

    public static DeepLinkRouter proxyProvidesDeepLinkRouter(ApplicationModule applicationModule, Context context, Dispatcher dispatcher, DeepLinkManager deepLinkManager, NavigationHelper navigationHelper, Lazy<AppSettings> lazy, Lazy<Session> lazy2, Lazy<AnalyticsService> lazy3) {
        return (DeepLinkRouter) Preconditions.checkNotNull(applicationModule.providesDeepLinkRouter(context, dispatcher, deepLinkManager, navigationHelper, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
