package com.myfitnesspal.feature.progress.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.meals.util.MealSharingDirectionsAnalyticsHelper;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.ui.activity.MfpActivity_MembersInjector;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class StatusUpdateActivity_MembersInjector implements MembersInjector<StatusUpdateActivity> {
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
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<FoodNotesTable> foodNotesProvider;
    private final Provider<FoodPermissionsService> foodPermissionsServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<ImageUploadService> imageUploadServiceProvider;
    private final Provider<InputMethodManager> immProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MealAnalyticsHelper> mealAnalyticsHelperProvider;
    private final Provider<MealSharingDirectionsAnalyticsHelper> mealBrowseAnalyticsProvider;
    private final Provider<MealService> mealServiceProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final Provider<NewsFeedService> newsFeedServiceProvider;
    private final Provider<PermissionAnalyticsHelper> permissionAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<ProgressAnalytics> progressAnalyticsProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<SHealthConnection> samsungConnectionProvider;
    private final Provider<StartupManager> startupManagerProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncService> syncServiceProvider;

    public StatusUpdateActivity_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<ProgressAnalytics> provider29, Provider<ImageService> provider30, Provider<ImageUploadService> provider31, Provider<NewsFeedService> provider32, Provider<FoodPermissionsService> provider33, Provider<MealService> provider34, Provider<MealAnalyticsHelper> provider35, Provider<MealSharingDirectionsAnalyticsHelper> provider36, Provider<FoodNotesTable> provider37) {
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
        this.progressAnalyticsProvider = provider29;
        this.imageServiceProvider = provider30;
        this.imageUploadServiceProvider = provider31;
        this.newsFeedServiceProvider = provider32;
        this.foodPermissionsServiceProvider = provider33;
        this.mealServiceProvider = provider34;
        this.mealAnalyticsHelperProvider = provider35;
        this.mealBrowseAnalyticsProvider = provider36;
        this.foodNotesProvider = provider37;
    }

    public static MembersInjector<StatusUpdateActivity> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<ProgressAnalytics> provider29, Provider<ImageService> provider30, Provider<ImageUploadService> provider31, Provider<NewsFeedService> provider32, Provider<FoodPermissionsService> provider33, Provider<MealService> provider34, Provider<MealAnalyticsHelper> provider35, Provider<MealSharingDirectionsAnalyticsHelper> provider36, Provider<FoodNotesTable> provider37) {
        StatusUpdateActivity_MembersInjector statusUpdateActivity_MembersInjector = new StatusUpdateActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37);
        return statusUpdateActivity_MembersInjector;
    }

    public void injectMembers(StatusUpdateActivity statusUpdateActivity) {
        MfpActivity_MembersInjector.injectAdsSettings(statusUpdateActivity, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(statusUpdateActivity, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(statusUpdateActivity, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(statusUpdateActivity, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(statusUpdateActivity, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(statusUpdateActivity, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(statusUpdateActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(statusUpdateActivity, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(statusUpdateActivity, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(statusUpdateActivity, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(statusUpdateActivity, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(statusUpdateActivity, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(statusUpdateActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(statusUpdateActivity, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(statusUpdateActivity, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(statusUpdateActivity, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(statusUpdateActivity, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(statusUpdateActivity, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(statusUpdateActivity, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(statusUpdateActivity, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(statusUpdateActivity, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(statusUpdateActivity, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(statusUpdateActivity, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(statusUpdateActivity, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(statusUpdateActivity, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(statusUpdateActivity, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(statusUpdateActivity, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(statusUpdateActivity, DoubleCheck.lazy(this.locationServiceProvider));
        injectProgressAnalytics(statusUpdateActivity, DoubleCheck.lazy(this.progressAnalyticsProvider));
        injectImageService(statusUpdateActivity, DoubleCheck.lazy(this.imageServiceProvider));
        injectImageUploadService(statusUpdateActivity, DoubleCheck.lazy(this.imageUploadServiceProvider));
        injectNewsFeedService(statusUpdateActivity, DoubleCheck.lazy(this.newsFeedServiceProvider));
        injectFoodPermissionsService(statusUpdateActivity, DoubleCheck.lazy(this.foodPermissionsServiceProvider));
        injectMealService(statusUpdateActivity, DoubleCheck.lazy(this.mealServiceProvider));
        injectMealAnalyticsHelper(statusUpdateActivity, DoubleCheck.lazy(this.mealAnalyticsHelperProvider));
        injectMealBrowseAnalytics(statusUpdateActivity, DoubleCheck.lazy(this.mealBrowseAnalyticsProvider));
        injectFoodNotes(statusUpdateActivity, DoubleCheck.lazy(this.foodNotesProvider));
    }

    public static void injectProgressAnalytics(StatusUpdateActivity statusUpdateActivity, Lazy<ProgressAnalytics> lazy) {
        statusUpdateActivity.progressAnalytics = lazy;
    }

    public static void injectImageService(StatusUpdateActivity statusUpdateActivity, Lazy<ImageService> lazy) {
        statusUpdateActivity.imageService = lazy;
    }

    public static void injectImageUploadService(StatusUpdateActivity statusUpdateActivity, Lazy<ImageUploadService> lazy) {
        statusUpdateActivity.imageUploadService = lazy;
    }

    public static void injectNewsFeedService(StatusUpdateActivity statusUpdateActivity, Lazy<NewsFeedService> lazy) {
        statusUpdateActivity.newsFeedService = lazy;
    }

    public static void injectFoodPermissionsService(StatusUpdateActivity statusUpdateActivity, Lazy<FoodPermissionsService> lazy) {
        statusUpdateActivity.foodPermissionsService = lazy;
    }

    public static void injectMealService(StatusUpdateActivity statusUpdateActivity, Lazy<MealService> lazy) {
        statusUpdateActivity.mealService = lazy;
    }

    public static void injectMealAnalyticsHelper(StatusUpdateActivity statusUpdateActivity, Lazy<MealAnalyticsHelper> lazy) {
        statusUpdateActivity.mealAnalyticsHelper = lazy;
    }

    public static void injectMealBrowseAnalytics(StatusUpdateActivity statusUpdateActivity, Lazy<MealSharingDirectionsAnalyticsHelper> lazy) {
        statusUpdateActivity.mealBrowseAnalytics = lazy;
    }

    public static void injectFoodNotes(StatusUpdateActivity statusUpdateActivity, Lazy<FoodNotesTable> lazy) {
        statusUpdateActivity.foodNotes = lazy;
    }
}
