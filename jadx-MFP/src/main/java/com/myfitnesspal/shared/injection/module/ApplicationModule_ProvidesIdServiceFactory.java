package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.id.IdService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesIdServiceFactory implements Factory<IdService> {
    private final Provider<MfpV2Api> apiProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesIdServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        this.module = applicationModule;
        this.apiProvider = provider;
    }

    public IdService get() {
        return provideInstance(this.module, this.apiProvider);
    }

    public static IdService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return proxyProvidesIdService(applicationModule, provider);
    }

    public static ApplicationModule_ProvidesIdServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return new ApplicationModule_ProvidesIdServiceFactory(applicationModule, provider);
    }

    public static IdService proxyProvidesIdService(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return (IdService) Preconditions.checkNotNull(applicationModule.providesIdService(provider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
