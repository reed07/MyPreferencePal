package com.myfitnesspal.framework.mvvm;

import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.uacf.core.util.Ln;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Runner.TaskCallbacks;
import com.uacf.taskrunner.Task;

public abstract class RunnerViewModel<T> extends LoadableViewModel<T> {
    private TaskCallbacks callbacks = new TaskCallbacks() {
        public void onTaskCompleted(String str, long j, Task task, Object obj) {
            RunnerViewModel runnerViewModel = RunnerViewModel.this;
            runnerViewModel.onTaskCompleted(TaskDetails.success(runnerViewModel.getRunner(), task, str, j, obj));
        }

        public void onTaskError(String str, long j, Task task, Throwable th) {
            RunnerViewModel runnerViewModel = RunnerViewModel.this;
            runnerViewModel.onTaskCompleted(TaskDetails.failure(runnerViewModel.getRunner(), task, str, j, th));
        }
    };
    private Runner runner;

    /* access modifiers changed from: protected */
    public void onAttach() {
    }

    /* access modifiers changed from: protected */
    public void onDetach() {
    }

    /* access modifiers changed from: protected */
    public void onNewRunner() {
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
    }

    protected RunnerViewModel(Runner runner2) {
        attach(runner2);
    }

    public final void attach(Runner runner2) {
        Runner runner3 = this.runner;
        boolean z = false;
        if (runner3 != null) {
            if (runner3 != runner2) {
                Ln.e("ERROR! we already have a different Runner attached! some events may be lost.", new Object[0]);
                detach(getRunner());
                z = true;
            } else {
                return;
            }
        }
        this.runner = runner2;
        this.runner.attach(this.callbacks);
        onAttach();
        if (z) {
            onNewRunner();
        }
    }

    public final boolean isAttached() {
        return this.runner != null;
    }

    public final boolean isAttachedTo(Runner runner2) {
        return isAttached() && this.runner == runner2;
    }

    public final void detach(Runner runner2) {
        Runner runner3 = this.runner;
        if (runner3 == runner2) {
            runner3.detach(this.callbacks);
            this.runner = null;
            onDetach();
            return;
        }
        throw new IllegalArgumentException("invalid runner detaching!");
    }

    /* access modifiers changed from: protected */
    public final Runner getRunner() {
        return this.runner;
    }
}
