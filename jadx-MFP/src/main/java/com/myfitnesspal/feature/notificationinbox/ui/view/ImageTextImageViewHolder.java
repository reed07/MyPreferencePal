package com.myfitnesspal.feature.notificationinbox.ui.view;

import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.notificationinbox.ui.adapter.NotificationCursorAdapter;
import com.myfitnesspal.feature.notificationinbox.ui.adapter.NotificationsAdapter.NotificationsAdapterOperationListener;
import com.uacf.core.util.Strings;
import io.uacf.inbox.sdk.model.UacfNotification;

public class ImageTextImageViewHolder extends BaseNotificationViewHolder {
    private static final RequestOptions REQUEST_OPTIONS = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
    @BindView(2131363179)
    ImageView notifSecondImage;

    public ImageTextImageViewHolder(ViewGroup viewGroup, NotificationsAdapterOperationListener notificationsAdapterOperationListener, NotificationCursorAdapter.NotificationsAdapterOperationListener notificationsAdapterOperationListener2) {
        super(R.layout.notification_image_text_image_item, viewGroup, notificationsAdapterOperationListener, notificationsAdapterOperationListener2);
    }

    public void setData(UacfNotification uacfNotification, int i) {
        super.setData(uacfNotification, i);
        String secondaryImageUri = uacfNotification.getSecondaryImageUri();
        if (Strings.notEmpty(secondaryImageUri)) {
            Glide.with(this.context).load(secondaryImageUri).apply(REQUEST_OPTIONS).into(this.notifSecondImage);
        }
    }
}
