package com.twitter.sdk.android.core.internal;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.scribe.DefaultScribeClient;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace.Builder;
import com.twitter.sdk.android.core.internal.scribe.TwitterCoreScribeClientHolder;
import com.twitter.sdk.android.core.services.AccountService;
import java.io.IOException;

public class TwitterSessionVerifier implements SessionVerifier<TwitterSession> {
    private final AccountServiceProvider accountServiceProvider = new AccountServiceProvider();
    private final DefaultScribeClient scribeClient = TwitterCoreScribeClientHolder.getScribeClient();

    protected static class AccountServiceProvider {
        protected AccountServiceProvider() {
        }

        public AccountService getAccountService(TwitterSession twitterSession) {
            return new TwitterApiClient(twitterSession).getAccountService();
        }
    }

    public void verifySession(TwitterSession twitterSession) {
        AccountService accountService = this.accountServiceProvider.getAccountService(twitterSession);
        try {
            scribeVerifySession();
            accountService.verifyCredentials(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false)).execute();
        } catch (IOException | RuntimeException unused) {
        }
    }

    private void scribeVerifySession() {
        if (this.scribeClient != null) {
            EventNamespace builder = new Builder().setClient("android").setPage("credentials").setSection("").setComponent("").setElement("").setAction("impression").builder();
            this.scribeClient.scribe(builder);
        }
    }
}
