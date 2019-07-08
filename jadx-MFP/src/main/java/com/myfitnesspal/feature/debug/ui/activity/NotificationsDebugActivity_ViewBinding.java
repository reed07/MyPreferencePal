package com.myfitnesspal.feature.debug.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NotificationsDebugActivity_ViewBinding implements Unbinder {
    private NotificationsDebugActivity target;

    @UiThread
    public NotificationsDebugActivity_ViewBinding(NotificationsDebugActivity notificationsDebugActivity) {
        this(notificationsDebugActivity, notificationsDebugActivity.getWindow().getDecorView());
    }

    @UiThread
    public NotificationsDebugActivity_ViewBinding(NotificationsDebugActivity notificationsDebugActivity, View view) {
        this.target = notificationsDebugActivity;
        notificationsDebugActivity.notif1 = (Button) Utils.findRequiredViewAsType(view, R.id.notif_1, "field 'notif1'", Button.class);
        notificationsDebugActivity.notif2 = (Button) Utils.findRequiredViewAsType(view, R.id.notif_2, "field 'notif2'", Button.class);
        notificationsDebugActivity.notif3 = (Button) Utils.findRequiredViewAsType(view, R.id.notif_3, "field 'notif3'", Button.class);
        notificationsDebugActivity.notif4 = (Button) Utils.findRequiredViewAsType(view, R.id.notif_4, "field 'notif4'", Button.class);
    }

    @CallSuper
    public void unbind() {
        NotificationsDebugActivity notificationsDebugActivity = this.target;
        if (notificationsDebugActivity != null) {
            this.target = null;
            notificationsDebugActivity.notif1 = null;
            notificationsDebugActivity.notif2 = null;
            notificationsDebugActivity.notif3 = null;
            notificationsDebugActivity.notif4 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
