package com.myfitnesspal.feature.onboarding.navigation;

import android.arch.lifecycle.DefaultLifecycleObserver.CC;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.dashboard.ui.activity.NutrientDashboardSettingsActivity;
import com.myfitnesspal.feature.goals.ui.activity.MacroGoalEditorActivity;
import com.myfitnesspal.feature.nutrition.ui.activity.Nutrition;
import com.myfitnesspal.feature.onboarding.ui.fragment.OnboardingFlowFragment;
import com.myfitnesspal.feature.onboarding.ui.fragment.WelcomeScreenFragment;
import com.myfitnesspal.feature.payments.util.PaymentUtils;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u001a\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0010\u001a\u00020\u0011H\u0002J(\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\nH\u0016J\b\u0010\u0019\u001a\u00020\nH\u0016J\b\u0010\u001a\u001a\u00020\nH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0016J\u001a\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\nH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\""}, d2 = {"Lcom/myfitnesspal/feature/onboarding/navigation/NavigatorImpl;", "Lcom/myfitnesspal/feature/onboarding/navigation/Navigator;", "()V", "activity", "Landroid/support/v4/app/FragmentActivity;", "getActivity", "()Landroid/support/v4/app/FragmentActivity;", "setActivity", "(Landroid/support/v4/app/FragmentActivity;)V", "onDestroy", "", "register", "host", "replaceFragment", "fragment", "Landroid/support/v4/app/Fragment;", "fragmentContainerId", "", "showMacroGoalEditor", "localizedEnergyValue", "", "carb", "protein", "fat", "showNutrientDashboardSettings", "showNutrition", "showOnboardingFlow", "showOnboardingWelcome", "showPremiumUpsellActivity", "featureId", "", "mode", "Lcom/myfitnesspal/shared/constants/Constants$UpsellDisplayMode;", "showSubscriptionMarketDetails", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: NavigatorImpl.kt */
public final class NavigatorImpl implements Navigator {
    @Nullable
    private FragmentActivity activity;

    public /* synthetic */ void onCreate(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onDestroy(this, lifecycleOwner);
    }

    public /* synthetic */ void onPause(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onPause(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onResume(this, lifecycleOwner);
    }

    public /* synthetic */ void onStart(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onStart(this, lifecycleOwner);
    }

    public /* synthetic */ void onStop(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onStop(this, lifecycleOwner);
    }

    @Nullable
    public FragmentActivity getActivity() {
        return this.activity;
    }

    public void setActivity(@Nullable FragmentActivity fragmentActivity) {
        this.activity = fragmentActivity;
    }

    public void onDestroy() {
        setActivity(null);
    }

    public void register(@NotNull FragmentActivity fragmentActivity) {
        Intrinsics.checkParameterIsNotNull(fragmentActivity, "host");
        setActivity(fragmentActivity);
        fragmentActivity.getLifecycle().addObserver(this);
    }

    public void showNutrition() {
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            activity2.startActivity(Nutrition.newStartIntent(getActivity()));
        }
    }

    public void showNutrientDashboardSettings() {
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            Intent newStartIntent = NutrientDashboardSettingsActivity.newStartIntent(getActivity());
            newStartIntent.putExtra(Extras.SETTINGS_PARENT, "home");
            activity2.startActivity(newStartIntent);
        }
    }

    public void showPremiumUpsellActivity(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode) {
        Intrinsics.checkParameterIsNotNull(upsellDisplayMode, InternalAvidAdSessionContext.CONTEXT_MODE);
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            activity2.startActivity(PremiumUpsellActivity.newStartIntent((Context) activity2, str, upsellDisplayMode));
        }
    }

    public void showMacroGoalEditor(float f, float f2, float f3, float f4) {
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            activity2.startActivityForResult(MacroGoalEditorActivity.newStartIntent(getActivity(), f, f2, f3, f4, 1), 149);
        }
    }

    public void showSubscriptionMarketDetails() {
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        PaymentUtils.manageSubscription(activity2);
    }

    public void showOnboardingFlow() {
        replaceFragment(new OnboardingFlowFragment(), R.id.id_content);
    }

    public void showOnboardingWelcome() {
        replaceFragment(new WelcomeScreenFragment(), R.id.id_content);
    }

    private final void replaceFragment(Fragment fragment, @IdRes int i) {
        if (i != 0) {
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                activity2.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left_medium, R.anim.slide_out_right_medium).replace(i, fragment).commit();
                return;
            }
            return;
        }
        throw new RuntimeException("Incorrect fragmentContainerId");
    }
}
