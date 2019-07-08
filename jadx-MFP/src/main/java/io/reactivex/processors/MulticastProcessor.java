package io.reactivex.processors;

import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@BackpressureSupport
@SchedulerSupport
public final class MulticastProcessor<T> extends FlowableProcessor<T> {
    static final MulticastSubscription[] EMPTY = new MulticastSubscription[0];
    static final MulticastSubscription[] TERMINATED = new MulticastSubscription[0];
    final int bufferSize;
    int consumed;
    volatile boolean done;
    volatile Throwable error;
    int fusionMode;
    final int limit;
    final AtomicBoolean once;
    volatile SimpleQueue<T> queue;
    final boolean refcount;
    final AtomicReference<MulticastSubscription<T>[]> subscribers;
    final AtomicReference<Subscription> upstream;
    final AtomicInteger wip;

    static final class MulticastSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = -363282618957264509L;
        final Subscriber<? super T> downstream;
        long emitted;
        final MulticastProcessor<T> parent;

        MulticastSubscription(Subscriber<? super T> subscriber, MulticastProcessor<T> multicastProcessor) {
            this.downstream = subscriber;
            this.parent = multicastProcessor;
        }

        public void request(long j) {
            long j2;
            long j3;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 != Long.MIN_VALUE) {
                        j3 = Long.MAX_VALUE;
                        if (j2 != Long.MAX_VALUE) {
                            long j4 = j2 + j;
                            if (j4 >= 0) {
                                j3 = j4;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j3));
                this.parent.drain();
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
            }
        }

        /* access modifiers changed from: 0000 */
        public void onNext(T t) {
            if (get() != Long.MIN_VALUE) {
                this.emitted++;
                this.downstream.onNext(t);
            }
        }

        /* access modifiers changed from: 0000 */
        public void onError(Throwable th) {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onError(th);
            }
        }

        /* access modifiers changed from: 0000 */
        public void onComplete() {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onComplete();
            }
        }
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
            if (subscription instanceof QueueSubscription) {
                QueueSubscription queueSubscription = (QueueSubscription) subscription;
                int requestFusion = queueSubscription.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = queueSubscription;
                    this.done = true;
                    drain();
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = queueSubscription;
                    subscription.request((long) this.bufferSize);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.bufferSize);
            subscription.request((long) this.bufferSize);
        }
    }

    public void onNext(T t) {
        if (!this.once.get()) {
            if (this.fusionMode == 0) {
                ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
                if (!this.queue.offer(t)) {
                    SubscriptionHelper.cancel(this.upstream);
                    onError(new MissingBackpressureException());
                    return;
                }
            }
            drain();
        }
    }

    public void onError(Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.once.compareAndSet(false, true)) {
            this.error = th;
            this.done = true;
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    public void onComplete() {
        if (this.once.compareAndSet(false, true)) {
            this.done = true;
            drain();
        }
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        MulticastSubscription multicastSubscription = new MulticastSubscription(subscriber, this);
        subscriber.onSubscribe(multicastSubscription);
        if (!add(multicastSubscription)) {
            if (this.once.get() || !this.refcount) {
                Throwable th = this.error;
                if (th != null) {
                    subscriber.onError(th);
                    return;
                }
            }
            subscriber.onComplete();
        } else if (multicastSubscription.get() == Long.MIN_VALUE) {
            remove(multicastSubscription);
        } else {
            drain();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean add(MulticastSubscription<T> multicastSubscription) {
        MulticastSubscription[] multicastSubscriptionArr;
        MulticastSubscription[] multicastSubscriptionArr2;
        do {
            multicastSubscriptionArr = (MulticastSubscription[]) this.subscribers.get();
            if (multicastSubscriptionArr == TERMINATED) {
                return false;
            }
            int length = multicastSubscriptionArr.length;
            multicastSubscriptionArr2 = new MulticastSubscription[(length + 1)];
            System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, length);
            multicastSubscriptionArr2[length] = multicastSubscription;
        } while (!this.subscribers.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void remove(MulticastSubscription<T> multicastSubscription) {
        while (true) {
            MulticastSubscription<T>[] multicastSubscriptionArr = (MulticastSubscription[]) this.subscribers.get();
            int length = multicastSubscriptionArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (multicastSubscriptionArr[i2] == multicastSubscription) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i < 0) {
                    break;
                } else if (length != 1) {
                    MulticastSubscription[] multicastSubscriptionArr2 = new MulticastSubscription[(length - 1)];
                    System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, i);
                    System.arraycopy(multicastSubscriptionArr, i + 1, multicastSubscriptionArr2, i, (length - i) - 1);
                    if (this.subscribers.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2)) {
                        break;
                    }
                } else if (this.refcount) {
                    if (this.subscribers.compareAndSet(multicastSubscriptionArr, TERMINATED)) {
                        SubscriptionHelper.cancel(this.upstream);
                        this.once.set(true);
                        break;
                    }
                } else if (this.subscribers.compareAndSet(multicastSubscriptionArr, EMPTY)) {
                    break;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void drain() {
        int i;
        boolean z;
        Object obj;
        if (this.wip.getAndIncrement() == 0) {
            AtomicReference<MulticastSubscription<T>[]> atomicReference = this.subscribers;
            int i2 = this.consumed;
            int i3 = this.limit;
            int i4 = this.fusionMode;
            int i5 = 1;
            while (true) {
                SimpleQueue<T> simpleQueue = this.queue;
                if (simpleQueue != null) {
                    MulticastSubscription[] multicastSubscriptionArr = (MulticastSubscription[]) atomicReference.get();
                    if (multicastSubscriptionArr.length != 0) {
                        int length = multicastSubscriptionArr.length;
                        long j = -1;
                        long j2 = -1;
                        int i6 = 0;
                        while (i6 < length) {
                            MulticastSubscription multicastSubscription = multicastSubscriptionArr[i6];
                            long j3 = multicastSubscription.get();
                            if (j3 >= 0) {
                                if (j2 == j) {
                                    j2 = j3 - multicastSubscription.emitted;
                                } else {
                                    j2 = Math.min(j2, j3 - multicastSubscription.emitted);
                                }
                            }
                            i6++;
                            j = -1;
                        }
                        int i7 = i2;
                        while (true) {
                            i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
                            if (i <= 0) {
                                break;
                            }
                            MulticastSubscription[] multicastSubscriptionArr2 = (MulticastSubscription[]) atomicReference.get();
                            if (multicastSubscriptionArr2 == TERMINATED) {
                                simpleQueue.clear();
                                return;
                            } else if (multicastSubscriptionArr != multicastSubscriptionArr2) {
                                break;
                            } else {
                                try {
                                    Object poll = simpleQueue.poll();
                                    z = this.done;
                                    obj = poll;
                                } catch (Throwable th) {
                                    Throwable th2 = th;
                                    Exceptions.throwIfFatal(th2);
                                    SubscriptionHelper.cancel(this.upstream);
                                    obj = null;
                                    this.error = th2;
                                    this.done = true;
                                    z = true;
                                }
                                boolean z2 = obj == null;
                                if (z && z2) {
                                    Throwable th3 = this.error;
                                    if (th3 != null) {
                                        for (MulticastSubscription onError : (MulticastSubscription[]) atomicReference.getAndSet(TERMINATED)) {
                                            onError.onError(th3);
                                        }
                                    } else {
                                        for (MulticastSubscription onComplete : (MulticastSubscription[]) atomicReference.getAndSet(TERMINATED)) {
                                            onComplete.onComplete();
                                        }
                                    }
                                    return;
                                } else if (z2) {
                                    break;
                                } else {
                                    for (MulticastSubscription onNext : multicastSubscriptionArr) {
                                        onNext.onNext(obj);
                                    }
                                    j2--;
                                    if (i4 != 1) {
                                        int i8 = i7 + 1;
                                        if (i8 == i3) {
                                            ((Subscription) this.upstream.get()).request((long) i3);
                                            i7 = 0;
                                        } else {
                                            i7 = i8;
                                        }
                                    }
                                }
                            }
                        }
                        if (i == 0) {
                            MulticastSubscription[] multicastSubscriptionArr3 = (MulticastSubscription[]) atomicReference.get();
                            if (multicastSubscriptionArr3 == TERMINATED) {
                                simpleQueue.clear();
                                return;
                            }
                            if (multicastSubscriptionArr == multicastSubscriptionArr3) {
                                if (this.done && simpleQueue.isEmpty()) {
                                    Throwable th4 = this.error;
                                    if (th4 != null) {
                                        for (MulticastSubscription onError2 : (MulticastSubscription[]) atomicReference.getAndSet(TERMINATED)) {
                                            onError2.onError(th4);
                                        }
                                    } else {
                                        for (MulticastSubscription onComplete2 : (MulticastSubscription[]) atomicReference.getAndSet(TERMINATED)) {
                                            onComplete2.onComplete();
                                        }
                                    }
                                    return;
                                }
                            }
                            i2 = i7;
                        }
                        i2 = i7;
                    }
                }
                i5 = this.wip.addAndGet(-i5);
                if (i5 == 0) {
                    return;
                }
            }
        }
    }
}
