package com.myfitnesspal.feature.onboarding.navigation;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.support.v4.app.FragmentActivity;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0003H&J(\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH&J\b\u0010\u0012\u001a\u00020\tH&J\b\u0010\u0013\u001a\u00020\tH&J\b\u0010\u0014\u001a\u00020\tH&J\b\u0010\u0015\u001a\u00020\tH&J\u001c\u0010\u0016\u001a\u00020\t2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\tH&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u001c"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/navigation/Navigator;", "Landroid/arch/lifecycle/DefaultLifecycleObserver;", "activity", "Landroid/support/v4/app/FragmentActivity;", "getActivity", "()Landroid/support/v4/app/FragmentActivity;", "setActivity", "(Landroid/support/v4/app/FragmentActivity;)V", "onDestroy", "", "register", "host", "showMacroGoalEditor", "localizedEnergyValue", "", "carb", "protein", "fat", "showNutrientDashboardSettings", "showNutrition", "showOnboardingFlow", "showOnboardingWelcome", "showPremiumUpsellActivity", "featureId", "", "mode", "Lcom/myfitnesspal/shared/constants/Constants$UpsellDisplayMode;", "showSubscriptionMarketDetails", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: Navigator.kt */
public interface Navigator extends DefaultLifecycleObserver {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: Navigator.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void showPremiumUpsellActivity$default(Navigator navigator, String str, UpsellDisplayMode upsellDisplayMode, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    str = null;
                }
                navigator.showPremiumUpsellActivity(str, upsellDisplayMode);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showPremiumUpsellActivity");
        }
    }

    @Nullable
    FragmentActivity getActivity();

    void onDestroy();

    void register(@NotNull FragmentActivity fragmentActivity);

    void setActivity(@Nullable FragmentActivity fragmentActivity);

    void showMacroGoalEditor(float f, float f2, float f3, float f4);

    void showNutrientDashboardSettings();

    void showNutrition();

    void showOnboardingFlow();

    void showOnboardingWelcome();

    void showPremiumUpsellActivity(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode);

    void showSubscriptionMarketDetails();
}
