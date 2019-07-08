package com.twitter.sdk.android.core.internal.scribe;

public interface EventsStorageListener {
    void onRollOver(String str);
}
