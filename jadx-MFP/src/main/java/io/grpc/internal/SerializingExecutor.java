package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class SerializingExecutor implements Runnable, Executor {
    private static final AtomicHelper atomicHelper = getAtomicHelper();
    private static final Logger log = Logger.getLogger(SerializingExecutor.class.getName());
    private final Executor executor;
    private final Queue<Runnable> runQueue = new ConcurrentLinkedQueue();
    /* access modifiers changed from: private */
    public volatile int runState = 0;

    private static abstract class AtomicHelper {
        public abstract boolean runStateCompareAndSet(SerializingExecutor serializingExecutor, int i, int i2);

        public abstract void runStateSet(SerializingExecutor serializingExecutor, int i);

        private AtomicHelper() {
        }
    }

    private static final class FieldUpdaterAtomicHelper extends AtomicHelper {
        private final AtomicIntegerFieldUpdater<SerializingExecutor> runStateUpdater;

        private FieldUpdaterAtomicHelper(AtomicIntegerFieldUpdater<SerializingExecutor> atomicIntegerFieldUpdater) {
            super();
            this.runStateUpdater = atomicIntegerFieldUpdater;
        }

        public boolean runStateCompareAndSet(SerializingExecutor serializingExecutor, int i, int i2) {
            return this.runStateUpdater.compareAndSet(serializingExecutor, i, i2);
        }

        public void runStateSet(SerializingExecutor serializingExecutor, int i) {
            this.runStateUpdater.set(serializingExecutor, i);
        }
    }

    private static final class SynchronizedAtomicHelper extends AtomicHelper {
        private SynchronizedAtomicHelper() {
            super();
        }

        public boolean runStateCompareAndSet(SerializingExecutor serializingExecutor, int i, int i2) {
            synchronized (serializingExecutor) {
                if (serializingExecutor.runState != i) {
                    return false;
                }
                serializingExecutor.runState = i2;
                return true;
            }
        }

        public void runStateSet(SerializingExecutor serializingExecutor, int i) {
            synchronized (serializingExecutor) {
                serializingExecutor.runState = i;
            }
        }
    }

    private static AtomicHelper getAtomicHelper() {
        try {
            return new FieldUpdaterAtomicHelper(AtomicIntegerFieldUpdater.newUpdater(SerializingExecutor.class, "runState"));
        } catch (Throwable th) {
            log.log(Level.SEVERE, "FieldUpdaterAtomicHelper failed", th);
            return new SynchronizedAtomicHelper();
        }
    }

    public SerializingExecutor(Executor executor2) {
        Preconditions.checkNotNull(executor2, "'executor' must not be null.");
        this.executor = executor2;
    }

    public void execute(Runnable runnable) {
        this.runQueue.add(Preconditions.checkNotNull(runnable, "'r' must not be null."));
        schedule(runnable);
    }

    private void schedule(@Nullable Runnable runnable) {
        if (atomicHelper.runStateCompareAndSet(this, 0, -1)) {
            try {
                this.executor.execute(this);
            } catch (Throwable th) {
                if (runnable != null) {
                    this.runQueue.remove(runnable);
                }
                atomicHelper.runStateSet(this, 0);
                throw th;
            }
        }
    }

    public void run() {
        Runnable runnable;
        while (true) {
            try {
                runnable = (Runnable) this.runQueue.poll();
                if (runnable == null) {
                    break;
                }
                runnable.run();
            } catch (RuntimeException e) {
                Logger logger = log;
                Level level = Level.SEVERE;
                StringBuilder sb = new StringBuilder();
                sb.append("Exception while executing runnable ");
                sb.append(runnable);
                logger.log(level, sb.toString(), e);
            } catch (Throwable th) {
                atomicHelper.runStateSet(this, 0);
                throw th;
            }
        }
        atomicHelper.runStateSet(this, 0);
        if (!this.runQueue.isEmpty()) {
            schedule(null);
        }
    }
}
