package com.myfitnesspal.shared.notification;

import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.uacf.core.util.Ln;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.FormBody;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
/* compiled from: PushNotificationManager.kt */
final class PushNotificationManager$startSendingRequestToClearDeviceToken$thread$1 implements Runnable {
    final /* synthetic */ String $token;
    final /* synthetic */ String $url;
    final /* synthetic */ long $userId;
    final /* synthetic */ PushNotificationManager this$0;

    PushNotificationManager$startSendingRequestToClearDeviceToken$thread$1(PushNotificationManager pushNotificationManager, String str, String str2, long j) {
        this.this$0 = pushNotificationManager;
        this.$url = str;
        this.$token = str2;
        this.$userId = j;
    }

    public final void run() {
        try {
            Ln.d("sending request to server to clear device token id", new Object[0]);
            new Builder().build().newCall(new Request.Builder().url(this.$url).post(new FormBody.Builder().add("device_token", this.$token).add("user_id", Long.toString(this.$userId)).build()).build()).execute();
            Object obj = this.this$0.globalSettingsService.get();
            Intrinsics.checkExpressionValueIsNotNull(obj, "globalSettingsService.get()");
            if (((GlobalSettingsService) obj).getEncodedGCMToken() == null) {
                return;
            }
        } catch (Exception e) {
            Ln.e(e);
            Object obj2 = this.this$0.globalSettingsService.get();
            Intrinsics.checkExpressionValueIsNotNull(obj2, "globalSettingsService.get()");
            if (((GlobalSettingsService) obj2).getEncodedGCMToken() == null) {
                return;
            }
        } catch (Throwable th) {
            Object obj3 = this.this$0.globalSettingsService.get();
            Intrinsics.checkExpressionValueIsNotNull(obj3, "globalSettingsService.get()");
            if (((GlobalSettingsService) obj3).getEncodedGCMToken() != null) {
                ((GlobalSettingsService) this.this$0.globalSettingsService.get()).clearGCMToken();
            }
            throw th;
        }
        ((GlobalSettingsService) this.this$0.globalSettingsService.get()).clearGCMToken();
    }
}
