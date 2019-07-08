package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideDeleteAccountAnalyticsHelperFactory implements Factory<DeleteAccountAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideDeleteAccountAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public DeleteAccountAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static DeleteAccountAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideDeleteAccountAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideDeleteAccountAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideDeleteAccountAnalyticsHelperFactory(applicationModule, provider);
    }

    public static DeleteAccountAnalyticsHelper proxyProvideDeleteAccountAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (DeleteAccountAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideDeleteAccountAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
