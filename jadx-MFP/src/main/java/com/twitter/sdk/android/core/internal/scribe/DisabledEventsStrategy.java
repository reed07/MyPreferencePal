package com.twitter.sdk.android.core.internal.scribe;

public class DisabledEventsStrategy<T> implements EventsStrategy<T> {
    public void cancelTimeBasedFileRollOver() {
    }

    public FilesSender getFilesSender() {
        return null;
    }

    public void recordEvent(T t) {
    }

    public boolean rollFileOver() {
        return false;
    }

    public void sendEvents() {
    }
}
