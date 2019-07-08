package com.brightcove.player.analytics;

import android.support.annotation.NonNull;

public interface IAnalyticsErrorListener {
    void onAnalyticsError(@NonNull Throwable th);
}
