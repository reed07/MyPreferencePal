package com.uacf.core.util;

import java.lang.Exception;

public interface CheckedFunction2<T1, T2, ExceptionType extends Exception> {
    void execute(T1 t1, T2 t2) throws Exception;
}
