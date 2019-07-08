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

public final class ChallengesFragment_MembersInjector implements MembersInjector<ChallengesFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ChallengesAnalyticsHelper> challengesAnalyticsHelperProvider;
    private final Provider<ChallengesService> challengesServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;

    public ChallengesFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<ChallengesService> provider4, Provider<ChallengesAnalyticsHelper> provider5, Provider<ImageService> provider6) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.localSettingsServiceProvider = provider3;
        this.challengesServiceProvider = provider4;
        this.challengesAnalyticsHelperProvider = provider5;
        this.imageServiceProvider = provider6;
    }

    public static MembersInjector<ChallengesFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<ChallengesService> provider4, Provider<ChallengesAnalyticsHelper> provider5, Provider<ImageService> provider6) {
        ChallengesFragment_MembersInjector challengesFragment_MembersInjector = new ChallengesFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return challengesFragment_MembersInjector;
    }

    public void injectMembers(ChallengesFragment challengesFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(challengesFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(challengesFragment, (Glide) this.glideProvider.get());
        injectLocalSettingsService(challengesFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectChallengesService(challengesFragment, DoubleCheck.lazy(this.challengesServiceProvider));
        injectChallengesAnalyticsHelper(challengesFragment, DoubleCheck.lazy(this.challengesAnalyticsHelperProvider));
        injectImageService(challengesFragment, DoubleCheck.lazy(this.imageServiceProvider));
    }

    public static void injectLocalSettingsService(ChallengesFragment challengesFragment, Lazy<LocalSettingsService> lazy) {
        challengesFragment.localSettingsService = lazy;
    }

    public static void injectChallengesService(ChallengesFragment challengesFragment, Lazy<ChallengesService> lazy) {
        challengesFragment.challengesService = lazy;
    }

    public static void injectChallengesAnalyticsHelper(ChallengesFragment challengesFragment, Lazy<ChallengesAnalyticsHelper> lazy) {
        challengesFragment.challengesAnalyticsHelper = lazy;
    }

    public static void injectImageService(ChallengesFragment challengesFragment, Lazy<ImageService> lazy) {
        challengesFragment.imageService = lazy;
    }
}
