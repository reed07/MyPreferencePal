package com.brightcove.player.analytics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class AnalyticsOutOfSpaceException extends IOException {
    public final long backlogLimit;
    public final long backlogSize;
    @NonNull
    public final Map<String, String> parameters;
    public final int priority;
    @NonNull
    public final String type;

    public AnalyticsOutOfSpaceException(@NonNull AnalyticsEvent analyticsEvent, long j, long j2, @Nullable Throwable th) {
        super("Not enough space", th);
        this.priority = analyticsEvent.getPriority();
        this.type = analyticsEvent.getType();
        this.backlogSize = j;
        this.backlogLimit = j2;
        Map parameters2 = analyticsEvent.getParameters();
        this.parameters = parameters2 == null ? Collections.emptyMap() : Collections.unmodifiableMap(parameters2);
    }
}
