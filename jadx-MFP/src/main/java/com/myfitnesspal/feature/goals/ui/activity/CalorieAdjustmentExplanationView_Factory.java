package com.myfitnesspal.feature.goals.ui.activity;

import android.view.inputmethod.InputMethodManager;
import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
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
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.sync.engine.UacfScheduler;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CalorieAdjustmentExplanationView_Factory implements Factory<CalorieAdjustmentExplanationView> {
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
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<PermissionAnalyticsHelper> permissionAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<SHealthConnection> samsungConnectionProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StartupManager> startupManagerProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public CalorieAdjustmentExplanationView_Factory(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<UserEnergyService> provider29, Provider<NutrientGoalService> provider30, Provider<LocalizedStringsUtil> provider31, Provider<Session> provider32) {
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
        this.nutrientGoalServiceProvider = provider30;
        this.localizedStringsUtilProvider = provider31;
        this.sessionProvider = provider32;
    }

    public CalorieAdjustmentExplanationView get() {
        Provider<AdsSettings> provider = this.adsSettingsProvider;
        return provideInstance(provider, this.immProvider, this.startupManagerProvider, this.recipeServiceProvider, this.actionTrackingServiceProvider, this.backgroundServiceHelperProvider, this.premiumServiceProvider, this.syncServiceProvider, this.stepServiceProvider, this.syncSchedulerProvider, this.analyticsSyncSchedulerProvider, this.appIndexerBotProvider, this.localSettingsServiceProvider, this.adsAnalyticsHelperProvider, this.mfpAnalyticsServiceProvider, this.googleFitClientProvider, this.samsungConnectionProvider, this.deepLinkManagerProvider, this.configServiceProvider, this.adUnitServiceProvider, this.apiUrlProvider, this.glideProvider, this.globalSettingsServiceProvider, this.recipesAnalyticsHelperProvider, this.dbConnectionManagerProvider, this.advancedDebuggingUtilProvider, this.permissionAnalyticsHelperProvider, this.locationServiceProvider, this.userEnergyServiceProvider, this.nutrientGoalServiceProvider, this.localizedStringsUtilProvider, this.sessionProvider);
    }

    public static CalorieAdjustmentExplanationView provideInstance(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<UserEnergyService> provider29, Provider<NutrientGoalService> provider30, Provider<LocalizedStringsUtil> provider31, Provider<Session> provider32) {
        CalorieAdjustmentExplanationView calorieAdjustmentExplanationView = new CalorieAdjustmentExplanationView();
        MfpActivity_MembersInjector.injectAdsSettings(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider));
        MfpActivity_MembersInjector.injectImm(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider2));
        MfpActivity_MembersInjector.injectStartupManager(calorieAdjustmentExplanationView, (StartupManager) provider3.get());
        MfpActivity_MembersInjector.injectRecipeService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider4));
        MfpActivity_MembersInjector.injectActionTrackingService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider5));
        MfpActivity_MembersInjector.injectBackgroundServiceHelper(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider6));
        MfpActivity_MembersInjector.injectPremiumService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider7));
        MfpActivity_MembersInjector.injectSyncService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider8));
        MfpActivity_MembersInjector.injectStepService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider9));
        MfpActivity_MembersInjector.injectSyncScheduler(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider10));
        MfpActivity_MembersInjector.injectAnalyticsSyncScheduler(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider11));
        MfpActivity_MembersInjector.injectAppIndexerBot(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider12));
        MfpActivity_MembersInjector.injectLocalSettingsService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider13));
        MfpActivity_MembersInjector.injectAdsAnalyticsHelper(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider14));
        MfpActivity_MembersInjector.injectMfpAnalyticsService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider15));
        MfpActivity_MembersInjector.injectGoogleFitClient(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider16));
        MfpActivity_MembersInjector.injectSamsungConnection(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider17));
        MfpActivity_MembersInjector.injectDeepLinkManager(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider18));
        MfpActivity_MembersInjector.injectConfigService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider19));
        MfpActivity_MembersInjector.injectAdUnitService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider20));
        MfpActivity_MembersInjector.injectApiUrlProvider(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider21));
        MfpActivity_MembersInjector.injectGlide(calorieAdjustmentExplanationView, (Glide) provider22.get());
        MfpActivity_MembersInjector.injectGlobalSettingsService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider23));
        MfpActivity_MembersInjector.injectRecipesAnalyticsHelper(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider24));
        MfpActivity_MembersInjector.injectDbConnectionManager(calorieAdjustmentExplanationView, (DbConnectionManager) provider25.get());
        MfpActivity_MembersInjector.injectAdvancedDebuggingUtil(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider26));
        MfpActivity_MembersInjector.injectPermissionAnalyticsHelper(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider27));
        MfpActivity_MembersInjector.injectLocationService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider28));
        CalorieAdjustmentExplanationView_MembersInjector.injectUserEnergyService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider29));
        CalorieAdjustmentExplanationView_MembersInjector.injectNutrientGoalService(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider30));
        CalorieAdjustmentExplanationView_MembersInjector.injectLocalizedStringsUtil(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider31));
        CalorieAdjustmentExplanationView_MembersInjector.injectSession(calorieAdjustmentExplanationView, DoubleCheck.lazy(provider32));
        return calorieAdjustmentExplanationView;
    }

    public static CalorieAdjustmentExplanationView_Factory create(Provider<AdsSettings> provider, Provider<InputMethodManager> provider2, Provider<StartupManager> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5, Provider<BackgroundJobHelper> provider6, Provider<PremiumService> provider7, Provider<SyncService> provider8, Provider<StepService> provider9, Provider<UacfScheduler<SyncType>> provider10, Provider<UacfScheduler<AnalyticsSyncMode>> provider11, Provider<AppIndexerBot> provider12, Provider<LocalSettingsService> provider13, Provider<AdsAnalyticsHelper> provider14, Provider<MfpAnalyticsService> provider15, Provider<GoogleFitClient> provider16, Provider<SHealthConnection> provider17, Provider<DeepLinkManager> provider18, Provider<ConfigService> provider19, Provider<AdUnitService> provider20, Provider<ApiUrlProvider> provider21, Provider<Glide> provider22, Provider<GlobalSettingsService> provider23, Provider<RecipesAnalyticsHelper> provider24, Provider<DbConnectionManager> provider25, Provider<AdvancedDebuggingUtil> provider26, Provider<PermissionAnalyticsHelper> provider27, Provider<LocationService> provider28, Provider<UserEnergyService> provider29, Provider<NutrientGoalService> provider30, Provider<LocalizedStringsUtil> provider31, Provider<Session> provider32) {
        CalorieAdjustmentExplanationView_Factory calorieAdjustmentExplanationView_Factory = new CalorieAdjustmentExplanationView_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32);
        return calorieAdjustmentExplanationView_Factory;
    }

    public static CalorieAdjustmentExplanationView newCalorieAdjustmentExplanationView() {
        return new CalorieAdjustmentExplanationView();
    }
}
