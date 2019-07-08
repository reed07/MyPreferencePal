package com.uacf.core.performance;

import com.uacf.core.performance.PerformanceMonitor.TimerFactory;
import com.uacf.core.performance.PerformanceMonitorImpl.TimerBase;

public class StopwatchTimerImpl extends TimerBase {
    public static final TimerFactory CREATOR = new TimerFactory() {
    };
}
