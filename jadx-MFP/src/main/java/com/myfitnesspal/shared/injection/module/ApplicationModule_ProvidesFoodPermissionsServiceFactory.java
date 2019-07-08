package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.db.table.FoodPermissionsTable;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFoodPermissionsServiceFactory implements Factory<FoodPermissionsService> {
    private final Provider<FoodPermissionsTable> foodPermissionsTableProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesFoodPermissionsServiceFactory(ApplicationModule applicationModule, Provider<Session> provider, Provider<FoodPermissionsTable> provider2) {
        this.module = applicationModule;
        this.sessionProvider = provider;
        this.foodPermissionsTableProvider = provider2;
    }

    public FoodPermissionsService get() {
        return provideInstance(this.module, this.sessionProvider, this.foodPermissionsTableProvider);
    }

    public static FoodPermissionsService provideInstance(ApplicationModule applicationModule, Provider<Session> provider, Provider<FoodPermissionsTable> provider2) {
        return proxyProvidesFoodPermissionsService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesFoodPermissionsServiceFactory create(ApplicationModule applicationModule, Provider<Session> provider, Provider<FoodPermissionsTable> provider2) {
        return new ApplicationModule_ProvidesFoodPermissionsServiceFactory(applicationModule, provider, provider2);
    }

    public static FoodPermissionsService proxyProvidesFoodPermissionsService(ApplicationModule applicationModule, Lazy<Session> lazy, Lazy<FoodPermissionsTable> lazy2) {
        return (FoodPermissionsService) Preconditions.checkNotNull(applicationModule.providesFoodPermissionsService(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
