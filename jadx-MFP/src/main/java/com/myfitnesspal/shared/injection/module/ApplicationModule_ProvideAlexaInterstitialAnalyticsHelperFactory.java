package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.alexainterstitial.analytics.AlexaInterstitialAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAlexaInterstitialAnalyticsHelperFactory implements Factory<AlexaInterstitialAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideAlexaInterstitialAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public AlexaInterstitialAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static AlexaInterstitialAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideAlexaInterstitialAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideAlexaInterstitialAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideAlexaInterstitialAnalyticsHelperFactory(applicationModule, provider);
    }

    public static AlexaInterstitialAnalyticsHelper proxyProvideAlexaInterstitialAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (AlexaInterstitialAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideAlexaInterstitialAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
