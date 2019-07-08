package io.reactivex.internal.util;

import org.reactivestreams.Subscriber;

public interface QueueDrain<T, U> {
    boolean accept(Subscriber<? super U> subscriber, T t);

    boolean cancelled();

    boolean done();

    Throwable error();

    int leave(int i);

    long produced(long j);

    long requested();
}
