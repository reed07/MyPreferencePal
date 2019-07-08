package com.uacf.core.util;

import java.lang.Exception;

public interface CheckedReturningFunction1<ReturnType, T, ExceptionType extends Exception> {
    ReturnType execute(T t) throws Exception;
}
