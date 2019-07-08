package com.myfitnesspal.feature.onboarding.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import com.myfitnesspal.feature.onboarding.navigation.PremiumOnboardingFlowCoordinator;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000bR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingWelcomeViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "onboardingFlowCoordinator", "Lcom/myfitnesspal/feature/onboarding/navigation/PremiumOnboardingFlowCoordinator;", "analyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/onboarding/service/PremiumOnboardingAnalyticsHelper;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/onboarding/navigation/PremiumOnboardingFlowCoordinator;Ldagger/Lazy;)V", "goToSubscriptions", "", "showOnboardingFlow", "showOnboardingWelcome", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumOnboardingWelcomeViewModel.kt */
public final class PremiumOnboardingWelcomeViewModel extends AndroidViewModel {
    private final Lazy<PremiumOnboardingAnalyticsHelper> analyticsHelper;
    private final PremiumOnboardingFlowCoordinator onboardingFlowCoordinator;

    public PremiumOnboardingWelcomeViewModel(@NotNull Application application, @NotNull PremiumOnboardingFlowCoordinator premiumOnboardingFlowCoordinator, @NotNull Lazy<PremiumOnboardingAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Intrinsics.checkParameterIsNotNull(premiumOnboardingFlowCoordinator, "onboardingFlowCoordinator");
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsHelper");
        super(application);
        this.onboardingFlowCoordinator = premiumOnboardingFlowCoordinator;
        this.analyticsHelper = lazy;
    }

    public final void goToSubscriptions() {
        ((PremiumOnboardingAnalyticsHelper) this.analyticsHelper.get()).manageGooglePlaySubscriptionLinkClicked();
        this.onboardingFlowCoordinator.showSubscriptionMarketDetails();
    }

    public final void showOnboardingWelcome() {
        ((PremiumOnboardingAnalyticsHelper) this.analyticsHelper.get()).onboardingDisplayed(Screens.PREMIUM_ONBOARDING_WELCOME);
        this.onboardingFlowCoordinator.showOnboardingWelcomeFragment();
    }

    public final void showOnboardingFlow() {
        this.onboardingFlowCoordinator.showOnboardingFlowFragment();
    }
}
