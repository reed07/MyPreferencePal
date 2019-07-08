package com.myfitnesspal.shared.api.auth;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.exception.HttpAuthFailureRetryException;
import com.myfitnesspal.shared.api.response.AuthTokenResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.constants.SharedConstants.Uri;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import io.uacf.core.app.UacfUserAccountDomain;
import java.util.ArrayList;
import java.util.UUID;
import javax.inject.Provider;

public class LegacyAuthTokenProvider implements AuthTokenProvider {
    private final Provider<MfpV2Api> authApi;
    private final String clientId;
    private final UUID deviceId;
    private final Lazy<Session> session;
    private final LegacyAuthTokenStore store;

    public String getClientToken() {
        return "";
    }

    public Long getLongUacfUserId() {
        return null;
    }

    public String getUacfUserId() {
        return "";
    }

    public String getUserLocale() {
        return "en-US";
    }

    public LegacyAuthTokenProvider(Lazy<Session> lazy, Provider<MfpV2Api> provider, LegacyAuthTokenStore legacyAuthTokenStore, String str, UUID uuid) {
        this.session = lazy;
        this.authApi = provider;
        this.store = legacyAuthTokenStore;
        this.clientId = str;
        this.deviceId = uuid;
    }

    public void refreshAccessToken() throws ApiException {
        String deviceId2 = getDeviceId();
        if (Strings.notEmpty(deviceId2)) {
            AuthTokenResponse attemptRefresh = attemptRefresh(deviceId2);
            Ln.d("AUTH: refreshAccessToken got error %s, user ID %s", attemptRefresh.getError(), attemptRefresh.getObfuscatedUserId());
            persistTokenResponse(attemptRefresh);
            return;
        }
        try {
            AuthTokenResponse attemptRefresh2 = attemptRefresh("");
            Ln.d("AUTH: refreshAccessToken got error %s, user ID %s", attemptRefresh2.getError(), attemptRefresh2.getObfuscatedUserId());
            persistTokenResponse(attemptRefresh2);
        } catch (ApiException e) {
            if (e.getStatusCode() == 400) {
                AuthTokenResponse attemptRefresh3 = attemptRefresh(Strings.toString(this.deviceId));
                Ln.d("AUTH: refreshAccessToken got error %s, user ID %s", attemptRefresh3.getError(), attemptRefresh3.getObfuscatedUserId());
                persistTokenResponseAndWriteTokenRequestTriple(attemptRefresh3);
                return;
            }
            throw e;
        }
    }

    private AuthTokenResponse attemptRefresh(String str) throws ApiException {
        if (!Strings.isEmpty(this.store.getRefreshToken())) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Http.GRANT_TYPE);
            arrayList.add(Http.REFRESH_TOKEN);
            arrayList.add(Http.REFRESH_TOKEN);
            arrayList.add(this.store.getRefreshToken());
            arrayList.add("client_id");
            arrayList.add(this.clientId);
            if (Strings.notEmpty(str)) {
                arrayList.add("device_id");
                arrayList.add(str);
            }
            return (AuthTokenResponse) ((MfpV2Api) ((MfpV2Api) this.authApi.get()).withOutputType(AuthTokenResponse.class)).post(Uri.OAUTH2_TOKEN, arrayList.toArray(new String[arrayList.size()]));
        }
        throw new HttpAuthFailureRetryException("Auth failure. Retrying. ", 401);
    }

    public String getRefreshToken() {
        if (!this.store.isLoggedIn()) {
            return "";
        }
        return this.store.getRefreshToken();
    }

    public String getAccessToken() {
        if (!this.store.isLoggedIn()) {
            return "";
        }
        return this.store.getAccessToken();
    }

    public boolean isValidLoginSession() {
        return ((Session) this.session.get()).getUser().isLoggedIn() && this.store.isLoggedIn();
    }

    public String getDomainUserId() {
        return this.store.getUserId();
    }

    public UacfUserAccountDomain getDomain() {
        return UacfUserAccountDomain.MFP;
    }

    public String getDeviceId() {
        return this.store.getDeviceId();
    }

    public String login(String str, String str2) throws ApiException {
        Ln.d("AUTH: login, username=%s, password=%s", str, str2);
        String accessToken = getAccessToken();
        if (!Strings.isEmpty(accessToken)) {
            return accessToken;
        }
        Ln.d("AUTH: login, auth token empty, make /v2/oauth2/token call now", new Object[0]);
        AuthTokenResponse authTokenResponse = (AuthTokenResponse) ((MfpV2Api) ((MfpV2Api) this.authApi.get()).withOutputType(AuthTokenResponse.class)).post(Uri.OAUTH2_TOKEN, Http.GRANT_TYPE, "password", "username", str, "password", str2, "client_id", this.clientId, "device_id", Strings.toString(this.deviceId));
        Ln.d("AUTH: login, got response with error='%s' userId=%s", authTokenResponse.getError(), authTokenResponse.getObfuscatedUserId());
        persistTokenResponseAndWriteTokenRequestTriple(authTokenResponse);
        return authTokenResponse.getAccessToken();
    }

    public String loginWithFacebook(String str, String str2) throws ApiException {
        Ln.d("AUTH: loginWithFacebook, facebookUserId=%s, facebookToken=%s", str, str2);
        AuthTokenResponse authTokenResponse = (AuthTokenResponse) ((MfpV2Api) ((MfpV2Api) this.authApi.get()).withOutputType(AuthTokenResponse.class)).post(Uri.OAUTH2_TOKEN, Http.GRANT_TYPE, Http.FACEBOOK_TOKEN, "user_id", str, "access_token", str2, "client_id", this.clientId, "device_id", Strings.toString(this.deviceId));
        Ln.d("AUTH: loginWithFacebook, got response with error='%s' userId=%s", authTokenResponse.getError(), authTokenResponse.getObfuscatedUserId());
        persistTokenResponseAndWriteTokenRequestTriple(authTokenResponse);
        return authTokenResponse.getAccessToken();
    }

    public void logout() {
        this.store.clear();
    }

    private void persistTokenResponse(AuthTokenResponse authTokenResponse) {
        this.store.setAccessToken(authTokenResponse.getAccessToken());
        this.store.setRefreshToken(authTokenResponse.getRefreshToken());
        this.store.setUserId(authTokenResponse.getObfuscatedUserId());
        this.store.setDeviceId(this.deviceId);
    }

    private void persistTokenResponseAndWriteTokenRequestTriple(AuthTokenResponse authTokenResponse) {
        persistTokenResponse(authTokenResponse);
        this.store.setDeviceId(this.deviceId);
    }
}
