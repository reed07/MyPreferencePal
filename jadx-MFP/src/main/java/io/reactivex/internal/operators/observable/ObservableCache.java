package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.util.LinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCache<T> extends AbstractObservableWithUpstream<T, T> {
    final AtomicBoolean once;
    final CacheState<T> state;

    static final class CacheState<T> extends LinkedArrayList implements Observer<T> {
        static final ReplayDisposable[] EMPTY = new ReplayDisposable[0];
        static final ReplayDisposable[] TERMINATED = new ReplayDisposable[0];
        final SequentialDisposable connection;
        volatile boolean isConnected;
        final AtomicReference<ReplayDisposable<T>[]> observers;
        final Observable<? extends T> source;
        boolean sourceDone;

        public boolean addChild(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable[] replayDisposableArr;
            ReplayDisposable[] replayDisposableArr2;
            do {
                replayDisposableArr = (ReplayDisposable[]) this.observers.get();
                if (replayDisposableArr == TERMINATED) {
                    return false;
                }
                int length = replayDisposableArr.length;
                replayDisposableArr2 = new ReplayDisposable[(length + 1)];
                System.arraycopy(replayDisposableArr, 0, replayDisposableArr2, 0, length);
                replayDisposableArr2[length] = replayDisposable;
            } while (!this.observers.compareAndSet(replayDisposableArr, replayDisposableArr2));
            return true;
        }

        public void removeChild(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable[] replayDisposableArr;
            ReplayDisposable[] replayDisposableArr2;
            do {
                replayDisposableArr = (ReplayDisposable[]) this.observers.get();
                int length = replayDisposableArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (replayDisposableArr[i2].equals(replayDisposable)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            replayDisposableArr2 = EMPTY;
                        } else {
                            ReplayDisposable[] replayDisposableArr3 = new ReplayDisposable[(length - 1)];
                            System.arraycopy(replayDisposableArr, 0, replayDisposableArr3, 0, i);
                            System.arraycopy(replayDisposableArr, i + 1, replayDisposableArr3, i, (length - i) - 1);
                            replayDisposableArr2 = replayDisposableArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.observers.compareAndSet(replayDisposableArr, replayDisposableArr2));
        }

        public void onSubscribe(Disposable disposable) {
            this.connection.update(disposable);
        }

        public void connect() {
            this.source.subscribe((Observer<? super T>) this);
            this.isConnected = true;
        }

        public void onNext(T t) {
            if (!this.sourceDone) {
                add(NotificationLite.next(t));
                for (ReplayDisposable replay : (ReplayDisposable[]) this.observers.get()) {
                    replay.replay();
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.error(th));
                this.connection.dispose();
                for (ReplayDisposable replay : (ReplayDisposable[]) this.observers.getAndSet(TERMINATED)) {
                    replay.replay();
                }
            }
        }

        public void onComplete() {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.complete());
                this.connection.dispose();
                for (ReplayDisposable replay : (ReplayDisposable[]) this.observers.getAndSet(TERMINATED)) {
                    replay.replay();
                }
            }
        }
    }

    static final class ReplayDisposable<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 7058506693698832024L;
        volatile boolean cancelled;
        final Observer<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        int index;
        final CacheState<T> state;

        ReplayDisposable(Observer<? super T> observer, CacheState<T> cacheState) {
            this.child = observer;
            this.state = cacheState;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.removeChild(this);
            }
        }

        public void replay() {
            if (getAndIncrement() == 0) {
                Observer<? super T> observer = this.child;
                int i = 1;
                while (!this.cancelled) {
                    int size = this.state.size();
                    if (size != 0) {
                        Object[] objArr = this.currentBuffer;
                        if (objArr == null) {
                            objArr = this.state.head();
                            this.currentBuffer = objArr;
                        }
                        int length = objArr.length - 1;
                        int i2 = this.index;
                        int i3 = this.currentIndexInBuffer;
                        while (i2 < size) {
                            if (!this.cancelled) {
                                if (i3 == length) {
                                    objArr = (Object[]) objArr[length];
                                    i3 = 0;
                                }
                                if (!NotificationLite.accept(objArr[i3], observer)) {
                                    i3++;
                                    i2++;
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                        if (!this.cancelled) {
                            this.index = i2;
                            this.currentIndexInBuffer = i3;
                            this.currentBuffer = objArr;
                        } else {
                            return;
                        }
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        ReplayDisposable replayDisposable = new ReplayDisposable(observer, this.state);
        observer.onSubscribe(replayDisposable);
        this.state.addChild(replayDisposable);
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            this.state.connect();
        }
        replayDisposable.replay();
    }
}
