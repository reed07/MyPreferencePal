package com.uacf.sync.engine;

import java.util.List;

public interface UacfSchedulerEngineDelegate<TScheduleGroup> {
    List<UacfScheduleOp> getSyncOpsForType(TScheduleGroup tschedulegroup);

    void onSyncEnqueued(UacfScheduleEnqueuedInfo<TScheduleGroup> uacfScheduleEnqueuedInfo);

    void onSyncFailed(UacfScheduleFailedInfo<TScheduleGroup> uacfScheduleFailedInfo);

    void onSyncFinished(UacfScheduleFinishedInfo<TScheduleGroup> uacfScheduleFinishedInfo);

    void onSyncProgress(UacfScheduleProgressInfo<TScheduleGroup> uacfScheduleProgressInfo);

    void onSyncStarted(UacfScheduleStartedInfo<TScheduleGroup> uacfScheduleStartedInfo);
}
