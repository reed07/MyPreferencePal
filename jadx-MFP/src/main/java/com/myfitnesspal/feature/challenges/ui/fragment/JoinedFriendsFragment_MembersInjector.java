package com.myfitnesspal.feature.challenges.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class JoinedFriendsFragment_MembersInjector implements MembersInjector<JoinedFriendsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;

    public JoinedFriendsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.imageServiceProvider = provider3;
    }

    public static MembersInjector<JoinedFriendsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3) {
        return new JoinedFriendsFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(JoinedFriendsFragment joinedFriendsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(joinedFriendsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(joinedFriendsFragment, (Glide) this.glideProvider.get());
        injectImageService(joinedFriendsFragment, DoubleCheck.lazy(this.imageServiceProvider));
    }

    public static void injectImageService(JoinedFriendsFragment joinedFriendsFragment, Lazy<ImageService> lazy) {
        joinedFriendsFragment.imageService = lazy;
    }
}
