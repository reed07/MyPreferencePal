package com.myfitnesspal.feature.progress.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.fileexport.service.FileExportAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.feature.progress.ui.chart.MeasurementLineChartRenderer;
import com.myfitnesspal.feature.progress.ui.chart.StepsBarChartRenderer;
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
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.activity.MfpActivity_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ProgressActivity_MembersInjector implements MembersInjector<ProgressActivity> {
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
    private final Provider<FileExportAnalyticsHelper> fileExportAnalyticsHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<InputMethodManager> immProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MeasurementLineChartRenderer> measurementLineChartRendererProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<PermissionAnalyticsHelper> permissionAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<ProgressAnalytics> progressAnalyticsProvider;
    private final Provider<ProgressService> progressServiceProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<SHealthConnection> samsungConnectionProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StartupManager> startupManagerProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<StepsBarChartRenderer> stepsBarChartRendererProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;
    private final Provider<UserWeightService> weightServiceProvider;

    public ProgressActivity_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<LocalizedStringsUtil> provider29, Provider<StepsBarChartRenderer> provider30, Provider<MeasurementLineChartRenderer> provider31, Provider<UserWeightService> provider32, Provider<MeasurementsService> provider33, Provider<ImageService> provider34, Provider<ProgressService> provider35, Provider<ProgressAnalytics> provider36, Provider<NavigationHelper> provider37, Provider<UserApplicationSettingsService> provider38, Provider<FileExportAnalyticsHelper> provider39, Provider<Session> provider40) {
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
        this.stepsBarChartRendererProvider = provider30;
        this.measurementLineChartRendererProvider = provider31;
        this.weightServiceProvider = provider32;
        this.measurementsServiceProvider = provider33;
        this.imageServiceProvider = provider34;
        this.progressServiceProvider = provider35;
        this.progressAnalyticsProvider = provider36;
        this.navigationHelperProvider = provider37;
        this.userApplicationSettingsServiceProvider = provider38;
        this.fileExportAnalyticsHelperProvider = provider39;
        this.sessionProvider = provider40;
    }

    public static MembersInjector<ProgressActivity> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<LocalizedStringsUtil> provider29, Provider<StepsBarChartRenderer> provider30, Provider<MeasurementLineChartRenderer> provider31, Provider<UserWeightService> provider32, Provider<MeasurementsService> provider33, Provider<ImageService> provider34, Provider<ProgressService> provider35, Provider<ProgressAnalytics> provider36, Provider<NavigationHelper> provider37, Provider<UserApplicationSettingsService> provider38, Provider<FileExportAnalyticsHelper> provider39, Provider<Session> provider40) {
        ProgressActivity_MembersInjector progressActivity_MembersInjector = new ProgressActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37, provider38, provider39, provider40);
        return progressActivity_MembersInjector;
    }

    public void injectMembers(ProgressActivity progressActivity) {
        MfpActivity_MembersInjector.injectAdsSettings(progressActivity, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(progressActivity, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(progressActivity, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(progressActivity, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(progressActivity, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(progressActivity, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(progressActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(progressActivity, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(progressActivity, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(progressActivity, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(progressActivity, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(progressActivity, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(progressActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(progressActivity, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(progressActivity, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(progressActivity, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(progressActivity, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(progressActivity, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(progressActivity, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(progressActivity, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(progressActivity, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(progressActivity, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(progressActivity, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(progressActivity, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(progressActivity, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(progressActivity, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(progressActivity, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(progressActivity, DoubleCheck.lazy(this.locationServiceProvider));
        injectLocalizedStringsUtil(progressActivity, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectStepsBarChartRenderer(progressActivity, DoubleCheck.lazy(this.stepsBarChartRendererProvider));
        injectMeasurementLineChartRenderer(progressActivity, DoubleCheck.lazy(this.measurementLineChartRendererProvider));
        injectWeightService(progressActivity, DoubleCheck.lazy(this.weightServiceProvider));
        injectMeasurementsService(progressActivity, DoubleCheck.lazy(this.measurementsServiceProvider));
        injectImageService(progressActivity, DoubleCheck.lazy(this.imageServiceProvider));
        injectProgressService(progressActivity, DoubleCheck.lazy(this.progressServiceProvider));
        injectProgressAnalytics(progressActivity, DoubleCheck.lazy(this.progressAnalyticsProvider));
        injectAdsSettings(progressActivity, DoubleCheck.lazy(this.adsSettingsProvider));
        injectAdsAnalyticsHelper(progressActivity, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        injectNavigationHelper(progressActivity, DoubleCheck.lazy(this.navigationHelperProvider));
        injectPremiumService(progressActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        injectLocalSettingsService(progressActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectConfigService(progressActivity, DoubleCheck.lazy(this.configServiceProvider));
        injectUserApplicationSettingsService(progressActivity, DoubleCheck.lazy(this.userApplicationSettingsServiceProvider));
        injectFileExportAnalyticsHelper(progressActivity, DoubleCheck.lazy(this.fileExportAnalyticsHelperProvider));
        injectSession(progressActivity, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectLocalizedStringsUtil(ProgressActivity progressActivity, Lazy<LocalizedStringsUtil> lazy) {
        progressActivity.localizedStringsUtil = lazy;
    }

    public static void injectStepsBarChartRenderer(ProgressActivity progressActivity, Lazy<StepsBarChartRenderer> lazy) {
        progressActivity.stepsBarChartRenderer = lazy;
    }

    public static void injectMeasurementLineChartRenderer(ProgressActivity progressActivity, Lazy<MeasurementLineChartRenderer> lazy) {
        progressActivity.measurementLineChartRenderer = lazy;
    }

    public static void injectWeightService(ProgressActivity progressActivity, Lazy<UserWeightService> lazy) {
        progressActivity.weightService = lazy;
    }

    public static void injectMeasurementsService(ProgressActivity progressActivity, Lazy<MeasurementsService> lazy) {
        progressActivity.measurementsService = lazy;
    }

    public static void injectImageService(ProgressActivity progressActivity, Lazy<ImageService> lazy) {
        progressActivity.imageService = lazy;
    }

    public static void injectProgressService(ProgressActivity progressActivity, Lazy<ProgressService> lazy) {
        progressActivity.progressService = lazy;
    }

    public static void injectProgressAnalytics(ProgressActivity progressActivity, Lazy<ProgressAnalytics> lazy) {
        progressActivity.progressAnalytics = lazy;
    }

    public static void injectAdsSettings(ProgressActivity progressActivity, Lazy<AdsSettings> lazy) {
        progressActivity.adsSettings = lazy;
    }

    public static void injectAdsAnalyticsHelper(ProgressActivity progressActivity, Lazy<AdsAnalyticsHelper> lazy) {
        progressActivity.adsAnalyticsHelper = lazy;
    }

    public static void injectNavigationHelper(ProgressActivity progressActivity, Lazy<NavigationHelper> lazy) {
        progressActivity.navigationHelper = lazy;
    }

    public static void injectPremiumService(ProgressActivity progressActivity, Lazy<PremiumService> lazy) {
        progressActivity.premiumService = lazy;
    }

    public static void injectLocalSettingsService(ProgressActivity progressActivity, Lazy<LocalSettingsService> lazy) {
        progressActivity.localSettingsService = lazy;
    }

    public static void injectConfigService(ProgressActivity progressActivity, Lazy<ConfigService> lazy) {
        progressActivity.configService = lazy;
    }

    public static void injectUserApplicationSettingsService(ProgressActivity progressActivity, Lazy<UserApplicationSettingsService> lazy) {
        progressActivity.userApplicationSettingsService = lazy;
    }

    public static void injectFileExportAnalyticsHelper(ProgressActivity progressActivity, Lazy<FileExportAnalyticsHelper> lazy) {
        progressActivity.fileExportAnalyticsHelper = lazy;
    }

    public static void injectSession(ProgressActivity progressActivity, Lazy<Session> lazy) {
        progressActivity.session = lazy;
    }
}
