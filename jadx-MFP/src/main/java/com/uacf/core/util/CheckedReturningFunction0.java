package com.uacf.core.util;

import java.lang.Exception;

public interface CheckedReturningFunction0<ReturnType, ExceptionType extends Exception> {
    ReturnType execute() throws Exception;
}
