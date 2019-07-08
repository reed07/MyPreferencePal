package com.myfitnesspal.feature.premium.ui.navigation;

import android.arch.lifecycle.DefaultLifecycleObserver.CC;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellNativeFragment;
import com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J\u001a\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u001a\u0010\u001b\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u001c\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u001d\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellNavigatorImpl;", "Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellNavigator;", "()V", "activity", "Landroid/support/v4/app/FragmentActivity;", "getActivity", "()Landroid/support/v4/app/FragmentActivity;", "setActivity", "(Landroid/support/v4/app/FragmentActivity;)V", "containerId", "", "getContainerId", "()I", "setContainerId", "(I)V", "onDestroy", "", "register", "host", "showBenefitUpsell", "promotedFeature", "", "displayMode", "Lcom/myfitnesspal/shared/constants/Constants$UpsellDisplayMode;", "showFragment", "fragment", "Landroid/support/v4/app/Fragment;", "showNativeUpsell", "showPriceUpsell", "showWebUpsell", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumUpsellNavigatorImpl.kt */
public final class PremiumUpsellNavigatorImpl implements PremiumUpsellNavigator {
    @Nullable
    private FragmentActivity activity;
    private int containerId = -1;

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

    public int getContainerId() {
        return this.containerId;
    }

    public void setContainerId(int i) {
        this.containerId = i;
    }

    public void onDestroy() {
        setActivity(null);
        setContainerId(-1);
    }

    public void register(@NotNull FragmentActivity fragmentActivity, int i) {
        Intrinsics.checkParameterIsNotNull(fragmentActivity, "host");
        setActivity(fragmentActivity);
        setContainerId(i);
        fragmentActivity.getLifecycle().addObserver(this);
    }

    public void showBenefitUpsell(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode) {
        Intrinsics.checkParameterIsNotNull(upsellDisplayMode, "displayMode");
        showWebUpsell(str, upsellDisplayMode);
    }

    public void showPriceUpsell(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode) {
        Intrinsics.checkParameterIsNotNull(upsellDisplayMode, "displayMode");
        showWebUpsell(str, upsellDisplayMode);
    }

    public void showNativeUpsell(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode) {
        Intrinsics.checkParameterIsNotNull(upsellDisplayMode, "displayMode");
        PremiumUpsellNativeFragment newInstance = PremiumUpsellNativeFragment.newInstance(str, upsellDisplayMode);
        Intrinsics.checkExpressionValueIsNotNull(newInstance, "PremiumUpsellNativeFragm…otedFeature, displayMode)");
        showFragment(newInstance);
    }

    public void showWebUpsell(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode) {
        Intrinsics.checkParameterIsNotNull(upsellDisplayMode, "displayMode");
        PremiumUpsellWebFragment newInstance = PremiumUpsellWebFragment.newInstance(str, upsellDisplayMode);
        Intrinsics.checkExpressionValueIsNotNull(newInstance, "PremiumUpsellWebFragment…otedFeature, displayMode)");
        showFragment(newInstance);
    }

    private final void showFragment(Fragment fragment) {
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            FragmentManager supportFragmentManager = activity2.getSupportFragmentManager();
            if (supportFragmentManager != null) {
                FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
                if (beginTransaction != null) {
                    beginTransaction.replace(getContainerId(), fragment);
                    beginTransaction.commit();
                }
            }
        }
    }
}
