package kotlinx.coroutines.scheduling;

import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0015\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0018J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005J!\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u001dH\bJ\r\u0010\u001e\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u001fJ\u0010\u0010 \u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0016\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013J \u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", "", "()V", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "Lkotlinx/coroutines/scheduling/Task;", "bufferSize", "", "getBufferSize$kotlinx_coroutines_core", "()I", "consumerIndex", "Lkotlinx/atomicfu/AtomicInt;", "lastScheduledTask", "Lkotlinx/atomicfu/AtomicRef;", "producerIndex", "add", "", "task", "globalQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "addLast", "addToGlobalQueue", "", "offloadAllWork", "offloadAllWork$kotlinx_coroutines_core", "offloadWork", "poll", "pollExternal", "predicate", "Lkotlin/Function1;", "size", "size$kotlinx_coroutines_core", "tryAddLast", "trySteal", "victim", "tryStealLastScheduled", "time", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: WorkQueue.kt */
public final class WorkQueue {
    static final AtomicIntegerFieldUpdater consumerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");
    private static final AtomicReferenceFieldUpdater lastScheduledTask$FU = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");
    static final AtomicIntegerFieldUpdater producerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");
    /* access modifiers changed from: private */
    public final AtomicReferenceArray<Task> buffer = new AtomicReferenceArray<>(128);
    volatile int consumerIndex = 0;
    private volatile Object lastScheduledTask = null;
    volatile int producerIndex = 0;

    public final int getBufferSize$kotlinx_coroutines_core() {
        return this.producerIndex - this.consumerIndex;
    }

    @Nullable
    public final Task poll() {
        Task task = (Task) lastScheduledTask$FU.getAndSet(this, null);
        if (task != null) {
            return task;
        }
        while (true) {
            int i = this.consumerIndex;
            if (i - this.producerIndex == 0) {
                return null;
            }
            int i2 = i & Statements.GetOwnedFoodIdsDateDescending;
            if (((Task) this.buffer.get(i2)) != null && consumerIndex$FU.compareAndSet(this, i, i + 1)) {
                return (Task) this.buffer.getAndSet(i2, null);
            }
        }
    }

    public final boolean add(@NotNull Task task, @NotNull GlobalQueue globalQueue) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(globalQueue, "globalQueue");
        Task task2 = (Task) lastScheduledTask$FU.getAndSet(this, task);
        if (task2 != null) {
            return addLast(task2, globalQueue);
        }
        return true;
    }

    public final boolean addLast(@NotNull Task task, @NotNull GlobalQueue globalQueue) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(globalQueue, "globalQueue");
        boolean z = true;
        while (!tryAddLast(task)) {
            offloadWork(globalQueue);
            z = false;
        }
        return z;
    }

    public final boolean trySteal(@NotNull WorkQueue workQueue, @NotNull GlobalQueue globalQueue) {
        Task task;
        WorkQueue workQueue2 = workQueue;
        GlobalQueue globalQueue2 = globalQueue;
        Intrinsics.checkParameterIsNotNull(workQueue2, "victim");
        Intrinsics.checkParameterIsNotNull(globalQueue2, "globalQueue");
        long nanoTime = TasksKt.schedulerTimeSource.nanoTime();
        int bufferSize$kotlinx_coroutines_core = workQueue.getBufferSize$kotlinx_coroutines_core();
        if (bufferSize$kotlinx_coroutines_core == 0) {
            return tryStealLastScheduled(nanoTime, workQueue2, globalQueue2);
        }
        int coerceAtLeast = RangesKt.coerceAtLeast(bufferSize$kotlinx_coroutines_core / 2, 1);
        int i = 0;
        boolean z = false;
        while (i < coerceAtLeast) {
            while (true) {
                int i2 = workQueue2.consumerIndex;
                task = null;
                if (i2 - workQueue2.producerIndex != 0) {
                    int i3 = i2 & Statements.GetOwnedFoodIdsDateDescending;
                    Task task2 = (Task) workQueue.buffer.get(i3);
                    if (task2 != null) {
                        if (!(nanoTime - task2.submissionTime >= TasksKt.WORK_STEALING_TIME_RESOLUTION_NS || workQueue.getBufferSize$kotlinx_coroutines_core() > TasksKt.QUEUE_SIZE_OFFLOAD_THRESHOLD)) {
                            break;
                        } else if (consumerIndex$FU.compareAndSet(workQueue2, i2, i2 + 1)) {
                            task = (Task) workQueue.buffer.getAndSet(i3, null);
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
            if (task == null) {
                return z;
            }
            add(task, globalQueue2);
            i++;
            z = true;
        }
        return z;
    }

    private final boolean tryStealLastScheduled(long j, WorkQueue workQueue, GlobalQueue globalQueue) {
        Task task = (Task) workQueue.lastScheduledTask;
        if (task == null || j - task.submissionTime < TasksKt.WORK_STEALING_TIME_RESOLUTION_NS || !lastScheduledTask$FU.compareAndSet(workQueue, task, null)) {
            return false;
        }
        add(task, globalQueue);
        return true;
    }

    public final int size$kotlinx_coroutines_core() {
        return this.lastScheduledTask != null ? getBufferSize$kotlinx_coroutines_core() + 1 : getBufferSize$kotlinx_coroutines_core();
    }

    private final void offloadWork(GlobalQueue globalQueue) {
        Task task;
        int coerceAtLeast = RangesKt.coerceAtLeast(getBufferSize$kotlinx_coroutines_core() / 2, 1);
        int i = 0;
        while (i < coerceAtLeast) {
            while (true) {
                int i2 = this.consumerIndex;
                task = null;
                if (i2 - this.producerIndex != 0) {
                    int i3 = i2 & Statements.GetOwnedFoodIdsDateDescending;
                    if (((Task) this.buffer.get(i3)) != null && consumerIndex$FU.compareAndSet(this, i2, i2 + 1)) {
                        task = (Task) this.buffer.getAndSet(i3, null);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (task != null) {
                addToGlobalQueue(globalQueue, task);
                i++;
            } else {
                return;
            }
        }
    }

    private final void addToGlobalQueue(GlobalQueue globalQueue, Task task) {
        if (!globalQueue.addLast(task)) {
            throw new IllegalStateException("GlobalQueue could not be closed yet".toString());
        }
    }

    public final void offloadAllWork$kotlinx_coroutines_core(@NotNull GlobalQueue globalQueue) {
        Task task;
        Intrinsics.checkParameterIsNotNull(globalQueue, "globalQueue");
        Task task2 = (Task) lastScheduledTask$FU.getAndSet(this, null);
        if (task2 != null) {
            addToGlobalQueue(globalQueue, task2);
        }
        while (true) {
            int i = this.consumerIndex;
            if (i - this.producerIndex == 0) {
                task = null;
            } else {
                int i2 = i & Statements.GetOwnedFoodIdsDateDescending;
                if (((Task) this.buffer.get(i2)) != null && consumerIndex$FU.compareAndSet(this, i, i + 1)) {
                    task = (Task) this.buffer.getAndSet(i2, null);
                }
            }
            if (task != null) {
                addToGlobalQueue(globalQueue, task);
            } else {
                return;
            }
        }
    }

    private final boolean tryAddLast(Task task) {
        if (getBufferSize$kotlinx_coroutines_core() == 127) {
            return false;
        }
        int i = this.producerIndex & Statements.GetOwnedFoodIdsDateDescending;
        if (this.buffer.get(i) != null) {
            return false;
        }
        this.buffer.lazySet(i, task);
        producerIndex$FU.incrementAndGet(this);
        return true;
    }
}
