package io.reactivex.observables;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class ConnectableObservable<T> extends Observable<T> {
    public abstract void connect(@NonNull Consumer<? super Disposable> consumer);
}
