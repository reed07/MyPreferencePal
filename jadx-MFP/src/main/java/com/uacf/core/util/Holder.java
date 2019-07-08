package com.uacf.core.util;

public class Holder<T> {
    private T value;

    public Holder() {
    }

    public Holder(T t) {
        this.value = t;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T t) {
        this.value = t;
    }
}
