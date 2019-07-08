package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSwitchMap<T, R> extends AbstractObservableWithUpstream<T, R> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends ObservableSource<? extends R>> mapper;

    static final class SwitchMapInnerObserver<T, R> extends AtomicReference<Disposable> implements Observer<R> {
        private static final long serialVersionUID = 3837284832786408377L;
        final int bufferSize;
        volatile boolean done;
        final long index;
        final SwitchMapObserver<T, R> parent;
        volatile SimpleQueue<R> queue;

        SwitchMapInnerObserver(SwitchMapObserver<T, R> switchMapObserver, long j, int i) {
            this.parent = switchMapObserver;
            this.index = j;
            this.bufferSize = i;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable)) {
                if (disposable instanceof QueueDisposable) {
                    QueueDisposable queueDisposable = (QueueDisposable) disposable;
                    int requestFusion = queueDisposable.requestFusion(7);
                    if (requestFusion == 1) {
                        this.queue = queueDisposable;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.queue = queueDisposable;
                        return;
                    }
                }
                this.queue = new SpscLinkedArrayQueue(this.bufferSize);
            }
        }

        public void onNext(R r) {
            if (this.index == this.parent.unique) {
                if (r != null) {
                    this.queue.offer(r);
                }
                this.parent.drain();
            }
        }

        public void onError(Throwable th) {
            this.parent.innerError(this, th);
        }

        public void onComplete() {
            if (this.index == this.parent.unique) {
                this.done = true;
                this.parent.drain();
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this);
        }
    }

    static final class SwitchMapObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        static final SwitchMapInnerObserver<Object, Object> CANCELLED = new SwitchMapInnerObserver<>(null, -1, 1);
        private static final long serialVersionUID = -3491074160481096299L;
        final AtomicReference<SwitchMapInnerObserver<T, R>> active = new AtomicReference<>();
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Observer<? super R> downstream;
        final AtomicThrowable errors;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        volatile long unique;
        Disposable upstream;

        static {
            CANCELLED.cancel();
        }

        SwitchMapObserver(Observer<? super R> observer, Function<? super T, ? extends ObservableSource<? extends R>> function, int i, boolean z) {
            this.downstream = observer;
            this.mapper = function;
            this.bufferSize = i;
            this.delayErrors = z;
            this.errors = new AtomicThrowable();
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long j = this.unique + 1;
            this.unique = j;
            SwitchMapInnerObserver switchMapInnerObserver = (SwitchMapInnerObserver) this.active.get();
            if (switchMapInnerObserver != null) {
                switchMapInnerObserver.cancel();
            }
            try {
                ObservableSource observableSource = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The ObservableSource returned is null");
                SwitchMapInnerObserver switchMapInnerObserver2 = new SwitchMapInnerObserver(this, j, this.bufferSize);
                while (true) {
                    SwitchMapInnerObserver<Object, Object> switchMapInnerObserver3 = (SwitchMapInnerObserver) this.active.get();
                    if (switchMapInnerObserver3 != CANCELLED) {
                        if (this.active.compareAndSet(switchMapInnerObserver3, switchMapInnerObserver2)) {
                            observableSource.subscribe(switchMapInnerObserver2);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (this.done || !this.errors.addThrowable(th)) {
                RxJavaPlugins.onError(th);
                return;
            }
            if (!this.delayErrors) {
                disposeInner();
            }
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                disposeInner();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void disposeInner() {
            SwitchMapInnerObserver<Object, Object> switchMapInnerObserver = (SwitchMapInnerObserver) this.active.get();
            SwitchMapInnerObserver<Object, Object> switchMapInnerObserver2 = CANCELLED;
            if (switchMapInnerObserver != switchMapInnerObserver2) {
                SwitchMapInnerObserver<Object, Object> switchMapInnerObserver3 = (SwitchMapInnerObserver) this.active.getAndSet(switchMapInnerObserver2);
                if (switchMapInnerObserver3 != CANCELLED && switchMapInnerObserver3 != null) {
                    switchMapInnerObserver3.cancel();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            Object obj;
            if (getAndIncrement() == 0) {
                Observer<? super R> observer = this.downstream;
                AtomicReference<SwitchMapInnerObserver<T, R>> atomicReference = this.active;
                boolean z = this.delayErrors;
                int i = 1;
                while (!this.cancelled) {
                    if (this.done) {
                        boolean z2 = atomicReference.get() == null;
                        if (z) {
                            if (z2) {
                                Throwable th = (Throwable) this.errors.get();
                                if (th != null) {
                                    observer.onError(th);
                                } else {
                                    observer.onComplete();
                                }
                                return;
                            }
                        } else if (((Throwable) this.errors.get()) != null) {
                            observer.onError(this.errors.terminate());
                            return;
                        } else if (z2) {
                            observer.onComplete();
                            return;
                        }
                    }
                    SwitchMapInnerObserver switchMapInnerObserver = (SwitchMapInnerObserver) atomicReference.get();
                    if (switchMapInnerObserver != null) {
                        SimpleQueue<R> simpleQueue = switchMapInnerObserver.queue;
                        if (simpleQueue != null) {
                            if (switchMapInnerObserver.done) {
                                boolean isEmpty = simpleQueue.isEmpty();
                                if (z) {
                                    if (isEmpty) {
                                        atomicReference.compareAndSet(switchMapInnerObserver, null);
                                    }
                                } else if (((Throwable) this.errors.get()) != null) {
                                    observer.onError(this.errors.terminate());
                                    return;
                                } else if (isEmpty) {
                                    atomicReference.compareAndSet(switchMapInnerObserver, null);
                                }
                            }
                            boolean z3 = false;
                            while (!this.cancelled) {
                                if (switchMapInnerObserver != atomicReference.get()) {
                                    z3 = true;
                                } else if (z || ((Throwable) this.errors.get()) == null) {
                                    boolean z4 = switchMapInnerObserver.done;
                                    try {
                                        obj = simpleQueue.poll();
                                    } catch (Throwable th2) {
                                        Exceptions.throwIfFatal(th2);
                                        this.errors.addThrowable(th2);
                                        atomicReference.compareAndSet(switchMapInnerObserver, null);
                                        if (!z) {
                                            disposeInner();
                                            this.upstream.dispose();
                                            this.done = true;
                                        } else {
                                            switchMapInnerObserver.cancel();
                                        }
                                        obj = null;
                                        z3 = true;
                                    }
                                    boolean z5 = obj == null;
                                    if (z4 && z5) {
                                        atomicReference.compareAndSet(switchMapInnerObserver, null);
                                        z3 = true;
                                    } else if (!z5) {
                                        observer.onNext(obj);
                                    }
                                } else {
                                    observer.onError(this.errors.terminate());
                                    return;
                                }
                                if (z3) {
                                    continue;
                                }
                            }
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

        /* access modifiers changed from: 0000 */
        public void innerError(SwitchMapInnerObserver<T, R> switchMapInnerObserver, Throwable th) {
            if (switchMapInnerObserver.index != this.unique || !this.errors.addThrowable(th)) {
                RxJavaPlugins.onError(th);
                return;
            }
            if (!this.delayErrors) {
                this.upstream.dispose();
            }
            switchMapInnerObserver.done = true;
            drain();
        }
    }

    public void subscribeActual(Observer<? super R> observer) {
        if (!ObservableScalarXMap.tryScalarXMapSubscribe(this.source, observer, this.mapper)) {
            this.source.subscribe(new SwitchMapObserver(observer, this.mapper, this.bufferSize, this.delayErrors));
        }
    }
}
