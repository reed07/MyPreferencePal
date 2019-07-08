package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005B\u001b\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\tJ\u0017\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\n\u0018\u00010\u001fj\u0004\u0018\u0001` H\u0016J\u0016\u0010!\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\b¢\u0006\u0002\u0010\u001dJ\u0011\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\bJ\t\u0010%\u001a\u00020&H\bJ\u0016\u0010'\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\b¢\u0006\u0002\u0010\u001dJ\u0011\u0010(\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\bJ\u001e\u0010)\u001a\u00020\u001a2\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000+H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\n\u0010,\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010-\u001a\u00020.H\u0016R\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0012X\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u000b8\u0000X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, d2 = {"Lkotlinx/coroutines/DispatchedContinuation;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "Lkotlin/coroutines/Continuation;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "continuation", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "_state", "", "_state$annotations", "()V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "countOrElement", "delegate", "getDelegate", "()Lkotlin/coroutines/Continuation;", "dispatchYield", "", "value", "dispatchYield$kotlinx_coroutines_core", "(Ljava/lang/Object;)V", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "resumeCancellable", "resumeCancellableWithException", "exception", "", "resumeCancelled", "", "resumeUndispatched", "resumeUndispatchedWithException", "resumeWith", "result", "Lkotlin/Result;", "takeState", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Dispatched.kt */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements Continuation<T>, CoroutineStackFrame {
    @Nullable
    @JvmField
    public Object _state = DispatchedKt.UNDEFINED;
    @Nullable
    private final CoroutineStackFrame callerFrame;
    @NotNull
    @JvmField
    public final Continuation<T> continuation;
    @NotNull
    @JvmField
    public final Object countOrElement;
    @NotNull
    @JvmField
    public final CoroutineDispatcher dispatcher;

    @NotNull
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public DispatchedContinuation(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Continuation<? super T> continuation2) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "dispatcher");
        Intrinsics.checkParameterIsNotNull(continuation2, "continuation");
        super(0);
        this.dispatcher = coroutineDispatcher;
        this.continuation = continuation2;
        Continuation<T> continuation3 = this.continuation;
        if (!(continuation3 instanceof CoroutineStackFrame)) {
            continuation3 = null;
        }
        this.callerFrame = (CoroutineStackFrame) continuation3;
        this.countOrElement = ThreadContextKt.threadContextElements(getContext());
    }

    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        return this.callerFrame;
    }

    @Nullable
    public Object takeState() {
        Object obj = this._state;
        if (obj != DispatchedKt.UNDEFINED) {
            this._state = DispatchedKt.UNDEFINED;
            return obj;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @NotNull
    public Continuation<T> getDelegate() {
        return this;
    }

    public void resumeWith(@NotNull Object obj) {
        CoroutineContext context;
        Object updateThreadContext;
        CoroutineContext context2 = this.continuation.getContext();
        Object state = CompletedExceptionallyKt.toState(obj);
        if (this.dispatcher.isDispatchNeeded(context2)) {
            this._state = state;
            this.resumeMode = 0;
            this.dispatcher.dispatch(context2, this);
            return;
        }
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            this._state = state;
            this.resumeMode = 0;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(this);
            return;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            context = getContext();
            updateThreadContext = ThreadContextKt.updateThreadContext(context, this.countOrElement);
            this.continuation.resumeWith(obj);
            Unit unit = Unit.INSTANCE;
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
            eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
        } catch (Throwable th) {
            try {
                throw new DispatchException("Unexpected exception in unconfined event loop", th);
            } catch (Throwable th2) {
                eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
                throw th2;
            }
        }
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DispatchedContinuation[");
        sb.append(this.dispatcher);
        sb.append(", ");
        sb.append(DebugKt.toDebugString(this.continuation));
        sb.append(']');
        return sb.toString();
    }
}
