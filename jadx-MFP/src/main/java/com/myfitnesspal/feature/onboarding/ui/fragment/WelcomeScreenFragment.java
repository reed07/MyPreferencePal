package com.myfitnesspal.feature.onboarding.ui.fragment;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingViewModelFactory;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingWelcomeViewModel;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001a\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/fragment/WelcomeScreenFragment;", "Lcom/myfitnesspal/shared/ui/fragment/MfpFragment;", "()V", "premiumOnboardingWelcomeViewModel", "Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingWelcomeViewModel;", "vmFactory", "Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;", "getVmFactory", "()Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;", "setVmFactory", "(Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setupGooglePlaySubscriptionLink", "root", "stopAnimation", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: WelcomeScreenFragment.kt */
public final class WelcomeScreenFragment extends MfpFragment {
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public PremiumOnboardingWelcomeViewModel premiumOnboardingWelcomeViewModel;
    @Inject
    @NotNull
    public PremiumOnboardingViewModelFactory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/fragment/WelcomeScreenFragment$Companion;", "", "()V", "newInstance", "Lcom/myfitnesspal/feature/onboarding/ui/fragment/WelcomeScreenFragment;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: WelcomeScreenFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final WelcomeScreenFragment newInstance() {
            return new WelcomeScreenFragment();
        }
    }

    @JvmStatic
    @NotNull
    public static final WelcomeScreenFragment newInstance() {
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

    @NotNull
    public static final /* synthetic */ PremiumOnboardingWelcomeViewModel access$getPremiumOnboardingWelcomeViewModel$p(WelcomeScreenFragment welcomeScreenFragment) {
        PremiumOnboardingWelcomeViewModel premiumOnboardingWelcomeViewModel2 = welcomeScreenFragment.premiumOnboardingWelcomeViewModel;
        if (premiumOnboardingWelcomeViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("premiumOnboardingWelcomeViewModel");
        }
        return premiumOnboardingWelcomeViewModel2;
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
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        PremiumOnboardingViewModelFactory premiumOnboardingViewModelFactory = this.vmFactory;
        if (premiumOnboardingViewModelFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        ViewModel viewModel = ViewModelProviders.of(activity, (Factory) premiumOnboardingViewModelFactory).get(PremiumOnboardingWelcomeViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac…omeViewModel::class.java)");
        this.premiumOnboardingWelcomeViewModel = (PremiumOnboardingWelcomeViewModel) viewModel;
        return layoutInflater.inflate(R.layout.fragment_welcome_onboarding, viewGroup, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        super.onViewCreated(view, bundle);
        ((ImageButton) view.findViewById(R.id.btnOpenOnboardingFlow)).setOnClickListener(new WelcomeScreenFragment$onViewCreated$1(this, view));
        setupGooglePlaySubscriptionLink(view);
    }

    /* access modifiers changed from: private */
    public final void stopAnimation(View view) {
        View findViewById = view.findViewById(R.id.animation_view);
        if (findViewById != null) {
            ((LottieAnimationView) findViewById).cancelAnimation();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.airbnb.lottie.LottieAnimationView");
    }

    private final void setupGooglePlaySubscriptionLink(View view) {
        String string = getString(R.string.onboarding_welcome_screen_manage_your_subscription);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onboa…manage_your_subscription)");
        String string2 = getString(R.string.google_play_settings);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.google_play_settings)");
        CharSequence charSequence = string;
        int indexOf$default = StringsKt.indexOf$default(charSequence, string2, 0, true, 2, (Object) null);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        if (indexOf$default != -1) {
            WelcomeScreenFragment$setupGooglePlaySubscriptionLink$$inlined$apply$lambda$1 welcomeScreenFragment$setupGooglePlaySubscriptionLink$$inlined$apply$lambda$1 = new WelcomeScreenFragment$setupGooglePlaySubscriptionLink$$inlined$apply$lambda$1(view.getContext(), this, indexOf$default, view, string2);
            spannableStringBuilder.setSpan(welcomeScreenFragment$setupGooglePlaySubscriptionLink$$inlined$apply$lambda$1, indexOf$default, string2.length() + indexOf$default, 33);
        }
        TextView textView = (TextView) view.findViewById(R.id.btnSubscriptionSettings);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(textView.getResources().getColor(R.color.transparent));
        textView.setText(spannableStringBuilder);
    }
}
