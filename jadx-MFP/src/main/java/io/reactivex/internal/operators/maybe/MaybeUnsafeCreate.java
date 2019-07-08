package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;

public final class MaybeUnsafeCreate<T> extends AbstractMaybeWithUpstream<T, T> {
    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(maybeObserver);
    }
}
