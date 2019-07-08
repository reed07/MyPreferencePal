package com.uacf.core.asyncservice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ReadWriteServiceBase extends SimpleAsyncServiceBase {
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    private class Runner implements Runnable {
        private Lock lock;
        private Runnable wrapped;

        public Runner(Lock lock2, Runnable runnable) {
            this.wrapped = runnable;
            this.lock = lock2;
        }

        public void run() {
            this.lock.lock();
            try {
                this.wrapped.run();
            } finally {
                this.lock.unlock();
            }
        }
    }

    public void read(Runnable runnable) {
        async(new Runner(this.rwl.readLock(), runnable));
    }

    public void write(Runnable runnable) {
        async(new Runner(this.rwl.writeLock(), runnable));
    }
}
