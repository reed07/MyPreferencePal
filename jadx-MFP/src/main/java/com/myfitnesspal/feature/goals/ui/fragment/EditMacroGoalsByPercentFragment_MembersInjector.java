package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EditMacroGoalsByPercentFragment_MembersInjector implements MembersInjector<EditMacroGoalsByPercentFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyProvider;

    public EditMacroGoalsByPercentFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<Session> provider4, Provider<UserEnergyService> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.nutrientGoalsUtilProvider = provider3;
        this.sessionProvider = provider4;
        this.userEnergyProvider = provider5;
    }

    public static MembersInjector<EditMacroGoalsByPercentFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<Session> provider4, Provider<UserEnergyService> provider5) {
        EditMacroGoalsByPercentFragment_MembersInjector editMacroGoalsByPercentFragment_MembersInjector = new EditMacroGoalsByPercentFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return editMacroGoalsByPercentFragment_MembersInjector;
    }

    public void injectMembers(EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(editMacroGoalsByPercentFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(editMacroGoalsByPercentFragment, (Glide) this.glideProvider.get());
        injectNutrientGoalsUtil(editMacroGoalsByPercentFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectSession(editMacroGoalsByPercentFragment, DoubleCheck.lazy(this.sessionProvider));
        injectUserEnergy(editMacroGoalsByPercentFragment, DoubleCheck.lazy(this.userEnergyProvider));
    }

    public static void injectNutrientGoalsUtil(EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment, Lazy<NutrientGoalsUtil> lazy) {
        editMacroGoalsByPercentFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectSession(EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment, Lazy<Session> lazy) {
        editMacroGoalsByPercentFragment.session = lazy;
    }

    public static void injectUserEnergy(EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment, Lazy<UserEnergyService> lazy) {
        editMacroGoalsByPercentFragment.userEnergy = lazy;
    }
}
