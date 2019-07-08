package com.myfitnesspal.feature.settings.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.dashboard.service.NutrientDashboardAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.InsightSettings;
import com.myfitnesspal.feature.settings.util.DiarySharingSettingsManager;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class DiarySettingsFragment_MembersInjector implements MembersInjector<DiarySettingsFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DiarySharingSettingsManager> diarySharingSettingsManagerProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<InsightSettings> insightSettingsProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<NutrientDashboardAnalyticsHelper> nutrientDashboardAnalyticsHelperProvider;
    private final Provider<PremiumAnalyticsHelper> premiumAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<TimestampAnalyticsHelper> timestampAnalyticsHelperProvider;

    public DiarySettingsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<DiarySharingSettingsManager> provider3, Provider<CountryService> provider4, Provider<AnalyticsService> provider5, Provider<InsightSettings> provider6, Provider<PremiumService> provider7, Provider<LocalSettingsService> provider8, Provider<PremiumAnalyticsHelper> provider9, Provider<GlobalSettingsService> provider10, Provider<NutrientDashboardAnalyticsHelper> provider11, Provider<TimestampAnalyticsHelper> provider12) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.diarySharingSettingsManagerProvider = provider3;
        this.countryServiceProvider = provider4;
        this.analyticsServiceProvider = provider5;
        this.insightSettingsProvider = provider6;
        this.premiumServiceProvider = provider7;
        this.localSettingsServiceProvider = provider8;
        this.premiumAnalyticsHelperProvider = provider9;
        this.globalSettingsServiceProvider = provider10;
        this.nutrientDashboardAnalyticsHelperProvider = provider11;
        this.timestampAnalyticsHelperProvider = provider12;
    }

    public static MembersInjector<DiarySettingsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<DiarySharingSettingsManager> provider3, Provider<CountryService> provider4, Provider<AnalyticsService> provider5, Provider<InsightSettings> provider6, Provider<PremiumService> provider7, Provider<LocalSettingsService> provider8, Provider<PremiumAnalyticsHelper> provider9, Provider<GlobalSettingsService> provider10, Provider<NutrientDashboardAnalyticsHelper> provider11, Provider<TimestampAnalyticsHelper> provider12) {
        DiarySettingsFragment_MembersInjector diarySettingsFragment_MembersInjector = new DiarySettingsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
        return diarySettingsFragment_MembersInjector;
    }

    public void injectMembers(DiarySettingsFragment diarySettingsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(diarySettingsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(diarySettingsFragment, (Glide) this.glideProvider.get());
        injectDiarySharingSettingsManager(diarySettingsFragment, DoubleCheck.lazy(this.diarySharingSettingsManagerProvider));
        injectCountryService(diarySettingsFragment, DoubleCheck.lazy(this.countryServiceProvider));
        injectAnalyticsService(diarySettingsFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectInsightSettings(diarySettingsFragment, DoubleCheck.lazy(this.insightSettingsProvider));
        injectPremiumService(diarySettingsFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectLocalSettingsService(diarySettingsFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectPremiumAnalyticsHelper(diarySettingsFragment, DoubleCheck.lazy(this.premiumAnalyticsHelperProvider));
        injectGlobalSettingsService(diarySettingsFragment, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        injectNutrientDashboardAnalyticsHelper(diarySettingsFragment, DoubleCheck.lazy(this.nutrientDashboardAnalyticsHelperProvider));
        injectTimestampAnalyticsHelper(diarySettingsFragment, DoubleCheck.lazy(this.timestampAnalyticsHelperProvider));
    }

    public static void injectDiarySharingSettingsManager(DiarySettingsFragment diarySettingsFragment, Lazy<DiarySharingSettingsManager> lazy) {
        diarySettingsFragment.diarySharingSettingsManager = lazy;
    }

    public static void injectCountryService(DiarySettingsFragment diarySettingsFragment, Lazy<CountryService> lazy) {
        diarySettingsFragment.countryService = lazy;
    }

    public static void injectAnalyticsService(DiarySettingsFragment diarySettingsFragment, Lazy<AnalyticsService> lazy) {
        diarySettingsFragment.analyticsService = lazy;
    }

    public static void injectInsightSettings(DiarySettingsFragment diarySettingsFragment, Lazy<InsightSettings> lazy) {
        diarySettingsFragment.insightSettings = lazy;
    }

    public static void injectPremiumService(DiarySettingsFragment diarySettingsFragment, Lazy<PremiumService> lazy) {
        diarySettingsFragment.premiumService = lazy;
    }

    public static void injectLocalSettingsService(DiarySettingsFragment diarySettingsFragment, Lazy<LocalSettingsService> lazy) {
        diarySettingsFragment.localSettingsService = lazy;
    }

    public static void injectPremiumAnalyticsHelper(DiarySettingsFragment diarySettingsFragment, Lazy<PremiumAnalyticsHelper> lazy) {
        diarySettingsFragment.premiumAnalyticsHelper = lazy;
    }

    public static void injectGlobalSettingsService(DiarySettingsFragment diarySettingsFragment, Lazy<GlobalSettingsService> lazy) {
        diarySettingsFragment.globalSettingsService = lazy;
    }

    public static void injectNutrientDashboardAnalyticsHelper(DiarySettingsFragment diarySettingsFragment, Lazy<NutrientDashboardAnalyticsHelper> lazy) {
        diarySettingsFragment.nutrientDashboardAnalyticsHelper = lazy;
    }

    public static void injectTimestampAnalyticsHelper(DiarySettingsFragment diarySettingsFragment, Lazy<TimestampAnalyticsHelper> lazy) {
        diarySettingsFragment.timestampAnalyticsHelper = lazy;
    }
}
