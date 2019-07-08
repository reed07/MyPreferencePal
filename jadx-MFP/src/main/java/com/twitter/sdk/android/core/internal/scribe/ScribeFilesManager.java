package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import com.twitter.sdk.android.core.internal.CurrentTimeProvider;
import java.io.IOException;
import java.util.UUID;

class ScribeFilesManager extends EventsFilesManager<ScribeEvent> {
    public ScribeFilesManager(Context context, EventTransform<ScribeEvent> eventTransform, CurrentTimeProvider currentTimeProvider, QueueFileEventStorage queueFileEventStorage, int i) throws IOException {
        super(context, eventTransform, currentTimeProvider, queueFileEventStorage, i);
    }

    /* access modifiers changed from: protected */
    public String generateUniqueRollOverFileName() {
        UUID randomUUID = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        sb.append("se");
        sb.append("_");
        sb.append(randomUUID.toString());
        sb.append("_");
        sb.append(this.currentTimeProvider.getCurrentTimeMillis());
        sb.append(".tap");
        return sb.toString();
    }
}
