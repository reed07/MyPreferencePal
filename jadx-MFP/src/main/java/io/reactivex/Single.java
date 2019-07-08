package io.reactivex;

import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.internal.observers.BlockingMultiObserver;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.internal.operators.single.SingleCache;
import io.reactivex.internal.operators.single.SingleCreate;
import io.reactivex.internal.operators.single.SingleDoFinally;
import io.reactivex.internal.operators.single.SingleDoOnError;
import io.reactivex.internal.operators.single.SingleError;
import io.reactivex.internal.operators.single.SingleFlatMap;
import io.reactivex.internal.operators.single.SingleFlatMapCompletable;
import io.reactivex.internal.operators.single.SingleFromCallable;
import io.reactivex.internal.operators.single.SingleJust;
import io.reactivex.internal.operators.single.SingleMap;
import io.reactivex.internal.operators.single.SingleObserveOn;
import io.reactivex.internal.operators.single.SingleOnErrorReturn;
import io.reactivex.internal.operators.single.SingleSubscribeOn;
import io.reactivex.internal.operators.single.SingleTimeout;
import io.reactivex.internal.operators.single.SingleToObservable;
import io.reactivex.internal.operators.single.SingleZipArray;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public abstract class Single<T> implements SingleSource<T> {
    /* access modifiers changed from: protected */
    public abstract void subscribeActual(@NonNull SingleObserver<? super T> singleObserver);

    @CheckReturnValue
    @SchedulerSupport
    public static <T> Single<T> create(SingleOnSubscribe<T> singleOnSubscribe) {
        ObjectHelper.requireNonNull(singleOnSubscribe, "source is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleCreate<T>(singleOnSubscribe));
    }

    @CheckReturnValue
    @SchedulerSupport
    public static <T> Single<T> error(Callable<? extends Throwable> callable) {
        ObjectHelper.requireNonNull(callable, "errorSupplier is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleError<T>(callable));
    }

    @CheckReturnValue
    @SchedulerSupport
    public static <T> Single<T> error(Throwable th) {
        ObjectHelper.requireNonNull(th, "error is null");
        return error(Functions.justCallable(th));
    }

    @CheckReturnValue
    @SchedulerSupport
    public static <T> Single<T> fromCallable(Callable<? extends T> callable) {
        ObjectHelper.requireNonNull(callable, "callable is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleFromCallable<T>(callable));
    }

    @CheckReturnValue
    @SchedulerSupport
    public static <T> Single<T> just(T t) {
        ObjectHelper.requireNonNull(t, "value is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleJust<T>(t));
    }

    @CheckReturnValue
    @SchedulerSupport
    public static <T1, T2, R> Single<R> zip(SingleSource<? extends T1> singleSource, SingleSource<? extends T2> singleSource2, BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
        ObjectHelper.requireNonNull(singleSource, "source1 is null");
        ObjectHelper.requireNonNull(singleSource2, "source2 is null");
        return zipArray(Functions.toFunction(biFunction), singleSource, singleSource2);
    }

    @CheckReturnValue
    @SchedulerSupport
    public static <T, R> Single<R> zipArray(Function<? super Object[], ? extends R> function, SingleSource<? extends T>... singleSourceArr) {
        ObjectHelper.requireNonNull(function, "zipper is null");
        ObjectHelper.requireNonNull(singleSourceArr, "sources is null");
        if (singleSourceArr.length == 0) {
            return error((Throwable) new NoSuchElementException());
        }
        return RxJavaPlugins.onAssembly((Single<T>) new SingleZipArray<T>(singleSourceArr, function));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Single<T> cache() {
        return RxJavaPlugins.onAssembly((Single<T>) new SingleCache<T>(this));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Single<T> doFinally(Action action) {
        ObjectHelper.requireNonNull(action, "onFinally is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleDoFinally<T>(this, action));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Single<T> doOnError(Consumer<? super Throwable> consumer) {
        ObjectHelper.requireNonNull(consumer, "onError is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleDoOnError<T>(this, consumer));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final <R> Single<R> flatMap(Function<? super T, ? extends SingleSource<? extends R>> function) {
        ObjectHelper.requireNonNull(function, "mapper is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleFlatMap<T>(this, function));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Completable flatMapCompletable(Function<? super T, ? extends CompletableSource> function) {
        ObjectHelper.requireNonNull(function, "mapper is null");
        return RxJavaPlugins.onAssembly((Completable) new SingleFlatMapCompletable(this, function));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final T blockingGet() {
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe((SingleObserver<? super T>) blockingMultiObserver);
        return blockingMultiObserver.blockingGet();
    }

    @CheckReturnValue
    @SchedulerSupport
    public final <R> Single<R> map(Function<? super T, ? extends R> function) {
        ObjectHelper.requireNonNull(function, "mapper is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleMap<T>(this, function));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Single<T> observeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleObserveOn<T>(this, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Single<T> onErrorReturn(Function<Throwable, ? extends T> function) {
        ObjectHelper.requireNonNull(function, "resumeFunction is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleOnErrorReturn<T>(this, function, null));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Disposable subscribe(Consumer<? super T> consumer) {
        return subscribe(consumer, Functions.ON_ERROR_MISSING);
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
        ObjectHelper.requireNonNull(consumer, "onSuccess is null");
        ObjectHelper.requireNonNull(consumer2, "onError is null");
        ConsumerSingleObserver consumerSingleObserver = new ConsumerSingleObserver(consumer, consumer2);
        subscribe((SingleObserver<? super T>) consumerSingleObserver);
        return consumerSingleObserver;
    }

    @SchedulerSupport
    public final void subscribe(SingleObserver<? super T> singleObserver) {
        ObjectHelper.requireNonNull(singleObserver, "subscriber is null");
        SingleObserver onSubscribe = RxJavaPlugins.onSubscribe(this, singleObserver);
        ObjectHelper.requireNonNull(onSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            subscribeActual(onSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Single<T> subscribeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly((Single<T>) new SingleSubscribeOn<T>(this, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Single<T> timeout(long j, TimeUnit timeUnit) {
        return timeout0(j, timeUnit, Schedulers.computation(), null);
    }

    private Single<T> timeout0(long j, TimeUnit timeUnit, Scheduler scheduler, SingleSource<? extends T> singleSource) {
        ObjectHelper.requireNonNull(timeUnit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        SingleTimeout singleTimeout = new SingleTimeout(this, j, timeUnit, scheduler, singleSource);
        return RxJavaPlugins.onAssembly((Single<T>) singleTimeout);
    }

    @CheckReturnValue
    @SchedulerSupport
    public final Observable<T> toObservable() {
        if (this instanceof FuseToObservable) {
            return ((FuseToObservable) this).fuseToObservable();
        }
        return RxJavaPlugins.onAssembly((Observable<T>) new SingleToObservable<T>(this));
    }

    @CheckReturnValue
    @SchedulerSupport
    public final <U, R> Single<R> zipWith(SingleSource<U> singleSource, BiFunction<? super T, ? super U, ? extends R> biFunction) {
        return zip(this, singleSource, biFunction);
    }
}
