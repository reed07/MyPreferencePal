package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideGlobalSettingsServiceFactory implements Factory<GlobalSettingsService> {
    private final ApplicationModule module;
    private final Provider<SharedPreferences> prefsProvider;

    public ApplicationModule_ProvideGlobalSettingsServiceFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.prefsProvider = provider;
    }

    public GlobalSettingsService get() {
        return provideInstance(this.module, this.prefsProvider);
    }

    public static GlobalSettingsService provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvideGlobalSettingsService(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvideGlobalSettingsServiceFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvideGlobalSettingsServiceFactory(applicationModule, provider);
    }

    public static GlobalSettingsService proxyProvideGlobalSettingsService(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (GlobalSettingsService) Preconditions.checkNotNull(applicationModule.provideGlobalSettingsService(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
