package com.uacf.core.util;

import java.lang.Exception;

public interface CheckedReturningFunction2<ReturnType, T1, T2, ExceptionType extends Exception> {
    ReturnType execute(T1 t1, T2 t2) throws Exception;
}
