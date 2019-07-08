package com.uacf.core.util;

import java.lang.Exception;

public interface CheckedFunction3<T, U, V, ExceptionType extends Exception> {
    void execute(T t, U u, V v) throws Exception;
}
