package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NotificationSettingsView_ViewBinding implements Unbinder {
    private NotificationSettingsView target;

    @UiThread
    public NotificationSettingsView_ViewBinding(NotificationSettingsView notificationSettingsView) {
        this(notificationSettingsView, notificationSettingsView.getWindow().getDecorView());
    }

    @UiThread
    public NotificationSettingsView_ViewBinding(NotificationSettingsView notificationSettingsView, View view) {
        this.target = notificationSettingsView;
        notificationSettingsView.notificationSettingsListView = (ListView) Utils.findRequiredViewAsType(view, R.id.list, "field 'notificationSettingsListView'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        NotificationSettingsView notificationSettingsView = this.target;
        if (notificationSettingsView != null) {
            this.target = null;
            notificationSettingsView.notificationSettingsListView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
