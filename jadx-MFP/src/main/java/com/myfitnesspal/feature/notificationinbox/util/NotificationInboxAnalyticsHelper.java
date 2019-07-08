package com.myfitnesspal.feature.notificationinbox.util;

import com.myfitnesspal.feature.notificationinbox.model.NotificationTapTarget;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;
import io.uacf.inbox.sdk.model.UacfNotificationAnalyticData;
import java.util.Date;
import java.util.Map;

public class NotificationInboxAnalyticsHelper {
    private final Lazy<AnalyticsService> analyticsService;

    interface Attributes {
        public static final String DEEPLINK_URL = "deeplink_url";
        public static final String ENGAGEMENT_ID = "engagement_id";
        public static final String ENTRY_POINT = "entry_point";
        public static final String NOTIFICATION_DATE = "notification_date";
        public static final String NOTIFICATION_STATE = "notification_state";
        public static final String NUMBER_OF_NEW_ITEMS = "number_of_new_items";
        public static final String PENDING_COUNT = "pending_count";
        public static final String POSITION = "position";
        public static final String READ_COUNT = "read_count";
        public static final String TAP_TARGET = "tap_target";
        public static final String UNREAD_COUNT = "unread_count";
    }

    interface Events {
        public static final String NOTIFICATION_INBOX_DISPLAYED = "notification_inbox_displayed";
        public static final String NOTIFICATION_INBOX_EMPTY_STATE_ADD_FRIENDS_TAPPED = "notification_inbox_empty_state_add_friends_tapped";
        public static final String NOTIFICATION_INBOX_NEW_ITEMS_PILL_DISPLAYED = "notification_inbox_new_items_pill_displayed";
        public static final String NOTIFICATION_INBOX_NEW_ITEMS_PILL_TAPPED = "notification_inbox_new_items_pill_tapped";
        public static final String NOTIFICATION_INBOX_NOTIFICATION_DELETED = "notification_inbox_notification_deleted";
        public static final String NOTIFICATION_INBOX_NOTIFICATION_TAPPED = "notification_inbox_notification_tapped";
        public static final String NOTIFICATION_INBOX_PULL_TO_REFRESH = "notification_inbox_pull_to_refresh";
    }

    public NotificationInboxAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportPillDisplayed(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_INBOX_NEW_ITEMS_PILL_DISPLAYED, MapUtil.createMap(Attributes.NUMBER_OF_NEW_ITEMS, String.valueOf(i)));
    }

    public void reportPillTapped(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_INBOX_NEW_ITEMS_PILL_TAPPED, MapUtil.createMap(Attributes.NUMBER_OF_NEW_ITEMS, String.valueOf(i)));
    }

    public void reportPullToRefresh() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_INBOX_PULL_TO_REFRESH);
    }

    public void reportNotificationTapped(NotificationTapTarget notificationTapTarget, String str, String str2, int i, UacfNotificationAnalyticData uacfNotificationAnalyticData, Date date) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_INBOX_NOTIFICATION_TAPPED, addDataFromNotificationPayload(MapUtil.createMap(Attributes.TAP_TARGET, notificationTapTarget.getTarget(), Attributes.ENGAGEMENT_ID, str, Attributes.DEEPLINK_URL, str2, "position", String.valueOf(i), Attributes.NOTIFICATION_DATE, DateTimeUtils.getDateStringISOFormatFromDate(date)), uacfNotificationAnalyticData));
    }

    public void reportNotificationDeleted(String str, String str2, int i, String str3, UacfNotificationAnalyticData uacfNotificationAnalyticData) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_INBOX_NOTIFICATION_DELETED, addDataFromNotificationPayload(MapUtil.createMap(Attributes.ENGAGEMENT_ID, str, Attributes.DEEPLINK_URL, str2, "position", String.valueOf(i), Attributes.NOTIFICATION_STATE, str3), uacfNotificationAnalyticData));
    }

    public void reportNotifEmptyStateAddFriendTapped() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_INBOX_EMPTY_STATE_ADD_FRIENDS_TAPPED);
    }

    public void reportNotifInboxDisplayed(int i, int i2, int i3, String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_INBOX_DISPLAYED, MapUtil.createMap(Attributes.PENDING_COUNT, String.valueOf(i), Attributes.READ_COUNT, String.valueOf(i3), Attributes.UNREAD_COUNT, String.valueOf(i2), "entry_point", str));
    }

    private Map<String, String> addDataFromNotificationPayload(Map<String, String> map, UacfNotificationAnalyticData uacfNotificationAnalyticData) {
        if (!(uacfNotificationAnalyticData == null || uacfNotificationAnalyticData.getData() == null)) {
            Map data = uacfNotificationAnalyticData.getData();
            for (String str : data.keySet()) {
                map.put(str, String.valueOf(data.get(str)));
            }
        }
        return map;
    }
}
