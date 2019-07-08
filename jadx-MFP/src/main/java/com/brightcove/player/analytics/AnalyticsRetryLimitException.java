package com.brightcove.player.analytics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class AnalyticsRetryLimitException extends IOException {
    public final long attemptsMade;
    @NonNull
    public final Map<String, String> parameters;
    public final int priority;
    public final long retryLimit;
    @NonNull
    public final String type;

    public AnalyticsRetryLimitException(@NonNull AnalyticsEvent analyticsEvent, long j, @Nullable Exception exc) {
        super("Retry limit reached", exc);
        this.priority = analyticsEvent.getPriority();
        this.type = analyticsEvent.getType();
        this.attemptsMade = (long) analyticsEvent.getAttemptsMade();
        this.retryLimit = j;
        Map parameters2 = analyticsEvent.getParameters();
        this.parameters = parameters2 == null ? Collections.emptyMap() : Collections.unmodifiableMap(parameters2);
    }
}
