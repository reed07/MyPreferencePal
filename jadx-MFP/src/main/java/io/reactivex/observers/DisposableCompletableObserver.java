package io.reactivex.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DisposableCompletableObserver implements CompletableObserver, Disposable {
    final AtomicReference<Disposable> upstream = new AtomicReference<>();
}
