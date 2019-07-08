package com.myfitnesspal.feature.notificationinbox.ui.view;

import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.notificationinbox.ui.adapter.NotificationCursorAdapter;
import com.myfitnesspal.feature.notificationinbox.ui.adapter.NotificationsAdapter.NotificationsAdapterOperationListener;

public class ImageTextViewHolder extends BaseNotificationViewHolder {
    public ImageTextViewHolder(ViewGroup viewGroup, NotificationsAdapterOperationListener notificationsAdapterOperationListener, NotificationCursorAdapter.NotificationsAdapterOperationListener notificationsAdapterOperationListener2) {
        super(R.layout.notification_image_text_item, viewGroup, notificationsAdapterOperationListener, notificationsAdapterOperationListener2);
    }
}
