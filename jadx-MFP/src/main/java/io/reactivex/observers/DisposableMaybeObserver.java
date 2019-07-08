package io.reactivex.observers;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DisposableMaybeObserver<T> implements MaybeObserver<T>, Disposable {
    final AtomicReference<Disposable> upstream = new AtomicReference<>();
}
