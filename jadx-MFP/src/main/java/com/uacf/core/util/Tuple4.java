package com.uacf.core.util;

public class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {
    private T4 item4;

    public Tuple4(T1 t1, T2 t2, T3 t3, T4 t4) {
        super(t1, t2, t3);
        this.item4 = t4;
    }

    public T4 getItem4() {
        return this.item4;
    }

    public Tuple4<T1, T2, T3, T4> setItem4(T4 t4) {
        this.item4 = t4;
        return this;
    }
}
