package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.subscribers.SerializedSubscriber;
import org.reactivestreams.Subscriber;

public final class FlowableSerialized<T> extends AbstractFlowableWithUpstream<T, T> {
    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber<? super T>) new SerializedSubscriber<Object>(subscriber));
    }
}
