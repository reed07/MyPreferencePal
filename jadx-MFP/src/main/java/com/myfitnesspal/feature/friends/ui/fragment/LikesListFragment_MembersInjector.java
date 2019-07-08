package com.myfitnesspal.feature.friends.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class LikesListFragment_MembersInjector implements MembersInjector<LikesListFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<NewsFeedService> newsFeedServiceProvider;

    public LikesListFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NewsFeedService> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.newsFeedServiceProvider = provider3;
    }

    public static MembersInjector<LikesListFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NewsFeedService> provider3) {
        return new LikesListFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(LikesListFragment likesListFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(likesListFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(likesListFragment, (Glide) this.glideProvider.get());
        injectNewsFeedService(likesListFragment, (NewsFeedService) this.newsFeedServiceProvider.get());
    }

    public static void injectNewsFeedService(LikesListFragment likesListFragment, NewsFeedService newsFeedService) {
        likesListFragment.newsFeedService = newsFeedService;
    }
}
