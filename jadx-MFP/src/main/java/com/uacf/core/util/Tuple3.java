package com.uacf.core.util;

public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {
    private T3 item3;

    Tuple3(T1 t1, T2 t2, T3 t3) {
        super(t1, t2);
        this.item3 = t3;
    }

    public T3 getItem3() {
        return this.item3;
    }

    public Tuple3<T1, T2, T3> setItem3(T3 t3) {
        this.item3 = t3;
        return this;
    }
}
