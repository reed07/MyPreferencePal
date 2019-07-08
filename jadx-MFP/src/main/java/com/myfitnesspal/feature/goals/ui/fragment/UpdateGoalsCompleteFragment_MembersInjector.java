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

public final class UpdateGoalsCompleteFragment_MembersInjector implements MembersInjector<UpdateGoalsCompleteFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public UpdateGoalsCompleteFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserWeightService> provider3, Provider<UserEnergyService> provider4, Provider<NutrientGoalsUtil> provider5, Provider<LocalizedStringsUtil> provider6) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userWeightServiceProvider = provider3;
        this.userEnergyServiceProvider = provider4;
        this.nutrientGoalsUtilProvider = provider5;
        this.localizedStringsUtilProvider = provider6;
    }

    public static MembersInjector<UpdateGoalsCompleteFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserWeightService> provider3, Provider<UserEnergyService> provider4, Provider<NutrientGoalsUtil> provider5, Provider<LocalizedStringsUtil> provider6) {
        UpdateGoalsCompleteFragment_MembersInjector updateGoalsCompleteFragment_MembersInjector = new UpdateGoalsCompleteFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return updateGoalsCompleteFragment_MembersInjector;
    }

    public void injectMembers(UpdateGoalsCompleteFragment updateGoalsCompleteFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(updateGoalsCompleteFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(updateGoalsCompleteFragment, (Glide) this.glideProvider.get());
        injectUserWeightService(updateGoalsCompleteFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectUserEnergyService(updateGoalsCompleteFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectNutrientGoalsUtil(updateGoalsCompleteFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectLocalizedStringsUtil(updateGoalsCompleteFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
    }

    public static void injectUserWeightService(UpdateGoalsCompleteFragment updateGoalsCompleteFragment, Lazy<UserWeightService> lazy) {
        updateGoalsCompleteFragment.userWeightService = lazy;
    }

    public static void injectUserEnergyService(UpdateGoalsCompleteFragment updateGoalsCompleteFragment, Lazy<UserEnergyService> lazy) {
        updateGoalsCompleteFragment.userEnergyService = lazy;
    }

    public static void injectNutrientGoalsUtil(UpdateGoalsCompleteFragment updateGoalsCompleteFragment, Lazy<NutrientGoalsUtil> lazy) {
        updateGoalsCompleteFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectLocalizedStringsUtil(UpdateGoalsCompleteFragment updateGoalsCompleteFragment, Lazy<LocalizedStringsUtil> lazy) {
        updateGoalsCompleteFragment.localizedStringsUtil = lazy;
    }
}
