package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EditMacroGoalsByGramsFragment_MembersInjector implements MembersInjector<EditMacroGoalsByGramsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<UserEnergyService> userEnergyProvider;

    public EditMacroGoalsByGramsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<UserEnergyService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.nutrientGoalsUtilProvider = provider3;
        this.userEnergyProvider = provider4;
    }

    public static MembersInjector<EditMacroGoalsByGramsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<UserEnergyService> provider4) {
        return new EditMacroGoalsByGramsFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(EditMacroGoalsByGramsFragment editMacroGoalsByGramsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(editMacroGoalsByGramsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(editMacroGoalsByGramsFragment, (Glide) this.glideProvider.get());
        injectNutrientGoalsUtil(editMacroGoalsByGramsFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectUserEnergy(editMacroGoalsByGramsFragment, DoubleCheck.lazy(this.userEnergyProvider));
    }

    public static void injectNutrientGoalsUtil(EditMacroGoalsByGramsFragment editMacroGoalsByGramsFragment, Lazy<NutrientGoalsUtil> lazy) {
        editMacroGoalsByGramsFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectUserEnergy(EditMacroGoalsByGramsFragment editMacroGoalsByGramsFragment, Lazy<UserEnergyService> lazy) {
        editMacroGoalsByGramsFragment.userEnergy = lazy;
    }
}
