package io.uacf.inbox.sdk;

import android.database.Cursor;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import io.uacf.inbox.internal.model.Notification;
import io.uacf.inbox.sdk.model.UacfNotification;
import java.util.List;

public abstract class UacfNotificationSdk implements SyncItemHandler<Notification> {
    private static final UacfNotificationInboxSettings settings = new UacfNotificationInboxSettings(2, false);

    public abstract boolean deleteNotification(String str);

    public abstract boolean deleteNotifications(List<String> list);

    public abstract boolean expireNotifications(List<String> list);

    public abstract List<UacfNotification> getAllNotifications();

    public abstract Cursor getAllNotificationsAsCursor();

    public abstract int getCountForStates(String... strArr);

    public abstract UacfNotification getObjectFromCursor(Cursor cursor);

    public abstract void reportNotificationRead(String str, int i, int i2);

    public abstract boolean updateNotification(String str, String str2);

    public abstract boolean updateNotifications(String str, String str2);

    public abstract boolean updateNotifications(List<String> list, String str);

    public static UacfNotificationInboxSettings getSettings() {
        return settings;
    }
}
