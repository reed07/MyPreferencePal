package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesUpdatedTermsAnalyticsHelperFactory implements Factory<UpdatedTermsAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesUpdatedTermsAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public UpdatedTermsAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static UpdatedTermsAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesUpdatedTermsAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesUpdatedTermsAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesUpdatedTermsAnalyticsHelperFactory(applicationModule, provider);
    }

    public static UpdatedTermsAnalyticsHelper proxyProvidesUpdatedTermsAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (UpdatedTermsAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesUpdatedTermsAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
