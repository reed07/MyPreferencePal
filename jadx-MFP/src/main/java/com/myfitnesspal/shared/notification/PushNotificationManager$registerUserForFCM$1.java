package com.myfitnesspal.shared.notification;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.InstanceIdResult;
import com.uacf.core.util.Ln;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "task", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/firebase/iid/InstanceIdResult;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: PushNotificationManager.kt */
final class PushNotificationManager$registerUserForFCM$1<TResult> implements OnCompleteListener<InstanceIdResult> {
    final /* synthetic */ PushNotificationManager this$0;

    PushNotificationManager$registerUserForFCM$1(PushNotificationManager pushNotificationManager) {
        this.this$0 = pushNotificationManager;
    }

    /* renamed from: invoke */
    public final void onComplete(@NotNull Task<InstanceIdResult> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        if (!task.isSuccessful()) {
            Ln.e("getInstanceId for fcm failed", task.getException());
            this.this$0.retryRegistrationWithBackOff();
            return;
        }
        InstanceIdResult instanceIdResult = (InstanceIdResult) task.getResult();
        if (instanceIdResult != null) {
            String token = instanceIdResult.getToken();
            if (token != null) {
                this.this$0.registerTokenWithBackend(token);
            }
        }
    }
}
