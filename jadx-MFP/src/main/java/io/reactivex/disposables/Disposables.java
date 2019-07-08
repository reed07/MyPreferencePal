package io.reactivex.disposables;

import io.reactivex.annotations.NonNull;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;

public final class Disposables {
    private Disposables() {
        throw new IllegalStateException("No instances!");
    }

    @NonNull
    public static Disposable fromRunnable(@NonNull Runnable runnable) {
        ObjectHelper.requireNonNull(runnable, "run is null");
        return new RunnableDisposable(runnable);
    }

    @NonNull
    public static Disposable empty() {
        return fromRunnable(Functions.EMPTY_RUNNABLE);
    }

    @NonNull
    public static Disposable disposed() {
        return EmptyDisposable.INSTANCE;
    }
}
