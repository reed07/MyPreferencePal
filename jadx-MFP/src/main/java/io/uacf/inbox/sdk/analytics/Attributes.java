package io.uacf.inbox.sdk.analytics;

import com.google.gson.annotations.Expose;
import java.util.Map;

public final class Attributes {

    public static final class NotificationDeleted {
        @Expose
        private final Map<String, Object> analyticData;
        @Expose
        private final String engagementId;
        @Expose
        private final boolean priority;
        @Expose
        private final String state;

        public NotificationDeleted(String str, Map<String, Object> map, String str2, boolean z) {
            this.engagementId = str;
            this.analyticData = map;
            this.state = str2;
            this.priority = z;
        }
    }

    public static final class NotificationExpired {
        @Expose
        private final Map<String, Object> analyticData;
        @Expose
        private final String engagementId;
        @Expose
        private final boolean priority;
        @Expose
        private final String state;

        public NotificationExpired(String str, Map<String, Object> map, String str2, boolean z) {
            this.engagementId = str;
            this.analyticData = map;
            this.state = str2;
            this.priority = z;
        }
    }

    public static final class NotificationRead {
        @Expose
        private final Map<String, Object> analyticData;
        @Expose
        private final String engagementId;
        @Expose
        private final int listItemCount;
        @Expose
        private final int position;
        @Expose
        private final boolean priority;

        public NotificationRead(String str, Map<String, Object> map, int i, int i2, boolean z) {
            this.engagementId = str;
            this.analyticData = map;
            this.position = i;
            this.listItemCount = i2;
            this.priority = z;
        }
    }

    public static final class NotificationReceived {
        @Expose
        private final Map<String, Object> analyticData;
        @Expose
        private final String engagementId;
        @Expose
        private final long latency;
        @Expose
        private final boolean priority;

        public NotificationReceived(String str, Map<String, Object> map, long j, boolean z) {
            this.engagementId = str;
            this.analyticData = map;
            this.latency = j;
            this.priority = z;
        }
    }

    public static final class NotificationsOpened {
        @Expose
        private final int pendingCount;
        @Expose
        private final int readCount;
        @Expose
        private final int unreadCount;

        public NotificationsOpened(int i, int i2, int i3) {
            this.pendingCount = i;
            this.unreadCount = i2;
            this.readCount = i3;
        }
    }
}
