package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesSubscriptionServiceFactory implements Factory<SubscriptionService> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<Bus> busProvider;
    private final Provider<Context> contextProvider;
    private final Provider<SubscriptionServiceDbAdapter> dbAdapterProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesSubscriptionServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<Bus> provider3, Provider<SubscriptionServiceDbAdapter> provider4, Provider<MfpV2Api> provider5, Provider<AnalyticsService> provider6) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.busProvider = provider3;
        this.dbAdapterProvider = provider4;
        this.apiProvider = provider5;
        this.analyticsProvider = provider6;
    }

    public SubscriptionService get() {
        return provideInstance(this.module, this.contextProvider, this.sessionProvider, this.busProvider, this.dbAdapterProvider, this.apiProvider, this.analyticsProvider);
    }

    public static SubscriptionService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<Bus> provider3, Provider<SubscriptionServiceDbAdapter> provider4, Provider<MfpV2Api> provider5, Provider<AnalyticsService> provider6) {
        return proxyProvidesSubscriptionService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), provider5, DoubleCheck.lazy(provider6));
    }

    public static ApplicationModule_ProvidesSubscriptionServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<Bus> provider3, Provider<SubscriptionServiceDbAdapter> provider4, Provider<MfpV2Api> provider5, Provider<AnalyticsService> provider6) {
        ApplicationModule_ProvidesSubscriptionServiceFactory applicationModule_ProvidesSubscriptionServiceFactory = new ApplicationModule_ProvidesSubscriptionServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6);
        return applicationModule_ProvidesSubscriptionServiceFactory;
    }

    public static SubscriptionService proxyProvidesSubscriptionService(ApplicationModule applicationModule, Context context, Lazy<Session> lazy, Lazy<Bus> lazy2, Lazy<SubscriptionServiceDbAdapter> lazy3, Provider<MfpV2Api> provider, Lazy<AnalyticsService> lazy4) {
        return (SubscriptionService) Preconditions.checkNotNull(applicationModule.providesSubscriptionService(context, lazy, lazy2, lazy3, provider, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}
