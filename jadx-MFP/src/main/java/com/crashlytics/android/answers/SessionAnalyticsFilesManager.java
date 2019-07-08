package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.events.EventsStorage;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.io.IOException;
import java.util.UUID;

class SessionAnalyticsFilesManager extends EventsFilesManager<SessionEvent> {
    private AnalyticsSettingsData analyticsSettingsData;

    SessionAnalyticsFilesManager(Context context, SessionEventTransform sessionEventTransform, CurrentTimeProvider currentTimeProvider, EventsStorage eventsStorage) throws IOException {
        super(context, sessionEventTransform, currentTimeProvider, eventsStorage, 100);
    }

    /* access modifiers changed from: protected */
    public String generateUniqueRollOverFileName() {
        UUID randomUUID = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        sb.append("sa");
        sb.append("_");
        sb.append(randomUUID.toString());
        sb.append("_");
        sb.append(this.currentTimeProvider.getCurrentTimeMillis());
        sb.append(".tap");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public int getMaxFilesToKeep() {
        AnalyticsSettingsData analyticsSettingsData2 = this.analyticsSettingsData;
        return analyticsSettingsData2 == null ? super.getMaxFilesToKeep() : analyticsSettingsData2.maxPendingSendFileCount;
    }

    /* access modifiers changed from: protected */
    public int getMaxByteSizePerFile() {
        AnalyticsSettingsData analyticsSettingsData2 = this.analyticsSettingsData;
        return analyticsSettingsData2 == null ? super.getMaxByteSizePerFile() : analyticsSettingsData2.maxByteSizePerFile;
    }

    /* access modifiers changed from: 0000 */
    public void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData2) {
        this.analyticsSettingsData = analyticsSettingsData2;
    }
}
