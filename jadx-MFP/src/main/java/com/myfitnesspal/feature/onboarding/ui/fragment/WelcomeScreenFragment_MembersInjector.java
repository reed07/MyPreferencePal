package com.myfitnesspal.feature.onboarding.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingViewModelFactory;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class WelcomeScreenFragment_MembersInjector implements MembersInjector<WelcomeScreenFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<PremiumOnboardingViewModelFactory> vmFactoryProvider;

    public WelcomeScreenFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumOnboardingViewModelFactory> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.vmFactoryProvider = provider3;
    }

    public static MembersInjector<WelcomeScreenFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumOnboardingViewModelFactory> provider3) {
        return new WelcomeScreenFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(WelcomeScreenFragment welcomeScreenFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(welcomeScreenFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(welcomeScreenFragment, (Glide) this.glideProvider.get());
        injectVmFactory(welcomeScreenFragment, (PremiumOnboardingViewModelFactory) this.vmFactoryProvider.get());
    }

    public static void injectVmFactory(WelcomeScreenFragment welcomeScreenFragment, PremiumOnboardingViewModelFactory premiumOnboardingViewModelFactory) {
        welcomeScreenFragment.vmFactory = premiumOnboardingViewModelFactory;
    }
}
