package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.analytics.AmplitudeService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.GoogleAnalyticsService;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesAnalyticsServiceFactory implements Factory<AnalyticsService> {
    private final Provider<AmplitudeService> amplitudeServiceProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<GoogleAnalyticsService> googleAnalyticsServiceProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesAnalyticsServiceFactory(ApplicationModule applicationModule, Provider<AppIndexerBot> provider, Provider<AmplitudeService> provider2, Provider<MfpAnalyticsService> provider3, Provider<GoogleAnalyticsService> provider4) {
        this.module = applicationModule;
        this.appIndexerBotProvider = provider;
        this.amplitudeServiceProvider = provider2;
        this.mfpAnalyticsServiceProvider = provider3;
        this.googleAnalyticsServiceProvider = provider4;
    }

    public AnalyticsService get() {
        return provideInstance(this.module, this.appIndexerBotProvider, this.amplitudeServiceProvider, this.mfpAnalyticsServiceProvider, this.googleAnalyticsServiceProvider);
    }

    public static AnalyticsService provideInstance(ApplicationModule applicationModule, Provider<AppIndexerBot> provider, Provider<AmplitudeService> provider2, Provider<MfpAnalyticsService> provider3, Provider<GoogleAnalyticsService> provider4) {
        return proxyProvidesAnalyticsService(applicationModule, DoubleCheck.lazy(provider), (AmplitudeService) provider2.get(), (MfpAnalyticsService) provider3.get(), (GoogleAnalyticsService) provider4.get());
    }

    public static ApplicationModule_ProvidesAnalyticsServiceFactory create(ApplicationModule applicationModule, Provider<AppIndexerBot> provider, Provider<AmplitudeService> provider2, Provider<MfpAnalyticsService> provider3, Provider<GoogleAnalyticsService> provider4) {
        ApplicationModule_ProvidesAnalyticsServiceFactory applicationModule_ProvidesAnalyticsServiceFactory = new ApplicationModule_ProvidesAnalyticsServiceFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesAnalyticsServiceFactory;
    }

    public static AnalyticsService proxyProvidesAnalyticsService(ApplicationModule applicationModule, Lazy<AppIndexerBot> lazy, AmplitudeService amplitudeService, MfpAnalyticsService mfpAnalyticsService, GoogleAnalyticsService googleAnalyticsService) {
        return (AnalyticsService) Preconditions.checkNotNull(applicationModule.providesAnalyticsService(lazy, amplitudeService, mfpAnalyticsService, googleAnalyticsService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
