package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableReplay<T> extends ConnectableFlowable<T> implements ResettableConnectable, HasUpstreamPublisher<T> {
    static final Callable DEFAULT_UNBOUNDED_FACTORY = new DefaultUnboundedFactory();
    final Callable<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<ReplaySubscriber<T>> current;
    final Publisher<T> onSubscribe;
    final Flowable<T> source;

    static final class DefaultUnboundedFactory implements Callable<Object> {
        DefaultUnboundedFactory() {
        }

        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    }

    static final class InnerSubscription<T> extends AtomicLong implements Disposable, Subscription {
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested;

        public void request(long j) {
            if (SubscriptionHelper.validate(j) && BackpressureHelper.addCancel(this, j) != Long.MIN_VALUE) {
                BackpressureHelper.add(this.totalRequested, j);
                this.parent.manageRequests();
                this.parent.buffer.replay(this);
            }
        }

        public long produced(long j) {
            return BackpressureHelper.producedCancel(this, j);
        }

        public boolean isDisposed() {
            return get() == Long.MIN_VALUE;
        }

        public void cancel() {
            dispose();
        }

        public void dispose() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.manageRequests();
            }
        }

        /* access modifiers changed from: 0000 */
        public <U> U index() {
            return this.index;
        }
    }

    interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerSubscription<T> innerSubscription);
    }

    static final class ReplaySubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Disposable {
        static final InnerSubscription[] EMPTY = new InnerSubscription[0];
        static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
        private static final long serialVersionUID = 7224554242710036740L;
        final ReplayBuffer<T> buffer;
        boolean done;
        final AtomicInteger management = new AtomicInteger();
        long maxChildRequested;
        long maxUpstreamRequested;
        final AtomicBoolean shouldConnect = new AtomicBoolean();
        final AtomicReference<InnerSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);

        ReplaySubscriber(ReplayBuffer<T> replayBuffer) {
            this.buffer = replayBuffer;
        }

        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        public void dispose() {
            this.subscribers.set(TERMINATED);
            SubscriptionHelper.cancel(this);
        }

        /* access modifiers changed from: 0000 */
        public void remove(InnerSubscription<T> innerSubscription) {
            InnerSubscription[] innerSubscriptionArr;
            InnerSubscription[] innerSubscriptionArr2;
            do {
                innerSubscriptionArr = (InnerSubscription[]) this.subscribers.get();
                int length = innerSubscriptionArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerSubscriptionArr[i2].equals(innerSubscription)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            innerSubscriptionArr2 = EMPTY;
                        } else {
                            InnerSubscription[] innerSubscriptionArr3 = new InnerSubscription[(length - 1)];
                            System.arraycopy(innerSubscriptionArr, 0, innerSubscriptionArr3, 0, i);
                            System.arraycopy(innerSubscriptionArr, i + 1, innerSubscriptionArr3, i, (length - i) - 1);
                            innerSubscriptionArr2 = innerSubscriptionArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(innerSubscriptionArr, innerSubscriptionArr2));
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                manageRequests();
                for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.get()) {
                    this.buffer.replay(replay);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.get()) {
                    this.buffer.replay(replay);
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(th);
                for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(replay);
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.buffer.complete();
                for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(replay);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void manageRequests() {
            if (this.management.getAndIncrement() == 0) {
                int i = 1;
                while (!isDisposed()) {
                    InnerSubscription[] innerSubscriptionArr = (InnerSubscription[]) this.subscribers.get();
                    long j = this.maxChildRequested;
                    long j2 = j;
                    for (InnerSubscription innerSubscription : innerSubscriptionArr) {
                        j2 = Math.max(j2, innerSubscription.totalRequested.get());
                    }
                    long j3 = this.maxUpstreamRequested;
                    Subscription subscription = (Subscription) get();
                    long j4 = j2 - j;
                    if (j4 != 0) {
                        this.maxChildRequested = j2;
                        if (subscription == null) {
                            long j5 = j3 + j4;
                            if (j5 < 0) {
                                j5 = Long.MAX_VALUE;
                            }
                            this.maxUpstreamRequested = j5;
                        } else if (j3 != 0) {
                            this.maxUpstreamRequested = 0;
                            subscription.request(j3 + j4);
                        } else {
                            subscription.request(j4);
                        }
                    } else if (!(j3 == 0 || subscription == null)) {
                        this.maxUpstreamRequested = 0;
                        subscription.request(j3);
                    }
                    i = this.management.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        UnboundedReplayBuffer(int i) {
            super(i);
        }

        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
            if (r15.isDisposed() == false) goto L_0x0016;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0016, code lost:
            r1 = r14.size;
            r2 = (java.lang.Integer) r15.index();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
            if (r2 == null) goto L_0x0026;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
            r2 = r2.intValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
            r2 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
            r4 = r15.get();
            r8 = r4;
            r10 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
            if (r8 == 0) goto L_0x0065;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
            if (r2 >= r1) goto L_0x0065;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0035, code lost:
            r12 = get(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
            if (io.reactivex.internal.util.NotificationLite.accept(r12, r0) == false) goto L_0x0040;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x003f, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0044, code lost:
            if (r15.isDisposed() == false) goto L_0x0047;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0046, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0047, code lost:
            r2 = r2 + 1;
            r8 = r8 - 1;
            r10 = r10 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x004e, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x004f, code lost:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r1);
            r15.dispose();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0061, code lost:
            r0.onError(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0067, code lost:
            if (r10 == 0) goto L_0x007b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0069, code lost:
            r15.index = java.lang.Integer.valueOf(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0076, code lost:
            if (r4 == Long.MAX_VALUE) goto L_0x007b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0078, code lost:
            r15.produced(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x007b, code lost:
            monitor-enter(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x007e, code lost:
            if (r15.missed != false) goto L_0x0084;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0080, code lost:
            r15.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0082, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0083, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0084, code lost:
            r15.missed = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x0086, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
            r0 = r15.child;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay(io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription<T> r15) {
            /*
                r14 = this;
                monitor-enter(r15)
                boolean r0 = r15.emitting     // Catch:{ all -> 0x008b }
                r1 = 1
                if (r0 == 0) goto L_0x000a
                r15.missed = r1     // Catch:{ all -> 0x008b }
                monitor-exit(r15)     // Catch:{ all -> 0x008b }
                return
            L_0x000a:
                r15.emitting = r1     // Catch:{ all -> 0x008b }
                monitor-exit(r15)     // Catch:{ all -> 0x008b }
                org.reactivestreams.Subscriber<? super T> r0 = r15.child
            L_0x000f:
                boolean r1 = r15.isDisposed()
                if (r1 == 0) goto L_0x0016
                return
            L_0x0016:
                int r1 = r14.size
                java.lang.Object r2 = r15.index()
                java.lang.Integer r2 = (java.lang.Integer) r2
                r3 = 0
                if (r2 == 0) goto L_0x0026
                int r2 = r2.intValue()
                goto L_0x0027
            L_0x0026:
                r2 = 0
            L_0x0027:
                long r4 = r15.get()
                r6 = 0
                r8 = r4
                r10 = r6
            L_0x002f:
                int r12 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r12 == 0) goto L_0x0065
                if (r2 >= r1) goto L_0x0065
                java.lang.Object r12 = r14.get(r2)
                boolean r12 = io.reactivex.internal.util.NotificationLite.accept(r12, r0)     // Catch:{ Throwable -> 0x004e }
                if (r12 == 0) goto L_0x0040
                return
            L_0x0040:
                boolean r12 = r15.isDisposed()
                if (r12 == 0) goto L_0x0047
                return
            L_0x0047:
                int r2 = r2 + 1
                r12 = 1
                long r8 = r8 - r12
                long r10 = r10 + r12
                goto L_0x002f
            L_0x004e:
                r1 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r1)
                r15.dispose()
                boolean r15 = io.reactivex.internal.util.NotificationLite.isError(r12)
                if (r15 != 0) goto L_0x0064
                boolean r15 = io.reactivex.internal.util.NotificationLite.isComplete(r12)
                if (r15 != 0) goto L_0x0064
                r0.onError(r1)
            L_0x0064:
                return
            L_0x0065:
                int r1 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r1 == 0) goto L_0x007b
                java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
                r15.index = r1
                r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r6 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
                if (r6 == 0) goto L_0x007b
                r15.produced(r10)
            L_0x007b:
                monitor-enter(r15)
                boolean r1 = r15.missed     // Catch:{ all -> 0x0088 }
                if (r1 != 0) goto L_0x0084
                r15.emitting = r3     // Catch:{ all -> 0x0088 }
                monitor-exit(r15)     // Catch:{ all -> 0x0088 }
                return
            L_0x0084:
                r15.missed = r3     // Catch:{ all -> 0x0088 }
                monitor-exit(r15)     // Catch:{ all -> 0x0088 }
                goto L_0x000f
            L_0x0088:
                r0 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x0088 }
                throw r0
            L_0x008b:
                r0 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x008b }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.UnboundedReplayBuffer.replay(io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription):void");
        }
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.onSubscribe.subscribe(subscriber);
    }

    public void resetIf(Disposable disposable) {
        this.current.compareAndSet((ReplaySubscriber) disposable, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(io.reactivex.functions.Consumer<? super io.reactivex.disposables.Disposable> r5) {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber<T>> r0 = r4.current
            java.lang.Object r0 = r0.get()
            io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.ReplaySubscriber) r0
            if (r0 == 0) goto L_0x0010
            boolean r1 = r0.isDisposed()
            if (r1 == 0) goto L_0x0027
        L_0x0010:
            java.util.concurrent.Callable<? extends io.reactivex.internal.operators.flowable.FlowableReplay$ReplayBuffer<T>> r1 = r4.bufferFactory     // Catch:{ Throwable -> 0x0057 }
            java.lang.Object r1 = r1.call()     // Catch:{ Throwable -> 0x0057 }
            io.reactivex.internal.operators.flowable.FlowableReplay$ReplayBuffer r1 = (io.reactivex.internal.operators.flowable.FlowableReplay.ReplayBuffer) r1     // Catch:{ Throwable -> 0x0057 }
            io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber r2 = new io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber
            r2.<init>(r1)
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber<T>> r1 = r4.current
            boolean r0 = r1.compareAndSet(r0, r2)
            if (r0 != 0) goto L_0x0026
            goto L_0x0000
        L_0x0026:
            r0 = r2
        L_0x0027:
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.get()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x003b
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.compareAndSet(r3, r2)
            if (r1 == 0) goto L_0x003b
            r1 = 1
            goto L_0x003c
        L_0x003b:
            r1 = 0
        L_0x003c:
            r5.accept(r0)     // Catch:{ Throwable -> 0x0047 }
            if (r1 == 0) goto L_0x0046
            io.reactivex.Flowable<T> r5 = r4.source
            r5.subscribe(r0)
        L_0x0046:
            return
        L_0x0047:
            r5 = move-exception
            if (r1 == 0) goto L_0x004f
            java.util.concurrent.atomic.AtomicBoolean r0 = r0.shouldConnect
            r0.compareAndSet(r2, r3)
        L_0x004f:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r5)
            java.lang.RuntimeException r5 = io.reactivex.internal.util.ExceptionHelper.wrapOrThrow(r5)
            throw r5
        L_0x0057:
            r5 = move-exception
            io.reactivex.exceptions.Exceptions.throwIfFatal(r5)
            java.lang.RuntimeException r5 = io.reactivex.internal.util.ExceptionHelper.wrapOrThrow(r5)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.connect(io.reactivex.functions.Consumer):void");
    }
}
