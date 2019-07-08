package com.myfitnesspal.feature.onboarding.ui.viewmodel;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "anim", "Lcom/airbnb/lottie/LottieComposition;", "kotlin.jvm.PlatformType", "onResult"}, k = 3, mv = {1, 1, 13})
/* compiled from: PremiumOnboardingFlowViewModel.kt */
final class PremiumOnboardingFlowViewModelKt$setAnimation$1<T> implements LottieListener<LottieComposition> {
    final /* synthetic */ LottieAnimationView $view;

    PremiumOnboardingFlowViewModelKt$setAnimation$1(LottieAnimationView lottieAnimationView) {
        this.$view = lottieAnimationView;
    }

    public final void onResult(LottieComposition lottieComposition) {
        this.$view.setComposition(lottieComposition);
    }
}
