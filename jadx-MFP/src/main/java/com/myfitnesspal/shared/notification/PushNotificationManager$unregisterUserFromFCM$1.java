package com.myfitnesspal.shared.notification;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "com/myfitnesspal/shared/notification/PushNotificationManager$unregisterUserFromFCM$1", f = "PushNotificationManager.kt", i = {}, l = {61}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PushNotificationManager.kt */
final class PushNotificationManager$unregisterUserFromFCM$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;

    PushNotificationManager$unregisterUserFromFCM$1(Continuation continuation) {
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        PushNotificationManager$unregisterUserFromFCM$1 pushNotificationManager$unregisterUserFromFCM$1 = new PushNotificationManager$unregisterUserFromFCM$1(continuation);
        pushNotificationManager$unregisterUserFromFCM$1.p$ = (CoroutineScope) obj;
        return pushNotificationManager$unregisterUserFromFCM$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PushNotificationManager$unregisterUserFromFCM$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else if (!(obj instanceof Failure)) {
            CoroutineScope coroutineScope = this.p$;
            FirebaseInstanceId.getInstance().deleteToken("513007887437", FirebaseMessaging.INSTANCE_ID_SCOPE);
            return Unit.INSTANCE;
        } else {
            throw ((Failure) obj).exception;
        }
    }
}
