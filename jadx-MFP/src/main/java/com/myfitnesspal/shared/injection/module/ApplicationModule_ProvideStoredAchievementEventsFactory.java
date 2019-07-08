package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.achievementinterstitialad.service.StoredAchievementEvents;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideStoredAchievementEventsFactory implements Factory<StoredAchievementEvents> {
    private final ApplicationModule module;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public ApplicationModule_ProvideStoredAchievementEventsFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.sharedPreferencesProvider = provider;
    }

    public StoredAchievementEvents get() {
        return provideInstance(this.module, this.sharedPreferencesProvider);
    }

    public static StoredAchievementEvents provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvideStoredAchievementEvents(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvideStoredAchievementEventsFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvideStoredAchievementEventsFactory(applicationModule, provider);
    }

    public static StoredAchievementEvents proxyProvideStoredAchievementEvents(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (StoredAchievementEvents) Preconditions.checkNotNull(applicationModule.provideStoredAchievementEvents(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
