package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.foodfeedback.repository.FoodFeedbackAction;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideFoodFeedbackRepositoryFactory implements Factory<FoodFeedbackAction> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideFoodFeedbackRepositoryFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<DbConnectionManager> provider2, Provider<AppSettings> provider3) {
        this.module = applicationModule;
        this.apiProvider = provider;
        this.dbConnectionManagerProvider = provider2;
        this.appSettingsProvider = provider3;
    }

    public FoodFeedbackAction get() {
        return provideInstance(this.module, this.apiProvider, this.dbConnectionManagerProvider, this.appSettingsProvider);
    }

    public static FoodFeedbackAction provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<DbConnectionManager> provider2, Provider<AppSettings> provider3) {
        return proxyProvideFoodFeedbackRepository(applicationModule, provider, DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvideFoodFeedbackRepositoryFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<DbConnectionManager> provider2, Provider<AppSettings> provider3) {
        return new ApplicationModule_ProvideFoodFeedbackRepositoryFactory(applicationModule, provider, provider2, provider3);
    }

    public static FoodFeedbackAction proxyProvideFoodFeedbackRepository(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Lazy<DbConnectionManager> lazy, Lazy<AppSettings> lazy2) {
        return (FoodFeedbackAction) Preconditions.checkNotNull(applicationModule.provideFoodFeedbackRepository(provider, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
