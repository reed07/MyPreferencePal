package com.myfitnesspal.feature.profile.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyInfoFragment_MembersInjector implements MembersInjector<MyInfoFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ChallengesAnalyticsHelper> challengesAnalyticsProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public MyInfoFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3, Provider<UserEnergyService> provider4, Provider<UserWeightService> provider5, Provider<ImageService> provider6, Provider<ConfigService> provider7, Provider<ChallengesAnalyticsHelper> provider8) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.premiumServiceProvider = provider3;
        this.userEnergyServiceProvider = provider4;
        this.userWeightServiceProvider = provider5;
        this.imageServiceProvider = provider6;
        this.configServiceProvider = provider7;
        this.challengesAnalyticsProvider = provider8;
    }

    public static MembersInjector<MyInfoFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3, Provider<UserEnergyService> provider4, Provider<UserWeightService> provider5, Provider<ImageService> provider6, Provider<ConfigService> provider7, Provider<ChallengesAnalyticsHelper> provider8) {
        MyInfoFragment_MembersInjector myInfoFragment_MembersInjector = new MyInfoFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return myInfoFragment_MembersInjector;
    }

    public void injectMembers(MyInfoFragment myInfoFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(myInfoFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(myInfoFragment, (Glide) this.glideProvider.get());
        injectPremiumService(myInfoFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectUserEnergyService(myInfoFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectUserWeightService(myInfoFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectImageService(myInfoFragment, DoubleCheck.lazy(this.imageServiceProvider));
        injectConfigService(myInfoFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectChallengesAnalytics(myInfoFragment, DoubleCheck.lazy(this.challengesAnalyticsProvider));
    }

    public static void injectPremiumService(MyInfoFragment myInfoFragment, Lazy<PremiumService> lazy) {
        myInfoFragment.premiumService = lazy;
    }

    public static void injectUserEnergyService(MyInfoFragment myInfoFragment, Lazy<UserEnergyService> lazy) {
        myInfoFragment.userEnergyService = lazy;
    }

    public static void injectUserWeightService(MyInfoFragment myInfoFragment, Lazy<UserWeightService> lazy) {
        myInfoFragment.userWeightService = lazy;
    }

    public static void injectImageService(MyInfoFragment myInfoFragment, Lazy<ImageService> lazy) {
        myInfoFragment.imageService = lazy;
    }

    public static void injectConfigService(MyInfoFragment myInfoFragment, Lazy<ConfigService> lazy) {
        myInfoFragment.configService = lazy;
    }

    public static void injectChallengesAnalytics(MyInfoFragment myInfoFragment, Lazy<ChallengesAnalyticsHelper> lazy) {
        myInfoFragment.challengesAnalytics = lazy;
    }
}
