package com.myfitnesspal.feature.registration.service;

import android.database.sqlite.SQLiteException;
import android.util.Patterns;
import com.myfitnesspal.feature.registration.exception.RegistrationError;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.LoginModel.FacebookData;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.auth.SSO;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.notification.JobServiceFactory;
import com.myfitnesspal.shared.notification.JobServiceFactory.Job;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.thirdparty.ThirdPartyService;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.myfitnesspal.shared.util.UserV1Utils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import dagger.Lazy;

public class SignInServiceImpl implements SignInService {
    private Lazy<AnalyticsService> analyticsService;
    private Lazy<MfpApiSettings> apiSettings;
    private Lazy<AuthTokenProvider> authTokens;
    private Lazy<DbConnectionManager> dbConnectionManager;
    private Lazy<JobServiceFactory> jobServiceFactory;
    private Lazy<LocalSettingsService> localSettingsService;
    private Lazy<LoginModel> loginModel;
    private Lazy<PushNotificationManager> pushNotificationManager;
    private Lazy<Session> session;
    private Lazy<SyncService> syncService;
    private Lazy<ThirdPartyService> thirdPartyService;

    public SignInServiceImpl(Lazy<LoginModel> lazy, Lazy<Session> lazy2, Lazy<MfpApiSettings> lazy3, Lazy<SyncService> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<AnalyticsService> lazy6, Lazy<AuthTokenProvider> lazy7, Lazy<ThirdPartyService> lazy8, Lazy<PushNotificationManager> lazy9, Lazy<DbConnectionManager> lazy10, Lazy<JobServiceFactory> lazy11) {
        this.loginModel = lazy;
        this.session = lazy2;
        this.apiSettings = lazy3;
        this.syncService = lazy4;
        this.localSettingsService = lazy5;
        this.analyticsService = lazy6;
        this.authTokens = lazy7;
        this.thirdPartyService = lazy8;
        this.pushNotificationManager = lazy9;
        this.dbConnectionManager = lazy10;
        this.jobServiceFactory = lazy11;
    }

    public void signInToFacebook(String str, FacebookData facebookData) throws RegistrationException {
        ((LoginModel) this.loginModel.get()).setSignInAuthType("facebook");
        try {
            ((Session) this.session.get()).logoutAndKeepFacebookData();
            String userId = facebookData.getUserId();
            String accessToken = facebookData.getAccessToken();
            throwIfAccessTokenInvalid(((AuthTokenProvider) this.authTokens.get()).loginWithFacebook(userId, accessToken));
            throwIfNoVerticalAccountExists();
            ((LoginModel) this.loginModel.get()).setFacebookUserIdAndToken(userId, accessToken);
            onAuthTokensObtained(str);
            ((ThirdPartyService) this.thirdPartyService.get()).associate(1, facebookData.getUserId(), facebookData.getAccessToken());
            ((AnalyticsService) this.analyticsService.get()).reportLogin("facebook");
            ((LoginModel) this.loginModel.get()).setLastUsername(null);
            ((LoginModel) this.loginModel.get()).setUsername(str);
        } catch (RegistrationException e) {
            throw e;
        } catch (ApiException e2) {
            throw new RegistrationException(RegistrationError.InvalidToken, e2);
        } catch (Exception e3) {
            throw new RegistrationException(RegistrationError.Unknown, e3);
        }
    }

    public void signIn(String str, String str2) throws RegistrationException {
        String authType = getAuthType(str);
        ((LoginModel) this.loginModel.get()).setSignInAuthType(authType);
        try {
            ((Session) this.session.get()).logout();
            throwIfAccessTokenInvalid(((AuthTokenProvider) this.authTokens.get()).login(Strings.toString(str), Strings.toString(str2)));
            throwIfNoVerticalAccountExists();
            ((LoginModel) this.loginModel.get()).clearFacebookData();
            onAuthTokensObtained(str);
            ((AnalyticsService) this.analyticsService.get()).restartSession();
            ((AnalyticsService) this.analyticsService.get()).reportLogin(authType);
            ((LoginModel) this.loginModel.get()).setLastUsername(str);
            ((LoginModel) this.loginModel.get()).setUsername(str);
        } catch (RegistrationException e) {
            throw e;
        } catch (ApiException e2) {
            throw new RegistrationException(RegistrationError.InvalidToken, e2);
        } catch (Exception e3) {
            throw new RegistrationException(RegistrationError.Unknown, e3);
        }
    }

    public boolean verifySignIn(String str, String str2) {
        try {
            verifyToken(((AuthTokenProvider) this.authTokens.get()).login(Strings.toString(str), Strings.toString(str2)));
            return true;
        } catch (ApiException unused) {
            return false;
        }
    }

    public boolean verifyFacebookSignIn(String str, String str2) {
        try {
            return verifyToken(((AuthTokenProvider) this.authTokens.get()).loginWithFacebook(str, str2));
        } catch (ApiException unused) {
            return false;
        }
    }

    private boolean verifyToken(String str) {
        try {
            throwIfAccessTokenInvalid(str);
            return true;
        } catch (RegistrationException unused) {
            return false;
        }
    }

    private String getAuthType(String str) {
        return Patterns.EMAIL_ADDRESS.matcher(str).matches() ? "email" : "username";
    }

    private void throwIfAccessTokenInvalid(String str) throws RegistrationException {
        if (Strings.isEmpty(str)) {
            throw new RegistrationException(RegistrationError.InvalidToken, null);
        }
    }

    private void throwIfNoVerticalAccountExists() throws RegistrationException {
        if (Strings.isEmpty(SSO.getDomainUserId(SSO.getSdk()))) {
            throw new RegistrationException(RegistrationError.NoVerticalAccount);
        }
    }

    private void onAuthTokensObtained(String str) throws RegistrationException {
        try {
            if (!ConnectivityUtil.isOffline().booleanValue()) {
                UserV1 loadUserOnCurrentThread = UserV1Utils.loadUserOnCurrentThread(str, (DbConnectionManager) this.dbConnectionManager.get());
                if (loadUserOnCurrentThread != null) {
                    ((Session) this.session.get()).getUser().setUserV1(loadUserOnCurrentThread);
                }
                runImportSyncAndThrowOnFailure();
                ((AnalyticsService) this.analyticsService.get()).reportUserId(((Session) this.session.get()).getUser().getUserId());
                ((JobServiceFactory) this.jobServiceFactory.get()).scheduleJob(Job.NOTIFICATION_IN_APP);
                if (PushNotificationManager.pushNotificationsEnabled()) {
                    ((PushNotificationManager) this.pushNotificationManager.get()).registerUserForFCM();
                    return;
                }
                return;
            }
            throw new RegistrationException(RegistrationError.DeviceOffline);
        } catch (SQLiteException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Login.java An exception occurred while attempting to login()");
            sb.append(e.getMessage());
            Ln.e(e, sb.toString(), new Object[0]);
            throw new RegistrationException(RegistrationError.DatabaseError);
        }
    }

    private void runImportSyncAndThrowOnFailure() throws RegistrationException {
        UacfScheduleFinishedInfo enqueueAndWait = ((SyncService) this.syncService.get()).enqueueAndWait(SyncType.SignIn);
        if (!enqueueAndWait.isSuccessful()) {
            UacfScheduleException lastError = enqueueAndWait.getLastError();
            if (lastError != null && lastError.getStatusCode() == 2) {
                ((Session) this.session.get()).logout();
            }
            throw new RegistrationException(RegistrationError.SyncFailure, lastError);
        }
        ((LocalSettingsService) this.localSettingsService.get()).setProgressPhotosIntroDisplayed(true);
        ((AnalyticsService) this.analyticsService.get()).reportSessionStart();
    }
}
