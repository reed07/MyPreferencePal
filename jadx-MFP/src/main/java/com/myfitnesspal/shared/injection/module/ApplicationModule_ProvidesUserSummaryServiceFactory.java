package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.debug.util.DebugSettingsService;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesUserSummaryServiceFactory implements Factory<UserSummaryService> {
    private final Provider<DebugSettingsService> debugSettingsServiceProvider;
    private final Provider<MfpInformationApi> mfpInformationApiProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesUserSummaryServiceFactory(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<DebugSettingsService> provider2) {
        this.module = applicationModule;
        this.mfpInformationApiProvider = provider;
        this.debugSettingsServiceProvider = provider2;
    }

    public UserSummaryService get() {
        return provideInstance(this.module, this.mfpInformationApiProvider, this.debugSettingsServiceProvider);
    }

    public static UserSummaryService provideInstance(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<DebugSettingsService> provider2) {
        return proxyProvidesUserSummaryService(applicationModule, provider, DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesUserSummaryServiceFactory create(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<DebugSettingsService> provider2) {
        return new ApplicationModule_ProvidesUserSummaryServiceFactory(applicationModule, provider, provider2);
    }

    public static UserSummaryService proxyProvidesUserSummaryService(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Lazy<DebugSettingsService> lazy) {
        return (UserSummaryService) Preconditions.checkNotNull(applicationModule.providesUserSummaryService(provider, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
