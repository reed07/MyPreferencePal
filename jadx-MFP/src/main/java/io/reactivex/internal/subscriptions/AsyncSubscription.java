package io.reactivex.internal.subscriptions;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

public final class AsyncSubscription extends AtomicLong implements Disposable, Subscription {
    private static final long serialVersionUID = 7028635084060361255L;
    final AtomicReference<Subscription> actual = new AtomicReference<>();
    final AtomicReference<Disposable> resource = new AtomicReference<>();

    public void request(long j) {
        SubscriptionHelper.deferredRequest(this.actual, this, j);
    }

    public void cancel() {
        dispose();
    }

    public void dispose() {
        SubscriptionHelper.cancel(this.actual);
        DisposableHelper.dispose(this.resource);
    }

    public boolean isDisposed() {
        return this.actual.get() == SubscriptionHelper.CANCELLED;
    }
}
