package io.reactivex.internal.subscriptions;

import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.reactivestreams.Subscription;

public final class ArrayCompositeSubscription extends AtomicReferenceArray<Subscription> implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;

    public void dispose() {
        if (get(0) != SubscriptionHelper.CANCELLED) {
            int length = length();
            for (int i = 0; i < length; i++) {
                if (((Subscription) get(i)) != SubscriptionHelper.CANCELLED) {
                    Subscription subscription = (Subscription) getAndSet(i, SubscriptionHelper.CANCELLED);
                    if (!(subscription == SubscriptionHelper.CANCELLED || subscription == null)) {
                        subscription.cancel();
                    }
                }
            }
        }
    }

    public boolean isDisposed() {
        return get(0) == SubscriptionHelper.CANCELLED;
    }
}
