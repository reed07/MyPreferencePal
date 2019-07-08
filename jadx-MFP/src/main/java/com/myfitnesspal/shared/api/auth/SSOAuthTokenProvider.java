package com.myfitnesspal.shared.api.auth;

import android.content.Context;
import android.os.Looper;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.SharedConstants.Uri;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.identity.sdk.model.UacfVerticalAccountInfo;
import dagger.Lazy;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.app.UacfSocialNetworkProvider;
import io.uacf.core.app.UacfUserAccountDomain;
import java.util.UUID;
import javax.inject.Provider;

public class SSOAuthTokenProvider implements AuthTokenProvider {
    private static final Object CREDENTIAL_MIGRATION_LOCK = new Object();
    private static final String EVENT_MIGRATE_LEGACY_TO_IDM_FAIL = "migrate_legacy_to_idm_fail";
    private static final String EVENT_MIGRATE_LEGACY_TO_IDM_SUCCESS = "migrate_legacy_to_idm_success";
    private static final String KEY_CLIENT_ID = "client_id";
    private static final String KEY_CREDENTIAL = "credential";
    private static final String KEY_DEVICE_ID = "device_id";
    private static final String KEY_GRANT_TYPE = "grant_type";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PRINCIPAL = "principal";
    private static final String KEY_PROVIDER = "provider";
    private static final String KEY_USERNAME = "username";
    private static final String VALUE_ASSOCIATED_IDENTITY = "associated_identity";
    private static final String VALUE_EXCHANGE_TOKEN_PROVIDER = "idm_exchange_token";
    private static final String VALUE_GRANT_TYPE_PASSWORD = "password";
    private final Lazy<MfpAnalyticsService> analytics;
    private final Provider<MfpV2Api> api;
    private final String clientId;
    private final Context context;
    private final UUID deviceId;
    private final LegacyAuthTokenStore legacyCredentialStore;
    private final LegacyAuthTokenProvider legacyTokenProvider;
    private final UacfIdentitySdk sdk = SSO.getSdk();
    private final Lazy<Session> session;

    private static class TokenExchangeResponse {
        @Expose
        String accessToken;
        @Expose
        int expiresIn;
        @Expose
        String idToken;
        @Expose
        String refreshToken;

        private TokenExchangeResponse() {
        }

        public boolean isValid() {
            return Strings.notEmpty(this.accessToken) && Strings.notEmpty(this.idToken) && Strings.notEmpty(this.refreshToken) && this.expiresIn > 0;
        }
    }

    public SSOAuthTokenProvider(Context context2, Lazy<Session> lazy, Lazy<MfpAnalyticsService> lazy2, String str, Provider<MfpV2Api> provider, LegacyAuthTokenProvider legacyAuthTokenProvider, LegacyAuthTokenStore legacyAuthTokenStore, UUID uuid) {
        this.context = context2;
        this.analytics = lazy2;
        this.clientId = str;
        this.session = lazy;
        this.api = provider;
        this.legacyCredentialStore = legacyAuthTokenStore;
        this.legacyTokenProvider = legacyAuthTokenProvider;
        this.deviceId = uuid;
    }

    public void refreshAccessToken() throws ApiException {
        migrateLegacyCredentialsIfRequired();
        if (legacyCredentialsMigrated()) {
            try {
                this.sdk.refreshUserToken();
            } catch (UacfApiException e) {
                throw new ApiException(e);
            }
        } else {
            throw new ApiException("credential migration failed", 401);
        }
    }

    public String login(String str, String str2) throws ApiException {
        try {
            if (!useAdminUserLogin(str)) {
                return this.sdk.login(str, str2);
            }
            return this.sdk.loginWithToken(getIdmTokenForAdminLogin(str, str2).accessToken);
        } catch (UacfApiException e) {
            throw new ApiException(e);
        }
    }

    public String loginWithFacebook(String str, String str2) throws ApiException {
        try {
            return Strings.toString(this.sdk.login(UacfSocialNetworkProvider.FACEBOOK, this.context.getString(R.string.facebook_app_id), str, str2));
        } catch (UacfApiException e) {
            throw new ApiException(e);
        }
    }

    public boolean isValidLoginSession() {
        return ((Session) this.session.get()).getUser().isLoggedIn() && legacyCredentialsMigrated() && Strings.notEmpty(getAccessToken()) && Strings.notEmpty(getRefreshToken()) && Strings.notEmpty(getDomainUserId()) && Strings.notEmpty(getDeviceId());
    }

    public String getRefreshToken() {
        if (!(Looper.getMainLooper().getThread() == Thread.currentThread())) {
            try {
                migrateLegacyCredentialsIfRequired();
            } catch (ApiException unused) {
            }
        }
        UacfVerticalAccountInfo currentUserAccount = this.sdk.getCurrentUserAccount();
        return currentUserAccount != null ? currentUserAccount.getRefreshToken() : "";
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0017 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001d A[Catch:{ UacfApiException -> 0x0038 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031 A[Catch:{ UacfApiException -> 0x0038 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAccessToken() {
        /*
            r3 = this;
            android.os.Looper r0 = android.os.Looper.getMainLooper()
            java.lang.Thread r0 = r0.getThread()
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r2 = 0
            if (r0 != r1) goto L_0x0011
            r0 = 1
            goto L_0x0012
        L_0x0011:
            r0 = 0
        L_0x0012:
            if (r0 != 0) goto L_0x0017
            r3.migrateLegacyCredentialsIfRequired()     // Catch:{ ApiException -> 0x0017 }
        L_0x0017:
            boolean r1 = r3.legacyCredentialsMigrated()     // Catch:{ UacfApiException -> 0x0038 }
            if (r1 == 0) goto L_0x0031
            if (r0 == 0) goto L_0x0026
            com.uacf.identity.sdk.UacfIdentitySdk r0 = r3.sdk     // Catch:{ UacfApiException -> 0x0038 }
            java.lang.String r0 = r0.getCachedUserToken()     // Catch:{ UacfApiException -> 0x0038 }
            goto L_0x002c
        L_0x0026:
            com.uacf.identity.sdk.UacfIdentitySdk r0 = r3.sdk     // Catch:{ UacfApiException -> 0x0038 }
            java.lang.String r0 = r0.getCurrentUserToken()     // Catch:{ UacfApiException -> 0x0038 }
        L_0x002c:
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ UacfApiException -> 0x0038 }
            return r0
        L_0x0031:
            com.myfitnesspal.shared.api.auth.LegacyAuthTokenStore r0 = r3.legacyCredentialStore     // Catch:{ UacfApiException -> 0x0038 }
            java.lang.String r0 = r0.getAccessToken()     // Catch:{ UacfApiException -> 0x0038 }
            return r0
        L_0x0038:
            r0 = move-exception
            java.lang.String r1 = "unable to obtain an access token from SSO for the current user."
            java.lang.Object[] r2 = new java.lang.Object[r2]
            com.uacf.core.util.Ln.e(r1, r2)
            com.uacf.core.util.Ln.e(r0)
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.api.auth.SSOAuthTokenProvider.getAccessToken():java.lang.String");
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0017 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001d A[Catch:{ UacfApiException -> 0x0034 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031 A[Catch:{ UacfApiException -> 0x0034 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getClientToken() {
        /*
            r3 = this;
            android.os.Looper r0 = android.os.Looper.getMainLooper()
            java.lang.Thread r0 = r0.getThread()
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r2 = 0
            if (r0 != r1) goto L_0x0011
            r0 = 1
            goto L_0x0012
        L_0x0011:
            r0 = 0
        L_0x0012:
            if (r0 != 0) goto L_0x0017
            r3.migrateLegacyCredentialsIfRequired()     // Catch:{ ApiException -> 0x0017 }
        L_0x0017:
            boolean r1 = r3.legacyCredentialsMigrated()     // Catch:{ UacfApiException -> 0x0034 }
            if (r1 == 0) goto L_0x0031
            if (r0 == 0) goto L_0x0026
            com.uacf.identity.sdk.UacfIdentitySdk r0 = r3.sdk     // Catch:{ UacfApiException -> 0x0034 }
            java.lang.String r0 = r0.getCachedClientToken()     // Catch:{ UacfApiException -> 0x0034 }
            goto L_0x002c
        L_0x0026:
            com.uacf.identity.sdk.UacfIdentitySdk r0 = r3.sdk     // Catch:{ UacfApiException -> 0x0034 }
            java.lang.String r0 = r0.getCurrentClientToken()     // Catch:{ UacfApiException -> 0x0034 }
        L_0x002c:
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ UacfApiException -> 0x0034 }
            return r0
        L_0x0031:
            java.lang.String r0 = ""
            return r0
        L_0x0034:
            r0 = move-exception
            java.lang.String r1 = "unable to obtain an access token from SSO for the current user."
            java.lang.Object[] r2 = new java.lang.Object[r2]
            com.uacf.core.util.Ln.e(r1, r2)
            com.uacf.core.util.Ln.e(r0)
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.api.auth.SSOAuthTokenProvider.getClientToken():java.lang.String");
    }

    public String getDomainUserId() {
        if (!legacyCredentialsMigrated()) {
            return this.legacyCredentialStore.getUserId();
        }
        String domainUserId = SSO.getDomainUserId(this.sdk);
        if (Strings.isEmpty(domainUserId)) {
            domainUserId = ((Session) this.session.get()).getUser().getUserId();
        }
        return domainUserId;
    }

    public UacfUserAccountDomain getDomain() {
        UacfVerticalAccountInfo currentUserAccount = this.sdk.getCurrentUserAccount();
        if (currentUserAccount != null) {
            return currentUserAccount.getDomain();
        }
        return null;
    }

    public String getUserLocale() {
        UacfVerticalAccountInfo currentUserAccount = this.sdk.getCurrentUserAccount();
        if (currentUserAccount != null) {
            return currentUserAccount.getUserLocale();
        }
        return null;
    }

    public String getUacfUserId() {
        if (!legacyCredentialsMigrated()) {
            return "";
        }
        return Strings.toString(this.sdk.getCurrentUserId());
    }

    public Long getLongUacfUserId() {
        return Long.valueOf(getUacfUserId());
    }

    public String getDeviceId() {
        return this.deviceId.toString();
    }

    public void logout() {
        try {
            this.sdk.logout();
            this.legacyCredentialStore.clear();
        } catch (UacfApiException e) {
            Ln.e("SSO SDK logout failed!", new Object[0]);
            Ln.e(e);
        }
    }

    public boolean legacyCredentialsMigrated() {
        return Strings.isEmpty(this.legacyCredentialStore.getUserId()) && Strings.isEmpty(this.legacyCredentialStore.getAccessToken());
    }

    private TokenExchangeResponse exchangeLegacyTokensForIdentityTokens() throws ApiException {
        return (TokenExchangeResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(TokenExchangeResponse.class)).withAuthTokenProvider(this.legacyTokenProvider)).post(Uri.OAUTH2_TOKEN, (Object[]) new String[]{"grant_type", VALUE_ASSOCIATED_IDENTITY, KEY_CREDENTIAL, this.legacyCredentialStore.getAccessToken(), KEY_PROVIDER, VALUE_EXCHANGE_TOKEN_PROVIDER, "client_id", this.clientId, KEY_PRINCIPAL, getDomainUserId()});
    }

    private TokenExchangeResponse getIdmTokenForAdminLogin(String str, String str2) throws ApiException {
        return (TokenExchangeResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(TokenExchangeResponse.class)).withAuthTokenProvider(this.legacyTokenProvider)).post(Uri.OAUTH2_TOKEN, (Object[]) new String[]{"password", str2, "device_id", getDeviceId(), "client_id", this.clientId, "username", str, "grant_type", "password"});
    }

    private boolean useAdminUserLogin(String str) {
        return Strings.notEmpty(str) && str.contains("::");
    }

    public void migrateLegacyCredentialsIfRequired() throws ApiException {
        synchronized (CREDENTIAL_MIGRATION_LOCK) {
            if (!legacyCredentialsMigrated()) {
                if (this.legacyTokenProvider.isValidLoginSession()) {
                    try {
                        TokenExchangeResponse exchangeLegacyTokensForIdentityTokens = exchangeLegacyTokensForIdentityTokens();
                        if (exchangeLegacyTokensForIdentityTokens.isValid()) {
                            this.sdk.loginWithToken(exchangeLegacyTokensForIdentityTokens.accessToken);
                            this.legacyCredentialStore.clear();
                            ((MfpAnalyticsService) this.analytics.get()).reportEvent(EVENT_MIGRATE_LEGACY_TO_IDM_SUCCESS);
                            return;
                        }
                        throw new ApiException("invalid token migration response", 999);
                    } catch (Exception e) {
                        ((MfpAnalyticsService) this.analytics.get()).reportEvent(EVENT_MIGRATE_LEGACY_TO_IDM_FAIL);
                        Ln.e("error migrating user to SSO!", new Object[0]);
                        Ln.e(e);
                        throw new ApiException("token migration failed", 555);
                    }
                }
            }
        }
    }
}
