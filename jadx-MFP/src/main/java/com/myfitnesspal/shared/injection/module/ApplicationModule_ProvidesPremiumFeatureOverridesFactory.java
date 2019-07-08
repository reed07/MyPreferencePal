package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesPremiumFeatureOverridesFactory implements Factory<PremiumFeatureOverrides> {
    private final ApplicationModule module;
    private final Provider<SharedPreferences> prefsProvider;

    public ApplicationModule_ProvidesPremiumFeatureOverridesFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.prefsProvider = provider;
    }

    public PremiumFeatureOverrides get() {
        return provideInstance(this.module, this.prefsProvider);
    }

    public static PremiumFeatureOverrides provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvidesPremiumFeatureOverrides(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesPremiumFeatureOverridesFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvidesPremiumFeatureOverridesFactory(applicationModule, provider);
    }

    public static PremiumFeatureOverrides proxyProvidesPremiumFeatureOverrides(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (PremiumFeatureOverrides) Preconditions.checkNotNull(applicationModule.providesPremiumFeatureOverrides(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
