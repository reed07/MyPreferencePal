package com.myfitnesspal.feature.explore.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.explore.service.ExploreService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.service.MealService;
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
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity_MembersInjector;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ExploreActivity_MembersInjector implements MembersInjector<ExploreActivity> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AdUnitService> adUnitServiceProvider;
    private final Provider<AdsAnalyticsHelper> adsAnalyticsHelperProvider;
    private final Provider<AdsSettings> adsSettingsProvider;
    private final Provider<AdvancedDebuggingUtil> advancedDebuggingUtilProvider;
    private final Provider<UacfScheduler<AnalyticsSyncMode>> analyticsSyncSchedulerProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ChallengesAnalyticsHelper> challengesAnalyticsHelperProvider;
    private final Provider<CommunityService> communityServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<ExploreService> exploreServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<InputMethodManager> immProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MealService> mealServiceProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final Provider<PermissionAnalyticsHelper> permissionAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<SHealthConnection> samsungConnectionProvider;
    private final Provider<StartupManager> startupManagerProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public ExploreActivity_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<ExploreService> provider29, Provider<ImageService> provider30, Provider<MealService> provider31, Provider<UserEnergyService> provider32, Provider<ChallengesAnalyticsHelper> provider33, Provider<CommunityService> provider34) {
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
        this.exploreServiceProvider = provider29;
        this.imageServiceProvider = provider30;
        this.mealServiceProvider = provider31;
        this.userEnergyServiceProvider = provider32;
        this.challengesAnalyticsHelperProvider = provider33;
        this.communityServiceProvider = provider34;
    }

    public static MembersInjector<ExploreActivity> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<ExploreService> provider29, Provider<ImageService> provider30, Provider<MealService> provider31, Provider<UserEnergyService> provider32, Provider<ChallengesAnalyticsHelper> provider33, Provider<CommunityService> provider34) {
        ExploreActivity_MembersInjector exploreActivity_MembersInjector = new ExploreActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34);
        return exploreActivity_MembersInjector;
    }

    public void injectMembers(ExploreActivity exploreActivity) {
        MfpActivity_MembersInjector.injectAdsSettings(exploreActivity, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(exploreActivity, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(exploreActivity, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(exploreActivity, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(exploreActivity, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(exploreActivity, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(exploreActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(exploreActivity, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(exploreActivity, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(exploreActivity, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(exploreActivity, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(exploreActivity, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(exploreActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(exploreActivity, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(exploreActivity, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(exploreActivity, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(exploreActivity, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(exploreActivity, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(exploreActivity, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(exploreActivity, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(exploreActivity, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(exploreActivity, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(exploreActivity, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(exploreActivity, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(exploreActivity, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(exploreActivity, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(exploreActivity, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(exploreActivity, DoubleCheck.lazy(this.locationServiceProvider));
        injectExploreService(exploreActivity, DoubleCheck.lazy(this.exploreServiceProvider));
        injectImageService(exploreActivity, DoubleCheck.lazy(this.imageServiceProvider));
        injectMealService(exploreActivity, DoubleCheck.lazy(this.mealServiceProvider));
        injectUserEnergyService(exploreActivity, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectChallengesAnalyticsHelper(exploreActivity, DoubleCheck.lazy(this.challengesAnalyticsHelperProvider));
        injectCommunityService(exploreActivity, DoubleCheck.lazy(this.communityServiceProvider));
    }

    public static void injectExploreService(ExploreActivity exploreActivity, Lazy<ExploreService> lazy) {
        exploreActivity.exploreService = lazy;
    }

    public static void injectImageService(ExploreActivity exploreActivity, Lazy<ImageService> lazy) {
        exploreActivity.imageService = lazy;
    }

    public static void injectMealService(ExploreActivity exploreActivity, Lazy<MealService> lazy) {
        exploreActivity.mealService = lazy;
    }

    public static void injectUserEnergyService(ExploreActivity exploreActivity, Lazy<UserEnergyService> lazy) {
        exploreActivity.userEnergyService = lazy;
    }

    public static void injectChallengesAnalyticsHelper(ExploreActivity exploreActivity, Lazy<ChallengesAnalyticsHelper> lazy) {
        exploreActivity.challengesAnalyticsHelper = lazy;
    }

    public static void injectCommunityService(ExploreActivity exploreActivity, Lazy<CommunityService> lazy) {
        exploreActivity.communityService = lazy;
    }
}
