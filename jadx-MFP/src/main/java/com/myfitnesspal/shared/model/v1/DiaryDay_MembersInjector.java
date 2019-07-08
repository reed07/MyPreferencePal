package com.myfitnesspal.shared.model.v1;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.InsightSettings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.DiaryNoteMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryFromServerMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodEntryFromServerMapper;
import com.myfitnesspal.shared.model.mapper.impl.WaterEntryMapper;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.squareup.otto.Bus;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class DiaryDay_MembersInjector implements MembersInjector<DiaryDay> {
    private final Provider<Bus> busProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiaryNoteMapper> diaryNoteMapperProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseEntryMapper> exerciseEntryMapperProvider;
    private final Provider<ExerciseEntryFromServerMapper> exerciseV1EntryMapperProvider;
    private final Provider<ExternalNutritionService> externalNutritionServiceProvider;
    private final Provider<FoodEntryFromServerMapper> foodEntryMapperProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<InsightSettings> insightSettingsProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<UserDistanceService> userDistanceServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;
    private final Provider<WaterEntryMapper> waterEntryMapperProvider;

    public DiaryDay_MembersInjector(Provider<UserEnergyService> provider, Provider<UserWeightService> provider2, Provider<UserDistanceService> provider3, Provider<FoodEntryFromServerMapper> provider4, Provider<ExerciseEntryFromServerMapper> provider5, Provider<ExerciseEntryMapper> provider6, Provider<WaterEntryMapper> provider7, Provider<DiaryNoteMapper> provider8, Provider<NutrientGoalService> provider9, Provider<PremiumService> provider10, Provider<FoodService> provider11, Provider<InsightSettings> provider12, Provider<Session> provider13, Provider<UacfScheduler<SyncType>> provider14, Provider<DiaryService> provider15, Provider<Bus> provider16, Provider<DbConnectionManager> provider17, Provider<ExternalNutritionService> provider18) {
        this.userEnergyServiceProvider = provider;
        this.userWeightServiceProvider = provider2;
        this.userDistanceServiceProvider = provider3;
        this.foodEntryMapperProvider = provider4;
        this.exerciseV1EntryMapperProvider = provider5;
        this.exerciseEntryMapperProvider = provider6;
        this.waterEntryMapperProvider = provider7;
        this.diaryNoteMapperProvider = provider8;
        this.nutrientGoalServiceProvider = provider9;
        this.premiumServiceProvider = provider10;
        this.foodServiceProvider = provider11;
        this.insightSettingsProvider = provider12;
        this.sessionProvider = provider13;
        this.syncSchedulerProvider = provider14;
        this.diaryServiceProvider = provider15;
        this.busProvider = provider16;
        this.dbConnectionManagerProvider = provider17;
        this.externalNutritionServiceProvider = provider18;
    }

    public static MembersInjector<DiaryDay> create(Provider<UserEnergyService> provider, Provider<UserWeightService> provider2, Provider<UserDistanceService> provider3, Provider<FoodEntryFromServerMapper> provider4, Provider<ExerciseEntryFromServerMapper> provider5, Provider<ExerciseEntryMapper> provider6, Provider<WaterEntryMapper> provider7, Provider<DiaryNoteMapper> provider8, Provider<NutrientGoalService> provider9, Provider<PremiumService> provider10, Provider<FoodService> provider11, Provider<InsightSettings> provider12, Provider<Session> provider13, Provider<UacfScheduler<SyncType>> provider14, Provider<DiaryService> provider15, Provider<Bus> provider16, Provider<DbConnectionManager> provider17, Provider<ExternalNutritionService> provider18) {
        DiaryDay_MembersInjector diaryDay_MembersInjector = new DiaryDay_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18);
        return diaryDay_MembersInjector;
    }

    public void injectMembers(DiaryDay diaryDay) {
        injectUserEnergyService(diaryDay, (UserEnergyService) this.userEnergyServiceProvider.get());
        injectUserWeightService(diaryDay, (UserWeightService) this.userWeightServiceProvider.get());
        injectUserDistanceService(diaryDay, (UserDistanceService) this.userDistanceServiceProvider.get());
        injectFoodEntryMapper(diaryDay, DoubleCheck.lazy(this.foodEntryMapperProvider));
        injectExerciseV1EntryMapper(diaryDay, DoubleCheck.lazy(this.exerciseV1EntryMapperProvider));
        injectExerciseEntryMapper(diaryDay, DoubleCheck.lazy(this.exerciseEntryMapperProvider));
        injectWaterEntryMapper(diaryDay, DoubleCheck.lazy(this.waterEntryMapperProvider));
        injectDiaryNoteMapper(diaryDay, DoubleCheck.lazy(this.diaryNoteMapperProvider));
        injectNutrientGoalService(diaryDay, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectPremiumService(diaryDay, DoubleCheck.lazy(this.premiumServiceProvider));
        injectFoodService(diaryDay, DoubleCheck.lazy(this.foodServiceProvider));
        injectInsightSettings(diaryDay, DoubleCheck.lazy(this.insightSettingsProvider));
        injectSession(diaryDay, DoubleCheck.lazy(this.sessionProvider));
        injectSyncScheduler(diaryDay, DoubleCheck.lazy(this.syncSchedulerProvider));
        injectDiaryService(diaryDay, DoubleCheck.lazy(this.diaryServiceProvider));
        injectBus(diaryDay, DoubleCheck.lazy(this.busProvider));
        injectDbConnectionManager(diaryDay, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectExternalNutritionService(diaryDay, DoubleCheck.lazy(this.externalNutritionServiceProvider));
    }

    public static void injectUserEnergyService(DiaryDay diaryDay, UserEnergyService userEnergyService) {
        diaryDay.userEnergyService = userEnergyService;
    }

    public static void injectUserWeightService(DiaryDay diaryDay, UserWeightService userWeightService) {
        diaryDay.userWeightService = userWeightService;
    }

    public static void injectUserDistanceService(DiaryDay diaryDay, UserDistanceService userDistanceService) {
        diaryDay.userDistanceService = userDistanceService;
    }

    public static void injectFoodEntryMapper(DiaryDay diaryDay, Lazy<FoodEntryFromServerMapper> lazy) {
        diaryDay.foodEntryMapper = lazy;
    }

    public static void injectExerciseV1EntryMapper(DiaryDay diaryDay, Lazy<ExerciseEntryFromServerMapper> lazy) {
        diaryDay.exerciseV1EntryMapper = lazy;
    }

    public static void injectExerciseEntryMapper(DiaryDay diaryDay, Lazy<ExerciseEntryMapper> lazy) {
        diaryDay.exerciseEntryMapper = lazy;
    }

    public static void injectWaterEntryMapper(DiaryDay diaryDay, Lazy<WaterEntryMapper> lazy) {
        diaryDay.waterEntryMapper = lazy;
    }

    public static void injectDiaryNoteMapper(DiaryDay diaryDay, Lazy<DiaryNoteMapper> lazy) {
        diaryDay.diaryNoteMapper = lazy;
    }

    public static void injectNutrientGoalService(DiaryDay diaryDay, Lazy<NutrientGoalService> lazy) {
        diaryDay.nutrientGoalService = lazy;
    }

    public static void injectPremiumService(DiaryDay diaryDay, Lazy<PremiumService> lazy) {
        diaryDay.premiumService = lazy;
    }

    public static void injectFoodService(DiaryDay diaryDay, Lazy<FoodService> lazy) {
        diaryDay.foodService = lazy;
    }

    public static void injectInsightSettings(DiaryDay diaryDay, Lazy<InsightSettings> lazy) {
        diaryDay.insightSettings = lazy;
    }

    public static void injectSession(DiaryDay diaryDay, Lazy<Session> lazy) {
        diaryDay.session = lazy;
    }

    public static void injectSyncScheduler(DiaryDay diaryDay, Lazy<UacfScheduler<SyncType>> lazy) {
        diaryDay.syncScheduler = lazy;
    }

    public static void injectDiaryService(DiaryDay diaryDay, Lazy<DiaryService> lazy) {
        diaryDay.diaryService = lazy;
    }

    public static void injectBus(DiaryDay diaryDay, Lazy<Bus> lazy) {
        diaryDay.bus = lazy;
    }

    public static void injectDbConnectionManager(DiaryDay diaryDay, Lazy<DbConnectionManager> lazy) {
        diaryDay.dbConnectionManager = lazy;
    }

    public static void injectExternalNutritionService(DiaryDay diaryDay, Lazy<ExternalNutritionService> lazy) {
        diaryDay.externalNutritionService = lazy;
    }
}
