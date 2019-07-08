package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.diary.service.DiaryDayCache;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.shared.api.v1.MfpActionApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesDiaryServiceFactory implements Factory<DiaryService> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<MfpActionApi> apiProvider;
    private final Provider<MfpV2Api> apiv2Provider;
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;
    private final Provider<DiaryDayCache> diaryDayCacheProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseEntryMapper> exerciseEntryMapperProvider;
    private final Provider<ExerciseService> exerciseServiceProvider;
    private final Provider<ExternalExerciseService> externalExerciseServiceProvider;
    private final ApplicationModule module;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public ApplicationModule_ProvidesDiaryServiceFactory(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<MfpV2Api> provider2, Provider<DiaryDayCache> provider3, Provider<Session> provider4, Provider<ExternalExerciseService> provider5, Provider<ActionTrackingService> provider6, Provider<AnalyticsService> provider7, Provider<SQLiteDatabaseWrapper> provider8, Provider<ExerciseEntryMapper> provider9, Provider<ExerciseService> provider10, Provider<DiaryService> provider11, Provider<UserEnergyService> provider12, Provider<NutrientGoalService> provider13) {
        this.module = applicationModule;
        this.apiProvider = provider;
        this.apiv2Provider = provider2;
        this.diaryDayCacheProvider = provider3;
        this.sessionProvider = provider4;
        this.externalExerciseServiceProvider = provider5;
        this.actionTrackingServiceProvider = provider6;
        this.analyticsServiceProvider = provider7;
        this.databaseProvider = provider8;
        this.exerciseEntryMapperProvider = provider9;
        this.exerciseServiceProvider = provider10;
        this.diaryServiceProvider = provider11;
        this.userEnergyServiceProvider = provider12;
        this.nutrientGoalServiceProvider = provider13;
    }

    public DiaryService get() {
        return provideInstance(this.module, this.apiProvider, this.apiv2Provider, this.diaryDayCacheProvider, this.sessionProvider, this.externalExerciseServiceProvider, this.actionTrackingServiceProvider, this.analyticsServiceProvider, this.databaseProvider, this.exerciseEntryMapperProvider, this.exerciseServiceProvider, this.diaryServiceProvider, this.userEnergyServiceProvider, this.nutrientGoalServiceProvider);
    }

    public static DiaryService provideInstance(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<MfpV2Api> provider2, Provider<DiaryDayCache> provider3, Provider<Session> provider4, Provider<ExternalExerciseService> provider5, Provider<ActionTrackingService> provider6, Provider<AnalyticsService> provider7, Provider<SQLiteDatabaseWrapper> provider8, Provider<ExerciseEntryMapper> provider9, Provider<ExerciseService> provider10, Provider<DiaryService> provider11, Provider<UserEnergyService> provider12, Provider<NutrientGoalService> provider13) {
        return proxyProvidesDiaryService(applicationModule, provider, provider2, DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13));
    }

    public static ApplicationModule_ProvidesDiaryServiceFactory create(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<MfpV2Api> provider2, Provider<DiaryDayCache> provider3, Provider<Session> provider4, Provider<ExternalExerciseService> provider5, Provider<ActionTrackingService> provider6, Provider<AnalyticsService> provider7, Provider<SQLiteDatabaseWrapper> provider8, Provider<ExerciseEntryMapper> provider9, Provider<ExerciseService> provider10, Provider<DiaryService> provider11, Provider<UserEnergyService> provider12, Provider<NutrientGoalService> provider13) {
        ApplicationModule_ProvidesDiaryServiceFactory applicationModule_ProvidesDiaryServiceFactory = new ApplicationModule_ProvidesDiaryServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
        return applicationModule_ProvidesDiaryServiceFactory;
    }

    public static DiaryService proxyProvidesDiaryService(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<MfpV2Api> provider2, Lazy<DiaryDayCache> lazy, Lazy<Session> lazy2, Lazy<ExternalExerciseService> lazy3, Lazy<ActionTrackingService> lazy4, Lazy<AnalyticsService> lazy5, Lazy<SQLiteDatabaseWrapper> lazy6, Lazy<ExerciseEntryMapper> lazy7, Lazy<ExerciseService> lazy8, Lazy<DiaryService> lazy9, Lazy<UserEnergyService> lazy10, Lazy<NutrientGoalService> lazy11) {
        return (DiaryService) Preconditions.checkNotNull(applicationModule.providesDiaryService(provider, provider2, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11), "Cannot return null from a non-@Nullable @Provides method");
    }
}
