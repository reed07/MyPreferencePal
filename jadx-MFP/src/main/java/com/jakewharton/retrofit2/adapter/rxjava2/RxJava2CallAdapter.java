package com.jakewharton.retrofit2.adapter.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;

final class RxJava2CallAdapter implements CallAdapter<Object> {
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isFlowable;
    private final boolean isMaybe;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;
    private final Scheduler scheduler;

    RxJava2CallAdapter(Type type, Scheduler scheduler2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.responseType = type;
        this.scheduler = scheduler2;
        this.isResult = z;
        this.isBody = z2;
        this.isFlowable = z3;
        this.isSingle = z4;
        this.isMaybe = z5;
        this.isCompletable = z6;
    }

    public Type responseType() {
        return this.responseType;
    }

    public <R> Object adapt(Call<R> call) {
        Observable callObservable = new CallObservable(call);
        Observable observable = this.isResult ? new ResultObservable(callObservable) : this.isBody ? new BodyObservable(callObservable) : callObservable;
        Scheduler scheduler2 = this.scheduler;
        if (scheduler2 != null) {
            observable = observable.subscribeOn(scheduler2);
        }
        if (this.isFlowable) {
            return observable.toFlowable(BackpressureStrategy.LATEST);
        }
        if (this.isSingle) {
            return observable.singleOrError();
        }
        if (this.isMaybe) {
            return observable.singleElement();
        }
        return this.isCompletable ? observable.ignoreElements() : observable;
    }
}
