package io.reactivex.observers;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DisposableSingleObserver<T> implements SingleObserver<T>, Disposable {
    final AtomicReference<Disposable> upstream = new AtomicReference<>();
}
