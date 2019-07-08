package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideExerciseSortingPrefsFactory implements Factory<SharedPreferences> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideExerciseSortingPrefsFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public SharedPreferences get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static SharedPreferences provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvideExerciseSortingPrefs(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvideExerciseSortingPrefsFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideExerciseSortingPrefsFactory(applicationModule, provider);
    }

    public static SharedPreferences proxyProvideExerciseSortingPrefs(ApplicationModule applicationModule, Context context) {
        return (SharedPreferences) Preconditions.checkNotNull(applicationModule.provideExerciseSortingPrefs(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
