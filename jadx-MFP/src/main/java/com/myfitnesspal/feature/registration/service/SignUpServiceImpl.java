package com.myfitnesspal.feature.registration.service;

import com.myfitnesspal.feature.registration.api.RegionResponse;
import com.myfitnesspal.feature.registration.api.RegionResponse.API_RESPONSE_MAPPER;
import com.myfitnesspal.feature.registration.constants.SignUpBmi;
import com.myfitnesspal.feature.registration.exception.RegistrationError;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.IdmEmailUniquenessCheck;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.LoginModel.FacebookData;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.auth.SSO;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.model.v15.EmailUniquenessCheckObject;
import com.myfitnesspal.shared.model.v15.UsernameValidationObject;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.EmailUniquenessCheckRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.UsernameValidationRequestPacket;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.thirdparty.ThirdPartyService;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.identity.sdk.model.UacfAccountLink;
import com.uacf.identity.sdk.model.UacfUser;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import dagger.Lazy;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.app.UacfUserAccountDomain;
import java.util.List;
import javax.inject.Provider;

public class SignUpServiceImpl implements SignUpService {
    private final Lazy<AnalyticsService> analyticsService;
    private final Provider<MfpInformationApi> api;
    private final Lazy<AuthTokenProvider> authTokens;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<MeasurementsService> measurementsService;
    private final Lazy<PushNotificationManager> pushNotificationManager;
    private final Provider<MfpV2Api> regionServiceApi;
    private final Lazy<Session> session;
    private final Lazy<SyncService> syncService;
    private final Lazy<ThirdPartyService> thirdPartyService;

    public SignUpServiceImpl(Lazy<LocalSettingsService> lazy, Lazy<MeasurementsService> lazy2, Lazy<AnalyticsService> lazy3, Lazy<AuthTokenProvider> lazy4, Lazy<ThirdPartyService> lazy5, Lazy<SyncService> lazy6, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2, Lazy<Session> lazy7, Lazy<PushNotificationManager> lazy8, Lazy<DbConnectionManager> lazy9) {
        this.localSettingsService = lazy;
        this.measurementsService = lazy2;
        this.analyticsService = lazy3;
        this.authTokens = lazy4;
        this.thirdPartyService = lazy5;
        this.syncService = lazy6;
        this.api = provider;
        this.regionServiceApi = provider2;
        this.session = lazy7;
        this.pushNotificationManager = lazy8;
        this.dbConnectionManager = lazy9;
    }

    public EmailUniquenessCheckObject validateEmailAddressLegacy(String str) throws ApiException {
        return (EmailUniquenessCheckObject) ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new EmailUniquenessCheckRequestPacket(str))).post((ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(PacketTypes.EmailUniquenessCheckResponse), new Object[0]);
    }

    public IdmEmailUniquenessCheck validateEmailAddressIdm(String str) throws ApiException {
        try {
            List<UacfUser> findUserByEmailAddress = SSO.getSdk().findUserByEmailAddress(str);
            boolean z = false;
            boolean z2 = false;
            for (UacfUser uacfUser : findUserByEmailAddress) {
                UacfUserAccountDomain domain = uacfUser.getDomain();
                if (domain == UacfUserAccountDomain.UACF) {
                    z2 = true;
                } else if (domain == UacfUserAccountDomain.MFP) {
                    z = true;
                }
                for (UacfAccountLink domain2 : uacfUser.getAccountLinks()) {
                    UacfUserAccountDomain domain3 = domain2.getDomain();
                    if (domain3 == UacfUserAccountDomain.UACF) {
                        z2 = true;
                    } else if (domain3 == UacfUserAccountDomain.MFP) {
                        z = true;
                    }
                }
            }
            return new IdmEmailUniquenessCheck(findUserByEmailAddress, z, z2);
        } catch (UacfApiException e) {
            throw new ApiException(e);
        }
    }

    public String getRegionFromCountryCode(String str) throws ApiException {
        return ((RegionResponse) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.regionServiceApi.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.REGION_LOOKUP, Columns.COUNTRY_CODE, str)).getItem()).getName();
    }

    public void signUp(SignUpModel signUpModel, LoginModel loginModel) throws RegistrationException {
        try {
            ((SyncService) this.syncService.get()).abortAndClearQueue();
            FacebookData facebookData = loginModel.getFacebookData();
            ((Session) this.session.get()).logout();
            loginModel.setFacebookData(facebookData);
            if (ConnectivityUtil.isOnline()) {
                prepareUserAndServicesForSignUp(signUpModel);
                UserV1 userV1 = getUserV1();
                userV1.setUsername(signUpModel.getUsername());
                userV1.setEmail(signUpModel.getEmailAddress());
                userV1.markTOSAccepted();
                if (facebookData.isValid()) {
                    signUpModel.setPassword(facebookData.getUserId());
                    signUpModel.setEmailAddress(facebookData.getEmail());
                }
                UacfScheduleFinishedInfo enqueueAndWait = ((SyncService) this.syncService.get()).enqueueAndWait(SyncType.SignUp);
                if (enqueueAndWait.isSuccessful()) {
                    if (facebookData.isValid()) {
                        ((ThirdPartyService) this.thirdPartyService.get()).associate(1, facebookData.getUserId(), facebookData.getAccessToken());
                    }
                    onSignUpCompleted(signUpModel, loginModel);
                    return;
                }
                reportFailureExceptions("SignUpServiceImpl.signUp()", signUpModel);
                Ln.e(enqueueAndWait.getLastError());
                throw new RegistrationException(RegistrationError.SyncFailure, enqueueAndWait.getLastError());
            }
            throw new RegistrationException(RegistrationError.DeviceOffline);
        } catch (RegistrationException e) {
            onSignUpFailed(loginModel);
            throw e;
        } catch (Exception e2) {
            onSignUpFailed(loginModel);
            throw new RegistrationException((Throwable) e2);
        }
    }

    public UsernameValidationObject validateUsername(String str) throws ApiException {
        return (UsernameValidationObject) ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new UsernameValidationRequestPacket(str))).post((ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(PacketTypes.UsernameValidationResponse), new Object[0]);
    }

    private void onSignUpFailed(LoginModel loginModel) {
        ((AuthTokenProvider) this.authTokens.get()).logout();
        loginModel.setUsername(null);
        loginModel.setLastUsername(null);
        resetUser();
    }

    /* access modifiers changed from: protected */
    public void onSignUpCompleted(SignUpModel signUpModel, LoginModel loginModel) {
        UserV1 userV1 = getUserV1();
        ((DbConnectionManager) this.dbConnectionManager.get()).usersDbAdapter().saveUser(userV1);
        addInitialWeightEntry();
        loginModel.setUsername(userV1.getEmail());
        loginModel.setLastUsername(userV1.getEmail());
        ((LocalSettingsService) this.localSettingsService.get()).setProgressPhotosIntroDisplayed(true);
        reportSignUpBmi(signUpModel);
        signUpModel.clear();
        if (PushNotificationManager.pushNotificationsEnabled()) {
            ((PushNotificationManager) this.pushNotificationManager.get()).registerUserForFCM();
        }
        ((AnalyticsService) this.analyticsService.get()).restartSession();
        ((AnalyticsService) this.analyticsService.get()).reportSessionStart();
        ((AnalyticsService) this.analyticsService.get()).reportRegistration();
    }

    private void reportSignUpBmi(SignUpModel signUpModel) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(signUpModel.getBmiType() == SignUpBmi.Under ? Events.ED_SIGNUP_GOAL_WEIGHT_SET_UNDERWEIGHT_BMI : Events.ED_SIGNUP_GOAL_WEIGHT_SET_NORMAL_BMI);
    }

    private UserV1 getUserV1() {
        return ((Session) this.session.get()).getUser().getUserV1();
    }

    private void prepareUserAndServicesForSignUp(SignUpModel signUpModel) throws RegistrationException {
        try {
            resetUser();
            signUpModel.writeAllToServices();
            getUserV1().recalculateGoals();
        } catch (Exception e) {
            throw new RegistrationException(RegistrationError.DatabaseError, e);
        }
    }

    private void resetUser() {
        UserV1 userV1 = getUserV1();
        if (userV1 == null) {
            userV1 = new UserV1();
            ((Session) this.session.get()).getUser().setUserV1(userV1);
        }
        userV1.resetToDefault();
    }

    private void addInitialWeightEntry() {
        ((MeasurementsService) this.measurementsService.get()).insertOrUpdateMeasurementForToday(Measurement.WEIGHT, getUserV1().getProfile().getCurrentWeight().getPounds());
    }

    public static void reportFailureExceptions(String str, SignUpModel signUpModel) {
        try {
            String strings = Strings.toString(str);
            Exception exc = new Exception(String.format("SIGN_UP_SYNC_FAILURE %s uacfId=%s, username=%s, email=%s", new Object[]{strings, Strings.toString(signUpModel.getUacfUserId()), Strings.toString(signUpModel.getUsername()), Strings.toString(signUpModel.getEmailAddress())}));
            exc.fillInStackTrace();
            Ln.e(exc);
            StringBuilder sb = new StringBuilder();
            sb.append("SIGN_UP_SYNC_FAILURE_DETAILS ");
            sb.append(strings);
            sb.append(" ");
            sb.append(signUpModel.toJson());
            Exception exc2 = new Exception(sb.toString());
            exc2.fillInStackTrace();
            Ln.e(exc2);
        } catch (Exception e) {
            Ln.e(e);
        }
    }
}
