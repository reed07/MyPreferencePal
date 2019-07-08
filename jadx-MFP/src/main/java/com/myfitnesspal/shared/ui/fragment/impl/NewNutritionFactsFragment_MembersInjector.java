package com.myfitnesspal.shared.ui.fragment.impl;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.ui.fragment.NutritionFactsFragmentBase_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class NewNutritionFactsFragment_MembersInjector implements MembersInjector<NewNutritionFactsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public NewNutritionFactsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<PremiumService> provider4, Provider<NutrientGoalsUtil> provider5, Provider<NutrientGoalService> provider6) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userEnergyServiceProvider = provider3;
        this.premiumServiceProvider = provider4;
        this.nutrientGoalsUtilProvider = provider5;
        this.nutrientGoalServiceProvider = provider6;
    }

    public static MembersInjector<NewNutritionFactsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<PremiumService> provider4, Provider<NutrientGoalsUtil> provider5, Provider<NutrientGoalService> provider6) {
        NewNutritionFactsFragment_MembersInjector newNutritionFactsFragment_MembersInjector = new NewNutritionFactsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return newNutritionFactsFragment_MembersInjector;
    }

    public void injectMembers(NewNutritionFactsFragment newNutritionFactsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(newNutritionFactsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(newNutritionFactsFragment, (Glide) this.glideProvider.get());
        NutritionFactsFragmentBase_MembersInjector.injectUserEnergyService(newNutritionFactsFragment, (UserEnergyService) this.userEnergyServiceProvider.get());
        injectPremiumService(newNutritionFactsFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectNutrientGoalsUtil(newNutritionFactsFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectNutrientGoalService(newNutritionFactsFragment, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectUserEnergyService(newNutritionFactsFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
    }

    public static void injectPremiumService(NewNutritionFactsFragment newNutritionFactsFragment, Lazy<PremiumService> lazy) {
        newNutritionFactsFragment.premiumService = lazy;
    }

    public static void injectNutrientGoalsUtil(NewNutritionFactsFragment newNutritionFactsFragment, Lazy<NutrientGoalsUtil> lazy) {
        newNutritionFactsFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectNutrientGoalService(NewNutritionFactsFragment newNutritionFactsFragment, Lazy<NutrientGoalService> lazy) {
        newNutritionFactsFragment.nutrientGoalService = lazy;
    }

    public static void injectUserEnergyService(NewNutritionFactsFragment newNutritionFactsFragment, Lazy<UserEnergyService> lazy) {
        newNutritionFactsFragment.userEnergyService = lazy;
    }
}
