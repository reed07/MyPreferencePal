package com.myfitnesspal.feature.addentry.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
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
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.ui.activity.MfpActivity_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class WaterEntryActivity_MembersInjector implements MembersInjector<WaterEntryActivity> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AdUnitService> adUnitServiceProvider;
    private final Provider<AdsAnalyticsHelper> adsAnalyticsAndAdsAnalyticsHelperProvider;
    private final Provider<AdsSettings> adsSettingsProvider;
    private final Provider<AdvancedDebuggingUtil> advancedDebuggingUtilProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<UacfScheduler<AnalyticsSyncMode>> analyticsSyncSchedulerProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<InputMethodManager> immProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<PermissionAnalyticsHelper> permissionAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<SHealthConnection> samsungConnectionProvider;
    private final Provider<StartupManager> startupManagerProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;
    private final Provider<WaterLoggingAnalyticsHelper> waterLoggingAnalyticsHelperProvider;

    public WaterEntryActivity_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<UserApplicationSettingsService> provider29, Provider<WaterLoggingAnalyticsHelper> provider30, Provider<NavigationHelper> provider31, Provider<Bus> provider32, Provider<AnalyticsService> provider33, Provider<CountryService> provider34, Provider<DiaryService> provider35) {
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
        this.adsAnalyticsAndAdsAnalyticsHelperProvider = provider14;
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
        this.userApplicationSettingsServiceProvider = provider29;
        this.waterLoggingAnalyticsHelperProvider = provider30;
        this.navigationHelperProvider = provider31;
        this.messageBusProvider = provider32;
        this.analyticsServiceProvider = provider33;
        this.countryServiceProvider = provider34;
        this.diaryServiceProvider = provider35;
    }

    public static MembersInjector<WaterEntryActivity> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<UserApplicationSettingsService> provider29, Provider<WaterLoggingAnalyticsHelper> provider30, Provider<NavigationHelper> provider31, Provider<Bus> provider32, Provider<AnalyticsService> provider33, Provider<CountryService> provider34, Provider<DiaryService> provider35) {
        WaterEntryActivity_MembersInjector waterEntryActivity_MembersInjector = new WaterEntryActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35);
        return waterEntryActivity_MembersInjector;
    }

    public void injectMembers(WaterEntryActivity waterEntryActivity) {
        MfpActivity_MembersInjector.injectAdsSettings(waterEntryActivity, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(waterEntryActivity, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(waterEntryActivity, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(waterEntryActivity, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(waterEntryActivity, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(waterEntryActivity, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(waterEntryActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(waterEntryActivity, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(waterEntryActivity, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(waterEntryActivity, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(waterEntryActivity, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(waterEntryActivity, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(waterEntryActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(waterEntryActivity, DoubleCheck.lazy(this.adsAnalyticsAndAdsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(waterEntryActivity, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(waterEntryActivity, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(waterEntryActivity, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(waterEntryActivity, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(waterEntryActivity, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(waterEntryActivity, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(waterEntryActivity, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(waterEntryActivity, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(waterEntryActivity, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(waterEntryActivity, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(waterEntryActivity, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(waterEntryActivity, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(waterEntryActivity, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(waterEntryActivity, DoubleCheck.lazy(this.locationServiceProvider));
        injectUserApplicationSettingsService(waterEntryActivity, DoubleCheck.lazy(this.userApplicationSettingsServiceProvider));
        injectWaterLoggingAnalyticsHelper(waterEntryActivity, DoubleCheck.lazy(this.waterLoggingAnalyticsHelperProvider));
        injectConfigService(waterEntryActivity, DoubleCheck.lazy(this.configServiceProvider));
        injectAdsSettings(waterEntryActivity, DoubleCheck.lazy(this.adsSettingsProvider));
        injectAdsAnalytics(waterEntryActivity, DoubleCheck.lazy(this.adsAnalyticsAndAdsAnalyticsHelperProvider));
        injectNavigationHelper(waterEntryActivity, DoubleCheck.lazy(this.navigationHelperProvider));
        injectMessageBus(waterEntryActivity, DoubleCheck.lazy(this.messageBusProvider));
        injectPremiumService(waterEntryActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        injectAnalyticsService(waterEntryActivity, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectAdsAnalyticsHelper(waterEntryActivity, DoubleCheck.lazy(this.adsAnalyticsAndAdsAnalyticsHelperProvider));
        injectCountryService(waterEntryActivity, DoubleCheck.lazy(this.countryServiceProvider));
        injectLocalSettingsService(waterEntryActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectDiaryService(waterEntryActivity, DoubleCheck.lazy(this.diaryServiceProvider));
        injectLocationService(waterEntryActivity, DoubleCheck.lazy(this.locationServiceProvider));
    }

    public static void injectUserApplicationSettingsService(WaterEntryActivity waterEntryActivity, Lazy<UserApplicationSettingsService> lazy) {
        waterEntryActivity.userApplicationSettingsService = lazy;
    }

    public static void injectWaterLoggingAnalyticsHelper(WaterEntryActivity waterEntryActivity, Lazy<WaterLoggingAnalyticsHelper> lazy) {
        waterEntryActivity.waterLoggingAnalyticsHelper = lazy;
    }

    public static void injectConfigService(WaterEntryActivity waterEntryActivity, Lazy<ConfigService> lazy) {
        waterEntryActivity.configService = lazy;
    }

    public static void injectAdsSettings(WaterEntryActivity waterEntryActivity, Lazy<AdsSettings> lazy) {
        waterEntryActivity.adsSettings = lazy;
    }

    public static void injectAdsAnalytics(WaterEntryActivity waterEntryActivity, Lazy<AdsAnalyticsHelper> lazy) {
        waterEntryActivity.adsAnalytics = lazy;
    }

    public static void injectNavigationHelper(WaterEntryActivity waterEntryActivity, Lazy<NavigationHelper> lazy) {
        waterEntryActivity.navigationHelper = lazy;
    }

    public static void injectMessageBus(WaterEntryActivity waterEntryActivity, Lazy<Bus> lazy) {
        waterEntryActivity.messageBus = lazy;
    }

    public static void injectPremiumService(WaterEntryActivity waterEntryActivity, Lazy<PremiumService> lazy) {
        waterEntryActivity.premiumService = lazy;
    }

    public static void injectAnalyticsService(WaterEntryActivity waterEntryActivity, Lazy<AnalyticsService> lazy) {
        waterEntryActivity.analyticsService = lazy;
    }

    public static void injectAdsAnalyticsHelper(WaterEntryActivity waterEntryActivity, Lazy<AdsAnalyticsHelper> lazy) {
        waterEntryActivity.adsAnalyticsHelper = lazy;
    }

    public static void injectCountryService(WaterEntryActivity waterEntryActivity, Lazy<CountryService> lazy) {
        waterEntryActivity.countryService = lazy;
    }

    public static void injectLocalSettingsService(WaterEntryActivity waterEntryActivity, Lazy<LocalSettingsService> lazy) {
        waterEntryActivity.localSettingsService = lazy;
    }

    public static void injectDiaryService(WaterEntryActivity waterEntryActivity, Lazy<DiaryService> lazy) {
        waterEntryActivity.diaryService = lazy;
    }

    public static void injectLocationService(WaterEntryActivity waterEntryActivity, Lazy<LocationService> lazy) {
        waterEntryActivity.locationService = lazy;
    }
}
