package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler.Worker;
import io.reactivex.annotations.NonNull;

public interface SchedulerMultiWorkerSupport {

    public interface WorkerCallback {
        void onWorker(int i, @NonNull Worker worker);
    }

    void createWorkers(int i, @NonNull WorkerCallback workerCallback);
}
