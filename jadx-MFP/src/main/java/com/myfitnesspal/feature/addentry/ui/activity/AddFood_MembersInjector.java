package com.myfitnesspal.feature.addentry.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
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
import com.myfitnesspal.shared.util.InputMethodManagerHelper;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AddFood_MembersInjector implements MembersInjector<AddFood> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AdUnitService> adUnitServiceProvider;
    private final Provider<AdsAnalyticsHelper> adsAnalyticsHelperProvider;
    private final Provider<AdsSettings> adsSettingsProvider;
    private final Provider<AdvancedDebuggingUtil> advancedDebuggingUtilProvider;
    private final Provider<UacfScheduler<AnalyticsSyncMode>> analyticsSyncSchedulerProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<MfpFoodMapper> foodObjectToFoodV2Provider;
    private final Provider<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelperProvider;
    private final Provider<FoodMapper> foodToFoodObjectProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<InputMethodManager> immProvider;
    private final Provider<InputMethodManagerHelper> inputMethodManagerHelperProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocationService> locationServiceProvider;
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

    public AddFood_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<UserEnergyService> provider29, Provider<CountryService> provider30, Provider<InputMethodManagerHelper> provider31, Provider<FoodMapper> provider32, Provider<MfpFoodMapper> provider33, Provider<DiaryService> provider34, Provider<AppSettings> provider35, Provider<FoodSearchAnalyticsHelper> provider36) {
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
        this.countryServiceProvider = provider30;
        this.inputMethodManagerHelperProvider = provider31;
        this.foodToFoodObjectProvider = provider32;
        this.foodObjectToFoodV2Provider = provider33;
        this.diaryServiceProvider = provider34;
        this.appSettingsProvider = provider35;
        this.foodSearchAnalyticsHelperProvider = provider36;
    }

    public static MembersInjector<AddFood> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<UserEnergyService> provider29, Provider<CountryService> provider30, Provider<InputMethodManagerHelper> provider31, Provider<FoodMapper> provider32, Provider<MfpFoodMapper> provider33, Provider<DiaryService> provider34, Provider<AppSettings> provider35, Provider<FoodSearchAnalyticsHelper> provider36) {
        AddFood_MembersInjector addFood_MembersInjector = new AddFood_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36);
        return addFood_MembersInjector;
    }

    public void injectMembers(AddFood addFood) {
        MfpActivity_MembersInjector.injectAdsSettings(addFood, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(addFood, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(addFood, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(addFood, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(addFood, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(addFood, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(addFood, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(addFood, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(addFood, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(addFood, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(addFood, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(addFood, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(addFood, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(addFood, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(addFood, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(addFood, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(addFood, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(addFood, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(addFood, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(addFood, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(addFood, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(addFood, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(addFood, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(addFood, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(addFood, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(addFood, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(addFood, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(addFood, DoubleCheck.lazy(this.locationServiceProvider));
        injectUserEnergyService(addFood, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectActionTrackingService(addFood, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectCountryService(addFood, DoubleCheck.lazy(this.countryServiceProvider));
        injectSyncScheduler(addFood, DoubleCheck.lazy(this.syncSchedulerProvider));
        injectInputMethodManagerHelper(addFood, DoubleCheck.lazy(this.inputMethodManagerHelperProvider));
        injectFoodToFoodObject(addFood, (FoodMapper) this.foodToFoodObjectProvider.get());
        injectFoodObjectToFoodV2(addFood, (MfpFoodMapper) this.foodObjectToFoodV2Provider.get());
        injectDiaryService(addFood, DoubleCheck.lazy(this.diaryServiceProvider));
        injectAppSettings(addFood, DoubleCheck.lazy(this.appSettingsProvider));
        injectDbConnectionManager(addFood, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectFoodSearchAnalyticsHelper(addFood, DoubleCheck.lazy(this.foodSearchAnalyticsHelperProvider));
    }

    public static void injectUserEnergyService(AddFood addFood, Lazy<UserEnergyService> lazy) {
        addFood.userEnergyService = lazy;
    }

    public static void injectActionTrackingService(AddFood addFood, Lazy<ActionTrackingService> lazy) {
        addFood.actionTrackingService = lazy;
    }

    public static void injectCountryService(AddFood addFood, Lazy<CountryService> lazy) {
        addFood.countryService = lazy;
    }

    public static void injectSyncScheduler(AddFood addFood, Lazy<UacfScheduler<SyncType>> lazy) {
        addFood.syncScheduler = lazy;
    }

    public static void injectInputMethodManagerHelper(AddFood addFood, Lazy<InputMethodManagerHelper> lazy) {
        addFood.inputMethodManagerHelper = lazy;
    }

    public static void injectFoodToFoodObject(AddFood addFood, FoodMapper foodMapper) {
        addFood.foodToFoodObject = foodMapper;
    }

    public static void injectFoodObjectToFoodV2(AddFood addFood, MfpFoodMapper mfpFoodMapper) {
        addFood.foodObjectToFoodV2 = mfpFoodMapper;
    }

    public static void injectDiaryService(AddFood addFood, Lazy<DiaryService> lazy) {
        addFood.diaryService = lazy;
    }

    public static void injectAppSettings(AddFood addFood, Lazy<AppSettings> lazy) {
        addFood.appSettings = lazy;
    }

    public static void injectDbConnectionManager(AddFood addFood, Lazy<DbConnectionManager> lazy) {
        addFood.dbConnectionManager = lazy;
    }

    public static void injectFoodSearchAnalyticsHelper(AddFood addFood, Lazy<FoodSearchAnalyticsHelper> lazy) {
        addFood.foodSearchAnalyticsHelper = lazy;
    }
}
