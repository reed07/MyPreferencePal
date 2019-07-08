package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;

public abstract class AuthHandler {
    private final Callback<TwitterSession> callback;
    private final TwitterAuthConfig config;
    protected final int requestCode;

    public abstract boolean authorize(Activity activity);

    AuthHandler(TwitterAuthConfig twitterAuthConfig, Callback<TwitterSession> callback2, int i) {
        this.config = twitterAuthConfig;
        this.callback = callback2;
        this.requestCode = i;
    }

    /* access modifiers changed from: 0000 */
    public TwitterAuthConfig getAuthConfig() {
        return this.config;
    }
}
