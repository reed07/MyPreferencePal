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
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class DiaryLandscapeAdapter_MembersInjector implements MembersInjector<DiaryLandscapeAdapter> {
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

    public DiaryLandscapeAdapter_MembersInjector(Provider<UserEnergyService> provider, Provider<ExerciseStringService> provider2, Provider<LocalizedStringsUtil> provider3, Provider<Bus> provider4, Provider<FoodService> provider5, Provider<AppGalleryService> provider6, Provider<StepService> provider7, Provider<AdsSettings> provider8, Provider<AdsAnalyticsHelper> provider9, Provider<NavigationHelper> provider10, Provider<ConfigService> provider11, Provider<LocalSettingsService> provider12, Provider<Session> provider13, Provider<PremiumService> provider14, Provider<AnalyticsService> provider15, Provider<DiaryAnalyticsHelper> provider16, Provider<SharedPreferences> provider17, Provider<LocationService> provider18, Provider<AdUnitService> provider19) {
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

    public static MembersInjector<DiaryLandscapeAdapter> create(Provider<UserEnergyService> provider, Provider<ExerciseStringService> provider2, Provider<LocalizedStringsUtil> provider3, Provider<Bus> provider4, Provider<FoodService> provider5, Provider<AppGalleryService> provider6, Provider<StepService> provider7, Provider<AdsSettings> provider8, Provider<AdsAnalyticsHelper> provider9, Provider<NavigationHelper> provider10, Provider<ConfigService> provider11, Provider<LocalSettingsService> provider12, Provider<Session> provider13, Provider<PremiumService> provider14, Provider<AnalyticsService> provider15, Provider<DiaryAnalyticsHelper> provider16, Provider<SharedPreferences> provider17, Provider<LocationService> provider18, Provider<AdUnitService> provider19) {
        DiaryLandscapeAdapter_MembersInjector diaryLandscapeAdapter_MembersInjector = new DiaryLandscapeAdapter_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19);
        return diaryLandscapeAdapter_MembersInjector;
    }

    public void injectMembers(DiaryLandscapeAdapter diaryLandscapeAdapter) {
        DiaryAdapter_MembersInjector.injectUserEnergyService(diaryLandscapeAdapter, DoubleCheck.lazy(this.userEnergyServiceProvider));
        DiaryAdapter_MembersInjector.injectExerciseStringService(diaryLandscapeAdapter, DoubleCheck.lazy(this.exerciseStringServiceProvider));
        DiaryAdapter_MembersInjector.injectLocalizedStringsUtil(diaryLandscapeAdapter, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        DiaryAdapter_MembersInjector.injectBus(diaryLandscapeAdapter, DoubleCheck.lazy(this.busProvider));
        DiaryAdapter_MembersInjector.injectFoodService(diaryLandscapeAdapter, DoubleCheck.lazy(this.foodServiceProvider));
        DiaryAdapter_MembersInjector.injectAppGalleryService(diaryLandscapeAdapter, DoubleCheck.lazy(this.appGalleryServiceProvider));
        DiaryAdapter_MembersInjector.injectStepService(diaryLandscapeAdapter, DoubleCheck.lazy(this.stepServiceProvider));
        DiaryAdapter_MembersInjector.injectAdsSettings(diaryLandscapeAdapter, DoubleCheck.lazy(this.adsSettingsProvider));
        DiaryAdapter_MembersInjector.injectAdsAnalytics(diaryLandscapeAdapter, DoubleCheck.lazy(this.adsAnalyticsProvider));
        DiaryAdapter_MembersInjector.injectNavigationHelper(diaryLandscapeAdapter, DoubleCheck.lazy(this.navigationHelperProvider));
        DiaryAdapter_MembersInjector.injectConfigService(diaryLandscapeAdapter, DoubleCheck.lazy(this.configServiceProvider));
        DiaryAdapter_MembersInjector.injectLocalSettingsService(diaryLandscapeAdapter, DoubleCheck.lazy(this.localSettingsServiceProvider));
        DiaryAdapter_MembersInjector.injectSession(diaryLandscapeAdapter, DoubleCheck.lazy(this.sessionProvider));
        DiaryAdapter_MembersInjector.injectPremiumService(diaryLandscapeAdapter, DoubleCheck.lazy(this.premiumServiceProvider));
        DiaryAdapter_MembersInjector.injectAnalyticsService(diaryLandscapeAdapter, DoubleCheck.lazy(this.analyticsServiceProvider));
        DiaryAdapter_MembersInjector.injectDiaryAnalyticsHelper(diaryLandscapeAdapter, DoubleCheck.lazy(this.diaryAnalyticsHelperProvider));
        DiaryAdapter_MembersInjector.injectFitStepsSharedPreferences(diaryLandscapeAdapter, DoubleCheck.lazy(this.fitStepsSharedPreferencesProvider));
        DiaryAdapter_MembersInjector.injectLocationService(diaryLandscapeAdapter, DoubleCheck.lazy(this.locationServiceProvider));
        DiaryAdapter_MembersInjector.injectAdUnitService(diaryLandscapeAdapter, DoubleCheck.lazy(this.adUnitServiceProvider));
    }
}
