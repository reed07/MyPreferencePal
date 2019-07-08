package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.FoodPermissionsTable;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesMealServiceFactory implements Factory<MealService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<FoodPermissionsTable> foodPermissionsTableProvider;
    private final Provider<ImageAssociationService> imageAssociationServiceProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;

    public ApplicationModule_ProvidesMealServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<MfpV2Api> provider2, Provider<Session> provider3, Provider<DiaryService> provider4, Provider<ImageAssociationService> provider5, Provider<SyncService> provider6, Provider<FoodPermissionsTable> provider7, Provider<DbConnectionManager> provider8, Provider<CountryService> provider9) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.apiProvider = provider2;
        this.sessionProvider = provider3;
        this.diaryServiceProvider = provider4;
        this.imageAssociationServiceProvider = provider5;
        this.syncServiceProvider = provider6;
        this.foodPermissionsTableProvider = provider7;
        this.dbConnectionManagerProvider = provider8;
        this.countryServiceProvider = provider9;
    }

    public MealService get() {
        return provideInstance(this.module, this.contextProvider, this.apiProvider, this.sessionProvider, this.diaryServiceProvider, this.imageAssociationServiceProvider, this.syncServiceProvider, this.foodPermissionsTableProvider, this.dbConnectionManagerProvider, this.countryServiceProvider);
    }

    public static MealService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<MfpV2Api> provider2, Provider<Session> provider3, Provider<DiaryService> provider4, Provider<ImageAssociationService> provider5, Provider<SyncService> provider6, Provider<FoodPermissionsTable> provider7, Provider<DbConnectionManager> provider8, Provider<CountryService> provider9) {
        return proxyProvidesMealService(applicationModule, (Context) provider.get(), provider2, DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9));
    }

    public static ApplicationModule_ProvidesMealServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<MfpV2Api> provider2, Provider<Session> provider3, Provider<DiaryService> provider4, Provider<ImageAssociationService> provider5, Provider<SyncService> provider6, Provider<FoodPermissionsTable> provider7, Provider<DbConnectionManager> provider8, Provider<CountryService> provider9) {
        ApplicationModule_ProvidesMealServiceFactory applicationModule_ProvidesMealServiceFactory = new ApplicationModule_ProvidesMealServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return applicationModule_ProvidesMealServiceFactory;
    }

    public static MealService proxyProvidesMealService(ApplicationModule applicationModule, Context context, Provider<MfpV2Api> provider, Lazy<Session> lazy, Lazy<DiaryService> lazy2, Lazy<ImageAssociationService> lazy3, Lazy<SyncService> lazy4, Lazy<FoodPermissionsTable> lazy5, Lazy<DbConnectionManager> lazy6, Lazy<CountryService> lazy7) {
        return (MealService) Preconditions.checkNotNull(applicationModule.providesMealService(context, provider, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7), "Cannot return null from a non-@Nullable @Provides method");
    }
}
