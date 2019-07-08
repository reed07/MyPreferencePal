package com.myfitnesspal.feature.onboarding.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.onboarding.model.OnboardingScreen;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/fragment/OnboardingFlowFragment;", "Lcom/myfitnesspal/shared/ui/fragment/MfpFragment;", "()V", "fragments", "", "Lcom/myfitnesspal/feature/onboarding/ui/fragment/AnimatedOnboardingFragment;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "startAnimationAfterLayoutPass", "adapter", "Landroid/support/v4/app/FragmentPagerAdapter;", "AnimationFragmentAdapter", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: OnboardingFlowFragment.kt */
public final class OnboardingFlowFragment extends MfpFragment {
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final List<AnimatedOnboardingFragment> fragments = CollectionsKt.listOf(AnimatedOnboardingFragment.Companion.newInstance(OnboardingScreen.FoodAnalysis), AnimatedOnboardingFragment.Companion.newInstance(OnboardingScreen.CustomDashboard), AnimatedOnboardingFragment.Companion.newInstance(OnboardingScreen.MacroByGram));

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000bH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/fragment/OnboardingFlowFragment$AnimationFragmentAdapter;", "Landroid/support/v4/app/FragmentPagerAdapter;", "fragmentManager", "Landroid/support/v4/app/FragmentManager;", "fragments", "", "Lcom/myfitnesspal/feature/onboarding/ui/fragment/AnimatedOnboardingFragment;", "(Lcom/myfitnesspal/feature/onboarding/ui/fragment/OnboardingFlowFragment;Landroid/support/v4/app/FragmentManager;Ljava/util/List;)V", "getFragments", "()Ljava/util/List;", "getCount", "", "getItem", "i", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnboardingFlowFragment.kt */
    private final class AnimationFragmentAdapter extends FragmentPagerAdapter {
        @NotNull
        private final List<AnimatedOnboardingFragment> fragments;
        final /* synthetic */ OnboardingFlowFragment this$0;

        public AnimationFragmentAdapter(OnboardingFlowFragment onboardingFlowFragment, @NotNull FragmentManager fragmentManager, @NotNull List<AnimatedOnboardingFragment> list) {
            Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
            Intrinsics.checkParameterIsNotNull(list, "fragments");
            this.this$0 = onboardingFlowFragment;
            super(fragmentManager);
            this.fragments = list;
        }

        @NotNull
        public final List<AnimatedOnboardingFragment> getFragments() {
            return this.fragments;
        }

        @NotNull
        public AnimatedOnboardingFragment getItem(int i) {
            return (AnimatedOnboardingFragment) this.fragments.get(i);
        }

        public int getCount() {
            return this.fragments.size();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/fragment/OnboardingFlowFragment$Companion;", "", "()V", "newInstance", "Lcom/myfitnesspal/feature/onboarding/ui/fragment/OnboardingFlowFragment;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnboardingFlowFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final OnboardingFlowFragment newInstance() {
            return new OnboardingFlowFragment();
        }
    }

    @JvmStatic
    @NotNull
    public static final OnboardingFlowFragment newInstance() {
        return Companion.newInstance();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_onboarding_flow, viewGroup, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        super.onViewCreated(view, bundle);
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        AnimationFragmentAdapter animationFragmentAdapter = new AnimationFragmentAdapter(this, childFragmentManager, this.fragments);
        startAnimationAfterLayoutPass(view, animationFragmentAdapter);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "this");
        viewPager.setAdapter(animationFragmentAdapter);
        viewPager.addOnPageChangeListener(new OnboardingFlowFragment$onViewCreated$$inlined$with$lambda$1(this, animationFragmentAdapter));
    }

    private final void startAnimationAfterLayoutPass(View view, FragmentPagerAdapter fragmentPagerAdapter) {
        view.post(new OnboardingFlowFragment$startAnimationAfterLayoutPass$1(fragmentPagerAdapter, view));
    }
}
