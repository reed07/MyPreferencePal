package com.myfitnesspal.android.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingFlowViewModel;

public abstract class OnboardingAnimatedFragmentBinding extends ViewDataBinding {
    @NonNull
    public final TextView btnOpenFeature;
    @Bindable
    protected PremiumOnboardingFlowViewModel mViewModel;
    @NonNull
    public final LottieAnimationView screenAnimation;
    @NonNull
    public final TextView screenDescription;
    @NonNull
    public final TextView screenHeader;
    @NonNull
    public final TextView screenTitle;

    public abstract void setViewModel(@Nullable PremiumOnboardingFlowViewModel premiumOnboardingFlowViewModel);

    protected OnboardingAnimatedFragmentBinding(DataBindingComponent dataBindingComponent, View view, int i, TextView textView, LottieAnimationView lottieAnimationView, TextView textView2, TextView textView3, TextView textView4) {
        super(dataBindingComponent, view, i);
        this.btnOpenFeature = textView;
        this.screenAnimation = lottieAnimationView;
        this.screenDescription = textView2;
        this.screenHeader = textView3;
        this.screenTitle = textView4;
    }

    @Nullable
    public PremiumOnboardingFlowViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static OnboardingAnimatedFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static OnboardingAnimatedFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (OnboardingAnimatedFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.onboarding_animated_fragment, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static OnboardingAnimatedFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static OnboardingAnimatedFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return (OnboardingAnimatedFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.onboarding_animated_fragment, null, false, dataBindingComponent);
    }

    public static OnboardingAnimatedFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static OnboardingAnimatedFragmentBinding bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        return (OnboardingAnimatedFragmentBinding) bind(dataBindingComponent, view, R.layout.onboarding_animated_fragment);
    }
}
