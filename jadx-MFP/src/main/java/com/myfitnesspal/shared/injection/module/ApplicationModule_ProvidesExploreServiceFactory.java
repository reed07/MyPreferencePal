package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.explore.service.ExploreService;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesExploreServiceFactory implements Factory<ExploreService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<ChallengesService> challengesServiceProvider;
    private final Provider<MealService> mealsServiceProvider;
    private final ApplicationModule module;
    private final Provider<VenueService> venueServiceProvider;

    public ApplicationModule_ProvidesExploreServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<ChallengesService> provider2, Provider<MealService> provider3, Provider<VenueService> provider4) {
        this.module = applicationModule;
        this.apiProvider = provider;
        this.challengesServiceProvider = provider2;
        this.mealsServiceProvider = provider3;
        this.venueServiceProvider = provider4;
    }

    public ExploreService get() {
        return provideInstance(this.module, this.apiProvider, this.challengesServiceProvider, this.mealsServiceProvider, this.venueServiceProvider);
    }

    public static ExploreService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<ChallengesService> provider2, Provider<MealService> provider3, Provider<VenueService> provider4) {
        return proxyProvidesExploreService(applicationModule, provider, DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static ApplicationModule_ProvidesExploreServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<ChallengesService> provider2, Provider<MealService> provider3, Provider<VenueService> provider4) {
        ApplicationModule_ProvidesExploreServiceFactory applicationModule_ProvidesExploreServiceFactory = new ApplicationModule_ProvidesExploreServiceFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesExploreServiceFactory;
    }

    public static ExploreService proxyProvidesExploreService(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Lazy<ChallengesService> lazy, Lazy<MealService> lazy2, Lazy<VenueService> lazy3) {
        return (ExploreService) Preconditions.checkNotNull(applicationModule.providesExploreService(provider, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
