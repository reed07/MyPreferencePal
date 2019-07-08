package com.lightstep.tracer.shared;

public class SimpleFuture<T> {
    private boolean resolved = false;
    private T value;

    public SimpleFuture() {
    }

    public SimpleFuture(T t) {
        this.value = t;
    }

    public void set(T t) {
        synchronized (this) {
            this.value = t;
            this.resolved = true;
            notifyAll();
        }
    }

    public T get() throws InterruptedException {
        if (!this.resolved) {
            synchronized (this) {
                wait();
            }
        }
        return this.value;
    }
}
