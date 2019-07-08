package com.myfitnesspal.framework.taskrunner;

import com.uacf.taskrunner.Runner;
import java.lang.Throwable;

public class TaskEventBase<ResultT, ErrorT extends Throwable> {
    private TaskDetails details;

    public static abstract class Unchecked<ResultT> extends TaskEventBase<ResultT, RuntimeException> {
    }

    public TaskEventBase(TaskDetails taskDetails) {
        this.details = taskDetails;
    }

    public TaskEventBase() {
    }

    public boolean isFrom(Runner runner) {
        return runner.getId() == getRunnerId();
    }

    public void setTaskDetails(TaskDetails taskDetails) {
        this.details = taskDetails;
    }

    public final long getRunnerId() {
        return this.details.getRunnerId();
    }

    public final long getTaskId() {
        return this.details.getTaskId();
    }

    public final String getTaskName() {
        return this.details.getTaskName();
    }

    public final ResultT getResult() {
        return this.details.getResult();
    }

    public final ErrorT getFailure() {
        return this.details.getFailure();
    }

    public final boolean successful() {
        return this.details.successful();
    }
}
