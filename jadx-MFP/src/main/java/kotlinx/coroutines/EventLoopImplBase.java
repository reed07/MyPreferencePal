package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b \u0018\u00002\u00020\u00012\u00020\u0002:\u0003123B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001aH\u0002J\u001a\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060\u0019j\u0002`\u001aJ\u0012\u0010\u001f\u001a\u00020\u00172\n\u0010 \u001a\u00060\u0019j\u0002`\u001aJ\u0014\u0010!\u001a\u00020\u000b2\n\u0010 \u001a\u00060\u0019j\u0002`\u001aH\u0002J\b\u0010\"\u001a\u00020\u000fH\u0016J\b\u0010#\u001a\u00020\u0017H\u0002J\b\u0010$\u001a\u00020\u0017H\u0004J\u0015\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0007H\u0000¢\u0006\u0002\b'J\u0010\u0010(\u001a\u00020)2\u0006\u0010&\u001a\u00020\u0007H\u0002J\u001e\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u000f2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00170-H\u0016J\u0010\u0010.\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0007H\u0002J\b\u0010/\u001a\u00020\u0017H\u0014J\b\u00100\u001a\u00020\u0017H\u0002R\u001c\u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u000b8TX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8TX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u00064"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase;", "Lkotlinx/coroutines/EventLoop;", "Lkotlinx/coroutines/Delay;", "()V", "_delayed", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "_queue", "", "isCompleted", "", "isEmpty", "()Z", "nextTime", "", "getNextTime", "()J", "thread", "Ljava/lang/Thread;", "getThread", "()Ljava/lang/Thread;", "closeQueue", "", "dequeue", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "enqueue", "task", "enqueueImpl", "processNextEvent", "rescheduleAllDelayed", "resetAll", "schedule", "delayedTask", "schedule$kotlinx_coroutines_core", "scheduleImpl", "", "scheduleResumeAfterDelay", "timeMillis", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "shouldUnpark", "shutdown", "unpark", "DelayedResumeTask", "DelayedRunnableTask", "DelayedTask", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: EventLoop.kt */
public abstract class EventLoopImplBase extends EventLoop implements Delay {
    private static final AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");
    private static final AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
    private volatile Object _delayed = null;
    private volatile Object _queue = null;
    /* access modifiers changed from: private */
    public volatile boolean isCompleted;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedRunnableTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "time", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(JLjava/lang/Runnable;)V", "run", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: EventLoop.kt */
    public static final class DelayedRunnableTask extends DelayedTask {
        private final Runnable block;

        public void run() {
            this.block.run();
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(this.block.toString());
            return sb.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\u00020\u00042\u00020\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0000H\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u001c\u0010\u001e\u001a\u00020\u00132\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00000\f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0007J\b\u0010%\u001a\u00020&H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R0\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f8V@VX\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "timeMillis", "", "(J)V", "_heap", "", "value", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "nanoTime", "compareTo", "other", "dispose", "", "rescheduleOnShutdown", "schedule", "delayed", "eventLoop", "Lkotlinx/coroutines/EventLoopImplBase;", "timeToExecute", "", "now", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: EventLoop.kt */
    public static abstract class DelayedTask implements Comparable<DelayedTask>, Runnable, DisposableHandle, ThreadSafeHeapNode {
        private Object _heap;
        private int index;
        @JvmField
        public final long nanoTime;

        @Nullable
        public ThreadSafeHeap<?> getHeap() {
            Object obj = this._heap;
            if (!(obj instanceof ThreadSafeHeap)) {
                obj = null;
            }
            return (ThreadSafeHeap) obj;
        }

        public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
            if (this._heap != EventLoopKt.DISPOSED_TASK) {
                this._heap = threadSafeHeap;
                return;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            this.index = i;
        }

        public int compareTo(@NotNull DelayedTask delayedTask) {
            Intrinsics.checkParameterIsNotNull(delayedTask, "other");
            int i = ((this.nanoTime - delayedTask.nanoTime) > 0 ? 1 : ((this.nanoTime - delayedTask.nanoTime) == 0 ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i < 0 ? -1 : 0;
        }

        public final boolean timeToExecute(long j) {
            return j - this.nanoTime >= 0;
        }

        public final synchronized int schedule(@NotNull ThreadSafeHeap<DelayedTask> threadSafeHeap, @NotNull EventLoopImplBase eventLoopImplBase) {
            int i;
            Intrinsics.checkParameterIsNotNull(threadSafeHeap, "delayed");
            Intrinsics.checkParameterIsNotNull(eventLoopImplBase, "eventLoop");
            if (this._heap == EventLoopKt.DISPOSED_TASK) {
                return 2;
            }
            ThreadSafeHeapNode threadSafeHeapNode = this;
            synchronized (threadSafeHeap) {
                if (!eventLoopImplBase.isCompleted) {
                    threadSafeHeap.addImpl(threadSafeHeapNode);
                    i = 1;
                } else {
                    i = 0;
                }
            }
            return i ^ 1;
        }

        public final void rescheduleOnShutdown() {
            DefaultExecutor.INSTANCE.schedule$kotlinx_coroutines_core(this);
        }

        public final synchronized void dispose() {
            Object obj = this._heap;
            if (obj != EventLoopKt.DISPOSED_TASK) {
                if (!(obj instanceof ThreadSafeHeap)) {
                    obj = null;
                }
                ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) obj;
                if (threadSafeHeap != null) {
                    threadSafeHeap.remove(this);
                }
                this._heap = EventLoopKt.DISPOSED_TASK;
            }
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Delayed[nanos=");
            sb.append(this.nanoTime);
            sb.append(']');
            return sb.toString();
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract Thread getThread();

    /* access modifiers changed from: protected */
    public boolean isEmpty() {
        boolean z = false;
        if (!isUnconfinedQueueEmpty()) {
            return false;
        }
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap != null && !threadSafeHeap.isEmpty()) {
            return false;
        }
        Object obj = this._queue;
        if (obj == null) {
            z = true;
        } else if (obj instanceof LockFreeTaskQueueCore) {
            z = ((LockFreeTaskQueueCore) obj).isEmpty();
        } else if (obj == EventLoopKt.CLOSED_EMPTY) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public long getNextTime() {
        if (super.getNextTime() == 0) {
            return 0;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (obj instanceof LockFreeTaskQueueCore) {
                if (!((LockFreeTaskQueueCore) obj).isEmpty()) {
                    return 0;
                }
            } else if (obj == EventLoopKt.CLOSED_EMPTY) {
                return Long.MAX_VALUE;
            } else {
                return 0;
            }
        }
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap == null) {
            return Long.MAX_VALUE;
        }
        DelayedTask delayedTask = (DelayedTask) threadSafeHeap.peek();
        if (delayedTask != null) {
            return RangesKt.coerceAtLeast(delayedTask.nanoTime - TimeSourceKt.getTimeSource().nanoTime(), 0);
        }
        return Long.MAX_VALUE;
    }

    private final void unpark() {
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            TimeSourceKt.getTimeSource().unpark(thread);
        }
    }

    /* access modifiers changed from: protected */
    public void shutdown() {
        ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
        this.isCompleted = true;
        closeQueue();
        do {
        } while (processNextEvent() <= 0);
        rescheduleAllDelayed();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long processNextEvent() {
        /*
            r7 = this;
            boolean r0 = r7.processUnconfinedEvent()
            if (r0 == 0) goto L_0x000b
            long r0 = r7.getNextTime()
            return r0
        L_0x000b:
            java.lang.Object r0 = r7._delayed
            kotlinx.coroutines.internal.ThreadSafeHeap r0 = (kotlinx.coroutines.internal.ThreadSafeHeap) r0
            if (r0 == 0) goto L_0x004a
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x004a
            kotlinx.coroutines.TimeSource r1 = kotlinx.coroutines.TimeSourceKt.getTimeSource()
            long r1 = r1.nanoTime()
        L_0x001f:
            monitor-enter(r0)
            kotlinx.coroutines.internal.ThreadSafeHeapNode r3 = r0.firstImpl()     // Catch:{ all -> 0x0047 }
            r4 = 0
            if (r3 == 0) goto L_0x0041
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r3 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r3     // Catch:{ all -> 0x0047 }
            boolean r5 = r3.timeToExecute(r1)     // Catch:{ all -> 0x0047 }
            r6 = 0
            if (r5 == 0) goto L_0x0037
            java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch:{ all -> 0x0047 }
            boolean r3 = r7.enqueueImpl(r3)     // Catch:{ all -> 0x0047 }
            goto L_0x0038
        L_0x0037:
            r3 = 0
        L_0x0038:
            if (r3 == 0) goto L_0x003f
            kotlinx.coroutines.internal.ThreadSafeHeapNode r3 = r0.removeAtImpl(r6)     // Catch:{ all -> 0x0047 }
            r4 = r3
        L_0x003f:
            monitor-exit(r0)
            goto L_0x0042
        L_0x0041:
            monitor-exit(r0)
        L_0x0042:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r4 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r4
            if (r4 == 0) goto L_0x004a
            goto L_0x001f
        L_0x0047:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x004a:
            java.lang.Runnable r0 = r7.dequeue()
            if (r0 == 0) goto L_0x0053
            r0.run()
        L_0x0053:
            long r0 = r7.getNextTime()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.processNextEvent():long");
    }

    public final void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        enqueue(runnable);
    }

    public final void enqueue(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "task");
        if (enqueueImpl(runnable)) {
            unpark();
        } else {
            DefaultExecutor.INSTANCE.enqueue(runnable);
        }
    }

    private final void closeQueue() {
        boolean z = this.isCompleted;
        if (!_Assertions.ENABLED || z) {
            while (true) {
                Object obj = this._queue;
                if (obj == null) {
                    if (_queue$FU.compareAndSet(this, null, EventLoopKt.CLOSED_EMPTY)) {
                        return;
                    }
                } else if (obj instanceof LockFreeTaskQueueCore) {
                    ((LockFreeTaskQueueCore) obj).close();
                    return;
                } else if (obj != EventLoopKt.CLOSED_EMPTY) {
                    LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                    if (obj != null) {
                        lockFreeTaskQueueCore.addLast((Runnable) obj);
                        if (_queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore)) {
                            return;
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
                    }
                } else {
                    return;
                }
            }
        } else {
            throw new AssertionError("Assertion failed");
        }
    }

    public final void schedule$kotlinx_coroutines_core(@NotNull DelayedTask delayedTask) {
        Intrinsics.checkParameterIsNotNull(delayedTask, "delayedTask");
        switch (scheduleImpl(delayedTask)) {
            case 0:
                if (shouldUnpark(delayedTask)) {
                    unpark();
                    return;
                }
                return;
            case 1:
                DefaultExecutor.INSTANCE.schedule$kotlinx_coroutines_core(delayedTask);
                return;
            case 2:
                return;
            default:
                throw new IllegalStateException("unexpected result".toString());
        }
    }

    private final boolean shouldUnpark(DelayedTask delayedTask) {
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        return (threadSafeHeap != null ? (DelayedTask) threadSafeHeap.peek() : null) == delayedTask;
    }

    private final int scheduleImpl(DelayedTask delayedTask) {
        if (this.isCompleted) {
            return 1;
        }
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap == null) {
            EventLoopImplBase eventLoopImplBase = this;
            _delayed$FU.compareAndSet(eventLoopImplBase, null, new ThreadSafeHeap());
            Object obj = eventLoopImplBase._delayed;
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            threadSafeHeap = (ThreadSafeHeap) obj;
        }
        return delayedTask.schedule(threadSafeHeap, this);
    }

    /* access modifiers changed from: protected */
    public final void resetAll() {
        this._queue = null;
        this._delayed = null;
    }

    private final void rescheduleAllDelayed() {
        while (true) {
            ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
            if (threadSafeHeap != null) {
                DelayedTask delayedTask = (DelayedTask) threadSafeHeap.removeFirstOrNull();
                if (delayedTask != null) {
                    delayedTask.rescheduleOnShutdown();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private final boolean enqueueImpl(Runnable runnable) {
        while (true) {
            Object obj = this._queue;
            if (this.isCompleted) {
                return false;
            }
            if (obj == null) {
                if (_queue$FU.compareAndSet(this, null, runnable)) {
                    return true;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                if (obj != null) {
                    LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                    switch (lockFreeTaskQueueCore.addLast(runnable)) {
                        case 0:
                            return true;
                        case 1:
                            _queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore.next());
                            break;
                        case 2:
                            return false;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Queue<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> /* = kotlinx.coroutines.internal.LockFreeTaskQueueCore<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> */");
                }
            } else if (obj == EventLoopKt.CLOSED_EMPTY) {
                return false;
            } else {
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(8, true);
                if (obj != null) {
                    lockFreeTaskQueueCore2.addLast((Runnable) obj);
                    lockFreeTaskQueueCore2.addLast(runnable);
                    if (_queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore2)) {
                        return true;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
                }
            }
        }
    }

    private final Runnable dequeue() {
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                return null;
            }
            if (obj instanceof LockFreeTaskQueueCore) {
                if (obj != null) {
                    LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                    Object removeFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
                    if (removeFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                        return (Runnable) removeFirstOrNull;
                    }
                    _queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore.next());
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Queue<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> /* = kotlinx.coroutines.internal.LockFreeTaskQueueCore<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> */");
                }
            } else if (obj == EventLoopKt.CLOSED_EMPTY) {
                return null;
            } else {
                if (_queue$FU.compareAndSet(this, obj, null)) {
                    if (obj != null) {
                        return (Runnable) obj;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
                }
            }
        }
    }
}
