package com.myfitnesspal.feature.notificationinbox.ui.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;
import com.myfitnesspal.feature.notificationinbox.model.NotificationTypes;
import com.myfitnesspal.feature.notificationinbox.ui.view.ImageTextImageViewHolder;
import com.myfitnesspal.feature.notificationinbox.ui.view.ImageTextViewHolder;
import com.myfitnesspal.feature.notificationinbox.util.NotificationInboxUtil;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import io.uacf.inbox.sdk.model.UacfNotification;
import java.util.List;

public class NotificationsAdapter extends Adapter<RecyclerViewHolder<UacfNotification>> {
    private final NotificationsAdapterOperationListener adapterOperationListener;
    private List<UacfNotification> notifications;

    public interface NotificationsAdapterOperationListener {
        void onItemClicked(int i);

        void onItemLongClicked(int i);

        void onProfileImageClicked(int i);
    }

    public NotificationsAdapter(List<UacfNotification> list, NotificationsAdapterOperationListener notificationsAdapterOperationListener) {
        this.notifications = list;
        this.adapterOperationListener = notificationsAdapterOperationListener;
    }

    public RecyclerViewHolder<UacfNotification> onCreateViewHolder(ViewGroup viewGroup, int i) {
        NotificationTypes notificationTypes = NotificationTypes.values()[i];
        switch (notificationTypes) {
            case ImageTextType:
                return new ImageTextViewHolder(viewGroup, this.adapterOperationListener, null);
            case ImageTextImageType:
                return new ImageTextImageViewHolder(viewGroup, this.adapterOperationListener, null);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown view type: ");
                sb.append(notificationTypes);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public void onBindViewHolder(RecyclerViewHolder<UacfNotification> recyclerViewHolder, int i) {
        recyclerViewHolder.setData(getItem(i), i);
    }

    public int getItemViewType(int i) {
        return NotificationInboxUtil.getNotificationType(getItem(i)).ordinal();
    }

    public int getItemCount() {
        return this.notifications.size();
    }

    public UacfNotification getItem(int i) {
        return (UacfNotification) this.notifications.get(i);
    }
}
