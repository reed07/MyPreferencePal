package com.myfitnesspal.feature.notificationinbox.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import com.myfitnesspal.feature.notificationinbox.model.NotificationTypes;
import com.myfitnesspal.feature.notificationinbox.ui.view.ImageTextImageViewHolder;
import com.myfitnesspal.feature.notificationinbox.ui.view.ImageTextViewHolder;
import com.myfitnesspal.feature.notificationinbox.util.NotificationInboxUtil;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.uacf.core.exception.UacfNotImplementedException;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.UacfNotificationSdkFactory;
import io.uacf.inbox.sdk.model.UacfNotification;

public class NotificationCursorAdapter extends Adapter<RecyclerViewHolder<UacfNotification>> {
    private NotificationsAdapterOperationListener adapterOperationListener;
    InboxCursorAdapter cursorAdapter;

    private static final class InboxCursorAdapter extends CursorAdapter {
        public InboxCursorAdapter(Context context, Cursor cursor, int i) {
            super(context, cursor, i);
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            throw new UacfNotImplementedException();
        }

        public void bindView(View view, Context context, Cursor cursor) {
            throw new UacfNotImplementedException();
        }

        public void bindView(RecyclerViewHolder<UacfNotification> recyclerViewHolder, Cursor cursor, int i) {
            recyclerViewHolder.setData(NotificationCursorAdapter.getSdk().getObjectFromCursor(cursor), i);
        }
    }

    public interface NotificationsAdapterOperationListener {
        void onItemClicked(UacfNotification uacfNotification);

        void onItemLongClicked(UacfNotification uacfNotification);

        void onProfileImageClicked(UacfNotification uacfNotification);
    }

    public Cursor swapCursor(Cursor cursor) {
        return this.cursorAdapter.swapCursor(cursor);
    }

    public int getCount() {
        return this.cursorAdapter.getCount();
    }

    public NotificationCursorAdapter(Context context, Cursor cursor) {
        this.cursorAdapter = new InboxCursorAdapter(context, cursor, 0);
    }

    /* access modifiers changed from: private */
    public static UacfNotificationSdk getSdk() {
        return new UacfNotificationSdkFactory().newSdkInstance();
    }

    public RecyclerViewHolder<UacfNotification> onCreateViewHolder(ViewGroup viewGroup, int i) {
        NotificationTypes notificationTypes = NotificationTypes.values()[i];
        switch (notificationTypes) {
            case ImageTextType:
                return new ImageTextViewHolder(viewGroup, null, this.adapterOperationListener);
            case ImageTextImageType:
                return new ImageTextImageViewHolder(viewGroup, null, this.adapterOperationListener);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown view type: ");
                sb.append(notificationTypes);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public int getItemViewType(int i) {
        return NotificationInboxUtil.getNotificationType(getItem(i)).ordinal();
    }

    public void onBindViewHolder(RecyclerViewHolder<UacfNotification> recyclerViewHolder, int i) {
        this.cursorAdapter.getCursor().moveToPosition(i);
        InboxCursorAdapter inboxCursorAdapter = this.cursorAdapter;
        inboxCursorAdapter.bindView(recyclerViewHolder, inboxCursorAdapter.getCursor(), i);
    }

    public int getItemCount() {
        return this.cursorAdapter.getCount();
    }

    public UacfNotification getItem(int i) {
        this.cursorAdapter.getCursor().moveToPosition(i);
        return getSdk().getObjectFromCursor(this.cursorAdapter.getCursor());
    }

    public void setAdapterOperationListener(NotificationsAdapterOperationListener notificationsAdapterOperationListener) {
        this.adapterOperationListener = notificationsAdapterOperationListener;
    }
}
