package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.ops.FindAndCorrectFoodsWithMissingInfoOp;
import com.myfitnesspal.shared.util.FoodMigrationAndCorrectionHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesFindAndCorrectFoodsWithMissingInfoOpFactory implements Factory<FindAndCorrectFoodsWithMissingInfoOp> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<FoodMapper> foodMapperProvider;
    private final Provider<FoodMigrationAndCorrectionHelper> foodMigrationAndCorrectionHelperProvider;
    private final SyncModule module;
    private final Provider<Session> sessionProvider;

    public SyncModule_ProvidesFindAndCorrectFoodsWithMissingInfoOpFactory(SyncModule syncModule, Provider<MfpV2Api> provider, Provider<FoodMapper> provider2, Provider<Session> provider3, Provider<FoodMigrationAndCorrectionHelper> provider4, Provider<AnalyticsService> provider5, Provider<DbConnectionManager> provider6) {
        this.module = syncModule;
        this.apiProvider = provider;
        this.foodMapperProvider = provider2;
        this.sessionProvider = provider3;
        this.foodMigrationAndCorrectionHelperProvider = provider4;
        this.analyticsServiceProvider = provider5;
        this.dbConnectionManagerProvider = provider6;
    }

    public FindAndCorrectFoodsWithMissingInfoOp get() {
        return provideInstance(this.module, this.apiProvider, this.foodMapperProvider, this.sessionProvider, this.foodMigrationAndCorrectionHelperProvider, this.analyticsServiceProvider, this.dbConnectionManagerProvider);
    }

    public static FindAndCorrectFoodsWithMissingInfoOp provideInstance(SyncModule syncModule, Provider<MfpV2Api> provider, Provider<FoodMapper> provider2, Provider<Session> provider3, Provider<FoodMigrationAndCorrectionHelper> provider4, Provider<AnalyticsService> provider5, Provider<DbConnectionManager> provider6) {
        return proxyProvidesFindAndCorrectFoodsWithMissingInfoOp(syncModule, provider, DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static SyncModule_ProvidesFindAndCorrectFoodsWithMissingInfoOpFactory create(SyncModule syncModule, Provider<MfpV2Api> provider, Provider<FoodMapper> provider2, Provider<Session> provider3, Provider<FoodMigrationAndCorrectionHelper> provider4, Provider<AnalyticsService> provider5, Provider<DbConnectionManager> provider6) {
        SyncModule_ProvidesFindAndCorrectFoodsWithMissingInfoOpFactory syncModule_ProvidesFindAndCorrectFoodsWithMissingInfoOpFactory = new SyncModule_ProvidesFindAndCorrectFoodsWithMissingInfoOpFactory(syncModule, provider, provider2, provider3, provider4, provider5, provider6);
        return syncModule_ProvidesFindAndCorrectFoodsWithMissingInfoOpFactory;
    }

    public static FindAndCorrectFoodsWithMissingInfoOp proxyProvidesFindAndCorrectFoodsWithMissingInfoOp(SyncModule syncModule, Provider<MfpV2Api> provider, Lazy<FoodMapper> lazy, Lazy<Session> lazy2, Lazy<FoodMigrationAndCorrectionHelper> lazy3, Lazy<AnalyticsService> lazy4, Lazy<DbConnectionManager> lazy5) {
        return (FindAndCorrectFoodsWithMissingInfoOp) Preconditions.checkNotNull(syncModule.providesFindAndCorrectFoodsWithMissingInfoOp(provider, lazy, lazy2, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }
}
