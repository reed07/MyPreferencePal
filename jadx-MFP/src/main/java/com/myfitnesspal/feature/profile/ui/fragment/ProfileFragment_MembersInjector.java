package com.myfitnesspal.feature.profile.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdManager;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.strings.GrammarService;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ProfileFragment_MembersInjector implements MembersInjector<ProfileFragment> {
    private final Provider<AchievementAdManager> achievementAdManagerProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ChallengesAnalyticsHelper> challengesAnalyticsHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<FriendService> friendServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GrammarService> grammarServiceProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MiniUserInfoMapper> miniUserInfoMapperProvider;
    private final Provider<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelperProvider;
    private final Provider<NewsFeedService> newsFeedServiceProvider;
    private final Provider<PremiumAnalyticsHelper> premiumAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;
    private final Provider<UserSummaryService> userSummaryServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public ProfileFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NewsFeedAnalyticsHelper> provider3, Provider<UserWeightService> provider4, Provider<GrammarService> provider5, Provider<MiniUserInfoMapper> provider6, Provider<FriendService> provider7, Provider<UserSummaryService> provider8, Provider<NewsFeedService> provider9, Provider<ChallengesAnalyticsHelper> provider10, Provider<ConfigService> provider11, Provider<ImageService> provider12, Provider<LocalSettingsService> provider13, Provider<AppSettings> provider14, Provider<PremiumService> provider15, Provider<PremiumAnalyticsHelper> provider16, Provider<UserApplicationSettingsService> provider17, Provider<AchievementAdManager> provider18) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.newsFeedAnalyticsHelperProvider = provider3;
        this.userWeightServiceProvider = provider4;
        this.grammarServiceProvider = provider5;
        this.miniUserInfoMapperProvider = provider6;
        this.friendServiceProvider = provider7;
        this.userSummaryServiceProvider = provider8;
        this.newsFeedServiceProvider = provider9;
        this.challengesAnalyticsHelperProvider = provider10;
        this.configServiceProvider = provider11;
        this.imageServiceProvider = provider12;
        this.localSettingsServiceProvider = provider13;
        this.appSettingsProvider = provider14;
        this.premiumServiceProvider = provider15;
        this.premiumAnalyticsHelperProvider = provider16;
        this.userApplicationSettingsServiceProvider = provider17;
        this.achievementAdManagerProvider = provider18;
    }

    public static MembersInjector<ProfileFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NewsFeedAnalyticsHelper> provider3, Provider<UserWeightService> provider4, Provider<GrammarService> provider5, Provider<MiniUserInfoMapper> provider6, Provider<FriendService> provider7, Provider<UserSummaryService> provider8, Provider<NewsFeedService> provider9, Provider<ChallengesAnalyticsHelper> provider10, Provider<ConfigService> provider11, Provider<ImageService> provider12, Provider<LocalSettingsService> provider13, Provider<AppSettings> provider14, Provider<PremiumService> provider15, Provider<PremiumAnalyticsHelper> provider16, Provider<UserApplicationSettingsService> provider17, Provider<AchievementAdManager> provider18) {
        ProfileFragment_MembersInjector profileFragment_MembersInjector = new ProfileFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18);
        return profileFragment_MembersInjector;
    }

    public void injectMembers(ProfileFragment profileFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(profileFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(profileFragment, (Glide) this.glideProvider.get());
        injectNewsFeedAnalyticsHelper(profileFragment, DoubleCheck.lazy(this.newsFeedAnalyticsHelperProvider));
        injectUserWeightService(profileFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectGrammarService(profileFragment, DoubleCheck.lazy(this.grammarServiceProvider));
        injectMiniUserInfoMapper(profileFragment, (MiniUserInfoMapper) this.miniUserInfoMapperProvider.get());
        injectFriendService(profileFragment, DoubleCheck.lazy(this.friendServiceProvider));
        injectUserSummaryService(profileFragment, DoubleCheck.lazy(this.userSummaryServiceProvider));
        injectNewsFeedService(profileFragment, DoubleCheck.lazy(this.newsFeedServiceProvider));
        injectChallengesAnalyticsHelper(profileFragment, DoubleCheck.lazy(this.challengesAnalyticsHelperProvider));
        injectConfigService(profileFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectImageService(profileFragment, DoubleCheck.lazy(this.imageServiceProvider));
        injectLocalSettingsService(profileFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectAppSettings(profileFragment, DoubleCheck.lazy(this.appSettingsProvider));
        injectPremiumService(profileFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectPremiumAnalyticsHelper(profileFragment, DoubleCheck.lazy(this.premiumAnalyticsHelperProvider));
        injectUserApplicationSettingsService(profileFragment, DoubleCheck.lazy(this.userApplicationSettingsServiceProvider));
        injectAchievementAdManager(profileFragment, DoubleCheck.lazy(this.achievementAdManagerProvider));
    }

    public static void injectNewsFeedAnalyticsHelper(ProfileFragment profileFragment, Lazy<NewsFeedAnalyticsHelper> lazy) {
        profileFragment.newsFeedAnalyticsHelper = lazy;
    }

    public static void injectUserWeightService(ProfileFragment profileFragment, Lazy<UserWeightService> lazy) {
        profileFragment.userWeightService = lazy;
    }

    public static void injectGrammarService(ProfileFragment profileFragment, Lazy<GrammarService> lazy) {
        profileFragment.grammarService = lazy;
    }

    public static void injectMiniUserInfoMapper(ProfileFragment profileFragment, MiniUserInfoMapper miniUserInfoMapper) {
        profileFragment.miniUserInfoMapper = miniUserInfoMapper;
    }

    public static void injectFriendService(ProfileFragment profileFragment, Lazy<FriendService> lazy) {
        profileFragment.friendService = lazy;
    }

    public static void injectUserSummaryService(ProfileFragment profileFragment, Lazy<UserSummaryService> lazy) {
        profileFragment.userSummaryService = lazy;
    }

    public static void injectNewsFeedService(ProfileFragment profileFragment, Lazy<NewsFeedService> lazy) {
        profileFragment.newsFeedService = lazy;
    }

    public static void injectChallengesAnalyticsHelper(ProfileFragment profileFragment, Lazy<ChallengesAnalyticsHelper> lazy) {
        profileFragment.challengesAnalyticsHelper = lazy;
    }

    public static void injectConfigService(ProfileFragment profileFragment, Lazy<ConfigService> lazy) {
        profileFragment.configService = lazy;
    }

    public static void injectImageService(ProfileFragment profileFragment, Lazy<ImageService> lazy) {
        profileFragment.imageService = lazy;
    }

    public static void injectLocalSettingsService(ProfileFragment profileFragment, Lazy<LocalSettingsService> lazy) {
        profileFragment.localSettingsService = lazy;
    }

    public static void injectAppSettings(ProfileFragment profileFragment, Lazy<AppSettings> lazy) {
        profileFragment.appSettings = lazy;
    }

    public static void injectPremiumService(ProfileFragment profileFragment, Lazy<PremiumService> lazy) {
        profileFragment.premiumService = lazy;
    }

    public static void injectPremiumAnalyticsHelper(ProfileFragment profileFragment, Lazy<PremiumAnalyticsHelper> lazy) {
        profileFragment.premiumAnalyticsHelper = lazy;
    }

    public static void injectUserApplicationSettingsService(ProfileFragment profileFragment, Lazy<UserApplicationSettingsService> lazy) {
        profileFragment.userApplicationSettingsService = lazy;
    }

    public static void injectAchievementAdManager(ProfileFragment profileFragment, Lazy<AchievementAdManager> lazy) {
        profileFragment.achievementAdManager = lazy;
    }
}
