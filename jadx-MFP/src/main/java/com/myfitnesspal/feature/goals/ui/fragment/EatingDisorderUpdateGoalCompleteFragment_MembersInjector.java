package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EatingDisorderUpdateGoalCompleteFragment_MembersInjector implements MembersInjector<EatingDisorderUpdateGoalCompleteFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public EatingDisorderUpdateGoalCompleteFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserWeightService> provider3, Provider<UserEnergyService> provider4, Provider<NutrientGoalsUtil> provider5, Provider<LocalizedStringsUtil> provider6) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userWeightServiceProvider = provider3;
        this.userEnergyServiceProvider = provider4;
        this.nutrientGoalsUtilProvider = provider5;
        this.localizedStringsUtilProvider = provider6;
    }

    public static MembersInjector<EatingDisorderUpdateGoalCompleteFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserWeightService> provider3, Provider<UserEnergyService> provider4, Provider<NutrientGoalsUtil> provider5, Provider<LocalizedStringsUtil> provider6) {
        EatingDisorderUpdateGoalCompleteFragment_MembersInjector eatingDisorderUpdateGoalCompleteFragment_MembersInjector = new EatingDisorderUpdateGoalCompleteFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return eatingDisorderUpdateGoalCompleteFragment_MembersInjector;
    }

    public void injectMembers(EatingDisorderUpdateGoalCompleteFragment eatingDisorderUpdateGoalCompleteFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(eatingDisorderUpdateGoalCompleteFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(eatingDisorderUpdateGoalCompleteFragment, (Glide) this.glideProvider.get());
        injectUserWeightService(eatingDisorderUpdateGoalCompleteFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectUserEnergyService(eatingDisorderUpdateGoalCompleteFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectNutrientGoalsUtil(eatingDisorderUpdateGoalCompleteFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectLocalizedStringsUtil(eatingDisorderUpdateGoalCompleteFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
    }

    public static void injectUserWeightService(EatingDisorderUpdateGoalCompleteFragment eatingDisorderUpdateGoalCompleteFragment, Lazy<UserWeightService> lazy) {
        eatingDisorderUpdateGoalCompleteFragment.userWeightService = lazy;
    }

    public static void injectUserEnergyService(EatingDisorderUpdateGoalCompleteFragment eatingDisorderUpdateGoalCompleteFragment, Lazy<UserEnergyService> lazy) {
        eatingDisorderUpdateGoalCompleteFragment.userEnergyService = lazy;
    }

    public static void injectNutrientGoalsUtil(EatingDisorderUpdateGoalCompleteFragment eatingDisorderUpdateGoalCompleteFragment, Lazy<NutrientGoalsUtil> lazy) {
        eatingDisorderUpdateGoalCompleteFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectLocalizedStringsUtil(EatingDisorderUpdateGoalCompleteFragment eatingDisorderUpdateGoalCompleteFragment, Lazy<LocalizedStringsUtil> lazy) {
        eatingDisorderUpdateGoalCompleteFragment.localizedStringsUtil = lazy;
    }
}
