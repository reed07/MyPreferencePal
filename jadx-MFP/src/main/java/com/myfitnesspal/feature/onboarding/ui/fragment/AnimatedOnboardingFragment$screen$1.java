package com.myfitnesspal.feature.onboarding.ui.fragment;

import android.os.Bundle;
import com.myfitnesspal.feature.onboarding.model.OnboardingScreen;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/feature/onboarding/model/OnboardingScreen;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: AnimatedOnboardingFragment.kt */
final class AnimatedOnboardingFragment$screen$1 extends Lambda implements Function0<OnboardingScreen> {
    final /* synthetic */ AnimatedOnboardingFragment this$0;

    AnimatedOnboardingFragment$screen$1(AnimatedOnboardingFragment animatedOnboardingFragment) {
        this.this$0 = animatedOnboardingFragment;
        super(0);
    }

    @NotNull
    public final OnboardingScreen invoke() {
        Bundle arguments = this.this$0.getArguments();
        Serializable serializable = arguments != null ? arguments.getSerializable("ARG_SCREEN") : null;
        if (serializable != null) {
            return (OnboardingScreen) serializable;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.feature.onboarding.model.OnboardingScreen");
    }
}
