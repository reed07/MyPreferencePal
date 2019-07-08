package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideProfileAggregatorServiceFactory implements Factory<ProfileAggregatorService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<GoogleFitClient> fitClientProvider;
    private final Provider<NutrientGoalService> goalsServiceProvider;
    private final ApplicationModule module;
    private final Provider<SHealthConnection> sHealthClientProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public ApplicationModule_ProvideProfileAggregatorServiceFactory(ApplicationModule applicationModule, Provider<Session> provider, Provider<MfpV2Api> provider2, Provider<SHealthConnection> provider3, Provider<GoogleFitClient> provider4, Provider<CountryService> provider5, Provider<NutrientGoalService> provider6, Provider<UserWeightService> provider7) {
        this.module = applicationModule;
        this.sessionProvider = provider;
        this.apiProvider = provider2;
        this.sHealthClientProvider = provider3;
        this.fitClientProvider = provider4;
        this.countryServiceProvider = provider5;
        this.goalsServiceProvider = provider6;
        this.userWeightServiceProvider = provider7;
    }

    public ProfileAggregatorService get() {
        return provideInstance(this.module, this.sessionProvider, this.apiProvider, this.sHealthClientProvider, this.fitClientProvider, this.countryServiceProvider, this.goalsServiceProvider, this.userWeightServiceProvider);
    }

    public static ProfileAggregatorService provideInstance(ApplicationModule applicationModule, Provider<Session> provider, Provider<MfpV2Api> provider2, Provider<SHealthConnection> provider3, Provider<GoogleFitClient> provider4, Provider<CountryService> provider5, Provider<NutrientGoalService> provider6, Provider<UserWeightService> provider7) {
        return proxyProvideProfileAggregatorService(applicationModule, DoubleCheck.lazy(provider), provider2, (SHealthConnection) provider3.get(), (GoogleFitClient) provider4.get(), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7));
    }

    public static ApplicationModule_ProvideProfileAggregatorServiceFactory create(ApplicationModule applicationModule, Provider<Session> provider, Provider<MfpV2Api> provider2, Provider<SHealthConnection> provider3, Provider<GoogleFitClient> provider4, Provider<CountryService> provider5, Provider<NutrientGoalService> provider6, Provider<UserWeightService> provider7) {
        ApplicationModule_ProvideProfileAggregatorServiceFactory applicationModule_ProvideProfileAggregatorServiceFactory = new ApplicationModule_ProvideProfileAggregatorServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return applicationModule_ProvideProfileAggregatorServiceFactory;
    }

    public static ProfileAggregatorService proxyProvideProfileAggregatorService(ApplicationModule applicationModule, Lazy<Session> lazy, Provider<MfpV2Api> provider, SHealthConnection sHealthConnection, GoogleFitClient googleFitClient, Lazy<CountryService> lazy2, Lazy<NutrientGoalService> lazy3, Lazy<UserWeightService> lazy4) {
        return (ProfileAggregatorService) Preconditions.checkNotNull(applicationModule.provideProfileAggregatorService(lazy, provider, sHealthConnection, googleFitClient, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}
