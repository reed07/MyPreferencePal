package com.myfitnesspal.feature.home.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdManager;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.feature.home.service.AppRatingService;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class HomeFragment_MembersInjector implements MembersInjector<HomeFragment> {
    private final Provider<AchievementAdManager> achievementAdManagerProvider;
    private final Provider<AchievementInterstitialAd> achievementInterstitialAdProvider;
    private final Provider<AdUnitService> adUnitServiceProvider;
    private final Provider<AdsSettings> adsSettingsProvider;
    private final Provider<AppRatingService> appRatingServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ChallengesService> challengesServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<UacfEmailVerificationManager> emailVerificationManagerProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MessageService> messageServiceProvider;
    private final Provider<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelperProvider;
    private final Provider<NewsFeedService> newsFeedServiceProvider;
    private final Provider<NutrientDashboardRenderer> nutrientDashboardRendererProvider;
    private final Provider<PremiumOnboardingAnalyticsHelper> premiumOnboardingAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;
    private final Provider<UserPropertiesService> userPropertiesServiceProvider;

    public HomeFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AdsSettings> provider3, Provider<NewsFeedService> provider4, Provider<NewsFeedAnalyticsHelper> provider5, Provider<ConfigService> provider6, Provider<UserPropertiesService> provider7, Provider<PremiumService> provider8, Provider<AdUnitService> provider9, Provider<LocalSettingsService> provider10, Provider<NutrientDashboardRenderer> provider11, Provider<ImageService> provider12, Provider<GlobalSettingsService> provider13, Provider<ChallengesService> provider14, Provider<DiaryService> provider15, Provider<DeepLinkManager> provider16, Provider<AppRatingService> provider17, Provider<MessageService> provider18, Provider<UserApplicationSettingsService> provider19, Provider<UacfEmailVerificationManager> provider20, Provider<LocationService> provider21, Provider<AchievementInterstitialAd> provider22, Provider<AchievementAdManager> provider23, Provider<PremiumOnboardingAnalyticsHelper> provider24) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.adsSettingsProvider = provider3;
        this.newsFeedServiceProvider = provider4;
        this.newsFeedAnalyticsHelperProvider = provider5;
        this.configServiceProvider = provider6;
        this.userPropertiesServiceProvider = provider7;
        this.premiumServiceProvider = provider8;
        this.adUnitServiceProvider = provider9;
        this.localSettingsServiceProvider = provider10;
        this.nutrientDashboardRendererProvider = provider11;
        this.imageServiceProvider = provider12;
        this.globalSettingsServiceProvider = provider13;
        this.challengesServiceProvider = provider14;
        this.diaryServiceProvider = provider15;
        this.deepLinkManagerProvider = provider16;
        this.appRatingServiceProvider = provider17;
        this.messageServiceProvider = provider18;
        this.userApplicationSettingsServiceProvider = provider19;
        this.emailVerificationManagerProvider = provider20;
        this.locationServiceProvider = provider21;
        this.achievementInterstitialAdProvider = provider22;
        this.achievementAdManagerProvider = provider23;
        this.premiumOnboardingAnalyticsHelperProvider = provider24;
    }

    public static MembersInjector<HomeFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AdsSettings> provider3, Provider<NewsFeedService> provider4, Provider<NewsFeedAnalyticsHelper> provider5, Provider<ConfigService> provider6, Provider<UserPropertiesService> provider7, Provider<PremiumService> provider8, Provider<AdUnitService> provider9, Provider<LocalSettingsService> provider10, Provider<NutrientDashboardRenderer> provider11, Provider<ImageService> provider12, Provider<GlobalSettingsService> provider13, Provider<ChallengesService> provider14, Provider<DiaryService> provider15, Provider<DeepLinkManager> provider16, Provider<AppRatingService> provider17, Provider<MessageService> provider18, Provider<UserApplicationSettingsService> provider19, Provider<UacfEmailVerificationManager> provider20, Provider<LocationService> provider21, Provider<AchievementInterstitialAd> provider22, Provider<AchievementAdManager> provider23, Provider<PremiumOnboardingAnalyticsHelper> provider24) {
        HomeFragment_MembersInjector homeFragment_MembersInjector = new HomeFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24);
        return homeFragment_MembersInjector;
    }

    public void injectMembers(HomeFragment homeFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(homeFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(homeFragment, (Glide) this.glideProvider.get());
        injectAdsSettings(homeFragment, DoubleCheck.lazy(this.adsSettingsProvider));
        injectNewsFeedService(homeFragment, DoubleCheck.lazy(this.newsFeedServiceProvider));
        injectNewsFeedAnalyticsHelper(homeFragment, DoubleCheck.lazy(this.newsFeedAnalyticsHelperProvider));
        injectConfigService(homeFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectUserPropertiesService(homeFragment, DoubleCheck.lazy(this.userPropertiesServiceProvider));
        injectPremiumService(homeFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectAdUnitService(homeFragment, DoubleCheck.lazy(this.adUnitServiceProvider));
        injectLocalSettingsService(homeFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectNutrientDashboardRenderer(homeFragment, DoubleCheck.lazy(this.nutrientDashboardRendererProvider));
        injectImageService(homeFragment, DoubleCheck.lazy(this.imageServiceProvider));
        injectGlobalSettingsService(homeFragment, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        injectChallengesService(homeFragment, DoubleCheck.lazy(this.challengesServiceProvider));
        injectDiaryService(homeFragment, DoubleCheck.lazy(this.diaryServiceProvider));
        injectDeepLinkManager(homeFragment, DoubleCheck.lazy(this.deepLinkManagerProvider));
        injectAppRatingService(homeFragment, DoubleCheck.lazy(this.appRatingServiceProvider));
        injectMessageService(homeFragment, DoubleCheck.lazy(this.messageServiceProvider));
        injectUserApplicationSettingsService(homeFragment, DoubleCheck.lazy(this.userApplicationSettingsServiceProvider));
        injectEmailVerificationManager(homeFragment, DoubleCheck.lazy(this.emailVerificationManagerProvider));
        injectLocationService(homeFragment, DoubleCheck.lazy(this.locationServiceProvider));
        injectAchievementInterstitialAd(homeFragment, (AchievementInterstitialAd) this.achievementInterstitialAdProvider.get());
        injectAchievementAdManager(homeFragment, DoubleCheck.lazy(this.achievementAdManagerProvider));
        injectPremiumOnboardingAnalyticsHelper(homeFragment, DoubleCheck.lazy(this.premiumOnboardingAnalyticsHelperProvider));
    }

    public static void injectAdsSettings(HomeFragment homeFragment, Lazy<AdsSettings> lazy) {
        homeFragment.adsSettings = lazy;
    }

    public static void injectNewsFeedService(HomeFragment homeFragment, Lazy<NewsFeedService> lazy) {
        homeFragment.newsFeedService = lazy;
    }

    public static void injectNewsFeedAnalyticsHelper(HomeFragment homeFragment, Lazy<NewsFeedAnalyticsHelper> lazy) {
        homeFragment.newsFeedAnalyticsHelper = lazy;
    }

    public static void injectConfigService(HomeFragment homeFragment, Lazy<ConfigService> lazy) {
        homeFragment.configService = lazy;
    }

    public static void injectUserPropertiesService(HomeFragment homeFragment, Lazy<UserPropertiesService> lazy) {
        homeFragment.userPropertiesService = lazy;
    }

    public static void injectPremiumService(HomeFragment homeFragment, Lazy<PremiumService> lazy) {
        homeFragment.premiumService = lazy;
    }

    public static void injectAdUnitService(HomeFragment homeFragment, Lazy<AdUnitService> lazy) {
        homeFragment.adUnitService = lazy;
    }

    public static void injectLocalSettingsService(HomeFragment homeFragment, Lazy<LocalSettingsService> lazy) {
        homeFragment.localSettingsService = lazy;
    }

    public static void injectNutrientDashboardRenderer(HomeFragment homeFragment, Lazy<NutrientDashboardRenderer> lazy) {
        homeFragment.nutrientDashboardRenderer = lazy;
    }

    public static void injectImageService(HomeFragment homeFragment, Lazy<ImageService> lazy) {
        homeFragment.imageService = lazy;
    }

    public static void injectGlobalSettingsService(HomeFragment homeFragment, Lazy<GlobalSettingsService> lazy) {
        homeFragment.globalSettingsService = lazy;
    }

    public static void injectChallengesService(HomeFragment homeFragment, Lazy<ChallengesService> lazy) {
        homeFragment.challengesService = lazy;
    }

    public static void injectDiaryService(HomeFragment homeFragment, Lazy<DiaryService> lazy) {
        homeFragment.diaryService = lazy;
    }

    public static void injectDeepLinkManager(HomeFragment homeFragment, Lazy<DeepLinkManager> lazy) {
        homeFragment.deepLinkManager = lazy;
    }

    public static void injectAppRatingService(HomeFragment homeFragment, Lazy<AppRatingService> lazy) {
        homeFragment.appRatingService = lazy;
    }

    public static void injectMessageService(HomeFragment homeFragment, Lazy<MessageService> lazy) {
        homeFragment.messageService = lazy;
    }

    public static void injectUserApplicationSettingsService(HomeFragment homeFragment, Lazy<UserApplicationSettingsService> lazy) {
        homeFragment.userApplicationSettingsService = lazy;
    }

    public static void injectEmailVerificationManager(HomeFragment homeFragment, Lazy<UacfEmailVerificationManager> lazy) {
        homeFragment.emailVerificationManager = lazy;
    }

    public static void injectLocationService(HomeFragment homeFragment, Lazy<LocationService> lazy) {
        homeFragment.locationService = lazy;
    }

    public static void injectAchievementInterstitialAd(HomeFragment homeFragment, AchievementInterstitialAd achievementInterstitialAd) {
        homeFragment.achievementInterstitialAd = achievementInterstitialAd;
    }

    public static void injectAchievementAdManager(HomeFragment homeFragment, Lazy<AchievementAdManager> lazy) {
        homeFragment.achievementAdManager = lazy;
    }

    public static void injectPremiumOnboardingAnalyticsHelper(HomeFragment homeFragment, Lazy<PremiumOnboardingAnalyticsHelper> lazy) {
        homeFragment.premiumOnboardingAnalyticsHelper = lazy;
    }
}
