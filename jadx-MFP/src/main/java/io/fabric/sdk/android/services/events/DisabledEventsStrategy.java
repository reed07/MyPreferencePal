package io.fabric.sdk.android.services.events;

public class DisabledEventsStrategy<T> implements EventsStrategy<T> {
    public void cancelTimeBasedFileRollOver() {
    }

    public boolean rollFileOver() {
        return false;
    }
}
