package com.myfitnesspal.feature.onboarding.navigation;

import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.uacf.core.util.Function1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "nutrientGoal", "Lcom/myfitnesspal/shared/model/v2/MfpNutrientGoal;", "kotlin.jvm.PlatformType", "execute"}, k = 3, mv = {1, 1, 13})
/* compiled from: PremiumOnboardingFlowCoordinator.kt */
final class PremiumOnboardingFlowCoordinator$showMacroGoals$1<T> implements Function1<MfpNutrientGoal> {
    final /* synthetic */ PremiumOnboardingFlowCoordinator this$0;

    PremiumOnboardingFlowCoordinator$showMacroGoals$1(PremiumOnboardingFlowCoordinator premiumOnboardingFlowCoordinator) {
        this.this$0 = premiumOnboardingFlowCoordinator;
    }

    public final void execute(MfpNutrientGoal mfpNutrientGoal) {
        Intrinsics.checkExpressionValueIsNotNull(mfpNutrientGoal, "nutrientGoal");
        MfpDailyGoal defaultGoal = mfpNutrientGoal.getDefaultGoal();
        UserEnergyService access$getUserEnergyService$p = this.this$0.userEnergyService;
        Intrinsics.checkExpressionValueIsNotNull(defaultGoal, "defaultGoal");
        MfpMeasuredValue energy = defaultGoal.getEnergy();
        Intrinsics.checkExpressionValueIsNotNull(energy, "defaultGoal.energy");
        this.this$0.navigator.showMacroGoalEditor(access$getUserEnergyService$p.getCurrentEnergy((double) energy.getValue()), defaultGoal.getCarbohydrates(), defaultGoal.getProtein(), defaultGoal.getFat());
    }
}
