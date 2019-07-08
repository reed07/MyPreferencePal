package com.myfitnesspal.shared.service.session;

import android.content.Context;
import android.content.Intent;
import com.myfitnesspal.feature.achievementinterstitialad.service.StoredAchievementEvents;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.LoginModel.FacebookData;
import com.myfitnesspal.feature.registration.ui.activity.LoginActivity;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.notification.InAppNotificationManager;
import com.myfitnesspal.shared.notification.MfpNotificationHandler;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.provider.MPFAppWidgetProvider;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import io.uacf.configuration.sdk.UacfConfigurationSdk;

public class SessionImpl extends SimpleAsyncServiceBase implements Session {
    private final Lazy<AchievementInterstitialAd> achievementInterstitialAd;
    private final Lazy<AnalyticsService> analytics;
    private final Lazy<AuthTokenProvider> authTokens;
    private final Function1<Boolean> completion = new Function1() {
        public final void execute(Object obj) {
            SessionImpl.lambda$new$0(SessionImpl.this, (Boolean) obj);
        }
    };
    private final Context context;
    private final Lazy<DiaryService> diaryService;
    private final Lazy<GeoLocationService> geoLocationService;
    private final Lazy<InAppNotificationManager> inAppNotificationManager;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<LoginModel> loginModel;
    private final Lazy<MfpNotificationHandler> mfpNotificationHandler;
    private final Lazy<NavigationHelper> navigationHelper;
    private final Lazy<PushNotificationManager> pushNotificationManager;
    private final Lazy<StoredAchievementEvents> storedAchievementEvents;
    private final Lazy<UacfConfigurationSdk> uacfConfigurationSdk;
    private final Lazy<UserImpl> user;
    private final Lazy<UserSummaryService> userSummaryService;
    private final Lazy<UserV2Service> userV2Service;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 3;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "SessionImpl";
    }

    public SessionImpl(Context context2, Lazy<LoginModel> lazy, Lazy<UserImpl> lazy2, Lazy<AuthTokenProvider> lazy3, Lazy<AnalyticsService> lazy4, Lazy<GeoLocationService> lazy5, Lazy<UserSummaryService> lazy6, Lazy<DiaryService> lazy7, Lazy<LocalSettingsService> lazy8, Lazy<PushNotificationManager> lazy9, Lazy<MfpNotificationHandler> lazy10, Lazy<InAppNotificationManager> lazy11, Lazy<UserV2Service> lazy12, Lazy<UacfConfigurationSdk> lazy13, Lazy<NavigationHelper> lazy14, Lazy<StoredAchievementEvents> lazy15, Lazy<AchievementInterstitialAd> lazy16) {
        this.context = context2;
        this.loginModel = lazy;
        this.user = lazy2;
        this.authTokens = lazy3;
        this.analytics = lazy4;
        this.geoLocationService = lazy5;
        this.userSummaryService = lazy6;
        this.diaryService = lazy7;
        this.localSettingsService = lazy8;
        this.pushNotificationManager = lazy9;
        this.mfpNotificationHandler = lazy10;
        this.inAppNotificationManager = lazy11;
        this.userV2Service = lazy12;
        this.uacfConfigurationSdk = lazy13;
        this.navigationHelper = lazy14;
        this.storedAchievementEvents = lazy15;
        this.achievementInterstitialAd = lazy16;
    }

    public static /* synthetic */ void lambda$new$0(SessionImpl sessionImpl, Boolean bool) throws RuntimeException {
        if (bool.booleanValue()) {
            ((NavigationHelper) sessionImpl.navigationHelper.get()).withContext(sessionImpl.context).finishActivityAfterNavigation().withClearTopAndNewTask().withIntent(LoginActivity.newStartIntent(sessionImpl.context)).startActivity();
        }
    }

    public User getUser() {
        return (User) this.user.get();
    }

    public void logoutAndNavigateToLoginActivity() {
        async(new Runnable() {
            public final void run() {
                SessionImpl.lambda$logoutAndNavigateToLoginActivity$1(SessionImpl.this);
            }
        });
    }

    public static /* synthetic */ void lambda$logoutAndNavigateToLoginActivity$1(SessionImpl sessionImpl) {
        try {
            sessionImpl.logout();
            sessionImpl.postToMainThread(sessionImpl.completion, Boolean.valueOf(true));
        } catch (Exception e) {
            Ln.d(e, "failed to sign out", new Object[0]);
            sessionImpl.postToMainThread(sessionImpl.completion, Boolean.valueOf(false));
        }
    }

    public synchronized void logoutAndKeepFacebookData() {
        FacebookData facebookData = ((LoginModel) this.loginModel.get()).getFacebookData();
        logout();
        ((LoginModel) this.loginModel.get()).setFacebookData(facebookData);
    }

    public synchronized void logout() {
        boolean isLoggedIn = getUser().isLoggedIn();
        getUser().getMasterDatabaseId();
        if (isLoggedIn) {
            ((AnalyticsService) this.analytics.get()).reportLogout(((LoginModel) this.loginModel.get()).getFacebookData().isValid() ? "facebook" : "username");
        }
        if (isLoggedIn) {
            ((PushNotificationManager) this.pushNotificationManager.get()).unregisterUserFromFCM();
        }
        ((UserSummaryService) this.userSummaryService.get()).clearCache();
        ((MfpNotificationHandler) this.mfpNotificationHandler.get()).cancelAllNotifications();
        ((InAppNotificationManager) this.inAppNotificationManager.get()).clearInAppNotifications();
        ((LocalSettingsService) this.localSettingsService.get()).setLastLoginDayNumber(-1);
        ((DiaryService) this.diaryService.get()).clearDiaryDayCache();
        ((AuthTokenProvider) this.authTokens.get()).logout();
        ((AnalyticsService) this.analytics.get()).restartSession();
        ((GeoLocationService) this.geoLocationService.get()).reset();
        ((UacfConfigurationSdk) this.uacfConfigurationSdk.get()).clearConfiguration();
        ((LoginModel) this.loginModel.get()).logout();
        ((UserV2Service) this.userV2Service.get()).logout();
        ((StoredAchievementEvents) this.storedAchievementEvents.get()).clearStoredEvents();
        ((AchievementInterstitialAd) this.achievementInterstitialAd.get()).resetLoadedAd();
        ((UserImpl) this.user.get()).setUserV1(null);
        ((UserImpl) this.user.get()).setUserV2(null);
        Intent intent = new Intent(this.context, MPFAppWidgetProvider.class);
        intent.setAction(MPFAppWidgetProvider.ACTION_SIGNED_OUT);
        this.context.sendBroadcast(intent);
    }
}
