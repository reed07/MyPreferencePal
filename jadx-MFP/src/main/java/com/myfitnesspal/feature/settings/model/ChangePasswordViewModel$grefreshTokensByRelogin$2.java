package com.myfitnesspal.feature.settings.model;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "com/myfitnesspal/feature/settings/model/ChangePasswordViewModel$grefreshTokensByRelogin$2", f = "ChangePasswordViewModel.kt", i = {}, l = {150, 153}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ChangePasswordViewModel.kt */
final class ChangePasswordViewModel$grefreshTokensByRelogin$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
    final /* synthetic */ String $password;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ChangePasswordViewModel this$0;

    ChangePasswordViewModel$grefreshTokensByRelogin$2(ChangePasswordViewModel changePasswordViewModel, String str, Continuation continuation) {
        this.this$0 = changePasswordViewModel;
        this.$password = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChangePasswordViewModel$grefreshTokensByRelogin$2 changePasswordViewModel$grefreshTokensByRelogin$2 = new ChangePasswordViewModel$grefreshTokensByRelogin$2(this.this$0, this.$password, continuation);
        changePasswordViewModel$grefreshTokensByRelogin$2.p$ = (CoroutineScope) obj;
        return changePasswordViewModel$grefreshTokensByRelogin$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChangePasswordViewModel$grefreshTokensByRelogin$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 1
            switch(r1) {
                case 0: goto L_0x001e;
                case 1: goto L_0x0012;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0012:
            boolean r0 = r10 instanceof kotlin.Result.Failure     // Catch:{ ApiException -> 0x001c }
            if (r0 != 0) goto L_0x0017
            goto L_0x004d
        L_0x0017:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10     // Catch:{ ApiException -> 0x001c }
            java.lang.Throwable r10 = r10.exception     // Catch:{ ApiException -> 0x001c }
            throw r10     // Catch:{ ApiException -> 0x001c }
        L_0x001c:
            r10 = move-exception
            goto L_0x003e
        L_0x001e:
            boolean r1 = r10 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x004e
            kotlinx.coroutines.CoroutineScope r3 = r9.p$
            r4 = 0
            r5 = 0
            com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$grefreshTokensByRelogin$2$1 r10 = new com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$grefreshTokensByRelogin$2$1     // Catch:{ ApiException -> 0x001c }
            r1 = 0
            r10.<init>(r9, r1)     // Catch:{ ApiException -> 0x001c }
            r6 = r10
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6     // Catch:{ ApiException -> 0x001c }
            r7 = 3
            r8 = 0
            kotlinx.coroutines.Deferred r10 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r3, r4, r5, r6, r7, r8)     // Catch:{ ApiException -> 0x001c }
            r9.label = r2     // Catch:{ ApiException -> 0x001c }
            java.lang.Object r10 = r10.await(r9)     // Catch:{ ApiException -> 0x001c }
            if (r10 != r0) goto L_0x004d
            return r0
        L_0x003e:
            java.lang.String r0 = "Failed to relog used after change password"
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r2 = 0
            r1[r2] = r10
            int r10 = com.uacf.core.util.Ln.e(r0, r1)
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)
        L_0x004d:
            return r10
        L_0x004e:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r10 = r10.exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$grefreshTokensByRelogin$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
