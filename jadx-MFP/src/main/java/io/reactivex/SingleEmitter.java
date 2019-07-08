package io.reactivex;

import io.reactivex.annotations.NonNull;

public interface SingleEmitter<T> {
    boolean isDisposed();

    void onError(@NonNull Throwable th);

    void onSuccess(@NonNull T t);
}
