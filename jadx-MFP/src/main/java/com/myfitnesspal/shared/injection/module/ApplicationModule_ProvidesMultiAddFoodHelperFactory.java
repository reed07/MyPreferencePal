package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesMultiAddFoodHelperFactory implements Factory<MultiAddFoodHelper> {
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMultiAddFoodHelperFactory(ApplicationModule applicationModule, Provider<LocalSettingsService> provider) {
        this.module = applicationModule;
        this.localSettingsServiceProvider = provider;
    }

    public MultiAddFoodHelper get() {
        return provideInstance(this.module, this.localSettingsServiceProvider);
    }

    public static MultiAddFoodHelper provideInstance(ApplicationModule applicationModule, Provider<LocalSettingsService> provider) {
        return proxyProvidesMultiAddFoodHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesMultiAddFoodHelperFactory create(ApplicationModule applicationModule, Provider<LocalSettingsService> provider) {
        return new ApplicationModule_ProvidesMultiAddFoodHelperFactory(applicationModule, provider);
    }

    public static MultiAddFoodHelper proxyProvidesMultiAddFoodHelper(ApplicationModule applicationModule, Lazy<LocalSettingsService> lazy) {
        return (MultiAddFoodHelper) Preconditions.checkNotNull(applicationModule.providesMultiAddFoodHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
