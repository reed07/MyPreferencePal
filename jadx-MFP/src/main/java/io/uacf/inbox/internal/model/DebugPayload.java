package io.uacf.inbox.internal.model;

import com.google.gson.annotations.Expose;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import io.uacf.core.app.UacfAppId;
import java.util.UUID;

public class DebugPayload {
    @Expose
    private InboxContext inboxContext;
    @Expose
    private DebugNotificationPayload notification;

    public DebugPayload(String str, UacfAppId uacfAppId, String str2, String str3, boolean z, long j, String str4) {
        String uuid = UUID.randomUUID().toString();
        String str5 = str;
        UacfAppId uacfAppId2 = uacfAppId;
        this.inboxContext = new InboxContext(str, uacfAppId, uuid);
        DebugNotificationPayload debugNotificationPayload = new DebugNotificationPayload(str, uacfAppId, str2, str3, uuid, z, j > 0 ? GsonMappableIso8601Date.newInstance(j) : null, str4);
        this.notification = debugNotificationPayload;
    }
}
