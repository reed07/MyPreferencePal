package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.myfitnesspal.feature.achievementinterstitialad.service.StoredAchievementEvents;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.auth.LegacyAuthTokenProvider;
import com.myfitnesspal.shared.api.auth.LegacyAuthTokenStore;
import com.myfitnesspal.shared.api.auth.SSOAuthTokenProvider;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.notification.InAppNotificationManager;
import com.myfitnesspal.shared.notification.MfpNotificationHandler;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBotAuthTokenProvider;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.session.SessionImpl;
import com.myfitnesspal.shared.service.session.UserImpl;
import com.myfitnesspal.shared.service.session.UserV2Service;
import com.myfitnesspal.shared.service.session.UserV2ServiceImpl;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import io.uacf.configuration.sdk.UacfConfigurationSdk;
import java.util.UUID;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

@Module
public class SessionModule {
    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UserImpl provideUserImpl(Lazy<AppIndexerBot> lazy, Lazy<UserV2Service> lazy2, Lazy<LoginModel> lazy3, Lazy<PremiumService> lazy4, DbConnectionManager dbConnectionManager) {
        UserImpl userImpl = new UserImpl(lazy, lazy2, lazy3, lazy4, dbConnectionManager);
        return userImpl;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public Session providesSession(Context context, Lazy<UserImpl> lazy, Lazy<LoginModel> lazy2, Lazy<AuthTokenProvider> lazy3, Lazy<AnalyticsService> lazy4, Lazy<GeoLocationService> lazy5, Lazy<UserSummaryService> lazy6, Lazy<DiaryService> lazy7, Lazy<LocalSettingsService> lazy8, Lazy<PushNotificationManager> lazy9, Lazy<MfpNotificationHandler> lazy10, Lazy<InAppNotificationManager> lazy11, Lazy<UserV2Service> lazy12, Lazy<UacfConfigurationSdk> lazy13, Lazy<NavigationHelper> lazy14, Lazy<StoredAchievementEvents> lazy15, Lazy<AchievementInterstitialAd> lazy16) {
        SessionImpl sessionImpl = new SessionImpl(context, lazy2, lazy, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16);
        return sessionImpl;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UserV2Service providesUserService(Context context, Lazy<UserImpl> lazy, DbConnectionManager dbConnectionManager, Provider<MfpV2Api> provider, Session session) {
        UserV2ServiceImpl userV2ServiceImpl = new UserV2ServiceImpl(context, lazy, dbConnectionManager, provider, session);
        return userV2ServiceImpl;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public LegacyAuthTokenStore provideOAuthTokenStore(@Named("app-settings") SharedPreferences sharedPreferences) {
        return new LegacyAuthTokenStore(sharedPreferences);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public AuthTokenProvider provideAuthTokenProvider(Context context, Lazy<MfpAnalyticsService> lazy, Lazy<Session> lazy2, AppIndexerBot appIndexerBot, Provider<MfpV2Api> provider, LegacyAuthTokenStore legacyAuthTokenStore, @Named("client_id") String str, @Named("deviceUUID") UUID uuid) {
        if (appIndexerBot.isActive()) {
            return new AppIndexerBotAuthTokenProvider();
        }
        String str2 = str;
        LegacyAuthTokenProvider legacyAuthTokenProvider = new LegacyAuthTokenProvider(lazy2, provider, legacyAuthTokenStore, str2, uuid);
        SSOAuthTokenProvider sSOAuthTokenProvider = new SSOAuthTokenProvider(context.getApplicationContext(), lazy2, lazy, str2, provider, legacyAuthTokenProvider, legacyAuthTokenStore, uuid);
        return sSOAuthTokenProvider;
    }
}
