package com.myfitnesspal.feature.premium.ui.navigation;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.support.v4.app.FragmentActivity;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u000e\u001a\u00020\u000fH&J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u001a\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&J\u001a\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&J\u001a\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&J\u001a\u0010\u0019\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellNavigator;", "Landroid/arch/lifecycle/DefaultLifecycleObserver;", "activity", "Landroid/support/v4/app/FragmentActivity;", "getActivity", "()Landroid/support/v4/app/FragmentActivity;", "setActivity", "(Landroid/support/v4/app/FragmentActivity;)V", "containerId", "", "getContainerId", "()I", "setContainerId", "(I)V", "onDestroy", "", "register", "host", "showBenefitUpsell", "promotedFeature", "", "displayMode", "Lcom/myfitnesspal/shared/constants/Constants$UpsellDisplayMode;", "showNativeUpsell", "showPriceUpsell", "showWebUpsell", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumUpsellNavigator.kt */
public interface PremiumUpsellNavigator extends DefaultLifecycleObserver {
    @Nullable
    FragmentActivity getActivity();

    int getContainerId();

    void onDestroy();

    void register(@NotNull FragmentActivity fragmentActivity, int i);

    void setActivity(@Nullable FragmentActivity fragmentActivity);

    void setContainerId(int i);

    void showBenefitUpsell(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode);

    void showNativeUpsell(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode);

    void showPriceUpsell(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode);

    void showWebUpsell(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode);
}
