package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CustomGoalByDayFragment_MembersInjector implements MembersInjector<CustomGoalByDayFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalUtilsProvider;
    private final Provider<PremiumApiErrorUtil> premiumApiErrorUtilProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public CustomGoalByDayFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<UserEnergyService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<NutrientGoalService> provider6, Provider<Session> provider7, Provider<AnalyticsService> provider8, Provider<PremiumApiErrorUtil> provider9) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.nutrientGoalUtilsProvider = provider3;
        this.userEnergyServiceProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
        this.nutrientGoalServiceProvider = provider6;
        this.sessionProvider = provider7;
        this.analyticsServiceProvider = provider8;
        this.premiumApiErrorUtilProvider = provider9;
    }

    public static MembersInjector<CustomGoalByDayFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<UserEnergyService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<NutrientGoalService> provider6, Provider<Session> provider7, Provider<AnalyticsService> provider8, Provider<PremiumApiErrorUtil> provider9) {
        CustomGoalByDayFragment_MembersInjector customGoalByDayFragment_MembersInjector = new CustomGoalByDayFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return customGoalByDayFragment_MembersInjector;
    }

    public void injectMembers(CustomGoalByDayFragment customGoalByDayFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(customGoalByDayFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(customGoalByDayFragment, (Glide) this.glideProvider.get());
        injectNutrientGoalUtils(customGoalByDayFragment, DoubleCheck.lazy(this.nutrientGoalUtilsProvider));
        injectUserEnergyService(customGoalByDayFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectLocalizedStringsUtil(customGoalByDayFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectNutrientGoalService(customGoalByDayFragment, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectSession(customGoalByDayFragment, DoubleCheck.lazy(this.sessionProvider));
        injectAnalyticsService(customGoalByDayFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectPremiumApiErrorUtil(customGoalByDayFragment, DoubleCheck.lazy(this.premiumApiErrorUtilProvider));
    }

    public static void injectNutrientGoalUtils(CustomGoalByDayFragment customGoalByDayFragment, Lazy<NutrientGoalsUtil> lazy) {
        customGoalByDayFragment.nutrientGoalUtils = lazy;
    }

    public static void injectUserEnergyService(CustomGoalByDayFragment customGoalByDayFragment, Lazy<UserEnergyService> lazy) {
        customGoalByDayFragment.userEnergyService = lazy;
    }

    public static void injectLocalizedStringsUtil(CustomGoalByDayFragment customGoalByDayFragment, Lazy<LocalizedStringsUtil> lazy) {
        customGoalByDayFragment.localizedStringsUtil = lazy;
    }

    public static void injectNutrientGoalService(CustomGoalByDayFragment customGoalByDayFragment, Lazy<NutrientGoalService> lazy) {
        customGoalByDayFragment.nutrientGoalService = lazy;
    }

    public static void injectSession(CustomGoalByDayFragment customGoalByDayFragment, Lazy<Session> lazy) {
        customGoalByDayFragment.session = lazy;
    }

    public static void injectAnalyticsService(CustomGoalByDayFragment customGoalByDayFragment, Lazy<AnalyticsService> lazy) {
        customGoalByDayFragment.analyticsService = lazy;
    }

    public static void injectPremiumApiErrorUtil(CustomGoalByDayFragment customGoalByDayFragment, Lazy<PremiumApiErrorUtil> lazy) {
        customGoalByDayFragment.premiumApiErrorUtil = lazy;
    }
}
