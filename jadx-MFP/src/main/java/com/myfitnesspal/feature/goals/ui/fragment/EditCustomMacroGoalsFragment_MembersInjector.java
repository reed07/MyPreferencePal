package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
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

public final class EditCustomMacroGoalsFragment_MembersInjector implements MembersInjector<EditCustomMacroGoalsFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutritionalGoalsUtilProvider;
    private final Provider<PremiumApiErrorUtil> premiumApiErrorUtilProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public EditCustomMacroGoalsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<PremiumService> provider5, Provider<NutrientGoalService> provider6, Provider<NutrientGoalsUtil> provider7, Provider<Session> provider8, Provider<AnalyticsService> provider9, Provider<PremiumApiErrorUtil> provider10) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userEnergyServiceProvider = provider3;
        this.localizedStringsUtilProvider = provider4;
        this.premiumServiceProvider = provider5;
        this.nutrientGoalServiceProvider = provider6;
        this.nutritionalGoalsUtilProvider = provider7;
        this.sessionProvider = provider8;
        this.analyticsServiceProvider = provider9;
        this.premiumApiErrorUtilProvider = provider10;
    }

    public static MembersInjector<EditCustomMacroGoalsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<PremiumService> provider5, Provider<NutrientGoalService> provider6, Provider<NutrientGoalsUtil> provider7, Provider<Session> provider8, Provider<AnalyticsService> provider9, Provider<PremiumApiErrorUtil> provider10) {
        EditCustomMacroGoalsFragment_MembersInjector editCustomMacroGoalsFragment_MembersInjector = new EditCustomMacroGoalsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
        return editCustomMacroGoalsFragment_MembersInjector;
    }

    public void injectMembers(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(editCustomMacroGoalsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(editCustomMacroGoalsFragment, (Glide) this.glideProvider.get());
        injectUserEnergyService(editCustomMacroGoalsFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectLocalizedStringsUtil(editCustomMacroGoalsFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectPremiumService(editCustomMacroGoalsFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectNutrientGoalService(editCustomMacroGoalsFragment, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectNutritionalGoalsUtil(editCustomMacroGoalsFragment, DoubleCheck.lazy(this.nutritionalGoalsUtilProvider));
        injectSession(editCustomMacroGoalsFragment, DoubleCheck.lazy(this.sessionProvider));
        injectAnalyticsService(editCustomMacroGoalsFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectPremiumApiErrorUtil(editCustomMacroGoalsFragment, DoubleCheck.lazy(this.premiumApiErrorUtilProvider));
    }

    public static void injectUserEnergyService(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment, Lazy<UserEnergyService> lazy) {
        editCustomMacroGoalsFragment.userEnergyService = lazy;
    }

    public static void injectLocalizedStringsUtil(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment, Lazy<LocalizedStringsUtil> lazy) {
        editCustomMacroGoalsFragment.localizedStringsUtil = lazy;
    }

    public static void injectPremiumService(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment, Lazy<PremiumService> lazy) {
        editCustomMacroGoalsFragment.premiumService = lazy;
    }

    public static void injectNutrientGoalService(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment, Lazy<NutrientGoalService> lazy) {
        editCustomMacroGoalsFragment.nutrientGoalService = lazy;
    }

    public static void injectNutritionalGoalsUtil(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment, Lazy<NutrientGoalsUtil> lazy) {
        editCustomMacroGoalsFragment.nutritionalGoalsUtil = lazy;
    }

    public static void injectSession(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment, Lazy<Session> lazy) {
        editCustomMacroGoalsFragment.session = lazy;
    }

    public static void injectAnalyticsService(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment, Lazy<AnalyticsService> lazy) {
        editCustomMacroGoalsFragment.analyticsService = lazy;
    }

    public static void injectPremiumApiErrorUtil(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment, Lazy<PremiumApiErrorUtil> lazy) {
        editCustomMacroGoalsFragment.premiumApiErrorUtil = lazy;
    }
}
