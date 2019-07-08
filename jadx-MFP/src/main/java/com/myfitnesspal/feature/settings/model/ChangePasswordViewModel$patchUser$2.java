package com.myfitnesspal.feature.settings.model;

import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.service.syncv1.packets.request.ChangePasswordRequestPacket;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "com/myfitnesspal/feature/settings/model/ChangePasswordViewModel$patchUser$2", f = "ChangePasswordViewModel.kt", i = {}, l = {141, 141}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ChangePasswordViewModel.kt */
final class ChangePasswordViewModel$patchUser$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ String $password;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ChangePasswordViewModel this$0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "com/myfitnesspal/feature/settings/model/ChangePasswordViewModel$patchUser$2$1", f = "ChangePasswordViewModel.kt", i = {}, l = {142}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$patchUser$2$1 reason: invalid class name */
    /* compiled from: ChangePasswordViewModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        int label;
        private CoroutineScope p$;
        final /* synthetic */ ChangePasswordViewModel$patchUser$2 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, continuation);
            r0.p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else if (!(obj instanceof Failure)) {
                CoroutineScope coroutineScope = this.p$;
                return ((MfpInformationApi) ((MfpInformationApi) this.this$0.this$0.api.get()).addPacket(new ChangePasswordRequestPacket(this.this$0.$password))).post(new Object[0]);
            } else {
                throw ((Failure) obj).exception;
            }
        }
    }

    ChangePasswordViewModel$patchUser$2(ChangePasswordViewModel changePasswordViewModel, String str, Continuation continuation) {
        this.this$0 = changePasswordViewModel;
        this.$password = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChangePasswordViewModel$patchUser$2 changePasswordViewModel$patchUser$2 = new ChangePasswordViewModel$patchUser$2(this.this$0, this.$password, continuation);
        changePasswordViewModel$patchUser$2.p$ = (CoroutineScope) obj;
        return changePasswordViewModel$patchUser$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChangePasswordViewModel$patchUser$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                if (!(obj instanceof Failure)) {
                    Deferred async$default = BuildersKt__Builders_commonKt.async$default(this.p$, null, null, new AnonymousClass1(this, null), 3, null);
                    this.label = 1;
                    obj = async$default.await(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    throw ((Failure) obj).exception;
                }
                break;
            case 1:
                if (obj instanceof Failure) {
                    throw ((Failure) obj).exception;
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
