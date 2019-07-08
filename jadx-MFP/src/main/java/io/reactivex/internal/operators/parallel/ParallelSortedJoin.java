package io.reactivex.internal.operators.parallel;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelSortedJoin<T> extends Flowable<T> {
    final Comparator<? super T> comparator;
    final ParallelFlowable<List<T>> source;

    static final class SortedJoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final SortedJoinSubscription<T> parent;

        public void onComplete() {
        }

        SortedJoinInnerSubscriber(SortedJoinSubscription<T> sortedJoinSubscription, int i) {
            this.parent = sortedJoinSubscription;
            this.index = i;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this, subscription, Long.MAX_VALUE);
        }

        public void onNext(List<T> list) {
            this.parent.innerNext(list, this.index);
        }

        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        /* access modifiers changed from: 0000 */
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }

    static final class SortedJoinSubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3481980673745556697L;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final Subscriber<? super T> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final int[] indexes;
        final List<T>[] lists;
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicLong requested = new AtomicLong();
        final SortedJoinInnerSubscriber<T>[] subscribers;

        SortedJoinSubscription(Subscriber<? super T> subscriber, int i, Comparator<? super T> comparator2) {
            this.downstream = subscriber;
            this.comparator = comparator2;
            SortedJoinInnerSubscriber<T>[] sortedJoinInnerSubscriberArr = new SortedJoinInnerSubscriber[i];
            for (int i2 = 0; i2 < i; i2++) {
                sortedJoinInnerSubscriberArr[i2] = new SortedJoinInnerSubscriber<>(this, i2);
            }
            this.subscribers = sortedJoinInnerSubscriberArr;
            this.lists = new List[i];
            this.indexes = new int[i];
            this.remaining.lazySet(i);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                if (this.remaining.get() == 0) {
                    drain();
                }
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    Arrays.fill(this.lists, null);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelAll() {
            for (SortedJoinInnerSubscriber<T> cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerNext(List<T> list, int i) {
            this.lists[i] = list;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerError(Throwable th) {
            if (this.error.compareAndSet(null, th)) {
                drain();
            } else if (th != this.error.get()) {
                RxJavaPlugins.onError(th);
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a6, code lost:
            if (r15 != 0) goto L_0x00e5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00aa, code lost:
            if (r1.cancelled == false) goto L_0x00b0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ac, code lost:
            java.util.Arrays.fill(r3, null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00af, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b0, code lost:
            r5 = (java.lang.Throwable) r1.error.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b8, code lost:
            if (r5 == null) goto L_0x00c4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ba, code lost:
            cancelAll();
            java.util.Arrays.fill(r3, null);
            r2.onError(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c3, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c4, code lost:
            r5 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c5, code lost:
            if (r5 >= r4) goto L_0x00d7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00cf, code lost:
            if (r0[r5] == r3[r5].size()) goto L_0x00d4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d1, code lost:
            r16 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d4, code lost:
            r5 = r5 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d7, code lost:
            r16 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d9, code lost:
            if (r16 == false) goto L_0x00e2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x00db, code lost:
            java.util.Arrays.fill(r3, null);
            r2.onComplete();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e1, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e2, code lost:
            r13 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x00e5, code lost:
            r13 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e9, code lost:
            if (r11 == r13) goto L_0x00fa;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f2, code lost:
            if (r7 == Long.MAX_VALUE) goto L_0x00fa;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x00f4, code lost:
            r1.requested.addAndGet(-r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x00fa, code lost:
            r5 = get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x00fe, code lost:
            if (r5 != r6) goto L_0x010b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x0100, code lost:
            r5 = addAndGet(-r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x0105, code lost:
            if (r5 != 0) goto L_0x0108;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x0107, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:0x0108, code lost:
            r6 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:73:0x010b, code lost:
            r6 = r5;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
                r18 = this;
                r1 = r18
                int r0 = r18.getAndIncrement()
                if (r0 == 0) goto L_0x0009
                return
            L_0x0009:
                org.reactivestreams.Subscriber<? super T> r2 = r1.downstream
                java.util.List<T>[] r3 = r1.lists
                int[] r0 = r1.indexes
                int r4 = r0.length
                r6 = 1
            L_0x0011:
                java.util.concurrent.atomic.AtomicLong r7 = r1.requested
                long r7 = r7.get()
                r11 = 0
            L_0x0019:
                r14 = 0
                int r15 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
                if (r15 == 0) goto L_0x00a5
                boolean r15 = r1.cancelled
                if (r15 == 0) goto L_0x0026
                java.util.Arrays.fill(r3, r14)
                return
            L_0x0026:
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r15 = r1.error
                java.lang.Object r15 = r15.get()
                java.lang.Throwable r15 = (java.lang.Throwable) r15
                if (r15 == 0) goto L_0x003a
                r18.cancelAll()
                java.util.Arrays.fill(r3, r14)
                r2.onError(r15)
                return
            L_0x003a:
                r15 = -1
                r13 = r14
                r15 = 0
                r17 = -1
            L_0x003f:
                if (r15 >= r4) goto L_0x008e
                r9 = r3[r15]
                r10 = r0[r15]
                int r5 = r9.size()
                if (r5 == r10) goto L_0x008b
                if (r13 != 0) goto L_0x0055
                java.lang.Object r5 = r9.get(r10)
                r13 = r5
                r17 = r15
                goto L_0x008b
            L_0x0055:
                java.lang.Object r5 = r9.get(r10)
                java.util.Comparator<? super T> r9 = r1.comparator     // Catch:{ Throwable -> 0x006a }
                int r9 = r9.compare(r13, r5)     // Catch:{ Throwable -> 0x006a }
                if (r9 <= 0) goto L_0x0063
                r9 = 1
                goto L_0x0064
            L_0x0063:
                r9 = 0
            L_0x0064:
                if (r9 == 0) goto L_0x008b
                r13 = r5
                r17 = r15
                goto L_0x008b
            L_0x006a:
                r0 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r0)
                r18.cancelAll()
                java.util.Arrays.fill(r3, r14)
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r3 = r1.error
                boolean r3 = r3.compareAndSet(r14, r0)
                if (r3 != 0) goto L_0x007f
                io.reactivex.plugins.RxJavaPlugins.onError(r0)
            L_0x007f:
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r0 = r1.error
                java.lang.Object r0 = r0.get()
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                r2.onError(r0)
                return
            L_0x008b:
                int r15 = r15 + 1
                goto L_0x003f
            L_0x008e:
                if (r13 != 0) goto L_0x0097
                java.util.Arrays.fill(r3, r14)
                r2.onComplete()
                return
            L_0x0097:
                r2.onNext(r13)
                r5 = r0[r17]
                r9 = 1
                int r5 = r5 + r9
                r0[r17] = r5
                r13 = 1
                long r11 = r11 + r13
                goto L_0x0019
            L_0x00a5:
                r9 = 1
                if (r15 != 0) goto L_0x00e5
                boolean r5 = r1.cancelled
                if (r5 == 0) goto L_0x00b0
                java.util.Arrays.fill(r3, r14)
                return
            L_0x00b0:
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r5 = r1.error
                java.lang.Object r5 = r5.get()
                java.lang.Throwable r5 = (java.lang.Throwable) r5
                if (r5 == 0) goto L_0x00c4
                r18.cancelAll()
                java.util.Arrays.fill(r3, r14)
                r2.onError(r5)
                return
            L_0x00c4:
                r5 = 0
            L_0x00c5:
                if (r5 >= r4) goto L_0x00d7
                r10 = r0[r5]
                r13 = r3[r5]
                int r13 = r13.size()
                if (r10 == r13) goto L_0x00d4
                r16 = 0
                goto L_0x00d9
            L_0x00d4:
                int r5 = r5 + 1
                goto L_0x00c5
            L_0x00d7:
                r16 = 1
            L_0x00d9:
                if (r16 == 0) goto L_0x00e2
                java.util.Arrays.fill(r3, r14)
                r2.onComplete()
                return
            L_0x00e2:
                r13 = 0
                goto L_0x00e7
            L_0x00e5:
                r13 = 0
            L_0x00e7:
                int r5 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
                if (r5 == 0) goto L_0x00fa
                r13 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r5 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
                if (r5 == 0) goto L_0x00fa
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r7 = -r11
                r5.addAndGet(r7)
            L_0x00fa:
                int r5 = r18.get()
                if (r5 != r6) goto L_0x010b
                int r5 = -r6
                int r5 = r1.addAndGet(r5)
                if (r5 != 0) goto L_0x0108
                return
            L_0x0108:
                r6 = r5
                goto L_0x0011
            L_0x010b:
                r6 = r5
                goto L_0x0011
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelSortedJoin.SortedJoinSubscription.drain():void");
        }
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        SortedJoinSubscription sortedJoinSubscription = new SortedJoinSubscription(subscriber, this.source.parallelism(), this.comparator);
        subscriber.onSubscribe(sortedJoinSubscription);
        this.source.subscribe(sortedJoinSubscription.subscribers);
    }
}
