package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.internal.CommonUtils;
import com.twitter.sdk.android.core.internal.IdManager;
import com.twitter.sdk.android.core.internal.SystemCurrentTimeProvider;
import com.twitter.sdk.android.core.internal.persistence.FileStoreImpl;
import com.twitter.sdk.android.core.internal.scribe.ScribeEvent.Transform;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

public class ScribeClient {
    private final TwitterAuthConfig authConfig;
    private final Context context;
    private final ScheduledExecutorService executor;
    private final GuestSessionProvider guestSessionProvider;
    private final IdManager idManager;
    private final ScribeConfig scribeConfig;
    final ConcurrentHashMap<Long, ScribeHandler> scribeHandlers = new ConcurrentHashMap<>(2);
    private final SessionManager<? extends Session<TwitterAuthToken>> sessionManager;
    private final Transform transform;

    public ScribeClient(Context context2, ScheduledExecutorService scheduledExecutorService, ScribeConfig scribeConfig2, Transform transform2, TwitterAuthConfig twitterAuthConfig, SessionManager<? extends Session<TwitterAuthToken>> sessionManager2, GuestSessionProvider guestSessionProvider2, IdManager idManager2) {
        this.context = context2;
        this.executor = scheduledExecutorService;
        this.scribeConfig = scribeConfig2;
        this.transform = transform2;
        this.authConfig = twitterAuthConfig;
        this.sessionManager = sessionManager2;
        this.guestSessionProvider = guestSessionProvider2;
        this.idManager = idManager2;
    }

    public boolean scribe(ScribeEvent scribeEvent, long j) {
        try {
            getScribeHandler(j).scribe(scribeEvent);
            return true;
        } catch (IOException e) {
            CommonUtils.logControlledError(this.context, "Failed to scribe event", e);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public ScribeHandler getScribeHandler(long j) throws IOException {
        if (!this.scribeHandlers.containsKey(Long.valueOf(j))) {
            this.scribeHandlers.putIfAbsent(Long.valueOf(j), newScribeHandler(j));
        }
        return (ScribeHandler) this.scribeHandlers.get(Long.valueOf(j));
    }

    private ScribeHandler newScribeHandler(long j) throws IOException {
        Context context2 = this.context;
        ScribeFilesManager scribeFilesManager = new ScribeFilesManager(this.context, this.transform, new SystemCurrentTimeProvider(), new QueueFileEventStorage(context2, new FileStoreImpl(context2).getFilesDir(), getWorkingFileNameForOwner(j), getStorageDirForOwner(j)), this.scribeConfig.maxFilesToKeep);
        return new ScribeHandler(this.context, getScribeStrategy(j, scribeFilesManager), scribeFilesManager, this.executor);
    }

    /* access modifiers changed from: 0000 */
    public EventsStrategy<ScribeEvent> getScribeStrategy(long j, ScribeFilesManager scribeFilesManager) {
        if (this.scribeConfig.isEnabled) {
            CommonUtils.logControlled(this.context, "Scribe enabled");
            Context context2 = this.context;
            ScheduledExecutorService scheduledExecutorService = this.executor;
            ScribeConfig scribeConfig2 = this.scribeConfig;
            ScribeFilesSender scribeFilesSender = new ScribeFilesSender(context2, scribeConfig2, j, this.authConfig, this.sessionManager, this.guestSessionProvider, scheduledExecutorService, this.idManager);
            EnabledScribeStrategy enabledScribeStrategy = new EnabledScribeStrategy(context2, scheduledExecutorService, scribeFilesManager, scribeConfig2, scribeFilesSender);
            return enabledScribeStrategy;
        }
        CommonUtils.logControlled(this.context, "Scribe disabled");
        return new DisabledEventsStrategy();
    }

    /* access modifiers changed from: 0000 */
    public String getWorkingFileNameForOwner(long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(j);
        sb.append("_se.tap");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public String getStorageDirForOwner(long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(j);
        sb.append("_se_to_send");
        return sb.toString();
    }
}
