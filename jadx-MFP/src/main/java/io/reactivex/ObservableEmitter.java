package io.reactivex;

public interface ObservableEmitter<T> extends Emitter<T> {
    boolean isDisposed();
}
