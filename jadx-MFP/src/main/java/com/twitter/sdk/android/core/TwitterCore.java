package com.twitter.sdk.android.core;

import android.annotation.SuppressLint;
import android.content.Context;
import com.twitter.sdk.android.core.GuestSession.Serializer;
import com.twitter.sdk.android.core.internal.SessionMonitor;
import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.internal.TwitterSessionVerifier;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Service;
import com.twitter.sdk.android.core.internal.persistence.PreferenceStoreImpl;
import com.twitter.sdk.android.core.internal.scribe.TwitterCoreScribeClientHolder;
import java.util.concurrent.ConcurrentHashMap;

public class TwitterCore {
    @SuppressLint({"StaticFieldLeak"})
    static volatile TwitterCore instance;
    private final ConcurrentHashMap<Session, TwitterApiClient> apiClients;
    private final TwitterAuthConfig authConfig;
    private final Context context;
    private volatile TwitterApiClient guestClient;
    SessionManager<GuestSession> guestSessionManager;
    private volatile GuestSessionProvider guestSessionProvider;
    SessionMonitor<TwitterSession> sessionMonitor;
    SessionManager<TwitterSession> twitterSessionManager;

    public String getIdentifier() {
        return "com.twitter.sdk.android:twitter-core";
    }

    public String getVersion() {
        return "3.1.1.9";
    }

    TwitterCore(TwitterAuthConfig twitterAuthConfig) {
        this(twitterAuthConfig, new ConcurrentHashMap(), null);
    }

    TwitterCore(TwitterAuthConfig twitterAuthConfig, ConcurrentHashMap<Session, TwitterApiClient> concurrentHashMap, TwitterApiClient twitterApiClient) {
        this.authConfig = twitterAuthConfig;
        this.apiClients = concurrentHashMap;
        this.guestClient = twitterApiClient;
        this.context = Twitter.getInstance().getContext(getIdentifier());
        this.twitterSessionManager = new PersistedSessionManager(new PreferenceStoreImpl(this.context, "session_store"), new Serializer(), "active_twittersession", "twittersession");
        this.guestSessionManager = new PersistedSessionManager(new PreferenceStoreImpl(this.context, "session_store"), new Serializer(), "active_guestsession", "guestsession");
        this.sessionMonitor = new SessionMonitor<>(this.twitterSessionManager, Twitter.getInstance().getExecutorService(), new TwitterSessionVerifier());
    }

    public static TwitterCore getInstance() {
        if (instance == null) {
            synchronized (TwitterCore.class) {
                if (instance == null) {
                    instance = new TwitterCore(Twitter.getInstance().getTwitterAuthConfig());
                    Twitter.getInstance().getExecutorService().execute(new Runnable() {
                        public void run() {
                            TwitterCore.instance.doInBackground();
                        }
                    });
                }
            }
        }
        return instance;
    }

    public TwitterAuthConfig getAuthConfig() {
        return this.authConfig;
    }

    /* access modifiers changed from: 0000 */
    public void doInBackground() {
        this.twitterSessionManager.getActiveSession();
        this.guestSessionManager.getActiveSession();
        getGuestSessionProvider();
        setUpScribeClient();
        this.sessionMonitor.monitorActivityLifecycle(Twitter.getInstance().getActivityLifecycleManager());
    }

    private void setUpScribeClient() {
        TwitterCoreScribeClientHolder.initialize(this.context, getSessionManager(), getGuestSessionProvider(), Twitter.getInstance().getIdManager(), "TwitterCore", getVersion());
    }

    public SessionManager<TwitterSession> getSessionManager() {
        return this.twitterSessionManager;
    }

    public GuestSessionProvider getGuestSessionProvider() {
        if (this.guestSessionProvider == null) {
            createGuestSessionProvider();
        }
        return this.guestSessionProvider;
    }

    private synchronized void createGuestSessionProvider() {
        if (this.guestSessionProvider == null) {
            this.guestSessionProvider = new GuestSessionProvider(new OAuth2Service(this, new TwitterApi()), this.guestSessionManager);
        }
    }

    public TwitterApiClient getApiClient(TwitterSession twitterSession) {
        if (!this.apiClients.containsKey(twitterSession)) {
            this.apiClients.putIfAbsent(twitterSession, new TwitterApiClient(twitterSession));
        }
        return (TwitterApiClient) this.apiClients.get(twitterSession);
    }
}
