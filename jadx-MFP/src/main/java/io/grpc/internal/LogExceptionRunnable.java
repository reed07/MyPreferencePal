package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LogExceptionRunnable implements Runnable {
    private static final Logger log = Logger.getLogger(LogExceptionRunnable.class.getName());
    private final Runnable task;

    public LogExceptionRunnable(Runnable runnable) {
        this.task = (Runnable) Preconditions.checkNotNull(runnable, "task");
    }

    public void run() {
        try {
            this.task.run();
        } catch (Throwable th) {
            Logger logger = log;
            Level level = Level.SEVERE;
            StringBuilder sb = new StringBuilder();
            sb.append("Exception while executing runnable ");
            sb.append(this.task);
            logger.log(level, sb.toString(), th);
            MoreThrowables.throwIfUnchecked(th);
            throw new AssertionError(th);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LogExceptionRunnable(");
        sb.append(this.task);
        sb.append(")");
        return sb.toString();
    }
}
