package io.reactivex;

import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.flowable.FlowableFromIterable;
import io.reactivex.internal.operators.flowable.FlowableFromPublisher;
import io.reactivex.internal.operators.flowable.FlowableMap;
import io.reactivex.internal.operators.flowable.FlowableMaterialize;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureBuffer;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureDrop;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureLatest;
import io.reactivex.internal.subscribers.StrictSubscriber;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public abstract class Flowable<T> implements Publisher<T> {
    static final int BUFFER_SIZE = Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());

    /* access modifiers changed from: protected */
    public abstract void subscribeActual(Subscriber<? super T> subscriber);

    public static int bufferSize() {
        return BUFFER_SIZE;
    }

    @CheckReturnValue
    @BackpressureSupport
    @SchedulerSupport
    public static <T> Flowable<T> fromIterable(Iterable<? extends T> iterable) {
        ObjectHelper.requireNonNull(iterable, "source is null");
        return RxJavaPlugins.onAssembly((Flowable<T>) new FlowableFromIterable<T>(iterable));
    }

    @CheckReturnValue
    @BackpressureSupport
    @SchedulerSupport
    public static <T> Flowable<T> fromPublisher(Publisher<? extends T> publisher) {
        if (publisher instanceof Flowable) {
            return RxJavaPlugins.onAssembly((Flowable) publisher);
        }
        ObjectHelper.requireNonNull(publisher, "publisher is null");
        return RxJavaPlugins.onAssembly((Flowable<T>) new FlowableFromPublisher<T>(publisher));
    }

    @CheckReturnValue
    @BackpressureSupport
    @SchedulerSupport
    public final <R> Flowable<R> map(Function<? super T, ? extends R> function) {
        ObjectHelper.requireNonNull(function, "mapper is null");
        return RxJavaPlugins.onAssembly((Flowable<T>) new FlowableMap<T>(this, function));
    }

    @CheckReturnValue
    @BackpressureSupport
    @SchedulerSupport
    public final Flowable<Notification<T>> materialize() {
        return RxJavaPlugins.onAssembly((Flowable<T>) new FlowableMaterialize<T>(this));
    }

    @CheckReturnValue
    @BackpressureSupport
    @SchedulerSupport
    public final Flowable<T> onBackpressureBuffer() {
        return onBackpressureBuffer(bufferSize(), false, true);
    }

    @CheckReturnValue
    @BackpressureSupport
    @SchedulerSupport
    public final Flowable<T> onBackpressureBuffer(int i, boolean z, boolean z2) {
        ObjectHelper.verifyPositive(i, "bufferSize");
        FlowableOnBackpressureBuffer flowableOnBackpressureBuffer = new FlowableOnBackpressureBuffer(this, i, z2, z, Functions.EMPTY_ACTION);
        return RxJavaPlugins.onAssembly((Flowable<T>) flowableOnBackpressureBuffer);
    }

    @CheckReturnValue
    @BackpressureSupport
    @SchedulerSupport
    public final Flowable<T> onBackpressureDrop() {
        return RxJavaPlugins.onAssembly((Flowable<T>) new FlowableOnBackpressureDrop<T>(this));
    }

    @CheckReturnValue
    @BackpressureSupport
    @SchedulerSupport
    public final Flowable<T> onBackpressureLatest() {
        return RxJavaPlugins.onAssembly((Flowable<T>) new FlowableOnBackpressureLatest<T>(this));
    }

    @BackpressureSupport
    @SchedulerSupport
    public final void subscribe(Subscriber<? super T> subscriber) {
        if (subscriber instanceof FlowableSubscriber) {
            subscribe((FlowableSubscriber) subscriber);
            return;
        }
        ObjectHelper.requireNonNull(subscriber, "s is null");
        subscribe((FlowableSubscriber<? super T>) new StrictSubscriber<Object>(subscriber));
    }

    @BackpressureSupport
    @SchedulerSupport
    public final void subscribe(FlowableSubscriber<? super T> flowableSubscriber) {
        ObjectHelper.requireNonNull(flowableSubscriber, "s is null");
        try {
            Subscriber onSubscribe = RxJavaPlugins.onSubscribe(this, (Subscriber<? super T>) flowableSubscriber);
            ObjectHelper.requireNonNull(onSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null FlowableSubscriber. Please check the handler provided to RxJavaPlugins.setOnFlowableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            subscribeActual(onSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }
}
