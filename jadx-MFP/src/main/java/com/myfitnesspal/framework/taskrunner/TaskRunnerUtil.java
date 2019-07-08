package com.myfitnesspal.framework.taskrunner;

import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Task;

public final class TaskRunnerUtil {
    public static boolean postSuccess(MfpUiComponentInterface mfpUiComponentInterface, Runner runner, Task task, String str, long j, Object obj) {
        if (!(task instanceof EventedTask)) {
            return false;
        }
        TaskEventBase event = ((EventedTask) task).getEvent();
        if (event != null) {
            event.setTaskDetails(TaskDetails.success(runner, task, str, j, obj));
            mfpUiComponentInterface.postEventAfterResume(event);
        }
        return true;
    }

    public static boolean postFailure(MfpUiComponentInterface mfpUiComponentInterface, Runner runner, Task task, String str, long j, Throwable th) {
        if (task instanceof EventedTask) {
            TaskEventBase event = ((EventedTask) task).getEvent();
            if (event != null) {
                event.setTaskDetails(TaskDetails.failure(runner, task, str, j, th));
                mfpUiComponentInterface.postEventAfterResume(event);
                return true;
            }
        }
        return false;
    }
}
