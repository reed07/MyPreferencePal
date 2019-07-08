package com.myfitnesspal.shared.api.auth;

import com.myfitnesspal.shared.api.ApiException;
import io.uacf.core.auth.UacfAuthProvider;

public interface AuthTokenProvider extends UacfAuthProvider {
    String getDeviceId();

    String login(String str, String str2) throws ApiException;

    String loginWithFacebook(String str, String str2) throws ApiException;

    void logout();

    void refreshAccessToken() throws ApiException;
}
