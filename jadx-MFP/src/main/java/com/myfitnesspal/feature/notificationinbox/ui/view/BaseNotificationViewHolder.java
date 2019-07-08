package com.myfitnesspal.feature.notificationinbox.ui.view;

import android.os.Build.VERSION;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnContextClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.notificationinbox.ui.adapter.NotificationCursorAdapter;
import com.myfitnesspal.feature.notificationinbox.ui.adapter.NotificationsAdapter.NotificationsAdapterOperationListener;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Strings;
import io.uacf.inbox.sdk.model.UacfNotification;

public abstract class BaseNotificationViewHolder extends RecyclerViewHolder<UacfNotification> {
    /* access modifiers changed from: private */
    public NotificationsAdapterOperationListener adapterOperationListener;
    /* access modifiers changed from: private */
    public NotificationCursorAdapter.NotificationsAdapterOperationListener cursorAdapterOperationListener;
    @BindView(2131363174)
    View notifContainer;
    @BindView(2131363180)
    MfpImageView notifUserImage;
    private OnClickListener notifUserImageClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (BaseNotificationViewHolder.this.adapterOperationListener != null) {
                BaseNotificationViewHolder.this.adapterOperationListener.onProfileImageClicked(BaseNotificationViewHolder.this.getAdapterPosition());
            } else {
                BaseNotificationViewHolder.this.cursorAdapterOperationListener.onProfileImageClicked((UacfNotification) BaseNotificationViewHolder.this.itemView.getTag());
            }
        }
    };
    private OnLongClickListener notifUserImageOnLongClickListener = new OnLongClickListener() {
        public boolean onLongClick(View view) {
            if (BaseNotificationViewHolder.this.adapterOperationListener != null) {
                BaseNotificationViewHolder.this.adapterOperationListener.onItemLongClicked(BaseNotificationViewHolder.this.getAdapterPosition());
            } else {
                BaseNotificationViewHolder.this.cursorAdapterOperationListener.onItemLongClicked((UacfNotification) BaseNotificationViewHolder.this.itemView.getTag());
            }
            return true;
        }
    };
    @BindView(2131363731)
    View statusContainer;
    private OnClickListener statusContainerClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (BaseNotificationViewHolder.this.adapterOperationListener != null) {
                BaseNotificationViewHolder.this.adapterOperationListener.onItemClicked(BaseNotificationViewHolder.this.getAdapterPosition());
            } else {
                BaseNotificationViewHolder.this.cursorAdapterOperationListener.onItemClicked((UacfNotification) BaseNotificationViewHolder.this.itemView.getTag());
            }
        }
    };
    private OnLongClickListener statusContainerOnLongClickListener = new OnLongClickListener() {
        public boolean onLongClick(View view) {
            if (BaseNotificationViewHolder.this.adapterOperationListener != null) {
                BaseNotificationViewHolder.this.adapterOperationListener.onItemLongClicked(BaseNotificationViewHolder.this.getAdapterPosition());
            } else {
                BaseNotificationViewHolder.this.cursorAdapterOperationListener.onItemLongClicked((UacfNotification) BaseNotificationViewHolder.this.itemView.getTag());
            }
            return true;
        }
    };
    @BindView(2131363968)
    TextView tvNotifStatus;
    @BindView(2131363969)
    TextView tvNotifTime;

    public BaseNotificationViewHolder(int i, ViewGroup viewGroup, NotificationsAdapterOperationListener notificationsAdapterOperationListener, NotificationCursorAdapter.NotificationsAdapterOperationListener notificationsAdapterOperationListener2) {
        super(i, viewGroup);
        this.adapterOperationListener = notificationsAdapterOperationListener;
        this.cursorAdapterOperationListener = notificationsAdapterOperationListener2;
    }

    public void setData(UacfNotification uacfNotification, int i) {
        this.tvNotifStatus.setText(Html.fromHtml(uacfNotification.getBodyText()));
        this.notifUserImage.setTransformCircular(true);
        String primaryImageUri = uacfNotification.getPrimaryImageUri();
        if (Strings.notEmpty(primaryImageUri)) {
            this.notifUserImage.setUrl(primaryImageUri);
        }
        this.tvNotifTime.setText(DateTimeUtils.formatHumanReadableTime(this.context, uacfNotification.getCreatedAt()));
        this.itemView.setBackgroundColor(this.context.getResources().getColor(Strings.equals(uacfNotification.getState(), "READ") ? R.color.white : R.color.notif_unread_bg));
        this.itemView.setTag(uacfNotification);
        this.statusContainer.setOnLongClickListener(this.statusContainerOnLongClickListener);
        if (VERSION.SDK_INT >= 23) {
            this.statusContainer.setOnContextClickListener(new OnContextClickListener() {
                public final boolean onContextClick(View view) {
                    return BaseNotificationViewHolder.lambda$setData$0(BaseNotificationViewHolder.this, view);
                }
            });
        }
        this.notifUserImage.setOnLongClickListener(this.notifUserImageOnLongClickListener);
        if (VERSION.SDK_INT >= 23) {
            this.notifUserImage.setOnContextClickListener(new OnContextClickListener() {
                public boolean onContextClick(View view) {
                    if (BaseNotificationViewHolder.this.adapterOperationListener != null) {
                        BaseNotificationViewHolder.this.adapterOperationListener.onItemLongClicked(BaseNotificationViewHolder.this.getAdapterPosition());
                    } else {
                        BaseNotificationViewHolder.this.cursorAdapterOperationListener.onItemLongClicked((UacfNotification) BaseNotificationViewHolder.this.itemView.getTag());
                    }
                    return true;
                }
            });
        }
        this.statusContainer.setOnClickListener(this.statusContainerClickListener);
        this.notifUserImage.setOnClickListener(this.notifUserImageClickListener);
    }

    public static /* synthetic */ boolean lambda$setData$0(BaseNotificationViewHolder baseNotificationViewHolder, View view) {
        NotificationsAdapterOperationListener notificationsAdapterOperationListener = baseNotificationViewHolder.adapterOperationListener;
        if (notificationsAdapterOperationListener != null) {
            notificationsAdapterOperationListener.onItemLongClicked(baseNotificationViewHolder.getAdapterPosition());
        } else {
            baseNotificationViewHolder.cursorAdapterOperationListener.onItemLongClicked((UacfNotification) baseNotificationViewHolder.itemView.getTag());
        }
        return true;
    }
}
