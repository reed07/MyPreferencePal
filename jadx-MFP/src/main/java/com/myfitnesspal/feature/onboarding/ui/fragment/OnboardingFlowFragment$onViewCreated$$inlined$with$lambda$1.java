package com.myfitnesspal.feature.onboarding.ui.fragment;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.uacf.core.util.Ln;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0005H\u0016¨\u0006\r¸\u0006\u0000"}, d2 = {"com/myfitnesspal/feature/onboarding/ui/fragment/OnboardingFlowFragment$onViewCreated$1$1", "Landroid/support/v4/view/ViewPager$OnPageChangeListener;", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "newPosition", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: OnboardingFlowFragment.kt */
public final class OnboardingFlowFragment$onViewCreated$$inlined$with$lambda$1 implements OnPageChangeListener {
    final /* synthetic */ AnimationFragmentAdapter $adapter$inlined;
    final /* synthetic */ OnboardingFlowFragment this$0;

    OnboardingFlowFragment$onViewCreated$$inlined$with$lambda$1(OnboardingFlowFragment onboardingFlowFragment, AnimationFragmentAdapter animationFragmentAdapter) {
        this.this$0 = onboardingFlowFragment;
        this.$adapter$inlined = animationFragmentAdapter;
    }

    public void onPageScrollStateChanged(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("onPageScrollStateChanged(");
        sb.append(i);
        sb.append(": Int)");
        Ln.d(sb.toString(), new Object[0]);
    }

    public void onPageScrolled(int i, float f, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("onPageScrolled(");
        sb.append(i);
        sb.append(": Int, ");
        sb.append(f);
        sb.append(": Float, ");
        sb.append(i2);
        sb.append(": Int)");
        Ln.d(sb.toString(), new Object[0]);
    }

    public void onPageSelected(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("onPageSelected(");
        sb.append(i);
        sb.append(": Int)");
        int i2 = 0;
        Ln.d(sb.toString(), new Object[0]);
        for (Object next : this.this$0.fragments) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            AnimatedOnboardingFragment animatedOnboardingFragment = (AnimatedOnboardingFragment) next;
            if (i == i2) {
                animatedOnboardingFragment.startAnimation();
            } else {
                animatedOnboardingFragment.pauseAnimation();
            }
            i2 = i3;
        }
    }
}
