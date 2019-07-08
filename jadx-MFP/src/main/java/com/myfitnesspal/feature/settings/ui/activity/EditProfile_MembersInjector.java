package com.myfitnesspal.feature.settings.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.activity.MfpActivity_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EditProfile_MembersInjector implements MembersInjector<EditProfile> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AdUnitService> adUnitServiceProvider;
    private final Provider<AdsAnalyticsHelper> adsAnalyticsHelperProvider;
    private final Provider<AdsSettings> adsSettingsProvider;
    private final Provider<AdvancedDebuggingUtil> advancedDebuggingUtilProvider;
    private final Provider<UacfScheduler<AnalyticsSyncMode>> analyticsSyncSchedulerProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<ConsentsAnalyticsHelper> consentsAnalyticsHelperProvider;
    private final Provider<ConsentsService> consentsServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<InputMethodManager> immProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<PermissionAnalyticsHelper> permissionAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<SHealthConnection> samsungConnectionProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StartupManager> startupManagerProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserDistanceService> userDistanceServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<UserHeightService> userHeightServiceProvider;
    private final Provider<UserImageService> userImageServiceProvider;
    private final Provider<UserPropertiesService> userPropertiesServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;
    private final Provider<WaterLoggingAnalyticsHelper> waterLoggingAnalyticsHelperProvider;

    public EditProfile_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<LocalizedStringsUtil> provider29, Provider<UserWeightService> provider30, Provider<UserEnergyService> provider31, Provider<UserHeightService> provider32, Provider<UserDistanceService> provider33, Provider<UserPropertiesService> provider34, Provider<UserImageService> provider35, Provider<WaterLoggingAnalyticsHelper> provider36, Provider<NutrientGoalsUtil> provider37, Provider<DiaryService> provider38, Provider<NutrientGoalService> provider39, Provider<Session> provider40, Provider<ConsentsService> provider41, Provider<ConsentsAnalyticsHelper> provider42) {
        this.adsSettingsProvider = provider;
        this.immProvider = provider2;
        this.startupManagerProvider = provider3;
        this.recipeServiceProvider = provider4;
        this.actionTrackingServiceProvider = provider5;
        this.backgroundServiceHelperProvider = provider6;
        this.premiumServiceProvider = provider7;
        this.syncServiceProvider = provider8;
        this.stepServiceProvider = provider9;
        this.syncSchedulerProvider = provider10;
        this.analyticsSyncSchedulerProvider = provider11;
        this.appIndexerBotProvider = provider12;
        this.localSettingsServiceProvider = provider13;
        this.adsAnalyticsHelperProvider = provider14;
        this.mfpAnalyticsServiceProvider = provider15;
        this.googleFitClientProvider = provider16;
        this.samsungConnectionProvider = provider17;
        this.deepLinkManagerProvider = provider18;
        this.configServiceProvider = provider19;
        this.adUnitServiceProvider = provider20;
        this.apiUrlProvider = provider21;
        this.glideProvider = provider22;
        this.globalSettingsServiceProvider = provider23;
        this.recipesAnalyticsHelperProvider = provider24;
        this.dbConnectionManagerProvider = provider25;
        this.advancedDebuggingUtilProvider = provider26;
        this.permissionAnalyticsHelperProvider = provider27;
        this.locationServiceProvider = provider28;
        this.localizedStringsUtilProvider = provider29;
        this.userWeightServiceProvider = provider30;
        this.userEnergyServiceProvider = provider31;
        this.userHeightServiceProvider = provider32;
        this.userDistanceServiceProvider = provider33;
        this.userPropertiesServiceProvider = provider34;
        this.userImageServiceProvider = provider35;
        this.waterLoggingAnalyticsHelperProvider = provider36;
        this.nutrientGoalsUtilProvider = provider37;
        this.diaryServiceProvider = provider38;
        this.nutrientGoalServiceProvider = provider39;
        this.sessionProvider = provider40;
        this.consentsServiceProvider = provider41;
        this.consentsAnalyticsHelperProvider = provider42;
    }

    public static MembersInjector<EditProfile> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<LocalizedStringsUtil> provider29, Provider<UserWeightService> provider30, Provider<UserEnergyService> provider31, Provider<UserHeightService> provider32, Provider<UserDistanceService> provider33, Provider<UserPropertiesService> provider34, Provider<UserImageService> provider35, Provider<WaterLoggingAnalyticsHelper> provider36, Provider<NutrientGoalsUtil> provider37, Provider<DiaryService> provider38, Provider<NutrientGoalService> provider39, Provider<Session> provider40, Provider<ConsentsService> provider41, Provider<ConsentsAnalyticsHelper> provider42) {
        EditProfile_MembersInjector editProfile_MembersInjector = new EditProfile_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37, provider38, provider39, provider40, provider41, provider42);
        return editProfile_MembersInjector;
    }

    public void injectMembers(EditProfile editProfile) {
        MfpActivity_MembersInjector.injectAdsSettings(editProfile, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(editProfile, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(editProfile, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(editProfile, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(editProfile, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(editProfile, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(editProfile, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(editProfile, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(editProfile, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(editProfile, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(editProfile, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(editProfile, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(editProfile, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(editProfile, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(editProfile, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(editProfile, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(editProfile, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(editProfile, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(editProfile, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(editProfile, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(editProfile, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(editProfile, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(editProfile, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(editProfile, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(editProfile, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(editProfile, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(editProfile, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(editProfile, DoubleCheck.lazy(this.locationServiceProvider));
        injectLocalizedStringsUtil(editProfile, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectUserWeightService(editProfile, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectUserEnergyService(editProfile, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectUserHeightService(editProfile, DoubleCheck.lazy(this.userHeightServiceProvider));
        injectUserDistanceService(editProfile, DoubleCheck.lazy(this.userDistanceServiceProvider));
        injectUserPropertiesService(editProfile, DoubleCheck.lazy(this.userPropertiesServiceProvider));
        injectUserImageService(editProfile, DoubleCheck.lazy(this.userImageServiceProvider));
        injectWaterLoggingAnalyticsHelper(editProfile, DoubleCheck.lazy(this.waterLoggingAnalyticsHelperProvider));
        injectDbConnectionManager(editProfile, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectNutrientGoalsUtil(editProfile, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectDiaryService(editProfile, DoubleCheck.lazy(this.diaryServiceProvider));
        injectNutrientGoalService(editProfile, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectSession(editProfile, DoubleCheck.lazy(this.sessionProvider));
        injectConsentsService(editProfile, DoubleCheck.lazy(this.consentsServiceProvider));
        injectConsentsAnalyticsHelper(editProfile, DoubleCheck.lazy(this.consentsAnalyticsHelperProvider));
    }

    public static void injectLocalizedStringsUtil(EditProfile editProfile, Lazy<LocalizedStringsUtil> lazy) {
        editProfile.localizedStringsUtil = lazy;
    }

    public static void injectUserWeightService(EditProfile editProfile, Lazy<UserWeightService> lazy) {
        editProfile.userWeightService = lazy;
    }

    public static void injectUserEnergyService(EditProfile editProfile, Lazy<UserEnergyService> lazy) {
        editProfile.userEnergyService = lazy;
    }

    public static void injectUserHeightService(EditProfile editProfile, Lazy<UserHeightService> lazy) {
        editProfile.userHeightService = lazy;
    }

    public static void injectUserDistanceService(EditProfile editProfile, Lazy<UserDistanceService> lazy) {
        editProfile.userDistanceService = lazy;
    }

    public static void injectUserPropertiesService(EditProfile editProfile, Lazy<UserPropertiesService> lazy) {
        editProfile.userPropertiesService = lazy;
    }

    public static void injectUserImageService(EditProfile editProfile, Lazy<UserImageService> lazy) {
        editProfile.userImageService = lazy;
    }

    public static void injectWaterLoggingAnalyticsHelper(EditProfile editProfile, Lazy<WaterLoggingAnalyticsHelper> lazy) {
        editProfile.waterLoggingAnalyticsHelper = lazy;
    }

    public static void injectDbConnectionManager(EditProfile editProfile, Lazy<DbConnectionManager> lazy) {
        editProfile.dbConnectionManager = lazy;
    }

    public static void injectNutrientGoalsUtil(EditProfile editProfile, Lazy<NutrientGoalsUtil> lazy) {
        editProfile.nutrientGoalsUtil = lazy;
    }

    public static void injectDiaryService(EditProfile editProfile, Lazy<DiaryService> lazy) {
        editProfile.diaryService = lazy;
    }

    public static void injectNutrientGoalService(EditProfile editProfile, Lazy<NutrientGoalService> lazy) {
        editProfile.nutrientGoalService = lazy;
    }

    public static void injectSession(EditProfile editProfile, Lazy<Session> lazy) {
        editProfile.session = lazy;
    }

    public static void injectConsentsService(EditProfile editProfile, Lazy<ConsentsService> lazy) {
        editProfile.consentsService = lazy;
    }

    public static void injectConsentsAnalyticsHelper(EditProfile editProfile, Lazy<ConsentsAnalyticsHelper> lazy) {
        editProfile.consentsAnalyticsHelper = lazy;
    }
}
