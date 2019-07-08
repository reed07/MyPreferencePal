package io.reactivex.flowables;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class ConnectableFlowable<T> extends Flowable<T> {
    public abstract void connect(@NonNull Consumer<? super Disposable> consumer);
}
