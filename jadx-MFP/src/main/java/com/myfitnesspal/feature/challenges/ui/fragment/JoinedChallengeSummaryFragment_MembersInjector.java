package com.myfitnesspal.feature.challenges.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class JoinedChallengeSummaryFragment_MembersInjector implements MembersInjector<JoinedChallengeSummaryFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ChallengesAnalyticsHelper> challengesAnalyticsHelperProvider;
    private final Provider<ChallengesService> challengesServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;

    public JoinedChallengeSummaryFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3, Provider<ChallengesService> provider4, Provider<LocalSettingsService> provider5, Provider<ChallengesAnalyticsHelper> provider6) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.imageServiceProvider = provider3;
        this.challengesServiceProvider = provider4;
        this.localSettingsServiceProvider = provider5;
        this.challengesAnalyticsHelperProvider = provider6;
    }

    public static MembersInjector<JoinedChallengeSummaryFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ImageService> provider3, Provider<ChallengesService> provider4, Provider<LocalSettingsService> provider5, Provider<ChallengesAnalyticsHelper> provider6) {
        JoinedChallengeSummaryFragment_MembersInjector joinedChallengeSummaryFragment_MembersInjector = new JoinedChallengeSummaryFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return joinedChallengeSummaryFragment_MembersInjector;
    }

    public void injectMembers(JoinedChallengeSummaryFragment joinedChallengeSummaryFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(joinedChallengeSummaryFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(joinedChallengeSummaryFragment, (Glide) this.glideProvider.get());
        injectImageService(joinedChallengeSummaryFragment, DoubleCheck.lazy(this.imageServiceProvider));
        injectChallengesService(joinedChallengeSummaryFragment, DoubleCheck.lazy(this.challengesServiceProvider));
        injectLocalSettingsService(joinedChallengeSummaryFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectChallengesAnalyticsHelper(joinedChallengeSummaryFragment, DoubleCheck.lazy(this.challengesAnalyticsHelperProvider));
    }

    public static void injectImageService(JoinedChallengeSummaryFragment joinedChallengeSummaryFragment, Lazy<ImageService> lazy) {
        joinedChallengeSummaryFragment.imageService = lazy;
    }

    public static void injectChallengesService(JoinedChallengeSummaryFragment joinedChallengeSummaryFragment, Lazy<ChallengesService> lazy) {
        joinedChallengeSummaryFragment.challengesService = lazy;
    }

    public static void injectLocalSettingsService(JoinedChallengeSummaryFragment joinedChallengeSummaryFragment, Lazy<LocalSettingsService> lazy) {
        joinedChallengeSummaryFragment.localSettingsService = lazy;
    }

    public static void injectChallengesAnalyticsHelper(JoinedChallengeSummaryFragment joinedChallengeSummaryFragment, Lazy<ChallengesAnalyticsHelper> lazy) {
        joinedChallengeSummaryFragment.challengesAnalyticsHelper = lazy;
    }
}
