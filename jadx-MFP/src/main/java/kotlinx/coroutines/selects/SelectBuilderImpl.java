package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletedExceptionallyKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@PublishedApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005:\u0003DEFB\u0013\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0002J'\u0010\u001f\u001a\u00020\u001c2\u000e\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001c0!H\bJ\n\u0010#\u001a\u0004\u0018\u00010\nH\u0001J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&H\u0001J\b\u0010'\u001a\u00020\u001cH\u0002J6\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\u001c\u0010\"\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0+H\u0016ø\u0001\u0000¢\u0006\u0002\u0010,J\u0012\u0010-\u001a\u0004\u0018\u00010\n2\u0006\u0010.\u001a\u00020/H\u0016J\u0012\u00100\u001a\u0004\u0018\u00010\n2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020&H\u0016J\u001e\u00103\u001a\u00020\u001c2\f\u00104\u001a\b\u0012\u0004\u0012\u00028\u000005H\u0016ø\u0001\u0000¢\u0006\u0002\u00106J\u0012\u00107\u001a\u00020\u00142\b\u00108\u001a\u0004\u0018\u00010\nH\u0016J3\u00109\u001a\u00020\u001c*\u00020:2\u001c\u0010\"\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0+H\u0002ø\u0001\u0000¢\u0006\u0002\u0010;JE\u00109\u001a\u00020\u001c\"\u0004\b\u0001\u0010<*\b\u0012\u0004\u0012\u0002H<0=2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H<\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0>H\u0002ø\u0001\u0000¢\u0006\u0002\u0010?JY\u00109\u001a\u00020\u001c\"\u0004\b\u0001\u0010@\"\u0004\b\u0002\u0010<*\u000e\u0012\u0004\u0012\u0002H@\u0012\u0004\u0012\u0002H<0A2\u0006\u0010B\u001a\u0002H@2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H<\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0>H\u0002ø\u0001\u0000¢\u0006\u0002\u0010CR\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006G"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl;", "R", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/coroutines/Continuation;", "uCont", "(Lkotlin/coroutines/Continuation;)V", "_result", "Lkotlinx/atomicfu/AtomicRef;", "", "_state", "completion", "getCompletion", "()Lkotlin/coroutines/Continuation;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "isSelected", "", "()Z", "parentHandle", "Lkotlinx/coroutines/DisposableHandle;", "state", "getState", "()Ljava/lang/Object;", "disposeOnSelect", "", "handle", "doAfterSelect", "doResume", "value", "Lkotlin/Function0;", "block", "getResult", "handleBuilderException", "e", "", "initCancellability", "onTimeout", "timeMillis", "", "Lkotlin/Function1;", "(JLkotlin/jvm/functions/Function1;)V", "performAtomicIfNotSelected", "desc", "Lkotlinx/coroutines/internal/AtomicDesc;", "performAtomicTrySelect", "resumeSelectCancellableWithException", "exception", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "trySelect", "idempotent", "invoke", "Lkotlinx/coroutines/selects/SelectClause0;", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "AtomicSelectOp", "DisposeNode", "SelectOnCancelling", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Select.kt */
public final class SelectBuilderImpl<R> extends LockFreeLinkedListHead implements Continuation<R>, SelectBuilder<R>, SelectInstance<R> {
    static final AtomicReferenceFieldUpdater _result$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");
    static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");
    volatile Object _result;
    volatile Object _state;
    private volatile DisposableHandle parentHandle;
    private final Continuation<R> uCont;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$DisposeNode;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "handle", "Lkotlinx/coroutines/DisposableHandle;", "(Lkotlinx/coroutines/DisposableHandle;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Select.kt */
    private static final class DisposeNode extends LockFreeLinkedListNode {
        @NotNull
        @JvmField
        public final DisposableHandle handle;
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.uCont.getContext();
    }

    @NotNull
    public Continuation<R> getCompletion() {
        return this;
    }

    public boolean isSelected() {
        return getState() != this;
    }

    private final void doAfterSelect() {
        DisposableHandle disposableHandle = this.parentHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        Object next = getNext();
        if (next != null) {
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) this); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof DisposeNode) {
                    ((DisposeNode) lockFreeLinkedListNode).handle.dispose();
                }
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    public boolean trySelect(@Nullable Object obj) {
        if (!(obj instanceof OpDescriptor)) {
            do {
                Object state = getState();
                if (state != this) {
                    return obj != null && state == obj;
                }
            } while (!_state$FU.compareAndSet(this, this, obj));
            doAfterSelect();
            return true;
        }
        throw new IllegalStateException("cannot use OpDescriptor as idempotent marker".toString());
    }

    public void resumeWith(@NotNull Object obj) {
        if (isSelected()) {
            while (true) {
                Object obj2 = this._result;
                if (obj2 == SelectKt.UNDECIDED) {
                    if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, CompletedExceptionallyKt.toState(obj))) {
                        return;
                    }
                } else if (obj2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    throw new IllegalStateException("Already resumed");
                } else if (_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    this.uCont.resumeWith(obj);
                    return;
                }
            }
        } else {
            throw new IllegalStateException("Must be selected first".toString());
        }
    }

    /* access modifiers changed from: private */
    public final Object getState() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }
}
