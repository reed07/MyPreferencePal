package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideDbConnectionManagerFactory implements Factory<DbConnectionManager> {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ExerciseService> exerciseServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideDbConnectionManagerFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<ExerciseService> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.appSettingsProvider = provider2;
        this.exerciseServiceProvider = provider3;
    }

    public DbConnectionManager get() {
        return provideInstance(this.module, this.contextProvider, this.appSettingsProvider, this.exerciseServiceProvider);
    }

    public static DbConnectionManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<ExerciseService> provider3) {
        return proxyProvideDbConnectionManager(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvideDbConnectionManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<ExerciseService> provider3) {
        return new ApplicationModule_ProvideDbConnectionManagerFactory(applicationModule, provider, provider2, provider3);
    }

    public static DbConnectionManager proxyProvideDbConnectionManager(ApplicationModule applicationModule, Context context, Lazy<AppSettings> lazy, Lazy<ExerciseService> lazy2) {
        return (DbConnectionManager) Preconditions.checkNotNull(applicationModule.provideDbConnectionManager(context, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
