package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.analytics.GoogleAnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesGoogleAnalyticsServiceFactory implements Factory<GoogleAnalyticsService> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesGoogleAnalyticsServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.configServiceProvider = provider2;
    }

    public GoogleAnalyticsService get() {
        return provideInstance(this.module, this.contextProvider, this.configServiceProvider);
    }

    public static GoogleAnalyticsService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2) {
        return proxyProvidesGoogleAnalyticsService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesGoogleAnalyticsServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2) {
        return new ApplicationModule_ProvidesGoogleAnalyticsServiceFactory(applicationModule, provider, provider2);
    }

    public static GoogleAnalyticsService proxyProvidesGoogleAnalyticsService(ApplicationModule applicationModule, Context context, Lazy<ConfigService> lazy) {
        return (GoogleAnalyticsService) Preconditions.checkNotNull(applicationModule.providesGoogleAnalyticsService(context, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
