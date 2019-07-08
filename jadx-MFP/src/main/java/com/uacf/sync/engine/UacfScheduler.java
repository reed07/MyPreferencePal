package com.uacf.sync.engine;

public interface UacfScheduler<TScheduleGroup> {
    void debounceDefaultSync();

    void debounceSyncTypes(TScheduleGroup... tschedulegroupArr);

    void schedulePeriodicSyncIfNecessary();
}
