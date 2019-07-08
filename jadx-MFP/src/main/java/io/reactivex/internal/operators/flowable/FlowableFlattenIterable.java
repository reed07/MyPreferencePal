package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.Nullable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableFlattenIterable<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;

    static final class FlattenIterableSubscriber<T, R> extends BasicIntQueueSubscription<R> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -3096000382929934955L;
        volatile boolean cancelled;
        int consumed;
        Iterator<? extends R> current;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        int fusionMode;
        final int limit;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        final int prefetch;
        SimpleQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription upstream;

        FlattenIterableSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends Iterable<? extends R>> function, int i) {
            this.downstream = subscriber;
            this.mapper = function;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int requestFusion = queueSubscription.requestFusion(3);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = queueSubscription;
                        this.downstream.onSubscribe(this);
                        subscription.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
                subscription.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode != 0 || this.queue.offer(t)) {
                    drain();
                } else {
                    onError(new MissingBackpressureException("Queue is full?!"));
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done || !ExceptionHelper.addThrowable(this.error, th)) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super R> subscriber = this.downstream;
                SimpleQueue<T> simpleQueue = this.queue;
                boolean z = this.fusionMode != 1;
                Iterator<? extends R> it = this.current;
                int i = 1;
                while (true) {
                    if (it == null) {
                        boolean z2 = this.done;
                        try {
                            Object poll = simpleQueue.poll();
                            if (!checkTerminated(z2, poll == null, subscriber, simpleQueue)) {
                                if (poll != null) {
                                    try {
                                        it = ((Iterable) this.mapper.apply(poll)).iterator();
                                        if (!it.hasNext()) {
                                            consumedOne(z);
                                            it = null;
                                        } else {
                                            this.current = it;
                                        }
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        this.upstream.cancel();
                                        ExceptionHelper.addThrowable(this.error, th);
                                        subscriber.onError(ExceptionHelper.terminate(this.error));
                                        return;
                                    }
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            Exceptions.throwIfFatal(th3);
                            this.upstream.cancel();
                            ExceptionHelper.addThrowable(this.error, th3);
                            Throwable terminate = ExceptionHelper.terminate(this.error);
                            this.current = null;
                            simpleQueue.clear();
                            subscriber.onError(terminate);
                            return;
                        }
                    }
                    if (it != null) {
                        long j = this.requested.get();
                        long j2 = 0;
                        while (true) {
                            if (j2 == j) {
                                break;
                            } else if (!checkTerminated(this.done, false, subscriber, simpleQueue)) {
                                try {
                                    subscriber.onNext(ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value"));
                                    if (!checkTerminated(this.done, false, subscriber, simpleQueue)) {
                                        j2++;
                                        try {
                                            if (!it.hasNext()) {
                                                consumedOne(z);
                                                this.current = null;
                                                it = null;
                                                break;
                                            }
                                        } catch (Throwable th4) {
                                            Throwable th5 = th4;
                                            Exceptions.throwIfFatal(th5);
                                            this.current = null;
                                            this.upstream.cancel();
                                            ExceptionHelper.addThrowable(this.error, th5);
                                            subscriber.onError(ExceptionHelper.terminate(this.error));
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th6) {
                                    Exceptions.throwIfFatal(th6);
                                    this.current = null;
                                    this.upstream.cancel();
                                    ExceptionHelper.addThrowable(this.error, th6);
                                    subscriber.onError(ExceptionHelper.terminate(this.error));
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                        if (j2 == j) {
                            if (checkTerminated(this.done, simpleQueue.isEmpty() && it == null, subscriber, simpleQueue)) {
                                return;
                            }
                        }
                        if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                            this.requested.addAndGet(-j2);
                        }
                        if (it == null) {
                        }
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void consumedOne(boolean z) {
            if (z) {
                int i = this.consumed + 1;
                if (i == this.limit) {
                    this.consumed = 0;
                    this.upstream.request((long) i);
                    return;
                }
                this.consumed = i;
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, SimpleQueue<?> simpleQueue) {
            if (this.cancelled) {
                this.current = null;
                simpleQueue.clear();
                return true;
            }
            if (z) {
                if (((Throwable) this.error.get()) != null) {
                    Throwable terminate = ExceptionHelper.terminate(this.error);
                    this.current = null;
                    simpleQueue.clear();
                    subscriber.onError(terminate);
                    return true;
                } else if (z2) {
                    subscriber.onComplete();
                    return true;
                }
            }
            return false;
        }

        public void clear() {
            this.current = null;
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.current == null && this.queue.isEmpty();
        }

        @Nullable
        public R poll() throws Exception {
            Iterator<? extends R> it = this.current;
            while (true) {
                if (it != null) {
                    break;
                }
                Object poll = this.queue.poll();
                if (poll != null) {
                    it = ((Iterable) this.mapper.apply(poll)).iterator();
                    if (it.hasNext()) {
                        this.current = it;
                        break;
                    }
                    it = null;
                } else {
                    return null;
                }
            }
            R requireNonNull = ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value");
            if (!it.hasNext()) {
                this.current = null;
            }
            return requireNonNull;
        }

        public int requestFusion(int i) {
            return ((i & 1) == 0 || this.fusionMode != 1) ? 0 : 1;
        }
    }

    public void subscribeActual(Subscriber<? super R> subscriber) {
        if (this.source instanceof Callable) {
            try {
                Object call = ((Callable) this.source).call();
                if (call == null) {
                    EmptySubscription.complete(subscriber);
                    return;
                }
                try {
                    FlowableFromIterable.subscribe(subscriber, ((Iterable) this.mapper.apply(call)).iterator());
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    EmptySubscription.error(th, subscriber);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                EmptySubscription.error(th2, subscriber);
            }
        } else {
            this.source.subscribe((FlowableSubscriber<? super T>) new FlattenIterableSubscriber<Object>(subscriber, this.mapper, this.prefetch));
        }
    }
}
