package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.api.MfpApiSettings;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideApiSettingsFactory implements Factory<MfpApiSettings> {
    private final ApplicationModule module;
    private final Provider<SharedPreferences> prefsProvider;

    public ApplicationModule_ProvideApiSettingsFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.prefsProvider = provider;
    }

    public MfpApiSettings get() {
        return provideInstance(this.module, this.prefsProvider);
    }

    public static MfpApiSettings provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvideApiSettings(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvideApiSettingsFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvideApiSettingsFactory(applicationModule, provider);
    }

    public static MfpApiSettings proxyProvideApiSettings(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (MfpApiSettings) Preconditions.checkNotNull(applicationModule.provideApiSettings(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
