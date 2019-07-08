package com.myfitnesspal.feature.search.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsService;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class FoodSearchActivity_MembersInjector implements MembersInjector<FoodSearchActivity> {
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
    private final Provider<CountryService> countryServiceLazyProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<FoodEditorAnalytics> foodEditorAnalyticsProvider;
    private final Provider<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelperProvider;
    private final Provider<FoodSearchActivityFactory> foodSearchRouterProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<InputMethodManager> immProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MealIngredientMapper> mealIngredientMapperProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;
    private final Provider<PermissionAnalyticsHelper> permissionAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<RestaurantLoggingAnalyticsHelper> restaurantLoggingAnalyticsHelperProvider;
    private final Provider<RestaurantLoggingSettingsService> restaurantLoggingSettingsServiceProvider;
    private final Provider<SHealthConnection> samsungConnectionProvider;
    private final Provider<StartupManager> startupManagerProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public FoodSearchActivity_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<CountryService> provider29, Provider<LocalizedStringsUtil> provider30, Provider<UserEnergyService> provider31, Provider<MultiAddFoodHelper> provider32, Provider<FoodSearchAnalyticsHelper> provider33, Provider<RestaurantLoggingSettingsService> provider34, Provider<RestaurantLoggingAnalyticsHelper> provider35, Provider<MealIngredientMapper> provider36, Provider<DiaryService> provider37, Provider<FoodEditorAnalytics> provider38, Provider<FoodSearchActivityFactory> provider39) {
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
        this.countryServiceLazyProvider = provider29;
        this.localizedStringsUtilProvider = provider30;
        this.userEnergyServiceProvider = provider31;
        this.multiAddFoodHelperProvider = provider32;
        this.foodSearchAnalyticsHelperProvider = provider33;
        this.restaurantLoggingSettingsServiceProvider = provider34;
        this.restaurantLoggingAnalyticsHelperProvider = provider35;
        this.mealIngredientMapperProvider = provider36;
        this.diaryServiceProvider = provider37;
        this.foodEditorAnalyticsProvider = provider38;
        this.foodSearchRouterProvider = provider39;
    }

    public static MembersInjector<FoodSearchActivity> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<CountryService> provider29, Provider<LocalizedStringsUtil> provider30, Provider<UserEnergyService> provider31, Provider<MultiAddFoodHelper> provider32, Provider<FoodSearchAnalyticsHelper> provider33, Provider<RestaurantLoggingSettingsService> provider34, Provider<RestaurantLoggingAnalyticsHelper> provider35, Provider<MealIngredientMapper> provider36, Provider<DiaryService> provider37, Provider<FoodEditorAnalytics> provider38, Provider<FoodSearchActivityFactory> provider39) {
        FoodSearchActivity_MembersInjector foodSearchActivity_MembersInjector = new FoodSearchActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37, provider38, provider39);
        return foodSearchActivity_MembersInjector;
    }

    public void injectMembers(FoodSearchActivity foodSearchActivity) {
        MfpActivity_MembersInjector.injectAdsSettings(foodSearchActivity, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(foodSearchActivity, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(foodSearchActivity, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(foodSearchActivity, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(foodSearchActivity, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(foodSearchActivity, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(foodSearchActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(foodSearchActivity, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(foodSearchActivity, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(foodSearchActivity, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(foodSearchActivity, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(foodSearchActivity, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(foodSearchActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(foodSearchActivity, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(foodSearchActivity, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(foodSearchActivity, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(foodSearchActivity, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(foodSearchActivity, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(foodSearchActivity, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(foodSearchActivity, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(foodSearchActivity, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(foodSearchActivity, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(foodSearchActivity, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(foodSearchActivity, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(foodSearchActivity, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(foodSearchActivity, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(foodSearchActivity, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(foodSearchActivity, DoubleCheck.lazy(this.locationServiceProvider));
        injectCountryServiceLazy(foodSearchActivity, DoubleCheck.lazy(this.countryServiceLazyProvider));
        injectLocalizedStringsUtil(foodSearchActivity, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectUserEnergyService(foodSearchActivity, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectMultiAddFoodHelper(foodSearchActivity, DoubleCheck.lazy(this.multiAddFoodHelperProvider));
        injectSyncScheduler(foodSearchActivity, DoubleCheck.lazy(this.syncSchedulerProvider));
        injectPremiumService(foodSearchActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        injectFoodSearchAnalyticsHelper(foodSearchActivity, DoubleCheck.lazy(this.foodSearchAnalyticsHelperProvider));
        injectRestaurantLoggingSettingsService(foodSearchActivity, DoubleCheck.lazy(this.restaurantLoggingSettingsServiceProvider));
        injectRestaurantLoggingAnalyticsHelper(foodSearchActivity, DoubleCheck.lazy(this.restaurantLoggingAnalyticsHelperProvider));
        injectLocalSettingsService(foodSearchActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectMealIngredientMapper(foodSearchActivity, DoubleCheck.lazy(this.mealIngredientMapperProvider));
        injectDiaryService(foodSearchActivity, DoubleCheck.lazy(this.diaryServiceProvider));
        injectDbConnectionManager(foodSearchActivity, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectFoodEditorAnalytics(foodSearchActivity, DoubleCheck.lazy(this.foodEditorAnalyticsProvider));
        injectFoodSearchRouter(foodSearchActivity, DoubleCheck.lazy(this.foodSearchRouterProvider));
    }

    public static void injectCountryServiceLazy(FoodSearchActivity foodSearchActivity, Lazy<CountryService> lazy) {
        foodSearchActivity.countryServiceLazy = lazy;
    }

    public static void injectLocalizedStringsUtil(FoodSearchActivity foodSearchActivity, Lazy<LocalizedStringsUtil> lazy) {
        foodSearchActivity.localizedStringsUtil = lazy;
    }

    public static void injectUserEnergyService(FoodSearchActivity foodSearchActivity, Lazy<UserEnergyService> lazy) {
        foodSearchActivity.userEnergyService = lazy;
    }

    public static void injectMultiAddFoodHelper(FoodSearchActivity foodSearchActivity, Lazy<MultiAddFoodHelper> lazy) {
        foodSearchActivity.multiAddFoodHelper = lazy;
    }

    public static void injectSyncScheduler(FoodSearchActivity foodSearchActivity, Lazy<UacfScheduler<SyncType>> lazy) {
        foodSearchActivity.syncScheduler = lazy;
    }

    public static void injectPremiumService(FoodSearchActivity foodSearchActivity, Lazy<PremiumService> lazy) {
        foodSearchActivity.premiumService = lazy;
    }

    public static void injectFoodSearchAnalyticsHelper(FoodSearchActivity foodSearchActivity, Lazy<FoodSearchAnalyticsHelper> lazy) {
        foodSearchActivity.foodSearchAnalyticsHelper = lazy;
    }

    public static void injectRestaurantLoggingSettingsService(FoodSearchActivity foodSearchActivity, Lazy<RestaurantLoggingSettingsService> lazy) {
        foodSearchActivity.restaurantLoggingSettingsService = lazy;
    }

    public static void injectRestaurantLoggingAnalyticsHelper(FoodSearchActivity foodSearchActivity, Lazy<RestaurantLoggingAnalyticsHelper> lazy) {
        foodSearchActivity.restaurantLoggingAnalyticsHelper = lazy;
    }

    public static void injectLocalSettingsService(FoodSearchActivity foodSearchActivity, Lazy<LocalSettingsService> lazy) {
        foodSearchActivity.localSettingsService = lazy;
    }

    public static void injectMealIngredientMapper(FoodSearchActivity foodSearchActivity, Lazy<MealIngredientMapper> lazy) {
        foodSearchActivity.mealIngredientMapper = lazy;
    }

    public static void injectDiaryService(FoodSearchActivity foodSearchActivity, Lazy<DiaryService> lazy) {
        foodSearchActivity.diaryService = lazy;
    }

    public static void injectDbConnectionManager(FoodSearchActivity foodSearchActivity, Lazy<DbConnectionManager> lazy) {
        foodSearchActivity.dbConnectionManager = lazy;
    }

    public static void injectFoodEditorAnalytics(FoodSearchActivity foodSearchActivity, Lazy<FoodEditorAnalytics> lazy) {
        foodSearchActivity.foodEditorAnalytics = lazy;
    }

    public static void injectFoodSearchRouter(FoodSearchActivity foodSearchActivity, Lazy<FoodSearchActivityFactory> lazy) {
        foodSearchActivity.foodSearchRouter = lazy;
    }
}
