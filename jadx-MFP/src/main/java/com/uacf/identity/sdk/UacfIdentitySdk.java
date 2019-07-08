package com.uacf.identity.sdk;

import android.support.annotation.NonNull;
import com.uacf.core.tracing.FSTraceableSdk;
import com.uacf.identity.sdk.model.UacfAccountLink;
import com.uacf.identity.sdk.model.UacfUser;
import com.uacf.identity.sdk.model.UacfVerticalAccountInfo;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.app.UacfSocialNetworkProvider;
import java.util.List;

public interface UacfIdentitySdk extends FSTraceableSdk<UacfIdentitySdk> {
    void changePassword(@NonNull String str) throws UacfApiException;

    List<UacfUser> findUserByEmailAddress(String str) throws UacfApiException;

    void forgotPassword(String str, String str2) throws UacfApiException;

    String getCachedClientToken();

    UacfUser getCachedCurrentUser();

    String getCachedUserToken();

    String getCurrentClientToken() throws UacfApiException;

    UacfVerticalAccountInfo getCurrentUserAccount();

    String getCurrentUserId();

    String getCurrentUserToken() throws UacfApiException;

    String login(UacfSocialNetworkProvider uacfSocialNetworkProvider, String str, String str2, String str3) throws UacfApiException;

    String login(String str, String str2) throws UacfApiException;

    String loginWithToken(String str) throws UacfApiException;

    void logout() throws UacfApiException;

    UacfUser refreshCurrentUser() throws UacfApiException;

    String refreshUserToken() throws UacfApiException;

    @Deprecated
    void sendVerificationEmail(String str) throws UacfApiException;

    UacfUser updateAccount(@NonNull String str, @NonNull UacfAccountLink uacfAccountLink) throws UacfApiException;
}
