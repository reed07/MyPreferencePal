package com.myfitnesspal.shared.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
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
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MfpActivity_MembersInjector implements MembersInjector<MfpActivity> {
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
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<InputMethodManager> immProvider;
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

    public MfpActivity_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28) {
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
    }

    public static MembersInjector<MfpActivity> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28) {
        MfpActivity_MembersInjector mfpActivity_MembersInjector = new MfpActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28);
        return mfpActivity_MembersInjector;
    }

    public void injectMembers(MfpActivity mfpActivity) {
        injectAdsSettings(mfpActivity, DoubleCheck.lazy(this.adsSettingsProvider));
        injectImm(mfpActivity, DoubleCheck.lazy(this.immProvider));
        injectStartupManager(mfpActivity, (StartupManager) this.startupManagerProvider.get());
        injectRecipeService(mfpActivity, DoubleCheck.lazy(this.recipeServiceProvider));
        injectActionTrackingService(mfpActivity, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectBackgroundServiceHelper(mfpActivity, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        injectPremiumService(mfpActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        injectSyncService(mfpActivity, DoubleCheck.lazy(this.syncServiceProvider));
        injectStepService(mfpActivity, DoubleCheck.lazy(this.stepServiceProvider));
        injectSyncScheduler(mfpActivity, DoubleCheck.lazy(this.syncSchedulerProvider));
        injectAnalyticsSyncScheduler(mfpActivity, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        injectAppIndexerBot(mfpActivity, DoubleCheck.lazy(this.appIndexerBotProvider));
        injectLocalSettingsService(mfpActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectAdsAnalyticsHelper(mfpActivity, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        injectMfpAnalyticsService(mfpActivity, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        injectGoogleFitClient(mfpActivity, DoubleCheck.lazy(this.googleFitClientProvider));
        injectSamsungConnection(mfpActivity, DoubleCheck.lazy(this.samsungConnectionProvider));
        injectDeepLinkManager(mfpActivity, DoubleCheck.lazy(this.deepLinkManagerProvider));
        injectConfigService(mfpActivity, DoubleCheck.lazy(this.configServiceProvider));
        injectAdUnitService(mfpActivity, DoubleCheck.lazy(this.adUnitServiceProvider));
        injectApiUrlProvider(mfpActivity, DoubleCheck.lazy(this.apiUrlProvider));
        injectGlide(mfpActivity, (Glide) this.glideProvider.get());
        injectGlobalSettingsService(mfpActivity, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        injectRecipesAnalyticsHelper(mfpActivity, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        injectDbConnectionManager(mfpActivity, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        injectAdvancedDebuggingUtil(mfpActivity, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        injectPermissionAnalyticsHelper(mfpActivity, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        injectLocationService(mfpActivity, DoubleCheck.lazy(this.locationServiceProvider));
    }

    public static void injectAdsSettings(MfpActivity mfpActivity, Lazy<AdsSettings> lazy) {
        mfpActivity.adsSettings = lazy;
    }

    public static void injectImm(MfpActivity mfpActivity, Lazy<InputMethodManager> lazy) {
        mfpActivity.imm = lazy;
    }

    public static void injectStartupManager(MfpActivity mfpActivity, StartupManager startupManager) {
        mfpActivity.startupManager = startupManager;
    }

    public static void injectRecipeService(MfpActivity mfpActivity, Lazy<RecipeService> lazy) {
        mfpActivity.recipeService = lazy;
    }

    public static void injectActionTrackingService(MfpActivity mfpActivity, Lazy<ActionTrackingService> lazy) {
        mfpActivity.actionTrackingService = lazy;
    }

    public static void injectBackgroundServiceHelper(MfpActivity mfpActivity, Lazy<BackgroundJobHelper> lazy) {
        mfpActivity.backgroundServiceHelper = lazy;
    }

    public static void injectPremiumService(MfpActivity mfpActivity, Lazy<PremiumService> lazy) {
        mfpActivity.premiumService = lazy;
    }

    public static void injectSyncService(MfpActivity mfpActivity, Lazy<SyncService> lazy) {
        mfpActivity.syncService = lazy;
    }

    public static void injectStepService(MfpActivity mfpActivity, Lazy<StepService> lazy) {
        mfpActivity.stepService = lazy;
    }

    public static void injectSyncScheduler(MfpActivity mfpActivity, Lazy<UacfScheduler<SyncType>> lazy) {
        mfpActivity.syncScheduler = lazy;
    }

    public static void injectAnalyticsSyncScheduler(MfpActivity mfpActivity, Lazy<UacfScheduler<AnalyticsSyncMode>> lazy) {
        mfpActivity.analyticsSyncScheduler = lazy;
    }

    public static void injectAppIndexerBot(MfpActivity mfpActivity, Lazy<AppIndexerBot> lazy) {
        mfpActivity.appIndexerBot = lazy;
    }

    public static void injectLocalSettingsService(MfpActivity mfpActivity, Lazy<LocalSettingsService> lazy) {
        mfpActivity.localSettingsService = lazy;
    }

    public static void injectAdsAnalyticsHelper(MfpActivity mfpActivity, Lazy<AdsAnalyticsHelper> lazy) {
        mfpActivity.adsAnalyticsHelper = lazy;
    }

    public static void injectMfpAnalyticsService(MfpActivity mfpActivity, Lazy<MfpAnalyticsService> lazy) {
        mfpActivity.mfpAnalyticsService = lazy;
    }

    public static void injectGoogleFitClient(MfpActivity mfpActivity, Lazy<GoogleFitClient> lazy) {
        mfpActivity.googleFitClient = lazy;
    }

    public static void injectSamsungConnection(MfpActivity mfpActivity, Lazy<SHealthConnection> lazy) {
        mfpActivity.samsungConnection = lazy;
    }

    public static void injectDeepLinkManager(MfpActivity mfpActivity, Lazy<DeepLinkManager> lazy) {
        mfpActivity.deepLinkManager = lazy;
    }

    public static void injectConfigService(MfpActivity mfpActivity, Lazy<ConfigService> lazy) {
        mfpActivity.configService = lazy;
    }

    public static void injectAdUnitService(MfpActivity mfpActivity, Lazy<AdUnitService> lazy) {
        mfpActivity.adUnitService = lazy;
    }

    public static void injectApiUrlProvider(MfpActivity mfpActivity, Lazy<ApiUrlProvider> lazy) {
        mfpActivity.apiUrlProvider = lazy;
    }

    public static void injectGlide(MfpActivity mfpActivity, Glide glide) {
        mfpActivity.glide = glide;
    }

    public static void injectGlobalSettingsService(MfpActivity mfpActivity, Lazy<GlobalSettingsService> lazy) {
        mfpActivity.globalSettingsService = lazy;
    }

    public static void injectRecipesAnalyticsHelper(MfpActivity mfpActivity, Lazy<RecipesAnalyticsHelper> lazy) {
        mfpActivity.recipesAnalyticsHelper = lazy;
    }

    public static void injectDbConnectionManager(MfpActivity mfpActivity, DbConnectionManager dbConnectionManager) {
        mfpActivity.dbConnectionManager = dbConnectionManager;
    }

    public static void injectAdvancedDebuggingUtil(MfpActivity mfpActivity, Lazy<AdvancedDebuggingUtil> lazy) {
        mfpActivity.advancedDebuggingUtil = lazy;
    }

    public static void injectPermissionAnalyticsHelper(MfpActivity mfpActivity, Lazy<PermissionAnalyticsHelper> lazy) {
        mfpActivity.permissionAnalyticsHelper = lazy;
    }

    public static void injectLocationService(MfpActivity mfpActivity, Lazy<LocationService> lazy) {
        mfpActivity.locationService = lazy;
    }
}
