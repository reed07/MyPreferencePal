package bolts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Task<TResult> {
    public static final ExecutorService BACKGROUND_EXECUTOR = BoltsExecutors.background();
    private static final Executor IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
    private static Task<?> TASK_CANCELLED = new Task<>(true);
    private static Task<Boolean> TASK_FALSE = new Task<>((TResult) Boolean.valueOf(false));
    private static Task<?> TASK_NULL = new Task<>((TResult) null);
    private static Task<Boolean> TASK_TRUE = new Task<>((TResult) Boolean.valueOf(true));
    public static final Executor UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations = new ArrayList();
    private final Object lock = new Object();
    private TResult result;

    public class TaskCompletionSource extends TaskCompletionSource<TResult> {
    }

    public interface UnobservedExceptionHandler {
    }

    Task() {
    }

    private Task(TResult tresult) {
        trySetResult(tresult);
    }

    private Task(boolean z) {
        if (z) {
            trySetCancelled();
        } else {
            trySetResult(null);
        }
    }

    private void runContinuations() {
        synchronized (this.lock) {
            for (Continuation then : this.continuations) {
                try {
                    then.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.continuations = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean trySetCancelled() {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.cancelled = true;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean trySetResult(TResult tresult) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.result = tresult;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }
}
