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

public final class ChallengeDetailsFragment_MembersInjector implements MembersInjector<ChallengeDetailsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ChallengesAnalyticsHelper> challengesAnalyticsHelperProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;

    public ChallengeDetailsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3, Provider<ChallengesAnalyticsHelper> provider4, Provider<DeviceInfo> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.imageServiceProvider = provider3;
        this.challengesAnalyticsHelperProvider = provider4;
        this.deviceInfoProvider = provider5;
    }

    public static MembersInjector<ChallengeDetailsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3, Provider<ChallengesAnalyticsHelper> provider4, Provider<DeviceInfo> provider5) {
        ChallengeDetailsFragment_MembersInjector challengeDetailsFragment_MembersInjector = new ChallengeDetailsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return challengeDetailsFragment_MembersInjector;
    }

    public void injectMembers(ChallengeDetailsFragment challengeDetailsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(challengeDetailsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(challengeDetailsFragment, (Glide) this.glideProvider.get());
        injectImageService(challengeDetailsFragment, DoubleCheck.lazy(this.imageServiceProvider));
        injectChallengesAnalyticsHelper(challengeDetailsFragment, DoubleCheck.lazy(this.challengesAnalyticsHelperProvider));
        injectDeviceInfo(challengeDetailsFragment, DoubleCheck.lazy(this.deviceInfoProvider));
    }

    public static void injectImageService(ChallengeDetailsFragment challengeDetailsFragment, Lazy<ImageService> lazy) {
        challengeDetailsFragment.imageService = lazy;
    }

    public static void injectChallengesAnalyticsHelper(ChallengeDetailsFragment challengeDetailsFragment, Lazy<ChallengesAnalyticsHelper> lazy) {
        challengeDetailsFragment.challengesAnalyticsHelper = lazy;
    }

    public static void injectDeviceInfo(ChallengeDetailsFragment challengeDetailsFragment, Lazy<DeviceInfo> lazy) {
        challengeDetailsFragment.deviceInfo = lazy;
    }
}
