package io.reactivex.internal.operators.parallel;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.ParallelFailureHandling;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelDoOnNextTry<T> extends ParallelFlowable<T> {
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
    final Consumer<? super T> onNext;
    final ParallelFlowable<T> source;

    static final class ParallelDoOnNextConditionalSubscriber<T> implements ConditionalSubscriber<T>, Subscription {
        boolean done;
        final ConditionalSubscriber<? super T> downstream;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Consumer<? super T> onNext;
        Subscription upstream;

        ParallelDoOnNextConditionalSubscriber(ConditionalSubscriber<? super T> conditionalSubscriber, Consumer<? super T> consumer, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.downstream = conditionalSubscriber;
            this.onNext = consumer;
            this.errorHandler = biFunction;
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        public void cancel() {
            this.upstream.cancel();
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!tryOnNext(t) && !this.done) {
                this.upstream.request(1);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:4:0x0008 A[LOOP:0: B:4:0x0008->B:13:0x0035, LOOP_START, PHI: r2 
  PHI: (r2v1 long) = (r2v0 long), (r2v3 long) binds: [B:3:0x0006, B:13:0x0035] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:4:0x0008] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean tryOnNext(T r8) {
            /*
                r7 = this;
                boolean r0 = r7.done
                r1 = 0
                if (r0 == 0) goto L_0x0006
                return r1
            L_0x0006:
                r2 = 0
            L_0x0008:
                io.reactivex.functions.Consumer<? super T> r0 = r7.onNext     // Catch:{ Throwable -> 0x0014 }
                r0.accept(r8)     // Catch:{ Throwable -> 0x0014 }
                io.reactivex.internal.fuseable.ConditionalSubscriber<? super T> r0 = r7.downstream
                boolean r8 = r0.tryOnNext(r8)
                return r8
            L_0x0014:
                r0 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r0)
                io.reactivex.functions.BiFunction<? super java.lang.Long, ? super java.lang.Throwable, io.reactivex.parallel.ParallelFailureHandling> r4 = r7.errorHandler     // Catch:{ Throwable -> 0x0047 }
                r5 = 1
                long r2 = r2 + r5
                java.lang.Long r5 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x0047 }
                java.lang.Object r4 = r4.apply(r5, r0)     // Catch:{ Throwable -> 0x0047 }
                java.lang.String r5 = "The errorHandler returned a null item"
                java.lang.Object r4 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r4, r5)     // Catch:{ Throwable -> 0x0047 }
                io.reactivex.parallel.ParallelFailureHandling r4 = (io.reactivex.parallel.ParallelFailureHandling) r4     // Catch:{ Throwable -> 0x0047 }
                int[] r5 = io.reactivex.internal.operators.parallel.ParallelDoOnNextTry.AnonymousClass1.$SwitchMap$io$reactivex$parallel$ParallelFailureHandling
                int r4 = r4.ordinal()
                r4 = r5[r4]
                switch(r4) {
                    case 1: goto L_0x0008;
                    case 2: goto L_0x0046;
                    case 3: goto L_0x003f;
                    default: goto L_0x0038;
                }
            L_0x0038:
                r7.cancel()
                r7.onError(r0)
                return r1
            L_0x003f:
                r7.cancel()
                r7.onComplete()
                return r1
            L_0x0046:
                return r1
            L_0x0047:
                r8 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r8)
                r7.cancel()
                io.reactivex.exceptions.CompositeException r2 = new io.reactivex.exceptions.CompositeException
                r3 = 2
                java.lang.Throwable[] r3 = new java.lang.Throwable[r3]
                r3[r1] = r0
                r0 = 1
                r3[r0] = r8
                r2.<init>(r3)
                r7.onError(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelDoOnNextTry.ParallelDoOnNextConditionalSubscriber.tryOnNext(java.lang.Object):boolean");
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
            }
        }
    }

    static final class ParallelDoOnNextSubscriber<T> implements ConditionalSubscriber<T>, Subscription {
        boolean done;
        final Subscriber<? super T> downstream;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Consumer<? super T> onNext;
        Subscription upstream;

        ParallelDoOnNextSubscriber(Subscriber<? super T> subscriber, Consumer<? super T> consumer, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.downstream = subscriber;
            this.onNext = consumer;
            this.errorHandler = biFunction;
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        public void cancel() {
            this.upstream.cancel();
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.upstream.request(1);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:4:0x0008 A[LOOP:0: B:4:0x0008->B:14:0x0035, LOOP_START, PHI: r2 
  PHI: (r2v1 long) = (r2v0 long), (r2v3 long) binds: [B:3:0x0006, B:14:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean tryOnNext(T r9) {
            /*
                r8 = this;
                boolean r0 = r8.done
                r1 = 0
                if (r0 == 0) goto L_0x0006
                return r1
            L_0x0006:
                r2 = 0
            L_0x0008:
                r0 = 1
                io.reactivex.functions.Consumer<? super T> r4 = r8.onNext     // Catch:{ Throwable -> 0x0014 }
                r4.accept(r9)     // Catch:{ Throwable -> 0x0014 }
                org.reactivestreams.Subscriber<? super T> r1 = r8.downstream
                r1.onNext(r9)
                return r0
            L_0x0014:
                r4 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r4)
                io.reactivex.functions.BiFunction<? super java.lang.Long, ? super java.lang.Throwable, io.reactivex.parallel.ParallelFailureHandling> r5 = r8.errorHandler     // Catch:{ Throwable -> 0x0047 }
                r6 = 1
                long r2 = r2 + r6
                java.lang.Long r6 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x0047 }
                java.lang.Object r5 = r5.apply(r6, r4)     // Catch:{ Throwable -> 0x0047 }
                java.lang.String r6 = "The errorHandler returned a null item"
                java.lang.Object r5 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r5, r6)     // Catch:{ Throwable -> 0x0047 }
                io.reactivex.parallel.ParallelFailureHandling r5 = (io.reactivex.parallel.ParallelFailureHandling) r5     // Catch:{ Throwable -> 0x0047 }
                int[] r0 = io.reactivex.internal.operators.parallel.ParallelDoOnNextTry.AnonymousClass1.$SwitchMap$io$reactivex$parallel$ParallelFailureHandling
                int r5 = r5.ordinal()
                r0 = r0[r5]
                switch(r0) {
                    case 1: goto L_0x0008;
                    case 2: goto L_0x0046;
                    case 3: goto L_0x003f;
                    default: goto L_0x0038;
                }
            L_0x0038:
                r8.cancel()
                r8.onError(r4)
                return r1
            L_0x003f:
                r8.cancel()
                r8.onComplete()
                return r1
            L_0x0046:
                return r1
            L_0x0047:
                r9 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r9)
                r8.cancel()
                io.reactivex.exceptions.CompositeException r2 = new io.reactivex.exceptions.CompositeException
                r3 = 2
                java.lang.Throwable[] r3 = new java.lang.Throwable[r3]
                r3[r1] = r4
                r3[r0] = r9
                r2.<init>(r3)
                r8.onError(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelDoOnNextTry.ParallelDoOnNextSubscriber.tryOnNext(java.lang.Object):boolean");
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
            }
        }
    }

    public void subscribe(Subscriber<? super T>[] subscriberArr) {
        if (validate(subscriberArr)) {
            int length = subscriberArr.length;
            Subscriber[] subscriberArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                ConditionalSubscriber conditionalSubscriber = subscriberArr[i];
                if (conditionalSubscriber instanceof ConditionalSubscriber) {
                    subscriberArr2[i] = new ParallelDoOnNextConditionalSubscriber(conditionalSubscriber, this.onNext, this.errorHandler);
                } else {
                    subscriberArr2[i] = new ParallelDoOnNextSubscriber(conditionalSubscriber, this.onNext, this.errorHandler);
                }
            }
            this.source.subscribe(subscriberArr2);
        }
    }

    public int parallelism() {
        return this.source.parallelism();
    }
}
