package com.uacf.core.util;

public class Tuple1<T1> {
    private T1 item1;

    Tuple1(T1 t1) {
        this.item1 = t1;
    }

    public synchronized T1 getItem1() {
        return this.item1;
    }

    public synchronized Tuple1<T1> setItem1(T1 t1) {
        this.item1 = t1;
        return this;
    }
}
