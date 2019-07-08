package io.uacf.inbox.internal.model;

import com.google.gson.annotations.Expose;
import io.uacf.core.app.UacfAppId;

/* compiled from: DebugPayload */
class InboxContext {
    @Expose
    private String application;
    @Expose
    private final String engagementId;
    @Expose
    private Recipient recipient;

    /* compiled from: DebugPayload */
    public static final class Recipient {
        @Expose
        public String domain = "UACF";
        @Expose
        public String id;

        public Recipient(String str) {
            this.id = str;
        }
    }

    public InboxContext(String str, UacfAppId uacfAppId, String str2) {
        this.recipient = new Recipient(str);
        this.application = uacfAppId.toString();
        this.engagementId = str2;
    }
}
