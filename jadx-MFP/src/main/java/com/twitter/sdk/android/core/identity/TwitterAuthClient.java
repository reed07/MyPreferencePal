package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.scribe.DefaultScribeClient;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace.Builder;
import com.twitter.sdk.android.core.internal.scribe.TwitterCoreScribeClientHolder;

public class TwitterAuthClient {
    final TwitterAuthConfig authConfig;
    final AuthState authState;
    final SessionManager<TwitterSession> sessionManager;
    final TwitterCore twitterCore;

    private static class AuthStateLazyHolder {
        /* access modifiers changed from: private */
        public static final AuthState INSTANCE = new AuthState();

        private AuthStateLazyHolder() {
        }
    }

    static class CallbackWrapper extends Callback<TwitterSession> {
        private final Callback<TwitterSession> callback;
        private final SessionManager<TwitterSession> sessionManager;

        CallbackWrapper(SessionManager<TwitterSession> sessionManager2, Callback<TwitterSession> callback2) {
            this.sessionManager = sessionManager2;
            this.callback = callback2;
        }

        public void success(Result<TwitterSession> result) {
            Twitter.getLogger().d("Twitter", "Authorization completed successfully");
            this.sessionManager.setActiveSession((Session) result.data);
            this.callback.success(result);
        }

        public void failure(TwitterException twitterException) {
            Twitter.getLogger().e("Twitter", "Authorization completed with an error", twitterException);
            this.callback.failure(twitterException);
        }
    }

    public TwitterAuthClient() {
        this(TwitterCore.getInstance(), TwitterCore.getInstance().getAuthConfig(), TwitterCore.getInstance().getSessionManager(), AuthStateLazyHolder.INSTANCE);
    }

    TwitterAuthClient(TwitterCore twitterCore2, TwitterAuthConfig twitterAuthConfig, SessionManager<TwitterSession> sessionManager2, AuthState authState2) {
        this.twitterCore = twitterCore2;
        this.authState = authState2;
        this.authConfig = twitterAuthConfig;
        this.sessionManager = sessionManager2;
    }

    public void authorize(Activity activity, Callback<TwitterSession> callback) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity must not be null.");
        } else if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null.");
        } else if (activity.isFinishing()) {
            Twitter.getLogger().e("Twitter", "Cannot authorize, activity is finishing.", null);
        } else {
            handleAuthorize(activity, callback);
        }
    }

    private void handleAuthorize(Activity activity, Callback<TwitterSession> callback) {
        scribeAuthorizeImpression();
        CallbackWrapper callbackWrapper = new CallbackWrapper(this.sessionManager, callback);
        if (!authorizeUsingSSO(activity, callbackWrapper) && !authorizeUsingOAuth(activity, callbackWrapper)) {
            callbackWrapper.failure(new TwitterAuthException("Authorize failed."));
        }
    }

    private boolean authorizeUsingSSO(Activity activity, CallbackWrapper callbackWrapper) {
        if (!SSOAuthHandler.isAvailable(activity)) {
            return false;
        }
        Twitter.getLogger().d("Twitter", "Using SSO");
        AuthState authState2 = this.authState;
        TwitterAuthConfig twitterAuthConfig = this.authConfig;
        return authState2.beginAuthorize(activity, new SSOAuthHandler(twitterAuthConfig, callbackWrapper, twitterAuthConfig.getRequestCode()));
    }

    private boolean authorizeUsingOAuth(Activity activity, CallbackWrapper callbackWrapper) {
        Twitter.getLogger().d("Twitter", "Using OAuth");
        AuthState authState2 = this.authState;
        TwitterAuthConfig twitterAuthConfig = this.authConfig;
        return authState2.beginAuthorize(activity, new OAuthHandler(twitterAuthConfig, callbackWrapper, twitterAuthConfig.getRequestCode()));
    }

    private void scribeAuthorizeImpression() {
        DefaultScribeClient scribeClient = getScribeClient();
        if (scribeClient != null) {
            scribeClient.scribe(new Builder().setClient("android").setPage(Event.LOGIN).setSection("").setComponent("").setElement("").setAction("impression").builder());
        }
    }

    /* access modifiers changed from: protected */
    public DefaultScribeClient getScribeClient() {
        return TwitterCoreScribeClientHolder.getScribeClient();
    }
}
