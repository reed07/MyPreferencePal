package com.twitter.sdk.android.core;

import android.content.Context;
import java.util.concurrent.ExecutorService;

public class TwitterConfig {
    final Context context;
    final Boolean debug;
    final ExecutorService executorService;
    final Logger logger;
    final TwitterAuthConfig twitterAuthConfig;

    public static class Builder {
        private final Context context;
        private Boolean debug;
        private ExecutorService executorService;
        private Logger logger;
        private TwitterAuthConfig twitterAuthConfig;

        public Builder(Context context2) {
            if (context2 != null) {
                this.context = context2.getApplicationContext();
                return;
            }
            throw new IllegalArgumentException("Context must not be null.");
        }

        public Builder twitterAuthConfig(TwitterAuthConfig twitterAuthConfig2) {
            if (twitterAuthConfig2 != null) {
                this.twitterAuthConfig = twitterAuthConfig2;
                return this;
            }
            throw new IllegalArgumentException("TwitterAuthConfig must not be null.");
        }

        public TwitterConfig build() {
            TwitterConfig twitterConfig = new TwitterConfig(this.context, this.logger, this.twitterAuthConfig, this.executorService, this.debug);
            return twitterConfig;
        }
    }

    private TwitterConfig(Context context2, Logger logger2, TwitterAuthConfig twitterAuthConfig2, ExecutorService executorService2, Boolean bool) {
        this.context = context2;
        this.logger = logger2;
        this.twitterAuthConfig = twitterAuthConfig2;
        this.executorService = executorService2;
        this.debug = bool;
    }
}
