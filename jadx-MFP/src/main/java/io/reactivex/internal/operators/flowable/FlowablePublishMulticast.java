package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowablePublishMulticast<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayError;
    final int prefetch;
    final Function<? super Flowable<T>, ? extends Publisher<? extends R>> selector;

    static final class MulticastProcessor<T> extends Flowable<T> implements FlowableSubscriber<T>, Disposable {
        static final MulticastSubscription[] EMPTY = new MulticastSubscription[0];
        static final MulticastSubscription[] TERMINATED = new MulticastSubscription[0];
        int consumed;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        volatile SimpleQueue<T> queue;
        int sourceMode;
        final AtomicReference<MulticastSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);
        final AtomicReference<Subscription> upstream = new AtomicReference<>();
        final AtomicInteger wip = new AtomicInteger();

        MulticastProcessor(int i, boolean z) {
            this.prefetch = i;
            this.limit = i - (i >> 2);
            this.delayError = z;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int requestFusion = queueSubscription.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        QueueDrainHelper.request(subscription, this.prefetch);
                        return;
                    }
                }
                this.queue = QueueDrainHelper.createQueue(this.prefetch);
                QueueDrainHelper.request(subscription, this.prefetch);
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this.upstream);
            if (this.wip.getAndIncrement() == 0) {
                SimpleQueue<T> simpleQueue = this.queue;
                if (simpleQueue != null) {
                    simpleQueue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) this.upstream.get());
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode != 0 || this.queue.offer(t)) {
                    drain();
                    return;
                }
                ((Subscription) this.upstream.get()).cancel();
                onError(new MissingBackpressureException());
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
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
            MulticastSubscription<T>[] multicastSubscriptionArr;
            MulticastSubscription[] multicastSubscriptionArr2;
            do {
                multicastSubscriptionArr = (MulticastSubscription[]) this.subscribers.get();
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
                    if (i >= 0) {
                        if (length == 1) {
                            multicastSubscriptionArr2 = EMPTY;
                        } else {
                            MulticastSubscription[] multicastSubscriptionArr3 = new MulticastSubscription[(length - 1)];
                            System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr3, 0, i);
                            System.arraycopy(multicastSubscriptionArr, i + 1, multicastSubscriptionArr3, i, (length - i) - 1);
                            multicastSubscriptionArr2 = multicastSubscriptionArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2));
        }

        /* access modifiers changed from: protected */
        public void subscribeActual(Subscriber<? super T> subscriber) {
            MulticastSubscription multicastSubscription = new MulticastSubscription(subscriber, this);
            subscriber.onSubscribe(multicastSubscription);
            if (!add(multicastSubscription)) {
                Throwable th = this.error;
                if (th != null) {
                    subscriber.onError(th);
                } else {
                    subscriber.onComplete();
                }
            } else if (multicastSubscription.isCancelled()) {
                remove(multicastSubscription);
            } else {
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x00ff, code lost:
            if (r7 != 0) goto L_0x0131;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x0105, code lost:
            if (isDisposed() == false) goto L_0x010b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x0107, code lost:
            r0.clear();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x010a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x010b, code lost:
            r5 = r1.done;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x010d, code lost:
            if (r5 == false) goto L_0x011b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:0x0111, code lost:
            if (r1.delayError != false) goto L_0x011b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:0x0113, code lost:
            r6 = r1.error;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x0115, code lost:
            if (r6 == null) goto L_0x011b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x0117, code lost:
            errorAll(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:0x011a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x011b, code lost:
            if (r5 == false) goto L_0x0131;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:0x0121, code lost:
            if (r0.isEmpty() == false) goto L_0x0131;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:0x0123, code lost:
            r0 = r1.error;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x0125, code lost:
            if (r0 == null) goto L_0x012b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:93:0x0127, code lost:
            errorAll(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:94:0x012b, code lost:
            completeAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:0x012e, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
                r24 = this;
                r1 = r24
                java.util.concurrent.atomic.AtomicInteger r0 = r1.wip
                int r0 = r0.getAndIncrement()
                if (r0 == 0) goto L_0x000b
                return
            L_0x000b:
                io.reactivex.internal.fuseable.SimpleQueue<T> r0 = r1.queue
                int r2 = r1.consumed
                int r3 = r1.limit
                int r4 = r1.sourceMode
                r6 = 1
                if (r4 == r6) goto L_0x0018
                r4 = 1
                goto L_0x0019
            L_0x0018:
                r4 = 0
            L_0x0019:
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublishMulticast$MulticastSubscription<T>[]> r7 = r1.subscribers
                java.lang.Object r8 = r7.get()
                io.reactivex.internal.operators.flowable.FlowablePublishMulticast$MulticastSubscription[] r8 = (io.reactivex.internal.operators.flowable.FlowablePublishMulticast.MulticastSubscription[]) r8
                r9 = r2
                r2 = 1
            L_0x0023:
                int r10 = r8.length
                if (r0 == 0) goto L_0x012f
                if (r10 == 0) goto L_0x012f
                int r11 = r8.length
                r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r14 = r10
                r15 = r12
                r10 = 0
            L_0x0031:
                r17 = -9223372036854775808
                if (r10 >= r11) goto L_0x0054
                r5 = r8[r10]
                long r19 = r5.get()
                r21 = r7
                long r6 = r5.emitted
                long r19 = r19 - r6
                int r5 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
                if (r5 == 0) goto L_0x004c
                int r5 = (r15 > r19 ? 1 : (r15 == r19 ? 0 : -1))
                if (r5 <= 0) goto L_0x004e
                r15 = r19
                goto L_0x004e
            L_0x004c:
                int r14 = r14 + -1
            L_0x004e:
                int r10 = r10 + 1
                r7 = r21
                r6 = 1
                goto L_0x0031
            L_0x0054:
                r21 = r7
                r5 = 0
                if (r14 != 0) goto L_0x005b
                r15 = r5
            L_0x005b:
                int r7 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1))
                if (r7 == 0) goto L_0x00ff
                boolean r10 = r24.isDisposed()
                if (r10 == 0) goto L_0x0069
                r0.clear()
                return
            L_0x0069:
                boolean r10 = r1.done
                if (r10 == 0) goto L_0x0079
                boolean r11 = r1.delayError
                if (r11 != 0) goto L_0x0079
                java.lang.Throwable r11 = r1.error
                if (r11 == 0) goto L_0x0079
                r1.errorAll(r11)
                return
            L_0x0079:
                java.lang.Object r11 = r0.poll()     // Catch:{ Throwable -> 0x00f1 }
                if (r11 != 0) goto L_0x0081
                r14 = 1
                goto L_0x0082
            L_0x0081:
                r14 = 0
            L_0x0082:
                if (r10 == 0) goto L_0x0092
                if (r14 == 0) goto L_0x0092
                java.lang.Throwable r0 = r1.error
                if (r0 == 0) goto L_0x008e
                r1.errorAll(r0)
                goto L_0x0091
            L_0x008e:
                r24.completeAll()
            L_0x0091:
                return
            L_0x0092:
                if (r14 == 0) goto L_0x0096
                goto L_0x00ff
            L_0x0096:
                int r7 = r8.length
                r10 = 0
                r14 = 0
            L_0x0099:
                r19 = 1
                if (r10 >= r7) goto L_0x00c2
                r5 = r8[r10]
                long r22 = r5.get()
                int r6 = (r22 > r17 ? 1 : (r22 == r17 ? 0 : -1))
                if (r6 == 0) goto L_0x00b7
                int r6 = (r22 > r12 ? 1 : (r22 == r12 ? 0 : -1))
                if (r6 == 0) goto L_0x00b1
                long r12 = r5.emitted
                long r12 = r12 + r19
                r5.emitted = r12
            L_0x00b1:
                org.reactivestreams.Subscriber<? super T> r5 = r5.downstream
                r5.onNext(r11)
                goto L_0x00b8
            L_0x00b7:
                r14 = 1
            L_0x00b8:
                int r10 = r10 + 1
                r5 = 0
                r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                goto L_0x0099
            L_0x00c2:
                long r15 = r15 - r19
                if (r4 == 0) goto L_0x00d7
                int r9 = r9 + 1
                if (r9 != r3) goto L_0x00d7
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r5 = r1.upstream
                java.lang.Object r5 = r5.get()
                org.reactivestreams.Subscription r5 = (org.reactivestreams.Subscription) r5
                long r6 = (long) r3
                r5.request(r6)
                r9 = 0
            L_0x00d7:
                java.lang.Object r5 = r21.get()
                io.reactivex.internal.operators.flowable.FlowablePublishMulticast$MulticastSubscription[] r5 = (io.reactivex.internal.operators.flowable.FlowablePublishMulticast.MulticastSubscription[]) r5
                if (r14 != 0) goto L_0x00eb
                if (r5 == r8) goto L_0x00e2
                goto L_0x00eb
            L_0x00e2:
                r5 = 0
                r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                goto L_0x005b
            L_0x00eb:
                r8 = r5
                r7 = r21
                r6 = 1
                goto L_0x0023
            L_0x00f1:
                r0 = move-exception
                r2 = r0
                io.reactivex.exceptions.Exceptions.throwIfFatal(r2)
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r0 = r1.upstream
                io.reactivex.internal.subscriptions.SubscriptionHelper.cancel(r0)
                r1.errorAll(r2)
                return
            L_0x00ff:
                if (r7 != 0) goto L_0x0131
                boolean r5 = r24.isDisposed()
                if (r5 == 0) goto L_0x010b
                r0.clear()
                return
            L_0x010b:
                boolean r5 = r1.done
                if (r5 == 0) goto L_0x011b
                boolean r6 = r1.delayError
                if (r6 != 0) goto L_0x011b
                java.lang.Throwable r6 = r1.error
                if (r6 == 0) goto L_0x011b
                r1.errorAll(r6)
                return
            L_0x011b:
                if (r5 == 0) goto L_0x0131
                boolean r5 = r0.isEmpty()
                if (r5 == 0) goto L_0x0131
                java.lang.Throwable r0 = r1.error
                if (r0 == 0) goto L_0x012b
                r1.errorAll(r0)
                goto L_0x012e
            L_0x012b:
                r24.completeAll()
            L_0x012e:
                return
            L_0x012f:
                r21 = r7
            L_0x0131:
                r1.consumed = r9
                java.util.concurrent.atomic.AtomicInteger r5 = r1.wip
                int r2 = -r2
                int r2 = r5.addAndGet(r2)
                if (r2 != 0) goto L_0x013d
                return
            L_0x013d:
                if (r0 != 0) goto L_0x0141
                io.reactivex.internal.fuseable.SimpleQueue<T> r0 = r1.queue
            L_0x0141:
                java.lang.Object r5 = r21.get()
                r8 = r5
                io.reactivex.internal.operators.flowable.FlowablePublishMulticast$MulticastSubscription[] r8 = (io.reactivex.internal.operators.flowable.FlowablePublishMulticast.MulticastSubscription[]) r8
                r7 = r21
                r6 = 1
                goto L_0x0023
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublishMulticast.MulticastProcessor.drain():void");
        }

        /* access modifiers changed from: 0000 */
        public void errorAll(Throwable th) {
            MulticastSubscription[] multicastSubscriptionArr;
            for (MulticastSubscription multicastSubscription : (MulticastSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.downstream.onError(th);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void completeAll() {
            MulticastSubscription[] multicastSubscriptionArr;
            for (MulticastSubscription multicastSubscription : (MulticastSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.downstream.onComplete();
                }
            }
        }
    }

    static final class MulticastSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 8664815189257569791L;
        final Subscriber<? super T> downstream;
        long emitted;
        final MulticastProcessor<T> parent;

        MulticastSubscription(Subscriber<? super T> subscriber, MulticastProcessor<T> multicastProcessor) {
            this.downstream = subscriber;
            this.parent = multicastProcessor;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.addCancel(this, j);
                this.parent.drain();
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.drain();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }
    }

    static final class OutputCanceller<R> implements FlowableSubscriber<R>, Subscription {
        final Subscriber<? super R> downstream;
        final MulticastProcessor<?> processor;
        Subscription upstream;

        OutputCanceller(Subscriber<? super R> subscriber, MulticastProcessor<?> multicastProcessor) {
            this.downstream = subscriber;
            this.processor = multicastProcessor;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
            this.processor.dispose();
        }

        public void onComplete() {
            this.downstream.onComplete();
            this.processor.dispose();
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        public void cancel() {
            this.upstream.cancel();
            this.processor.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super R> subscriber) {
        MulticastProcessor multicastProcessor = new MulticastProcessor(this.prefetch, this.delayError);
        try {
            ((Publisher) ObjectHelper.requireNonNull(this.selector.apply(multicastProcessor), "selector returned a null Publisher")).subscribe(new OutputCanceller(subscriber, multicastProcessor));
            this.source.subscribe((FlowableSubscriber<? super T>) multicastProcessor);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, subscriber);
        }
    }
}
