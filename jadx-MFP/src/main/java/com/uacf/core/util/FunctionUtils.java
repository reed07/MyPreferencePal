package com.uacf.core.util;

import android.os.Handler;

public class FunctionUtils {
    public static void invokeIfValid(Function0 function0) {
        if (function0 != null) {
            function0.execute();
        }
    }

    public static void invokeDelayed(long j, Function0 function0) {
        invokeIfValid(new Handler(), function0, j);
    }

    public static <T> void invokeIfValid(Function1<T> function1, T t) {
        if (function1 != null) {
            function1.execute(t);
        }
    }

    public static <T, U> void invokeIfValid(Function2<T, U> function2, T t, U u) {
        if (function2 != null) {
            function2.execute(t, u);
        }
    }

    public static <T, U, V> void invokeIfValid(Function3<T, U, V> function3, T t, U u, V v) {
        if (function3 != null) {
            function3.execute(t, u, v);
        }
    }

    public static void invokeIfValid(Handler handler, final Function0 function0, long j) {
        if (function0 != null) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    function0.execute();
                }
            }, j);
        }
    }

    public static <T> void invokeIfValid(Handler handler, final Function1<T> function1, final T t, long j) {
        if (function1 != null) {
            handler.post(new Runnable() {
                public void run() {
                    function1.execute(t);
                }
            });
        }
    }

    public static <T, U> void invokeIfValid(Handler handler, final Function2<T, U> function2, final T t, final U u) {
        if (function2 != null) {
            handler.post(new Runnable() {
                public void run() {
                    function2.execute(t, u);
                }
            });
        }
    }

    public static <T1, T2, T3> void invokeIfValid(Handler handler, final Function3<T1, T2, T3> function3, final T1 t1, final T2 t2, final T3 t3) {
        if (function3 != null) {
            handler.post(new Runnable() {
                public void run() {
                    function3.execute(t1, t2, t3);
                }
            });
        }
    }
}
