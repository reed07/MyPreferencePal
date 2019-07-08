package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.achievementinterstitialad.service.StoredAchievementEvents;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.notification.InAppNotificationManager;
import com.myfitnesspal.shared.notification.MfpNotificationHandler;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.session.UserImpl;
import com.myfitnesspal.shared.service.session.UserV2Service;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.configuration.sdk.UacfConfigurationSdk;
import javax.inject.Provider;

public final class SessionModule_ProvidesSessionFactory implements Factory<Session> {
    private final Provider<AchievementInterstitialAd> achievementInterstitialAdProvider;
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<GeoLocationService> geoLocationServiceProvider;
    private final Provider<InAppNotificationManager> inAppNotificationManagerProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final Provider<MfpNotificationHandler> mfpNotificationHandlerProvider;
    private final SessionModule module;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<PushNotificationManager> pushNotificationManagerProvider;
    private final Provider<StoredAchievementEvents> storedAchievementEventsProvider;
    private final Provider<UacfConfigurationSdk> uacfConfigurationSdkProvider;
    private final Provider<UserImpl> userProvider;
    private final Provider<UserSummaryService> userSummaryServiceProvider;
    private final Provider<UserV2Service> userV2ServiceProvider;

    public SessionModule_ProvidesSessionFactory(SessionModule sessionModule, Provider<Context> provider, Provider<UserImpl> provider2, Provider<LoginModel> provider3, Provider<AuthTokenProvider> provider4, Provider<AnalyticsService> provider5, Provider<GeoLocationService> provider6, Provider<UserSummaryService> provider7, Provider<DiaryService> provider8, Provider<LocalSettingsService> provider9, Provider<PushNotificationManager> provider10, Provider<MfpNotificationHandler> provider11, Provider<InAppNotificationManager> provider12, Provider<UserV2Service> provider13, Provider<UacfConfigurationSdk> provider14, Provider<NavigationHelper> provider15, Provider<StoredAchievementEvents> provider16, Provider<AchievementInterstitialAd> provider17) {
        this.module = sessionModule;
        this.contextProvider = provider;
        this.userProvider = provider2;
        this.loginModelProvider = provider3;
        this.authTokensProvider = provider4;
        this.analyticsProvider = provider5;
        this.geoLocationServiceProvider = provider6;
        this.userSummaryServiceProvider = provider7;
        this.diaryServiceProvider = provider8;
        this.localSettingsServiceProvider = provider9;
        this.pushNotificationManagerProvider = provider10;
        this.mfpNotificationHandlerProvider = provider11;
        this.inAppNotificationManagerProvider = provider12;
        this.userV2ServiceProvider = provider13;
        this.uacfConfigurationSdkProvider = provider14;
        this.navigationHelperProvider = provider15;
        this.storedAchievementEventsProvider = provider16;
        this.achievementInterstitialAdProvider = provider17;
    }

    public Session get() {
        SessionModule sessionModule = this.module;
        return provideInstance(sessionModule, this.contextProvider, this.userProvider, this.loginModelProvider, this.authTokensProvider, this.analyticsProvider, this.geoLocationServiceProvider, this.userSummaryServiceProvider, this.diaryServiceProvider, this.localSettingsServiceProvider, this.pushNotificationManagerProvider, this.mfpNotificationHandlerProvider, this.inAppNotificationManagerProvider, this.userV2ServiceProvider, this.uacfConfigurationSdkProvider, this.navigationHelperProvider, this.storedAchievementEventsProvider, this.achievementInterstitialAdProvider);
    }

    public static Session provideInstance(SessionModule sessionModule, Provider<Context> provider, Provider<UserImpl> provider2, Provider<LoginModel> provider3, Provider<AuthTokenProvider> provider4, Provider<AnalyticsService> provider5, Provider<GeoLocationService> provider6, Provider<UserSummaryService> provider7, Provider<DiaryService> provider8, Provider<LocalSettingsService> provider9, Provider<PushNotificationManager> provider10, Provider<MfpNotificationHandler> provider11, Provider<InAppNotificationManager> provider12, Provider<UserV2Service> provider13, Provider<UacfConfigurationSdk> provider14, Provider<NavigationHelper> provider15, Provider<StoredAchievementEvents> provider16, Provider<AchievementInterstitialAd> provider17) {
        return proxyProvidesSession(sessionModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13), DoubleCheck.lazy(provider14), DoubleCheck.lazy(provider15), DoubleCheck.lazy(provider16), DoubleCheck.lazy(provider17));
    }

    public static SessionModule_ProvidesSessionFactory create(SessionModule sessionModule, Provider<Context> provider, Provider<UserImpl> provider2, Provider<LoginModel> provider3, Provider<AuthTokenProvider> provider4, Provider<AnalyticsService> provider5, Provider<GeoLocationService> provider6, Provider<UserSummaryService> provider7, Provider<DiaryService> provider8, Provider<LocalSettingsService> provider9, Provider<PushNotificationManager> provider10, Provider<MfpNotificationHandler> provider11, Provider<InAppNotificationManager> provider12, Provider<UserV2Service> provider13, Provider<UacfConfigurationSdk> provider14, Provider<NavigationHelper> provider15, Provider<StoredAchievementEvents> provider16, Provider<AchievementInterstitialAd> provider17) {
        SessionModule_ProvidesSessionFactory sessionModule_ProvidesSessionFactory = new SessionModule_ProvidesSessionFactory(sessionModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17);
        return sessionModule_ProvidesSessionFactory;
    }

    public static Session proxyProvidesSession(SessionModule sessionModule, Context context, Lazy<UserImpl> lazy, Lazy<LoginModel> lazy2, Lazy<AuthTokenProvider> lazy3, Lazy<AnalyticsService> lazy4, Lazy<GeoLocationService> lazy5, Lazy<UserSummaryService> lazy6, Lazy<DiaryService> lazy7, Lazy<LocalSettingsService> lazy8, Lazy<PushNotificationManager> lazy9, Lazy<MfpNotificationHandler> lazy10, Lazy<InAppNotificationManager> lazy11, Lazy<UserV2Service> lazy12, Lazy<UacfConfigurationSdk> lazy13, Lazy<NavigationHelper> lazy14, Lazy<StoredAchievementEvents> lazy15, Lazy<AchievementInterstitialAd> lazy16) {
        return (Session) Preconditions.checkNotNull(sessionModule.providesSession(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16), "Cannot return null from a non-@Nullable @Provides method");
    }
}
