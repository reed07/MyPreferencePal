package io.reactivex.internal.operators.parallel;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.ParallelFailureHandling;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelFilterTry<T> extends ParallelFlowable<T> {
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
    final Predicate<? super T> predicate;
    final ParallelFlowable<T> source;

    static abstract class BaseFilterSubscriber<T> implements ConditionalSubscriber<T>, Subscription {
        boolean done;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Predicate<? super T> predicate;
        Subscription upstream;

        BaseFilterSubscriber(Predicate<? super T> predicate2, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.predicate = predicate2;
            this.errorHandler = biFunction;
        }

        public final void request(long j) {
            this.upstream.request(j);
        }

        public final void cancel() {
            this.upstream.cancel();
        }

        public final void onNext(T t) {
            if (!tryOnNext(t) && !this.done) {
                this.upstream.request(1);
            }
        }
    }

    static final class ParallelFilterConditionalSubscriber<T> extends BaseFilterSubscriber<T> {
        final ConditionalSubscriber<? super T> downstream;

        ParallelFilterConditionalSubscriber(ConditionalSubscriber<? super T> conditionalSubscriber, Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            super(predicate, biFunction);
            this.downstream = conditionalSubscriber;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:3:0x0007 A[LOOP:0: B:3:0x0007->B:16:0x003c, LOOP_START, PHI: r2 
  PHI: (r2v1 long) = (r2v0 long), (r2v3 long) binds: [B:2:0x0005, B:16:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean tryOnNext(T r9) {
            /*
                r8 = this;
                boolean r0 = r8.done
                r1 = 0
                if (r0 != 0) goto L_0x0065
                r2 = 0
            L_0x0007:
                r0 = 1
                io.reactivex.functions.Predicate r4 = r8.predicate     // Catch:{ Throwable -> 0x001b }
                boolean r2 = r4.test(r9)     // Catch:{ Throwable -> 0x001b }
                if (r2 == 0) goto L_0x0019
                io.reactivex.internal.fuseable.ConditionalSubscriber<? super T> r2 = r8.downstream
                boolean r9 = r2.tryOnNext(r9)
                if (r9 == 0) goto L_0x0019
                goto L_0x001a
            L_0x0019:
                r0 = 0
            L_0x001a:
                return r0
            L_0x001b:
                r4 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r4)
                io.reactivex.functions.BiFunction r5 = r8.errorHandler     // Catch:{ Throwable -> 0x004e }
                r6 = 1
                long r2 = r2 + r6
                java.lang.Long r6 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x004e }
                java.lang.Object r5 = r5.apply(r6, r4)     // Catch:{ Throwable -> 0x004e }
                java.lang.String r6 = "The errorHandler returned a null item"
                java.lang.Object r5 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r5, r6)     // Catch:{ Throwable -> 0x004e }
                io.reactivex.parallel.ParallelFailureHandling r5 = (io.reactivex.parallel.ParallelFailureHandling) r5     // Catch:{ Throwable -> 0x004e }
                int[] r0 = io.reactivex.internal.operators.parallel.ParallelFilterTry.AnonymousClass1.$SwitchMap$io$reactivex$parallel$ParallelFailureHandling
                int r5 = r5.ordinal()
                r0 = r0[r5]
                switch(r0) {
                    case 1: goto L_0x0007;
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
            L_0x0065:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelFilterTry.ParallelFilterConditionalSubscriber.tryOnNext(java.lang.Object):boolean");
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

    static final class ParallelFilterSubscriber<T> extends BaseFilterSubscriber<T> {
        final Subscriber<? super T> downstream;

        ParallelFilterSubscriber(Subscriber<? super T> subscriber, Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            super(predicate, biFunction);
            this.downstream = subscriber;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:3:0x0007 A[LOOP:0: B:3:0x0007->B:15:0x0038, LOOP_START, PHI: r2 
  PHI: (r2v1 long) = (r2v0 long), (r2v3 long) binds: [B:2:0x0005, B:15:0x0038] A[DONT_GENERATE, DONT_INLINE]] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean tryOnNext(T r9) {
            /*
                r8 = this;
                boolean r0 = r8.done
                r1 = 0
                if (r0 != 0) goto L_0x0061
                r2 = 0
            L_0x0007:
                r0 = 1
                io.reactivex.functions.Predicate r4 = r8.predicate     // Catch:{ Throwable -> 0x0017 }
                boolean r2 = r4.test(r9)     // Catch:{ Throwable -> 0x0017 }
                if (r2 == 0) goto L_0x0016
                org.reactivestreams.Subscriber<? super T> r1 = r8.downstream
                r1.onNext(r9)
                return r0
            L_0x0016:
                return r1
            L_0x0017:
                r4 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r4)
                io.reactivex.functions.BiFunction r5 = r8.errorHandler     // Catch:{ Throwable -> 0x004a }
                r6 = 1
                long r2 = r2 + r6
                java.lang.Long r6 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x004a }
                java.lang.Object r5 = r5.apply(r6, r4)     // Catch:{ Throwable -> 0x004a }
                java.lang.String r6 = "The errorHandler returned a null item"
                java.lang.Object r5 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r5, r6)     // Catch:{ Throwable -> 0x004a }
                io.reactivex.parallel.ParallelFailureHandling r5 = (io.reactivex.parallel.ParallelFailureHandling) r5     // Catch:{ Throwable -> 0x004a }
                int[] r0 = io.reactivex.internal.operators.parallel.ParallelFilterTry.AnonymousClass1.$SwitchMap$io$reactivex$parallel$ParallelFailureHandling
                int r5 = r5.ordinal()
                r0 = r0[r5]
                switch(r0) {
                    case 1: goto L_0x0007;
                    case 2: goto L_0x0049;
                    case 3: goto L_0x0042;
                    default: goto L_0x003b;
                }
            L_0x003b:
                r8.cancel()
                r8.onError(r4)
                return r1
            L_0x0042:
                r8.cancel()
                r8.onComplete()
                return r1
            L_0x0049:
                return r1
            L_0x004a:
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
            L_0x0061:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelFilterTry.ParallelFilterSubscriber.tryOnNext(java.lang.Object):boolean");
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
                    subscriberArr2[i] = new ParallelFilterConditionalSubscriber(conditionalSubscriber, this.predicate, this.errorHandler);
                } else {
                    subscriberArr2[i] = new ParallelFilterSubscriber(conditionalSubscriber, this.predicate, this.errorHandler);
                }
            }
            this.source.subscribe(subscriberArr2);
        }
    }

    public int parallelism() {
        return this.source.parallelism();
    }
}
