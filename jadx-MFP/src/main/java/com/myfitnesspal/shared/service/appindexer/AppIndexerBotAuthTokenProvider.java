package com.myfitnesspal.shared.service.appindexer;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import io.uacf.core.app.UacfUserAccountDomain;

public class AppIndexerBotAuthTokenProvider implements AuthTokenProvider {
    public String getAccessToken() {
        return null;
    }

    public String getClientToken() {
        return null;
    }

    public String getDeviceId() {
        return null;
    }

    public UacfUserAccountDomain getDomain() {
        return null;
    }

    public String getDomainUserId() {
        return null;
    }

    public Long getLongUacfUserId() {
        return null;
    }

    public String getRefreshToken() {
        return null;
    }

    public String getUacfUserId() {
        return null;
    }

    public String getUserLocale() {
        return null;
    }

    public boolean isValidLoginSession() {
        return false;
    }

    public String login(String str, String str2) throws ApiException {
        return null;
    }

    public String loginWithFacebook(String str, String str2) throws ApiException {
        return null;
    }

    public void logout() {
    }

    public void refreshAccessToken() throws ApiException {
    }
}
