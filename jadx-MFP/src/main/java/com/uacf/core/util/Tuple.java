package com.uacf.core.util;

public final class Tuple {
    public static <T1> Tuple1<T1> create(T1 t1) {
        return new Tuple1<>(t1);
    }

    public static <T1, T2> Tuple2<T1, T2> create(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> create(T1 t1, T2 t2, T3 t3) {
        return new Tuple3<>(t1, t2, t3);
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> create(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new Tuple4<>(t1, t2, t3, t4);
    }
}
