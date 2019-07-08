package com.myfitnesspal.feature.challenges.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ChallengeTabFragmentBase_MembersInjector implements MembersInjector<ChallengeTabFragmentBase> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public ChallengeTabFragmentBase_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static MembersInjector<ChallengeTabFragmentBase> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new ChallengeTabFragmentBase_MembersInjector(provider, provider2);
    }

    public void injectMembers(ChallengeTabFragmentBase challengeTabFragmentBase) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(challengeTabFragmentBase, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(challengeTabFragmentBase, (Glide) this.glideProvider.get());
    }
}
