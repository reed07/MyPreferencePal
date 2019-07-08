package com.crashlytics.android.answers;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.crashlytics.android.answers.BackgroundManager.Listener;
import io.fabric.sdk.android.ActivityLifecycleManager;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

class SessionAnalyticsManager implements Listener {
    final BackgroundManager backgroundManager;
    final AnswersEventsHandler eventsHandler;
    private final long installedAt;
    final ActivityLifecycleManager lifecycleManager;
    final AnswersPreferenceManager preferenceManager;

    public void onError(String str) {
    }

    public static SessionAnalyticsManager build(Kit kit, Context context, IdManager idManager, String str, String str2, long j) {
        Context context2 = context;
        IdManager idManager2 = idManager;
        SessionMetadataCollector sessionMetadataCollector = new SessionMetadataCollector(context, idManager, str, str2);
        Kit kit2 = kit;
        AnswersFilesManagerProvider answersFilesManagerProvider = new AnswersFilesManagerProvider(context, new FileStoreImpl(kit));
        DefaultHttpRequestFactory defaultHttpRequestFactory = new DefaultHttpRequestFactory(Fabric.getLogger());
        ActivityLifecycleManager activityLifecycleManager = new ActivityLifecycleManager(context);
        ScheduledExecutorService buildSingleThreadScheduledExecutorService = ExecutorUtils.buildSingleThreadScheduledExecutorService("Answers Events Handler");
        BackgroundManager backgroundManager2 = new BackgroundManager(buildSingleThreadScheduledExecutorService);
        AnswersEventsHandler answersEventsHandler = new AnswersEventsHandler(kit2, context, answersFilesManagerProvider, sessionMetadataCollector, defaultHttpRequestFactory, buildSingleThreadScheduledExecutorService, new FirebaseAnalyticsApiAdapter(context));
        SessionAnalyticsManager sessionAnalyticsManager = new SessionAnalyticsManager(answersEventsHandler, activityLifecycleManager, backgroundManager2, AnswersPreferenceManager.build(context), j);
        return sessionAnalyticsManager;
    }

    SessionAnalyticsManager(AnswersEventsHandler answersEventsHandler, ActivityLifecycleManager activityLifecycleManager, BackgroundManager backgroundManager2, AnswersPreferenceManager answersPreferenceManager, long j) {
        this.eventsHandler = answersEventsHandler;
        this.lifecycleManager = activityLifecycleManager;
        this.backgroundManager = backgroundManager2;
        this.preferenceManager = answersPreferenceManager;
        this.installedAt = j;
    }

    public void enable() {
        this.eventsHandler.enable();
        this.lifecycleManager.registerCallbacks(new AnswersLifecycleCallbacks(this, this.backgroundManager));
        this.backgroundManager.registerListener(this);
        if (isFirstLaunch()) {
            onInstall(this.installedAt);
            this.preferenceManager.setAnalyticsLaunched();
        }
    }

    public void disable() {
        this.lifecycleManager.resetCallbacks();
        this.eventsHandler.disable();
    }

    public void onCrash(String str, String str2) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Fabric.getLogger().d("Answers", "Logged crash");
            this.eventsHandler.processEventSync(SessionEvent.crashEventBuilder(str, str2));
            return;
        }
        throw new IllegalStateException("onCrash called from main thread!!!");
    }

    public void onInstall(long j) {
        Fabric.getLogger().d("Answers", "Logged install");
        this.eventsHandler.processEventAsyncAndFlush(SessionEvent.installEventBuilder(j));
    }

    public void onLifecycle(Activity activity, Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append("Logged lifecycle event: ");
        sb.append(type.name());
        Fabric.getLogger().d("Answers", sb.toString());
        this.eventsHandler.processEventAsync(SessionEvent.lifecycleEventBuilder(type, activity));
    }

    public void onBackground() {
        Fabric.getLogger().d("Answers", "Flush events when app is backgrounded");
        this.eventsHandler.flushEvents();
    }

    public void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String str) {
        this.backgroundManager.setFlushOnBackground(analyticsSettingsData.flushOnBackground);
        this.eventsHandler.setAnalyticsSettingsData(analyticsSettingsData, str);
    }

    /* access modifiers changed from: 0000 */
    public boolean isFirstLaunch() {
        return !this.preferenceManager.hasAnalyticsLaunched();
    }
}
