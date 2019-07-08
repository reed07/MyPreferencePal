package io.reactivex.parallel;

import io.reactivex.annotations.NonNull;
import io.reactivex.internal.subscriptions.EmptySubscription;
import org.reactivestreams.Subscriber;

public abstract class ParallelFlowable<T> {
    public abstract int parallelism();

    public abstract void subscribe(@NonNull Subscriber<? super T>[] subscriberArr);

    /* access modifiers changed from: protected */
    public final boolean validate(@NonNull Subscriber<?>[] subscriberArr) {
        int parallelism = parallelism();
        if (subscriberArr.length == parallelism) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("parallelism = ");
        sb.append(parallelism);
        sb.append(", subscribers = ");
        sb.append(subscriberArr.length);
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(sb.toString());
        for (Subscriber<?> error : subscriberArr) {
            EmptySubscription.error(illegalArgumentException, error);
        }
        return false;
    }
}
