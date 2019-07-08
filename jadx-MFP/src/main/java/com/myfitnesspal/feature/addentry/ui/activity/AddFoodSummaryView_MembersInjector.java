package com.myfitnesspal.feature.addentry.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodService;
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

public final class AddFoodSummaryView_MembersInjector implements MembersInjector<AddFoodSummaryView> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AdUnitService> adUnitServiceProvider;
    private final Provider<AdsAnalyticsHelper> adsAnalyticsHelperProvider;
    private final Provider<AdsSettings> adsSettingsProvider;
    private final Provider<AdvancedDebuggingUtil> advancedDebuggingUtilProvider;
    private final Provider<AnalyticsService> analyticsServiceLazyProvider;
    private final Provider<UacfScheduler<AnalyticsSyncMode>> analyticsSyncSchedulerProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<FoodFeedbackAnalyticsHelper> foodFeedbackAnalyticsHelperProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<InputMethodManager> immProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MealUtil> mealHelperUtilProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperLazyProvider;
    private final Provider<PermissionAnalyticsHelper> permissionAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<SHealthConnection> samsungConnectionProvider;
    private final Provider<SearchService> searchServiceProvider;
    private final Provider<StartupManager> startupManagerProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public AddFoodSummaryView_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<UserEnergyService> provider29, Provider<MealUtil> provider30, Provider<FoodService> provider31, Provider<ImageService> provider32, Provider<DiaryService> provider33, Provider<LocalizedStringsUtil> provider34, Provider<MultiAddFoodHelper> provider35, Provider<SearchService> provider36, Provider<AnalyticsService> provider37, Provider<CountryService> provider38, Provider<FoodFeedbackAnalyticsHelper> provider39) {
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
        this.userEnergyServiceProvider = provider29;
        this.mealHelperUtilProvider = provider30;
        this.foodServiceProvider = provider31;
        this.imageServiceProvider = provider32;
        this.diaryServiceProvider = provider33;
        this.localizedStringsUtilProvider = provider34;
        this.multiAddFoodHelperLazyProvider = provider35;
        this.searchServiceProvider = provider36;
        this.analyticsServiceLazyProvider = provider37;
        this.countryServiceProvider = provider38;
        this.foodFeedbackAnalyticsHelperProvider = provider39;
    }

    public static MembersInjector<AddFoodSummaryView> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<UserEnergyService> provider29, Provider<MealUtil> provider30, Provider<FoodService> provider31, Provider<ImageService> provider32, Provider<DiaryService> provider33, Provider<LocalizedStringsUtil> provider34, Provider<MultiAddFoodHelper> provider35, Provider<SearchService> provider36, Provider<AnalyticsService> provider37, Provider<CountryService> provider38, Provider<FoodFeedbackAnalyticsHelper> provider39) {
        AddFoodSummaryView_MembersInjector addFoodSummaryView_MembersInjector = new AddFoodSummaryView_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37, provider38, provider39);
        return addFoodSummaryView_MembersInjector;
    }

    public void injectMembers(AddFoodSummaryView addFoodSummaryView) {
        MfpActivity_MembersInjector.injectAdsSettings(addFoodSummaryView, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(addFoodSummaryView, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(addFoodSummaryView, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(addFoodSummaryView, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(addFoodSummaryView, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(addFoodSummaryView, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(addFoodSummaryView, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(addFoodSummaryView, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(addFoodSummaryView, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(addFoodSummaryView, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(addFoodSummaryView, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(addFoodSummaryView, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(addFoodSummaryView, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(addFoodSummaryView, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(addFoodSummaryView, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(addFoodSummaryView, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(addFoodSummaryView, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(addFoodSummaryView, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(addFoodSummaryView, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(addFoodSummaryView, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(addFoodSummaryView, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(addFoodSummaryView, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(addFoodSummaryView, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(addFoodSummaryView, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(addFoodSummaryView, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(addFoodSummaryView, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(addFoodSummaryView, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(addFoodSummaryView, DoubleCheck.lazy(this.locationServiceProvider));
        injectUserEnergyService(addFoodSummaryView, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectMealHelperUtil(addFoodSummaryView, DoubleCheck.lazy(this.mealHelperUtilProvider));
        injectFoodService(addFoodSummaryView, DoubleCheck.lazy(this.foodServiceProvider));
        injectImageService(addFoodSummaryView, DoubleCheck.lazy(this.imageServiceProvider));
        injectDiaryService(addFoodSummaryView, DoubleCheck.lazy(this.diaryServiceProvider));
        injectActionTrackingService(addFoodSummaryView, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectLocalizedStringsUtil(addFoodSummaryView, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectConfigService(addFoodSummaryView, DoubleCheck.lazy(this.configServiceProvider));
        injectMultiAddFoodHelperLazy(addFoodSummaryView, DoubleCheck.lazy(this.multiAddFoodHelperLazyProvider));
        injectSearchService(addFoodSummaryView, DoubleCheck.lazy(this.searchServiceProvider));
        injectAnalyticsServiceLazy(addFoodSummaryView, DoubleCheck.lazy(this.analyticsServiceLazyProvider));
        injectLocalSettingsService(addFoodSummaryView, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectCountryService(addFoodSummaryView, DoubleCheck.lazy(this.countryServiceProvider));
        injectFoodFeedbackAnalyticsHelper(addFoodSummaryView, DoubleCheck.lazy(this.foodFeedbackAnalyticsHelperProvider));
    }

    public static void injectUserEnergyService(AddFoodSummaryView addFoodSummaryView, Lazy<UserEnergyService> lazy) {
        addFoodSummaryView.userEnergyService = lazy;
    }

    public static void injectMealHelperUtil(AddFoodSummaryView addFoodSummaryView, Lazy<MealUtil> lazy) {
        addFoodSummaryView.mealHelperUtil = lazy;
    }

    public static void injectFoodService(AddFoodSummaryView addFoodSummaryView, Lazy<FoodService> lazy) {
        addFoodSummaryView.foodService = lazy;
    }

    public static void injectImageService(AddFoodSummaryView addFoodSummaryView, Lazy<ImageService> lazy) {
        addFoodSummaryView.imageService = lazy;
    }

    public static void injectDiaryService(AddFoodSummaryView addFoodSummaryView, Lazy<DiaryService> lazy) {
        addFoodSummaryView.diaryService = lazy;
    }

    public static void injectActionTrackingService(AddFoodSummaryView addFoodSummaryView, Lazy<ActionTrackingService> lazy) {
        addFoodSummaryView.actionTrackingService = lazy;
    }

    public static void injectLocalizedStringsUtil(AddFoodSummaryView addFoodSummaryView, Lazy<LocalizedStringsUtil> lazy) {
        addFoodSummaryView.localizedStringsUtil = lazy;
    }

    public static void injectConfigService(AddFoodSummaryView addFoodSummaryView, Lazy<ConfigService> lazy) {
        addFoodSummaryView.configService = lazy;
    }

    public static void injectMultiAddFoodHelperLazy(AddFoodSummaryView addFoodSummaryView, Lazy<MultiAddFoodHelper> lazy) {
        addFoodSummaryView.multiAddFoodHelperLazy = lazy;
    }

    public static void injectSearchService(AddFoodSummaryView addFoodSummaryView, Lazy<SearchService> lazy) {
        addFoodSummaryView.searchService = lazy;
    }

    public static void injectAnalyticsServiceLazy(AddFoodSummaryView addFoodSummaryView, Lazy<AnalyticsService> lazy) {
        addFoodSummaryView.analyticsServiceLazy = lazy;
    }

    public static void injectLocalSettingsService(AddFoodSummaryView addFoodSummaryView, Lazy<LocalSettingsService> lazy) {
        addFoodSummaryView.localSettingsService = lazy;
    }

    public static void injectCountryService(AddFoodSummaryView addFoodSummaryView, Lazy<CountryService> lazy) {
        addFoodSummaryView.countryService = lazy;
    }

    public static void injectFoodFeedbackAnalyticsHelper(AddFoodSummaryView addFoodSummaryView, Lazy<FoodFeedbackAnalyticsHelper> lazy) {
        addFoodSummaryView.foodFeedbackAnalyticsHelper = lazy;
    }
}
