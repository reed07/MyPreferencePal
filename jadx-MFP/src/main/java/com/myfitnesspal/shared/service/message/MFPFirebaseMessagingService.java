package com.myfitnesspal.shared.service.message;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.syncv1.ApiDeviceTokenProvider;
import com.uacf.core.util.Ln;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/myfitnesspal/shared/service/message/MFPFirebaseMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "apiDeviceTokenProvider", "Lcom/myfitnesspal/shared/service/syncv1/ApiDeviceTokenProvider;", "getApiDeviceTokenProvider", "()Lcom/myfitnesspal/shared/service/syncv1/ApiDeviceTokenProvider;", "setApiDeviceTokenProvider", "(Lcom/myfitnesspal/shared/service/syncv1/ApiDeviceTokenProvider;)V", "pushNotificationManager", "Lcom/myfitnesspal/shared/notification/PushNotificationManager;", "getPushNotificationManager", "()Lcom/myfitnesspal/shared/notification/PushNotificationManager;", "setPushNotificationManager", "(Lcom/myfitnesspal/shared/notification/PushNotificationManager;)V", "onCreate", "", "onMessageReceived", "message", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: MFPFirebaseMessagingService.kt */
public final class MFPFirebaseMessagingService extends FirebaseMessagingService {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_PAYLOAD = "payload";
    @Inject
    @NotNull
    public ApiDeviceTokenProvider apiDeviceTokenProvider;
    @Inject
    @NotNull
    public PushNotificationManager pushNotificationManager;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/shared/service/message/MFPFirebaseMessagingService$Companion;", "", "()V", "EXTRA_PAYLOAD", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MFPFirebaseMessagingService.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public final PushNotificationManager getPushNotificationManager() {
        PushNotificationManager pushNotificationManager2 = this.pushNotificationManager;
        if (pushNotificationManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pushNotificationManager");
        }
        return pushNotificationManager2;
    }

    public final void setPushNotificationManager(@NotNull PushNotificationManager pushNotificationManager2) {
        Intrinsics.checkParameterIsNotNull(pushNotificationManager2, "<set-?>");
        this.pushNotificationManager = pushNotificationManager2;
    }

    @NotNull
    public final ApiDeviceTokenProvider getApiDeviceTokenProvider() {
        ApiDeviceTokenProvider apiDeviceTokenProvider2 = this.apiDeviceTokenProvider;
        if (apiDeviceTokenProvider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiDeviceTokenProvider");
        }
        return apiDeviceTokenProvider2;
    }

    public final void setApiDeviceTokenProvider(@NotNull ApiDeviceTokenProvider apiDeviceTokenProvider2) {
        Intrinsics.checkParameterIsNotNull(apiDeviceTokenProvider2, "<set-?>");
        this.apiDeviceTokenProvider = apiDeviceTokenProvider2;
    }

    public void onCreate() {
        super.onCreate();
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void onNewToken(@Nullable String str) {
        super.onNewToken(str);
        StringBuilder sb = new StringBuilder();
        sb.append("fcm token: ");
        sb.append(str);
        Ln.i(sb.toString(), new Object[0]);
        PushNotificationManager pushNotificationManager2 = this.pushNotificationManager;
        if (pushNotificationManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pushNotificationManager");
        }
        pushNotificationManager2.registerTokenWithBackend(str);
    }

    public void onMessageReceived(@Nullable RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage != null) {
            Map data = remoteMessage.getData();
            if (data != null) {
                data.isEmpty();
                PushNotificationManager pushNotificationManager2 = this.pushNotificationManager;
                if (pushNotificationManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pushNotificationManager");
                }
                pushNotificationManager2.processMessage((String) remoteMessage.getData().get("payload"));
            }
        }
    }
}
