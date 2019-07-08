package com.uacf.core.performance;

public interface PerformanceMonitor {

    public enum CreationBehavior {
        OverwriteExisting,
        AutomaticStart
    }

    public interface Timer {
    }

    public interface TimerFactory {
    }
}
