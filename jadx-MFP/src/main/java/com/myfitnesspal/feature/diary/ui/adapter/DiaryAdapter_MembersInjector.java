package com.myfitnesspal.feature.diary.ui.adapter;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class DiaryAdapter_MembersInjector implements MembersInjector<DiaryAdapter> {
    private final Provider<AdUnitService> adUnitServiceProvider;
    private final Provider<AdsAnalyticsHelper> adsAnalyticsProvider;
    private final Provider<AdsSettings> adsSettingsProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<Bus> busProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DiaryAnalyticsHelper> diaryAnalyticsHelperProvider;
    private final Provider<ExerciseStringService> exerciseStringServiceProvider;
    private final Provider<SharedPreferences> fitStepsSharedPreferencesProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public DiaryAdapter_MembersInjector(Provider<UserEnergyService> provider, Provider<ExerciseStringService> provider2, Provider<LocalizedStringsUtil> provider3, Provider<Bus> provider4, Provider<FoodService> provider5, Provider<AppGalleryService> provider6, Provider<StepService> provider7, Provider<AdsSettings> provider8, Provider<AdsAnalyticsHelper> provider9, Provider<NavigationHelper> provider10, Provider<ConfigService> provider11, Provider<LocalSettingsService> provider12, Provider<Session> provider13, Provider<PremiumService> provider14, Provider<AnalyticsService> provider15, Provider<DiaryAnalyticsHelper> provider16, Provider<SharedPreferences> provider17, Provider<LocationService> provider18, Provider<AdUnitService> provider19) {
        this.userEnergyServiceProvider = provider;
        this.exerciseStringServiceProvider = provider2;
        this.localizedStringsUtilProvider = provider3;
        this.busProvider = provider4;
        this.foodServiceProvider = provider5;
        this.appGalleryServiceProvider = provider6;
        this.stepServiceProvider = provider7;
        this.adsSettingsProvider = provider8;
        this.adsAnalyticsProvider = provider9;
        this.navigationHelperProvider = provider10;
        this.configServiceProvider = provider11;
        this.localSettingsServiceProvider = provider12;
        this.sessionProvider = provider13;
        this.premiumServiceProvider = provider14;
        this.analyticsServiceProvider = provider15;
        this.diaryAnalyticsHelperProvider = provider16;
        this.fitStepsSharedPreferencesProvider = provider17;
        this.locationServiceProvider = provider18;
        this.adUnitServiceProvider = provider19;
    }

    public static MembersInjector<DiaryAdapter> create(Provider<UserEnergyService> provider, Provider<ExerciseStringService> provider2, Provider<LocalizedStringsUtil> provider3, Provider<Bus> provider4, Provider<FoodService> provider5, Provider<AppGalleryService> provider6, Provider<StepService> provider7, Provider<AdsSettings> provider8, Provider<AdsAnalyticsHelper> provider9, Provider<NavigationHelper> provider10, Provider<ConfigService> provider11, Provider<LocalSettingsService> provider12, Provider<Session> provider13, Provider<PremiumService> provider14, Provider<AnalyticsService> provider15, Provider<DiaryAnalyticsHelper> provider16, Provider<SharedPreferences> provider17, Provider<LocationService> provider18, Provider<AdUnitService> provider19) {
        DiaryAdapter_MembersInjector diaryAdapter_MembersInjector = new DiaryAdapter_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19);
        return diaryAdapter_MembersInjector;
    }

    public void injectMembers(DiaryAdapter diaryAdapter) {
        injectUserEnergyService(diaryAdapter, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectExerciseStringService(diaryAdapter, DoubleCheck.lazy(this.exerciseStringServiceProvider));
        injectLocalizedStringsUtil(diaryAdapter, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectBus(diaryAdapter, DoubleCheck.lazy(this.busProvider));
        injectFoodService(diaryAdapter, DoubleCheck.lazy(this.foodServiceProvider));
        injectAppGalleryService(diaryAdapter, DoubleCheck.lazy(this.appGalleryServiceProvider));
        injectStepService(diaryAdapter, DoubleCheck.lazy(this.stepServiceProvider));
        injectAdsSettings(diaryAdapter, DoubleCheck.lazy(this.adsSettingsProvider));
        injectAdsAnalytics(diaryAdapter, DoubleCheck.lazy(this.adsAnalyticsProvider));
        injectNavigationHelper(diaryAdapter, DoubleCheck.lazy(this.navigationHelperProvider));
        injectConfigService(diaryAdapter, DoubleCheck.lazy(this.configServiceProvider));
        injectLocalSettingsService(diaryAdapter, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectSession(diaryAdapter, DoubleCheck.lazy(this.sessionProvider));
        injectPremiumService(diaryAdapter, DoubleCheck.lazy(this.premiumServiceProvider));
        injectAnalyticsService(diaryAdapter, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectDiaryAnalyticsHelper(diaryAdapter, DoubleCheck.lazy(this.diaryAnalyticsHelperProvider));
        injectFitStepsSharedPreferences(diaryAdapter, DoubleCheck.lazy(this.fitStepsSharedPreferencesProvider));
        injectLocationService(diaryAdapter, DoubleCheck.lazy(this.locationServiceProvider));
        injectAdUnitService(diaryAdapter, DoubleCheck.lazy(this.adUnitServiceProvider));
    }

    public static void injectUserEnergyService(DiaryAdapter diaryAdapter, Lazy<UserEnergyService> lazy) {
        diaryAdapter.userEnergyService = lazy;
    }

    public static void injectExerciseStringService(DiaryAdapter diaryAdapter, Lazy<ExerciseStringService> lazy) {
        diaryAdapter.exerciseStringService = lazy;
    }

    public static void injectLocalizedStringsUtil(DiaryAdapter diaryAdapter, Lazy<LocalizedStringsUtil> lazy) {
        diaryAdapter.localizedStringsUtil = lazy;
    }

    public static void injectBus(DiaryAdapter diaryAdapter, Lazy<Bus> lazy) {
        diaryAdapter.bus = lazy;
    }

    public static void injectFoodService(DiaryAdapter diaryAdapter, Lazy<FoodService> lazy) {
        diaryAdapter.foodService = lazy;
    }

    public static void injectAppGalleryService(DiaryAdapter diaryAdapter, Lazy<AppGalleryService> lazy) {
        diaryAdapter.appGalleryService = lazy;
    }

    public static void injectStepService(DiaryAdapter diaryAdapter, Lazy<StepService> lazy) {
        diaryAdapter.stepService = lazy;
    }

    public static void injectAdsSettings(DiaryAdapter diaryAdapter, Lazy<AdsSettings> lazy) {
        diaryAdapter.adsSettings = lazy;
    }

    public static void injectAdsAnalytics(DiaryAdapter diaryAdapter, Lazy<AdsAnalyticsHelper> lazy) {
        diaryAdapter.adsAnalytics = lazy;
    }

    public static void injectNavigationHelper(DiaryAdapter diaryAdapter, Lazy<NavigationHelper> lazy) {
        diaryAdapter.navigationHelper = lazy;
    }

    public static void injectConfigService(DiaryAdapter diaryAdapter, Lazy<ConfigService> lazy) {
        diaryAdapter.configService = lazy;
    }

    public static void injectLocalSettingsService(DiaryAdapter diaryAdapter, Lazy<LocalSettingsService> lazy) {
        diaryAdapter.localSettingsService = lazy;
    }

    public static void injectSession(DiaryAdapter diaryAdapter, Lazy<Session> lazy) {
        diaryAdapter.session = lazy;
    }

    public static void injectPremiumService(DiaryAdapter diaryAdapter, Lazy<PremiumService> lazy) {
        diaryAdapter.premiumService = lazy;
    }

    public static void injectAnalyticsService(DiaryAdapter diaryAdapter, Lazy<AnalyticsService> lazy) {
        diaryAdapter.analyticsService = lazy;
    }

    public static void injectDiaryAnalyticsHelper(DiaryAdapter diaryAdapter, Lazy<DiaryAnalyticsHelper> lazy) {
        diaryAdapter.diaryAnalyticsHelper = lazy;
    }

    public static void injectFitStepsSharedPreferences(DiaryAdapter diaryAdapter, Lazy<SharedPreferences> lazy) {
        diaryAdapter.fitStepsSharedPreferences = lazy;
    }

    public static void injectLocationService(DiaryAdapter diaryAdapter, Lazy<LocationService> lazy) {
        diaryAdapter.locationService = lazy;
    }

    public static void injectAdUnitService(DiaryAdapter diaryAdapter, Lazy<AdUnitService> lazy) {
        diaryAdapter.adUnitService = lazy;
    }
}
