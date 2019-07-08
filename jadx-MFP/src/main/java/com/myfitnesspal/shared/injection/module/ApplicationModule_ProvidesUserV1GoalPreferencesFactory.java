package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.model.v1.UserV1GoalPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesUserV1GoalPreferencesFactory implements Factory<UserV1GoalPreferences> {
    private final ApplicationModule module;
    private final Provider<SharedPreferences> prefsProvider;

    public ApplicationModule_ProvidesUserV1GoalPreferencesFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.prefsProvider = provider;
    }

    public UserV1GoalPreferences get() {
        return provideInstance(this.module, this.prefsProvider);
    }

    public static UserV1GoalPreferences provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvidesUserV1GoalPreferences(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesUserV1GoalPreferencesFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvidesUserV1GoalPreferencesFactory(applicationModule, provider);
    }

    public static UserV1GoalPreferences proxyProvidesUserV1GoalPreferences(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (UserV1GoalPreferences) Preconditions.checkNotNull(applicationModule.providesUserV1GoalPreferences(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
