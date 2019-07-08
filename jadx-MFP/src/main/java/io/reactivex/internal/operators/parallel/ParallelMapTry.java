package io.reactivex.internal.operators.parallel;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.ParallelFailureHandling;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelMapTry<T, R> extends ParallelFlowable<R> {
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
    final Function<? super T, ? extends R> mapper;
    final ParallelFlowable<T> source;

    static final class ParallelMapTryConditionalSubscriber<T, R> implements ConditionalSubscriber<T>, Subscription {
        boolean done;
        final ConditionalSubscriber<? super R> downstream;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Function<? super T, ? extends R> mapper;
        Subscription upstream;

        ParallelMapTryConditionalSubscriber(ConditionalSubscriber<? super R> conditionalSubscriber, Function<? super T, ? extends R> function, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.downstream = conditionalSubscriber;
            this.mapper = function;
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

        /* JADX WARNING: Removed duplicated region for block: B:4:0x0008 A[LOOP:0: B:4:0x0008->B:13:0x003c, LOOP_START, PHI: r2 
  PHI: (r2v1 long) = (r2v0 long), (r2v3 long) binds: [B:3:0x0006, B:13:0x003c] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:4:0x0008] */
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
                io.reactivex.functions.Function<? super T, ? extends R> r0 = r7.mapper     // Catch:{ Throwable -> 0x001b }
                java.lang.Object r0 = r0.apply(r8)     // Catch:{ Throwable -> 0x001b }
                java.lang.String r4 = "The mapper returned a null value"
                java.lang.Object r8 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r0, r4)     // Catch:{ Throwable -> 0x001b }
                io.reactivex.internal.fuseable.ConditionalSubscriber<? super R> r0 = r7.downstream
                boolean r8 = r0.tryOnNext(r8)
                return r8
            L_0x001b:
                r0 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r0)
                io.reactivex.functions.BiFunction<? super java.lang.Long, ? super java.lang.Throwable, io.reactivex.parallel.ParallelFailureHandling> r4 = r7.errorHandler     // Catch:{ Throwable -> 0x004e }
                r5 = 1
                long r2 = r2 + r5
                java.lang.Long r5 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x004e }
                java.lang.Object r4 = r4.apply(r5, r0)     // Catch:{ Throwable -> 0x004e }
                java.lang.String r5 = "The errorHandler returned a null item"
                java.lang.Object r4 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r4, r5)     // Catch:{ Throwable -> 0x004e }
                io.reactivex.parallel.ParallelFailureHandling r4 = (io.reactivex.parallel.ParallelFailureHandling) r4     // Catch:{ Throwable -> 0x004e }
                int[] r5 = io.reactivex.internal.operators.parallel.ParallelMapTry.AnonymousClass1.$SwitchMap$io$reactivex$parallel$ParallelFailureHandling
                int r4 = r4.ordinal()
                r4 = r5[r4]
                switch(r4) {
                    case 1: goto L_0x0008;
                    case 2: goto L_0x004d;
                    case 3: goto L_0x0046;
                    default: goto L_0x003f;
                }
            L_0x003f:
                r7.cancel()
                r7.onError(r0)
                return r1
            L_0x0046:
                r7.cancel()
                r7.onComplete()
                return r1
            L_0x004d:
                return r1
            L_0x004e:
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
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelMapTry.ParallelMapTryConditionalSubscriber.tryOnNext(java.lang.Object):boolean");
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

    static final class ParallelMapTrySubscriber<T, R> implements ConditionalSubscriber<T>, Subscription {
        boolean done;
        final Subscriber<? super R> downstream;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Function<? super T, ? extends R> mapper;
        Subscription upstream;

        ParallelMapTrySubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends R> function, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.downstream = subscriber;
            this.mapper = function;
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

        /* JADX WARNING: Removed duplicated region for block: B:4:0x0008 A[LOOP:0: B:4:0x0008->B:14:0x003c, LOOP_START, PHI: r2 
  PHI: (r2v1 long) = (r2v0 long), (r2v3 long) binds: [B:3:0x0006, B:14:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
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
                io.reactivex.functions.Function<? super T, ? extends R> r4 = r8.mapper     // Catch:{ Throwable -> 0x001b }
                java.lang.Object r4 = r4.apply(r9)     // Catch:{ Throwable -> 0x001b }
                java.lang.String r5 = "The mapper returned a null value"
                java.lang.Object r9 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r4, r5)     // Catch:{ Throwable -> 0x001b }
                org.reactivestreams.Subscriber<? super R> r1 = r8.downstream
                r1.onNext(r9)
                return r0
            L_0x001b:
                r4 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r4)
                io.reactivex.functions.BiFunction<? super java.lang.Long, ? super java.lang.Throwable, io.reactivex.parallel.ParallelFailureHandling> r5 = r8.errorHandler     // Catch:{ Throwable -> 0x004e }
                r6 = 1
                long r2 = r2 + r6
                java.lang.Long r6 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x004e }
                java.lang.Object r5 = r5.apply(r6, r4)     // Catch:{ Throwable -> 0x004e }
                java.lang.String r6 = "The errorHandler returned a null item"
                java.lang.Object r5 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r5, r6)     // Catch:{ Throwable -> 0x004e }
                io.reactivex.parallel.ParallelFailureHandling r5 = (io.reactivex.parallel.ParallelFailureHandling) r5     // Catch:{ Throwable -> 0x004e }
                int[] r0 = io.reactivex.internal.operators.parallel.ParallelMapTry.AnonymousClass1.$SwitchMap$io$reactivex$parallel$ParallelFailureHandling
                int r5 = r5.ordinal()
                r0 = r0[r5]
                switch(r0) {
                    case 1: goto L_0x0008;
                    case 2: goto L_0x004d;
                    case 3: goto L_0x0046;
                    default: goto L_0x003f;
                }
            L_0x003f:
                r8.cancel()
                r8.onError(r4)
                return r1
            L_0x0046:
                r8.cancel()
                r8.onComplete()
                return r1
            L_0x004d:
                return r1
            L_0x004e:
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
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelMapTry.ParallelMapTrySubscriber.tryOnNext(java.lang.Object):boolean");
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

    public void subscribe(Subscriber<? super R>[] subscriberArr) {
        if (validate(subscriberArr)) {
            int length = subscriberArr.length;
            Subscriber[] subscriberArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                ConditionalSubscriber conditionalSubscriber = subscriberArr[i];
                if (conditionalSubscriber instanceof ConditionalSubscriber) {
                    subscriberArr2[i] = new ParallelMapTryConditionalSubscriber(conditionalSubscriber, this.mapper, this.errorHandler);
                } else {
                    subscriberArr2[i] = new ParallelMapTrySubscriber(conditionalSubscriber, this.mapper, this.errorHandler);
                }
            }
            this.source.subscribe(subscriberArr2);
        }
    }

    public int parallelism() {
        return this.source.parallelism();
    }
}
