package com.brightcove.player.analytics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IAnalyticsHandler {

    public interface ProcessListener {
        public static final int OUTCOME_DROPPED = 0;
        public static final int OUTCOME_FAILED = 3;
        public static final int OUTCOME_SAVED = 2;
        public static final int OUTCOME_SUBMITTED = 1;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Outcome {
        }

        void onProcessed(@NonNull AnalyticsEvent analyticsEvent, int i, @Nullable Exception exc);
    }

    void onAnalyticsEvent(@NonNull AnalyticsEvent analyticsEvent, @Nullable IAnalyticsErrorListener iAnalyticsErrorListener);

    void onAttached();

    void onNetworkEntitlementChanged(int i, boolean z);

    void onRemoved();
}
