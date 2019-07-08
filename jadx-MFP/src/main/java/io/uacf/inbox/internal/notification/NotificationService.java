package io.uacf.inbox.internal.notification;

import android.database.Cursor;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import io.uacf.core.api.UacfApiException;
import io.uacf.inbox.internal.model.Notification;
import java.util.List;

public interface NotificationService extends SyncItemHandler<Notification> {
    void createDebugNotification(String str) throws UacfApiException;

    void createDebugNotification(boolean z, long j, String str) throws UacfApiException;

    List<Notification> getAllNotifications();

    Cursor getAllNotificationsAsCursor();

    int getCountForStates(String... strArr);

    Notification getObjectFromCursor(Cursor cursor);

    boolean localDeleteNotification(String str);

    boolean localDeleteNotifications(List<String> list);

    boolean localExpireNotifications(List<String> list);

    boolean localUpdateNotifications(String str, String str2);

    boolean localUpdateNotifications(List<String> list, String str);

    void reportNotificationRead(String str, int i, int i2);
}
