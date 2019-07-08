package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/ResumeAwaitOnCompletion;", "T", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/JobSupport;", "job", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/CancellableContinuationImpl;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: JobSupport.kt */
final class ResumeAwaitOnCompletion<T> extends JobNode<JobSupport> {
    private final CancellableContinuationImpl<T> continuation;

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public ResumeAwaitOnCompletion(@NotNull JobSupport jobSupport, @NotNull CancellableContinuationImpl<? super T> cancellableContinuationImpl) {
        Intrinsics.checkParameterIsNotNull(jobSupport, "job");
        Intrinsics.checkParameterIsNotNull(cancellableContinuationImpl, "continuation");
        super(jobSupport);
        this.continuation = cancellableContinuationImpl;
    }

    public void invoke(@Nullable Throwable th) {
        Object state$kotlinx_coroutines_core = ((JobSupport) this.job).getState$kotlinx_coroutines_core();
        if (!(!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            throw new IllegalStateException("Check failed.".toString());
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            this.continuation.resumeWithExceptionMode$kotlinx_coroutines_core(((CompletedExceptionally) state$kotlinx_coroutines_core).cause, 0);
        } else {
            Continuation continuation2 = this.continuation;
            Object unboxState = JobSupportKt.unboxState(state$kotlinx_coroutines_core);
            Companion companion = Result.Companion;
            continuation2.resumeWith(Result.m6constructorimpl(unboxState));
        }
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ResumeAwaitOnCompletion[");
        sb.append(this.continuation);
        sb.append(']');
        return sb.toString();
    }
}
