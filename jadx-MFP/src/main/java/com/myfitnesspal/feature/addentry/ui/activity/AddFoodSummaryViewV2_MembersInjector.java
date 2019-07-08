package com.myfitnesspal.feature.addentry.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
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

public final class AddFoodSummaryViewV2_MembersInjector implements MembersInjector<AddFoodSummaryViewV2> {
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
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<FoodFeedbackAnalyticsHelper> foodFeedbackAnalyticsHelperProvider;
    private final Provider<FoodMapper> foodMapperProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<InputMethodManager> immProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;
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

    public AddFoodSummaryViewV2_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<FoodMapper> provider29, Provider<UserEnergyService> provider30, Provider<FoodService> provider31, Provider<DiaryService> provider32, Provider<LocalizedStringsUtil> provider33, Provider<CountryService> provider34, Provider<MultiAddFoodHelper> provider35, Provider<FoodFeedbackAnalyticsHelper> provider36) {
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
        this.foodMapperProvider = provider29;
        this.userEnergyServiceProvider = provider30;
        this.foodServiceProvider = provider31;
        this.diaryServiceProvider = provider32;
        this.localizedStringsUtilProvider = provider33;
        this.countryServiceProvider = provider34;
        this.multiAddFoodHelperProvider = provider35;
        this.foodFeedbackAnalyticsHelperProvider = provider36;
    }

    public static MembersInjector<AddFoodSummaryViewV2> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<FoodMapper> provider29, Provider<UserEnergyService> provider30, Provider<FoodService> provider31, Provider<DiaryService> provider32, Provider<LocalizedStringsUtil> provider33, Provider<CountryService> provider34, Provider<MultiAddFoodHelper> provider35, Provider<FoodFeedbackAnalyticsHelper> provider36) {
        AddFoodSummaryViewV2_MembersInjector addFoodSummaryViewV2_MembersInjector = new AddFoodSummaryViewV2_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36);
        return addFoodSummaryViewV2_MembersInjector;
    }

    public void injectMembers(AddFoodSummaryViewV2 addFoodSummaryViewV2) {
        MfpActivity_MembersInjector.injectAdsSettings(addFoodSummaryViewV2, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(addFoodSummaryViewV2, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(addFoodSummaryViewV2, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(addFoodSummaryViewV2, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(addFoodSummaryViewV2, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(addFoodSummaryViewV2, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(addFoodSummaryViewV2, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(addFoodSummaryViewV2, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(addFoodSummaryViewV2, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(addFoodSummaryViewV2, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(addFoodSummaryViewV2, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(addFoodSummaryViewV2, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(addFoodSummaryViewV2, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(addFoodSummaryViewV2, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(addFoodSummaryViewV2, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(addFoodSummaryViewV2, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(addFoodSummaryViewV2, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(addFoodSummaryViewV2, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(addFoodSummaryViewV2, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(addFoodSummaryViewV2, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(addFoodSummaryViewV2, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(addFoodSummaryViewV2, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(addFoodSummaryViewV2, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(addFoodSummaryViewV2, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(addFoodSummaryViewV2, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(addFoodSummaryViewV2, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(addFoodSummaryViewV2, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(addFoodSummaryViewV2, DoubleCheck.lazy(this.locationServiceProvider));
        injectFoodMapper(addFoodSummaryViewV2, (FoodMapper) this.foodMapperProvider.get());
        injectUserEnergyService(addFoodSummaryViewV2, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectFoodService(addFoodSummaryViewV2, DoubleCheck.lazy(this.foodServiceProvider));
        injectDiaryService(addFoodSummaryViewV2, DoubleCheck.lazy(this.diaryServiceProvider));
        injectActionTrackingService(addFoodSummaryViewV2, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectLocalizedStringsUtil(addFoodSummaryViewV2, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectConfigService(addFoodSummaryViewV2, DoubleCheck.lazy(this.configServiceProvider));
        injectCountryService(addFoodSummaryViewV2, DoubleCheck.lazy(this.countryServiceProvider));
        injectMultiAddFoodHelper(addFoodSummaryViewV2, DoubleCheck.lazy(this.multiAddFoodHelperProvider));
        injectAppIndexerBot(addFoodSummaryViewV2, DoubleCheck.lazy(this.appIndexerBotProvider));
        injectLocalSettingsService(addFoodSummaryViewV2, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectFoodFeedbackAnalyticsHelper(addFoodSummaryViewV2, DoubleCheck.lazy(this.foodFeedbackAnalyticsHelperProvider));
    }

    public static void injectFoodMapper(AddFoodSummaryViewV2 addFoodSummaryViewV2, FoodMapper foodMapper) {
        addFoodSummaryViewV2.foodMapper = foodMapper;
    }

    public static void injectUserEnergyService(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<UserEnergyService> lazy) {
        addFoodSummaryViewV2.userEnergyService = lazy;
    }

    public static void injectFoodService(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<FoodService> lazy) {
        addFoodSummaryViewV2.foodService = lazy;
    }

    public static void injectDiaryService(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<DiaryService> lazy) {
        addFoodSummaryViewV2.diaryService = lazy;
    }

    public static void injectActionTrackingService(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<ActionTrackingService> lazy) {
        addFoodSummaryViewV2.actionTrackingService = lazy;
    }

    public static void injectLocalizedStringsUtil(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<LocalizedStringsUtil> lazy) {
        addFoodSummaryViewV2.localizedStringsUtil = lazy;
    }

    public static void injectConfigService(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<ConfigService> lazy) {
        addFoodSummaryViewV2.configService = lazy;
    }

    public static void injectCountryService(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<CountryService> lazy) {
        addFoodSummaryViewV2.countryService = lazy;
    }

    public static void injectMultiAddFoodHelper(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<MultiAddFoodHelper> lazy) {
        addFoodSummaryViewV2.multiAddFoodHelper = lazy;
    }

    public static void injectAppIndexerBot(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<AppIndexerBot> lazy) {
        addFoodSummaryViewV2.appIndexerBot = lazy;
    }

    public static void injectLocalSettingsService(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<LocalSettingsService> lazy) {
        addFoodSummaryViewV2.localSettingsService = lazy;
    }

    public static void injectFoodFeedbackAnalyticsHelper(AddFoodSummaryViewV2 addFoodSummaryViewV2, Lazy<FoodFeedbackAnalyticsHelper> lazy) {
        addFoodSummaryViewV2.foodFeedbackAnalyticsHelper = lazy;
    }
}
