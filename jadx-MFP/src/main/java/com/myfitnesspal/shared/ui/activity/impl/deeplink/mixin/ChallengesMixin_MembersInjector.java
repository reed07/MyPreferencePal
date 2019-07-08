package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ChallengesMixin_MembersInjector implements MembersInjector<ChallengesMixin> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<ChallengesService> challengesServiceProvider;

    public ChallengesMixin_MembersInjector(Provider<AnalyticsService> provider, Provider<ChallengesService> provider2) {
        this.analyticsProvider = provider;
        this.challengesServiceProvider = provider2;
    }

    public static MembersInjector<ChallengesMixin> create(Provider<AnalyticsService> provider, Provider<ChallengesService> provider2) {
        return new ChallengesMixin_MembersInjector(provider, provider2);
    }

    public void injectMembers(ChallengesMixin challengesMixin) {
        DeepLinkMixinBase_MembersInjector.injectAnalytics(challengesMixin, DoubleCheck.lazy(this.analyticsProvider));
        injectChallengesService(challengesMixin, DoubleCheck.lazy(this.challengesServiceProvider));
    }

    public static void injectChallengesService(ChallengesMixin challengesMixin, Lazy<ChallengesService> lazy) {
        challengesMixin.challengesService = lazy;
    }
}
