package com.myfitnesspal.feature.onboarding.ui.activity;

import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingWelcomeViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingWelcomeViewModel;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnboardingActivity.kt */
final class OnboardingActivity$premiumOnboardingWelcomeViewModel$1 extends Lambda implements Function0<PremiumOnboardingWelcomeViewModel> {
    final /* synthetic */ OnboardingActivity this$0;

    OnboardingActivity$premiumOnboardingWelcomeViewModel$1(OnboardingActivity onboardingActivity) {
        this.this$0 = onboardingActivity;
        super(0);
    }

    @NotNull
    public final PremiumOnboardingWelcomeViewModel invoke() {
        OnboardingActivity onboardingActivity = this.this$0;
        return (PremiumOnboardingWelcomeViewModel) ViewModelProviders.of((FragmentActivity) onboardingActivity, (Factory) onboardingActivity.getVmFactory()).get(PremiumOnboardingWelcomeViewModel.class);
    }
}
