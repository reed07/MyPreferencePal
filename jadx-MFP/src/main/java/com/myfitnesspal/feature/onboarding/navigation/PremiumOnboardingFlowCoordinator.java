package com.myfitnesspal.feature.onboarding.navigation;

import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.onboarding.model.OnboardingScreen;
import com.myfitnesspal.feature.onboarding.navigation.Navigator.DefaultImpls;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\nJ\u0006\u0010\u000f\u001a\u00020\nJ\u0006\u0010\u0010\u001a\u00020\nJ\u0006\u0010\u0011\u001a\u00020\nJ\u0006\u0010\u0012\u001a\u00020\nJ\u0006\u0010\u0013\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/navigation/PremiumOnboardingFlowCoordinator;", "", "navigator", "Lcom/myfitnesspal/feature/onboarding/navigation/Navigator;", "userEnergyService", "Lcom/myfitnesspal/shared/service/userdata/UserEnergyService;", "nutrientGoalService", "Lcom/myfitnesspal/feature/goals/service/NutrientGoalService;", "(Lcom/myfitnesspal/feature/onboarding/navigation/Navigator;Lcom/myfitnesspal/shared/service/userdata/UserEnergyService;Lcom/myfitnesspal/feature/goals/service/NutrientGoalService;)V", "goToPremiumFeature", "", "screen", "Lcom/myfitnesspal/feature/onboarding/model/OnboardingScreen;", "showAllFeatures", "showCustomDashboard", "showFoodAnalysis", "showMacroGoals", "showOnboardingFlowFragment", "showOnboardingWelcomeFragment", "showSubscriptionMarketDetails", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumOnboardingFlowCoordinator.kt */
public final class PremiumOnboardingFlowCoordinator {
    /* access modifiers changed from: private */
    public final Navigator navigator;
    private final NutrientGoalService nutrientGoalService;
    /* access modifiers changed from: private */
    public final UserEnergyService userEnergyService;

    public PremiumOnboardingFlowCoordinator(@NotNull Navigator navigator2, @NotNull UserEnergyService userEnergyService2, @NotNull NutrientGoalService nutrientGoalService2) {
        Intrinsics.checkParameterIsNotNull(navigator2, "navigator");
        Intrinsics.checkParameterIsNotNull(userEnergyService2, "userEnergyService");
        Intrinsics.checkParameterIsNotNull(nutrientGoalService2, "nutrientGoalService");
        this.navigator = navigator2;
        this.userEnergyService = userEnergyService2;
        this.nutrientGoalService = nutrientGoalService2;
    }

    public final void goToPremiumFeature(@NotNull OnboardingScreen onboardingScreen) {
        Intrinsics.checkParameterIsNotNull(onboardingScreen, "screen");
        switch (onboardingScreen) {
            case FoodAnalysis:
                showFoodAnalysis();
                return;
            case CustomDashboard:
                showCustomDashboard();
                return;
            case MacroByGram:
                showMacroGoals();
                return;
            default:
                return;
        }
    }

    public final void showFoodAnalysis() {
        this.navigator.showNutrition();
    }

    public final void showCustomDashboard() {
        this.navigator.showNutrientDashboardSettings();
    }

    public final void showMacroGoals() {
        this.nutrientGoalService.getNutrientGoal(new PremiumOnboardingFlowCoordinator$showMacroGoals$1(this), PremiumOnboardingFlowCoordinator$showMacroGoals$2.INSTANCE);
    }

    public final void showAllFeatures() {
        DefaultImpls.showPremiumUpsellActivity$default(this.navigator, null, UpsellDisplayMode.FeatureScreen, 1, null);
    }

    public final void showSubscriptionMarketDetails() {
        this.navigator.showSubscriptionMarketDetails();
    }

    public final void showOnboardingFlowFragment() {
        this.navigator.showOnboardingFlow();
    }

    public final void showOnboardingWelcomeFragment() {
        this.navigator.showOnboardingWelcome();
    }
}
