package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v1.MfpActionApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.DeletedItemsDBAdapter;
import com.myfitnesspal.shared.db.table.DeletedItemsTable;
import com.myfitnesspal.shared.db.table.DeletedMostUsedFoodsTable;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.db.table.FoodsTable;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideFoodServiceFactory implements Factory<FoodService> {
    private final Provider<MfpActionApi> actionApiProvider;
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DeletedItemsDBAdapter> deletedItemsAdapterProvider;
    private final Provider<DeletedItemsTable> deletedItemsTableProvider;
    private final Provider<DeletedMostUsedFoodsTable> deletedMostUsedFoodsTableProvider;
    private final Provider<FoodMapper> foodMapperProvider;
    private final Provider<FoodNotesTable> foodNotesTableProvider;
    private final Provider<FoodsTable> foodsTableProvider;
    private final Provider<MfpV2Api> insightsApiProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvideFoodServiceFactory(ApplicationModule applicationModule, Provider<DeletedMostUsedFoodsTable> provider, Provider<Session> provider2, Provider<FoodsTable> provider3, Provider<DeletedItemsTable> provider4, Provider<AuthTokenProvider> provider5, Provider<MfpV2Api> provider6, Provider<ActionTrackingService> provider7, Provider<MfpActionApi> provider8, Provider<FoodMapper> provider9, Provider<FoodNotesTable> provider10, Provider<DeletedItemsDBAdapter> provider11, Provider<DbConnectionManager> provider12) {
        this.module = applicationModule;
        this.deletedMostUsedFoodsTableProvider = provider;
        this.sessionProvider = provider2;
        this.foodsTableProvider = provider3;
        this.deletedItemsTableProvider = provider4;
        this.authTokensProvider = provider5;
        this.insightsApiProvider = provider6;
        this.actionTrackingServiceProvider = provider7;
        this.actionApiProvider = provider8;
        this.foodMapperProvider = provider9;
        this.foodNotesTableProvider = provider10;
        this.deletedItemsAdapterProvider = provider11;
        this.dbConnectionManagerProvider = provider12;
    }

    public FoodService get() {
        return provideInstance(this.module, this.deletedMostUsedFoodsTableProvider, this.sessionProvider, this.foodsTableProvider, this.deletedItemsTableProvider, this.authTokensProvider, this.insightsApiProvider, this.actionTrackingServiceProvider, this.actionApiProvider, this.foodMapperProvider, this.foodNotesTableProvider, this.deletedItemsAdapterProvider, this.dbConnectionManagerProvider);
    }

    public static FoodService provideInstance(ApplicationModule applicationModule, Provider<DeletedMostUsedFoodsTable> provider, Provider<Session> provider2, Provider<FoodsTable> provider3, Provider<DeletedItemsTable> provider4, Provider<AuthTokenProvider> provider5, Provider<MfpV2Api> provider6, Provider<ActionTrackingService> provider7, Provider<MfpActionApi> provider8, Provider<FoodMapper> provider9, Provider<FoodNotesTable> provider10, Provider<DeletedItemsDBAdapter> provider11, Provider<DbConnectionManager> provider12) {
        return proxyProvideFoodService(applicationModule, (DeletedMostUsedFoodsTable) provider.get(), DoubleCheck.lazy(provider2), (FoodsTable) provider3.get(), (DeletedItemsTable) provider4.get(), DoubleCheck.lazy(provider5), provider6, DoubleCheck.lazy(provider7), provider8, DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12));
    }

    public static ApplicationModule_ProvideFoodServiceFactory create(ApplicationModule applicationModule, Provider<DeletedMostUsedFoodsTable> provider, Provider<Session> provider2, Provider<FoodsTable> provider3, Provider<DeletedItemsTable> provider4, Provider<AuthTokenProvider> provider5, Provider<MfpV2Api> provider6, Provider<ActionTrackingService> provider7, Provider<MfpActionApi> provider8, Provider<FoodMapper> provider9, Provider<FoodNotesTable> provider10, Provider<DeletedItemsDBAdapter> provider11, Provider<DbConnectionManager> provider12) {
        ApplicationModule_ProvideFoodServiceFactory applicationModule_ProvideFoodServiceFactory = new ApplicationModule_ProvideFoodServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
        return applicationModule_ProvideFoodServiceFactory;
    }

    public static FoodService proxyProvideFoodService(ApplicationModule applicationModule, DeletedMostUsedFoodsTable deletedMostUsedFoodsTable, Lazy<Session> lazy, FoodsTable foodsTable, DeletedItemsTable deletedItemsTable, Lazy<AuthTokenProvider> lazy2, Provider<MfpV2Api> provider, Lazy<ActionTrackingService> lazy3, Provider<MfpActionApi> provider2, Lazy<FoodMapper> lazy4, Lazy<FoodNotesTable> lazy5, Lazy<DeletedItemsDBAdapter> lazy6, Lazy<DbConnectionManager> lazy7) {
        return (FoodService) Preconditions.checkNotNull(applicationModule.provideFoodService(deletedMostUsedFoodsTable, lazy, foodsTable, deletedItemsTable, lazy2, provider, lazy3, provider2, lazy4, lazy5, lazy6, lazy7), "Cannot return null from a non-@Nullable @Provides method");
    }
}
