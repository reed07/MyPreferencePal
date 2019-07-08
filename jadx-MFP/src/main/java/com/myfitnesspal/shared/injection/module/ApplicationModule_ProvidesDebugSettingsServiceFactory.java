package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.debug.util.DebugSettingsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesDebugSettingsServiceFactory implements Factory<DebugSettingsService> {
    private final ApplicationModule module;
    private final Provider<SharedPreferences> prefsProvider;

    public ApplicationModule_ProvidesDebugSettingsServiceFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.prefsProvider = provider;
    }

    public DebugSettingsService get() {
        return provideInstance(this.module, this.prefsProvider);
    }

    public static DebugSettingsService provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvidesDebugSettingsService(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesDebugSettingsServiceFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvidesDebugSettingsServiceFactory(applicationModule, provider);
    }

    public static DebugSettingsService proxyProvidesDebugSettingsService(ApplicationModule applicationModule, Lazy<SharedPreferences> lazy) {
        return (DebugSettingsService) Preconditions.checkNotNull(applicationModule.providesDebugSettingsService(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
