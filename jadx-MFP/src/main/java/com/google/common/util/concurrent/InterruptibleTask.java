package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.j2objc.annotations.ReflectionSupport;
import com.google.j2objc.annotations.ReflectionSupport.Level;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
@ReflectionSupport(Level.FULL)
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE = new DoNothingRunnable();
    private static final Runnable INTERRUPTING = new DoNothingRunnable();

    private static final class DoNothingRunnable implements Runnable {
        public void run() {
        }

        private DoNothingRunnable() {
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void afterRanInterruptibly(@NullableDecl T t, @NullableDecl Throwable th);

    /* access modifiers changed from: 0000 */
    public abstract boolean isDone();

    /* access modifiers changed from: 0000 */
    public abstract T runInterruptibly() throws Exception;

    /* access modifiers changed from: 0000 */
    public abstract String toPendingString();

    InterruptibleTask() {
    }

    public final void run() {
        Object obj;
        Thread currentThread = Thread.currentThread();
        if (compareAndSet(null, currentThread)) {
            boolean z = !isDone();
            if (z) {
                try {
                    obj = runInterruptibly();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, DONE)) {
                        while (get() == INTERRUPTING) {
                            Thread.yield();
                        }
                    }
                    if (z) {
                        afterRanInterruptibly(null, null);
                    }
                    throw th;
                }
            } else {
                obj = null;
            }
            if (!compareAndSet(currentThread, DONE)) {
                while (get() == INTERRUPTING) {
                    Thread.yield();
                }
            }
            if (z) {
                afterRanInterruptibly(obj, null);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void interruptTask() {
        Runnable runnable = (Runnable) get();
        if ((runnable instanceof Thread) && compareAndSet(runnable, INTERRUPTING)) {
            ((Thread) runnable).interrupt();
            set(DONE);
        }
    }

    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable == INTERRUPTING) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            StringBuilder sb = new StringBuilder();
            sb.append("running=[RUNNING ON ");
            sb.append(((Thread) runnable).getName());
            sb.append("]");
            str = sb.toString();
        } else {
            str = "running=[NOT STARTED YET]";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(", ");
        sb2.append(toPendingString());
        return sb2.toString();
    }
}
