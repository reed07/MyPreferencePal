package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.restaurantlogging.service.MenuService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesMenuServiceFactory implements Factory<MenuService> {
    private final Provider<MfpV2Api> apiProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMenuServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        this.module = applicationModule;
        this.apiProvider = provider;
    }

    public MenuService get() {
        return provideInstance(this.module, this.apiProvider);
    }

    public static MenuService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return proxyProvidesMenuService(applicationModule, provider);
    }

    public static ApplicationModule_ProvidesMenuServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return new ApplicationModule_ProvidesMenuServiceFactory(applicationModule, provider);
    }

    public static MenuService proxyProvidesMenuService(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return (MenuService) Preconditions.checkNotNull(applicationModule.providesMenuService(provider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
