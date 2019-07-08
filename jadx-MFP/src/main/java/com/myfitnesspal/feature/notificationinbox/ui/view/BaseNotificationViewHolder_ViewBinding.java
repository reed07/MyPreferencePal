package com.myfitnesspal.feature.notificationinbox.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class BaseNotificationViewHolder_ViewBinding implements Unbinder {
    private BaseNotificationViewHolder target;

    @UiThread
    public BaseNotificationViewHolder_ViewBinding(BaseNotificationViewHolder baseNotificationViewHolder, View view) {
        this.target = baseNotificationViewHolder;
        baseNotificationViewHolder.notifUserImage = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.notif_user_image, "field 'notifUserImage'", MfpImageView.class);
        baseNotificationViewHolder.tvNotifStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_notif_status, "field 'tvNotifStatus'", TextView.class);
        baseNotificationViewHolder.tvNotifTime = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_notif_time, "field 'tvNotifTime'", TextView.class);
        baseNotificationViewHolder.statusContainer = Utils.findRequiredView(view, R.id.status_container, "field 'statusContainer'");
        baseNotificationViewHolder.notifContainer = Utils.findRequiredView(view, R.id.notif_container, "field 'notifContainer'");
    }

    @CallSuper
    public void unbind() {
        BaseNotificationViewHolder baseNotificationViewHolder = this.target;
        if (baseNotificationViewHolder != null) {
            this.target = null;
            baseNotificationViewHolder.notifUserImage = null;
            baseNotificationViewHolder.tvNotifStatus = null;
            baseNotificationViewHolder.tvNotifTime = null;
            baseNotificationViewHolder.statusContainer = null;
            baseNotificationViewHolder.notifContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
