package com.myfitnesspal.feature.onboarding.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.myfitnesspal.android.R;
import com.uacf.core.util.Ln;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnboardingFlowFragment.kt */
final class OnboardingFlowFragment$startAnimationAfterLayoutPass$1 implements Runnable {
    final /* synthetic */ FragmentPagerAdapter $adapter;
    final /* synthetic */ View $view;

    OnboardingFlowFragment$startAnimationAfterLayoutPass$1(FragmentPagerAdapter fragmentPagerAdapter, View view) {
        this.$adapter = fragmentPagerAdapter;
        this.$view = view;
    }

    public final void run() {
        Ln.d("startAnimationAfterLayoutPass", new Object[0]);
        FragmentPagerAdapter fragmentPagerAdapter = this.$adapter;
        ViewPager viewPager = (ViewPager) this.$view.findViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "view.viewPager");
        Fragment item = fragmentPagerAdapter.getItem(viewPager.getCurrentItem());
        if (item != null) {
            ((AnimatedOnboardingFragment) item).startAnimation();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.feature.onboarding.ui.fragment.AnimatedOnboardingFragment");
    }
}
