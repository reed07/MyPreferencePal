package com.twitter.sdk.android.core;

import android.annotation.SuppressLint;
import android.content.Context;
import com.twitter.sdk.android.core.internal.ActivityLifecycleManager;
import com.twitter.sdk.android.core.internal.CommonUtils;
import com.twitter.sdk.android.core.internal.ExecutorUtils;
import com.twitter.sdk.android.core.internal.IdManager;
import java.io.File;
import java.util.concurrent.ExecutorService;

public class Twitter {
    static final Logger DEFAULT_LOGGER = new DefaultLogger();
    @SuppressLint({"StaticFieldLeak"})
    static volatile Twitter instance;
    private final Context context;
    private final boolean debug;
    private final ExecutorService executorService;
    private final IdManager idManager = new IdManager(this.context);
    private final ActivityLifecycleManager lifecycleManager = new ActivityLifecycleManager(this.context);
    private final Logger logger;
    private final TwitterAuthConfig twitterAuthConfig;

    private Twitter(TwitterConfig twitterConfig) {
        this.context = twitterConfig.context;
        if (twitterConfig.twitterAuthConfig == null) {
            this.twitterAuthConfig = new TwitterAuthConfig(CommonUtils.getStringResourceValue(this.context, "com.twitter.sdk.android.CONSUMER_KEY", ""), CommonUtils.getStringResourceValue(this.context, "com.twitter.sdk.android.CONSUMER_SECRET", ""));
        } else {
            this.twitterAuthConfig = twitterConfig.twitterAuthConfig;
        }
        if (twitterConfig.executorService == null) {
            this.executorService = ExecutorUtils.buildThreadPoolExecutorService("twitter-worker");
        } else {
            this.executorService = twitterConfig.executorService;
        }
        if (twitterConfig.logger == null) {
            this.logger = DEFAULT_LOGGER;
        } else {
            this.logger = twitterConfig.logger;
        }
        if (twitterConfig.debug == null) {
            this.debug = false;
        } else {
            this.debug = twitterConfig.debug.booleanValue();
        }
    }

    public static void initialize(TwitterConfig twitterConfig) {
        createTwitter(twitterConfig);
    }

    static synchronized Twitter createTwitter(TwitterConfig twitterConfig) {
        synchronized (Twitter.class) {
            if (instance == null) {
                instance = new Twitter(twitterConfig);
                Twitter twitter = instance;
                return twitter;
            }
            Twitter twitter2 = instance;
            return twitter2;
        }
    }

    static void checkInitialized() {
        if (instance == null) {
            throw new IllegalStateException("Must initialize Twitter before using getInstance()");
        }
    }

    public static Twitter getInstance() {
        checkInitialized();
        return instance;
    }

    public Context getContext(String str) {
        Context context2 = this.context;
        StringBuilder sb = new StringBuilder();
        sb.append(".TwitterKit");
        sb.append(File.separator);
        sb.append(str);
        return new TwitterContext(context2, str, sb.toString());
    }

    public IdManager getIdManager() {
        return this.idManager;
    }

    public TwitterAuthConfig getTwitterAuthConfig() {
        return this.twitterAuthConfig;
    }

    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public ActivityLifecycleManager getActivityLifecycleManager() {
        return this.lifecycleManager;
    }

    public static boolean isDebug() {
        if (instance == null) {
            return false;
        }
        return instance.debug;
    }

    public static Logger getLogger() {
        if (instance == null) {
            return DEFAULT_LOGGER;
        }
        return instance.logger;
    }
}
