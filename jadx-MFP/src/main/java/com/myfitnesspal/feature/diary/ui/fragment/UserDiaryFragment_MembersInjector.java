package com.myfitnesspal.feature.diary.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.notification.MfpNotificationHandler;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
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

public final class UserDiaryFragment_MembersInjector implements MembersInjector<UserDiaryFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AdsAnalyticsHelper> adsAnalyticsHelperProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiaryAnalyticsHelper> diaryAnalyticsHelperProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseAnalyticsHelper> exerciseAnalyticsHelperProvider;
    private final Provider<ExerciseStringService> exerciseStringServiceProvider;
    private final Provider<FoodSearchActivityFactory> foodSearchRouterProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<MealAnalyticsHelper> mealAnalyticsHelperProvider;
    private final Provider<MfpNotificationHandler> mfpNotificationHandlerProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelperProvider;
    private final Provider<NutrientDashboardRenderer> nutrientDashboardRendererProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepsServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<WalkthroughUtil> walkthroughUtilProvider;
    private final Provider<WaterLoggingAnalyticsHelper> waterLoggingAnalyticsHelperProvider;

    public UserDiaryFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<DiaryService> provider5, Provider<NutrientDashboardRenderer> provider6, Provider<PremiumService> provider7, Provider<UacfScheduler<SyncType>> provider8, Provider<ExerciseStringService> provider9, Provider<NavigationHelper> provider10, Provider<DiaryAnalyticsHelper> provider11, Provider<ActionTrackingService> provider12, Provider<ConfigService> provider13, Provider<Session> provider14, Provider<LocalSettingsService> provider15, Provider<NutrientGoalService> provider16, Provider<NutrientGoalsUtil> provider17, Provider<AppGalleryService> provider18, Provider<StepService> provider19, Provider<NewsFeedAnalyticsHelper> provider20, Provider<MealAnalyticsHelper> provider21, Provider<UserApplicationSettingsService> provider22, Provider<DbConnectionManager> provider23, Provider<FoodSearchActivityFactory> provider24, Provider<MfpNotificationHandler> provider25, Provider<WalkthroughUtil> provider26, Provider<AdsAnalyticsHelper> provider27, Provider<WaterLoggingAnalyticsHelper> provider28, Provider<SyncService> provider29, Provider<GlobalSettingsService> provider30, Provider<ExerciseAnalyticsHelper> provider31) {
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
        this.mfpNotificationHandlerProvider = provider25;
        this.walkthroughUtilProvider = provider26;
        this.adsAnalyticsHelperProvider = provider27;
        this.waterLoggingAnalyticsHelperProvider = provider28;
        this.syncServiceProvider = provider29;
        this.globalSettingsServiceProvider = provider30;
        this.exerciseAnalyticsHelperProvider = provider31;
    }

    public static MembersInjector<UserDiaryFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<DiaryService> provider5, Provider<NutrientDashboardRenderer> provider6, Provider<PremiumService> provider7, Provider<UacfScheduler<SyncType>> provider8, Provider<ExerciseStringService> provider9, Provider<NavigationHelper> provider10, Provider<DiaryAnalyticsHelper> provider11, Provider<ActionTrackingService> provider12, Provider<ConfigService> provider13, Provider<Session> provider14, Provider<LocalSettingsService> provider15, Provider<NutrientGoalService> provider16, Provider<NutrientGoalsUtil> provider17, Provider<AppGalleryService> provider18, Provider<StepService> provider19, Provider<NewsFeedAnalyticsHelper> provider20, Provider<MealAnalyticsHelper> provider21, Provider<UserApplicationSettingsService> provider22, Provider<DbConnectionManager> provider23, Provider<FoodSearchActivityFactory> provider24, Provider<MfpNotificationHandler> provider25, Provider<WalkthroughUtil> provider26, Provider<AdsAnalyticsHelper> provider27, Provider<WaterLoggingAnalyticsHelper> provider28, Provider<SyncService> provider29, Provider<GlobalSettingsService> provider30, Provider<ExerciseAnalyticsHelper> provider31) {
        UserDiaryFragment_MembersInjector userDiaryFragment_MembersInjector = new UserDiaryFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31);
        return userDiaryFragment_MembersInjector;
    }

    public void injectMembers(UserDiaryFragment userDiaryFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(userDiaryFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(userDiaryFragment, (Glide) this.glideProvider.get());
        DiaryFragmentBase_MembersInjector.injectUserEnergyService(userDiaryFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        DiaryFragmentBase_MembersInjector.injectLocalizedStringsUtil(userDiaryFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        DiaryFragmentBase_MembersInjector.injectDiaryService(userDiaryFragment, DoubleCheck.lazy(this.diaryServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNutrientDashboardRenderer(userDiaryFragment, DoubleCheck.lazy(this.nutrientDashboardRendererProvider));
        DiaryFragmentBase_MembersInjector.injectPremiumService(userDiaryFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        DiaryFragmentBase_MembersInjector.injectSyncScheduler(userDiaryFragment, DoubleCheck.lazy(this.syncSchedulerProvider));
        DiaryFragmentBase_MembersInjector.injectExerciseStringService(userDiaryFragment, DoubleCheck.lazy(this.exerciseStringServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNavigationHelper(userDiaryFragment, DoubleCheck.lazy(this.navigationHelperProvider));
        DiaryFragmentBase_MembersInjector.injectDiaryAnalyticsHelper(userDiaryFragment, DoubleCheck.lazy(this.diaryAnalyticsHelperProvider));
        DiaryFragmentBase_MembersInjector.injectActionTrackingService(userDiaryFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        DiaryFragmentBase_MembersInjector.injectConfigService(userDiaryFragment, DoubleCheck.lazy(this.configServiceProvider));
        DiaryFragmentBase_MembersInjector.injectSession(userDiaryFragment, DoubleCheck.lazy(this.sessionProvider));
        DiaryFragmentBase_MembersInjector.injectLocalSettingsService(userDiaryFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNutrientGoalService(userDiaryFragment, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNutrientGoalsUtil(userDiaryFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        DiaryFragmentBase_MembersInjector.injectAppGalleryService(userDiaryFragment, DoubleCheck.lazy(this.appGalleryServiceProvider));
        DiaryFragmentBase_MembersInjector.injectStepsService(userDiaryFragment, DoubleCheck.lazy(this.stepsServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNewsFeedAnalyticsHelper(userDiaryFragment, DoubleCheck.lazy(this.newsFeedAnalyticsHelperProvider));
        DiaryFragmentBase_MembersInjector.injectMealAnalyticsHelper(userDiaryFragment, DoubleCheck.lazy(this.mealAnalyticsHelperProvider));
        DiaryFragmentBase_MembersInjector.injectUserApplicationSettingsService(userDiaryFragment, DoubleCheck.lazy(this.userApplicationSettingsServiceProvider));
        DiaryFragmentBase_MembersInjector.injectDbConnectionManager(userDiaryFragment, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        DiaryFragmentBase_MembersInjector.injectFoodSearchRouter(userDiaryFragment, DoubleCheck.lazy(this.foodSearchRouterProvider));
        injectSession(userDiaryFragment, DoubleCheck.lazy(this.sessionProvider));
        injectMfpNotificationHandler(userDiaryFragment, DoubleCheck.lazy(this.mfpNotificationHandlerProvider));
        injectWalkthroughUtil(userDiaryFragment, DoubleCheck.lazy(this.walkthroughUtilProvider));
        injectAdsAnalyticsHelper(userDiaryFragment, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        injectLocalSettingsService(userDiaryFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectWaterLoggingAnalyticsHelper(userDiaryFragment, DoubleCheck.lazy(this.waterLoggingAnalyticsHelperProvider));
        injectMealAnalyticsHelper(userDiaryFragment, DoubleCheck.lazy(this.mealAnalyticsHelperProvider));
        injectSyncService(userDiaryFragment, DoubleCheck.lazy(this.syncServiceProvider));
        injectGlobalSettingsService(userDiaryFragment, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        injectExerciseAnalyticsHelper(userDiaryFragment, DoubleCheck.lazy(this.exerciseAnalyticsHelperProvider));
        injectFoodSearchRouter(userDiaryFragment, DoubleCheck.lazy(this.foodSearchRouterProvider));
    }

    public static void injectSession(UserDiaryFragment userDiaryFragment, Lazy<Session> lazy) {
        userDiaryFragment.session = lazy;
    }

    public static void injectMfpNotificationHandler(UserDiaryFragment userDiaryFragment, Lazy<MfpNotificationHandler> lazy) {
        userDiaryFragment.mfpNotificationHandler = lazy;
    }

    public static void injectWalkthroughUtil(UserDiaryFragment userDiaryFragment, Lazy<WalkthroughUtil> lazy) {
        userDiaryFragment.walkthroughUtil = lazy;
    }

    public static void injectAdsAnalyticsHelper(UserDiaryFragment userDiaryFragment, Lazy<AdsAnalyticsHelper> lazy) {
        userDiaryFragment.adsAnalyticsHelper = lazy;
    }

    public static void injectLocalSettingsService(UserDiaryFragment userDiaryFragment, Lazy<LocalSettingsService> lazy) {
        userDiaryFragment.localSettingsService = lazy;
    }

    public static void injectWaterLoggingAnalyticsHelper(UserDiaryFragment userDiaryFragment, Lazy<WaterLoggingAnalyticsHelper> lazy) {
        userDiaryFragment.waterLoggingAnalyticsHelper = lazy;
    }

    public static void injectMealAnalyticsHelper(UserDiaryFragment userDiaryFragment, Lazy<MealAnalyticsHelper> lazy) {
        userDiaryFragment.mealAnalyticsHelper = lazy;
    }

    public static void injectSyncService(UserDiaryFragment userDiaryFragment, Lazy<SyncService> lazy) {
        userDiaryFragment.syncService = lazy;
    }

    public static void injectGlobalSettingsService(UserDiaryFragment userDiaryFragment, Lazy<GlobalSettingsService> lazy) {
        userDiaryFragment.globalSettingsService = lazy;
    }

    public static void injectExerciseAnalyticsHelper(UserDiaryFragment userDiaryFragment, Lazy<ExerciseAnalyticsHelper> lazy) {
        userDiaryFragment.exerciseAnalyticsHelper = lazy;
    }

    public static void injectFoodSearchRouter(UserDiaryFragment userDiaryFragment, Lazy<FoodSearchActivityFactory> lazy) {
        userDiaryFragment.foodSearchRouter = lazy;
    }
}
