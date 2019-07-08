package com.myfitnesspal.shared.notification;

import com.uacf.core.util.Ln;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
/* compiled from: PushNotificationManager.kt */
final class PushNotificationManager$retryRegistrationWithBackOff$1 implements Runnable {
    final /* synthetic */ PushNotificationManager this$0;

    PushNotificationManager$retryRegistrationWithBackOff$1(PushNotificationManager pushNotificationManager) {
        this.this$0 = pushNotificationManager;
    }

    public final void run() {
        long j = (long) 15000;
        long access$getBackOffTime$cp = PushNotificationManager.backOffTime;
        PushNotificationManager.backOffTime = 1 + access$getBackOffTime$cp;
        long j2 = j * access$getBackOffTime$cp;
        StringBuilder sb = new StringBuilder();
        sb.append("invoking backoff ... retry after : ");
        sb.append(j2);
        Ln.i(sb.toString(), new Object[0]);
        try {
            Thread.sleep(j2);
        } catch (InterruptedException e) {
            Ln.e(e);
        }
        this.this$0.registerUserForFCM();
    }
}
