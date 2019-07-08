package com.myfitnesspal.feature.friends.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MessagesActivity_ViewBinding implements Unbinder {
    private MessagesActivity target;

    @UiThread
    public MessagesActivity_ViewBinding(MessagesActivity messagesActivity) {
        this(messagesActivity, messagesActivity.getWindow().getDecorView());
    }

    @UiThread
    public MessagesActivity_ViewBinding(MessagesActivity messagesActivity, View view) {
        this.target = messagesActivity;
        messagesActivity.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
        messagesActivity.messagesPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.messages_pager, "field 'messagesPager'", ViewPager.class);
        messagesActivity.composeMessage = (FloatingActionButton) Utils.findRequiredViewAsType(view, R.id.compose_message, "field 'composeMessage'", FloatingActionButton.class);
    }

    @CallSuper
    public void unbind() {
        MessagesActivity messagesActivity = this.target;
        if (messagesActivity != null) {
            this.target = null;
            messagesActivity.tabLayout = null;
            messagesActivity.messagesPager = null;
            messagesActivity.composeMessage = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
