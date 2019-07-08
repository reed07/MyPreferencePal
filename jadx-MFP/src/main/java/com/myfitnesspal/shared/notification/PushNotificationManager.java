package com.myfitnesspal.shared.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import com.google.firebase.iid.FirebaseInstanceId;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.extension.BuildUtil;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpNotificationPayload;
import com.myfitnesspal.shared.model.v2.MfpNotificationType;
import com.myfitnesspal.shared.notification.model.MfpNotificationChannel;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000W\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003*\u0001\u0013\u0018\u0000 !2\u00020\u0001:\u0001!Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0010\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u0018J\u0006\u0010\u001b\u001a\u00020\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\u0018\u0010\u001d\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u0016R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/myfitnesspal/shared/notification/PushNotificationManager;", "", "context", "Landroid/content/Context;", "apiUrlProvider", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/api/ApiUrlProvider;", "mfpNotificationHandler", "Lcom/myfitnesspal/shared/notification/MfpNotificationHandler;", "syncService", "Lcom/myfitnesspal/shared/service/syncv2/SyncService;", "analyticsService", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "globalSettingsService", "Lcom/myfitnesspal/shared/service/globalsettings/GlobalSettingsService;", "session", "Lcom/myfitnesspal/shared/service/session/Session;", "(Landroid/content/Context;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;)V", "syncDebouncer", "com/myfitnesspal/shared/notification/PushNotificationManager$syncDebouncer$1", "Lcom/myfitnesspal/shared/notification/PushNotificationManager$syncDebouncer$1;", "processMessage", "", "payload", "", "registerTokenWithBackend", "token", "registerUserForFCM", "retryRegistrationWithBackOff", "startSendingRequestToClearDeviceToken", "userId", "", "unregisterUserFromFCM", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PushNotificationManager.kt */
public final class PushNotificationManager {
    public static final Companion Companion = new Companion(null);
    private static final String FCM = "FCM";
    private static final String FCM_SENDER_ACCOUNT = "513007887437";
    private static final String NOTIFICATION_TYPE_NOTICE = "notice";
    private static final String NOTIFICATION_TYPE_REMINDER = "reminder";
    private static final String NOTIFICATION_TYPE_SYNC = "sync";
    private static final String NOTIFICATION_TYPE_URL_HANDLER = "url_handler";
    private static final int SYNC_DELAY_MS = 10000;
    private static final String SYNC_PUSH_EVENT = "sync_silent_push_received";
    /* access modifiers changed from: private */
    public static long backOffTime;
    private final Lazy<AnalyticsService> analyticsService;
    private final Lazy<ApiUrlProvider> apiUrlProvider;
    private final Context context;
    /* access modifiers changed from: private */
    public final Lazy<GlobalSettingsService> globalSettingsService;
    private final Lazy<MfpNotificationHandler> mfpNotificationHandler;
    private final Lazy<Session> session;
    private final PushNotificationManager$syncDebouncer$1 syncDebouncer = new PushNotificationManager$syncDebouncer$1(this, 10000);
    /* access modifiers changed from: private */
    public final Lazy<SyncService> syncService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/myfitnesspal/shared/notification/PushNotificationManager$Companion;", "", "()V", "FCM", "", "FCM_SENDER_ACCOUNT", "NOTIFICATION_TYPE_NOTICE", "NOTIFICATION_TYPE_REMINDER", "NOTIFICATION_TYPE_SYNC", "NOTIFICATION_TYPE_URL_HANDLER", "SYNC_DELAY_MS", "", "SYNC_PUSH_EVENT", "backOffTime", "", "createNotificationChannels", "", "context", "Landroid/content/Context;", "pushNotificationsEnabled", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: PushNotificationManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void createNotificationChannels(@NotNull Context context) {
            MfpNotificationChannel[] values;
            Intrinsics.checkParameterIsNotNull(context, "context");
            if (BuildUtil.isOreoOrHigher()) {
                Object systemService = context.getSystemService("notification");
                if (!(systemService instanceof NotificationManager)) {
                    systemService = null;
                }
                NotificationManager notificationManager = (NotificationManager) systemService;
                if (notificationManager != null) {
                    for (MfpNotificationChannel mfpNotificationChannel : MfpNotificationChannel.values()) {
                        NotificationChannel notificationChannel = new NotificationChannel(mfpNotificationChannel.getChannelId(), context.getString(mfpNotificationChannel.getChannelNameResId()), 3);
                        notificationChannel.setDescription(context.getString(mfpNotificationChannel.getDescriptionResId()));
                        notificationChannel.enableLights(true);
                        notificationChannel.enableVibration(true);
                        notificationManager.createNotificationChannel(notificationChannel);
                    }
                }
            }
        }

        @JvmStatic
        public final boolean pushNotificationsEnabled() {
            MyFitnessPalApp instance = MyFitnessPalApp.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(instance, "MyFitnessPalApp.getInstance()");
            return instance.getResources().getBoolean(R.bool.ENABLE_PUSH_NOTIFICATIONS);
        }
    }

    @JvmStatic
    public static final void createNotificationChannels(@NotNull Context context2) {
        Companion.createNotificationChannels(context2);
    }

    @JvmStatic
    public static final boolean pushNotificationsEnabled() {
        return Companion.pushNotificationsEnabled();
    }

    public PushNotificationManager(@NotNull Context context2, @NotNull Lazy<ApiUrlProvider> lazy, @NotNull Lazy<MfpNotificationHandler> lazy2, @NotNull Lazy<SyncService> lazy3, @NotNull Lazy<AnalyticsService> lazy4, @NotNull Lazy<GlobalSettingsService> lazy5, @NotNull Lazy<Session> lazy6) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(lazy, "apiUrlProvider");
        Intrinsics.checkParameterIsNotNull(lazy2, "mfpNotificationHandler");
        Intrinsics.checkParameterIsNotNull(lazy3, "syncService");
        Intrinsics.checkParameterIsNotNull(lazy4, "analyticsService");
        Intrinsics.checkParameterIsNotNull(lazy5, "globalSettingsService");
        Intrinsics.checkParameterIsNotNull(lazy6, "session");
        this.context = context2;
        this.apiUrlProvider = lazy;
        this.mfpNotificationHandler = lazy2;
        this.syncService = lazy3;
        this.analyticsService = lazy4;
        this.globalSettingsService = lazy5;
        this.session = lazy6;
    }

    public final void registerUserForFCM() {
        FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "FirebaseInstanceId.getInstance()");
        instance.getInstanceId().addOnCompleteListener(new PushNotificationManager$registerUserForFCM$1(this));
    }

    public final void unregisterUserFromFCM() {
        Object obj = this.globalSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "globalSettingsService.get()");
        startSendingRequestToClearDeviceToken(((GlobalSettingsService) obj).getEncodedFCMToken(), ((Session) this.session.get()).getUser().getMasterDatabaseId());
        ((GlobalSettingsService) this.globalSettingsService.get()).clearFCMToken();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new PushNotificationManager$unregisterUserFromFCM$1(null), 3, null);
    }

    public final void registerTokenWithBackend(@Nullable String str) {
        User user = ((Session) this.session.get()).getUser();
        Object obj = this.globalSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "globalSettingsService.get()");
        startSendingRequestToClearDeviceToken(((GlobalSettingsService) obj).getEncodedGCMToken(), user.getMasterDatabaseId());
        Object obj2 = this.globalSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj2, "globalSettingsService.get()");
        if (!Strings.equals(((GlobalSettingsService) obj2).getFCMToken(), str)) {
            Object obj3 = this.globalSettingsService.get();
            Intrinsics.checkExpressionValueIsNotNull(obj3, "globalSettingsService.get()");
            startSendingRequestToClearDeviceToken(((GlobalSettingsService) obj3).getEncodedFCMToken(), user.getMasterDatabaseId());
            if (user.isLoggedIn()) {
                CharSequence charSequence = str;
                if (!(charSequence == null || charSequence.length() == 0)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("register fcm token: ");
                    sb.append(str);
                    Ln.i(sb.toString(), new Object[0]);
                    Object obj4 = this.globalSettingsService.get();
                    Intrinsics.checkExpressionValueIsNotNull(obj4, "globalSettingsService.get()");
                    ((GlobalSettingsService) obj4).setFCMToken(str);
                    return;
                }
                return;
            }
            ((GlobalSettingsService) this.globalSettingsService.get()).clearFCMToken();
        }
    }

    public final void processMessage(@Nullable String str) {
        User user = ((Session) this.session.get()).getUser();
        if (!user.isLoggedIn() && user.hasMasterDatabaseId()) {
            unregisterUserFromFCM();
        }
        if (str != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("received a push notification: ");
            sb.append(str);
            Ln.i(sb.toString(), new Object[0]);
            MfpNotificationPayload mfpNotificationPayload = (MfpNotificationPayload) new ApiJsonMapper().withType(MfpNotificationPayload.class).tryMapFrom(str);
            if (mfpNotificationPayload != null) {
                String type = mfpNotificationPayload.getType();
                if (Strings.notEmpty(type)) {
                    if (Strings.equalsIgnoreCase(type, NOTIFICATION_TYPE_NOTICE)) {
                        MfpNotificationType objectType = mfpNotificationPayload.getObjectType();
                        if (objectType != null) {
                            ((MfpNotificationHandler) this.mfpNotificationHandler.get()).handleNoticeNotificationForItemType(this.context, objectType, mfpNotificationPayload);
                        }
                    } else if (Strings.equalsIgnoreCase(type, "reminder")) {
                        ((MfpNotificationHandler) this.mfpNotificationHandler.get()).handleNoticeNotificationForItemType(this.context, MfpNotificationType.REMINDER, mfpNotificationPayload);
                    } else if (Strings.equalsIgnoreCase(type, NOTIFICATION_TYPE_URL_HANDLER)) {
                        ((MfpNotificationHandler) this.mfpNotificationHandler.get()).handleNoticeNotificationForItemType(this.context, MfpNotificationType.URL_HANDLER, mfpNotificationPayload);
                    } else if (Strings.equalsIgnoreCase(type, NOTIFICATION_TYPE_SYNC)) {
                        ((AnalyticsService) this.analyticsService.get()).reportEvent(SYNC_PUSH_EVENT);
                        this.syncDebouncer.call();
                    }
                }
            } else {
                Ln.e("Unable to map push notification payload %s", str);
            }
        }
    }

    public final void startSendingRequestToClearDeviceToken(@Nullable String str, long j) {
        CharSequence charSequence = str;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            Object obj = this.apiUrlProvider.get();
            Intrinsics.checkExpressionValueIsNotNull(obj, "apiUrlProvider.get()");
            PushNotificationManager$startSendingRequestToClearDeviceToken$thread$1 pushNotificationManager$startSendingRequestToClearDeviceToken$thread$1 = new PushNotificationManager$startSendingRequestToClearDeviceToken$thread$1(this, ((ApiUrlProvider) obj).getClearDeviceTokenUrl(), str, j);
            Thread thread = new Thread(pushNotificationManager$startSendingRequestToClearDeviceToken$thread$1);
            thread.setName("startSendingRequestToClearDeviceToken");
            thread.start();
        }
    }

    /* access modifiers changed from: private */
    public final void retryRegistrationWithBackOff() {
        new Thread(new PushNotificationManager$retryRegistrationWithBackOff$1(this)).start();
    }
}
