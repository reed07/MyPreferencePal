package com.myfitnesspal.feature.onboarding.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingViewModelFactory;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AnimatedOnboardingFragment_MembersInjector implements MembersInjector<AnimatedOnboardingFragment> {
    private final Provider<PremiumOnboardingAnalyticsHelper> analyticsHelperProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<PremiumOnboardingViewModelFactory> vmFactoryProvider;

    public AnimatedOnboardingFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumOnboardingAnalyticsHelper> provider3, Provider<PremiumOnboardingViewModelFactory> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.analyticsHelperProvider = provider3;
        this.vmFactoryProvider = provider4;
    }

    public static MembersInjector<AnimatedOnboardingFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumOnboardingAnalyticsHelper> provider3, Provider<PremiumOnboardingViewModelFactory> provider4) {
        return new AnimatedOnboardingFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(AnimatedOnboardingFragment animatedOnboardingFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(animatedOnboardingFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(animatedOnboardingFragment, (Glide) this.glideProvider.get());
        injectAnalyticsHelper(animatedOnboardingFragment, DoubleCheck.lazy(this.analyticsHelperProvider));
        injectVmFactory(animatedOnboardingFragment, (PremiumOnboardingViewModelFactory) this.vmFactoryProvider.get());
    }

    public static void injectAnalyticsHelper(AnimatedOnboardingFragment animatedOnboardingFragment, Lazy<PremiumOnboardingAnalyticsHelper> lazy) {
        animatedOnboardingFragment.analyticsHelper = lazy;
    }

    public static void injectVmFactory(AnimatedOnboardingFragment animatedOnboardingFragment, PremiumOnboardingViewModelFactory premiumOnboardingViewModelFactory) {
        animatedOnboardingFragment.vmFactory = premiumOnboardingViewModelFactory;
    }
}
