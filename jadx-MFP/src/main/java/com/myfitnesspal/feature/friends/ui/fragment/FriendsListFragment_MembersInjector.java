package com.myfitnesspal.feature.friends.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class FriendsListFragment_MembersInjector implements MembersInjector<FriendsListFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<FriendService> friendServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<MiniUserInfoMapper> miniUserInfoMapperProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public FriendsListFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<FriendService> provider3, Provider<UserWeightService> provider4, Provider<MiniUserInfoMapper> provider5, Provider<NavigationHelper> provider6) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.friendServiceProvider = provider3;
        this.userWeightServiceProvider = provider4;
        this.miniUserInfoMapperProvider = provider5;
        this.navigationHelperProvider = provider6;
    }

    public static MembersInjector<FriendsListFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<FriendService> provider3, Provider<UserWeightService> provider4, Provider<MiniUserInfoMapper> provider5, Provider<NavigationHelper> provider6) {
        FriendsListFragment_MembersInjector friendsListFragment_MembersInjector = new FriendsListFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return friendsListFragment_MembersInjector;
    }

    public void injectMembers(FriendsListFragment friendsListFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(friendsListFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(friendsListFragment, (Glide) this.glideProvider.get());
        injectFriendService(friendsListFragment, DoubleCheck.lazy(this.friendServiceProvider));
        injectUserWeightService(friendsListFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectMiniUserInfoMapper(friendsListFragment, DoubleCheck.lazy(this.miniUserInfoMapperProvider));
        injectNavigationHelper(friendsListFragment, DoubleCheck.lazy(this.navigationHelperProvider));
    }

    public static void injectFriendService(FriendsListFragment friendsListFragment, Lazy<FriendService> lazy) {
        friendsListFragment.friendService = lazy;
    }

    public static void injectUserWeightService(FriendsListFragment friendsListFragment, Lazy<UserWeightService> lazy) {
        friendsListFragment.userWeightService = lazy;
    }

    public static void injectMiniUserInfoMapper(FriendsListFragment friendsListFragment, Lazy<MiniUserInfoMapper> lazy) {
        friendsListFragment.miniUserInfoMapper = lazy;
    }

    public static void injectNavigationHelper(FriendsListFragment friendsListFragment, Lazy<NavigationHelper> lazy) {
        friendsListFragment.navigationHelper = lazy;
    }
}
