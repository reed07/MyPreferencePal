package com.myfitnesspal.feature.friends.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.EmptyStateView;

public class MessagesFragment_ViewBinding implements Unbinder {
    private MessagesFragment target;

    @UiThread
    public MessagesFragment_ViewBinding(MessagesFragment messagesFragment, View view) {
        this.target = messagesFragment;
        messagesFragment.messagesRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.messages_recycler_view, "field 'messagesRecyclerView'", RecyclerView.class);
        messagesFragment.loadingProgressView = Utils.findRequiredView(view, R.id.loading_progress, "field 'loadingProgressView'");
        messagesFragment.refreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.refresh_layout, "field 'refreshLayout'", SwipeRefreshLayout.class);
        messagesFragment.emptyStateView = (EmptyStateView) Utils.findRequiredViewAsType(view, R.id.empty_state, "field 'emptyStateView'", EmptyStateView.class);
        messagesFragment.spinnerContainer = Utils.findRequiredView(view, R.id.spinner_container, "field 'spinnerContainer'");
        messagesFragment.spinner = (Spinner) Utils.findRequiredViewAsType(view, R.id.spinner, "field 'spinner'", Spinner.class);
    }

    @CallSuper
    public void unbind() {
        MessagesFragment messagesFragment = this.target;
        if (messagesFragment != null) {
            this.target = null;
            messagesFragment.messagesRecyclerView = null;
            messagesFragment.loadingProgressView = null;
            messagesFragment.refreshLayout = null;
            messagesFragment.emptyStateView = null;
            messagesFragment.spinnerContainer = null;
            messagesFragment.spinner = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
