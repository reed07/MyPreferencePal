package io.reactivex.internal.operators.observable;

import io.reactivex.Notification;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableDematerialize<T> extends AbstractObservableWithUpstream<Notification<T>, T> {

    static final class DematerializeObserver<T> implements Observer<Notification<T>>, Disposable {
        boolean done;
        final Observer<? super T> downstream;
        Disposable upstream;

        DematerializeObserver(Observer<? super T> observer) {
            this.downstream = observer;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void dispose() {
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public void onNext(Notification<T> notification) {
            if (this.done) {
                if (notification.isOnError()) {
                    RxJavaPlugins.onError(notification.getError());
                }
                return;
            }
            if (notification.isOnError()) {
                this.upstream.dispose();
                onError(notification.getError());
            } else if (notification.isOnComplete()) {
                this.upstream.dispose();
                onComplete();
            } else {
                this.downstream.onNext(notification.getValue());
            }
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

    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new DematerializeObserver(observer));
    }
}
