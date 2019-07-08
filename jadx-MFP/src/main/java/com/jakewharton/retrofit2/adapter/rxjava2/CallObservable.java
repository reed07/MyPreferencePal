package com.jakewharton.retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Response;

final class CallObservable<T> extends Observable<Response<T>> {
    private final Call<T> originalCall;

    private static final class CallDisposable implements Disposable {
        private final Call<?> call;

        CallDisposable(Call<?> call2) {
            this.call = call2;
        }

        public void dispose() {
            this.call.cancel();
        }

        public boolean isDisposed() {
            return this.call.isCanceled();
        }
    }

    CallObservable(Call<T> call) {
        this.originalCall = call;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super Response<T>> observer) {
        boolean z;
        Call clone = this.originalCall.clone();
        observer.onSubscribe(new CallDisposable(clone));
        try {
            Response execute = clone.execute();
            if (!clone.isCanceled()) {
                observer.onNext(execute);
            }
            if (!clone.isCanceled()) {
                try {
                    observer.onComplete();
                } catch (Throwable th) {
                    th = th;
                    z = true;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            z = false;
            Exceptions.throwIfFatal(th);
            if (z) {
                RxJavaPlugins.onError(th);
            } else if (!clone.isCanceled()) {
                try {
                    observer.onError(th);
                } catch (Throwable th3) {
                    Exceptions.throwIfFatal(th3);
                    RxJavaPlugins.onError(new CompositeException(th, th3));
                }
            }
        }
    }
}
