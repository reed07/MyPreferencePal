package com.facebook.ads.internal.w.b;

import java.lang.ref.WeakReference;

public abstract class y<T> implements Runnable {
    private final WeakReference<T> a;

    public y(T t) {
        this.a = new WeakReference<>(t);
    }

    public T a() {
        return this.a.get();
    }
}
