package com.uacf.taskrunner;

import android.content.Context;
import java.lang.Throwable;

public interface Task<ResultT, ErrorT extends Throwable> {
    ResultT get() throws Throwable;

    ResultT run(Context context) throws Throwable;
}
