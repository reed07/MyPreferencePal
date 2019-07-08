package com.myfitnesspal.shared.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EmptyStateView_ViewBinding implements Unbinder {
    private EmptyStateView target;

    @UiThread
    public EmptyStateView_ViewBinding(EmptyStateView emptyStateView) {
        this(emptyStateView, emptyStateView);
    }

    @UiThread
    public EmptyStateView_ViewBinding(EmptyStateView emptyStateView, View view) {
        this.target = emptyStateView;
        emptyStateView.image = (ImageView) Utils.findRequiredViewAsType(view, R.id.empty_state_image, "field 'image'", ImageView.class);
        emptyStateView.title = (TextView) Utils.findRequiredViewAsType(view, R.id.empty_state_title, "field 'title'", TextView.class);
        emptyStateView.message = (TextView) Utils.findRequiredViewAsType(view, R.id.empty_state_message, "field 'message'", TextView.class);
        emptyStateView.primaryButton = (TextView) Utils.findRequiredViewAsType(view, R.id.empty_state_button_primary, "field 'primaryButton'", TextView.class);
        emptyStateView.secondaryButton = (TextView) Utils.findRequiredViewAsType(view, R.id.empty_state_button_secondary, "field 'secondaryButton'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        EmptyStateView emptyStateView = this.target;
        if (emptyStateView != null) {
            this.target = null;
            emptyStateView.image = null;
            emptyStateView.title = null;
            emptyStateView.message = null;
            emptyStateView.primaryButton = null;
            emptyStateView.secondaryButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
