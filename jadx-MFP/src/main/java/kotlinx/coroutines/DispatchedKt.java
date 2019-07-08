package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0006\u001a\u001f\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\b\u001a\"\u0010\n\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0000\u001a;\u0010\u000f\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u00102\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\b\u001a.\u0010\u0015\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00172\u0006\u0010\u0018\u001a\u00020\u000eH\u0000\u001a%\u0010\u0019\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00172\u0006\u0010\u001a\u001a\u0002H\u000bH\u0000¢\u0006\u0002\u0010\u001b\u001a \u0010\u001c\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0000\u001a%\u0010\u001f\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00172\u0006\u0010\u001a\u001a\u0002H\u000bH\u0000¢\u0006\u0002\u0010\u001b\u001a \u0010 \u001a\u00020\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0000\u001a\u0010\u0010!\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\fH\u0002\u001a\u0019\u0010\"\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\b\u001a\u0012\u0010#\u001a\u00020\u0010*\b\u0012\u0004\u0012\u00020\u00050\u0011H\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003¨\u0006$"}, d2 = {"UNDEFINED", "Lkotlinx/coroutines/internal/Symbol;", "UNDEFINED$annotations", "()V", "runUnconfinedEventLoop", "", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "block", "Lkotlin/Function0;", "dispatch", "T", "Lkotlinx/coroutines/DispatchedTask;", "mode", "", "executeUnconfined", "", "Lkotlinx/coroutines/DispatchedContinuation;", "contState", "", "doYield", "resume", "delegate", "Lkotlin/coroutines/Continuation;", "useMode", "resumeCancellable", "value", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "resumeCancellableWithException", "exception", "", "resumeDirect", "resumeDirectWithException", "resumeUnconfined", "resumeWithStackTrace", "yieldUndispatched", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Dispatched.kt */
public final class DispatchedKt {
    /* access modifiers changed from: private */
    public static final Symbol UNDEFINED = new Symbol("UNDEFINED");

    private static final void resumeUnconfined(@NotNull DispatchedTask<?> dispatchedTask) {
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedTask);
            return;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            resume(dispatchedTask, dispatchedTask.getDelegate(), 3);
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
            eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
        } catch (Throwable th) {
            eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
            throw th;
        }
    }

    public static final <T> void resumeCancellable(@NotNull Continuation<? super T> continuation, T t) {
        boolean z;
        CoroutineContext context;
        Object updateThreadContext;
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        if (continuation instanceof DispatchedContinuation) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
            if (dispatchedContinuation.dispatcher.isDispatchNeeded(dispatchedContinuation.getContext())) {
                dispatchedContinuation._state = t;
                dispatchedContinuation.resumeMode = 1;
                dispatchedContinuation.dispatcher.dispatch(dispatchedContinuation.getContext(), dispatchedContinuation);
                return;
            }
            EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
                dispatchedContinuation._state = t;
                dispatchedContinuation.resumeMode = 1;
                eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedContinuation);
                return;
            }
            eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
            try {
                Job job = (Job) dispatchedContinuation.getContext().get(Job.Key);
                if (job == null || job.isActive()) {
                    z = false;
                } else {
                    Throwable cancellationException = job.getCancellationException();
                    Companion companion = Result.Companion;
                    dispatchedContinuation.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(cancellationException)));
                    z = true;
                }
                if (!z) {
                    context = dispatchedContinuation.getContext();
                    updateThreadContext = ThreadContextKt.updateThreadContext(context, dispatchedContinuation.countOrElement);
                    Continuation<T> continuation2 = dispatchedContinuation.continuation;
                    Companion companion2 = Result.Companion;
                    continuation2.resumeWith(Result.m6constructorimpl(t));
                    Unit unit = Unit.INSTANCE;
                    ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                }
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
        } else {
            Companion companion3 = Result.Companion;
            continuation.resumeWith(Result.m6constructorimpl(t));
        }
    }

    public static final <T> void resumeCancellableWithException(@NotNull Continuation<? super T> continuation, @NotNull Throwable th) {
        boolean z;
        CoroutineContext context;
        Object updateThreadContext;
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        if (continuation instanceof DispatchedContinuation) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
            CoroutineContext context2 = dispatchedContinuation.continuation.getContext();
            CompletedExceptionally completedExceptionally = new CompletedExceptionally(th);
            if (dispatchedContinuation.dispatcher.isDispatchNeeded(context2)) {
                dispatchedContinuation._state = new CompletedExceptionally(th);
                dispatchedContinuation.resumeMode = 1;
                dispatchedContinuation.dispatcher.dispatch(context2, dispatchedContinuation);
                return;
            }
            EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
                dispatchedContinuation._state = completedExceptionally;
                dispatchedContinuation.resumeMode = 1;
                eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedContinuation);
                return;
            }
            eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
            try {
                Job job = (Job) dispatchedContinuation.getContext().get(Job.Key);
                if (job == null || job.isActive()) {
                    z = false;
                } else {
                    Throwable cancellationException = job.getCancellationException();
                    Companion companion = Result.Companion;
                    dispatchedContinuation.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(cancellationException)));
                    z = true;
                }
                if (!z) {
                    context = dispatchedContinuation.getContext();
                    updateThreadContext = ThreadContextKt.updateThreadContext(context, dispatchedContinuation.countOrElement);
                    Continuation<T> continuation2 = dispatchedContinuation.continuation;
                    Companion companion2 = Result.Companion;
                    continuation2.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(StackTraceRecoveryKt.recoverStackTrace(th, continuation2))));
                    Unit unit = Unit.INSTANCE;
                    ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                }
                do {
                } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
                eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
            } catch (Throwable th2) {
                try {
                    throw new DispatchException("Unexpected exception in unconfined event loop", th2);
                } catch (Throwable th3) {
                    eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
                    throw th3;
                }
            }
        } else {
            Companion companion3 = Result.Companion;
            continuation.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(StackTraceRecoveryKt.recoverStackTrace(th, continuation))));
        }
    }

    public static final <T> void resumeDirect(@NotNull Continuation<? super T> continuation, T t) {
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        if (continuation instanceof DispatchedContinuation) {
            Continuation<T> continuation2 = ((DispatchedContinuation) continuation).continuation;
            Companion companion = Result.Companion;
            continuation2.resumeWith(Result.m6constructorimpl(t));
            return;
        }
        Companion companion2 = Result.Companion;
        continuation.resumeWith(Result.m6constructorimpl(t));
    }

    public static final <T> void resumeDirectWithException(@NotNull Continuation<? super T> continuation, @NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        if (continuation instanceof DispatchedContinuation) {
            Continuation<T> continuation2 = ((DispatchedContinuation) continuation).continuation;
            Companion companion = Result.Companion;
            continuation2.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(StackTraceRecoveryKt.recoverStackTrace(th, continuation2))));
            return;
        }
        Companion companion2 = Result.Companion;
        continuation.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(StackTraceRecoveryKt.recoverStackTrace(th, continuation))));
    }

    public static final <T> void dispatch(@NotNull DispatchedTask<? super T> dispatchedTask, int i) {
        Intrinsics.checkParameterIsNotNull(dispatchedTask, "receiver$0");
        Continuation delegate = dispatchedTask.getDelegate();
        if (!ResumeModeKt.isDispatchedMode(i) || !(delegate instanceof DispatchedContinuation) || ResumeModeKt.isCancellableMode(i) != ResumeModeKt.isCancellableMode(dispatchedTask.resumeMode)) {
            resume(dispatchedTask, delegate, i);
            return;
        }
        CoroutineDispatcher coroutineDispatcher = ((DispatchedContinuation) delegate).dispatcher;
        CoroutineContext context = delegate.getContext();
        if (coroutineDispatcher.isDispatchNeeded(context)) {
            coroutineDispatcher.dispatch(context, dispatchedTask);
        } else {
            resumeUnconfined(dispatchedTask);
        }
    }

    public static final <T> void resume(@NotNull DispatchedTask<? super T> dispatchedTask, @NotNull Continuation<? super T> continuation, int i) {
        Intrinsics.checkParameterIsNotNull(dispatchedTask, "receiver$0");
        Intrinsics.checkParameterIsNotNull(continuation, "delegate");
        Object takeState = dispatchedTask.takeState();
        Throwable exceptionalResult = dispatchedTask.getExceptionalResult(takeState);
        if (exceptionalResult != null) {
            ResumeModeKt.resumeWithExceptionMode(continuation, exceptionalResult, i);
        } else {
            ResumeModeKt.resumeMode(continuation, dispatchedTask.getSuccessfulResult(takeState), i);
        }
    }
}
