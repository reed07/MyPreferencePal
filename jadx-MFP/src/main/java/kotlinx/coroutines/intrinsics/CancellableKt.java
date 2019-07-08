package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DispatchedKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\b\u001a>\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0007*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t0\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0003H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aR\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0007*\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t0\f2\u0006\u0010\r\u001a\u0002H\u000b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0003H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"runSafely", "", "completion", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function0;", "startCoroutineCancellable", "T", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Cancellable.kt */
public final class CancellableKt {
    public static final <T> void startCoroutineCancellable(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(function1, "receiver$0");
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        try {
            DispatchedKt.resumeCancellable(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function1, continuation)), Unit.INSTANCE);
        } catch (Throwable th) {
            Companion companion = Result.Companion;
            continuation.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(th)));
        }
    }

    public static final <R, T> void startCoroutineCancellable(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(function2, "receiver$0");
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        try {
            DispatchedKt.resumeCancellable(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function2, r, continuation)), Unit.INSTANCE);
        } catch (Throwable th) {
            Companion companion = Result.Companion;
            continuation.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(th)));
        }
    }
}
