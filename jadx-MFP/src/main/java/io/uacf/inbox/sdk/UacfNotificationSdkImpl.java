package io.uacf.inbox.sdk;

import android.database.Cursor;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function2;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.provider.sdk.model.SyncItem;
import io.uacf.inbox.internal.model.Notification;
import io.uacf.inbox.internal.model.NotificationContent;
import io.uacf.inbox.internal.notification.NotificationService;
import io.uacf.inbox.sdk.model.UacfNotification;
import io.uacf.inbox.sdk.model.UacfNotification.Builder;
import io.uacf.inbox.sdk.model.UacfNotificationAnalyticData;
import io.uacf.inbox.sdk.model.UacfNotificationContent;
import io.uacf.inbox.sdk.model.UacfNotificationContent.Body;
import io.uacf.inbox.sdk.model.UacfNotificationContent.Image;
import io.uacf.inbox.sdk.model.UacfNotificationContent.Link;
import io.uacf.inbox.sdk.model.UacfNotificationContent.PlainText;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UacfNotificationSdkImpl extends UacfNotificationSdk {
    private final NotificationService notificationService;

    public UacfNotificationSdkImpl(NotificationService notificationService2) {
        this.notificationService = notificationService2;
    }

    public List<UacfNotification> getAllNotifications() {
        return buildUacfNotificationArrayList(this.notificationService.getAllNotifications());
    }

    public Cursor getAllNotificationsAsCursor() {
        return this.notificationService.getAllNotificationsAsCursor();
    }

    public UacfNotification getObjectFromCursor(Cursor cursor) {
        Notification objectFromCursor = this.notificationService.getObjectFromCursor(cursor);
        if (objectFromCursor != null) {
            return buildUacfNotification(objectFromCursor);
        }
        return null;
    }

    public boolean updateNotification(String str, String str2) {
        return updateNotifications(Collections.singletonList(str), str2);
    }

    public boolean updateNotifications(List<String> list, String str) {
        return this.notificationService.localUpdateNotifications(list, str);
    }

    public boolean updateNotifications(String str, String str2) {
        return this.notificationService.localUpdateNotifications(str, str2);
    }

    public void reportNotificationRead(String str, int i, int i2) {
        this.notificationService.reportNotificationRead(str, i, i2);
    }

    public boolean deleteNotification(String str) {
        return this.notificationService.localDeleteNotification(str);
    }

    public boolean deleteNotifications(List<String> list) {
        return this.notificationService.localDeleteNotifications(list);
    }

    public boolean expireNotifications(List<String> list) {
        return this.notificationService.localExpireNotifications(list);
    }

    public int getCountForStates(String... strArr) {
        return this.notificationService.getCountForStates(strArr);
    }

    private List<UacfNotification> buildUacfNotificationArrayList(List<Notification> list) {
        return Enumerable.select((Collection<T>) list, false, (ReturningFunction1<U, T>) new ReturningFunction1<UacfNotification, Notification>() {
            public UacfNotification execute(Notification notification) {
                return UacfNotificationSdkImpl.this.buildUacfNotification(notification);
            }
        });
    }

    private PlainText buildUacfNotificationContentPlainText(NotificationContent.PlainText plainText) {
        if (plainText != null) {
            return new PlainText(plainText.getText());
        }
        return null;
    }

    private Link buildUacfNotificationContentLink(NotificationContent.Link link) {
        if (link != null) {
            return new Link(link.getDeeplink());
        }
        return null;
    }

    private Image buildUacfNotificationContentImage(NotificationContent.Image image) {
        if (image != null) {
            return new Image(image.getUri(), buildUacfNotificationContentLink(image.getLink()));
        }
        return null;
    }

    private Body buildUacfNotificationContentBody(NotificationContent.Body body) {
        if (body != null) {
            return new Body(buildUacfNotificationContentPlainText(body.getPlainText()), buildUacfNotificationContentLink(body.getLink()), buildUacfNotificationContentImage(body.getImage()));
        }
        return null;
    }

    private UacfNotificationContent buildUacfNotificationContent(NotificationContent notificationContent) {
        if (notificationContent != null) {
            return new UacfNotificationContent(buildUacfNotificationContentBody(notificationContent.getBody()), buildUacfNotificationContentImage(notificationContent.getPrimaryImage()), buildUacfNotificationContentImage(notificationContent.getSecondaryImage()));
        }
        return null;
    }

    private UacfNotificationAnalyticData buildUacfNotificationAnalyticData(Notification notification) {
        if (notification == null || notification.getAnalyticData() == null) {
            return null;
        }
        return new UacfNotificationAnalyticData(notification.getAnalyticData().getData());
    }

    /* access modifiers changed from: private */
    public UacfNotification buildUacfNotification(Notification notification) {
        if (notification.getCreatedAt() == null) {
            return null;
        }
        return new Builder().withEngagementId(notification.getEngagementId()).withCollapseKey(notification.getCollapseKey()).withState(notification.getState()).withCategory(notification.getCategory()).withCreatedAt(new Date(notification.getCreatedAt().getTime())).withAnalytic(buildUacfNotificationAnalyticData(notification)).withContent(buildUacfNotificationContent(notification.getContent())).withExpiresAt(notification.getExpiresAt()).withMarkedAsExpired(notification.getMarkedAsExpired()).withPriority(notification.getPriority()).build();
    }

    public String getSyncResourceName() {
        return this.notificationService.getSyncResourceName();
    }

    public Class<?> getSyncItemClass() {
        return this.notificationService.getSyncItemClass();
    }

    public void consumeSyncItems(List<SyncItem<Notification>> list) {
        this.notificationService.consumeSyncItems(list);
    }

    public void publishUnsyncedItems(Function2<Integer, Integer> function2) throws UacfScheduleException {
        this.notificationService.publishUnsyncedItems(function2);
    }
}
