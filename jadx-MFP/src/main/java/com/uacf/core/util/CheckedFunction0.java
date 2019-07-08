package com.uacf.core.util;

import java.lang.Exception;

public interface CheckedFunction0<ExceptionType extends Exception> {
    void execute() throws Exception;
}
