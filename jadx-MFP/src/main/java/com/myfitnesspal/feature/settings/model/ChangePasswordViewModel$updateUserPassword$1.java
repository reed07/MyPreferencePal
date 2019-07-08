package com.myfitnesspal.feature.settings.model;

import com.myfitnesspal.feature.consents.model.Resource.Error;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "com/myfitnesspal/feature/settings/model/ChangePasswordViewModel$updateUserPassword$1", f = "ChangePasswordViewModel.kt", i = {}, l = {121, 124, 125}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ChangePasswordViewModel.kt */
final class ChangePasswordViewModel$updateUserPassword$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $password;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ChangePasswordViewModel this$0;

    ChangePasswordViewModel$updateUserPassword$1(ChangePasswordViewModel changePasswordViewModel, String str, Continuation continuation) {
        this.this$0 = changePasswordViewModel;
        this.$password = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChangePasswordViewModel$updateUserPassword$1 changePasswordViewModel$updateUserPassword$1 = new ChangePasswordViewModel$updateUserPassword$1(this.this$0, this.$password, continuation);
        changePasswordViewModel$updateUserPassword$1.p$ = (CoroutineScope) obj;
        return changePasswordViewModel$updateUserPassword$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChangePasswordViewModel$updateUserPassword$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                if (!(obj instanceof Failure)) {
                    CoroutineScope coroutineScope = this.p$;
                    ChangePasswordViewModel changePasswordViewModel = this.this$0;
                    String str = this.$password;
                    this.label = 1;
                    if (changePasswordViewModel.patchUser(str, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    throw ((Failure) obj).exception;
                }
                break;
            case 1:
                if (!(obj instanceof Failure)) {
                    break;
                } else {
                    throw ((Failure) obj).exception;
                }
            case 2:
                try {
                    if (!(obj instanceof Failure)) {
                        break;
                    } else {
                        throw ((Failure) obj).exception;
                    }
                } catch (Exception e) {
                    Object obj2 = this.this$0.localSettingsService.get();
                    Intrinsics.checkExpressionValueIsNotNull(obj2, "localSettingsService.get()");
                    ((LocalSettingsService) obj2).setPasswordResetDateTime(DateTimeUtils.getDateTimeFromNow(10, 24));
                    this.this$0.getPasswordUpdateStatus().setValue(new Error(new Throwable("Failed change password", e)));
                    break;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ChangePasswordViewModel changePasswordViewModel2 = this.this$0;
        String str2 = this.$password;
        this.label = 2;
        if (changePasswordViewModel2.grefreshTokensByRelogin(str2, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        this.this$0.getPasswordUpdateStatus().setValue(new Success(Boxing.boxBoolean(true)));
        return Unit.INSTANCE;
    }
}
