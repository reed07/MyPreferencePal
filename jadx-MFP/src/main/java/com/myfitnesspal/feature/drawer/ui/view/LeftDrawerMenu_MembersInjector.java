package com.myfitnesspal.feature.drawer.ui.view;

import com.myfitnesspal.feature.blog.service.BlogService;
import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.notification.InAppNotificationManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class LeftDrawerMenu_MembersInjector implements MembersInjector<LeftDrawerMenu> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundJobHelperProvider;
    private final Provider<BlogService> blogServiceProvider;
    private final Provider<Bus> busProvider;
    private final Provider<CommunityService> communityServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<ConsentsAnalyticsHelper> consentsAnalyticsHelperProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<InAppNotificationManager> inAppNotificationManagerProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncEngineProvider;
    private final Provider<UserSummaryService> userSummaryServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public LeftDrawerMenu_MembersInjector(Provider<NavigationHelper> provider, Provider<InAppNotificationManager> provider2, Provider<AnalyticsService> provider3, Provider<UserSummaryService> provider4, Provider<Bus> provider5, Provider<UserWeightService> provider6, Provider<ConfigService> provider7, Provider<ActionTrackingService> provider8, Provider<BlogService> provider9, Provider<CommunityService> provider10, Provider<SyncService> provider11, Provider<Session> provider12, Provider<PremiumService> provider13, Provider<LocalSettingsService> provider14, Provider<GoogleFitClient> provider15, Provider<BackgroundJobHelper> provider16, Provider<ConsentsAnalyticsHelper> provider17) {
        this.navigationHelperProvider = provider;
        this.inAppNotificationManagerProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.userSummaryServiceProvider = provider4;
        this.busProvider = provider5;
        this.userWeightServiceProvider = provider6;
        this.configServiceProvider = provider7;
        this.actionTrackingServiceProvider = provider8;
        this.blogServiceProvider = provider9;
        this.communityServiceProvider = provider10;
        this.syncEngineProvider = provider11;
        this.sessionProvider = provider12;
        this.premiumServiceProvider = provider13;
        this.localSettingsServiceProvider = provider14;
        this.googleFitClientProvider = provider15;
        this.backgroundJobHelperProvider = provider16;
        this.consentsAnalyticsHelperProvider = provider17;
    }

    public static MembersInjector<LeftDrawerMenu> create(Provider<NavigationHelper> provider, Provider<InAppNotificationManager> provider2, Provider<AnalyticsService> provider3, Provider<UserSummaryService> provider4, Provider<Bus> provider5, Provider<UserWeightService> provider6, Provider<ConfigService> provider7, Provider<ActionTrackingService> provider8, Provider<BlogService> provider9, Provider<CommunityService> provider10, Provider<SyncService> provider11, Provider<Session> provider12, Provider<PremiumService> provider13, Provider<LocalSettingsService> provider14, Provider<GoogleFitClient> provider15, Provider<BackgroundJobHelper> provider16, Provider<ConsentsAnalyticsHelper> provider17) {
        LeftDrawerMenu_MembersInjector leftDrawerMenu_MembersInjector = new LeftDrawerMenu_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17);
        return leftDrawerMenu_MembersInjector;
    }

    public void injectMembers(LeftDrawerMenu leftDrawerMenu) {
        injectNavigationHelper(leftDrawerMenu, (NavigationHelper) this.navigationHelperProvider.get());
        injectInAppNotificationManager(leftDrawerMenu, DoubleCheck.lazy(this.inAppNotificationManagerProvider));
        injectAnalyticsService(leftDrawerMenu, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectUserSummaryService(leftDrawerMenu, DoubleCheck.lazy(this.userSummaryServiceProvider));
        injectBus(leftDrawerMenu, DoubleCheck.lazy(this.busProvider));
        injectUserWeightService(leftDrawerMenu, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectConfigService(leftDrawerMenu, DoubleCheck.lazy(this.configServiceProvider));
        injectActionTrackingService(leftDrawerMenu, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectBlogService(leftDrawerMenu, DoubleCheck.lazy(this.blogServiceProvider));
        injectCommunityService(leftDrawerMenu, DoubleCheck.lazy(this.communityServiceProvider));
        injectSyncEngine(leftDrawerMenu, DoubleCheck.lazy(this.syncEngineProvider));
        injectSession(leftDrawerMenu, DoubleCheck.lazy(this.sessionProvider));
        injectPremiumService(leftDrawerMenu, DoubleCheck.lazy(this.premiumServiceProvider));
        injectLocalSettingsService(leftDrawerMenu, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectGoogleFitClient(leftDrawerMenu, DoubleCheck.lazy(this.googleFitClientProvider));
        injectBackgroundJobHelper(leftDrawerMenu, DoubleCheck.lazy(this.backgroundJobHelperProvider));
        injectConsentsAnalyticsHelper(leftDrawerMenu, DoubleCheck.lazy(this.consentsAnalyticsHelperProvider));
    }

    public static void injectNavigationHelper(LeftDrawerMenu leftDrawerMenu, NavigationHelper navigationHelper) {
        leftDrawerMenu.navigationHelper = navigationHelper;
    }

    public static void injectInAppNotificationManager(LeftDrawerMenu leftDrawerMenu, Lazy<InAppNotificationManager> lazy) {
        leftDrawerMenu.inAppNotificationManager = lazy;
    }

    public static void injectAnalyticsService(LeftDrawerMenu leftDrawerMenu, Lazy<AnalyticsService> lazy) {
        leftDrawerMenu.analyticsService = lazy;
    }

    public static void injectUserSummaryService(LeftDrawerMenu leftDrawerMenu, Lazy<UserSummaryService> lazy) {
        leftDrawerMenu.userSummaryService = lazy;
    }

    public static void injectBus(LeftDrawerMenu leftDrawerMenu, Lazy<Bus> lazy) {
        leftDrawerMenu.bus = lazy;
    }

    public static void injectUserWeightService(LeftDrawerMenu leftDrawerMenu, Lazy<UserWeightService> lazy) {
        leftDrawerMenu.userWeightService = lazy;
    }

    public static void injectConfigService(LeftDrawerMenu leftDrawerMenu, Lazy<ConfigService> lazy) {
        leftDrawerMenu.configService = lazy;
    }

    public static void injectActionTrackingService(LeftDrawerMenu leftDrawerMenu, Lazy<ActionTrackingService> lazy) {
        leftDrawerMenu.actionTrackingService = lazy;
    }

    public static void injectBlogService(LeftDrawerMenu leftDrawerMenu, Lazy<BlogService> lazy) {
        leftDrawerMenu.blogService = lazy;
    }

    public static void injectCommunityService(LeftDrawerMenu leftDrawerMenu, Lazy<CommunityService> lazy) {
        leftDrawerMenu.communityService = lazy;
    }

    public static void injectSyncEngine(LeftDrawerMenu leftDrawerMenu, Lazy<SyncService> lazy) {
        leftDrawerMenu.syncEngine = lazy;
    }

    public static void injectSession(LeftDrawerMenu leftDrawerMenu, Lazy<Session> lazy) {
        leftDrawerMenu.session = lazy;
    }

    public static void injectPremiumService(LeftDrawerMenu leftDrawerMenu, Lazy<PremiumService> lazy) {
        leftDrawerMenu.premiumService = lazy;
    }

    public static void injectLocalSettingsService(LeftDrawerMenu leftDrawerMenu, Lazy<LocalSettingsService> lazy) {
        leftDrawerMenu.localSettingsService = lazy;
    }

    public static void injectGoogleFitClient(LeftDrawerMenu leftDrawerMenu, Lazy<GoogleFitClient> lazy) {
        leftDrawerMenu.googleFitClient = lazy;
    }

    public static void injectBackgroundJobHelper(LeftDrawerMenu leftDrawerMenu, Lazy<BackgroundJobHelper> lazy) {
        leftDrawerMenu.backgroundJobHelper = lazy;
    }

    public static void injectConsentsAnalyticsHelper(LeftDrawerMenu leftDrawerMenu, Lazy<ConsentsAnalyticsHelper> lazy) {
        leftDrawerMenu.consentsAnalyticsHelper = lazy;
    }
}
