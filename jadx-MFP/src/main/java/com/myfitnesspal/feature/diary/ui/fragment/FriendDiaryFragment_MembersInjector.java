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
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class FriendDiaryFragment_MembersInjector implements MembersInjector<FriendDiaryFragment> {
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
    private final Provider<UserSummaryService> userSummaryServiceProvider;

    public FriendDiaryFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<DiaryService> provider5, Provider<NutrientDashboardRenderer> provider6, Provider<PremiumService> provider7, Provider<UacfScheduler<SyncType>> provider8, Provider<ExerciseStringService> provider9, Provider<NavigationHelper> provider10, Provider<DiaryAnalyticsHelper> provider11, Provider<ActionTrackingService> provider12, Provider<ConfigService> provider13, Provider<Session> provider14, Provider<LocalSettingsService> provider15, Provider<NutrientGoalService> provider16, Provider<NutrientGoalsUtil> provider17, Provider<AppGalleryService> provider18, Provider<StepService> provider19, Provider<NewsFeedAnalyticsHelper> provider20, Provider<MealAnalyticsHelper> provider21, Provider<UserApplicationSettingsService> provider22, Provider<DbConnectionManager> provider23, Provider<FoodSearchActivityFactory> provider24, Provider<UserSummaryService> provider25) {
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
        this.userSummaryServiceProvider = provider25;
    }

    public static MembersInjector<FriendDiaryFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<DiaryService> provider5, Provider<NutrientDashboardRenderer> provider6, Provider<PremiumService> provider7, Provider<UacfScheduler<SyncType>> provider8, Provider<ExerciseStringService> provider9, Provider<NavigationHelper> provider10, Provider<DiaryAnalyticsHelper> provider11, Provider<ActionTrackingService> provider12, Provider<ConfigService> provider13, Provider<Session> provider14, Provider<LocalSettingsService> provider15, Provider<NutrientGoalService> provider16, Provider<NutrientGoalsUtil> provider17, Provider<AppGalleryService> provider18, Provider<StepService> provider19, Provider<NewsFeedAnalyticsHelper> provider20, Provider<MealAnalyticsHelper> provider21, Provider<UserApplicationSettingsService> provider22, Provider<DbConnectionManager> provider23, Provider<FoodSearchActivityFactory> provider24, Provider<UserSummaryService> provider25) {
        FriendDiaryFragment_MembersInjector friendDiaryFragment_MembersInjector = new FriendDiaryFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25);
        return friendDiaryFragment_MembersInjector;
    }

    public void injectMembers(FriendDiaryFragment friendDiaryFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(friendDiaryFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(friendDiaryFragment, (Glide) this.glideProvider.get());
        DiaryFragmentBase_MembersInjector.injectUserEnergyService(friendDiaryFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        DiaryFragmentBase_MembersInjector.injectLocalizedStringsUtil(friendDiaryFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        DiaryFragmentBase_MembersInjector.injectDiaryService(friendDiaryFragment, DoubleCheck.lazy(this.diaryServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNutrientDashboardRenderer(friendDiaryFragment, DoubleCheck.lazy(this.nutrientDashboardRendererProvider));
        DiaryFragmentBase_MembersInjector.injectPremiumService(friendDiaryFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        DiaryFragmentBase_MembersInjector.injectSyncScheduler(friendDiaryFragment, DoubleCheck.lazy(this.syncSchedulerProvider));
        DiaryFragmentBase_MembersInjector.injectExerciseStringService(friendDiaryFragment, DoubleCheck.lazy(this.exerciseStringServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNavigationHelper(friendDiaryFragment, DoubleCheck.lazy(this.navigationHelperProvider));
        DiaryFragmentBase_MembersInjector.injectDiaryAnalyticsHelper(friendDiaryFragment, DoubleCheck.lazy(this.diaryAnalyticsHelperProvider));
        DiaryFragmentBase_MembersInjector.injectActionTrackingService(friendDiaryFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        DiaryFragmentBase_MembersInjector.injectConfigService(friendDiaryFragment, DoubleCheck.lazy(this.configServiceProvider));
        DiaryFragmentBase_MembersInjector.injectSession(friendDiaryFragment, DoubleCheck.lazy(this.sessionProvider));
        DiaryFragmentBase_MembersInjector.injectLocalSettingsService(friendDiaryFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNutrientGoalService(friendDiaryFragment, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNutrientGoalsUtil(friendDiaryFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        DiaryFragmentBase_MembersInjector.injectAppGalleryService(friendDiaryFragment, DoubleCheck.lazy(this.appGalleryServiceProvider));
        DiaryFragmentBase_MembersInjector.injectStepsService(friendDiaryFragment, DoubleCheck.lazy(this.stepsServiceProvider));
        DiaryFragmentBase_MembersInjector.injectNewsFeedAnalyticsHelper(friendDiaryFragment, DoubleCheck.lazy(this.newsFeedAnalyticsHelperProvider));
        DiaryFragmentBase_MembersInjector.injectMealAnalyticsHelper(friendDiaryFragment, DoubleCheck.lazy(this.mealAnalyticsHelperProvider));
        DiaryFragmentBase_MembersInjector.injectUserApplicationSettingsService(friendDiaryFragment, DoubleCheck.lazy(this.userApplicationSettingsServiceProvider));
        DiaryFragmentBase_MembersInjector.injectDbConnectionManager(friendDiaryFragment, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        DiaryFragmentBase_MembersInjector.injectFoodSearchRouter(friendDiaryFragment, DoubleCheck.lazy(this.foodSearchRouterProvider));
        injectUserSummaryService(friendDiaryFragment, DoubleCheck.lazy(this.userSummaryServiceProvider));
    }

    public static void injectUserSummaryService(FriendDiaryFragment friendDiaryFragment, Lazy<UserSummaryService> lazy) {
        friendDiaryFragment.userSummaryService = lazy;
    }
}
