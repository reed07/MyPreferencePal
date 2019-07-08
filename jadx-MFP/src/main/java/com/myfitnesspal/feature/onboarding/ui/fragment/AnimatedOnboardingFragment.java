package com.myfitnesspal.feature.onboarding.ui.fragment;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.databinding.OnboardingAnimatedFragmentBinding;
import com.myfitnesspal.feature.onboarding.model.OnboardingScreen;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingFlowViewModel;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingViewModelFactory;
import com.myfitnesspal.shared.extension.SpannableUtil;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0006\u0010!\u001a\u00020\u0018J\b\u0010\"\u001a\u00020\u0018H\u0002J\u0006\u0010#\u001a\u00020\u0018R$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/fragment/AnimatedOnboardingFragment;", "Lcom/myfitnesspal/shared/ui/fragment/MfpFragment;", "()V", "analyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/onboarding/service/PremiumOnboardingAnalyticsHelper;", "getAnalyticsHelper", "()Ldagger/Lazy;", "setAnalyticsHelper", "(Ldagger/Lazy;)V", "binding", "Lcom/myfitnesspal/android/databinding/OnboardingAnimatedFragmentBinding;", "premiumOnboardingFlowViewModel", "Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingFlowViewModel;", "screen", "Lkotlin/Lazy;", "Lcom/myfitnesspal/feature/onboarding/model/OnboardingScreen;", "vmFactory", "Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;", "getVmFactory", "()Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;", "setVmFactory", "(Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "pauseAnimation", "setupHeaderHighlighting", "startAnimation", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AnimatedOnboardingFragment.kt */
public final class AnimatedOnboardingFragment extends MfpFragment {
    private static final String ARG_SCREEN = "ARG_SCREEN";
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;
    @Inject
    @NotNull
    public Lazy<PremiumOnboardingAnalyticsHelper> analyticsHelper;
    private OnboardingAnimatedFragmentBinding binding;
    private PremiumOnboardingFlowViewModel premiumOnboardingFlowViewModel;
    private kotlin.Lazy<? extends OnboardingScreen> screen = LazyKt.lazy(new AnimatedOnboardingFragment$screen$1(this));
    @Inject
    @NotNull
    public PremiumOnboardingViewModelFactory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/fragment/AnimatedOnboardingFragment$Companion;", "", "()V", "ARG_SCREEN", "", "newInstance", "Lcom/myfitnesspal/feature/onboarding/ui/fragment/AnimatedOnboardingFragment;", "screen", "Lcom/myfitnesspal/feature/onboarding/model/OnboardingScreen;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AnimatedOnboardingFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final AnimatedOnboardingFragment newInstance(@NotNull OnboardingScreen onboardingScreen) {
            Intrinsics.checkParameterIsNotNull(onboardingScreen, "screen");
            AnimatedOnboardingFragment animatedOnboardingFragment = new AnimatedOnboardingFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(AnimatedOnboardingFragment.ARG_SCREEN, onboardingScreen);
            animatedOnboardingFragment.setArguments(bundle);
            return animatedOnboardingFragment;
        }
    }

    @JvmStatic
    @NotNull
    public static final AnimatedOnboardingFragment newInstance(@NotNull OnboardingScreen onboardingScreen) {
        return Companion.newInstance(onboardingScreen);
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

    @NotNull
    public final Lazy<PremiumOnboardingAnalyticsHelper> getAnalyticsHelper() {
        Lazy<PremiumOnboardingAnalyticsHelper> lazy = this.analyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("analyticsHelper");
        }
        return lazy;
    }

    public final void setAnalyticsHelper(@NotNull Lazy<PremiumOnboardingAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.analyticsHelper = lazy;
    }

    @NotNull
    public final PremiumOnboardingViewModelFactory getVmFactory() {
        PremiumOnboardingViewModelFactory premiumOnboardingViewModelFactory = this.vmFactory;
        if (premiumOnboardingViewModelFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        return premiumOnboardingViewModelFactory;
    }

    public final void setVmFactory(@NotNull PremiumOnboardingViewModelFactory premiumOnboardingViewModelFactory) {
        Intrinsics.checkParameterIsNotNull(premiumOnboardingViewModelFactory, "<set-?>");
        this.vmFactory = premiumOnboardingViewModelFactory;
    }

    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        Fragment fragment = this;
        PremiumOnboardingViewModelFactory premiumOnboardingViewModelFactory = this.vmFactory;
        if (premiumOnboardingViewModelFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        ViewModel viewModel = ViewModelProviders.of(fragment, (Factory) premiumOnboardingViewModelFactory).get(PremiumOnboardingFlowViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…lowViewModel::class.java)");
        this.premiumOnboardingFlowViewModel = (PremiumOnboardingFlowViewModel) viewModel;
        PremiumOnboardingFlowViewModel premiumOnboardingFlowViewModel2 = this.premiumOnboardingFlowViewModel;
        if (premiumOnboardingFlowViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("premiumOnboardingFlowViewModel");
        }
        premiumOnboardingFlowViewModel2.setScreen((OnboardingScreen) this.screen.getValue());
        OnboardingAnimatedFragmentBinding inflate = OnboardingAnimatedFragmentBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "this");
        PremiumOnboardingFlowViewModel premiumOnboardingFlowViewModel3 = this.premiumOnboardingFlowViewModel;
        if (premiumOnboardingFlowViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("premiumOnboardingFlowViewModel");
        }
        inflate.setViewModel(premiumOnboardingFlowViewModel3);
        inflate.setLifecycleOwner(this);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "OnboardingAnimatedFragme…ardingFragment)\n        }");
        this.binding = inflate;
        setupHeaderHighlighting();
        OnboardingAnimatedFragmentBinding onboardingAnimatedFragmentBinding = this.binding;
        if (onboardingAnimatedFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return onboardingAnimatedFragmentBinding.getRoot();
    }

    private final void setupHeaderHighlighting() {
        String string = getString(R.string.onboarding_screen_header);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onboarding_screen_header)");
        String string2 = getString(R.string.onboarding_screen_header_highlight);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.onboa…_screen_header_highlight)");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        SpannableUtil.setColorSpan(spannableStringBuilder, string2, getResources().getColor(R.color.premium_onboarding_header_highlight));
        OnboardingAnimatedFragmentBinding onboardingAnimatedFragmentBinding = this.binding;
        if (onboardingAnimatedFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        TextView textView = onboardingAnimatedFragmentBinding.screenHeader;
        Intrinsics.checkExpressionValueIsNotNull(textView, "binding.screenHeader");
        textView.setText(spannableStringBuilder);
    }

    public final void pauseAnimation() {
        if (isAdded()) {
            StringBuilder sb = new StringBuilder();
            sb.append("pauseAnimation ");
            sb.append(this);
            Ln.d(sb.toString(), new Object[0]);
            OnboardingAnimatedFragmentBinding onboardingAnimatedFragmentBinding = this.binding;
            if (onboardingAnimatedFragmentBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            onboardingAnimatedFragmentBinding.screenAnimation.pauseAnimation();
        }
    }

    public final void startAnimation() {
        if (isAdded()) {
            Lazy<PremiumOnboardingAnalyticsHelper> lazy = this.analyticsHelper;
            if (lazy == null) {
                Intrinsics.throwUninitializedPropertyAccessException("analyticsHelper");
            }
            ((PremiumOnboardingAnalyticsHelper) lazy.get()).onboardingDisplayed(((OnboardingScreen) this.screen.getValue()).getId());
            StringBuilder sb = new StringBuilder();
            sb.append("startAnimation ");
            sb.append(this);
            Ln.d(sb.toString(), new Object[0]);
            OnboardingAnimatedFragmentBinding onboardingAnimatedFragmentBinding = this.binding;
            if (onboardingAnimatedFragmentBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            onboardingAnimatedFragmentBinding.screenAnimation.playAnimation();
        }
    }
}
