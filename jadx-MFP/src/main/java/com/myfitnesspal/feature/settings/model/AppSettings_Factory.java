package com.myfitnesspal.feature.settings.model;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AppSettings_Factory implements Factory<AppSettings> {
    private final Provider<ApiJsonMapper> mapperProvider;
    private final Provider<SharedPreferences> prefsProvider;

    public AppSettings_Factory(Provider<SharedPreferences> provider, Provider<ApiJsonMapper> provider2) {
        this.prefsProvider = provider;
        this.mapperProvider = provider2;
    }

    public AppSettings get() {
        return provideInstance(this.prefsProvider, this.mapperProvider);
    }

    public static AppSettings provideInstance(Provider<SharedPreferences> provider, Provider<ApiJsonMapper> provider2) {
        return new AppSettings((SharedPreferences) provider.get(), provider2);
    }

    public static AppSettings_Factory create(Provider<SharedPreferences> provider, Provider<ApiJsonMapper> provider2) {
        return new AppSettings_Factory(provider, provider2);
    }

    public static AppSettings newAppSettings(SharedPreferences sharedPreferences, Provider<ApiJsonMapper> provider) {
        return new AppSettings(sharedPreferences, provider);
    }
}
