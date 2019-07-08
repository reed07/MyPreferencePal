package com.myfitnesspal.feature.notificationinbox.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NotificationInboxFragment_ViewBinding implements Unbinder {
    private NotificationInboxFragment target;

    @UiThread
    public NotificationInboxFragment_ViewBinding(NotificationInboxFragment notificationInboxFragment, View view) {
        this.target = notificationInboxFragment;
        notificationInboxFragment.notificationsContainer = Utils.findRequiredView(view, R.id.notifications_container, "field 'notificationsContainer'");
        notificationInboxFragment.notifRefreshContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.notif_refresh_container, "field 'notifRefreshContainer'", SwipeRefreshLayout.class);
        notificationInboxFragment.notificationsView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.notifications_view, "field 'notificationsView'", RecyclerView.class);
        notificationInboxFragment.notifEmptyState = Utils.findRequiredView(view, R.id.notif_empty_state, "field 'notifEmptyState'");
        notificationInboxFragment.tvAddFriends = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_add_friends, "field 'tvAddFriends'", TextView.class);
        notificationInboxFragment.btnNotifUpdate = (Button) Utils.findRequiredViewAsType(view, R.id.btn_notif_update, "field 'btnNotifUpdate'", Button.class);
    }

    @CallSuper
    public void unbind() {
        NotificationInboxFragment notificationInboxFragment = this.target;
        if (notificationInboxFragment != null) {
            this.target = null;
            notificationInboxFragment.notificationsContainer = null;
            notificationInboxFragment.notifRefreshContainer = null;
            notificationInboxFragment.notificationsView = null;
            notificationInboxFragment.notifEmptyState = null;
            notificationInboxFragment.tvAddFriends = null;
            notificationInboxFragment.btnNotifUpdate = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
