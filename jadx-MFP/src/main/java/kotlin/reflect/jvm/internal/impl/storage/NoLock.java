package kotlin.reflect.jvm.internal.impl.storage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.jetbrains.annotations.NotNull;

class NoLock implements Lock {
    public static final Lock INSTANCE = new NoLock();

    public void lock() {
    }

    public void unlock() {
    }

    private NoLock() {
    }

    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("Should not be called");
    }

    public boolean tryLock() {
        throw new UnsupportedOperationException("Should not be called");
    }

    public boolean tryLock(long j, @NotNull TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException("Should not be called");
    }

    @NotNull
    public Condition newCondition() {
        throw new UnsupportedOperationException("Should not be called");
    }
}
