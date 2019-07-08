package com.uacf.core.util;

import java.lang.Exception;

public interface CheckedFunction1<T, ExceptionType extends Exception> {
    void execute(T t) throws Exception;
}
