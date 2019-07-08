package io.uacf.core.auth;

import io.uacf.core.app.UacfUserAccountDomain;

public interface UacfAuthProvider {
    String getAccessToken();

    String getClientToken();

    UacfUserAccountDomain getDomain();

    String getDomainUserId();

    Long getLongUacfUserId();

    String getRefreshToken();

    @Deprecated
    String getUacfUserId();

    String getUserLocale();

    boolean isValidLoginSession();
}
