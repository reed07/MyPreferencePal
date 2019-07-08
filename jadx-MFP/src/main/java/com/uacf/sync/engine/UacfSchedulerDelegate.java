package com.uacf.sync.engine;

public interface UacfSchedulerDelegate<TScheduleGroup> {
    TScheduleGroup[] getDefaultSyncTypes();

    long getPeriodicSyncTime();

    int getSyncDebounceTimeout();

    UacfSchedulerEngine<TScheduleGroup> getSyncEngine();

    boolean requiresReschedule(TScheduleGroup tschedulegroup);

    boolean shouldResetLastPeriodicSyncTime(TScheduleGroup tschedulegroup);
}
