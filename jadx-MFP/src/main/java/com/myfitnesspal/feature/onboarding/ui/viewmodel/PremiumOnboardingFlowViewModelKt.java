package com.myfitnesspal.feature.onboarding.ui.viewmodel;

import android.databinding.BindingAdapter;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieCompositionFactory;
import com.google.android.gms.analytics.ecommerce.Promotion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"setAnimation", "", "view", "Lcom/airbnb/lottie/LottieAnimationView;", "animationName", "", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
/* compiled from: PremiumOnboardingFlowViewModel.kt */
public final class PremiumOnboardingFlowViewModelKt {
    @BindingAdapter({"app:lottie_fileName"})
    public static final void setAnimation(@NotNull LottieAnimationView lottieAnimationView, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(lottieAnimationView, Promotion.ACTION_VIEW);
        Intrinsics.checkParameterIsNotNull(str, "animationName");
        LottieCompositionFactory.fromAsset(lottieAnimationView.getContext(), str).addListener(new PremiumOnboardingFlowViewModelKt$setAnimation$1(lottieAnimationView));
    }
}
