package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MealGoalsFragment_MembersInjector implements MembersInjector<MealGoalsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MealGoalsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<NutrientGoalsUtil> provider4, Provider<LocalizedStringsUtil> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userEnergyServiceProvider = provider3;
        this.nutrientGoalsUtilProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
    }

    public static MembersInjector<MealGoalsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<NutrientGoalsUtil> provider4, Provider<LocalizedStringsUtil> provider5) {
        MealGoalsFragment_MembersInjector mealGoalsFragment_MembersInjector = new MealGoalsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return mealGoalsFragment_MembersInjector;
    }

    public void injectMembers(MealGoalsFragment mealGoalsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(mealGoalsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(mealGoalsFragment, (Glide) this.glideProvider.get());
        injectUserEnergyService(mealGoalsFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectNutrientGoalsUtil(mealGoalsFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectLocalizedStringsUtil(mealGoalsFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
    }

    public static void injectUserEnergyService(MealGoalsFragment mealGoalsFragment, Lazy<UserEnergyService> lazy) {
        mealGoalsFragment.userEnergyService = lazy;
    }

    public static void injectNutrientGoalsUtil(MealGoalsFragment mealGoalsFragment, Lazy<NutrientGoalsUtil> lazy) {
        mealGoalsFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectLocalizedStringsUtil(MealGoalsFragment mealGoalsFragment, Lazy<LocalizedStringsUtil> lazy) {
        mealGoalsFragment.localizedStringsUtil = lazy;
    }
}
