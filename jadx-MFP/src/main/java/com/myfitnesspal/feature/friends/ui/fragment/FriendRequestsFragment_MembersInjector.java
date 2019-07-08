package com.myfitnesspal.feature.friends.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class FriendRequestsFragment_MembersInjector implements MembersInjector<FriendRequestsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<FriendService> friendServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;

    public FriendRequestsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NavigationHelper> provider3, Provider<FriendService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.navigationHelperProvider = provider3;
        this.friendServiceProvider = provider4;
    }

    public static MembersInjector<FriendRequestsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NavigationHelper> provider3, Provider<FriendService> provider4) {
        return new FriendRequestsFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(FriendRequestsFragment friendRequestsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(friendRequestsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(friendRequestsFragment, (Glide) this.glideProvider.get());
        injectNavigationHelper(friendRequestsFragment, DoubleCheck.lazy(this.navigationHelperProvider));
        injectFriendService(friendRequestsFragment, DoubleCheck.lazy(this.friendServiceProvider));
    }

    public static void injectNavigationHelper(FriendRequestsFragment friendRequestsFragment, Lazy<NavigationHelper> lazy) {
        friendRequestsFragment.navigationHelper = lazy;
    }

    public static void injectFriendService(FriendRequestsFragment friendRequestsFragment, Lazy<FriendService> lazy) {
        friendRequestsFragment.friendService = lazy;
    }
}
