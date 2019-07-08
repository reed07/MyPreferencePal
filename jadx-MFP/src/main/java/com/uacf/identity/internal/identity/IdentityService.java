package com.uacf.identity.internal.identity;

import com.uacf.identity.internal.model.IdmAccountLink;
import com.uacf.identity.internal.model.IdmOAuthTokenInfo;
import com.uacf.identity.internal.model.IdmUser;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.app.UacfSocialNetworkProvider;
import java.util.List;

public interface IdentityService {
    IdmUser changePassword(String str) throws UacfApiException;

    IdmUser fetchCurrentUser() throws UacfApiException;

    List<IdmUser> findUserByEmail(String str) throws UacfApiException;

    IdmOAuthTokenInfo getCachedClientToken();

    IdmOAuthTokenInfo getCachedUserToken();

    IdmOAuthTokenInfo getClientToken() throws UacfApiException;

    IdmOAuthTokenInfo getUserToken() throws UacfApiException;

    void initiatePasswordReset(String str, String str2) throws UacfApiException;

    IdmOAuthTokenInfo obtainOAuthToken(UacfSocialNetworkProvider uacfSocialNetworkProvider, String str, String str2, String str3) throws UacfApiException;

    IdmOAuthTokenInfo obtainOAuthToken(String str, String str2) throws UacfApiException;

    IdmOAuthTokenInfo refreshUserToken() throws UacfApiException;

    void sendEvent(String str, Object obj);

    void sendVerificationEmail() throws UacfApiException;

    IdmOAuthTokenInfo storeTokenInfo(String str) throws UacfApiException;

    IdmUser updateAccount(Long l, String str, IdmAccountLink idmAccountLink) throws UacfApiException;
}
