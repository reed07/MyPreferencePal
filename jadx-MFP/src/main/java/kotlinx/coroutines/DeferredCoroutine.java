package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0010\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\r\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013JH\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0001\u0010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u00182\"\u0010\u0019\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001aH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u0014\u0010\n\u001a\u00020\b8TX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/DeferredCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlinx/coroutines/Deferred;", "Lkotlinx/coroutines/selects/SelectClause1;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "active", "", "(Lkotlin/coroutines/CoroutineContext;Z)V", "cancelsParent", "getCancelsParent", "()Z", "onAwait", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompleted", "()Ljava/lang/Object;", "registerSelectClause1", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Builders.common.kt */
class DeferredCoroutine<T> extends AbstractCoroutine<T> implements Deferred<T>, SelectClause1<T> {
    @Nullable
    public Object await(@NotNull Continuation<? super T> continuation) {
        return await$suspendImpl(this, continuation);
    }

    /* access modifiers changed from: protected */
    public boolean getCancelsParent() {
        return true;
    }

    public DeferredCoroutine(@NotNull CoroutineContext coroutineContext, boolean z) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "parentContext");
        super(coroutineContext, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object await$suspendImpl(kotlinx.coroutines.DeferredCoroutine r3, kotlin.coroutines.Continuation r4) {
        /*
            boolean r0 = r4 instanceof kotlinx.coroutines.DeferredCoroutine$await$1
            if (r0 == 0) goto L_0x0014
            r0 = r4
            kotlinx.coroutines.DeferredCoroutine$await$1 r0 = (kotlinx.coroutines.DeferredCoroutine$await$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r4 = r0.label
            int r4 = r4 - r2
            r0.label = r4
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.DeferredCoroutine$await$1 r0 = new kotlinx.coroutines.DeferredCoroutine$await$1
            r0.<init>(r3, r4)
        L_0x0019:
            java.lang.Object r4 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L_0x003a;
                case 1: goto L_0x002c;
                default: goto L_0x0024;
            }
        L_0x0024:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L_0x002c:
            java.lang.Object r3 = r0.L$0
            kotlinx.coroutines.DeferredCoroutine r3 = (kotlinx.coroutines.DeferredCoroutine) r3
            boolean r3 = r4 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0035
            goto L_0x004a
        L_0x0035:
            kotlin.Result$Failure r4 = (kotlin.Result.Failure) r4
            java.lang.Throwable r3 = r4.exception
            throw r3
        L_0x003a:
            boolean r2 = r4 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x004b
            r0.L$0 = r3
            r4 = 1
            r0.label = r4
            java.lang.Object r4 = r3.awaitInternal$kotlinx_coroutines_core(r0)
            if (r4 != r1) goto L_0x004a
            return r1
        L_0x004a:
            return r4
        L_0x004b:
            kotlin.Result$Failure r4 = (kotlin.Result.Failure) r4
            java.lang.Throwable r3 = r4.exception
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DeferredCoroutine.await$suspendImpl(kotlinx.coroutines.DeferredCoroutine, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
