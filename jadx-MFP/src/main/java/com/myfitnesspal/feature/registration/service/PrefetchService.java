package com.myfitnesspal.feature.registration.service;

import com.myfitnesspal.feature.registration.task.PrefetchTask.Result;

public interface PrefetchService {

    public interface OnCompletedListener {
        void onCompletedSuccessfully();

        void onRequiredOperationFailed();
    }

    Result getTaskResult();

    boolean isComplete();

    boolean isRunning();

    void prefetch();

    void setOnCompletedListener(OnCompletedListener onCompletedListener);

    void setTaskResult(Result result);
}
