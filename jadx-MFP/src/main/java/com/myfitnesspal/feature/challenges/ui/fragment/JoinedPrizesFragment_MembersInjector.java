package com.myfitnesspal.feature.challenges.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class JoinedPrizesFragment_MembersInjector implements MembersInjector<JoinedPrizesFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;

    public JoinedPrizesFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.imageServiceProvider = provider3;
    }

    public static MembersInjector<JoinedPrizesFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3) {
        return new JoinedPrizesFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(JoinedPrizesFragment joinedPrizesFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(joinedPrizesFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(joinedPrizesFragment, (Glide) this.glideProvider.get());
        injectImageService(joinedPrizesFragment, DoubleCheck.lazy(this.imageServiceProvider));
    }

    public static void injectImageService(JoinedPrizesFragment joinedPrizesFragment, Lazy<ImageService> lazy) {
        joinedPrizesFragment.imageService = lazy;
    }
}
