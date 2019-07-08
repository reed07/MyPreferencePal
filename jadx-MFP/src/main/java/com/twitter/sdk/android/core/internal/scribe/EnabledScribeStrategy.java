package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

class EnabledScribeStrategy extends EnabledEventsStrategy<ScribeEvent> {
    private final FilesSender filesSender;

    public EnabledScribeStrategy(Context context, ScheduledExecutorService scheduledExecutorService, ScribeFilesManager scribeFilesManager, ScribeConfig scribeConfig, ScribeFilesSender scribeFilesSender) {
        super(context, scheduledExecutorService, scribeFilesManager);
        this.filesSender = scribeFilesSender;
        configureRollover(scribeConfig.sendIntervalSeconds);
    }

    public FilesSender getFilesSender() {
        return this.filesSender;
    }
}
