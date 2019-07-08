package com.liulishuo.filedownloader.notification;

import android.util.SparseArray;
import com.liulishuo.filedownloader.notification.BaseNotificationItem;

public class FileDownloadNotificationHelper<T extends BaseNotificationItem> {
    private final SparseArray<T> notificationArray = new SparseArray<>();
}
