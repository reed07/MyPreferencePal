package com.uacf.core.logging;

public interface Printer {
    void clear();

    void printException(Throwable th, Object obj, Object... objArr);

    int println(int i, String str);

    void setEnabled(boolean z);
}
