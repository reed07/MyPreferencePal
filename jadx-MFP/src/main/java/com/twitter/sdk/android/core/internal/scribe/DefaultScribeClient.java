package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.logging.type.LogSeverity;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.ExecutorUtils;
import com.twitter.sdk.android.core.internal.IdManager;
import com.twitter.sdk.android.core.internal.scribe.ScribeEvent.Transform;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class DefaultScribeClient extends ScribeClient {
    private static volatile ScheduledExecutorService executor;
    private final String advertisingId;
    private final Context context;
    private final SessionManager<? extends Session<TwitterAuthToken>> sessionManager;

    public DefaultScribeClient(Context context2, SessionManager<? extends Session<TwitterAuthToken>> sessionManager2, GuestSessionProvider guestSessionProvider, IdManager idManager, ScribeConfig scribeConfig) {
        this(context2, TwitterCore.getInstance().getAuthConfig(), sessionManager2, guestSessionProvider, idManager, scribeConfig);
    }

    DefaultScribeClient(Context context2, TwitterAuthConfig twitterAuthConfig, SessionManager<? extends Session<TwitterAuthToken>> sessionManager2, GuestSessionProvider guestSessionProvider, IdManager idManager, ScribeConfig scribeConfig) {
        super(context2, getExecutor(), scribeConfig, new Transform(getGson()), twitterAuthConfig, sessionManager2, guestSessionProvider, idManager);
        this.context = context2;
        this.sessionManager = sessionManager2;
        this.advertisingId = idManager.getAdvertisingId();
    }

    public void scribe(EventNamespace... eventNamespaceArr) {
        for (EventNamespace scribe : eventNamespaceArr) {
            scribe(scribe, Collections.emptyList());
        }
    }

    public void scribe(EventNamespace eventNamespace, List<ScribeItem> list) {
        String str = "";
        EventNamespace eventNamespace2 = eventNamespace;
        scribe(ScribeEventFactory.newScribeEvent(eventNamespace2, str, System.currentTimeMillis(), getLanguage(), this.advertisingId, list));
    }

    public void scribe(ScribeEvent scribeEvent) {
        super.scribe(scribeEvent, getScribeSessionId(getActiveSession()));
    }

    /* access modifiers changed from: 0000 */
    public Session getActiveSession() {
        return this.sessionManager.getActiveSession();
    }

    /* access modifiers changed from: 0000 */
    public long getScribeSessionId(Session session) {
        if (session != null) {
            return session.getId();
        }
        return 0;
    }

    private String getLanguage() {
        return this.context.getResources().getConfiguration().locale.getLanguage();
    }

    private static Gson getGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    private static ScheduledExecutorService getExecutor() {
        if (executor == null) {
            synchronized (DefaultScribeClient.class) {
                if (executor == null) {
                    executor = ExecutorUtils.buildSingleThreadScheduledExecutorService("scribe");
                }
            }
        }
        return executor;
    }

    public static ScribeConfig getScribeConfig(String str, String str2) {
        ScribeConfig scribeConfig = new ScribeConfig(isEnabled(), getScribeUrl("https://syndication.twitter.com", ""), "i", ServerProtocol.DIALOG_PARAM_SDK_VERSION, "", getUserAgent(str, str2), 100, LogSeverity.CRITICAL_VALUE);
        return scribeConfig;
    }

    private static boolean isEnabled() {
        return !"release".equals("debug");
    }

    static String getUserAgent(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("TwitterKit/");
        sb.append("3.0");
        sb.append(" (Android ");
        sb.append(VERSION.SDK_INT);
        sb.append(") ");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    static String getScribeUrl(String str, String str2) {
        return !TextUtils.isEmpty(str2) ? str2 : str;
    }
}
