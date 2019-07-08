package com.myfitnesspal.feature.challenges.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ChallengeWebViewFragment_MembersInjector implements MembersInjector<ChallengeWebViewFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;

    public ChallengeWebViewFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.imageServiceProvider = provider3;
    }

    public static MembersInjector<ChallengeWebViewFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3) {
        return new ChallengeWebViewFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(ChallengeWebViewFragment challengeWebViewFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(challengeWebViewFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(challengeWebViewFragment, (Glide) this.glideProvider.get());
        injectImageService(challengeWebViewFragment, DoubleCheck.lazy(this.imageServiceProvider));
    }

    public static void injectImageService(ChallengeWebViewFragment challengeWebViewFragment, Lazy<ImageService> lazy) {
        challengeWebViewFragment.imageService = lazy;
    }
}
