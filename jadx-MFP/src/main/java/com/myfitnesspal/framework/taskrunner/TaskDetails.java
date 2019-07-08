package com.myfitnesspal.framework.taskrunner;

import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Task;

public final class TaskDetails {
    private final Throwable failure;
    private final Object result;
    private final long runnerId;
    private boolean successful = false;
    private final long taskId;
    private final String taskName;
    private final String taskType;

    public boolean matches(Runner runner, Class<?> cls) {
        return runner.getId() == getRunnerId() && getTaskType().equals(cls.getCanonicalName());
    }

    public boolean matches(Runner runner, Class<?> cls, long j) {
        return matches(runner, cls) && this.taskId == j;
    }

    public static TaskDetails success(Runner runner, Task task, String str, long j, Object obj) {
        TaskDetails taskDetails = new TaskDetails(runner, task, str, j, obj, null);
        taskDetails.successful = true;
        return taskDetails;
    }

    public static TaskDetails failure(Runner runner, Task task, String str, long j, Throwable th) {
        TaskDetails taskDetails = new TaskDetails(runner, task, str, j, null, th);
        return taskDetails;
    }

    private TaskDetails(Runner runner, Task task, String str, long j, Object obj, Throwable th) {
        this.runnerId = runner.getId();
        this.taskName = str;
        this.taskType = task.getClass().getCanonicalName();
        this.taskId = j;
        this.result = obj;
        this.failure = th;
    }

    public boolean isFrom(Runner runner) {
        return runner.getId() == this.runnerId;
    }

    public long getRunnerId() {
        return this.runnerId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public long getTaskId() {
        return this.taskId;
    }

    public <T> T getResult() {
        return this.result;
    }

    public <T extends Throwable> T getFailure() {
        return this.failure;
    }

    public boolean successful() {
        return this.successful;
    }
}
