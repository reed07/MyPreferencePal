package android.arch.core.executor;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

@RestrictTo
public abstract class TaskExecutor {
    public abstract void executeOnDiskIO(@NonNull Runnable runnable);

    public abstract boolean isMainThread();

    public abstract void postToMainThread(@NonNull Runnable runnable);
}
