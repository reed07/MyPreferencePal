package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

class ScribeHandler extends EventsHandler<ScribeEvent> {
    public ScribeHandler(Context context, EventsStrategy<ScribeEvent> eventsStrategy, EventsFilesManager eventsFilesManager, ScheduledExecutorService scheduledExecutorService) {
        super(context, eventsStrategy, eventsFilesManager, scheduledExecutorService);
    }

    public void scribe(ScribeEvent scribeEvent) {
        recordEventAsync(scribeEvent, false);
    }
}
