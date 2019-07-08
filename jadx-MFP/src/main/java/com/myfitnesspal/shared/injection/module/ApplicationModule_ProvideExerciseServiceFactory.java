package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideExerciseServiceFactory implements Factory<ExerciseService> {
    private final Provider<MfpV2Api> apiV2Provider;
    private final Provider<ExerciseMapper> exerciseMapperProvider;
    private final Provider<SQLiteDatabaseWrapper> mainDatabaseProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<SQLiteDatabaseWrapper> stockDatabaseProvider;
    private final Provider<SyncUtil> syncUtilProvider;

    public ApplicationModule_ProvideExerciseServiceFactory(ApplicationModule applicationModule, Provider<SyncUtil> provider, Provider<Session> provider2, Provider<MfpV2Api> provider3, Provider<ExerciseMapper> provider4, Provider<SQLiteDatabaseWrapper> provider5, Provider<SQLiteDatabaseWrapper> provider6) {
        this.module = applicationModule;
        this.syncUtilProvider = provider;
        this.sessionProvider = provider2;
        this.apiV2Provider = provider3;
        this.exerciseMapperProvider = provider4;
        this.mainDatabaseProvider = provider5;
        this.stockDatabaseProvider = provider6;
    }

    public ExerciseService get() {
        return provideInstance(this.module, this.syncUtilProvider, this.sessionProvider, this.apiV2Provider, this.exerciseMapperProvider, this.mainDatabaseProvider, this.stockDatabaseProvider);
    }

    public static ExerciseService provideInstance(ApplicationModule applicationModule, Provider<SyncUtil> provider, Provider<Session> provider2, Provider<MfpV2Api> provider3, Provider<ExerciseMapper> provider4, Provider<SQLiteDatabaseWrapper> provider5, Provider<SQLiteDatabaseWrapper> provider6) {
        return proxyProvideExerciseService(applicationModule, (SyncUtil) provider.get(), DoubleCheck.lazy(provider2), provider3, DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static ApplicationModule_ProvideExerciseServiceFactory create(ApplicationModule applicationModule, Provider<SyncUtil> provider, Provider<Session> provider2, Provider<MfpV2Api> provider3, Provider<ExerciseMapper> provider4, Provider<SQLiteDatabaseWrapper> provider5, Provider<SQLiteDatabaseWrapper> provider6) {
        ApplicationModule_ProvideExerciseServiceFactory applicationModule_ProvideExerciseServiceFactory = new ApplicationModule_ProvideExerciseServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6);
        return applicationModule_ProvideExerciseServiceFactory;
    }

    public static ExerciseService proxyProvideExerciseService(ApplicationModule applicationModule, SyncUtil syncUtil, Lazy<Session> lazy, Provider<MfpV2Api> provider, Lazy<ExerciseMapper> lazy2, Lazy<SQLiteDatabaseWrapper> lazy3, Lazy<SQLiteDatabaseWrapper> lazy4) {
        return (ExerciseService) Preconditions.checkNotNull(applicationModule.provideExerciseService(syncUtil, lazy, provider, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}
