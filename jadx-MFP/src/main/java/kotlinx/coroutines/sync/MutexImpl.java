package kotlinx.coroutines.sync;

import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuation.DefaultImpls;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.selects.SelectClause2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u0002:\u0007\"#$%&'(B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u001b\u0010\u0012\u001a\u00020\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u001b\u0010\u0015\u001a\u00020\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014JR\u0010\u0016\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u00192\b\u0010\u0011\u001a\u0004\u0018\u00010\u00032\"\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001bH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010!\u001a\u00020\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0016R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00058@X\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\"\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/selects/SelectClause2;", "", "locked", "", "(Z)V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "isLocked", "()Z", "isLockedEmptyQueueState", "isLockedEmptyQueueState$kotlinx_coroutines_core", "onLock", "getOnLock", "()Lkotlinx/coroutines/selects/SelectClause2;", "holdsLock", "owner", "lock", "", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockSuspend", "registerSelectClause2", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "toString", "", "tryLock", "unlock", "LockCont", "LockSelect", "LockWaiter", "LockedQueue", "TryEnqueueLockDesc", "TryLockDesc", "UnlockOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Mutex.kt */
public final class MutexImpl implements SelectClause2<Object, Mutex>, Mutex {
    static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");
    volatile Object _state;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockCont;", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "owner", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeLockWaiter", "token", "toString", "", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static final class LockCont extends LockWaiter {
        @NotNull
        @JvmField
        public final CancellableContinuation<Unit> cont;

        public LockCont(@Nullable Object obj, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            super(obj);
            this.cont = cancellableContinuation;
        }

        @Nullable
        public Object tryResumeLockWaiter() {
            return DefaultImpls.tryResume$default(this.cont, Unit.INSTANCE, null, 2, null);
        }

        public void completeResumeLockWaiter(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, GooglePlayConstants.BILLING_JSON_FIELD_TOKEN);
            this.cont.completeResume(obj);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("LockCont[");
            sb.append(this.owner);
            sb.append(", ");
            sb.append(this.cont);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\"\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H&J\u0006\u0010\t\u001a\u00020\u0007J\n\u0010\n\u001a\u0004\u0018\u00010\u0004H&R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/DisposableHandle;", "owner", "", "(Ljava/lang/Object;)V", "completeResumeLockWaiter", "", "token", "dispose", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {
        @Nullable
        @JvmField
        public final Object owner;

        public abstract void completeResumeLockWaiter(@NotNull Object obj);

        @Nullable
        public abstract Object tryResumeLockWaiter();

        public LockWaiter(@Nullable Object obj) {
            this.owner = obj;
        }

        public final void dispose() {
            remove();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u0012\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "owner", "", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static final class LockedQueue extends LockFreeLinkedListHead {
        @NotNull
        @JvmField
        public Object owner;

        public LockedQueue(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "owner");
            this.owner = obj;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("LockedQueue[");
            sb.append(this.owner);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$UnlockOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "queue", "Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "(Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;)V", "perform", "", "affected", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static final class UnlockOp extends OpDescriptor {
        @NotNull
        @JvmField
        public final LockedQueue queue;

        public UnlockOp(@NotNull LockedQueue lockedQueue) {
            Intrinsics.checkParameterIsNotNull(lockedQueue, "queue");
            this.queue = lockedQueue;
        }

        @Nullable
        public Object perform(@Nullable Object obj) {
            Object access$getEMPTY_UNLOCKED$p = this.queue.isEmpty() ? MutexKt.EMPTY_UNLOCKED : this.queue;
            if (obj != null) {
                MutexImpl mutexImpl = (MutexImpl) obj;
                MutexImpl._state$FU.compareAndSet(mutexImpl, this, access$getEMPTY_UNLOCKED$p);
                if (mutexImpl._state == this.queue) {
                    return MutexKt.UNLOCK_FAIL;
                }
                return null;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
        }
    }

    @Nullable
    public Object lock(@Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
        if (tryLock(obj)) {
            return Unit.INSTANCE;
        }
        return lockSuspend(obj, continuation);
    }

    public boolean tryLock(@Nullable Object obj) {
        while (true) {
            Object obj2 = this._state;
            boolean z = true;
            if (obj2 instanceof Empty) {
                if (((Empty) obj2).locked != MutexKt.UNLOCKED) {
                    return false;
                }
                if (_state$FU.compareAndSet(this, obj2, obj == null ? MutexKt.EMPTY_LOCKED : new Empty(obj))) {
                    return true;
                }
            } else if (obj2 instanceof LockedQueue) {
                if (((LockedQueue) obj2).owner == obj) {
                    z = false;
                }
                if (z) {
                    return false;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Already locked by ");
                sb.append(obj);
                throw new IllegalStateException(sb.toString().toString());
            } else if (obj2 instanceof OpDescriptor) {
                ((OpDescriptor) obj2).perform(this);
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Illegal state ");
                sb2.append(obj2);
                throw new IllegalStateException(sb2.toString().toString());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object lockSuspend(@Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
        Object obj2 = obj;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 0);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        LockCont lockCont = new LockCont(obj2, cancellableContinuation);
        while (true) {
            Object obj3 = this._state;
            if (obj3 instanceof Empty) {
                Empty empty = (Empty) obj3;
                if (empty.locked != MutexKt.UNLOCKED) {
                    _state$FU.compareAndSet(this, obj3, new LockedQueue(empty.locked));
                } else {
                    if (_state$FU.compareAndSet(this, obj3, obj2 == null ? MutexKt.EMPTY_LOCKED : new Empty(obj2))) {
                        Continuation continuation2 = cancellableContinuation;
                        Unit unit = Unit.INSTANCE;
                        Companion companion = Result.Companion;
                        continuation2.resumeWith(Result.m6constructorimpl(unit));
                    }
                }
            } else if (obj3 instanceof LockedQueue) {
                LockedQueue lockedQueue = (LockedQueue) obj3;
                boolean z = true;
                if (lockedQueue.owner != obj2) {
                    LockFreeLinkedListNode lockFreeLinkedListNode = lockCont;
                    LockFreeLinkedListNode lockFreeLinkedListNode2 = lockFreeLinkedListNode;
                    MutexImpl$lockSuspend$$inlined$suspendAtomicCancellableCoroutine$lambda$1 mutexImpl$lockSuspend$$inlined$suspendAtomicCancellableCoroutine$lambda$1 = new MutexImpl$lockSuspend$$inlined$suspendAtomicCancellableCoroutine$lambda$1(lockFreeLinkedListNode, lockFreeLinkedListNode, obj3, cancellableContinuation, lockCont, this, obj);
                    CondAddOp condAddOp = mutexImpl$lockSuspend$$inlined$suspendAtomicCancellableCoroutine$lambda$1;
                    while (true) {
                        Object prev = lockedQueue.getPrev();
                        if (prev != null) {
                            switch (((LockFreeLinkedListNode) prev).tryCondAddNext(lockFreeLinkedListNode2, lockedQueue, condAddOp)) {
                                case 1:
                                    break;
                                case 2:
                                    z = false;
                                    break;
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                        }
                    }
                    if (z) {
                        CancellableContinuationKt.removeOnCancellation(cancellableContinuation, lockFreeLinkedListNode2);
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Already locked by ");
                    sb.append(obj2);
                    throw new IllegalStateException(sb.toString().toString());
                }
            } else if (obj3 instanceof OpDescriptor) {
                ((OpDescriptor) obj3).perform(this);
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Illegal state ");
                sb2.append(obj3);
                throw new IllegalStateException(sb2.toString().toString());
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public void unlock(@Nullable Object obj) {
        while (true) {
            Object obj2 = this._state;
            boolean z = true;
            if (obj2 instanceof Empty) {
                if (obj == null) {
                    if (((Empty) obj2).locked == MutexKt.UNLOCKED) {
                        z = false;
                    }
                    if (!z) {
                        throw new IllegalStateException("Mutex is not locked".toString());
                    }
                } else {
                    Empty empty = (Empty) obj2;
                    if (empty.locked != obj) {
                        z = false;
                    }
                    if (!z) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Mutex is locked by ");
                        sb.append(empty.locked);
                        sb.append(" but expected ");
                        sb.append(obj);
                        throw new IllegalStateException(sb.toString().toString());
                    }
                }
                if (_state$FU.compareAndSet(this, obj2, MutexKt.EMPTY_UNLOCKED)) {
                    return;
                }
            } else if (obj2 instanceof OpDescriptor) {
                ((OpDescriptor) obj2).perform(this);
            } else if (obj2 instanceof LockedQueue) {
                if (obj != null) {
                    LockedQueue lockedQueue = (LockedQueue) obj2;
                    if (lockedQueue.owner != obj) {
                        z = false;
                    }
                    if (!z) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Mutex is locked by ");
                        sb2.append(lockedQueue.owner);
                        sb2.append(" but expected ");
                        sb2.append(obj);
                        throw new IllegalStateException(sb2.toString().toString());
                    }
                }
                LockedQueue lockedQueue2 = (LockedQueue) obj2;
                LockFreeLinkedListNode removeFirstOrNull = lockedQueue2.removeFirstOrNull();
                if (removeFirstOrNull == null) {
                    UnlockOp unlockOp = new UnlockOp(lockedQueue2);
                    if (_state$FU.compareAndSet(this, obj2, unlockOp) && unlockOp.perform(this) == null) {
                        return;
                    }
                } else {
                    LockWaiter lockWaiter = (LockWaiter) removeFirstOrNull;
                    Object tryResumeLockWaiter = lockWaiter.tryResumeLockWaiter();
                    if (tryResumeLockWaiter != null) {
                        Object obj3 = lockWaiter.owner;
                        if (obj3 == null) {
                            obj3 = MutexKt.LOCKED;
                        }
                        lockedQueue2.owner = obj3;
                        lockWaiter.completeResumeLockWaiter(tryResumeLockWaiter);
                        return;
                    }
                }
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Illegal state ");
                sb3.append(obj2);
                throw new IllegalStateException(sb3.toString().toString());
            }
        }
    }

    @NotNull
    public String toString() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty) {
                StringBuilder sb = new StringBuilder();
                sb.append("Mutex[");
                sb.append(((Empty) obj).locked);
                sb.append(']');
                return sb.toString();
            } else if (obj instanceof OpDescriptor) {
                ((OpDescriptor) obj).perform(this);
            } else if (obj instanceof LockedQueue) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Mutex[");
                sb2.append(((LockedQueue) obj).owner);
                sb2.append(']');
                return sb2.toString();
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Illegal state ");
                sb3.append(obj);
                throw new IllegalStateException(sb3.toString().toString());
            }
        }
    }
}
