package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesVanillaServiceFactory implements Factory<CommunityService> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final ApplicationModule module;
    private final Provider<MfpV2Api> v2ApiProvider;

    public ApplicationModule_ProvidesVanillaServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<ConfigService> provider2, Provider<CountryService> provider3) {
        this.module = applicationModule;
        this.v2ApiProvider = provider;
        this.configServiceProvider = provider2;
        this.countryServiceProvider = provider3;
    }

    public CommunityService get() {
        return provideInstance(this.module, this.v2ApiProvider, this.configServiceProvider, this.countryServiceProvider);
    }

    public static CommunityService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<ConfigService> provider2, Provider<CountryService> provider3) {
        return proxyProvidesVanillaService(applicationModule, provider, DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvidesVanillaServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<ConfigService> provider2, Provider<CountryService> provider3) {
        return new ApplicationModule_ProvidesVanillaServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static CommunityService proxyProvidesVanillaService(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Lazy<ConfigService> lazy, Lazy<CountryService> lazy2) {
        return (CommunityService) Preconditions.checkNotNull(applicationModule.providesVanillaService(provider, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
