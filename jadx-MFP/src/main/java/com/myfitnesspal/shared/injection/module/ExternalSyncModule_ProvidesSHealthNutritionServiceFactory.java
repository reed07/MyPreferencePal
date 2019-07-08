package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthNutritionService;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesSHealthNutritionServiceFactory implements Factory<SHealthNutritionService> {
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<SHealthConnection> connectionProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final ExternalSyncModule module;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public ExternalSyncModule_ProvidesSHealthNutritionServiceFactory(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<ConfigService> provider2, Provider<LocalizedStringsUtil> provider3, Provider<UserEnergyService> provider4, Provider<AppGalleryService> provider5, Provider<DbConnectionManager> provider6) {
        this.module = externalSyncModule;
        this.connectionProvider = provider;
        this.configServiceProvider = provider2;
        this.localizedStringsUtilProvider = provider3;
        this.userEnergyServiceProvider = provider4;
        this.appGalleryServiceProvider = provider5;
        this.dbConnectionManagerProvider = provider6;
    }

    public SHealthNutritionService get() {
        return provideInstance(this.module, this.connectionProvider, this.configServiceProvider, this.localizedStringsUtilProvider, this.userEnergyServiceProvider, this.appGalleryServiceProvider, this.dbConnectionManagerProvider);
    }

    public static SHealthNutritionService provideInstance(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<ConfigService> provider2, Provider<LocalizedStringsUtil> provider3, Provider<UserEnergyService> provider4, Provider<AppGalleryService> provider5, Provider<DbConnectionManager> provider6) {
        return proxyProvidesSHealthNutritionService(externalSyncModule, (SHealthConnection) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), (DbConnectionManager) provider6.get());
    }

    public static ExternalSyncModule_ProvidesSHealthNutritionServiceFactory create(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<ConfigService> provider2, Provider<LocalizedStringsUtil> provider3, Provider<UserEnergyService> provider4, Provider<AppGalleryService> provider5, Provider<DbConnectionManager> provider6) {
        ExternalSyncModule_ProvidesSHealthNutritionServiceFactory externalSyncModule_ProvidesSHealthNutritionServiceFactory = new ExternalSyncModule_ProvidesSHealthNutritionServiceFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5, provider6);
        return externalSyncModule_ProvidesSHealthNutritionServiceFactory;
    }

    public static SHealthNutritionService proxyProvidesSHealthNutritionService(ExternalSyncModule externalSyncModule, SHealthConnection sHealthConnection, Lazy<ConfigService> lazy, Lazy<LocalizedStringsUtil> lazy2, Lazy<UserEnergyService> lazy3, Lazy<AppGalleryService> lazy4, DbConnectionManager dbConnectionManager) {
        return (SHealthNutritionService) Preconditions.checkNotNull(externalSyncModule.providesSHealthNutritionService(sHealthConnection, lazy, lazy2, lazy3, lazy4, dbConnectionManager), "Cannot return null from a non-@Nullable @Provides method");
    }
}
