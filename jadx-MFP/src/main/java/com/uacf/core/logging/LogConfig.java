package com.uacf.core.logging;

public interface LogConfig {
    void forceDebugLogging(boolean z);

    int getLoggingLevel();

    void setLoggingLevel(int i);
}
