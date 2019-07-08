package com.uacf.sync.engine;

public interface UacfSchedulerEngine<TScheduleGroup> {

    public interface Callbacks<Type> {
        void onCompleted(UacfScheduleFinishedInfo<Type> uacfScheduleFinishedInfo);

        void onProgress(UacfScheduleProgressInfo<Type> uacfScheduleProgressInfo);
    }

    void abortAndClearQueue();

    String enqueue(TScheduleGroup tschedulegroup);

    String enqueue(TScheduleGroup tschedulegroup, Callbacks<TScheduleGroup> callbacks);

    UacfScheduleFinishedInfo<TScheduleGroup> enqueueAndWait(TScheduleGroup tschedulegroup);

    boolean isIdle();
}
