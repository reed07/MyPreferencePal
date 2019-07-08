package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.LinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableCache<T> extends AbstractFlowableWithUpstream<T, T> {
    final AtomicBoolean once;
    final CacheState<T> state;

    static final class CacheState<T> extends LinkedArrayList implements FlowableSubscriber<T> {
        static final ReplaySubscription[] EMPTY = new ReplaySubscription[0];
        static final ReplaySubscription[] TERMINATED = new ReplaySubscription[0];
        final AtomicReference<Subscription> connection;
        volatile boolean isConnected;
        final Flowable<T> source;
        boolean sourceDone;
        final AtomicReference<ReplaySubscription<T>[]> subscribers;

        public boolean addChild(ReplaySubscription<T> replaySubscription) {
            ReplaySubscription[] replaySubscriptionArr;
            ReplaySubscription[] replaySubscriptionArr2;
            do {
                replaySubscriptionArr = (ReplaySubscription[]) this.subscribers.get();
                if (replaySubscriptionArr == TERMINATED) {
                    return false;
                }
                int length = replaySubscriptionArr.length;
                replaySubscriptionArr2 = new ReplaySubscription[(length + 1)];
                System.arraycopy(replaySubscriptionArr, 0, replaySubscriptionArr2, 0, length);
                replaySubscriptionArr2[length] = replaySubscription;
            } while (!this.subscribers.compareAndSet(replaySubscriptionArr, replaySubscriptionArr2));
            return true;
        }

        public void removeChild(ReplaySubscription<T> replaySubscription) {
            ReplaySubscription[] replaySubscriptionArr;
            ReplaySubscription[] replaySubscriptionArr2;
            do {
                replaySubscriptionArr = (ReplaySubscription[]) this.subscribers.get();
                int length = replaySubscriptionArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (replaySubscriptionArr[i2].equals(replaySubscription)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            replaySubscriptionArr2 = EMPTY;
                        } else {
                            ReplaySubscription[] replaySubscriptionArr3 = new ReplaySubscription[(length - 1)];
                            System.arraycopy(replaySubscriptionArr, 0, replaySubscriptionArr3, 0, i);
                            System.arraycopy(replaySubscriptionArr, i + 1, replaySubscriptionArr3, i, (length - i) - 1);
                            replaySubscriptionArr2 = replaySubscriptionArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(replaySubscriptionArr, replaySubscriptionArr2));
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this.connection, subscription, Long.MAX_VALUE);
        }

        public void connect() {
            this.source.subscribe((FlowableSubscriber<? super T>) this);
            this.isConnected = true;
        }

        public void onNext(T t) {
            if (!this.sourceDone) {
                add(NotificationLite.next(t));
                for (ReplaySubscription replay : (ReplaySubscription[]) this.subscribers.get()) {
                    replay.replay();
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.error(th));
                SubscriptionHelper.cancel(this.connection);
                for (ReplaySubscription replay : (ReplaySubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    replay.replay();
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.complete());
                SubscriptionHelper.cancel(this.connection);
                for (ReplaySubscription replay : (ReplaySubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    replay.replay();
                }
            }
        }
    }

    static final class ReplaySubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = -2557562030197141021L;
        final Subscriber<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        long emitted;
        int index;
        final AtomicLong requested = new AtomicLong();
        final CacheState<T> state;

        ReplaySubscription(Subscriber<? super T> subscriber, CacheState<T> cacheState) {
            this.child = subscriber;
            this.state = cacheState;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.addCancel(this.requested, j);
                replay();
            }
        }

        public void cancel() {
            if (this.requested.getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.state.removeChild(this);
            }
        }

        public void replay() {
            if (getAndIncrement() == 0) {
                Subscriber<? super T> subscriber = this.child;
                AtomicLong atomicLong = this.requested;
                long j = this.emitted;
                int i = 1;
                int i2 = 1;
                while (true) {
                    long j2 = atomicLong.get();
                    if (j2 != Long.MIN_VALUE) {
                        int size = this.state.size();
                        if (size != 0) {
                            Object[] objArr = this.currentBuffer;
                            if (objArr == null) {
                                objArr = this.state.head();
                                this.currentBuffer = objArr;
                            }
                            int length = objArr.length - i;
                            int i3 = this.index;
                            int i4 = this.currentIndexInBuffer;
                            while (i3 < size && j != j2) {
                                if (atomicLong.get() != Long.MIN_VALUE) {
                                    if (i4 == length) {
                                        objArr = (Object[]) objArr[length];
                                        i4 = 0;
                                    }
                                    if (!NotificationLite.accept(objArr[i4], subscriber)) {
                                        i4++;
                                        i3++;
                                        j++;
                                    } else {
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }
                            if (atomicLong.get() != Long.MIN_VALUE) {
                                if (j2 == j) {
                                    Object obj = objArr[i4];
                                    if (NotificationLite.isComplete(obj)) {
                                        subscriber.onComplete();
                                        return;
                                    } else if (NotificationLite.isError(obj)) {
                                        subscriber.onError(NotificationLite.getError(obj));
                                        return;
                                    }
                                }
                                this.index = i3;
                                this.currentIndexInBuffer = i4;
                                this.currentBuffer = objArr;
                            } else {
                                return;
                            }
                        }
                        this.emitted = j;
                        i2 = addAndGet(-i2);
                        if (i2 != 0) {
                            i = 1;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        boolean z;
        ReplaySubscription replaySubscription = new ReplaySubscription(subscriber, this.state);
        subscriber.onSubscribe(replaySubscription);
        if (!this.state.addChild(replaySubscription) || replaySubscription.requested.get() != Long.MIN_VALUE) {
            z = true;
        } else {
            this.state.removeChild(replaySubscription);
            z = false;
        }
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            this.state.connect();
        }
        if (z) {
            replaySubscription.replay();
        }
    }
}
