package com.myfitnesspal.feature.diary.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class DiaryFragmentBase_MembersInjector implements MembersInjector<DiaryFragmentBase> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiaryAnalyticsHelper> diaryAnalyticsHelperProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseStringService> exerciseStringServiceProvider;
    private final Provider<FoodSearchActivityFactory> foodSearchRouterProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<MealAnalyticsHelper> mealAnalyticsHelperProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelperProvider;
    private final Provider<NutrientDashboardRenderer> nutrientDashboardRendererProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepsServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public DiaryFragmentBase_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<DiaryService> provider5, Provider<NutrientDashboardRenderer> provider6, Provider<PremiumService> provider7, Provider<UacfScheduler<SyncType>> provider8, Provider<ExerciseStringService> provider9, Provider<NavigationHelper> provider10, Provider<DiaryAnalyticsHelper> provider11, Provider<ActionTrackingService> provider12, Provider<ConfigService> provider13, Provider<Session> provider14, Provider<LocalSettingsService> provider15, Provider<NutrientGoalService> provider16, Provider<NutrientGoalsUtil> provider17, Provider<AppGalleryService> provider18, Provider<StepService> provider19, Provider<NewsFeedAnalyticsHelper> provider20, Provider<MealAnalyticsHelper> provider21, Provider<UserApplicationSettingsService> provider22, Provider<DbConnectionManager> provider23, Provider<FoodSearchActivityFactory> provider24) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userEnergyServiceProvider = provider3;
        this.localizedStringsUtilProvider = provider4;
        this.diaryServiceProvider = provider5;
        this.nutrientDashboardRendererProvider = provider6;
        this.premiumServiceProvider = provider7;
        this.syncSchedulerProvider = provider8;
        this.exerciseStringServiceProvider = provider9;
        this.navigationHelperProvider = provider10;
        this.diaryAnalyticsHelperProvider = provider11;
        this.actionTrackingServiceProvider = provider12;
        this.configServiceProvider = provider13;
        this.sessionProvider = provider14;
        this.localSettingsServiceProvider = provider15;
        this.nutrientGoalServiceProvider = provider16;
        this.nutrientGoalsUtilProvider = provider17;
        this.appGalleryServiceProvider = provider18;
        this.stepsServiceProvider = provider19;
        this.newsFeedAnalyticsHelperProvider = provider20;
        this.mealAnalyticsHelperProvider = provider21;
        this.userApplicationSettingsServiceProvider = provider22;
        this.dbConnectionManagerProvider = provider23;
        this.foodSearchRouterProvider = provider24;
    }

    public static MembersInjector<DiaryFragmentBase> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<DiaryService> provider5, Provider<NutrientDashboardRenderer> provider6, Provider<PremiumService> provider7, Provider<UacfScheduler<SyncType>> provider8, Provider<ExerciseStringService> provider9, Provider<NavigationHelper> provider10, Provider<DiaryAnalyticsHelper> provider11, Provider<ActionTrackingService> provider12, Provider<ConfigService> provider13, Provider<Session> provider14, Provider<LocalSettingsService> provider15, Provider<NutrientGoalService> provider16, Provider<NutrientGoalsUtil> provider17, Provider<AppGalleryService> provider18, Provider<StepService> provider19, Provider<NewsFeedAnalyticsHelper> provider20, Provider<MealAnalyticsHelper> provider21, Provider<UserApplicationSettingsService> provider22, Provider<DbConnectionManager> provider23, Provider<FoodSearchActivityFactory> provider24) {
        DiaryFragmentBase_MembersInjector diaryFragmentBase_MembersInjector = new DiaryFragmentBase_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24);
        return diaryFragmentBase_MembersInjector;
    }

    public void injectMembers(DiaryFragmentBase diaryFragmentBase) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(diaryFragmentBase, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(diaryFragmentBase, (Glide) this.glideProvider.get());
        injectUserEnergyService(diaryFragmentBase, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectLocalizedStringsUtil(diaryFragmentBase, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectDiaryService(diaryFragmentBase, DoubleCheck.lazy(this.diaryServiceProvider));
        injectNutrientDashboardRenderer(diaryFragmentBase, DoubleCheck.lazy(this.nutrientDashboardRendererProvider));
        injectPremiumService(diaryFragmentBase, DoubleCheck.lazy(this.premiumServiceProvider));
        injectSyncScheduler(diaryFragmentBase, DoubleCheck.lazy(this.syncSchedulerProvider));
        injectExerciseStringService(diaryFragmentBase, DoubleCheck.lazy(this.exerciseStringServiceProvider));
        injectNavigationHelper(diaryFragmentBase, DoubleCheck.lazy(this.navigationHelperProvider));
        injectDiaryAnalyticsHelper(diaryFragmentBase, DoubleCheck.lazy(this.diaryAnalyticsHelperProvider));
        injectActionTrackingService(diaryFragmentBase, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectConfigService(diaryFragmentBase, DoubleCheck.lazy(this.configServiceProvider));
        injectSession(diaryFragmentBase, DoubleCheck.lazy(this.sessionProvider));
        injectLocalSettingsService(diaryFragmentBase, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectNutrientGoalService(diaryFragmentBase, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectNutrientGoalsUtil(diaryFragmentBase, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectAppGalleryService(diaryFragmentBase, DoubleCheck.lazy(this.appGalleryServiceProvider));
        injectStepsService(diaryFragmentBase, DoubleCheck.lazy(this.stepsServiceProvider));
        injectNewsFeedAnalyticsHelper(diaryFragmentBase, DoubleCheck.lazy(this.newsFeedAnalyticsHelperProvider));
        injectMealAnalyticsHelper(diaryFragmentBase, DoubleCheck.lazy(this.mealAnalyticsHelperProvider));
        injectUserApplicationSettingsService(diaryFragmentBase, DoubleCheck.lazy(this.userApplicationSettingsServiceProvider));
        injectDbConnectionManager(diaryFragmentBase, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectFoodSearchRouter(diaryFragmentBase, DoubleCheck.lazy(this.foodSearchRouterProvider));
    }

    public static void injectUserEnergyService(DiaryFragmentBase diaryFragmentBase, Lazy<UserEnergyService> lazy) {
        diaryFragmentBase.userEnergyService = lazy;
    }

    public static void injectLocalizedStringsUtil(DiaryFragmentBase diaryFragmentBase, Lazy<LocalizedStringsUtil> lazy) {
        diaryFragmentBase.localizedStringsUtil = lazy;
    }

    public static void injectDiaryService(DiaryFragmentBase diaryFragmentBase, Lazy<DiaryService> lazy) {
        diaryFragmentBase.diaryService = lazy;
    }

    public static void injectNutrientDashboardRenderer(DiaryFragmentBase diaryFragmentBase, Lazy<NutrientDashboardRenderer> lazy) {
        diaryFragmentBase.nutrientDashboardRenderer = lazy;
    }

    public static void injectPremiumService(DiaryFragmentBase diaryFragmentBase, Lazy<PremiumService> lazy) {
        diaryFragmentBase.premiumService = lazy;
    }

    public static void injectSyncScheduler(DiaryFragmentBase diaryFragmentBase, Lazy<UacfScheduler<SyncType>> lazy) {
        diaryFragmentBase.syncScheduler = lazy;
    }

    public static void injectExerciseStringService(DiaryFragmentBase diaryFragmentBase, Lazy<ExerciseStringService> lazy) {
        diaryFragmentBase.exerciseStringService = lazy;
    }

    public static void injectNavigationHelper(DiaryFragmentBase diaryFragmentBase, Lazy<NavigationHelper> lazy) {
        diaryFragmentBase.navigationHelper = lazy;
    }

    public static void injectDiaryAnalyticsHelper(DiaryFragmentBase diaryFragmentBase, Lazy<DiaryAnalyticsHelper> lazy) {
        diaryFragmentBase.diaryAnalyticsHelper = lazy;
    }

    public static void injectActionTrackingService(DiaryFragmentBase diaryFragmentBase, Lazy<ActionTrackingService> lazy) {
        diaryFragmentBase.actionTrackingService = lazy;
    }

    public static void injectConfigService(DiaryFragmentBase diaryFragmentBase, Lazy<ConfigService> lazy) {
        diaryFragmentBase.configService = lazy;
    }

    public static void injectSession(DiaryFragmentBase diaryFragmentBase, Lazy<Session> lazy) {
        diaryFragmentBase.session = lazy;
    }

    public static void injectLocalSettingsService(DiaryFragmentBase diaryFragmentBase, Lazy<LocalSettingsService> lazy) {
        diaryFragmentBase.localSettingsService = lazy;
    }

    public static void injectNutrientGoalService(DiaryFragmentBase diaryFragmentBase, Lazy<NutrientGoalService> lazy) {
        diaryFragmentBase.nutrientGoalService = lazy;
    }

    public static void injectNutrientGoalsUtil(DiaryFragmentBase diaryFragmentBase, Lazy<NutrientGoalsUtil> lazy) {
        diaryFragmentBase.nutrientGoalsUtil = lazy;
    }

    public static void injectAppGalleryService(DiaryFragmentBase diaryFragmentBase, Lazy<AppGalleryService> lazy) {
        diaryFragmentBase.appGalleryService = lazy;
    }

    public static void injectStepsService(DiaryFragmentBase diaryFragmentBase, Lazy<StepService> lazy) {
        diaryFragmentBase.stepsService = lazy;
    }

    public static void injectNewsFeedAnalyticsHelper(DiaryFragmentBase diaryFragmentBase, Lazy<NewsFeedAnalyticsHelper> lazy) {
        diaryFragmentBase.newsFeedAnalyticsHelper = lazy;
    }

    public static void injectMealAnalyticsHelper(DiaryFragmentBase diaryFragmentBase, Lazy<MealAnalyticsHelper> lazy) {
        diaryFragmentBase.mealAnalyticsHelper = lazy;
    }

    public static void injectUserApplicationSettingsService(DiaryFragmentBase diaryFragmentBase, Lazy<UserApplicationSettingsService> lazy) {
        diaryFragmentBase.userApplicationSettingsService = lazy;
    }

    public static void injectDbConnectionManager(DiaryFragmentBase diaryFragmentBase, Lazy<DbConnectionManager> lazy) {
        diaryFragmentBase.dbConnectionManager = lazy;
    }

    public static void injectFoodSearchRouter(DiaryFragmentBase diaryFragmentBase, Lazy<FoodSearchActivityFactory> lazy) {
        diaryFragmentBase.foodSearchRouter = lazy;
    }
}
