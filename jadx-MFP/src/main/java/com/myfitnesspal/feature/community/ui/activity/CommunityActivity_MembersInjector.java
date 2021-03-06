package com.myfitnesspal.feature.community.ui.activity;

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
import com.myfitnesspal.shared.ui.activity.MfpActivity_MembersInjector;
import com.uacf.sync.engine.UacfScheduler;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CommunityActivity_MembersInjector implements MembersInjector<CommunityActivity> {
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

    public CommunityActivity_MembersInjector(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28) {
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

    public static MembersInjector<CommunityActivity> create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28) {
        CommunityActivity_MembersInjector communityActivity_MembersInjector = new CommunityActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28);
        return communityActivity_MembersInjector;
    }

    public void injectMembers(CommunityActivity communityActivity) {
        MfpActivity_MembersInjector.injectAdsSettings(communityActivity, DoubleCheck.lazy(this.adsSettingsProvider));
        MfpActivity_MembersInjector.injectImm(communityActivity, DoubleCheck.lazy(this.immProvider));
        MfpActivity_MembersInjector.injectStartupManager(communityActivity, (StartupManager) this.startupManagerProvider.get());
        MfpActivity_MembersInjector.injectRecipeService(communityActivity, DoubleCheck.lazy(this.recipeServiceProvider));
        MfpActivity_MembersInjector.injectActionTrackingService(communityActivity, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(communityActivity, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpActivity_MembersInjector.injectPremiumService(communityActivity, DoubleCheck.lazy(this.premiumServiceProvider));
        MfpActivity_MembersInjector.injectSyncService(communityActivity, DoubleCheck.lazy(this.syncServiceProvider));
        MfpActivity_MembersInjector.injectStepService(communityActivity, DoubleCheck.lazy(this.stepServiceProvider));
        MfpActivity_MembersInjector.injectSyncScheduler(communityActivity, DoubleCheck.lazy(this.syncSchedulerProvider));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(communityActivity, DoubleCheck.lazy(this.analyticsSyncSchedulerProvider));
        MfpActivity_MembersInjector.injectAppIndexerBot(communityActivity, DoubleCheck.lazy(this.appIndexerBotProvider));
        MfpActivity_MembersInjector.injectLocalSettingsService(communityActivity, DoubleCheck.lazy(this.localSettingsServiceProvider));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(communityActivity, DoubleCheck.lazy(this.adsAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(communityActivity, DoubleCheck.lazy(this.mfpAnalyticsServiceProvider));
        MfpActivity_MembersInjector.injectGoogleFitClient(communityActivity, DoubleCheck.lazy(this.googleFitClientProvider));
        MfpActivity_MembersInjector.injectSamsungConnection(communityActivity, DoubleCheck.lazy(this.samsungConnectionProvider));
        MfpActivity_MembersInjector.injectDeepLinkManager(communityActivity, DoubleCheck.lazy(this.deepLinkManagerProvider));
        MfpActivity_MembersInjector.injectConfigService(communityActivity, DoubleCheck.lazy(this.configServiceProvider));
        MfpActivity_MembersInjector.injectAdUnitService(communityActivity, DoubleCheck.lazy(this.adUnitServiceProvider));
        MfpActivity_MembersInjector.injectApiUrlProvider(communityActivity, DoubleCheck.lazy(this.apiUrlProvider));
        MfpActivity_MembersInjector.injectGlide(communityActivity, (Glide) this.glideProvider.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(communityActivity, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(communityActivity, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectDbConnectionManager(communityActivity, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(communityActivity, DoubleCheck.lazy(this.advancedDebuggingUtilProvider));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(communityActivity, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
        MfpActivity_MembersInjector.injectLocationService(communityActivity, DoubleCheck.lazy(this.locationServiceProvider));
    }
}
