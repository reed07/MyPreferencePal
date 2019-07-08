package com.uacf.core.util;

public class Tuple2<T1, T2> extends Tuple1<T1> {
    private T2 item2;

    Tuple2(T1 t1, T2 t2) {
        super(t1);
        this.item2 = t2;
    }

    public static <T1, T2> Tuple2<T1, T2> create(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    public T2 getItem2() {
        return this.item2;
    }

    public Tuple2<T1, T2> setItem2(T2 t2) {
        this.item2 = t2;
        return this;
    }
}
