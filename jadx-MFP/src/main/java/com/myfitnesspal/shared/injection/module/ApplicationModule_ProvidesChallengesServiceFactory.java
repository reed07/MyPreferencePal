package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesChallengesServiceFactory implements Factory<ChallengesService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesChallengesServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<Session> provider2, Provider<CountryService> provider3) {
        this.module = applicationModule;
        this.apiProvider = provider;
        this.sessionProvider = provider2;
        this.countryServiceProvider = provider3;
    }

    public ChallengesService get() {
        return provideInstance(this.module, this.apiProvider, this.sessionProvider, this.countryServiceProvider);
    }

    public static ChallengesService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<Session> provider2, Provider<CountryService> provider3) {
        return proxyProvidesChallengesService(applicationModule, provider, DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvidesChallengesServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<Session> provider2, Provider<CountryService> provider3) {
        return new ApplicationModule_ProvidesChallengesServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static ChallengesService proxyProvidesChallengesService(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Lazy<Session> lazy, Lazy<CountryService> lazy2) {
        return (ChallengesService) Preconditions.checkNotNull(applicationModule.providesChallengesService(provider, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
