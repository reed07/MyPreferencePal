package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.util.FoodMigrationAndCorrectionHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFoodMigrationAndCorrectionHelperFactory implements Factory<FoodMigrationAndCorrectionHelper> {
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFoodMigrationAndCorrectionHelperFactory(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<DbConnectionManager> provider2) {
        this.module = applicationModule;
        this.localSettingsServiceProvider = provider;
        this.dbConnectionManagerProvider = provider2;
    }

    public FoodMigrationAndCorrectionHelper get() {
        return provideInstance(this.module, this.localSettingsServiceProvider, this.dbConnectionManagerProvider);
    }

    public static FoodMigrationAndCorrectionHelper provideInstance(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<DbConnectionManager> provider2) {
        return proxyProvidesFoodMigrationAndCorrectionHelper(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesFoodMigrationAndCorrectionHelperFactory create(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<DbConnectionManager> provider2) {
        return new ApplicationModule_ProvidesFoodMigrationAndCorrectionHelperFactory(applicationModule, provider, provider2);
    }

    public static FoodMigrationAndCorrectionHelper proxyProvidesFoodMigrationAndCorrectionHelper(ApplicationModule applicationModule, Lazy<LocalSettingsService> lazy, Lazy<DbConnectionManager> lazy2) {
        return (FoodMigrationAndCorrectionHelper) Preconditions.checkNotNull(applicationModule.providesFoodMigrationAndCorrectionHelper(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
