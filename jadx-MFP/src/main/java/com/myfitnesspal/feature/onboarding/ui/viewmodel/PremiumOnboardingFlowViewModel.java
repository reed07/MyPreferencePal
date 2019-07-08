package com.myfitnesspal.feature.onboarding.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import com.myfitnesspal.feature.onboarding.model.OnboardingScreen;
import com.myfitnesspal.feature.onboarding.navigation.PremiumOnboardingFlowCoordinator;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingFlowViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "onboardingFlowCoordinator", "Lcom/myfitnesspal/feature/onboarding/navigation/PremiumOnboardingFlowCoordinator;", "analyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/onboarding/service/PremiumOnboardingAnalyticsHelper;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/onboarding/navigation/PremiumOnboardingFlowCoordinator;Ldagger/Lazy;)V", "screen", "Lcom/myfitnesspal/feature/onboarding/model/OnboardingScreen;", "getScreen", "()Lcom/myfitnesspal/feature/onboarding/model/OnboardingScreen;", "setScreen", "(Lcom/myfitnesspal/feature/onboarding/model/OnboardingScreen;)V", "goToAllFeatures", "", "goToPremiumFeature", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumOnboardingFlowViewModel.kt */
public final class PremiumOnboardingFlowViewModel extends AndroidViewModel {
    private final Lazy<PremiumOnboardingAnalyticsHelper> analyticsHelper;
    private final PremiumOnboardingFlowCoordinator onboardingFlowCoordinator;
    @NotNull
    public OnboardingScreen screen;

    public PremiumOnboardingFlowViewModel(@NotNull Application application, @NotNull PremiumOnboardingFlowCoordinator premiumOnboardingFlowCoordinator, @NotNull Lazy<PremiumOnboardingAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Intrinsics.checkParameterIsNotNull(premiumOnboardingFlowCoordinator, "onboardingFlowCoordinator");
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsHelper");
        super(application);
        this.onboardingFlowCoordinator = premiumOnboardingFlowCoordinator;
        this.analyticsHelper = lazy;
    }

    @NotNull
    public final OnboardingScreen getScreen() {
        OnboardingScreen onboardingScreen = this.screen;
        if (onboardingScreen == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screen");
        }
        return onboardingScreen;
    }

    public final void setScreen(@NotNull OnboardingScreen onboardingScreen) {
        Intrinsics.checkParameterIsNotNull(onboardingScreen, "<set-?>");
        this.screen = onboardingScreen;
    }

    public final void goToPremiumFeature() {
        PremiumOnboardingAnalyticsHelper premiumOnboardingAnalyticsHelper = (PremiumOnboardingAnalyticsHelper) this.analyticsHelper.get();
        OnboardingScreen onboardingScreen = this.screen;
        if (onboardingScreen == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screen");
        }
        premiumOnboardingAnalyticsHelper.onboardingTapped(onboardingScreen.getId());
        PremiumOnboardingFlowCoordinator premiumOnboardingFlowCoordinator = this.onboardingFlowCoordinator;
        OnboardingScreen onboardingScreen2 = this.screen;
        if (onboardingScreen2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screen");
        }
        premiumOnboardingFlowCoordinator.goToPremiumFeature(onboardingScreen2);
    }

    public final void goToAllFeatures() {
        ((PremiumOnboardingAnalyticsHelper) this.analyticsHelper.get()).featureListSeen();
        this.onboardingFlowCoordinator.showAllFeatures();
    }
}
