package com.myfitnesspal.feature.settings.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class WeeklyNutritionSettingsListFragment_MembersInjector implements MembersInjector<WeeklyNutritionSettingsListFragment> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;

    public WeeklyNutritionSettingsListFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<AnalyticsService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.localSettingsServiceProvider = provider3;
        this.analyticsProvider = provider4;
    }

    public static MembersInjector<WeeklyNutritionSettingsListFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<AnalyticsService> provider4) {
        return new WeeklyNutritionSettingsListFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(WeeklyNutritionSettingsListFragment weeklyNutritionSettingsListFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(weeklyNutritionSettingsListFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(weeklyNutritionSettingsListFragment, (Glide) this.glideProvider.get());
        injectLocalSettingsService(weeklyNutritionSettingsListFragment, (LocalSettingsService) this.localSettingsServiceProvider.get());
        injectAnalytics(weeklyNutritionSettingsListFragment, DoubleCheck.lazy(this.analyticsProvider));
    }

    public static void injectLocalSettingsService(WeeklyNutritionSettingsListFragment weeklyNutritionSettingsListFragment, LocalSettingsService localSettingsService) {
        weeklyNutritionSettingsListFragment.localSettingsService = localSettingsService;
    }

    public static void injectAnalytics(WeeklyNutritionSettingsListFragment weeklyNutritionSettingsListFragment, Lazy<AnalyticsService> lazy) {
        weeklyNutritionSettingsListFragment.analytics = lazy;
    }
}
