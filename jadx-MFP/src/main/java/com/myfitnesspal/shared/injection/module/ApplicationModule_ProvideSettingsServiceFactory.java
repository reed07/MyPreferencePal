package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideSettingsServiceFactory implements Factory<LocalSettingsService> {
    private final Provider<CountryService> countryServiceProvider;
    private final ApplicationModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;

    public ApplicationModule_ProvideSettingsServiceFactory(ApplicationModule applicationModule, Provider<KeyedSharedPreferences> provider, Provider<UserApplicationSettingsService> provider2, Provider<CountryService> provider3) {
        this.module = applicationModule;
        this.prefsProvider = provider;
        this.userApplicationSettingsServiceProvider = provider2;
        this.countryServiceProvider = provider3;
    }

    public LocalSettingsService get() {
        return provideInstance(this.module, this.prefsProvider, this.userApplicationSettingsServiceProvider, this.countryServiceProvider);
    }

    public static LocalSettingsService provideInstance(ApplicationModule applicationModule, Provider<KeyedSharedPreferences> provider, Provider<UserApplicationSettingsService> provider2, Provider<CountryService> provider3) {
        return proxyProvideSettingsService(applicationModule, (KeyedSharedPreferences) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvideSettingsServiceFactory create(ApplicationModule applicationModule, Provider<KeyedSharedPreferences> provider, Provider<UserApplicationSettingsService> provider2, Provider<CountryService> provider3) {
        return new ApplicationModule_ProvideSettingsServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static LocalSettingsService proxyProvideSettingsService(ApplicationModule applicationModule, KeyedSharedPreferences keyedSharedPreferences, Lazy<UserApplicationSettingsService> lazy, Lazy<CountryService> lazy2) {
        return (LocalSettingsService) Preconditions.checkNotNull(applicationModule.provideSettingsService(keyedSharedPreferences, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
