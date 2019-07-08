package com.myfitnesspal.feature.dashboard.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class NutrientDashboardPresetSelectionFragment_MembersInjector implements MembersInjector<NutrientDashboardPresetSelectionFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<NutrientGoalsUtil> goalsUtilProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public NutrientDashboardPresetSelectionFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3, Provider<NutrientGoalsUtil> provider4, Provider<LocalizedStringsUtil> provider5, Provider<UserEnergyService> provider6, Provider<AnalyticsService> provider7) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.premiumServiceProvider = provider3;
        this.goalsUtilProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
        this.userEnergyServiceProvider = provider6;
        this.analyticsServiceProvider = provider7;
    }

    public static MembersInjector<NutrientDashboardPresetSelectionFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3, Provider<NutrientGoalsUtil> provider4, Provider<LocalizedStringsUtil> provider5, Provider<UserEnergyService> provider6, Provider<AnalyticsService> provider7) {
        NutrientDashboardPresetSelectionFragment_MembersInjector nutrientDashboardPresetSelectionFragment_MembersInjector = new NutrientDashboardPresetSelectionFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return nutrientDashboardPresetSelectionFragment_MembersInjector;
    }

    public void injectMembers(NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(nutrientDashboardPresetSelectionFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(nutrientDashboardPresetSelectionFragment, (Glide) this.glideProvider.get());
        injectPremiumService(nutrientDashboardPresetSelectionFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectGoalsUtil(nutrientDashboardPresetSelectionFragment, DoubleCheck.lazy(this.goalsUtilProvider));
        injectLocalizedStringsUtil(nutrientDashboardPresetSelectionFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectUserEnergyService(nutrientDashboardPresetSelectionFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectAnalyticsService(nutrientDashboardPresetSelectionFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }

    public static void injectPremiumService(NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment, Lazy<PremiumService> lazy) {
        nutrientDashboardPresetSelectionFragment.premiumService = lazy;
    }

    public static void injectGoalsUtil(NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment, Lazy<NutrientGoalsUtil> lazy) {
        nutrientDashboardPresetSelectionFragment.goalsUtil = lazy;
    }

    public static void injectLocalizedStringsUtil(NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment, Lazy<LocalizedStringsUtil> lazy) {
        nutrientDashboardPresetSelectionFragment.localizedStringsUtil = lazy;
    }

    public static void injectUserEnergyService(NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment, Lazy<UserEnergyService> lazy) {
        nutrientDashboardPresetSelectionFragment.userEnergyService = lazy;
    }

    public static void injectAnalyticsService(NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment, Lazy<AnalyticsService> lazy) {
        nutrientDashboardPresetSelectionFragment.analyticsService = lazy;
    }
}
