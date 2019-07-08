package io.reactivex;

import io.reactivex.annotations.NonNull;

public interface Emitter<T> {
    void onNext(@NonNull T t);
}
