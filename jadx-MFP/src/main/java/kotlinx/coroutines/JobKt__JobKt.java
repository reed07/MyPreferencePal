package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0007\u001a\u0019\u0010\u0004\u001a\u00020\u00052\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\b\u001a\u0012\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u001a\n\u0010\f\u001a\u00020\b*\u00020\u0002\u001a\u0018\u0010\f\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007\u001a\u0011\u0010\u000f\u001a\u00020\u0001*\u00020\u0002H\u0007¢\u0006\u0002\b\f\u001a\u0015\u0010\u0010\u001a\u00020\b*\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\n\u0010\u0012\u001a\u00020\b*\u00020\u0002\u001a\u0018\u0010\u0012\u001a\u00020\b*\u00020\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007\u001a\n\u0010\u0012\u001a\u00020\b*\u00020\n\u001a\u0018\u0010\u0012\u001a\u00020\b*\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007\u001a\u0014\u0010\u0013\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0005H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"isActive", "", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)Z", "DisposableHandle", "Lkotlinx/coroutines/DisposableHandle;", "block", "Lkotlin/Function0;", "", "Job", "Lkotlinx/coroutines/Job;", "parent", "cancel", "cause", "", "cancel0", "cancelAndJoin", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelChildren", "disposeOnCompletion", "handle", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 13}, xs = "kotlinx/coroutines/JobKt")
/* compiled from: Job.kt */
final /* synthetic */ class JobKt__JobKt {
    @NotNull
    public static final Job Job(@Nullable Job job) {
        return new JobImpl(job);
    }

    @NotNull
    public static /* synthetic */ Job Job$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return JobKt.Job(job);
    }
}
