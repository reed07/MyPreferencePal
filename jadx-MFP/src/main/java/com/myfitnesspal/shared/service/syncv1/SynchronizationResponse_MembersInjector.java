package com.myfitnesspal.shared.service.syncv1;

import android.content.Context;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.db.table.RecipesTable;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SynchronizationResponse_MembersInjector implements MembersInjector<SynchronizationResponse> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseService> exerciseServiceProvider;
    private final Provider<ExternalNutritionService> externalNutritionServiceProvider;
    private final Provider<FoodNotesTable> foodNotesTableProvider;
    private final Provider<FoodPermissionsService> foodPermissionsServiceProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final Provider<RecipesTable> recipesTableProvider;
    private final Provider<RemindersService> remindersServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepsServiceProvider;
    private final Provider<SyncSettings> syncSettingsProvider;
    private final Provider<UserImageService> userImageServiceProvider;

    public SynchronizationResponse_MembersInjector(Provider<Context> provider, Provider<AppSettings> provider2, Provider<SyncSettings> provider3, Provider<Session> provider4, Provider<DbConnectionManager> provider5, Provider<StepService> provider6, Provider<AnalyticsService> provider7, Provider<DiaryService> provider8, Provider<RemindersService> provider9, Provider<UserImageService> provider10, Provider<ExerciseService> provider11, Provider<MeasurementsService> provider12, Provider<FoodService> provider13, Provider<LoginModel> provider14, Provider<ExternalNutritionService> provider15, Provider<FoodPermissionsService> provider16, Provider<GlobalSettingsService> provider17, Provider<RecipesTable> provider18, Provider<FoodNotesTable> provider19) {
        this.contextProvider = provider;
        this.appSettingsProvider = provider2;
        this.syncSettingsProvider = provider3;
        this.sessionProvider = provider4;
        this.dbConnectionManagerProvider = provider5;
        this.stepsServiceProvider = provider6;
        this.analyticsServiceProvider = provider7;
        this.diaryServiceProvider = provider8;
        this.remindersServiceProvider = provider9;
        this.userImageServiceProvider = provider10;
        this.exerciseServiceProvider = provider11;
        this.measurementsServiceProvider = provider12;
        this.foodServiceProvider = provider13;
        this.loginModelProvider = provider14;
        this.externalNutritionServiceProvider = provider15;
        this.foodPermissionsServiceProvider = provider16;
        this.globalSettingsServiceProvider = provider17;
        this.recipesTableProvider = provider18;
        this.foodNotesTableProvider = provider19;
    }

    public static MembersInjector<SynchronizationResponse> create(Provider<Context> provider, Provider<AppSettings> provider2, Provider<SyncSettings> provider3, Provider<Session> provider4, Provider<DbConnectionManager> provider5, Provider<StepService> provider6, Provider<AnalyticsService> provider7, Provider<DiaryService> provider8, Provider<RemindersService> provider9, Provider<UserImageService> provider10, Provider<ExerciseService> provider11, Provider<MeasurementsService> provider12, Provider<FoodService> provider13, Provider<LoginModel> provider14, Provider<ExternalNutritionService> provider15, Provider<FoodPermissionsService> provider16, Provider<GlobalSettingsService> provider17, Provider<RecipesTable> provider18, Provider<FoodNotesTable> provider19) {
        SynchronizationResponse_MembersInjector synchronizationResponse_MembersInjector = new SynchronizationResponse_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19);
        return synchronizationResponse_MembersInjector;
    }

    public void injectMembers(SynchronizationResponse synchronizationResponse) {
        BinaryResponse_MembersInjector.injectContext(synchronizationResponse, (Context) this.contextProvider.get());
        BinaryResponse_MembersInjector.injectAppSettings(synchronizationResponse, (AppSettings) this.appSettingsProvider.get());
        BinaryResponse_MembersInjector.injectSyncSettings(synchronizationResponse, (SyncSettings) this.syncSettingsProvider.get());
        BinaryResponse_MembersInjector.injectSession(synchronizationResponse, DoubleCheck.lazy(this.sessionProvider));
        BinaryResponse_MembersInjector.injectDbConnectionManager(synchronizationResponse, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectStepsService(synchronizationResponse, DoubleCheck.lazy(this.stepsServiceProvider));
        injectAnalyticsService(synchronizationResponse, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectSession(synchronizationResponse, DoubleCheck.lazy(this.sessionProvider));
        injectDiaryService(synchronizationResponse, DoubleCheck.lazy(this.diaryServiceProvider));
        injectRemindersService(synchronizationResponse, DoubleCheck.lazy(this.remindersServiceProvider));
        injectUserImageService(synchronizationResponse, DoubleCheck.lazy(this.userImageServiceProvider));
        injectExerciseService(synchronizationResponse, DoubleCheck.lazy(this.exerciseServiceProvider));
        injectMeasurementsService(synchronizationResponse, DoubleCheck.lazy(this.measurementsServiceProvider));
        injectFoodService(synchronizationResponse, DoubleCheck.lazy(this.foodServiceProvider));
        injectLoginModel(synchronizationResponse, DoubleCheck.lazy(this.loginModelProvider));
        injectExternalNutritionService(synchronizationResponse, DoubleCheck.lazy(this.externalNutritionServiceProvider));
        injectFoodPermissionsService(synchronizationResponse, DoubleCheck.lazy(this.foodPermissionsServiceProvider));
        injectGlobalSettingsService(synchronizationResponse, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        injectDbConnectionManager(synchronizationResponse, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        injectRecipesTable(synchronizationResponse, DoubleCheck.lazy(this.recipesTableProvider));
        injectFoodNotesTable(synchronizationResponse, DoubleCheck.lazy(this.foodNotesTableProvider));
    }

    public static void injectStepsService(SynchronizationResponse synchronizationResponse, Lazy<StepService> lazy) {
        synchronizationResponse.stepsService = lazy;
    }

    public static void injectAnalyticsService(SynchronizationResponse synchronizationResponse, Lazy<AnalyticsService> lazy) {
        synchronizationResponse.analyticsService = lazy;
    }

    public static void injectSession(SynchronizationResponse synchronizationResponse, Lazy<Session> lazy) {
        synchronizationResponse.session = lazy;
    }

    public static void injectDiaryService(SynchronizationResponse synchronizationResponse, Lazy<DiaryService> lazy) {
        synchronizationResponse.diaryService = lazy;
    }

    public static void injectRemindersService(SynchronizationResponse synchronizationResponse, Lazy<RemindersService> lazy) {
        synchronizationResponse.remindersService = lazy;
    }

    public static void injectUserImageService(SynchronizationResponse synchronizationResponse, Lazy<UserImageService> lazy) {
        synchronizationResponse.userImageService = lazy;
    }

    public static void injectExerciseService(SynchronizationResponse synchronizationResponse, Lazy<ExerciseService> lazy) {
        synchronizationResponse.exerciseService = lazy;
    }

    public static void injectMeasurementsService(SynchronizationResponse synchronizationResponse, Lazy<MeasurementsService> lazy) {
        synchronizationResponse.measurementsService = lazy;
    }

    public static void injectFoodService(SynchronizationResponse synchronizationResponse, Lazy<FoodService> lazy) {
        synchronizationResponse.foodService = lazy;
    }

    public static void injectLoginModel(SynchronizationResponse synchronizationResponse, Lazy<LoginModel> lazy) {
        synchronizationResponse.loginModel = lazy;
    }

    public static void injectExternalNutritionService(SynchronizationResponse synchronizationResponse, Lazy<ExternalNutritionService> lazy) {
        synchronizationResponse.externalNutritionService = lazy;
    }

    public static void injectFoodPermissionsService(SynchronizationResponse synchronizationResponse, Lazy<FoodPermissionsService> lazy) {
        synchronizationResponse.foodPermissionsService = lazy;
    }

    public static void injectGlobalSettingsService(SynchronizationResponse synchronizationResponse, Lazy<GlobalSettingsService> lazy) {
        synchronizationResponse.globalSettingsService = lazy;
    }

    public static void injectDbConnectionManager(SynchronizationResponse synchronizationResponse, DbConnectionManager dbConnectionManager) {
        synchronizationResponse.dbConnectionManager = dbConnectionManager;
    }

    public static void injectRecipesTable(SynchronizationResponse synchronizationResponse, Lazy<RecipesTable> lazy) {
        synchronizationResponse.recipesTable = lazy;
    }

    public static void injectFoodNotesTable(SynchronizationResponse synchronizationResponse, Lazy<FoodNotesTable> lazy) {
        synchronizationResponse.foodNotesTable = lazy;
    }
}
