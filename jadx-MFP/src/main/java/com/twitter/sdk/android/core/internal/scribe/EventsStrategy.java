package com.twitter.sdk.android.core.internal.scribe;

public interface EventsStrategy<T> extends EventsManager<T>, FileRollOverManager {
    FilesSender getFilesSender();
}
