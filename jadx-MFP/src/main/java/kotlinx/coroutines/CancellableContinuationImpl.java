package kotlinx.coroutines;

import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@PublishedApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u0005B\u001b\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010&\u001a\u00020\u001a2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0010\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020\u000fH\u0016J\u0010\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020\tH\u0002J\b\u0010-\u001a\u00020$H\u0002J\u0010\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u000200H\u0016J\n\u00101\u001a\u0004\u0018\u00010\u000fH\u0001J\u0010\u00102\u001a\n\u0018\u000103j\u0004\u0018\u0001`4H\u0016J\u001d\u00105\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010 \u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u00106J\b\u00107\u001a\u00020$H\u0016J\b\u00108\u001a\u00020$H\u0002J\u0017\u00109\u001a\u00020$2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020$0;H\bJ1\u0010<\u001a\u00020$2'\u0010=\u001a#\u0012\u0015\u0012\u0013\u0018\u00010(¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b('\u0012\u0004\u0012\u00020$0>j\u0002`AH\u0016J1\u0010B\u001a\u00020C2'\u0010=\u001a#\u0012\u0015\u0012\u0013\u0018\u00010(¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b('\u0012\u0004\u0012\u00020$0>j\u0002`AH\u0002J;\u0010D\u001a\u00020$2'\u0010=\u001a#\u0012\u0015\u0012\u0013\u0018\u00010(¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b('\u0012\u0004\u0012\u00020$0>j\u0002`A2\b\u0010 \u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010E\u001a\u00020FH\u0014J\u001a\u0010G\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\tH\u0002J\u001e\u0010H\u001a\u00020$2\f\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000JH\u0016ø\u0001\u0000¢\u0006\u0002\u0010KJ\u001d\u0010L\u001a\u00020$2\u0006\u0010M\u001a\u00020(2\u0006\u0010,\u001a\u00020\tH\u0000¢\u0006\u0002\bNJ\n\u0010O\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010P\u001a\u00020FH\u0016J\b\u0010Q\u001a\u00020\u001aH\u0002J!\u0010Q\u001a\u0004\u0018\u00010\u000f2\u0006\u0010R\u001a\u00028\u00002\b\u0010S\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u0010TJ\u0012\u0010U\u001a\u0004\u0018\u00010\u000f2\u0006\u0010M\u001a\u00020(H\u0016J\b\u0010V\u001a\u00020\u001aH\u0002J\u0019\u0010W\u001a\u00020$*\u00020X2\u0006\u0010R\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010YJ\u0014\u0010Z\u001a\u00020$*\u00020X2\u0006\u0010M\u001a\u00020(H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001a8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001a8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\u0004\u0018\u00010\u000f8@X\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0002\u0004\n\u0002\b\u0019¨\u0006["}, d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlinx/coroutines/CancellableContinuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "delegate", "Lkotlin/coroutines/Continuation;", "resumeMode", "", "(Lkotlin/coroutines/Continuation;I)V", "_decision", "Lkotlinx/atomicfu/AtomicInt;", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getDelegate", "()Lkotlin/coroutines/Continuation;", "isActive", "", "()Z", "isCancelled", "isCompleted", "parentHandle", "Lkotlinx/coroutines/DisposableHandle;", "state", "getState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "alreadyResumedError", "", "proposedUpdate", "cancel", "cause", "", "completeResume", "token", "dispatchResume", "mode", "disposeParentHandle", "getContinuationCancellationCause", "parent", "Lkotlinx/coroutines/Job;", "getResult", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getSuccessfulResult", "(Ljava/lang/Object;)Ljava/lang/Object;", "initCancellability", "installParentCancellationHandler", "invokeHandlerSafely", "block", "Lkotlin/Function0;", "invokeOnCancellation", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "makeHandler", "Lkotlinx/coroutines/CancelHandler;", "multipleHandlersError", "nameString", "", "resumeImpl", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "resumeWithExceptionMode", "exception", "resumeWithExceptionMode$kotlinx_coroutines_core", "takeState", "toString", "tryResume", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryResumeWithException", "trySuspend", "resumeUndispatched", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CancellableContinuationImpl.kt */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CoroutineStackFrame, CancellableContinuation<T> {
    private static final AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decision");
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");
    private volatile int _decision = 0;
    private volatile Object _state = Active.INSTANCE;
    @NotNull
    private final CoroutineContext context = this.delegate.getContext();
    @NotNull
    private final Continuation<T> delegate;
    private volatile DisposableHandle parentHandle;

    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public String nameString() {
        return "CancellableContinuation";
    }

    @NotNull
    public final Continuation<T> getDelegate() {
        return this.delegate;
    }

    public CancellableContinuationImpl(@NotNull Continuation<? super T> continuation, int i) {
        Intrinsics.checkParameterIsNotNull(continuation, "delegate");
        super(i);
        this.delegate = continuation;
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.context;
    }

    @Nullable
    public final Object getState$kotlinx_coroutines_core() {
        return this._state;
    }

    public boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof NotCompleted);
    }

    private final void installParentCancellationHandler() {
        if (!isCompleted()) {
            Job job = (Job) this.delegate.getContext().get(Job.Key);
            if (job != null) {
                job.start();
                DisposableHandle invokeOnCompletion$default = DefaultImpls.invokeOnCompletion$default(job, true, false, new ChildContinuation(job, this), 2, null);
                this.parentHandle = invokeOnCompletion$default;
                if (isCompleted()) {
                    invokeOnCompletion$default.dispose();
                    this.parentHandle = NonDisposableHandle.INSTANCE;
                }
            }
        }
    }

    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        return (CoroutineStackFrame) continuation;
    }

    @Nullable
    public Object takeState() {
        return getState$kotlinx_coroutines_core();
    }

    @NotNull
    public Throwable getContinuationCancellationCause(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "parent");
        return job.getCancellationException();
    }

    @Nullable
    @PublishedApi
    public final Object getResult() {
        installParentCancellationHandler();
        if (trySuspend()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return getSuccessfulResult(state$kotlinx_coroutines_core);
        }
        throw StackTraceRecoveryKt.recoverStackTrace(((CompletedExceptionally) state$kotlinx_coroutines_core).cause, this);
    }

    public void resumeWith(@NotNull Object obj) {
        resumeImpl(CompletedExceptionallyKt.toState(obj), this.resumeMode);
    }

    public final void resumeWithExceptionMode$kotlinx_coroutines_core(@NotNull Throwable th, int i) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
        resumeImpl(new CompletedExceptionally(th), i);
    }

    public void invokeOnCancellation(@NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "handler");
        CancelHandler cancelHandler = null;
        while (true) {
            Object obj = this._state;
            if (obj instanceof Active) {
                if (cancelHandler == null) {
                    cancelHandler = makeHandler(function1);
                }
                if (_state$FU.compareAndSet(this, obj, cancelHandler)) {
                    return;
                }
            } else if (obj instanceof CancelHandler) {
                multipleHandlersError(function1, obj);
            } else if (obj instanceof CancelledContinuation) {
                if (!((CancelledContinuation) obj).makeHandled()) {
                    multipleHandlersError(function1, obj);
                }
                try {
                    if (!(obj instanceof CompletedExceptionally)) {
                        obj = null;
                    }
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
                    function1.invoke(completedExceptionally != null ? completedExceptionally.cause : null);
                } catch (Throwable th) {
                    CoroutineContext context2 = getContext();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Exception in cancellation handler for ");
                    sb.append(this);
                    CoroutineExceptionHandlerKt.handleCoroutineException$default(context2, new CompletionHandlerException(sb.toString(), th), null, 4, null);
                }
                return;
            } else {
                return;
            }
        }
    }

    private final void multipleHandlersError(Function1<? super Throwable, Unit> function1, Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("It's prohibited to register multiple handlers, tried to register ");
        sb.append(function1);
        sb.append(", already has ");
        sb.append(obj);
        throw new IllegalStateException(sb.toString().toString());
    }

    private final CancelHandler makeHandler(Function1<? super Throwable, Unit> function1) {
        return function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1);
    }

    private final void dispatchResume(int i) {
        if (!tryResume()) {
            DispatchedKt.dispatch(this, i);
        }
    }

    private final void alreadyResumedError(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("Already resumed, but proposed with update ");
        sb.append(obj);
        throw new IllegalStateException(sb.toString().toString());
    }

    private final void disposeParentHandle() {
        DisposableHandle disposableHandle = this.parentHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
    }

    public void completeResume(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, GooglePlayConstants.BILLING_JSON_FIELD_TOKEN);
        dispatchResume(this.resumeMode);
    }

    public void resumeUndispatched(@NotNull CoroutineDispatcher coroutineDispatcher, T t) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "receiver$0");
        Continuation<T> continuation = this.delegate;
        CoroutineDispatcher coroutineDispatcher2 = null;
        if (!(continuation instanceof DispatchedContinuation)) {
            continuation = null;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.dispatcher;
        }
        resumeImpl(t, coroutineDispatcher2 == coroutineDispatcher ? 3 : this.resumeMode);
    }

    public <T> T getSuccessfulResult(@Nullable Object obj) {
        return obj instanceof CompletedIdempotentResult ? ((CompletedIdempotentResult) obj).result : obj;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nameString());
        sb.append('(');
        sb.append(DebugKt.toDebugString(this.delegate));
        sb.append("){");
        sb.append(getState$kotlinx_coroutines_core());
        sb.append("}@");
        sb.append(DebugKt.getHexAddress(this));
        return sb.toString();
    }

    public boolean cancel(@Nullable Throwable th) {
        Object obj;
        boolean z;
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            z = obj instanceof CancelHandler;
        } while (!_state$FU.compareAndSet(this, obj, new CancelledContinuation(this, th, z)));
        if (z) {
            try {
                ((CancelHandler) obj).invoke(th);
            } catch (Throwable th2) {
                CoroutineContext context2 = getContext();
                StringBuilder sb = new StringBuilder();
                sb.append("Exception in cancellation handler for ");
                sb.append(this);
                CoroutineExceptionHandlerKt.handleCoroutineException$default(context2, new CompletionHandlerException(sb.toString(), th2), null, 4, null);
            }
        }
        disposeParentHandle();
        dispatchResume(0);
        return true;
    }

    private final boolean trySuspend() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!_decision$FU.compareAndSet(this, 0, 1));
        return true;
    }

    private final boolean tryResume() {
        do {
            switch (this._decision) {
                case 0:
                    break;
                case 1:
                    return false;
                default:
                    throw new IllegalStateException("Already resumed".toString());
            }
        } while (!_decision$FU.compareAndSet(this, 0, 2));
        return true;
    }

    private final void resumeImpl(Object obj, int i) {
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof NotCompleted) {
                if (_state$FU.compareAndSet(this, obj2, obj)) {
                    disposeParentHandle();
                    dispatchResume(i);
                    return;
                }
            } else if (!(obj2 instanceof CancelledContinuation) || !((CancelledContinuation) obj2).makeResumed()) {
                alreadyResumedError(obj);
            } else {
                return;
            }
        }
    }

    @Nullable
    public Object tryResume(T t, @Nullable Object obj) {
        Object obj2;
        T t2;
        do {
            obj2 = this._state;
            if (!(obj2 instanceof NotCompleted)) {
                NotCompleted notCompleted = null;
                if (!(obj2 instanceof CompletedIdempotentResult)) {
                    return null;
                }
                CompletedIdempotentResult completedIdempotentResult = (CompletedIdempotentResult) obj2;
                if (completedIdempotentResult.idempotentResume == obj) {
                    if (completedIdempotentResult.result == t) {
                        notCompleted = completedIdempotentResult.token;
                    } else {
                        throw new IllegalStateException("Non-idempotent resume".toString());
                    }
                }
                return notCompleted;
            } else if (obj == null) {
                t2 = t;
            } else {
                t2 = new CompletedIdempotentResult(obj, t, (NotCompleted) obj2);
            }
        } while (!_state$FU.compareAndSet(this, obj2, t2));
        disposeParentHandle();
        return obj2;
    }

    @Nullable
    public Object tryResumeWithException(@NotNull Throwable th) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(th, "exception");
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                return null;
            }
        } while (!_state$FU.compareAndSet(this, obj, new CompletedExceptionally(th)));
        disposeParentHandle();
        return obj;
    }
}
