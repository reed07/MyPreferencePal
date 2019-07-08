package com.myfitnesspal.feature.challenges.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ChallengePrizesFragment_MembersInjector implements MembersInjector<ChallengePrizesFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;

    public ChallengePrizesFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.imageServiceProvider = provider3;
    }

    public static MembersInjector<ChallengePrizesFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3) {
        return new ChallengePrizesFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(ChallengePrizesFragment challengePrizesFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(challengePrizesFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(challengePrizesFragment, (Glide) this.glideProvider.get());
        injectImageService(challengePrizesFragment, DoubleCheck.lazy(this.imageServiceProvider));
    }

    public static void injectImageService(ChallengePrizesFragment challengePrizesFragment, Lazy<ImageService> lazy) {
        challengePrizesFragment.imageService = lazy;
    }
}
