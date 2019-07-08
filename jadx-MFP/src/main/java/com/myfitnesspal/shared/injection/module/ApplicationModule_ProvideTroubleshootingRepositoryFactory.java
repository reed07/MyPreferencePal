package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.settings.api.TroubleShootingApi;
import com.myfitnesspal.feature.settings.repository.TroubleshootingRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideTroubleshootingRepositoryFactory implements Factory<TroubleshootingRepository> {
    private final ApplicationModule module;
    private final Provider<TroubleShootingApi> troubleShootingApiProvider;

    public ApplicationModule_ProvideTroubleshootingRepositoryFactory(ApplicationModule applicationModule, Provider<TroubleShootingApi> provider) {
        this.module = applicationModule;
        this.troubleShootingApiProvider = provider;
    }

    public TroubleshootingRepository get() {
        return provideInstance(this.module, this.troubleShootingApiProvider);
    }

    public static TroubleshootingRepository provideInstance(ApplicationModule applicationModule, Provider<TroubleShootingApi> provider) {
        return proxyProvideTroubleshootingRepository(applicationModule, (TroubleShootingApi) provider.get());
    }

    public static ApplicationModule_ProvideTroubleshootingRepositoryFactory create(ApplicationModule applicationModule, Provider<TroubleShootingApi> provider) {
        return new ApplicationModule_ProvideTroubleshootingRepositoryFactory(applicationModule, provider);
    }

    public static TroubleshootingRepository proxyProvideTroubleshootingRepository(ApplicationModule applicationModule, TroubleShootingApi troubleShootingApi) {
        return (TroubleshootingRepository) Preconditions.checkNotNull(applicationModule.provideTroubleshootingRepository(troubleShootingApi), "Cannot return null from a non-@Nullable @Provides method");
    }
}
