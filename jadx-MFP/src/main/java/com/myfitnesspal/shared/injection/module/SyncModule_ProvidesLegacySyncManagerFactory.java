package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.util.PasswordResetHelper;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.v1.MfpSyncApi;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.LegacySyncManager;
import com.myfitnesspal.shared.service.syncv1.SyncPointerService;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesLegacySyncManagerFactory implements Factory<LegacySyncManager> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<Context> contextProvider;
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseMapper> exerciseMapperProvider;
    private final Provider<ExerciseService> exerciseServiceProvider;
    private final Provider<ExerciseStringService> exerciseStringServiceProvider;
    private final Provider<FoodPermissionsService> foodPermissionsServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final SyncModule module;
    private final Provider<PasswordResetHelper> passwordResetHelperProvider;
    private final Provider<RemindersService> remindersServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SignUpModel> signUpModelProvider;
    private final Provider<MfpSyncApi> syncApiProvider;
    private final Provider<SyncPointerService> syncPointerServiceProvider;
    private final Provider<SyncUtil> syncUtilProvider;
    private final Provider<UserImageService> userImageServiceProvider;

    public SyncModule_ProvidesLegacySyncManagerFactory(SyncModule syncModule, Provider<Context> provider, Provider<ApiUrlProvider> provider2, Provider<AnalyticsService> provider3, Provider<PasswordResetHelper> provider4, Provider<SyncPointerService> provider5, Provider<MfpSyncApi> provider6, Provider<Session> provider7, Provider<SyncUtil> provider8, Provider<DiaryService> provider9, Provider<RemindersService> provider10, Provider<UserImageService> provider11, Provider<ExerciseService> provider12, Provider<SQLiteDatabaseWrapper> provider13, Provider<ExerciseStringService> provider14, Provider<MeasurementsService> provider15, Provider<ExerciseMapper> provider16, Provider<LocalSettingsService> provider17, Provider<LoginModel> provider18, Provider<SignUpModel> provider19, Provider<FoodPermissionsService> provider20, Provider<DbConnectionManager> provider21) {
        this.module = syncModule;
        this.contextProvider = provider;
        this.apiUrlProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.passwordResetHelperProvider = provider4;
        this.syncPointerServiceProvider = provider5;
        this.syncApiProvider = provider6;
        this.sessionProvider = provider7;
        this.syncUtilProvider = provider8;
        this.diaryServiceProvider = provider9;
        this.remindersServiceProvider = provider10;
        this.userImageServiceProvider = provider11;
        this.exerciseServiceProvider = provider12;
        this.databaseProvider = provider13;
        this.exerciseStringServiceProvider = provider14;
        this.measurementsServiceProvider = provider15;
        this.exerciseMapperProvider = provider16;
        this.localSettingsServiceProvider = provider17;
        this.loginModelProvider = provider18;
        this.signUpModelProvider = provider19;
        this.foodPermissionsServiceProvider = provider20;
        this.dbConnectionManagerProvider = provider21;
    }

    public LegacySyncManager get() {
        SyncModule syncModule = this.module;
        return provideInstance(syncModule, this.contextProvider, this.apiUrlProvider, this.analyticsServiceProvider, this.passwordResetHelperProvider, this.syncPointerServiceProvider, this.syncApiProvider, this.sessionProvider, this.syncUtilProvider, this.diaryServiceProvider, this.remindersServiceProvider, this.userImageServiceProvider, this.exerciseServiceProvider, this.databaseProvider, this.exerciseStringServiceProvider, this.measurementsServiceProvider, this.exerciseMapperProvider, this.localSettingsServiceProvider, this.loginModelProvider, this.signUpModelProvider, this.foodPermissionsServiceProvider, this.dbConnectionManagerProvider);
    }

    public static LegacySyncManager provideInstance(SyncModule syncModule, Provider<Context> provider, Provider<ApiUrlProvider> provider2, Provider<AnalyticsService> provider3, Provider<PasswordResetHelper> provider4, Provider<SyncPointerService> provider5, Provider<MfpSyncApi> provider6, Provider<Session> provider7, Provider<SyncUtil> provider8, Provider<DiaryService> provider9, Provider<RemindersService> provider10, Provider<UserImageService> provider11, Provider<ExerciseService> provider12, Provider<SQLiteDatabaseWrapper> provider13, Provider<ExerciseStringService> provider14, Provider<MeasurementsService> provider15, Provider<ExerciseMapper> provider16, Provider<LocalSettingsService> provider17, Provider<LoginModel> provider18, Provider<SignUpModel> provider19, Provider<FoodPermissionsService> provider20, Provider<DbConnectionManager> provider21) {
        return proxyProvidesLegacySyncManager(syncModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), provider6, DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13), DoubleCheck.lazy(provider14), DoubleCheck.lazy(provider15), DoubleCheck.lazy(provider16), DoubleCheck.lazy(provider17), DoubleCheck.lazy(provider18), DoubleCheck.lazy(provider19), DoubleCheck.lazy(provider20), DoubleCheck.lazy(provider21));
    }

    public static SyncModule_ProvidesLegacySyncManagerFactory create(SyncModule syncModule, Provider<Context> provider, Provider<ApiUrlProvider> provider2, Provider<AnalyticsService> provider3, Provider<PasswordResetHelper> provider4, Provider<SyncPointerService> provider5, Provider<MfpSyncApi> provider6, Provider<Session> provider7, Provider<SyncUtil> provider8, Provider<DiaryService> provider9, Provider<RemindersService> provider10, Provider<UserImageService> provider11, Provider<ExerciseService> provider12, Provider<SQLiteDatabaseWrapper> provider13, Provider<ExerciseStringService> provider14, Provider<MeasurementsService> provider15, Provider<ExerciseMapper> provider16, Provider<LocalSettingsService> provider17, Provider<LoginModel> provider18, Provider<SignUpModel> provider19, Provider<FoodPermissionsService> provider20, Provider<DbConnectionManager> provider21) {
        SyncModule_ProvidesLegacySyncManagerFactory syncModule_ProvidesLegacySyncManagerFactory = new SyncModule_ProvidesLegacySyncManagerFactory(syncModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21);
        return syncModule_ProvidesLegacySyncManagerFactory;
    }

    public static LegacySyncManager proxyProvidesLegacySyncManager(SyncModule syncModule, Context context, Lazy<ApiUrlProvider> lazy, Lazy<AnalyticsService> lazy2, Lazy<PasswordResetHelper> lazy3, Lazy<SyncPointerService> lazy4, Provider<MfpSyncApi> provider, Lazy<Session> lazy5, Lazy<SyncUtil> lazy6, Lazy<DiaryService> lazy7, Lazy<RemindersService> lazy8, Lazy<UserImageService> lazy9, Lazy<ExerciseService> lazy10, Lazy<SQLiteDatabaseWrapper> lazy11, Lazy<ExerciseStringService> lazy12, Lazy<MeasurementsService> lazy13, Lazy<ExerciseMapper> lazy14, Lazy<LocalSettingsService> lazy15, Lazy<LoginModel> lazy16, Lazy<SignUpModel> lazy17, Lazy<FoodPermissionsService> lazy18, Lazy<DbConnectionManager> lazy19) {
        return (LegacySyncManager) Preconditions.checkNotNull(syncModule.providesLegacySyncManager(context, lazy, lazy2, lazy3, lazy4, provider, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16, lazy17, lazy18, lazy19), "Cannot return null from a non-@Nullable @Provides method");
    }
}
