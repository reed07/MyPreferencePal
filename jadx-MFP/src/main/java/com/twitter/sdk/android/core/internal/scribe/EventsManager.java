package com.twitter.sdk.android.core.internal.scribe;

public interface EventsManager<T> {
    void recordEvent(T t);

    void sendEvents();
}
