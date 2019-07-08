package com.myfitnesspal.feature.challenges.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.uacf.core.util.DeviceInfo;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ChallengeFriendsFragment_MembersInjector implements MembersInjector<ChallengeFriendsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ChallengesAnalyticsHelper> challengesAnalyticsHelperProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;

    public ChallengeFriendsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3, Provider<ChallengesAnalyticsHelper> provider4, Provider<DeviceInfo> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.imageServiceProvider = provider3;
        this.challengesAnalyticsHelperProvider = provider4;
        this.deviceInfoProvider = provider5;
    }

    public static MembersInjector<ChallengeFriendsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3, Provider<ChallengesAnalyticsHelper> provider4, Provider<DeviceInfo> provider5) {
        ChallengeFriendsFragment_MembersInjector challengeFriendsFragment_MembersInjector = new ChallengeFriendsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return challengeFriendsFragment_MembersInjector;
    }

    public void injectMembers(ChallengeFriendsFragment challengeFriendsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(challengeFriendsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(challengeFriendsFragment, (Glide) this.glideProvider.get());
        injectImageService(challengeFriendsFragment, DoubleCheck.lazy(this.imageServiceProvider));
        injectChallengesAnalyticsHelper(challengeFriendsFragment, DoubleCheck.lazy(this.challengesAnalyticsHelperProvider));
        injectDeviceInfo(challengeFriendsFragment, DoubleCheck.lazy(this.deviceInfoProvider));
    }

    public static void injectImageService(ChallengeFriendsFragment challengeFriendsFragment, Lazy<ImageService> lazy) {
        challengeFriendsFragment.imageService = lazy;
    }

    public static void injectChallengesAnalyticsHelper(ChallengeFriendsFragment challengeFriendsFragment, Lazy<ChallengesAnalyticsHelper> lazy) {
        challengeFriendsFragment.challengesAnalyticsHelper = lazy;
    }

    public static void injectDeviceInfo(ChallengeFriendsFragment challengeFriendsFragment, Lazy<DeviceInfo> lazy) {
        challengeFriendsFragment.deviceInfo = lazy;
    }
}
